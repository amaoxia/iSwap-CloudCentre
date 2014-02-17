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
 * @name com.ligitalsoft.model.ftp.FfpGeneralSet.java
 * @version 1.0
 */
@Entity
@Table(name = "FTP_GENERAl_SET")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FtpGeneralSet extends LongIdObject {

    private static final long serialVersionUID = -8221704646957154237L;
    /**
     * IP
     */
    private String ip;
    /**
     * FTP端口号
     */
    private String ftpport;
    /**
     * 最大用户数
     */
    private int maxusernum;
    /**
     * 缓冲活动时间
     */
    private int buffertime;
    /**
     * 允许被动模式传输
     */
    private boolean passivemode;
    /**
     * 启用动态DNS
     */
    private boolean dns;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getFtpport() {
		return ftpport;
	}
	public void setFtpport(String ftpport) {
		this.ftpport = ftpport;
	}
	public int getMaxusernum() {
		return maxusernum;
	}
	public void setMaxusernum(int maxusernum) {
		this.maxusernum = maxusernum;
	}
	public int getBuffertime() {
		return buffertime;
	}
	public void setBuffertime(int buffertime) {
		this.buffertime = buffertime;
	}
	public boolean isPassivemode() {
		return passivemode;
	}
	public void setPassivemode(boolean passivemode) {
		this.passivemode = passivemode;
	}
	public boolean isDns() {
		return dns;
	}
	public void setDns(boolean dns) {
		this.dns = dns;
	}
  
}
