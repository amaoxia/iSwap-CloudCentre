/**  
 * @公司名称：北京光码软件有限公司
 * @项目名称：iSwapV6.0云计算数据交换平台
 * @文件名称：Funcation.java
 * @子模块名：iSwapQA 数据审计
 * @模块名称：
 * @功能作用：规则文件中 函数实体类
 * @文件作者： Tony Wong
 * @创建时间：2011-6-27 上午10:20:42
 * @版本编号：v1.0  
 * @最后修改：(修改人) 2011-6-27 上午10:20:42
 */
package com.ligitalsoft.model.iswapqa;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * @author Tony
 */
@Entity
@Table(name = "ISWAPQA_FUNC_FILE_REL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FunctionFileRel extends LongIdObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 函数ID
	 */
	private long funcId;

	/**
	 * 规则文件ID
	 */
	private long ruleFileId;

	public long getFuncId() {
		return funcId;
	}

	public void setFuncId(long funcId) {
		this.funcId = funcId;
	}

	public long getRuleFileId() {
		return ruleFileId;
	}

	public void setRuleFileId(long ruleFileId) {
		this.ruleFileId = ruleFileId;
	}

	

}
