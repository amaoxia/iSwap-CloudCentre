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
import com.ligitalsoft.cloudnode.dao.MessageListenDao;
import com.ligitalsoft.cloudnode.service.IMessageListenService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudnode.MessageListen;
import com.ligitalsoft.webservices.ISwapNodeWS;
@Service("messagelistenService")
public class MessageListenServiceImpl extends BaseSericesImpl<MessageListen> implements
		IMessageListenService {
	@Autowired
	private MessageListenDao messageListenDao;
	@Autowired
	protected DozerBeanMapper mapperValue;
	@Override
	public EntityHibernateDao<MessageListen> getEntityDao() {
		return messageListenDao;
	}
	/**
	 * 更改状态时修改缓存中的部署的消息监听信息
	 * 
	 * @param entity
	 * @author fangbin
	 * @date 2011-10-29 下午05:32:50
	 */
	public  void updateStatus(MessageListen entity) {
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
		String keyName = entity.getListenName()+"#"+ entity.getId()+ "#" + code;
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		if ("0".equals(entity.getStatus())) {
			iswapNodeWs.stopJmsWfTask(keyName);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.jmsInfoDB, map);
		} else {
			iswapNodeWs.runJmsWfTask(keyName, cron);
			String jmsFactory = entity.getJmsServerInfo().getJmsFactory();
			String conntFactory = entity.getJmsServerInfo().getConntFactory();
			String queueName = entity.getJmsServerInfo().getQueueName();
			String que_url = entity.getJmsServerInfo().getUrl();
			String userName = entity.getJmsServerInfo().getUserName();
			String passWord = entity.getJmsServerInfo().getPassWord();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.jmsInfoDB, map);
			map.put("jmsFactory", jmsFactory);
			map.put("conntFactory", conntFactory);
			map.put("url", que_url);
			map.put("queueName", queueName);
			map.put("userName", userName);
			map.put("passWord", passWord);
			map.put("time", entity.getTimes());
			tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.jmsInfoDB, map);
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
	@Override
	public List<MessageListen> findMQDataSourcesByDept(String status,
			Long deptId) {
		return messageListenDao.findMQDataSourcesByDept(status, deptId);
	}
}
