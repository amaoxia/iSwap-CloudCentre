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

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * @author Tony
 */
@Entity
@Table(name = "ISWAPQA_FUNCTION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Function extends LongIdObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 函数名
	 */
	private String funcName;

	/**
	 * 函数参数
	 */
	private Map<String, String> funcParam;

	/**
	 * 函数体
	 */
	private String funcBody;

	/**
	 * 函数返回类型
	 */
	private String funcRetn;
	/**
	 * 函数字符串
	 */
	private String funcStr;
	public String getFuncStr() {
		return funcStr;
	}

	public void setFuncStr(String funcStr) {
		this.funcStr = funcStr;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	@Transient
	public Map<String, String> getFuncParam() {
		return funcParam;
	}

	public void setFuncParam(Map<String, String> funcParam) {
		this.funcParam = funcParam;
	}

	public String getFuncBody() {
		return funcBody;
	}

	public void setFuncBody(String funcBody) {
		this.funcBody = funcBody;
	}

	public String getFuncRetn() {
		return funcRetn;
	}

	public void setFuncRetn(String funcRetn) {
		this.funcRetn = funcRetn;
	}

}
