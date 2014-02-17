package com.ligitalsoft.model.cloudstorage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * 云存储节点的管理
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-8 下午02:06:58
 * @Team 研发中心
 */
@Entity
@Table(name = "CLOUDSTOR_NODEINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CloudNodeMg extends LongIdObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 节点名称
	 */
	private String nodesName;
	/**
	 * 服务地址
	 */
	private String serverAddress;
	/**
	 * 存储大小
	 */
	private String storageCount;
	/**
	 * 状态 1--成功 0--失败
	 */
	private String status;

	private Date createDate = DateUtil.toDate(new Date());

	/**
	 * 节点描述
	 */
	private String remark;

	/**
	 * 使用量
	 */
	private Double useCount=new Double(0);

	@Column(name = "NODE_NAME")
	public String getNodesName() {
		return nodesName;
	}

	public void setNodesName(String nodesName) {
		this.nodesName = nodesName;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getStorageCount() {
		return storageCount;
	}

	public void setStorageCount(String storageCount) {
		this.storageCount = storageCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public Double getUseCount() {
		return useCount;
	}

	public void setUseCount(Double useCount) {
		this.useCount = useCount;
	}

}
