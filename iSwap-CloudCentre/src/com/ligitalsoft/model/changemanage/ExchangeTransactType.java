

package com.ligitalsoft.model.changemanage;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 催办表
 * @author daic
 * @since 2011-08-16 16:16:38
 * @name com.ligitalsoft.model.changemanage.ExchangeCycle.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_TransactType")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExchangeTransactType extends LongIdObject {

    /**
     * 
     */
	private static final long serialVersionUID = -3899748623842439512L;
	private String name;
	@SuppressWarnings("unchecked")
	private Set transacts = new HashSet(0);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@SuppressWarnings("unchecked")
	@Transient
	public Set getTransacts() {
		return transacts;
	}
	@SuppressWarnings("unchecked")
	public void setTransacts(Set transacts) {
		this.transacts = transacts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
