/*
 * @(#)FtpAccessRule.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.ftp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.common.framework.domain.LongIdObject;

/**
 * 客户端访问规则
 * @author lifh
 * @mail wslfh2005@163.com
 * @since May 26, 2011 3:45:15 PM
 * @name com.ligitalsoft.model.ftp.FtpAccessRule.java
 * @version 1.0
 */
@Entity
@Table(name = "FTP_ACCESS_RULE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FtpAccessRule extends LongIdObject {

    private static final long serialVersionUID = -6983358727544841048L;

    /**
     * IP地址规则
     */
    private String ip;
    /**
     * 访问情况
     */
    private boolean enableaccess;
    /**
     * 用户ID
     */
    private FtpUser ftpUser;
    

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isEnableaccess() {
        return enableaccess;
    }

    public void setEnableaccess(boolean enableaccess) {
        this.enableaccess = enableaccess;
    }
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    @Fetch(FetchMode.JOIN)
    public FtpUser getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(FtpUser ftpUser) {
        this.ftpUser = ftpUser;
    }

}
