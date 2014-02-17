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
import com.ligitalsoft.model.performanage.Department;


/**
 * 部门和模板关系
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-16
 * @name com.ligitalsoft.model.DeptModelContact.java
 * @version 1.0
 */
@Entity
@Table(name = "PERF_DEPTMODELCONTACT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeptModelContact extends LongIdObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8103188247484107450L;
	/**
	 * 状态：1.季，2.年
	 */
	private String status;
	/**
	 * 部门
	 */
	private Department department;
	/**
	 * 模板
	 */
	private Model model;
		
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")
	public Department getDepartment() {
		return department;
	}
    
	public void setDepartment(Department department) {
		this.department = department;
	}
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID")	
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
}
