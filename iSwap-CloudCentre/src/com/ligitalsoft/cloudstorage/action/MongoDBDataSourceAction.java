package com.ligitalsoft.cloudstorage.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudstorage.service.IMongoDBDataSourceService;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;
import com.ligitalsoft.model.cloudstorage.MongoDBDataSource;

/**
 * 云存储数据源
 * 
 * @author zhangx
 * 
 */
@Scope("prototype")
@Namespace("/cloudstorage/mongodb")
@Action("mongodb")
@Results({
	    @Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAction", location = "mongodb!list.action", type = "redirect") })
public class MongoDBDataSourceAction extends FreemarkerBaseAction<MongoDBDataSource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965006985179686310L;

	private IMongoDBDataSourceService mongoDBDataSourceService;

	@SuppressWarnings("static-access")
	@Override
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
	 * 测试数据库连接是否成功
	 * 
	 * @return
	 */
	public String testConnDataSource() {
		if (id != null && id != 0L) {
			try {
				String result = mongoDBDataSourceService.testDataSource(id);
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
		String ip =getStringParameter("ip");
		String port=getStringParameter("port");
		String dbName=getStringParameter("dbName");
		getHttpServletResponse().setCharacterEncoding("GBK");
		if(!StringUtils.isBlank(ip)){
			Struts2Utils.renderText("succ", "encoding:GBK");
			return null; 
		}
		if(!StringUtils.isBlank(port)){
			Struts2Utils.renderText("succ", "encoding:GBK");
			return null; 
		}
		if(!StringUtils.isBlank(dbName)){
			Struts2Utils.renderText("succ", "encoding:GBK");
			return null; 
		}
			CouldDataSource  dataSource = mongoDBDataSourceService.findDatasourceIsExit(ip.trim(),port.trim(),dbName.trim());
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
	@Override
	protected IBaseServices<MongoDBDataSource> getEntityService() {
		return mongoDBDataSourceService;
	}

	@Resource(name = "mongoDBDataSourceService")
	public void setMongoDBDataSourceService(
			IMongoDBDataSourceService mongoDBDataSourceService) {
		this.mongoDBDataSourceService = mongoDBDataSourceService;
	}
}
