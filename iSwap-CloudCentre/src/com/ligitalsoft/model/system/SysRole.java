/*
 * @(#)SysRole.java
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
 * 角色信息表
 * @author zhangx
 * @since May 13, 2011 3:49:34 PM
 * @name com.ligitalsoft.model.system.SysRole.java
 * @version 1.0
 */
@Entity
@Table(name = "SYS_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRole extends LongIdObject {

    private static final long serialVersionUID = -125841757285479066L;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色代码
     */
    private String code;
    /**
     * 角色描述
     */
    private String descript;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date dateCreate =DateUtil.toDate(new Date());

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescript() {
        return descript;
    }
    public void setDescript(String descript) {
        this.descript = descript;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Date getDateCreate() {
        return dateCreate;
    }
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}
