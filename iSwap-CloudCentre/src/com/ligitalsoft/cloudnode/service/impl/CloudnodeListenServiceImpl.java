package com.ligitalsoft.cloudnode.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.cloudnode.dao.CloudnodeListenDao;
import com.ligitalsoft.cloudnode.service.ICloudnodeListenService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudnode.CloudnodeListen;
import com.ligitalsoft.webservices.ISwapNodeWS;

@Service("cloudnodeListenService")
public class CloudnodeListenServiceImpl extends
		BaseSericesImpl<CloudnodeListen> implements ICloudnodeListenService {
	
	private CloudnodeListenDao cloudnodeListenDao;
	
	@Override
	public EntityHibernateDao<CloudnodeListen> getEntityDao() {
		return cloudnodeListenDao;
	}

	/**
	 *将启动的任务添加到缓存中
	 */
	public void updateStatus(CloudnodeListen entity) {
		String code = entity.getWorkFlow().getCloudNodeInfo().getCode();
		String ip = entity.getWorkFlow().getCloudNodeInfo().getAddress();
		String port = entity.getWorkFlow().getCloudNodeInfo().getPort();
		String keyName = entity.getListenName()+"#"+entity.getWorkFlow().getWorkFlowCode()+"#"+code;;
		String url = "http://" + ip + ":" + port+ "/iswapnode/webservice/iSwapNodeWS";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ISwapNodeWS.class);
		factory.setAddress(url);
		System.setProperty("org.apache.cxf.bus.factory","org.apache.cxf.bus.CXFBusFactory");
		ISwapNodeWS iswapNodeWs = (ISwapNodeWS) factory.create();
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
    	if("0".equals(entity.getStatus())){
    		iswapNodeWs.stopCloudNodeListenWfTask(keyName);
    		Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.MONGODBLISTEN, map);
    		
    	}else{
    		Map<String,Object> map = new HashMap<String,Object>();
    		iswapNodeWs.runCloudNodeListenWfTask(keyName, entity.getCron());
    		map.put("key", keyName);
    		tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.MONGODBLISTEN, map);
    		map.put("cron", entity.getCron());
    		map.put("message",entity.getMessage());
    		tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.MONGODBLISTEN, map);
    		
    	}
    	tool.closeFileDB();
	}
	
	@Autowired
	public void setCloudnodeListenDao(CloudnodeListenDao cloudnodeListenDao) {
		this.cloudnodeListenDao = cloudnodeListenDao;
	}

	@Override
	public List<CloudnodeListen> findMongoDataSourcesByDept(String status,
			Long deptId) {
		return cloudnodeListenDao.findMongoDataSourcesByDept(status, deptId);
	}
}
