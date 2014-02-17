package com.ligitalsoft.cloudnode.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.cloudnode.MessageListen;

public interface IMessageListenService extends IBaseServices<MessageListen> {
	/**
	 *  更改状态时修改缓存中的部署的消息监听信息
	 * @author fangbin
	 * @param entity
	 */
	public  void updateStatus(MessageListen entity);
	
	public List<MessageListen> findMQDataSourcesByDept(String status,Long deptId);
}
