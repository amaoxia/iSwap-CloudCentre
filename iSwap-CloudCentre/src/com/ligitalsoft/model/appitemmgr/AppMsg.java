package com.ligitalsoft.model.appitemmgr;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 应用管理
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-8 下午05:19:30
 * @Team 研发中心
 */
@Entity
@Table(name = "CLOUDCENTER_APP")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppMsg extends LongIdObject {

    private static final long serialVersionUID = 1L;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用编码
     */
    private String appCode;
    /**
     * 状态
     */
    private int status = 1;
    /**
     * 描述
     */
    private String remark;

    private Date createDate = new Date();


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
