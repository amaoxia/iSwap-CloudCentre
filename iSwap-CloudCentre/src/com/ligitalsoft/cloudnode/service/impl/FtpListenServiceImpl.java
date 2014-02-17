package com.ligitalsoft.cloudnode.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.jobtool.ELTool;
import com.ligitalsoft.cloudnode.dao.FtpListenDao;
import com.ligitalsoft.cloudnode.service.IFtpListenService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.cloudnode.FtpListen;
import com.ligitalsoft.webservices.ISwapNodeWS;

/**
 * 远程目录监听
 * 
 * @Company 北京光码软件有限公司
 *@author fangbin
 *@version iSwap V6.0 数据交换平台
 *@date 2011-06-14
 *@Team 研发中心
 */
@Service("ftpListenService")
public class FtpListenServiceImpl extends BaseSericesImpl<FtpListen> implements
		IFtpListenService {
	@Autowired
	private FtpListenDao ftplistenDao;
	@Autowired
	protected DozerBeanMapper mapperValue;

	@Override
	public EntityHibernateDao<FtpListen> getEntityDao() {
		return ftplistenDao;
	}
	/**
	 * 状态改变时修改缓存中的部署的FTP信息
	 * 
	 * @param entity
	 * @author fangbin
	 * @date 2011-10-29 下午05:32:50
	 */
	public  void updateStatus(FtpListen entity) {
		String ip = entity.getWorkFlow().getCloudNodeInfo().getAddress();
		String code = entity.getWorkFlow().getCloudNodeInfo().getCode();
		String port = entity.getWorkFlow().getCloudNodeInfo().getPort();
		String url = "http://" + ip + ":" + port+ "/iswapnode/webservice/iSwapNodeWS";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ISwapNodeWS.class);
		factory.setAddress(url);
		System.setProperty("org.apache.cxf.bus.factory","org.apache.cxf.bus.CXFBusFactory");
		ISwapNodeWS iswapNodeWs = (ISwapNodeWS) factory.create();
		
		String cron = ELTool.init().cent(entity.getTimes());
		String keyName = entity.getListenName()+"#"+entity.getWorkFlow().getWorkFlowCode()+"#"+ entity.getId()+ "#" + code;
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		if ("0".equals(entity.getStatus())) {
			iswapNodeWs.stopFtpWfTask(keyName);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.ftpInfoDB, map);
		} else {
			iswapNodeWs.runFtpWfTask(keyName, cron);
			String ftp_ip = entity.getFtpServerInfo().getAddress();
			String ftp_port = entity.getFtpServerInfo().getPort();
			String filePath = entity.getFtpServerInfo().getFilePath();
			String userName = entity.getFtpServerInfo().getUserName();
			String password = entity.getFtpServerInfo().getPassword();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.ftpInfoDB, map);
			map.put("ftp_ip", ftp_ip);
			map.put("ftp_port", ftp_port);
			map.put("filePath", filePath);
			map.put("userName", userName);
			map.put("password", password);
			map.put("fileName",entity.getFileName());
			map.put("filePath",entity.getFilePath());
			map.put("time",entity.getTimes());
			tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.ftpInfoDB, map);
		}
		tool.closeFileDB();
	}

	
	/**
	 * 完成对象之间拷贝
	 * @author hudaowan
	 *@date 2010-9-15 下午12:46:16
	 *@param source
	 *@param destination
	 */
	protected void doValueCopy(Object source, Object destination) {// 将源对象中的值copy到目标对象中
		this.mapperValue.map(source, destination);
	}
	@Override
	public List<FtpListen> findFtpDataSourcesByDept(String status, Long deptId) {
		return ftplistenDao.findFtpDataSourcesByDept(status, deptId);
	}

}
