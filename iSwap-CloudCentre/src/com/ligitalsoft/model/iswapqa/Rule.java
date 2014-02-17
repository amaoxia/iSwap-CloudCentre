/**  
* @公司名称：北京光码软件有限公司
* @项目名称：iSwapV6.0云计算数据交换平台
* @文件名称：Rule.java
* @子模块名：iSwapQA 数据审计
* @模块名称：
* @功能作用：规则文件中，规则块对象
* @文件作者： Tony Wong
* @创建时间：2011-6-27 上午10:06:17
* @版本编号：v1.0  
* @最后修改：(修改人) 2011-6-27 上午10:06:17
*/ 
package com.ligitalsoft.model.iswapqa;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;


/**
 * @author Tony
 *
 */
@Entity
@Table(name = "ISWAPQA_RULE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rule extends LongIdObject {

    private static final long serialVersionUID = 1L;

    /**
     * 规则名称
     */
    private String ruleName;
    
    /**
     * 规则属性
     */
    private String attribute;
    
    /**
     * 规则条件 
     */
    private Map<String ,String> whens;
    /**
     * 规则条件字符串
     */
    private String whenStr;
    /**
     * 规则条件
     */
    private String paramTypeVal;


	public String getParamTypeVal() {
		return paramTypeVal;
	}


	public void setParamTypeVal(String paramTypeVal) {
		this.paramTypeVal = paramTypeVal;
	}


	/**
     * 成功执行
     */
    private Map<String ,String> thens;
    /**
     * 成功执行字符串
     */
    private String thenStr;
    /**
     * rule字符串
     */
    private String ruleStr;
    
    public String getRuleStr() {
		return ruleStr;
	}


    @Column(length=2000)
	public void setRuleStr(String ruleStr) {
		this.ruleStr = ruleStr;
	}


	public String getWhenStr() {
		return whenStr;
	}


	@Column(length=2000)
	public void setWhenStr(String whenStr) {
		this.whenStr = whenStr;
	}


	public String getThenStr() {
		return thenStr;
	}


	@Column(length=2000)
	public void setThenStr(String thenStr) {
		this.thenStr = thenStr;
	}


	public String getRuleName() {
        return ruleName;
    }

    
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    @Transient
    public Map<String, String> getWhens() {
        return whens;
    }
    
    public void setWhens(Map<String, String> whens) {
        this.whens = whens;
    }

    @Transient
    public Map<String, String> getThens() {
        return thens;
    }

    
    public void setThens(Map<String, String> thens) {
        this.thens = thens;
    }
    
    
    
}
