package com.ligitalsoft.cloudnode.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.QueryPara;
import com.common.framework.dao.SortPara;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.cloudnode.dao.NodeTaskMsgDao;
import com.ligitalsoft.cloudnode.service.INodeTaskMsgService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudnode.NodeTaskMsg;
import com.ligitalsoft.model.cloudnode.WorkFlow;
import com.ligitalsoft.webservices.ISwapNodeWS;

@Service("nodeTaskMsgService")
public class NodeTaskMsgServiceImpl extends BaseSericesImpl<NodeTaskMsg> implements INodeTaskMsgService {

	@Autowired
	private NodeTaskMsgDao nodeTaskMsgDao;
	@Autowired
	protected DozerBeanMapper mapperValue;

	@Override
	public EntityHibernateDao<NodeTaskMsg> getEntityDao() {
		return nodeTaskMsgDao;
	}

	/**
	 * 完成对象之间拷贝
	 * @author hudaowan
	 * @date 2010-9-15 下午12:46:16
	 * @param source
	 * @param destination
	 */
	protected void doValueCopy(Object source, Object destination) {// 将源对象中的值copy到目标对象中
		this.mapperValue.map(source, destination);
	}
	
	/**
	 * 添加生成的cron表达式
	 */
	protected void onInsertBefore(NodeTaskMsg entity) {
		
	}
	   
	/**
	 * 添加生成的cron表达式
	 */
    protected void onUpdateBefore(NodeTaskMsg entity) {
    	
    }
    
	/**
	 *将启动的任务添加到缓存中
	 */
	public void updateStatus(NodeTaskMsg entity) {
		String code = entity.getWorkFlow().getCloudNodeInfo().getCode();
		String ip = entity.getWorkFlow().getCloudNodeInfo().getAddress();
		String port = entity.getWorkFlow().getCloudNodeInfo().getPort();
		String keyName = entity.getTaskName()+"#"+entity.getWorkFlow().getWorkFlowCode()+"#"+code;
		String url = "http://" + ip + ":" + port
		+ "/iswapnode/webservice/iSwapNodeWS";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ISwapNodeWS.class);
		factory.setAddress(url);
		System.setProperty("org.apache.cxf.bus.factory","org.apache.cxf.bus.CXFBusFactory");
		ISwapNodeWS iswapNodeWs = (ISwapNodeWS) factory.create();
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
    	if("0".equals(entity.getStatus())){
    		Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.jobInfoDB, map);
    		iswapNodeWs.stopWorkFlowTask(keyName);
    	}else{
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("key", keyName);
    		tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.jobInfoDB, map);
    		map.put("cron", entity.getCron());
    		map.put("message",entity.getMessage());
    		tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.jobInfoDB, map);
    		iswapNodeWs.runWorkFlowTask(keyName, entity.getCron());
    	}
    	tool.closeFileDB();
	}
	
	
	public List<NodeTaskMsg>  findByProperty(String propertyName, String value) throws ServiceException{
		  List<NodeTaskMsg> node_list = new ArrayList<NodeTaskMsg>();
		  List<NodeTaskMsg> list = new ArrayList<NodeTaskMsg>();
		  String[] names = new String[1];
		  String[] values = new String[1];
		  names[0] = propertyName;
		  values[0] = value;
		  list = getEntityDao().findListByProperty(names, values, 0, Integer.MAX_VALUE);
		  for(NodeTaskMsg esbt:list){
			  NodeTaskMsg nodetask = new NodeTaskMsg();
			  WorkFlow workFlow = new WorkFlow();
			  CloudNodeInfo cloInfo = new CloudNodeInfo();
			  nodetask.setCron(esbt.getCron());
			  nodetask.setMessage(esbt.getMessage());
			  nodetask.setTaskName(esbt.getTaskName());
			  cloInfo.setAddress(esbt.getWorkFlow().getCloudNodeInfo().getAddress());
			  workFlow.setCloudNodeInfo(cloInfo);
			  workFlow.setWorkFlowName(esbt.getWorkFlow().getWorkFlowName());
			  workFlow.setWorkFlowCode(esbt.getWorkFlow().getWorkFlowCode());
			  nodetask.setWorkFlow(workFlow);
			  node_list.add(nodetask);
		  }
		  return node_list;
	}

	@Override
	public List<NodeTaskMsg> findNodeTaskMsgRightJoinWorkFlowList(
			List<QueryPara> queryParas, List<SortPara> sortParas, PageBean page) throws ServiceException {
		List<NodeTaskMsg> nodeTaskMsglist = new ArrayList<NodeTaskMsg>();
		String mainHql = "from NodeTaskMsg e right join e.workFlow c"; 
		List list = this.findAllByPage(mainHql, queryParas, sortParas, page);
         if(list!=null&&list.size()>0){
         	for(Object obj : list){
         		Object[] objArray = (Object[])obj;
         		NodeTaskMsg nodeTaskMsg = new NodeTaskMsg();
         		if(objArray[0]!=null)nodeTaskMsg = (NodeTaskMsg)objArray[0];
         		if(objArray[1]!=null)nodeTaskMsg.setWorkFlow((WorkFlow)objArray[1]);
         		nodeTaskMsglist.add(nodeTaskMsg);
         	}
         }
		return nodeTaskMsglist;
	}

}
