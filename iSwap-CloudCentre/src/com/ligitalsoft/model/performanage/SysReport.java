package com.ligitalsoft.model.performanage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 系统报告
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-16
 * @name com.ligitalsoft.model.SysReport.java
 * @version 1.0
 */
@Entity
@Table(name = "PERF_SYSREPORT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysReport extends LongIdObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1946564463736274L;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 类型:1.汇总表,2.部门表
	 */
	private String fileType;
	/**
	 * 考核
	 */
	private Check check;
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
    public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECK_ID")	
	public Check getCheck() {
		return check;
	}
	
	public void setCheck(Check check) {
		this.check = check;
	}

}
