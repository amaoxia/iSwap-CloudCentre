/*
 * @(#)ChangeItemServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.common.StringConvertion;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.datasharexchange.action.Node;
import com.ligitalsoft.datasharexchange.dao.ChangeItemAppMsgDao;
import com.ligitalsoft.datasharexchange.dao.ChangeItemCycleDao;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDao;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDocumentDao;
import com.ligitalsoft.datasharexchange.dao.ChangeItemTemplateDao;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.datasharexchange.service.ITaskService;
import com.ligitalsoft.defcat.webservice.CatalogWebService;
import com.ligitalsoft.defcat.webservice.CatalogWebServicePortClient;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ChangeItemAppMsg;
import com.ligitalsoft.model.changemanage.ChangeItemCycle;
import com.ligitalsoft.model.changemanage.ChangeItemDocument;
import com.ligitalsoft.model.changemanage.ChangeItemTemplate;
import com.ligitalsoft.model.cloudnode.WorkFlow;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.util.Costant;
import com.ligitalsoft.sysmanager.util.FileUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 交换_指标 实现类
 * @author zhangx
 * @since Jun 14, 2011 3:25:56 PM
 * @name com.ligitalsoft.cloudstorage.service.impl.ChangeItemServiceImpl.java
 * @version 1.0
 */
@Service("changeItemService")
public class ChangeItemServiceImpl extends BaseSericesImpl<ChangeItem> implements IChangeItemService {

    private ChangeItemDao changeItemDao;
    private ChangeItemAppMsgDao changeItemAppMsgDao;
    private ChangeItemDocumentDao changeItemDocumentDao;
    private ChangeItemTemplateDao changeItemTemplateDao;
    private ChangeItemCycleDao changeItemCycleDao;

    private ISysUserDeptService sysUserDeptService;

    private ISysDeptService deptService;
    
    private ITaskService taskService;
    private IWorkFlowService workFlowService;

    /*
     * (non-Javadoc)
     * @see com.ligitalsoft.cloudstorage.service.IMetaDataService#save(com.ligitalsoft.model.cloudstorage.MetaData,
     * java.lang.Long[])
     */
    @Override
    public void save(ChangeItem changeItem, String[] appId, ChangeItemCycle cycle) {
    	changeItem.setChangeItemCycle(cycle);
    	if(cycle!=null){
    		changeItemCycleDao.save(cycle);
    	}
        changeItemDao.save(changeItem);// 保存元数据对象
        /*if (appId != null && appId.length > 0) {
            for (int i = 0; i < appId.length; i++) {
                ChangeItemAppMsg chItemAppMsg = new ChangeItemAppMsg();
                AppMsg appMsg = new AppMsg();// 创建服务应用对象
                if (!StringUtils.isBlank(appId[i])) {
                    appMsg.setId(Long.parseLong(appId[i]));// 设置服务ID
                }
                chItemAppMsg.setAppMsg(appMsg);
                chItemAppMsg.setChangeItem(changeItem);
                changeItemAppMsgDao.save(chItemAppMsg);
                changeItemAppMsgDao.getSession().flush();// 连续写入操作
            }
        }
        if (!StringUtils.isBlank(changeItem.getDataType())) {
            if (changeItem.getDataType().toString().equals("0")) {// 数据源
                ChangeItemTemplate template = new ChangeItemTemplate();
                //
                template.setChangeItem(changeItem);
                changeItemTemplateDao.save(template);
            }
        }*/
    }

    @Override
    public void update(ChangeItem changeItem, String[] appId, ChangeItemCycle cycle) {
        // //////////////操作
        // if(!StringUtils.isBlank(changeItem.getDataType())){
        // if(changeItem.getDataType().toString().equals("0")){//文档对象
        // changeItem.setDataSource(null);
        // changeItem.setTableName("");
        // }
        // }
    	
    	if(cycle!=null){
	        changeItemCycleDao.saveOrUpdate(cycle);// 修改规则
    	}
    	changeItem.setChangeItemCycle(cycle);
    	changeItemDao.update(changeItem);
        /*changeItemAppMsgDao.deleteByItemId(changeItem.getId());
        if (appId != null && appId.length > 0) {
            for (int i = 0; i < appId.length; i++) {
                ChangeItemAppMsg chItemAppMsg = new ChangeItemAppMsg();
                AppMsg appMsg = new AppMsg();// 创建服务应用对象
                if (!StringUtils.isBlank(appId[i])) {
                    appMsg.setId(Long.parseLong(appId[i]));// 设置服务ID
                }
                chItemAppMsg.setAppMsg(appMsg);
                chItemAppMsg.setChangeItem(changeItem);
                changeItemAppMsgDao.saveOrUpdate(chItemAppMsg);
                changeItemAppMsgDao.getSession().flush();// 连续写入操作
            }
        }
        if (!StringUtils.isBlank(changeItem.getDataType())) {
            ChangeItemTemplate template = changeItemTemplateDao.findByItemId(changeItem.getId());// 指标模板对象
            if (changeItem.getDataType().toString().equals("0")) {// 文档对象
                // ChangeItem item=changeItemDao.findById(changeItem.getId());//指标对象
                if (template == null) {
                    template = new ChangeItemTemplate();
                }
                // 模板
                // template.setTemplateName(changeItem.getItemName());
                template.setChangeItem(changeItem);
                // 修改操作
                changeItemTemplateDao.saveOrUpdate(template);
            } else {
                if (template != null) {
                    // 删除文件
                    if (!StringUtils.isBlank(template.getTemplatePath())) {
                        FileUtil.deleOnefile(template.getTemplatePath());
                    }
                }
                changeItemTemplateDao.deleteByItemId(changeItem.getId());
            }
        }*/
    }

    @Override
    public List<ChangeItem> findListByDataType(String dataType, Long deptId) {
        if (deptId != null) {// 中心部门
            return changeItemDao.findListByDataTypeDeptId(dataType,deptId);
        } else {
            String[] names = { "dataType" }; 	
            String[] values = { dataType };
            return changeItemDao.findListByProperty(names, values, -1, -1);
        }
    }

    @Override
    public void deleteAllByIds(Serializable[] ids) throws ServiceException {
        for (Serializable id : ids) {
            ChangeItem item = findById(id);
            List<ChangeItemTemplate> template = item.getTemplate();
            if (template != null) {// 模板对象
                for (ChangeItemTemplate changeItemTemplate : template) {
                    if (!StringUtils.isBlank(changeItemTemplate.getTemplatePath())) {
                        FileUtil.deleOnefile(changeItemTemplate.getTemplatePath());// 删除模板
                    }
                    changeItemTemplateDao.remove(changeItemTemplate);
                }
            }
            List<ChangeItemDocument> document = item.getDocument();
            if (document != null) {// 文档对象
                for (ChangeItemDocument changeItemDocument : document) {
                    if (!StringUtils.isBlank(changeItemDocument.getUploadPath())) {
                        FileUtil.deleOnefile(changeItemDocument.getUploadPath());// 删除文档
                    }
                    changeItemDocumentDao.remove(changeItemDocument);
                }
            }
            changeItemDao.remove(item);// 删除文件
            changeItemDao.getSession().flush();
        }
    }

    @Override
    public void pushToCatalog(String itemIds, Long catalogId, String categoryIds) {
        Document document = DocumentHelper.createDocument();
        Element resources = document.addElement("resources");
        Long[] itemIdss = StringConvertion.convertionToLongs(itemIds, ",");
        for (Long itemId : itemIdss) {
            ChangeItem item = changeItemDao.findById(itemId);
            if(item.isHasProped()){
                continue;
            }
            Element resource = resources.addElement("resource");
            resource.addAttribute("temCode", "JHSJMLMB");
            // 基本元数据
            Element BaseMetadata = resource.addElement("BaseMetadata");
            BaseMetadata.addElement("restitle").setText(item.getItemName());
            BaseMetadata.addElement("mataClass").setText("1");
            BaseMetadata.addElement("catalogId").setText(catalogId + "");
            BaseMetadata.addElement("state").setText("0");
            BaseMetadata.addElement("deptUid").setText(getSysDeptId());
            BaseMetadata.addElement("createDept");
            BaseMetadata.addElement("resid");
            BaseMetadata.addElement("mdid");
            BaseMetadata.addElement("creator")
                            .setText(((SysUser) ActionContext.getContext().getSession().get(Costant.SESSION_USER))
                                            .getUserName());
            // 关键字
            Element EdesckEys = BaseMetadata.addElement("EdesckEys");
            EdesckEys.addElement("metadata");
            // 在线资源地址
            Element EonlineSrc = BaseMetadata.addElement("EonlineSrc");
            EonlineSrc.addElement("metadata");

            // 交换元数据
            Element SwapMetaData = resource.addElement("SwapMetaData");
            SwapMetaData.addElement("exchangefrequency").setText(item.getChangeItemCycle().makeExchangeCycleDate());
            SwapMetaData.addElement("exchangeType").setText(item.getDataType().equals("0") ? "文档" : "数据源");
            SwapMetaData.addElement("fileType").setText(item.getDataValue() == null ? "" : item.getDataValue());
            SwapMetaData.addElement("dataBaseType").setText("com.mysql.jdbc.Driver");
            SwapMetaData.addElement("baseMetadata");
            // 需求方
            Element EreqPar = SwapMetaData.addElement("EreqPar");
            EreqPar.addElement("SwapMetaData");
            // 指标Mapper
            Element Emapper = SwapMetaData.addElement("Emapper");
            Emapper.addElement("SwapMetaData");
            // 指标流程
            Element EindicantProcess = SwapMetaData.addElement("EindicantProcess");
            EindicantProcess.addElement("SwapMetaData");

            // 国家核心元数据
            Element MainMetadata = resource.addElement("MainMetadata");
            // 资源负责方
            Element Eidpoc = MainMetadata.addElement("Eidpoc");
            Eidpoc.addElement("rpOrgName").setText(item.getSysDept().getDeptName());
            Eidpoc.addElement("cntAdd").setText(item.getSysDept().getAddress()==null?"":item.getSysDept().getAddress());
            Eidpoc.addElement("MainMetadata");
            // 元数据联系方
            Element Emdcontact = MainMetadata.addElement("Emdcontact");
            Emdcontact.addElement("MainMetadata");
            // 资源格式信息
            Element Efminfo = MainMetadata.addElement("Efminfo");
            Efminfo.addElement("MainMetadata");

            MainMetadata.addElement("baseMetadata");
            item.setHasProped(true);
        }
        getService().changeItemTran(document.asXML(), categoryIds, catalogId);
    }

    private String getSysDeptId() {
        SysUser user = (SysUser) ActionContext.getContext().getSession().get(Costant.SESSION_USER);
        SysDept sysDept = null;
        try {
            sysDept = deptService.findById(sysUserDeptService.findByUserId(user.getId()).getDeptId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return sysDept.getDeptUid();
    }
    public CatalogWebService getService() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config/config.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException("得不到文件", e);
        }
        String catalogWebserviceUrl = prop.getProperty("catalogWebserviceUrl");
        return CatalogWebServicePortClient.getCatalogWebService(catalogWebserviceUrl);
    }
    
    /**
     * 根据部门ID查询数据交换总量和按时次数
	 * @author fangbin
     */
    @Override
	public List<Map<String, String>> targetDataShare(String deptId) {
    	List<Map<String,String>> maplist=new ArrayList<Map<String,String>>();
		try {
			maplist= changeItemDao.targetDataShare(deptId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maplist;
	}
    /**
     * 获得所有部门下的指标项树
     * @author fangbin
     * 
     */
    public JSONArray getItemTree(Long itemId){
    	List<SysDept> deptList =deptService.findAll();
		List<Node> nodes = new ArrayList<Node>();
		Node root = new Node();
		root.setName("指标名称");
		root.setId(-1L);
		root.setPid(0L);
		root.setOpen(true);
		nodes.add(root);
		for (SysDept dept : deptList) {
			Node node = new Node();
			node.setId(dept.getId());
			node.setName(dept.getDeptName());
			List<ChangeItem> itemList=changeItemDao.findListByDeptId(dept.getId());
			for(ChangeItem item :itemList){
				Node nodeitem = new Node();
				nodeitem.setId(item.getId());
				nodeitem.setName(item.getItemName());
				if(null!=itemId&&itemId!=0L&item.getId().equals(itemId)){
					node.setChecked(true);
				}
				nodeitem.setPid(dept.getId());
				nodes.add(nodeitem);
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
    
    public JSONArray getItemTree(Long deptId, Long itemId){
		List<SysDept> depts = deptService.findAll();
		List<Node> node = new ArrayList<Node>();
		
		for (SysDept sysDept : depts) {
			if (sysDept.getId()!=null&&!sysDept.getId().equals(deptId)) {
				
				List<ChangeItem> items = changeItemDao.findListByDeptId(sysDept.getId(), 1);// 当前部门下所有指标
				
				if ((sysDept.getChildrenDept()!=null&&sysDept.getChildrenDept().size()>0) || (items != null && items.size()>0)) {
					Node deptNode = new Node();
					deptNode.setId(-sysDept.getId());
					deptNode.setName(sysDept.getDeptName());
					deptNode.setPid(sysDept.getSysDept()!=null?-sysDept.getSysDept().getId():-0);
					deptNode.setOpen(false);
					node.add(deptNode);
					
					for (ChangeItem item : items) {
						Node itemNode = new Node();
						itemNode.setId(item.getId());
						itemNode.setName(item.getItemName());
						itemNode.setPid(-sysDept.getId());
						node.add(itemNode);
					}
				}
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {

			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("pid")|| name.equals("open")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(node, jsonConfig);
	}
    
    @Override
    public EntityHibernateDao<ChangeItem> getEntityDao() {
        return changeItemDao;
    }
    @Autowired
    public void setChangeItemDao(ChangeItemDao changeItemDao) {
        this.changeItemDao = changeItemDao;
    }

    @Autowired
    public void setChangeItemDocumentDao(ChangeItemDocumentDao changeItemDocumentDao) {
        this.changeItemDocumentDao = changeItemDocumentDao;
    }

    @Autowired
    public void setChangeItemTemplateDao(ChangeItemTemplateDao changeItemTemplateDao) {
        this.changeItemTemplateDao = changeItemTemplateDao;
    }
    @Autowired
    public void setChangeItemAppMsgDao(ChangeItemAppMsgDao changeItemAppMsgDao) {
        this.changeItemAppMsgDao = changeItemAppMsgDao;
    }
    @Autowired
    public void setChangeItemCycleDao(ChangeItemCycleDao changeItemCycleDao) {
        this.changeItemCycleDao = changeItemCycleDao;
    }

    @Override
    public List<ChangeItem> findListByDeptId(Long deptId) {
        return changeItemDao.findListByDeptId(deptId);
    }

    public ISysUserDeptService getSysUserDeptService() {
        return sysUserDeptService;
    }

    @Autowired
    public void setSysUserDeptService(ISysUserDeptService sysUserDeptService) {
        this.sysUserDeptService = sysUserDeptService;
    }

    public ISysDeptService getDeptService() {
        return deptService;
    }

    @Autowired
    public void setDeptService(ISysDeptService deptService) {
        this.deptService = deptService;
    }

	public void setWorkFlowService(IWorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	@Override
	public ChangeItem findChangeItemById(Long id) {
		ChangeItem changeItem = changeItemDao.findById(id);
		return changeItem;
	}

	@Autowired
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	@Override
	public JSONArray getAppItemTreeByDeptId4Node(Long deptId) {
		List<com.common.utils.tree.ztree.Node> nodes = new ArrayList<com.common.utils.tree.ztree.Node>();
		com.common.utils.tree.ztree.Node root = new com.common.utils.tree.ztree.Node();
		root.setName("应用");
		root.setId(-1 + "");
		root.setPid(0 + "");
		root.setOpen(true);
		nodes.add(root);
		
		List<ChangeItem> changeItemList = changeItemDao.findListByDeptId(deptId);
		Map<String, com.common.utils.tree.ztree.Node> map = new HashMap<String, com.common.utils.tree.ztree.Node>();
		Map<String, ChangeItem> sendChangeItemMap = new HashMap<String, ChangeItem>();
		Map<String, List<com.common.utils.tree.ztree.Node>> receiveChangeItemMap = new HashMap<String, List<com.common.utils.tree.ztree.Node>>();
		if(changeItemList!=null&&changeItemList.size()>0){
			/** 该段代码用来输出类似于 发送(维扬区) **/
			for(ChangeItem changeItem : changeItemList){
				if(changeItem.getItemType().equals(3))continue;
				/** 该段代码用来输出类似于 发送(维扬区) **/
				if(changeItem.getItemType().equals(2)){
					ChangeItem sendChangeItem = sendChangeItemMap.get(changeItem.getAppItemExchangeConf().getId()+"");
					if(sendChangeItem==null){
						sendChangeItem = findSendChangeItemByChangeConfId(1, null, changeItem.getAppItemExchangeConf().getId());
						sendChangeItemMap.put(changeItem.getAppItemExchangeConf().getId()+"", sendChangeItem);
					}
				}
				/*if(changeItem.getItemType().equals(1)){
					if(!sendChangeItemMap.containsKey(changeItem.getAppItemExchangeConf().getId()+"")){
						sendChangeItemMap.put(changeItem.getAppItemExchangeConf().getId()+"", changeItem);
						List<com.common.utils.tree.ztree.Node> nodeList = receiveChangeItemMap.get(changeItem.getAppItemExchangeConf().getId()+"");
						if(nodeList!=null){
							for(com.common.utils.tree.ztree.Node n : nodeList){
								n.setName(n.getName()+"("+changeItem.getSysDept().getDeptName()+")");
							}
						}
						receiveChangeItemMap.remove(changeItem.getAppItemExchangeConf().getId()+"");
					}
				}*/
				/** 该段代码用来输出类似于 发送(维扬区) **/
				String appId = changeItem.getAppItemExchangeConf().getAppMsg().getId()+"";
				if(!map.containsKey(appId)){
					com.common.utils.tree.ztree.Node appNode = new com.common.utils.tree.ztree.Node();
					appNode.setId(appId + "");
					appNode.setName(changeItem.getAppItemExchangeConf().getAppMsg().getAppName());
					appNode.setPid("-1");
					nodes.add(appNode);
					map.put(appId+"", appNode);
				}
				
				String appItemId = appId+"-"+changeItem.getAppItemExchangeConf().getAppItem().getId();
				if(!map.containsKey(appItemId)){
					com.common.utils.tree.ztree.Node itemNode = new com.common.utils.tree.ztree.Node();
					itemNode.setId(appItemId);
					itemNode.setName(changeItem.getAppItemExchangeConf().getAppItem().getAppItemName());
					itemNode.setPid(appId+"");
					nodes.add(itemNode);
					map.put(appItemId, itemNode);
				}
				
				com.common.utils.tree.ztree.Node itemTypeNode = new com.common.utils.tree.ztree.Node();
				itemTypeNode.setId(appItemId+"-"+changeItem.getItemType());
				itemTypeNode.setName(changeItem.getItemTypeStr());
				itemTypeNode.setPid(appItemId);
				itemTypeNode.setUri(changeItem.getId()+"");
				itemTypeNode.setData("接收者：");
				/** 该段代码用来输出类似于 发送(维扬区) **/
				if(changeItem.getItemType().equals(2)){
					ChangeItem sendChangeItem = sendChangeItemMap.get(changeItem.getAppItemExchangeConf().getId()+"");
					if(sendChangeItem!=null){
						itemTypeNode.setName(itemTypeNode.getName()+"("+sendChangeItem.getSysDept().getDeptName()+")");
					}/*else{
						List<com.common.utils.tree.ztree.Node> nodeList = receiveChangeItemMap.get(changeItem.getAppItemExchangeConf().getId()+"");
						if(nodeList==null)nodeList = new ArrayList<com.common.utils.tree.ztree.Node>();
						nodeList.add(itemTypeNode);
						receiveChangeItemMap.put(changeItem.getAppItemExchangeConf().getId()+"", nodeList);
					}*/
				}
				/** 该段代码用来输出类似于 发送(维扬区) **/
				nodes.add(itemTypeNode);
			}
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {

				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("url")
						|| name.equals("open")|| name.equals("data")
						|| name.equals("uri")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, jsonConfig);
	}

	@Override
	public JSONArray getAppItemTreeByDeptId4Center() {
		List<com.common.utils.tree.ztree.Node> nodes = new ArrayList<com.common.utils.tree.ztree.Node>();
		com.common.utils.tree.ztree.Node root = new com.common.utils.tree.ztree.Node();
		root.setName("应用");
		root.setId(-1 + "");
		root.setPid(0 + "");
		root.setOpen(true);
		nodes.add(root);
		
		List<ChangeItem> changeItemList = changeItemDao.findListByItemType(new Integer[]{1,3});
		Map<String, com.common.utils.tree.ztree.Node> map = new HashMap<String, com.common.utils.tree.ztree.Node>();
		if(changeItemList!=null&&changeItemList.size()>0){
			for(ChangeItem changeItem : changeItemList){
				String appId = changeItem.getAppItemExchangeConf().getAppMsg().getId()+"";
				if(!map.containsKey(appId)){
					com.common.utils.tree.ztree.Node appNode = new com.common.utils.tree.ztree.Node();
					appNode.setId(appId + "");
					appNode.setName(changeItem.getAppItemExchangeConf().getAppMsg().getAppName());
					appNode.setPid("-1");
					nodes.add(appNode);
					map.put(appId+"", appNode);
				}
				
				String appItemId = appId+"-"+changeItem.getAppItemExchangeConf().getAppItem().getId();
				if(!map.containsKey(appItemId)){
					com.common.utils.tree.ztree.Node itemNode = new com.common.utils.tree.ztree.Node();
					itemNode.setId(appItemId);
					itemNode.setName(changeItem.getAppItemExchangeConf().getAppItem().getAppItemName());
					itemNode.setPid(appId+"");
					nodes.add(itemNode);
					map.put(appItemId, itemNode);
				}
				
				com.common.utils.tree.ztree.Node itemTypeNode = new com.common.utils.tree.ztree.Node();
				itemTypeNode.setId(appItemId+"-"+changeItem.getItemType());
				itemTypeNode.setName(changeItem.getItemTypeStr()+"("+changeItem.getSysDept().getDeptName()+")");
				itemTypeNode.setPid(appItemId);
				itemTypeNode.setUri(changeItem.getId()+"");
				itemTypeNode.setData("接收者：");
				nodes.add(itemTypeNode);
			}
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {

				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("url")
						|| name.equals("open")|| name.equals("data")
						|| name.equals("uri")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, jsonConfig);
	}

	@Override
	public ChangeItem findSendChangeItemByChangeConfId(Integer itemType, Integer dataType, Long changeConfId) {
		String hql = "from ChangeItem e where ";
		if(itemType!=null)hql+=" e.itemType="+itemType;
		if(dataType!=null)hql+=" and e.dataType="+dataType;
		hql+=" and e.appItemExchangeConf.id=?";
		Object object = changeItemDao.findUniqueByHql(hql, changeConfId);
		if(object!=null)return (ChangeItem)object;
		return null;
	}

	//强制删除指标,包括删除所有关联的流程、任务,此删除只能删除接收类型指标
	@Override
	public void forcedDelete(Long[] ids, String delType) {
		//强制删除,将删除所有关联的流程、任务
		//若是发送指标,则需保证没有对应的接收指标才可删除
		if(ids==null||ids.length<=0)return;
		for(Long id : ids){
			if("1".equals(delType)){//发送指标
				//查看是否有对应的接收指标
				if(true){
					//删除交换规则
					//删除表结构
					//删除数据源
				}else{
					continue;
				}
	        }else {//接收指标
	        }
			//删除任务,包括前置机和中心
		 	//taskService.forcedDeleteByByItemId(id);
			//删除流程及中心流程,包括mongoDB
		 	//workFlowService.forcedDeleteByByItemId(id);
			//删除指标
		 	changeItemDao.removeById(id);
		}
	}
}
