package com.ligitalsoft.model.changemanage;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@DiscriminatorValue("changedetails")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChangeItemMapperDetails  extends MapperDetails {

	private static final long serialVersionUID = 1L;
	
	private ChangeItemMapper mapper;
	
	/**
	 * 映射目的字段
	 */
	private ChangeTableDesc tarTableDesc;
	
	@ManyToOne(targetEntity = ChangeItemMapper.class,cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "mapper_id",referencedColumnName = "id")
	public ChangeItemMapper getMapper() {
		return mapper;
	}

	public void setMapper(ChangeItemMapper mapper) {
		this.mapper = mapper;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "TAR_TABLEDESC_ID")
	public ChangeTableDesc getTarTableDesc() {
		return tarTableDesc;
	}
	
	public void setTarTableDesc(ChangeTableDesc tarTableDesc) {
		this.tarTableDesc = tarTableDesc;
	}
}
