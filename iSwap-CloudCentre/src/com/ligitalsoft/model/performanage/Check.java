package com.ligitalsoft.model.performanage;

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

/**
 * 考核
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-15
 * @name com.ligitalsoft.model.Check.java
 * @version 1.0
 */
@Entity
@Table(name = "PERF_CHECK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Check extends LongIdObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1386465013823510759L;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 考核日期
	 */
	private Date checkTime;
	/**
	 * 考核年份
	 */
	private String checkYear;
	/**
	 * 通报状态:1.未通报,2.已通报
	 */
	private String status;
	/**
	 * 周期:1.1季，2.2季，3.3季，4.4季，5.5年
	 */
	private String cycleType;
	/**
	 * 删除标识 1:默认不删除 2：理论删除
	 */
	private String isDel;
	/**
	 * 应用ID
	 */
	private String appId;
	/**
	 * 模板
	 */
	private Model model;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCheckTime() {
		return checkTime;
	}
	
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
		
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCycleType() {
		return cycleType;
	}

	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}

	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
    public String getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(String checkYear) {
		this.checkYear = checkYear;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID")
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
