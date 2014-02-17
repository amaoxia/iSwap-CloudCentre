/*
 * @(#)SysEmailConfig.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.system;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * 系统邮件
 * @author zhangx
 * @since Aug 3, 2011 4:05:26 PM
 * @name com.ligitalsoft.model.system.SysEmailConfig.java
 * @version 1.0
 */
@Entity
@Table(name = "SYS_EMAILCONFIG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysEmailConfig extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = 6814157366731970273L;
    /**
     * 邮件地址
     */
    private String emailAddress;
    /**
     * 服务器邮件地址
     */
    private String emailServerAddress;

    /**
     * 邮箱帐号
     */
    private String emailAccount;

    /**
     * 邮箱密码
     */
    private String emailPassWord;
    /**
     * 创建时间
     */
    private Date createDate = DateUtil.toDate(new Date());
    /**
     * 创建人
     */
    private String creator;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailServerAddress() {
        return emailServerAddress;
    }

    public void setEmailServerAddress(String emailServerAddress) {
        this.emailServerAddress = emailServerAddress;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getEmailPassWord() {
        return emailPassWord;
    }

    public void setEmailPassWord(String emailPassWord) {
        this.emailPassWord = emailPassWord;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}
