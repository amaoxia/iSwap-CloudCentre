package com.ligitalsoft.datasharexchange.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.datasharexchange.service.ISendItemDeptService;
import com.ligitalsoft.model.changemanage.SendItemDept;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

@Scope("prototype")
@Namespace("/exchange/sendItemDept")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAction", location = "sendItemDept!list.action", type = "redirectAction") })
@Action("sendItemDept")
public class SendItemDeptAction extends FreemarkerBaseAction<SysDept> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4291635193286682099L;
	private ISysDeptService sysDeptService;
	private ISendItemDeptService sendItemDeptService;
	private String deptNames;
	private Long deptId;

	// private String itemName;

	/**
	 * 列表
	 */
	public String list() {
		// sortParas.addAll(RequestHelper
		// .getOrderParametersWithOrder(getHttpServletRequest()));//
		// 在页面页面上的格式order[age]
		// if (sortParas.size() == 0) {
		// sortParas.add(new SortPara("d.id", Constants.DESC));// 初始以主键ID排序
		// }
		// Map<String, String> values = new HashMap<String, String>();
		// if (!StringUtils.isBlank(deptName)) {
		// values.put("deptName", deptName.trim());
		// }
		// if (!StringUtils.isBlank(itemName)) {
		// values.put("itemName", itemName.trim());
		// }
		// obj = sysDeptService.findDeptItemListByPage(page, values, sortParas);
		return LIST;
	}

	/**
	 * 树形集合
	 * 
	 * @return
	 */
	public String deptItemTree() {
		JSONArray tree = sendItemDeptService.getDetpItemTree(getDeptId(), null);
		if (tree != null) {
			Struts2Utils.renderJson(tree, "encoding:gbk");
		}
		return null;
	}

	@Override
	protected void onBeforeAddView() {
		//SendItemDept sendItemDept = new SendItemDept();
		
		List<SendItemDept> sendItemDeptList = sendItemDeptService.findListDeptId(getDeptId());
		String itemIds = "";
		if(sendItemDeptList!=null&&sendItemDeptList.size()>0){
			//sendItemDept = sendItemDeptList.get(0);
			for(SendItemDept SendItemDept : sendItemDeptList){
				itemIds += SendItemDept.getChangeItem().getId() + ",";
			}
		}
		
		getHttpServletRequest().setAttribute("itemIds", itemIds);
	}

	/**
	 * 添加
	 */
	public String add() {
		/*String deptIds = getStringParameter("deptIds");
		String deptCount = getStringParameter("deptCount");
		String deptNames = getStringParameter("deptNames");
		String itemIds = getStringParameter("itemIds");
		String itemCounts = getStringParameter("itemCounts");
		String lid = getStringParameter("id");
		String udeptName = "";
		try {
			udeptName = new String(deptNames.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (!StringUtils.isBlank(lid) && !StringUtils.isBlank(deptIds)
				&& !StringUtils.isBlank(deptCount)
				&& !StringUtils.isBlank(udeptName)
				&& !StringUtils.isBlank(itemIds)
				&& !StringUtils.isBlank(itemCounts)) {
			id = Long.parseLong(lid);
			sendItemDeptService.saveDeptItem(id, deptIds, udeptName,
					Integer.parseInt(deptCount), itemIds,
					Integer.parseInt(itemCounts));
		}
		return StrutsAction.RELOAD;
		*/
		JSONObject json = new JSONObject();
		json.put("success", "true");
		
		String itemids = getHttpServletRequest().getParameter("itemids");
		try{
			if(getDeptId()!=null&&!StringUtils.isBlank(itemids)) {
				sendItemDeptService.addSendItem(getDeptId(), itemids.split(","));
			}
		}catch(Exception e){
			json.put("success", "false");
		}finally{
			Struts2Utils.renderJson(json, "encoding:gbk");
		}
		return null;
	}

//	/**
//	 * 解除路由配置
//	 */
//	public String delete() {
//		try {
//			if (ids != null) {
//				sendItemDeptService.delete(ids);
//			}
//		} catch (ServiceException e) {
//			log.error("解除异常,请联系管理员!", e);
//			return ERROR;
//		}
//		return "listAction";
//	}

	@Override
	protected IBaseServices<SysDept> getEntityService() {
		return sysDeptService;
	}

	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}

	@Autowired
	public void setSendItemDeptService(ISendItemDeptService sendItemDeptService) {
		this.sendItemDeptService = sendItemDeptService;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(String deptNames) {
		this.deptNames = deptNames;
	}
}
