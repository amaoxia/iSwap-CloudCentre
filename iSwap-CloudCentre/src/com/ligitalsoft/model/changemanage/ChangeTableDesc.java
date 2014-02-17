/*
 * @(#)ChangeTableDesc.java
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

/**
 * 交换_指标表结构
 * @author zhangx
 * @since Jun 14, 2011 11:02:15 AM
 * @name com.ligitalsoft.model.changemanage.ChangeTableDesc.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_TABLEDESC")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChangeTableDesc extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = 2785805161434123986L;
    /**
     * 字段名称
     */
    private String name;
    /**
     * 代码
     */
    private String filedcode;
    /**
     * 类型
     */
    private String dataType;
    /**
     * 长度
     */
    private Integer filedLength;
    /**
     * 是否展示
     */
    private String isShow="1";
    /**
     * 是否主键  1,0   1 主键
     */
    private String isPk;

    /**
     * 是否为 空  1，0  
     */
    private String isNull;
    /**
     * 关联指标对象
     */
    private ChangeItem changeItem;



    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public String getFiledcode() {
        return filedcode;
    }

    public void setFiledcode(String filedcode) {
        this.filedcode = filedcode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getFiledLength() {
        return filedLength;
    }

    public void setFiledLength(Integer filedLength) {
        this.filedLength = filedLength;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    public ChangeItem getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(ChangeItem changeItem) {
        this.changeItem = changeItem;
    }

}
