package com.ligitalsoft.iswapmq.dao;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.serverinput.FtpServerInfo;

@Repository("ftpServiceDao")
public class FtpServiceDao extends EntityHibernateDao<FtpServerInfo> {

}
