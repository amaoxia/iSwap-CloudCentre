package com.ligitalsoft.cloudnode.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.jobtool.ELTool;
import com.ligitalsoft.cloudnode.dao.LocalHostListenDao;
import com.ligitalsoft.cloudnode.service.ILocalHostListenService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudnode.LocalHostListen;
import com.ligitalsoft.webservices.ISwapNodeWS;
@Service("localhostlistenService")
public class LocalHostListenServiceImpl extends
		BaseSericesImpl<LocalHostListen> implements ILocalHostListenService {
	@Autowired
	private LocalHostListenDao localhostListenDao;
	@Override
	public EntityHibernateDao<LocalHostListen> getEntityDao() {
		return localhostListenDao;
	}
	@Autowired
	protected DozerBeanMapper mapperValue;
	/**
	 * 更改状态时修改缓存中的部署的本地目录监听信息
	 * 
	 * @param entity
	 * @author fangbin
	 * @date 2011-10-29 下午05:32:50
	 */
	public void updateStatus(LocalHostListen entity) {
		String code = entity.getWorkFlow().getCloudNodeInfo().getCode();
		String ip = entity.getWorkFlow().getCloudNodeInfo().getAddress();
		String port = entity.getWorkFlow().getCloudNodeInfo().getPort();
		String url = "http://" + ip + ":" + port+ "/iswapnode/webservice/iSwapNodeWS";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ISwapNodeWS.class);
		factory.setAddress(url);
		System.setProperty("org.apache.cxf.bus.factory","org.apache.cxf.bus.CXFBusFactory");
		ISwapNodeWS iswapNodeWs = (ISwapNodeWS) factory.create();
		
		String cron = ELTool.init().cent(entity.getTimes());
		String keyName = entity.getFileName()+"#"+entity.getWorkFlow().getWorkFlowCode()+"#"+ entity.getId()+ "#" + code;
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		if ("0".equals(entity.getStatus())) {
			iswapNodeWs.stopLocalhostWfTask(keyName);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.localhostDB, map);
		} else {
			iswapNodeWs.runLocalhostWfTask(keyName, cron);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.localhostDB, map);
			map.put("filePath",entity.getFilePath());
			map.put("fileName",entity.getFileName());
			map.put("time",entity.getTimes());
			tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.localhostDB, map);
		}
		tool.closeFileDB();
	}
	/**
	 * 完成对象之间拷贝
	 * 
	 * @author hudaowan
	 *@date 2010-9-15 下午12:46:16
	 *@param source
	 *@param destination
	 */
	protected void doValueCopy(Object source, Object destination) {// 将源对象中的值copy到目标对象中
		this.mapperValue.map(source, destination);
	}
}
