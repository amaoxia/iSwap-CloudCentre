/*
 * @(#)MetaDataMsgApp.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.cloudstorage;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.appitemmgr.AppMsg;

/**
 * 元数据_应用关系表
 * @author zhangx
 * @since Jun 23, 2011 3:50:33 PM
 * @name com.ligitalsoft.model.cloudstorage.MetaDataMsgApp.java
 * @version 1.0
 */
 @Entity
 @Table(name = "CLOUDSTOR_METADATA_APPMSG")
 @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetaDataAppMsg extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = -2477453099055469330L;
    /**
     * 元数据集合
     */
    private MetaData metaData;
    /**
     * 应用集合
     */
    private AppMsg appMsg;

    /**
     * 发布状态 0-未发布,1-发布
     */
    private String shareState = "0";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "META_ID")
    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_ID")
    public AppMsg getAppMsg() {
        return appMsg;
    }

    public void setAppMsg(AppMsg appMsg) {
        this.appMsg = appMsg;
    }

    public String getShareState() {
        return shareState;
    }   

    public void setShareState(String shareState) {
        this.shareState = shareState;
    }

}
