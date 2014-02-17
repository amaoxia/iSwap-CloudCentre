/*
 * @(#)ChangeItemAppMsg.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.changemanage;

import javax.persistence.CascadeType;
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
 * 指标_应用_关系表
 * @author zhangx
 * @since Jun 23, 2011 3:23:51 PM
 * @name com.ligitalsoft.model.changemanage.ChangeItemAppMsg.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_ITEM_APPMSG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChangeItemAppMsg extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = 4614762433194134335L;

    /**
     * 指标对象
     */
    private ChangeItem changeItem;
    /**
     * 应用关联
     */
    private AppMsg appMsg;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    public ChangeItem getChangeItem() {
        return changeItem;
    }
    public void setChangeItem(ChangeItem changeItem) {
        this.changeItem = changeItem;
    }
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_ID")
    public AppMsg getAppMsg() {
        return appMsg;
    }
    
    public void setAppMsg(AppMsg appMsg) {
        this.appMsg = appMsg;
    }
}
