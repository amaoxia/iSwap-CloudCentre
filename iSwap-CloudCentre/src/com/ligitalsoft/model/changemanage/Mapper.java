package com.ligitalsoft.model.changemanage;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-9 下午06:53:26
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDNODE_MAPPING")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="MAPPER_TYPE")
@DiscriminatorValue("mapper")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Mapper  extends LongIdObject{

	private static final long serialVersionUID = 1L;
	/**
	 * mapping名称
	 */
	private String mapName;
	/**
	 * mapping代码
	 */
	private String mapCode;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * mpping模式　　0:发送  1:接收
	 */
	private String mappingType;
	/**
	 * 状态 		0:未部署  1:已部署
	 */
	private String status;

	/** 来源指标 **/
	private ChangeItem srcChangeItem;
	
	/**
	 * mapper内容
	 */
	private String contents;
	
	private  String  notes;
	
	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapCode() {
		return mapCode;
	}

	public void setMapCode(String mapCode) {
		this.mapCode = mapCode;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "SRC_ITEM_ID")
	public ChangeItem getSrcChangeItem() {
		return srcChangeItem;
	}
	
	public void setSrcChangeItem(ChangeItem srcChangeItem) {
		this.srcChangeItem = srcChangeItem;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Column(columnDefinition="text")
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Transient
	public List getMapperDetailsList() {
		return null;
	}
}
