package com.ligitalsoft.model.changemanage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

@Entity
@Table(name = "send_datafomat_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SendDataFomatResult extends LongIdObject {

	private static final long serialVersionUID = 479880844590126332L;

	private Integer errCount;
	private Integer succCount;
	private Integer total;
	private String errlog;
	private Date createDate;
	private String itemCode;
	private String fileName;
	/**
	 * 文档采集同步状态
	 */
	@Column(length = 10)
	private String syncDocState = "0";
	/**
	 * 同步状态
	 */
	@Column(length = 10)
	private String syncState = "0";

	public Integer getErrCount() {
		return errCount;
	}

	public void setErrCount(Integer errCount) {
		this.errCount = errCount;
	}

	public Integer getSuccCount() {
		return succCount;
	}

	public void setSuccCount(Integer succCount) {
		this.succCount = succCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getErrlog() {
		return errlog;
	}

	public void setErrlog(String errlog) {
		this.errlog = errlog;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSyncDocState() {
		return syncDocState;
	}

	public void setSyncDocState(String syncDocState) {
		this.syncDocState = syncDocState;
	}

}
