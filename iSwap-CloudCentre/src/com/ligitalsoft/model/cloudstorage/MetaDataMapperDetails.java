package com.ligitalsoft.model.cloudstorage;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ligitalsoft.model.changemanage.MapperDetails;
import com.ligitalsoft.model.cloudstorage.TableInfo;

/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-9 下午06:53:26
 *@Team 研发中心
 */
@Entity
@DiscriminatorValue("metadetails")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetaDataMapperDetails extends MapperDetails {

	private static final long serialVersionUID = 1L;
	
	private MetaDataMapper mapper;
	
	/**
	 * 映射目的字段
	 */
	private TableInfo tarTableInfo;
	
	@ManyToOne(targetEntity = MetaDataMapper.class,cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "mapper_id",referencedColumnName = "id")
	public MetaDataMapper getMapper() {
		return mapper;
	}

	public void setMapper(MetaDataMapper mapper) {
		this.mapper = mapper;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "TAR_TABLEINFO_ID")
	public TableInfo getTarTableInfo() {
		return tarTableInfo;
	}
	
	public void setTarTableInfo(TableInfo tarTableInfo) {
		this.tarTableInfo = tarTableInfo;
	}
}
