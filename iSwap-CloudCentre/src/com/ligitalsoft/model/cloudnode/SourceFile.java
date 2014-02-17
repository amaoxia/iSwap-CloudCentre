package com.ligitalsoft.model.cloudnode;

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
 * 源文件
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-12 下午10:28:18
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDNODE_SRCFILE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SourceFile  extends LongIdObject{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 文件名称
	 */
	private  String fileName;
	/**
	 * 类型
	 */
	private  String type;
	/**
	 * 路径
	 */
	private  String path;
	/**
	 * 文件内容
	 */
	private  String contents;
	/**
	 * 数据源
	 */
	private  DataSource ds;
	/**
	 * 头部内容
	 */
	private  String headText;
	/**
	 * 字段
	 */
	private  String fileds;
	/**
	 * 表名
	 */
	private  String tableName;
	/**
	 * mapping文件
	 */
	private MappingFile mapping;
	
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public String getHeadText() {
		return headText;
	}

	public void setHeadText(String headText) {
		this.headText = headText;
	}

	public String getFileds() {
		return fileds;
	}

	public void setFileds(String fileds) {
		this.fileds = fileds;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "MAPPING_ID")
	public MappingFile getMapping() {
		return mapping;
	}

	public void setMapping(MappingFile mapping) {
		this.mapping = mapping;
	}
}
