package com.ligitalsoft.model.changemanage;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "CLOUDNODE_MAPPING_DETAILS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="MAPPER_DETAILS_TYPE")  
@DiscriminatorValue("mapperdetails")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MapperDetails  extends LongIdObject{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 映射源字段
	 */
	private ChangeTableDesc srcTableDesc;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "SRC_TABLEDESC_ID")
	public ChangeTableDesc getSrcTableDesc() {
		return srcTableDesc;
	}
	
	public void setSrcTableDesc(ChangeTableDesc srcTableDesc) {
		this.srcTableDesc = srcTableDesc;
	}
}
