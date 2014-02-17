package com.ligitalsoft.model.performanage;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.common.framework.domain.StringIdObject;

/**
 * 部门
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-16
 * @name com.ligitalsoft.model.Department.java
 * @version 1.0
 */
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department extends StringIdObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 
	private String id;
	*/
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 部门编码
	 */
	private String code;
	/**
	 * 部门地址
	 */
	private String address;
	/**
	 * 是否为接收方
	 */
	private String reveiver;
	/**
	 * 排序
	
	private int sort;
	 */
	/**
	 * 发送库实例名
	 */
	private String sendDbName;
	/**
	 * 发送库地址IP
	 */
	private String sendDbIp;
	/**
	 * 发送库用户名
	 */
	private String sendJdbcUsername;
	/**
	 * 发送库密码
	 */
	private String sendJdbcPwd;
	/**
	 * 接收库实例名
	 */
	private String receDbName;
	/**
	 * 接收库地址IP
	 */
	private String receDbIp;
	/**
	 * 接收库用户名
	 */
	private String receJdbcUsername;
	/**
	 * 接收库密码
	 */
	private String receJdbcPwd;
	/**
	 * 服务地址
	 */
	private String serviceUrl;
	/**
	 * 发送库端口
	 */
	private String sendJdbcPort;
	/**
	 * 接收库端口
	 */
	private String receJdbcPort;
	/**
	 * 接收数据库类型ID
	 */
	private String receDbTypeId;
	/**
	 * 发送数据库类型
	 */
	private String sendDbTypeId;
	/**
	 * 是否考评
	 */
	private String isAccess;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getReveiver() {
		return reveiver;
	}
	public void setReveiver(String reveiver) {
		this.reveiver = reveiver;
	}
//	public int getSort() {
//		return sort;
//	}
//	public void setSort(int sort) {
//		this.sort = sort;
//	}
	public String getSendDbName() {
		return sendDbName;
	}
	public void setSendDbName(String sendDbName) {
		this.sendDbName = sendDbName;
	}
	public String getSendDbIp() {
		return sendDbIp;
	}
	public void setSendDbIp(String sendDbIp) {
		this.sendDbIp = sendDbIp;
	}
	public String getSendJdbcUsername() {
		return sendJdbcUsername;
	}
	public void setSendJdbcUsername(String sendJdbcUsername) {
		this.sendJdbcUsername = sendJdbcUsername;
	}
	public String getSendJdbcPwd() {
		return sendJdbcPwd;
	}
	public void setSendJdbcPwd(String sendJdbcPwd) {
		this.sendJdbcPwd = sendJdbcPwd;
	}
	public String getReceDbName() {
		return receDbName;
	}
	public void setReceDbName(String receDbName) {
		this.receDbName = receDbName;
	}
	public String getReceDbIp() {
		return receDbIp;
	}
	public void setReceDbIp(String receDbIp) {
		this.receDbIp = receDbIp;
	}
	public String getReceJdbcUsername() {
		return receJdbcUsername;
	}
	public void setReceJdbcUsername(String receJdbcUsername) {
		this.receJdbcUsername = receJdbcUsername;
	}
	public String getReceJdbcPwd() {
		return receJdbcPwd;
	}
	public void setReceJdbcPwd(String receJdbcPwd) {
		this.receJdbcPwd = receJdbcPwd;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public String getSendJdbcPort() {
		return sendJdbcPort;
	}
	public void setSendJdbcPort(String sendJdbcPort) {
		this.sendJdbcPort = sendJdbcPort;
	}
	public String getReceJdbcPort() {
		return receJdbcPort;
	}
	public void setReceJdbcPort(String receJdbcPort) {
		this.receJdbcPort = receJdbcPort;
	}
	public String getReceDbTypeId() {
		return receDbTypeId;
	}
	public void setReceDbTypeId(String receDbTypeId) {
		this.receDbTypeId = receDbTypeId;
	}
	public String getSendDbTypeId() {
		return sendDbTypeId;
	}
	public void setSendDbTypeId(String sendDbTypeId) {
		this.sendDbTypeId = sendDbTypeId;
	}
	public String getIsAccess() {
		return isAccess;
	}
	public void setIsAccess(String isAccess) {
		this.isAccess = isAccess;
	}
	
}
