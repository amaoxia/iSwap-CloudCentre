package com.ligitalsoft.iswapmq.dao;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.serverinput.JmsServerInfo;

@Repository("jmsServiceDao")
public class JmsServiceDao extends EntityHibernateDao<JmsServerInfo>
{

}
