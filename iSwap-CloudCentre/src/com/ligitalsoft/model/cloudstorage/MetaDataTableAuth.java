/*
 * @(#)MetaDataTableAuth.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.cloudstorage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 申请数据项授权表
 * @author zhangx
 * @since Aug 10, 2011 5:46:37 PM
 * @name com.ligitalsoft.model.cloudstorage.MetaDataTableApply.java
 * @version 1.0
 */
@Entity
@Table(name = "METADATA_TABLE_AUTH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetaDataTableAuth extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = 6396213013890907651L;

    /**
     * 申请字段Id
     */
    private TableInfo tableInfo;
    /**
     * 字段授权状态 0 未授权  1授权
     */
    private String filedAuthState="0";
    /**
     * 数据授权状态   null 未申请  0 申请_未授权  1 申请_授权
     */
    private String dataAuthState;
    private MetaDataTableApply metaDataTableApply;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLY_ID")
    public MetaDataTableApply getMetaDataTableApply() {
        return metaDataTableApply;
    }

    public void setMetaDataTableApply(MetaDataTableApply metaDataTableApply) {
        this.metaDataTableApply = metaDataTableApply;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "TALBE_ID")
    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String getFiledAuthState() {
        return filedAuthState;
    }

    public void setFiledAuthState(String filedAuthState) {
        this.filedAuthState = filedAuthState;
    }

    public String getDataAuthState() {
        return dataAuthState;
    }

    public void setDataAuthState(String dataAuthState) {
        this.dataAuthState = dataAuthState;
    }
}
