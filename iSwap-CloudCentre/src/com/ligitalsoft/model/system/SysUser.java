package com.ligitalsoft.model.system;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * 用户信息
 * 
 * @author zhangx
 * @since May 13, 2011 3:23:27 PM
 * @name com.ligitalsoft.model.system.SysUser.java
 * @version 1.0
 */
@Table(name = "SYS_USER")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUser extends LongIdObject {

	private static final long serialVersionUID = 1L;
	/**
	 * 身份证号
	 */
	private String idCardCode;
	/**
	 * 用户标识符
	 */
	private String userUid;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 性别
	 */
	private Character sex;
	/**
	 * 登录名称
	 */
	private String loginName;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 用户状态  1 激活  0  禁用
	 */
	private Character status='1';
	/**
	 * EMAIL
	 */
	private String email;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private Date dateCreate=DateUtil.toDate(new Date());
	/**
	 * 描述
	 */
	private String remark;

	public String getIdCardCode() {
		return idCardCode;
	}

	public void setIdCardCode(String idCardCode) {
		this.idCardCode = idCardCode;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
