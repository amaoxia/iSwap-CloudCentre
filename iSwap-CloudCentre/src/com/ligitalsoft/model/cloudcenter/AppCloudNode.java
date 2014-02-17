package com.ligitalsoft.model.cloudcenter;

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
 * 节点定制    记录节点和应用之间的关系
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-8 下午05:37:39
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDCENTER_APPNODE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppCloudNode extends LongIdObject{
	
	private static final long serialVersionUID = 1L;
    /**
     * 应用信息
     */
	private AppMsg appMsg;
	/**
	 * 节点信息
	 */
	private CloudNodeInfo cloudNodeInfo;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID")
	public AppMsg getAppMsg() {
		return appMsg;
	}

	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_ID")
	public CloudNodeInfo getCloudNodeInfo() {
		return cloudNodeInfo;
	}

	public void setCloudNodeInfo(CloudNodeInfo cloudNodeInfo) {
		this.cloudNodeInfo = cloudNodeInfo;
	}
	
}
