package com.ligitalsoft.model.cloudstorage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@DiscriminatorValue("relational") 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CouldDataSource extends AbstractDataSource {

	private static final long serialVersionUID = 1L;
	private String mysql_address = "jdbc:mysql://ip:port/dbName";
	private String db2_address = "jdbc:db2://ip:port/dbName";
	private String sqlserver_address = "jdbc:microsoft:sqlserver://ip:port/dbName";
	private String oracle_address = "jdbc:oracle:thin:@ip:port:dbName";
	
	private String type;
	
	private String driveName;

	private String address;

	private String maxConnt="5";

	private String defaultConnt="5";




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
		if (!StringUtils.isBlank(getType())&&!StringUtils.isBlank(getIp())&&!StringUtils.isBlank(getPort())&&!StringUtils.isBlank(getDbName())) {
			if (getType().equals("SQLSERVER")) {
				url = sqlserver_address.replace("ip", getIp());
				url = url.replace("port", getPort());
				url = url.replace("dbName", getDbName());
				address = url;
			} else if (getType().equals("MYSQL")){
				url = mysql_address.replace("ip", getIp());
				url = url.replace("port", getPort());
				url = url.replace("dbName", getDbName());
				address = url;
			} else if (getType().equals("ORACLE")) {
				url = oracle_address.replace("ip", getIp());
				url = url.replace("port", getPort());
				url = url.replace("dbName", getDbName());
				address = url;
			} else {
				url = db2_address.replace("ip", getIp());
				url = url.replace("port", getPort());
				url = url.replace("dbName", getDbName());
				address = url;
			}
		}
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}
