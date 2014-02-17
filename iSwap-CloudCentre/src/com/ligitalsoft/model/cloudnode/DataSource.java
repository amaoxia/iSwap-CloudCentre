package com.ligitalsoft.model.cloudnode;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.system.SysDept;

/**
 * 数据源的管理
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-9 上午09:28:40
 * @Team 研发中心
 */
@Entity
@Table(name = "CLOUDNODE_DATASOURCE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataSource extends LongIdObject {

	private static final long serialVersionUID = 1L;

	private String mysql_address = "jdbc:mysql://ip:port/dbName";
	private String db2_address = "jdbc:db2://ip:port/dbName";
	private String sqlserver_address = "jdbc:microsoft:sqlserver://ip:port;DatabaseName=dbName";
	private String oracle_address = "jdbc:oracle:thin:@ip:port:dbName";

	private String sourceName;

	private String sourceCode;

	private SysDept sysDept;

	private String type;

	private String driveName;

	private String address;

	/**
	 * 连接地址
	 */
	private String ip;

	/**
	 * 数据库名称
	 */
	private String dbName;

	/**
	 * 数据库端口
	 */
	private String port;

	private String userName;

	private String passWord;

	private CloudNodeInfo cloudNodeInfo;

	private String maxConnt;

	private String defaultConnt;

	private Date createDate;
	/**
	 * 状态
	 */
	private String status;
	
	/** GBK,UTF-8*/
	private String characterEncoding;

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

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDriveName() {
		if (!StringUtils.isBlank(getType())) {
			if (getType().equals("SQLSERVER")) {
				driveName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
			} else if (getType().equals("MYSQL")) {
				driveName = "com.mysql.jdbc.Driver";
			} else if (getType().equals("ORACLE")) {
				driveName = "oracle.jdbc.driver.OracleDriver";
			} else {
				driveName = "com.ibm.db2.jcc.DB2Driver";
			}
		}
		return driveName;
	}

	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}

	public String getAddress() {
		String url = "";
		if (!StringUtils.isBlank(getType())&&!StringUtils.isBlank(ip)&&!StringUtils.isBlank(port)&&!StringUtils.isBlank(dbName)) {
			if (getType().equals("SQLSERVER")) {
				url = sqlserver_address.replace("ip", ip);
				url = url.replace("port", port);
				url = url.replace("dbName", dbName);
				address=url;
			} else if (getType().equals("MYSQL")) {
				url = mysql_address.replace("ip", ip);
				url = url.replace("port", port);
				url = url.replace("dbName", dbName);
				address = url;
			} else if (getType().equals("ORACLE")) {
				url = oracle_address.replace("ip", ip);
				url = url.replace("port", port);
				url = url.replace("dbName", dbName);
				address = url;
			} else {
				url = db2_address.replace("ip", ip);
				url = url.replace("port", port);
				url = url.replace("dbName", dbName);
				address = url;
			}
		}
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_ID")
	public CloudNodeInfo getCloudNodeInfo() {
		return cloudNodeInfo;
	}

	public void setCloudNodeInfo(CloudNodeInfo cloudNodeInfo) {
		this.cloudNodeInfo = cloudNodeInfo;
	}

	public String getMaxConnt() {
		return maxConnt;
	}

	public void setMaxConnt(String maxConnt) {
		this.maxConnt = maxConnt;
	}

	public String getDefaultConnt() {
		return defaultConnt;
	}

	public void setDefaultConnt(String defaultConnt) {
		this.defaultConnt = defaultConnt;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

}
