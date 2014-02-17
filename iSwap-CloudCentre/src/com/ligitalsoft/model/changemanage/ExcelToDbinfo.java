package com.ligitalsoft.model.changemanage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

@Entity
@Table(name = "EXCEL_TO_DBINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExcelToDbinfo extends LongIdObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6748632594416082328L;
	/**
	 * 文件名称
	 */
	@Column(length = 50)
	private String errorFileName;
	private Integer total;
	private Integer succCount;
	private Integer errTotal;
	private Date createDate;
	@Column(length = 10)
	private String syncState = "0";// 文档信息同步状态

	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}

	public String getErrorFileName() {
		return errorFileName;
	}

	public void setErrorFileName(String errorFileName) {
		this.errorFileName = errorFileName;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSuccCount() {
		return succCount;
	}

	public void setSuccCount(Integer succCount) {
		this.succCount = succCount;
	}

	public Integer getErrTotal() {
		return errTotal;
	}

	public void setErrTotal(Integer errTotal) {
		this.errTotal = errTotal;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
