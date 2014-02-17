package com.ligitalsoft.iswapmq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.jmstool.JMSAttr;
import com.common.jmstool.JMSTool;
import com.ligitalsoft.iswapmq.dao.JmsServiceDao;
import com.ligitalsoft.iswapmq.service.IMQService;
import com.ligitalsoft.model.serverinput.JmsServerInfo;

@Transactional
@Service(value = "mqService")
public class MQServiceImpl  extends BaseSericesImpl<JmsServerInfo> implements IMQService
{
    @Autowired
    private JmsServiceDao jmsServiceDao;
    
    @Override
    public EntityHibernateDao<JmsServerInfo> getEntityDao()
    {
        return this.jmsServiceDao;
    }
    /**
	 * ftp测试连接
	 * @author fangbin
	 * @param jmsserver
	 * @return
	 */
	@Override
	public boolean testJMS(JmsServerInfo jmsserver) {
		boolean flag=false;
		JMSAttr attr=new JMSAttr();
		String conntFactory=jmsserver.getConntFactory();
		String jsmFactory= jmsserver.getJmsFactory();
		String quequeName=jmsserver.getQueueName();
		String url=jmsserver.getUrl();
		attr.setInitFactory(jsmFactory);
		attr.setUrl(url);
		attr.setQueFactory(conntFactory);
		attr.setRqueName(quequeName);
		JMSTool tool = new JMSTool();
		boolean jmsconnect=tool.isJMSConnect(attr);
		if(jmsconnect){
			flag=true;
		}
		return flag;
	}
}
