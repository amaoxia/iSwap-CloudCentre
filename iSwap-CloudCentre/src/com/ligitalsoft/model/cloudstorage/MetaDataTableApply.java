/*
 * @(#)MetaDataTableApply.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.cloudstorage;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.system.SysDept;

/**
 * 数据申请查看表
 * @author zhangx
 * @since Aug 10, 2011 5:46:37 PM
 * @name com.ligitalsoft.model.cloudstorage.MetaDataTableApply.java
 * @version 1.0
 */
@Entity
@Table(name = "METADATA_TABLE_APPLY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetaDataTableApply extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = 3290466904069328586L;
    /**
     * 申请部门
     */
    private SysDept sysDept;
    private MetaData metaData;
    private AppMsg appMsg;
    private CouldDataSource dataSource;

    /**
     * 表名称
     */
    private String tableName;
    /**
     * 字段申请时间
     */
    private Date filedApplyDate = DateUtil.toDate(new Date());
    /**
     * 字段申请状态 0未申请, 1申请 2 修改申请
     */
    private String filedApplystate;
    /**
     * 字段授权状态 0 未授权 1 授权 2 重新授权中
     */
    private String filedAuthState = "0";
    /**
     * 字段授权说明
     */
    private String filedAuthDescribe;
    /**
     * 字段授权时间
     */
    private Date filedAuthDate;
    /**
     * 字段申请类型 0 内容,1文件
     */
    private String filedApplyType;
    /**
     * 字段申请内容
     */
    private String filedApplyContent;
    /**
     * 字段申请文件 存数据库
     */
    private byte[] filedApplyFile;
    /**
     * 文件名称
     */
    private String filedApplyName;

    /**
     * 数据申请类型
     */
    private String dataApplyType;
    /**
     * 数据申请内容
     */
    private String dataApplyContent;
    /**
     * 数据申请文件
     */
    private byte[] dataApplyFile;
    /**
     * 上传文件名称
     */
    private String dataApplyFileName;
    /**
     * 申请状态  0- 未申请 1--已申请 2 二次申请
     */
    private String dataApplyState = "0";
    /**
     * 申请时间
     */
    private Date dataApplyDate;

    /**
     * 数据授权时间
     */
    private Date dataAuthDate;
    /**
     * 授权内容说明
     */
    private String dataAuthDescribe;

    /**
     * 数据授权状态 0--未授权 1--授权
     */
    private String dataAuthState;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")
    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "META_ID")
    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_ID")
    public AppMsg getAppMsg() {
        return appMsg;
    }

    public void setAppMsg(AppMsg appMsg) {
        this.appMsg = appMsg;
    }
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "DATA_ID")
    public CouldDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(CouldDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getFiledApplyDate() {
        return filedApplyDate;
    }

    public void setFiledApplyDate(Date filedApplyDate) {
        this.filedApplyDate = filedApplyDate;
    }

    public String getFiledApplystate() {
        return filedApplystate;
    }

    public void setFiledApplystate(String filedApplystate) {
        this.filedApplystate = filedApplystate;
    }

    public String getFiledAuthState() {
        return filedAuthState;
    }

    public void setFiledAuthState(String filedAuthState) {
        this.filedAuthState = filedAuthState;
    }

    public String getFiledAuthDescribe() {
        return filedAuthDescribe;
    }

    public void setFiledAuthDescribe(String filedAuthDescribe) {
        this.filedAuthDescribe = filedAuthDescribe;
    }

    public Date getFiledAuthDate() {
        return filedAuthDate;
    }

    public void setFiledAuthDate(Date filedAuthDate) {
        this.filedAuthDate = filedAuthDate;
    }

    public String getFiledApplyType() {
        return filedApplyType;
    }

    public void setFiledApplyType(String filedApplyType) {
        this.filedApplyType = filedApplyType;
    }

    @Lob
    public String getFiledApplyContent() {
        return filedApplyContent;
    }

    public void setFiledApplyContent(String filedApplyContent) {
        this.filedApplyContent = filedApplyContent;
    }
    
    @Lob
    public byte[] getFiledApplyFile() {
        return filedApplyFile;
    }

    public void setFiledApplyFile(byte[] filedApplyFile) {
        this.filedApplyFile = filedApplyFile;
    }

    public String getFiledApplyName() {
        return filedApplyName;
    }

    public void setFiledApplyName(String filedApplyName) {
        this.filedApplyName = filedApplyName;
    }

    public String getDataApplyType() {
        return dataApplyType;
    }

    public void setDataApplyType(String dataApplyType) {
        this.dataApplyType = dataApplyType;
    }

    @Lob
    public String getDataApplyContent() {
        return dataApplyContent;
    }

    public void setDataApplyContent(String dataApplyContent) {
        this.dataApplyContent = dataApplyContent;
    }

    @Lob
    public byte[] getDataApplyFile() {
        return dataApplyFile;
    }

    public void setDataApplyFile(byte[] dataApplyFile) {
        this.dataApplyFile = dataApplyFile;
    }

    public String getDataApplyFileName() {
        return dataApplyFileName;
    }

    public void setDataApplyFileName(String dataApplyFileName) {
        this.dataApplyFileName = dataApplyFileName;
    }

    public String getDataApplyState() {
        return dataApplyState;
    }

    public void setDataApplyState(String dataApplyState) {
        this.dataApplyState = dataApplyState;
    }

    public Date getDataApplyDate() {
        return dataApplyDate;
    }

    public void setDataApplyDate(Date dataApplyDate) {
        this.dataApplyDate = dataApplyDate;
    }

    public String getDataAuthDescribe() {
        return dataAuthDescribe;
    }

    public void setDataAuthDescribe(String dataAuthDescribe) {
        this.dataAuthDescribe = dataAuthDescribe;
    }

    public String getDataAuthState() {
        return dataAuthState;
    }

    public void setDataAuthState(String dataAuthState) {
        this.dataAuthState = dataAuthState;
    }

    
    public Date getDataAuthDate() {
        return dataAuthDate;
    }

    
    public void setDataAuthDate(Date dataAuthDate) {
        this.dataAuthDate = dataAuthDate;
    }

}
