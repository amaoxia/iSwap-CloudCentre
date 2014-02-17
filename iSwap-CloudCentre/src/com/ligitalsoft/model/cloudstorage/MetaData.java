package com.ligitalsoft.model.cloudstorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.system.SysDept;

/**
 * 元数据的管理
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-8 下午05:01:29
 * @Team 研发中心
 */
@Entity
@Table(name = "CLOUDSTOR_METADATA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetaData extends LongIdObject {

    private static final long serialVersionUID = 1L;
    /**
     * 指标项名称
     */
    private String targetName;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 类型 1-原始数据库,2-基础库管理,3-主题库管理,
     */
    private String type;
    /**
     * 部门
     */
    private SysDept sysDept;

    /**
     * 创建时间
     */
    private Date createDate = DateUtil.toDate(new Date());

    /**
     * 接收人
     */
    private String overPeople;

    /**
     * 接收人邮件地址
     */
    private String overPeopleEmail;
    /**
     * 数据源
     */
    private CouldDataSource couldDataSource;
    /**
     * 表的信息
     */
    private List<TableInfo> tableList = new ArrayList<TableInfo>();

    /**
     * 元数据下对应的应用服务
     */
    private List<MetaDataAppMsg> dataApp = new ArrayList<MetaDataAppMsg>();

    /**
     * 基础库所属类型
     */
    private MetaDataBasicType metaDataBasicType;
    /**
     *共享状态 0--未发布 1--发布成功
     */
    private String shareState="0";
    
    public String getOverPeople() {
        return overPeople;
    }

    public void setOverPeople(String overPeople) {
        this.overPeople = overPeople;
    }

    public String getOverPeopleEmail() {
        return overPeopleEmail;
    }

    public void setOverPeopleEmail(String overPeopleEmail) {
        this.overPeopleEmail = overPeopleEmail;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    public MetaDataBasicType getMetaDataBasicType() {
        return metaDataBasicType;
    }

    public void setMetaDataBasicType(MetaDataBasicType metaDataBasicType) {
        this.metaDataBasicType = metaDataBasicType;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")
    public SysDept getSysDept() {
        return sysDept;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_ID")
    public CouldDataSource getCouldDataSource() {
		return couldDataSource;
	}

	public void setCouldDataSource(CouldDataSource couldDataSource) {
		this.couldDataSource = couldDataSource;
	}

	public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    @OneToMany(mappedBy = "metaData", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public List<TableInfo> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableInfo> tableList) {
        this.tableList = tableList;
    }

    @OneToMany(mappedBy = "metaData", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
    public List<MetaDataAppMsg> getDataApp() {
        return dataApp;
    }

    public void setDataApp(List<MetaDataAppMsg> dataApp) {
        this.dataApp = dataApp;
    }

    
    public String getShareState() {
        return shareState;
    }

    public void setShareState(String shareState) {
        this.shareState = shareState;
    }
    
}
