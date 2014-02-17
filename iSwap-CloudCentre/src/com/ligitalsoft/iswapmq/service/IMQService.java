package com.ligitalsoft.iswapmq.service;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.serverinput.JmsServerInfo;

public interface IMQService extends IBaseServices<JmsServerInfo>
{
	/**
	 * jms测试连接
	 * @author fangbin
	 * @param jmsserver
	 * @return
	 */
	public boolean testJMS(JmsServerInfo jmsserver);
}
