package com.ligitalsoft.model.changemanage;

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

/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-9 下午06:53:26
 *@Team 研发中心
 */
@Entity
@DiscriminatorValue("change")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChangeItemMapper extends Mapper {

	private static final long serialVersionUID = 1L;
	
	/** 目的指标 **/
	private ChangeItem tarChangeItem;
	
	private List<ChangeItemMapperDetails> mapperDetailsList;
		
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "TAR_ITEM_ID")
	public ChangeItem getTarChangeItem() {
		return tarChangeItem;
	}
	
	public void setTarChangeItem(ChangeItem tarChangeItem) {
		this.tarChangeItem = tarChangeItem;
	}

	@OneToMany(mappedBy = "mapper", cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
	public List<ChangeItemMapperDetails> getMapperDetailsList() {
		return mapperDetailsList;
	}

	public void setMapperDetailsList(List<ChangeItemMapperDetails> mapperDetailsList) {
		this.mapperDetailsList = mapperDetailsList;
	}
}
