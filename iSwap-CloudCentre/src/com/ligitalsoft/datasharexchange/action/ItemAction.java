/*
 * @(#)ItemAction.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.ServletUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudnode.service.IDataSourceService;
import com.ligitalsoft.datasharexchange.service.IChangeItemAppMsgService;
import com.ligitalsoft.datasharexchange.service.IChangeItemCycleService;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.defcat.webservice.CatalogJson;
import com.ligitalsoft.defcat.webservice.CatalogWebService;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConfDetails;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ChangeItemAppMsg;
import com.ligitalsoft.model.changemanage.ChangeItemCycle;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.model.system.SysUserRole;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 交换_指标ACTION
 * @author zhangx
 * @since Jun 14, 2011 3:28:32 PM
 * @name com.ligitalsoft.cloudstorage.action.ItemAction.java
 * @version 1.0
 */
@Scope("prototype")
@Namespace("/exchange/item")
@Results({ @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
        @Result(name = "selectCatalog", location = "selectCatalog.ftl", type = "freemarker"),
        @Result(name = "catalogTreePage", location = "catalogTreePage.ftl", type = "freemarker"),
        @Result(name = "catalogTree", location = "catalogTree.ftl", type = "freemarker"),
        @Result(name = "pushToCatalogOk", location = "pushToCatalogOk.ftl", type = "freemarker"),
        @Result(name = "listAction", location = "item!list.action", type = "redirectAction", params = {"deptid", "${deptid}","deptName", "${deptName}"}) })
public class ItemAction extends FreemarkerBaseAction<ChangeItem> {

    private static final long serialVersionUID = 5854980905054524517L;
    // service
    private IChangeItemService changeItemService;
    private IDataSourceService dataSourceService;
    @Autowired
	private ISysUserRoleService sysUserRoleService;
    @Autowired
	private ISysUserDeptService sysUserDeptService;
    @Autowired
	private ISysDeptService sysDeptService;

    private IChangeItemAppMsgService changeItemAppMsgService;
    private IChangeItemCycleService changeItemCycleService;
    private List<DataSource> dataSources = new ArrayList<DataSource>();
    private List<AppMsg> appMsgs = new ArrayList<AppMsg>();// 应用服务
    private AppMsgService appMsgService;
    private List<ChangeItemAppMsg> itemApp = new ArrayList<ChangeItemAppMsg>();// 元数据应用服务
    private ChangeItemCycle cycle;// 指标交换规则
    // /property
    private String deptName;// 部门名称
    private String deptid;//部门id
    
    private List<ChangeItem> itemlist;
    private String dataXml;
    List<CatalogJson> catalogs;
    private Long catalogId;
    private String itemIds;
    private String categoryIds;
    private List<SysDept> depts = null;// 部门集合对象

    /**
     * 添加视图之前
     */
    @Override
    protected void onBeforeAddView() {
    	/*// 获得当前登陆用户
		SysUser user = (SysUser) getSession().get(Costant.SESSION_USER);
		// 获得当前用户角色
		SysUserRole role = sysUserRoleService.findByUserId(user.getId());
		
		SysDept sysDept = null;
		// 如果roleId大于2者为其它部门角色否则为信息中心人员
		if (role.getRoleId() > 2) {
			// 通过当前用户ID查询用户所属部门
			SysUserDept sysUserDept = sysUserDeptService.findByUserId(user
					.getId());
			// cniList =
			// cloudNodeInfoService.findListNodeByDeptId(sysUserDept.getId());
			
			try {
				sysDept = sysDeptService.findById(sysUserDept.getDeptId());
				dataSources = dataSourceService.findDataSourcesByDept("1",sysDept.getId());
				//appMsgs = appMsgService.findAll();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			depts = new ArrayList<SysDept>();
			depts.add(sysDept);
		}else {
			try {
				 dataSources = dataSourceService.findByProperty("status", "1");
			} catch (ServiceException e) {
				log.error("[ItemAction onBeforeAddView]查找数据源失败!",e);
			}
			// cniList = cloudNodeInfoService.findListNodeByDeptId(null);
		}*/
    	if(!StringUtils.isBlank(getDeptid())){
    		dataSources = dataSourceService.findDataSourcesByDept("1", new Long(getDeptid()));
    	}
    }
    /**
     * 修改视图之前
     */
    @Override
    protected void onBeforeUpdateView() {
    	/*// 获得当前登陆用户
		SysUser user = (SysUser) getSession().get(Costant.SESSION_USER);
		// 获得当前用户角色
		SysUserRole role = sysUserRoleService.findByUserId(user.getId());
		
		SysDept sysDept = null;
		// 如果roleId大于2者为其它部门角色否则为信息中心人员
		if (role.getRoleId() > 2) {
			// 通过当前用户ID查询用户所属部门
			SysUserDept sysUserDept = sysUserDeptService.findByUserId(user
					.getId());
			// cniList =
			// cloudNodeInfoService.findListNodeByDeptId(sysUserDept.getId());
			
			try {
				sysDept = sysDeptService.findById(sysUserDept.getDeptId());
				dataSources = dataSourceService.findDataSourcesByDept("1",sysDept.getId());
				//appMsgs = appMsgService.findAll();
			} catch (ServiceException e) {
				log.error("数据源注册页面获取部门出错!");
				e.printStackTrace();
			}
			depts = new ArrayList<SysDept>();
			depts.add(sysDept);
		}else {
			try {
				 dataSources = dataSourceService.findByProperty("status", "1");
			} catch (ServiceException e) {
				log.error("[ItemAction onBeforeAddView]查找数据源失败!",e);
			}
			// cniList = cloudNodeInfoService.findListNodeByDeptId(null);
		}
		cycle = entityobj.getChangeItemCycle();
    	 try {
	    	 dataSources = dataSourceService.findByProperty("status", "1");
	    	 itemApp = changeItemAppMsgService.findListByItemd(id);
	         //appMsgs = appMsgService.findAll();
	         cycle = entityobj.getChangeItemCycle();
    	 } catch (ServiceException e) {
 			log.error("[ItemAction onBeforeAddView]查找数据源失败!",e);
 		}*/
		if(!StringUtils.isBlank(getDeptid())){
    		dataSources = dataSourceService.findDataSourcesByDept("1", new Long(getDeptid()));
    	}
		cycle = entityobj.getChangeItemCycle();
    }
    @Override
    protected void onBeforeView() {
        appMsgs = appMsgService.findAll();
        itemApp = changeItemAppMsgService.findListByItemd(id);
    }
    /**
     * 添加操作之前
     */
    public void setRule() {
        if (!StringUtils.isBlank(cycle.getExchangeCycleValue())) {
            StringBuffer exchangeDateRule = new StringBuffer();
            if (cycle.getExchangeCycleValue().equals("0")) {// 周交换
                String w_sel = getStringParameter("w_sel");
                String w_day = getStringParameter("w_day");
                exchangeDateRule.append(w_sel);
                exchangeDateRule.append(",");
                exchangeDateRule.append(w_day);
            } else if (cycle.getExchangeCycleValue().equals("1")) {// 月交换
                String m_sel = getStringParameter("m_sel");
                String m_day = getStringParameter("m_day");
                exchangeDateRule.append(m_sel);
                exchangeDateRule.append(",");
                exchangeDateRule.append(m_day);
            } else if (cycle.getExchangeCycleValue().equals("2")) {// 季交换
                String j_sel = getStringParameter("j_sel");
                String j_month = getStringParameter("j_month");
                String j_day = getStringParameter("j_day");
                exchangeDateRule.append(j_sel);
                exchangeDateRule.append(",");
                exchangeDateRule.append(j_month);
                exchangeDateRule.append(",");
                exchangeDateRule.append(j_day);
            } else if (cycle.getExchangeCycleValue().equals("3")) {// 交换
                String y_sel = getStringParameter("y_sel");
                String y_month = getStringParameter("y_month");
                String y_day = getStringParameter("y_day");
                exchangeDateRule.append(y_sel);
                exchangeDateRule.append(",");
                exchangeDateRule.append(y_month);
                exchangeDateRule.append(",");
                exchangeDateRule.append(y_day);
            }
            cycle.setExchangeDateRule(exchangeDateRule.toString());
        }
        if (!StringUtils.isBlank(cycle.getUseDefaultRule()) && cycle.getUseDefaultRule().equals("0")) {// 不采用系统默认规则
            String greenNotify = getStringParameter("greenNotify");
            String greenNotifyDay = getStringParameter("greenNotifyDay");
            String yellowNotify = getStringParameter("yellowNotify");
            String yellowNotifyDay = getStringParameter("yellowNotifyDay");
            String redNotify = getStringParameter("redNotify");
            String redNotifyDay = getStringParameter("redNotifyDay");
            cycle.setRuleGreenNotify(greenNotify + "," + greenNotifyDay);
            cycle.setRuleYellowNotify(yellowNotify + "," + yellowNotifyDay);
            cycle.setRuleRedNotify(redNotify + "," + redNotifyDay);
        }
    }

    @Override
    protected void onBeforeAdd() {
        // 判断部门对象
        if (entityobj.getSysDept() != null) {
            if (entityobj.getSysDept().getId() == null) {
                entityobj.setSysDept(null);
            }
        }
        // 判断数据源对象
        if (entityobj.getDatSource() != null) {
            if (entityobj.getDatSource().getId() == null) {
                entityobj.setDatSource(null);
            }
        }
        setRule();
    }
    // 列表之前操作 手动设置查询参数
    @Override
    protected void onBeforeList() {
    	if (StringUtils.isBlank(deptName)) {
			deptName="";
		}
    	// 获得当前登陆用户
		SysUser user = (SysUser) getSession().get(Costant.SESSION_USER);
		// 通过当前用户ID查询用户所属部门
		// 获得当前用户角色
		SysUserRole role = sysUserRoleService.findByUserId(user.getId());
		
		// 如果roleId大于2者为其它部门角色否则为信息中心人员
		if (role.getRoleId() > 2) {
			SysUserDept sysUserDept = sysUserDeptService.findByUserId(user
					.getId());
			deptName = "NO";
			QueryPara queryPara = new QueryPara();
			queryPara.setName("sysDept.id");
			queryPara.setType("Long");
			queryPara.setValue(sysUserDept.getDeptId().toString());
			queryPara.setOp("=");
			queryParas.add(queryPara);
		}else{
			if(!StringUtils.isBlank(getDeptid())){
				 QueryPara queryPara = new QueryPara();
	            queryPara.setName("e.sysDept.id");
	            queryPara.setOp(Constants.OP_EQ_VALUE);
	            queryPara.setType(Constants.TYPE_LONG);
	            queryPara.setValue(getStringParameter("deptid"));
	            queryParas.add(queryPara);
			}
		}
		/*}else if (!StringUtils.isBlank(deptName)) {
            QueryPara queryPara = new QueryPara();
            queryPara.setName("e.sysDept.deptName");
            queryPara.setOp(Constants.OP_LIKE);
            queryPara.setType(Constants.TYPE_STRING);
            queryPara.setValue(deptName);
            queryParas.add(queryPara);
        }*/
    }

    /**
     * 删除指标
     */
    @Override
    @SuppressWarnings("static-access")
    public String delete() {
        try {
            this.onBeforeDelete();
            this.getEntityService().deleteAllByIds(ids);
            this.onAfterDelete();
            return "listAction";
        } catch (Exception e) {
            this.errorInfo = "删除数据失败，有关联数据正在使用!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }

    /**
     * 保存具体的实体
     * @date 2010-12-8 下午01:25:54
     * @return
     */
    @Override
    @SuppressWarnings("static-access")
    public String add() {
        try {
            if (validData(entityobj)) {// 验证业务逻辑数据
                this.onBeforeAdd();
                String[] appIds = this.getParameter("appMgName");
                changeItemService.save(entityobj, appIds, cycle);
                this.onAfterAdd();
            }
            return RELOAD;
        } catch (Exception e) {
            this.errorInfo = "添加数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    /**
     * 修改保存具体的实体
     * @date 2010-12-8 下午01:25:54
     * @return
     */
    @SuppressWarnings("static-access")
    public String update() {
        try {
            if (validData(entityobj)) {
                this.onBeforeUpdate();
                setRule();
                String[] appIds = this.getParameter("appMgName");
                changeItemService.update(entityobj, appIds, cycle);
                this.onAfterUpdate();
            }
            return RELOAD;
        } catch (Exception e) {
            this.errorInfo = "修改数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
    }
    /**
     * 检查指标编码唯一性
     * @return
     * @author zhangx
     */
    public String checkTemplateCode() {
    	String id = getHttpServletRequest().getParameter("id");
        String itemCode = getHttpServletRequest().getParameter("itemCode").trim();
        String result = "";
        try {
            if (!StringUtils.isBlank(itemCode)) {
                entityobj = changeItemService.findUniqueByProperty("itemCode",
                                java.net.URLDecoder.decode(itemCode, "UTF-8"));
            }
            if (entityobj == null) {
                result = "succ";
            } else {
                if (!StringUtils.isBlank(id)) {
                    if (entityobj.getId().toString().equals(id)) {
                        result = "succ";
                    }
                }
            }
            Struts2Utils.renderText(result, "encoding:GBK");
        } catch (Exception e) {
            log.error("userService invoke exception ", e);
        }
        return null;
    }

    /**
     * ajax请求获得某个部门所有指标项
     * @return
     */
    public String obtainitem() {
        String deptId = getHttpServletRequest().getParameter("deptId");
        itemlist = changeItemService.findListByDeptId(Long.valueOf(deptId));
        dataXml = buildJson(itemlist);
        try {
            getHttpServletResponse().setContentType("text/html");
            getHttpServletResponse().setCharacterEncoding("GB2312");
            getHttpServletResponse().getWriter().println(dataXml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }
    public String buildJson(List<ChangeItem> list) {
        StringBuffer sb = new StringBuffer("<option value='0'>所有指标项</option>");
        for (ChangeItem item : list) {
            sb.append("<option value=\"" + item.getId() + "\">" + item.getItemName() + "</option>");
        }
        return sb.toString();
    }

    /**
     * 选择要推送的目录
     * @return
     * @author lifh
     */
    @SuppressWarnings("static-access")
    public String selectCatalog() {
    	try {
    		 catalogs = findAllCatalogJson();
    	} catch (Exception e) {
    		this.errorInfo="推荐目录失败,请联系管理员!";
    		log.error(this.errorInfo, e);
    		return this.ERROR;
		}
       
        return "selectCatalog";
    }

    public String getCatalogTreePage() throws Exception {
        return "catalogTreePage";
    }

    public String catalogTree() {
        return "catalogTree";
    }

    /**
     * 得到上级目录系统的目录树
     * @return
     * @throws Exception
     * @author lifh
     */
    public String getSuperSysTree() throws Exception {
        Struts2Utils.renderJson(findSuperCatalogTree(catalogId), "encoding:GBK");
        return null;
    }

    /**
     * 推送
     * @return
     * @throws Exception
     * @author lifh
     */
    public String pushToCatalog() throws Exception {
        changeItemService.pushToCatalog(itemIds, catalogId, categoryIds);
        return "pushToCatalogOk";
    }

    private List<CatalogJson> findAllCatalogJson() {
        String json = changeItemService.getService().findCatalogs();
        List list = (List) getJSONList(json, CatalogJson.class);
        return list;
    }

    /**
     * Json转换为对象
     * @param json
     * @param clazz
     * @return
     * @author lifh
     */
    static List getJSONList(String jsonString, Class clazz) {
        JSONArray array = JSONArray.fromObject(jsonString);
        List list = new ArrayList();
        for (Iterator iter = array.iterator(); iter.hasNext();) {
            JSONObject jsonObject = (JSONObject) iter.next();
            list.add(JSONObject.toBean(jsonObject, clazz));
        }
        return list;
    }
    
    public void getItemtree(){
    	JSONArray itemTree = null;
    	itemTree = changeItemService.getItemTree(null);
    	Struts2Utils.renderJson(itemTree, "encoding:GBK");
    	
    }
    
    public void getItemtree4Ajax(){
    	JSONArray tree = changeItemService.getItemTree(getId(), null);
		Struts2Utils.renderJson(tree, "encoding:GBK");
    }
    
    private String findSuperCatalogTree(Long catalogId) {
        CatalogWebService service = changeItemService.getService();
        return service.findCatalogTreeById(catalogId);
    }
    
    public void getAppItemTreeByDeptId4Node(){
		JSONArray appItemTree = changeItemService.getAppItemTreeByDeptId4Node(new Long(deptid));
		Struts2Utils.renderJson(ServletUtils.TEXT_TYPE, appItemTree, "encoding:GBK");
		
	}

	public void getAppItemTreeByDeptId4Center(){
		JSONArray appItemTree = changeItemService.getAppItemTreeByDeptId4Center();
		Struts2Utils.renderJson(appItemTree, "encoding:GBK");
	}
	
	public void getChangeItem4Ajax() throws ServiceException{
		ChangeItem changeItem = changeItemService.findById(new Long(itemIds));
		JSONObject json = new JSONObject();
		if(changeItem.getItemType().equals(1)||changeItem.getItemType().equals(3)){
			json.put("sendDeptName", changeItem.getSysDept().getDeptName());
			List<AppItemExchangeConfDetails> list = changeItem.getAppItemExchangeConf().getAppItemExchangeConfDetails();
			String receiveDeptNames = "";
			if(list!=null&&list.size()>0){
				for(AppItemExchangeConfDetails appItemExchangeConfDetails : list){
					receiveDeptNames += appItemExchangeConfDetails.getReceiveDept().getDeptName()+",";
				}
			}
			json.put("receiveDeptNames", receiveDeptNames);
		}
		Struts2Utils.renderJson(json, "encoding:GBK");
	}
	
	//根据交换配置项的主键查询该配置项的数据提供方的交换指标
	public void getSendChangeItemByChangeConfId4Ajax() throws ServiceException{
		ChangeItem changeItem = changeItemService.findSendChangeItemByChangeConfId(1, 1, new Long(itemIds));
		JSONObject json = new JSONObject();
		if(changeItem!=null){
			json.put("sendChangeItemId", changeItem.getId());
		}
		Struts2Utils.renderJson(json, "encoding:GBK");
	}
	
    @Override
    protected IBaseServices<ChangeItem> getEntityService() {
        return changeItemService;
    }

    // serivce start
    @Autowired
    public void setChangeItemService(IChangeItemService changeItemService) {
        this.changeItemService = changeItemService;
    }
    @Autowired
    public void setDataSourceService(IDataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }
    @Autowired
    public void setChangeItemAppMsgService(IChangeItemAppMsgService changeItemAppMsgService) {
        this.changeItemAppMsgService = changeItemAppMsgService;
    }
    @Autowired
    public void setAppMsgService(AppMsgService appMsgService) {
        this.appMsgService = appMsgService;
    }

    @Autowired
    public void setChangeItemCycleService(IChangeItemCycleService changeItemCycleService) {
        this.changeItemCycleService = changeItemCycleService;
    }
    // serivce end
    // start property

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public List<AppMsg> getAppMsgs() {
        return appMsgs;
    }

    public void setAppMsgs(List<AppMsg> appMsgs) {
        this.appMsgs = appMsgs;
    }

    public List<ChangeItemAppMsg> getItemApp() {
        return itemApp;
    }

    public void setItemApp(List<ChangeItemAppMsg> itemApp) {
        this.itemApp = itemApp;
    }
    public ChangeItemCycle getCycle() {
        return cycle;
    }

    public void setCycle(ChangeItemCycle cycle) {
        this.cycle = cycle;
    }
    // end property

    public List<CatalogJson> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<CatalogJson> catalogs) {
        this.catalogs = catalogs;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    public String getDataXml() {
        return dataXml;
    }
    public void setDataXml(String dataXml) {
        this.dataXml = dataXml;
    }
    public List<ChangeItem> getItemlist() {
        return itemlist;
    }
    public void setItemlist(List<ChangeItem> itemlist) {
        this.itemlist = itemlist;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }
	public List<SysDept> getDepts() {
		return depts;
	}
	public void setDepts(List<SysDept> depts) {
		this.depts = depts;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

}
