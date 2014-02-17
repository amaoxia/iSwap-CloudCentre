package com.ligitalsoft.iswapmq.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.framework.dao.Constants;
import com.common.framework.dao.QueryPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.tree.ztree.Node;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.iswapmq.service.IWebService;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.cloudnode.MessageListen;
import com.ligitalsoft.model.serverinput.WebServerInfo;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

@Namespace("/iswapmq/external/webservice")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "webInfoAction!list.action", type = "redirect", params = {"deptId","${deptId}"}),
		@Result(name = "saveMQResult", location = "../../../common/succ.ftl", type = "freemarker") })
@Action("webInfoAction")
public class WebServiceAction extends FreemarkerBaseAction<WebServerInfo> {

	private static final long serialVersionUID = -5515456558168969977L;
	private Long deptId;
	private String status;
	private SysDept sysDept;
	private List<SysDept> listDepts = new ArrayList<SysDept>();
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private IWebService webService;

	@SuppressWarnings("static-access")
	@Override
	public String add() {
		try {
			if (validData(entityobj)) {// 验证业务逻辑数据
				entityobj.setStatus("0");// 未启用
				this.onBeforeAdd();
				getEntityService().insert(entityobj);
				this.onAfterAdd();
			}
			return "saveMQResult";
		} catch (Exception e) {
			this.errorInfo = "添加数据失败，请稍候再试!";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	@Override
	protected void onBeforeList() {
		if (null != deptId) {
			QueryPara para = new QueryPara();
			para.setName("sysDept.id");
			para.setOp(Constants.OP_EQ_VALUE);
			para.setType(Constants.TYPE_LONG);
			para.setValue(deptId + "");
			queryParas.add(para);
		}
	}

	/**
	 * 用户登录 检查登录名称是否存在
	 * 
	 * @return
	 * @author
	 * @2010-12-30 下午08:16:30
	 * @throws Exception
	 */
	public String checkName() throws Exception {
		String wsName = getHttpServletRequest().getParameter("wsName").trim();
		String id = getHttpServletRequest().getParameter("id");
		String result = "";
		try {
			WebServerInfo ftpInfo = webService.findUniqueByProperty("wsName",
					new String(wsName.getBytes("ISO-8859-1"), "UTF-8"));
			if (ftpInfo == null) {
				result = "succ";
			} else {
				if (!StringUtils.isBlank(id)) {
					if (ftpInfo.getId().toString().equals(id)) {
						result = "succ";
					}
				}
			}
			Struts2Utils.renderText(result, "encoding:GBK");
		} catch (ServiceException e) {
			log.error("ftpService invoke exception ", e);
		}
		return null;
	}

	/**
	 * 修改保存具体的实体
	 * 
	 * @author huwanshan
	 * @date 2010-12-8 下午01:25:54
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String update() {
		try {
			if (validData(entityobj)) {
				this.onBeforeUpdate();
				this.getEntityService().update(entityobj);
				this.onAfterUpdate();
			}
			return "saveMQResult";
		} catch (Exception e) {
			this.errorInfo = "修改数据失败，请稍候再试!";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/**
	 * 删除
	 */
	@Override
	@SuppressWarnings("static-access")
	public String delete() {
		try {
			this.onBeforeDelete();
			this.getEntityService().deleteAllByIds(ids);
			this.onAfterDelete();
			return StrutsAction.RELOAD;
		} catch (Exception e) {
			this.errorInfo = "删除数据失败!";
			log.error(errorInfo, e);
			return this.ERROR;
		}
	}

	/*
	 * 查出所有部门
	 * 
	 * @see com.common.framework.view.FreemarkerBaseAction#addView()
	 */
	@Override
	public String addView() {
		getDeptInfo();
		return StrutsAction.ADDVIEW;

	}

	/**
	 * 得到实体数据修改页面
	 * 
	 * @date 2010-12-9 下午02:58:56
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Override
	public String updateView() {
		getDeptInfo();
		this.onBeforeUpdateView();
		return this.UPDATEVIEW;
	}

	@Override
	protected void onBeforeView() {
		super.onBeforeView();
	}

	private void getDeptInfo() {
		try {
			sysDept = sysDeptService.findById(deptId);
		} catch (ServiceException e) {
			log.error("查找部门失败,请联系管理员!", e);
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
					WebServerInfo webServerInfo = webService
							.findById(ids[i]);
					webServerInfo.setStatus(status);
					webService.update(webServerInfo);
				}
			}
//			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return this.RELOAD;
	}
	
	public String getWSDataSourceJsonStr() {
		List<WebServerInfo> dataSourceList = webService.findWSDataSourcesByDept("1", deptId);
		if(dataSourceList==null||dataSourceList.size()<=0)return null;
		List<Node> nodes = new ArrayList<Node>();
		for(WebServerInfo dataSource : dataSourceList){
			Node node = new Node();
			node.setId(dataSource.getId()+"");
			node.setName(dataSource.getWsName());
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
	
	public List<SysDept> getListDepts() {
		return listDepts;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public void setListDepts(List<SysDept> listDepts) {
		this.listDepts = listDepts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected IBaseServices<WebServerInfo> getEntityService() {
		return this.webService;
	}
}
