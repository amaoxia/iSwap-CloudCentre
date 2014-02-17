package com.ligitalsoft.model.cloudstorage;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.common.framework.domain.LongIdObject;
import com.common.utils.date.DateUtil;

/**
 * ESB数据源的管理
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-10-17下午05:40:46
 * @Team 研发中心
 */
@Entity
@Table(name = "CLOUDSTOR_DATASOURCE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="dbType")   
public abstract class AbstractDataSource extends LongIdObject {

	private static final long serialVersionUID = 1L;

	private String ip;
	
	private String port;
	
	private String dbName;
	
	private String sourceName;

	private String sourceCode;

	private String userName;

	private String passWord;

	private Date createDate = DateUtil.toDate(new Date());

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
