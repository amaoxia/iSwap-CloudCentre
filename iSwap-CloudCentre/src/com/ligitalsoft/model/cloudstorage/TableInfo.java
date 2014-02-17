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
 * 指标项的信息
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-8 下午05:12:14
 * @Team 研发中心
 */
@Entity
@Table(name = "CLOUDSTOR_TABLEINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TableInfo extends LongIdObject {

    private static final long serialVersionUID = 1929482782465006559L;
    /**
     * 字段中文名称
     */
    private String name;
    /**
     * 字段编码
     */
    private String filedcode;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字段长度
     */
    private String filedLength;
    /**
     * 是否主键
     */
    private String isPk;
    /**
     * 是否为 空
     */
    private String isNull;
    /**
     * 指标项
     */
    private MetaData metaData;

    /**
     * 共享状态 0--未发布 1--发布成功
     */
    private String shareState = "0";

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

    public String getFiledLength() {
        return filedLength;
    }

    public void setFiledLength(String filedLength) {
        this.filedLength = filedLength;
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
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "METADATA_ID")
    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getShareState() {
        return shareState;
    }

    public void setShareState(String shareState) {
        this.shareState = shareState;
    }

}
