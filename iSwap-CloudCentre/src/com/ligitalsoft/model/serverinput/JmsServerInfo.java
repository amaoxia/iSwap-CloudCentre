package com.ligitalsoft.model.serverinput;

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
import com.ligitalsoft.model.system.SysDept;

/**
 * jms server 接入信息
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-12 下午07:04:10
 * @Team 研发中心
 */
@Entity
@Table(name = "SERVINPUT_JMSSERVERINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JmsServerInfo extends LongIdObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 服务的名字
	 */
	private String jmsServerName;
	/**
	 * 上下文工厂
	 */
	private String jmsFactory;
	/**
	 * 连接工厂
	 */
	private String conntFactory;
	/**
	 * 部门
	 */
	private SysDept sysDept;
	/**
	 * 连接超时
	 */
	private String outTime;
	
	/**
	 * 连接地址
	 */
	private String url;

	/**
	 * 队列名称
	 */
	private String queueName;

	private String userName;

	private String passWord;

	private String notes;

	/**
	 * 创建时间
	 */
	private Date dateCreate = new Date();

	public String getJmsServerName() {
		return jmsServerName;
	}

	public void setJmsServerName(String jmsServerName) {
		this.jmsServerName = jmsServerName;
	}

	public String getJmsFactory() {
		return jmsFactory;
	}

	public void setJmsFactory(String jmsFactory) {
		this.jmsFactory = jmsFactory;
	}

	public String getConntFactory() {
		return conntFactory;
	}

	public void setConntFactory(String conntFactory) {
		this.conntFactory = conntFactory;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
