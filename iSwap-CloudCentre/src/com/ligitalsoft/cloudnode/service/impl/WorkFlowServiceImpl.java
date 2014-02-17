package com.ligitalsoft.cloudnode.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.appitemmgr.dao.AppMsgDao;
import com.ligitalsoft.cloudnode.dao.WorkFlowDao;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.datasharexchange.action.Node;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudnode.WorkFlow;
import com.ligitalsoft.webservices.ISwapNodeWS;

@Service("workFlowService")
public class WorkFlowServiceImpl extends BaseSericesImpl<WorkFlow> implements
		IWorkFlowService {
	@Autowired
	private WorkFlowDao workFlowDao;
	@Autowired
	private AppMsgDao appMsgDao;
	@Autowired
	protected DozerBeanMapper mapperValue;

	@Override
	public EntityHibernateDao<WorkFlow> getEntityDao() {
		return workFlowDao;
	}

	/**
	 * 业务流程树(radio)
	 * 
	 * @author fangbin
	 * @return
	 */
	public JSONArray workFlowXTree(Long checkId) {
		  List<Node> nodes = new ArrayList<Node>();
		  Node root = new Node();
	        root.setName("业务流程");
	        root.setId(-1L);
	        root.setPid(0L);
	        root.setOpen(true);
	        nodes.add(root);
		List<AppMsg> appMsgList = appMsgDao.findAll(0, Integer.MAX_VALUE);
		for (AppMsg appMsg : appMsgList) {
			Node node = new Node();
			node.setName(appMsg.getAppName());
			node.setId(appMsg.getId());
			node.setPid(-1L);
			nodes.add(node);
			List<WorkFlow> workflowList = workFlowDao.getAllByAppMsg(appMsg
					.getId());
			for (WorkFlow workflow : workflowList) {
				Node nds = new Node();
				nds.setName(workflow.getWorkFlowName());
				nds.setId(workflow.getId());
				nds.setPid(appMsg.getId());
				nodes.add(nds);
				}
		}
		 JsonConfig jsonConfig = new JsonConfig();
	        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
	            @Override
	            public boolean apply(Object source, String name, Object value) {
	                if (name.equals("name") || name.equals("id") || name.equals("open") || name.equals("pid")||name.equals("checked")) {
	                    return false;
	                } else {
	                    return true;
	                }
	            }
	        });
	        return JSONArray.fromObject(nodes, jsonConfig);
	}

	/**
	 * 工作流程测试
	 * 
	 * @author fangbin
	 * @param workflow
	 * @param Xml
	 * @return
	 */
	@Override
	public String runWorkFlow(Long workflowId, String xml) {
		String msg = "";
		try {
			WorkFlow workFlow = this.findById(workflowId);
			// 组成wsdl地址
			String ip = workFlow.getCloudNodeInfo().getAddress();
			String port = workFlow.getCloudNodeInfo().getPort();
			String url = "http://" + ip + ":" + port
					+ "/iswapnode/webservice/iSwapNodeWS";
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(ISwapNodeWS.class);
			factory.setAddress(url);
			System.setProperty("org.apache.cxf.bus.factory","org.apache.cxf.bus.CXFBusFactory");
			ISwapNodeWS iswapNodeWs = (ISwapNodeWS) factory.create();
			msg = iswapNodeWs.runWorkFlow(workFlow.getWorkFlowCode(), xml);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 更改发布状态 时修改缓存中的部署的流程信息
	 * @param entity
	 * @author hudaowan
	 * @date 2011-9-4 下午05:32:50
	 */
	public void updateStatus(WorkFlow entity) {
		String code = entity.getCloudNodeInfo().getCode();
		String ip = entity.getCloudNodeInfo().getAddress();
		String port = entity.getCloudNodeInfo().getPort();
		String url = "http://" + ip + ":" + port+ "/iswapnode/webservice/iSwapNodeWS";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ISwapNodeWS.class);
		factory.setAddress(url);
		System.setProperty("org.apache.cxf.bus.factory","org.apache.cxf.bus.CXFBusFactory");
		ISwapNodeWS iswapNodeWs = (ISwapNodeWS) factory.create();

		String keyName = entity.getWorkFlowName() + "#"+ entity.getWorkFlowCode() + "#" + code;
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		if ("0".equals(entity.getStatus())) {
			iswapNodeWs.UnDepoyWorkFlow(entity.getWorkFlowCode());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.workflowDB, map);
		} else {
			iswapNodeWs.deployWorkFlow(entity.getWorkFlowCode(),entity.getWorkFlowXml());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.workflowDB, map);
			map.put("workflowcode", entity.getWorkFlowCode());
			map.put("xml", entity.getWorkFlowXml());
			tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.workflowDB, map);
		}
		tool.closeFileDB();
	}
	
	/**
	 * 查询部门下的所有流程
	 * 
	 * @author fangbin
	 * @param deptId 部门Id
	 * @return
	 */
	public List<WorkFlow> findByDeptId(Long deptId){
		return workFlowDao.findByDeptId(deptId);
		
	}
	/**
	 * 查询部门下的所有流程生成ZTree
	 * 
	 * @author fangbin
	 * @param deptId 部门Id
	 * @return
	 */
	public JSONArray workFlowZtreeByDeptId(Long deptId,Long workFlowId){
		List<WorkFlow> workFlowList= workFlowDao.findByDeptId(deptId);
		List<Node> nodes = new ArrayList<Node>();
		Node root = new Node();
		root.setName("流程名称");
		root.setId(-1L);
		root.setPid(0L);
		root.setOpen(true);
		nodes.add(root);
		for (WorkFlow workFlow : workFlowList) {
			Node node = new Node();
			node.setId(workFlow.getId());
			node.setName(workFlow.getWorkFlowName());
			if(null!=workFlowId&&workFlowId!=0L&workFlow.getId().equals(workFlowId)){
				node.setChecked(true);
			}
			node.setPid(-1L);
			nodes.add(node);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("pid")|| name.equals("open")||name.equals("checked")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, jsonConfig);
		
	}
	@Override
	public List<WorkFlow> findListByItemId(Long itemId) {
		return workFlowDao.findListByItemId(itemId);
	}

	public List<WorkFlow>  findByProperty(String propertyName, String value) throws ServiceException{
		  List<WorkFlow> workflw_list = new ArrayList<WorkFlow>();
		  List<WorkFlow> list = new ArrayList<WorkFlow>();
		  String[] names = new String[1];
		  String[] values = new String[1];
		  names[0] = propertyName;
		  values[0] = value;
		  list = getEntityDao().findListByProperty(names, values, 0, Integer.MAX_VALUE);
		  for(WorkFlow esbt:list){
			  WorkFlow workFlow = new WorkFlow();
			  CloudNodeInfo cloInfo = new CloudNodeInfo();
			  cloInfo.setAddress(esbt.getCloudNodeInfo().getAddress());
			  workFlow.setCloudNodeInfo(cloInfo);
			  workFlow.setWorkFlowName(esbt.getWorkFlowName());
			  workFlow.setWorkFlowCode(esbt.getWorkFlowCode());
			  workFlow.setWorkFlowXml(esbt.getWorkFlowXml());
			  workflw_list.add(workFlow);
		  }
		  return workflw_list;
	}
	
	/**
	 * 修改流程的名称    
	 */
	protected void onUpdateBefore(WorkFlow entity) {
		 if(!StringUtils.isBlank(entity.getWorkFlowXml())){
			 entity.setShowXml(showXmlLoad(new String(entity.getShowXml()),entity.getWorkFlowCode()).getBytes());
			 entity.setWorkFlowXml(showXmlLoad(entity.getWorkFlowXml(),entity.getWorkFlowCode()));
		 }
	 }
	     
	
	/**
	 * 解析xml重置流程的英文名称
	 * @return 
	 * @author  hudaowan
	 * @date 2011-10-27 下午07:42:41
	 */
	private String showXmlLoad(String xml,String wfcode) {
		String wf_xml = "";
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement(); 
			Attribute attr_id = root.attribute("name");
			root.remove(attr_id);
			root.addAttribute("name",wfcode);
			wf_xml = document.asXML();
		} catch (Exception e) {
			logger.error("解析xml失败", e);
		}
		return wf_xml;
	}
}
