package com.ligitalsoft.model.cloudstorage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * 云存储中心 的对象
 *
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-7 上午11:12:31
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDSTOR_CENTER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CloudStorageCenter extends LongIdObject {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 中心服务的名称
	 */
	private String serverName;
	/**
	 * 存储中心的地址
	 */
    private String address;
    /**
	 * 端口
	 */
    private String port;
    /**
	 * 数据存储地址
	 */
    private String dataPath;
    /**
	 * 日志存储地址
	 */
    private String logPath;
    /**
	 * 索引存储地址
	 */
    private String indexPath;
    /**
	 * 存储检测文件地址
	 */
    private String storagePath;
    /**
	 *连接超时时间 
	 */
    private String nodeConnTime;
    
    private Date createDate=DateUtil.toDate(new Date());
    
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
   

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public String getIndexPath() {
		return indexPath;
	}

	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public String getNodeConnTime() {
		return nodeConnTime;
	}

	public void setNodeConnTime(String nodeConnTime) {
		this.nodeConnTime = nodeConnTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
