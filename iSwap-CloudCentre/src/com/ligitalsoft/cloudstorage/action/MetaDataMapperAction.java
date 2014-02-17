package com.ligitalsoft.cloudstorage.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
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
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudcenter.service.CloudNodeInfoService;
import com.ligitalsoft.cloudstorage.service.IMetaDataMapperService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudstorage.MetaDataMapper;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

/**
 * Mapper管理
 * 
 * @Company 北京光码软件有限公司
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2011-06-13
 * @Team 研发中心
 */
@Namespace("/cloudstorage/metaDataMapper")
@Action("metaDataMapper")
@Scope("prototype")
@Results({
		@Result(name = "listAction", location = "metaDataMapper!list.action", type = "redirectAction",params = {
				"page.index", "${page.index}", "mapName",
				"${mapName}", "deptId","${deptId}", "metaDataId","${metaDataId}" }),
		@Result(name = StrutsAction.RELOAD, location = "../../common/succ.ftl", type = "freemarker"),
		@Result(name = "uploadView", location = "upload.ftl", type = "freemarker"),
		@Result(name = "upload", location = "mapper!list.action", type = "redirectAction") })
public class MetaDataMapperAction extends FreemarkerBaseAction<MetaDataMapper> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IMetaDataMapperService mapperService;
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private AppMsgService appMsgService;
	@Autowired
	private CloudNodeInfoService cloudNodeInfoService;

	private List<SysDept> sysdeptList = new ArrayList<SysDept>();
	private List<AppMsg> appMsgList = new ArrayList<AppMsg>();
	private List<CloudNodeInfo> cloudNodeInfoList = new ArrayList<CloudNodeInfo>();
	private String status;

	private File fileupload;

	// /多文件上传
	private File fileData;
	private String fileDataFileName;
	private String fileDataContentType;
	
	private MetaDataMapper mapper;
	private String mapName;
	private Long deptId;
	private Long metaDataId;

	@Override
	protected IBaseServices<MetaDataMapper> getEntityService() {
		return mapperService;
	}

	public List<SysDept> getSysdeptList() {
		return sysdeptList;
	}

	public void setSysdeptList(List<SysDept> sysdeptList) {
		this.sysdeptList = sysdeptList;
	}

	/**
	 * 
	 * @author fangbin
	 */
	@Override
	public void onBeforeList() {
		if (metaDataId!=null) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("tarMetaData.id");
			queryPara.setType(Constants.TYPE_LONG);
			queryPara.setValue(metaDataId+"");
			queryPara.setOp(Constants.OP_EQ);
			queryParas.add(queryPara);
		}
		if (!StringUtils.isBlank(mapName)) {
			QueryPara queryPara = new QueryPara();
			queryPara.setName("mapName");
			queryPara.setType(Constants.TYPE_STRING);
			queryPara.setValue(mapName);
			queryPara.setOp(Constants.OP_LIKE);
			queryParas.add(queryPara);
		}
		sysdeptList = sysDeptService.findAll();
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
            this.onBeforeAdd();
            mapperService.save(getMapper());
            this.onAfterAdd();
            return RELOAD;
        } catch (Exception e) {
            this.errorInfo = "添加数据失败，请稍候再试!";
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
    public String update() {
        try {
            this.onBeforeUpdate();
            mapperService.save(getMapper());
            this.onAfterUpdate();
            return RELOAD;
        } catch (Exception e) {
            this.errorInfo = "添加数据失败，请稍候再试!";
            log.error(errorInfo, e);
            return this.ERROR;
        }
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

	/**
	 * 批量删除
	 * 
	 * @return
	 */

	public String delMany() {
		try {
			for (int i = 0; i < ids.length; i++) {
				MetaDataMapper mapping = mapperService.findById(ids[i]);
				if (mapping.getStatus().equals("0")) {
					mapperService.delete(mapping);
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
			mapperService.updateStatus(ids, status);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAction";
	}

	/**
	 * Mapper上传页面
	 * 
	 * @author fangbin
	 * @return
	 * @throws ServiceException 
	 */
	public String uploadView() throws ServiceException {
		// 应用 选项
		appMsgList = appMsgService.findAll();
		// 所属云端节点
		cloudNodeInfoList = cloudNodeInfoService.findAll();
		// 部门
		sysdeptList = sysDeptService.findAll();
		return "uploadView";
	}

	/**
	 * 多Mapper上传页面
	 * 
	 * @author fangbin
	 * @return
	 */
	public String multUploadView() {
		// 应用 选项
		appMsgList = appMsgService.findAll();
		// 所属云端节点
		cloudNodeInfoList = cloudNodeInfoService.findAll();
		// 部门
		sysdeptList = sysDeptService.findAll();
		return "multupload";
	}
	
	@Override
	public void onBeforeView() {
		try {
			setMapper(mapperService.findById(getId()));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onBeforeUpdateView() {
		try {
			setMapper(mapperService.findById(getId()));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String multUpload() {
		PrintWriter out=null;
		BufferedReader br = null;
		try {
			// 获得上传mappper文件的内容
			br = new BufferedReader(new FileReader(fileData));
			String line = "";
			String contents = "";
			while ((line = br.readLine()) != null) {
				contents += line;
			}
			/* 注入属性 */
			String deptId = getStringParameter("deptId");
			String appId = getStringParameter("appMsgId");
			String nodeId = getStringParameter("cloudeNodeId");
			String  mappingType=getStringParameter("mappingType");
			AppMsg appMsg = appMsgService.findById(Long.parseLong(appId));
			SysDept dept = sysDeptService.findById(Long.parseLong(deptId));
			CloudNodeInfo cloudeNode = cloudNodeInfoService.findById(Long.parseLong(nodeId));
			if(entityobj==null){
				entityobj=new MetaDataMapper();
			}
			String fileName=fileDataFileName.substring(0, fileDataFileName.indexOf("."));
			entityobj.setMappingType(mappingType);
			entityobj.setContents(contents);
			entityobj.setStatus("0");
			entityobj.setMapName(fileName);
			entityobj.setMapCode(fileName);
			mapperService.saveOrUpdate(entityobj);
			out=getHttpServletResponse().getWriter();
			out.write(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error("关闭流异常!", e);
				}
			}
		}
		return null;
	}

	/**
	 * 上传mapper文件
	 * 
	 * @return
	 */
	public String upload() {
		BufferedReader br = null;
		try {
			// 获得上传mappper文件的内容
			br = new BufferedReader(new FileReader(fileupload));
			String line = "";
			String contents = "";
			while ((line = br.readLine()) != null) {
				contents += line;
			}
			entityobj.setContents(contents);
			entityobj.setStatus("0");
			mapperService.saveOrUpdate(entityobj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error("关闭流异常!", e);
				}
			}
		}
		return RELOAD;
	}

	/**
	 * 定义在Upload()前执行二次绑定.
	 */
	public void prepareUpload() throws Exception {
		prepareModel();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	public List<AppMsg> getAppMsgList() {
		return appMsgList;
	}

	public void setAppMsgList(List<AppMsg> appMsgList) {
		this.appMsgList = appMsgList;
	}

	public List<CloudNodeInfo> getCloudNodeInfoList() {
		return cloudNodeInfoList;
	}

	public void setCloudNodeInfoList(List<CloudNodeInfo> cloudNodeInfoList) {
		this.cloudNodeInfoList = cloudNodeInfoList;
	}

	public File getFileData() {
		return fileData;
	}

	public void setFileData(File fileData) {
		this.fileData = fileData;
	}

	public String getFileDataFileName() {
		return fileDataFileName;
	}

	public void setFileDataFileName(String fileDataFileName) {
		this.fileDataFileName = fileDataFileName;
	}

	public String getFileDataContentType() {
		return fileDataContentType;
	}

	public void setFileDataContentType(String fileDataContentType) {
		this.fileDataContentType = fileDataContentType;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getMetaDataId() {
		return metaDataId;
	}

	public void setMetaDataId(Long metaDataId) {
		this.metaDataId = metaDataId;
	}

	public MetaDataMapper getMapper() {
		return mapper;
	}

	public void setMapper(MetaDataMapper mapper) {
		this.mapper = mapper;
	}
}
