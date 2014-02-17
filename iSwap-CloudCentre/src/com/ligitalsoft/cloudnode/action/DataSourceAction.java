package com.ligitalsoft.cloudnode.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.common.utils.tree.jstree.JsTreeFactory;
import com.common.utils.tree.ztree.Node;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudcenter.service.CloudNodeInfoService;
import com.ligitalsoft.cloudcenter.service.ICouldNodeDeptService;
import com.ligitalsoft.cloudnode.service.IDataSourceService;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserDeptService;
import com.ligitalsoft.sysmanager.service.ISysUserRoleService;

/**
 * 数据源管理
 * 
 * @Company 北京光码软件有限公司
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2011-06-13
 * @Team 研发中心
 */
@Namespace("/cloudnode/datasource")
@Action("datasource")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "datasource!list.action", type = "redirectAction", params = {
				"page.index", "${page.index}", "sourceName", "${sourceName}",
				"type", "${type}","deptId","${deptId}" }),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class DataSourceAction extends FreemarkerBaseAction<DataSource> {

	/**
     * 
     */
	private static final long serialVersionUID = 8185700775751035939L;
	@Autowired
	private IDataSourceService datasourceService;
	@Autowired
	private CloudNodeInfoService cloudNodeInfoService;
	@Autowired
	private ICouldNodeDeptService couldNodeDeptService;
	@Autowired
	private ISysUserDeptService sysUserDeptService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private ISysDeptService sysDeptService;
	/* 部门ID */
	private Long deptId;
	private CouldNodeDept couldNodeDept;

	public List<CloudNodeInfo> cniList = new ArrayList<CloudNodeInfo>();
	private List<SysDept> depts = new ArrayList<SysDept>();// 部门集合对象
	private String status;
	private String sourceName;
	private String type;

	@Override
	protected IBaseServices<DataSource> getEntityService() {
		return datasourceService;
	}

	/**
	 * 通过当前用户添加检索条件
	 * 
	 * @author fangbin
	 */
	public void onBeforeList() {
		if (null != deptId) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("sysDept.id");
			queryPara.setType("Long");
			queryPara.setValue(deptId + "");
			queryPara.setOp("=");
			queryParas.add(queryPara);
		}
		if (sourceName != null && !StringUtils.isBlank(sourceName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("sourceName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(sourceName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}
		if (type != null && !StringUtils.isBlank(type)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("type");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(type);
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}

	}

	/**
	 * 进行入添加页面前的操作
	 * 
	 * @author fangbin
	 */
	@Override
	public void onBeforeAddView() {
		if (null != deptId) {
			List<CouldNodeDept> list = couldNodeDeptService
					.findDeptByDeptId(deptId);
			if (null != list && list.size() > 0) {
				couldNodeDept = list.get(0);
			}
		}
	}

	/**
	 * 执行数据源添加之前，创建时间加入对象
	 * 
	 * @author fangbin
	 */
	@Override
	protected void onBeforeAdd() {
		Date date = new Date();
		Date nowdate = DateUtil.strToDate(DateUtils.format(date, "yyyy-MM-dd"));
		entityobj.setCreateDate(nowdate);
		entityobj.setStatus("0");
	}


	/**
	 * 检查数据源是否存在
	 * 
	 * @return
	 * @author zhangx
	 * @2010-12-30 下午08:10:24
	 */
	public String checkDataSource() {
		String result = "";
		String id = getStringParameter("id");
		String ip = getStringParameter("ip").trim();
		String port = getStringParameter("port").trim();
		String dbName = getStringParameter("dbName").trim();
		getHttpServletResponse().setCharacterEncoding("GBK");
		DataSource dataSource = datasourceService.findDatasourceIsExit(ip,
				port, dbName);
		if (dataSource == null) {
			result = "succ";
		} else {
			if (!StringUtils.isBlank(id)) {
				if (dataSource.getId().toString().equals(id)) {
					result = "succ";
				}
			}
		}
		Struts2Utils.renderText(result, "encoding:GBK");
		return null;
	}

	/**
	 * 生成数据源树
	 * 
	 * @return
	 * @author zhangx
	 */
	public String dataSourceTree() {
		String tree = datasourceService.dataSourceTree();
		Struts2Utils.renderText(tree, "encoding:GBK");
		return null;
	}

	/**
	 * 删除
	 * 
	 * @author fangbin
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
	 * 更改状态
	 * 
	 * @return
	 */
	public String updateStatus() {
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					DataSource dataSource = datasourceService.findById(ids[i]);
					dataSource.setStatus(status);
					datasourceService.updateStatus(dataSource);
					datasourceService.update(dataSource);
				}
			}
			if (id != null) {
				DataSource dataSource = datasourceService.findById(id);
				dataSource.setStatus(status);
				datasourceService.updateStatus(dataSource);
				datasourceService.update(dataSource);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	/**
	 * 测试数据库连接是否成功
	 * 
	 * @return
	 */
	public String testConnDataSource() {
		if (id != null && id != 0L) {
			try {
				String result = datasourceService.testDataSource(id);
				Struts2Utils.renderText(result, "encoding:GBK");
				return null;
			} catch (ServiceException e) {
				Struts2Utils.renderText("false", "encoding:GBK");
				return null;
			}
		}
		Struts2Utils.renderText("false", "encoding:GBK");
		return null;
	}
	
	public String getDataSourceJsonStr() {
		List<DataSource> dataSourceList = datasourceService.findDataSourcesByDept("1", deptId);
		if(dataSourceList==null||dataSourceList.size()<=0)return null;
		List<Node> nodes = new ArrayList<Node>();
		for(DataSource dataSource : dataSourceList){
			Node node = new Node();
			node.setId(dataSource.getId()+"");
			node.setName(dataSource.getSourceName());
			nodes.add(node);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {

				if (name.equals("name") || name.equals("id")) {
					return false;
				} else {
					return true;
				}
			}
		});
		String dsJsonStr = JSONArray.fromObject(nodes, jsonConfig).toString();
		Struts2Utils.renderText(dsJsonStr, "encoding:GBK");
		return null;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CloudNodeInfo> getCniList() {
		return cniList;
	}

	public void setCniList(List<CloudNodeInfo> cniList) {
		this.cniList = cniList;
	}

	public List<SysDept> getDepts() {
		return depts;
	}

	public void setDepts(List<SysDept> depts) {
		this.depts = depts;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public CouldNodeDept getCouldNodeDept() {
		return couldNodeDept;
	}

	public void setCouldNodeDept(CouldNodeDept couldNodeDept) {
		this.couldNodeDept = couldNodeDept;
	}
}
