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


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;


/**
 * @author Tony
 *
 */
@Entity
@Table(name = "ISWAPQA_RULE_FILE_REL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RuleFileRel extends LongIdObject {

    private static final long serialVersionUID = 1L;

    /**
     * 规则ID
     */
    private long ruleId;
    
    /**
     * 规则文件ID
     */
    private long ruleFileId;

	public long getRuleId() {
		return ruleId;
	}

	public void setRuleId(long ruleId) {
		this.ruleId = ruleId;
	}

	public long getRuleFileId() {
		return ruleFileId;
	}

	public void setRuleFileId(long ruleFileId) {
		this.ruleFileId = ruleFileId;
	}

    
}
