package com.ligitalsoft.model.cloudcenter;

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
import com.ligitalsoft.model.appitemmgr.AppMsg;

/**
 * 云端存储空间定制
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-8 下午05:40:37
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDCENTER_STORAGE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CloudStorageMake  extends LongIdObject{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 存储的名称
	 */
	private String storageName;
	/**
	 * 更新频率
	 */
	private String times;
	/**
	 * 存储的总大小
	 */
	private String storageSizeCount;
	/**
	 * 已分配的存储大小
	 */
	private String storageSize;
	
	private Date createDate;
	/**
	 *应用信息 
	 */
	private AppMsg appMsg;
	
	/**
	 *描述
	 */
	private String description;
	
	

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}


	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getStorageSizeCount() {
		return storageSizeCount;
	}

	public void setStorageSizeCount(String storageSizeCount) {
		this.storageSizeCount = storageSizeCount;
	}

	public String getStorageSize() {
		return storageSize;
	}

	public void setStorageSize(String storageSize) {
		this.storageSize = storageSize;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID")
	public AppMsg getAppMsg() {
		return appMsg;
	}

	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
