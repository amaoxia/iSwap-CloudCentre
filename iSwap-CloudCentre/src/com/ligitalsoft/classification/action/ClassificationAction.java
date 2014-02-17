package com.ligitalsoft.classification.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.classification.service.IClassificationService;
import com.ligitalsoft.model.classification.Classification;

@Namespace("/rule/classification")
@Action("classification")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "classification!list.action", type = "redirectAction"),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker") })
public class ClassificationAction extends FreemarkerBaseAction<Classification> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IClassificationService classificationService;
	
	private String status;

	@Override
	protected IBaseServices<Classification> getEntityService() {
		return classificationService;
	}

	@Override
	public void onBeforeAdd() {
		Date date = new Date();
		Date nowdate = DateUtil.strToDate(DateUtils.format(date, "yyyy-MM-dd"));
		entityobj.setCreateDate(nowdate);
		entityobj.setStatus("0");
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */

	public String delMany() {
		try {
			for (int i = 0; i < ids.length; i++) {
				Classification classification = classificationService
						.findById(ids[i]);
				if (!classification.getStatus().equals("1")) {
					classificationService.delete(classification);
				}
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
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
					Classification classification = classificationService
							.findById(ids[i]);
					classification.setStatus(status);
					classificationService.update(classification);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
