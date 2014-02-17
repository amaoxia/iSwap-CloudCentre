/*
 * @(#)MetaDataType.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.cloudstorage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * 元数据库类型_基础库类型
 * @author zhangx
 * @since Jul 19, 2011 5:16:41 PM
 * @name com.ligitalsoft.model.cloudstorage.MetaDataType.java
 * @version 1.0
 */
@Entity
@Table(name = "METADATA_BASICTYPE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetaDataBasicType extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = 3437296517738399788L;
    /**
     * 基础库类型名称
     */
    private String basicTypeName;
    /**
     * 基础库类型编码
     */
    private String basicTypeCode;
    /**
     * 基础库类型描述
     */
    private String descript;
    /**
     * 基础库创建时间
     */
    private Date createDate = DateUtil.toDate(new Date());
    
    public String getBasicTypeName() {
        return basicTypeName;
    }
    
    public void setBasicTypeName(String basicTypeName) {
        this.basicTypeName = basicTypeName;
    }
    
    public String getBasicTypeCode() {
        return basicTypeCode;
    }
    
    public void setBasicTypeCode(String basicTypeCode) {
        this.basicTypeCode = basicTypeCode;
    }
    
    public String getDescript() {
        return descript;
    }
    
    public void setDescript(String descript) {
        this.descript = descript;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
            
}
