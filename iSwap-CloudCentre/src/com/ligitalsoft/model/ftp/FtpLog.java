/*
 * @(#)FtpUser.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.ftp;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * FTR目录表
 * @author daic
 * @mail daicheng0518@163.com
 * @since May 23, 2011 10:38:42 AM
 * @name com.ligitalsoft.model.ftp.FtpLog.java
 * @version 1.0
 */
@Entity
@Table(name = "FTP_LOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FtpLog extends LongIdObject {

    private static final long serialVersionUID = -8221704646957154237L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户IP
     */
    private String ip;
    /**
     * 活动
     */
    private String action;
    /**
     * 文件/目录
     */
    private String homedirectory;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getHomedirectory() {
		return homedirectory;
	}
	public void setHomedirectory(String homedirectory) {
		this.homedirectory = homedirectory;
	}
   

  
}
