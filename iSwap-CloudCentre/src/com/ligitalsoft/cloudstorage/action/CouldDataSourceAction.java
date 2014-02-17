package com.ligitalsoft.cloudstorage.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

import com.common.dbtool.FileDBTool;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.view.FreemarkerBaseAction;
import com.common.framework.view.StrutsAction;
import com.common.utils.common.StringUtils;
import com.common.utils.web.struts2.Struts2Utils;
import com.ligitalsoft.cloudstorage.service.ICloudDataSourceService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;

/**
 * 云存储数据源
 * 
 * @author zhangx
 * 
 */
@Scope("prototype")
@Namespace("/cloudstorage/datasource")
@Action("datasource")
@Results({
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "listAction", location = "datasource!list.action", type = "redirect") })
public class CouldDataSourceAction extends
		FreemarkerBaseAction<CouldDataSource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965006985179686310L;

	private ICloudDataSourceService couldDataSourceService;

	/**
	 * 测试数据库连接是否成功
	 * 
	 * @return
	 */
	public String testConnDataSource() {
		if (id != null && id != 0L) {
			try {
				String result = couldDataSourceService.testDataSource(id);
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
	 
	 protected void onBeforeDelete() {// delete后执行的操作
//			CacheTool tool = CacheTool.init();
//			Cache cache = tool.findCache(CacheConstant.dataSourceCache);
			FileDBTool tool = FileDBTool.init();
			try {     
			    tool.getMongoConn();
				for(Long id:ids){
					CouldDataSource  entity = couldDataSourceService.findById(id);
					String keyName = entity.getSourceName() + "#" + entity.getSourceCode()+"_center";
	//				tool.deleteCacheInfo(cache, keyName);
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("key", keyName);
					tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
				}
		        tool.closeFileDB();
			} catch (Exception e) {
				this.errorInfo = "删除数据失败，有关联数据正在使用!";
				log.error(errorInfo, e);
			}
			
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
			CouldDataSource  dataSource = couldDataSourceService.findDatasourceIsExit(ip.trim(),port.trim(),dbName.trim());
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
	protected IBaseServices<CouldDataSource> getEntityService() {
		return couldDataSourceService;
	}

	@Resource(name = "couldDataSourceService")
	public void setCouldDataSourceService(
			ICloudDataSourceService couldDataSourceService) {
		this.couldDataSourceService = couldDataSourceService;
	}
}
