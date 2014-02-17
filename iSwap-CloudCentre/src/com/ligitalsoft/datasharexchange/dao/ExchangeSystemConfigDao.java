/*
 * @(#)ExchangeSystemConfigDao.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.changemanage.ExchangeSystemConfig;
import com.ligitalsoft.util.SendMail;

/**
 * 交换系统配置dao层
 * @author daic
 * @since 2011-08-16 16:30:38
 * @name com.ligitalsoft.cloudstorage.dao.ExchangeSystemConfigDao.java
 * @version 1.0
 */
@Repository("exchangeSystemConfigDao")
public class ExchangeSystemConfigDao extends EntityHibernateDao<ExchangeSystemConfig> {
	@SuppressWarnings("unchecked")
	public ExchangeSystemConfig getSystemConfigInfo() {
		String hql = "from ExchangeSystemConfig";
		ExchangeSystemConfig sc = null;
		List<ExchangeSystemConfig> list = findListByHql(hql, null);
		if (list.size() > 0) {
			sc = list.get(0);
		}
		return sc;
	}
	public boolean sendEmail(String target, String title, String content) {
		ExchangeSystemConfig sc = this.getSystemConfigInfo();
		SendMail sendEmail = new SendMail();
		sendEmail.setTo(target);
		sendEmail.setSubject(title);
		sendEmail.setContent(content);
		sendEmail.setFrom(sc.getMailAddress());
		sendEmail.setHost(sc.getMailSmtp());
		sendEmail.setPassWord(sc.getMailPwd());
		sendEmail.setUserName(sc.getMailAccount());
		return sendEmail.sendMail();
	}
}
