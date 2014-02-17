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
 * 考核指标设置
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-15
 * @name com.ligitalsoft.model.CheckTargetSet.java
 * @version 1.0
 */
@Entity
@Table(name = "PERF_CHECKTARGETSET")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckTargetSet extends LongIdObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -183570331797879158L;
	/**
	 * 分数
	 */
	private int fraction;
	/**
	 * 考核项记分方法
	 */
	private String recodeMethod;
	/**
	 * 考核指标
	 */
	private CheckTarget checkTarget;
	/**
	 * 模板
	 */
	private Model model;
	
	public int getFraction() {
		return fraction;
	}
	
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}

	public String getRecodeMethod() {
		return recodeMethod;
	}
	
	public void setRecodeMethod(String recodeMethod) {
		this.recodeMethod = recodeMethod;
	}
	
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECK_TARGET_ID")
	public CheckTarget getCheckTarget() {
		return checkTarget;
	}
	
	public void setCheckTarget(CheckTarget checkTarget) {
		this.checkTarget = checkTarget;
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
