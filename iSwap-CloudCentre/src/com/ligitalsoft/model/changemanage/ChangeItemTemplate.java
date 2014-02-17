/*
 * @(#)ChangeItemTemplate.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.changemanage;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * 交换指标_模板
 * @author zhangx
 * @since Jun 23, 2011 10:04:05 AM
 * @name com.ligitalsoft.model.changemanage.ChangeItemTemplate.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_ITEM_TEMPLATE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChangeItemTemplate extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = 2162869322450840535L;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * 模板下载次数
     */
    private Integer downCount=0;
    /**
     * 模板状态(0 禁用,1 启用)
     */
    private String state;
    /**
     * 上传状态(0 未上传,1 上传)
     */
    private String  uploadState="0";
    /**
     * 上传人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date date = DateUtil.toDate(new Date());
    /**
     * 关联指标
     */
    private ChangeItem changeItem;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public Integer getDownCount() {
        return downCount;
    }

    public void setDownCount(Integer downCount) {
        this.downCount = downCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    public ChangeItem getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(ChangeItem changeItem) {
        this.changeItem = changeItem;
    }

	public String getUploadState() {
		return uploadState;
	}

	public void setUploadState(String uploadState) {
		this.uploadState = uploadState;
	}
    
    
}
