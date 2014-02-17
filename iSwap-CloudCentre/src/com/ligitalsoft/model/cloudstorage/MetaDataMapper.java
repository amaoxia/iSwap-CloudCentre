package com.ligitalsoft.model.cloudstorage;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ligitalsoft.model.changemanage.Mapper;

/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-9 下午06:53:26
 *@Team 研发中心
 */
@Entity
@DiscriminatorValue("meta")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetaDataMapper extends Mapper {

	private static final long serialVersionUID = 1L;
	
	/** 目的指标 **/
	private MetaData tarMetaData;
	
	private List<MetaDataMapperDetails> mapperDetailsList;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "TAR_META_ID")
	public MetaData getTarMetaData() {
		return tarMetaData;
	}
	
	public void setTarMetaData(MetaData tarMetaData) {
		this.tarMetaData = tarMetaData;
	}

	@OneToMany(mappedBy = "mapper", cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
	public List<MetaDataMapperDetails> getMapperDetailsList() {
		return mapperDetailsList;
	}
	
	public void setMapperDetailsList(List<MetaDataMapperDetails> mapperDetailsList) {
		this.mapperDetailsList = mapperDetailsList;
	}
}
