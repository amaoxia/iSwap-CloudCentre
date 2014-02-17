/**  
 * @公司名称：北京光码软件有限公司
 * @项目名称：iSwapV6.0云计算数据交换平台
 * @文件名称：Rules.java
 * @子模块名：iSwapQA 数据审计
 * @模块名称：
 * @功能作用：规则文件 实体类
 * @文件作者： Tony Wong
 * @创建时间：2011-6-27 上午11:12:48
 * @版本编号：v1.0  
 * @最后修改：(修改人) 2011-6-27 上午11:12:48
 */
package com.ligitalsoft.model.iswapqa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * @author Tony
 */
@Entity
@Table(name = "ISWAPQA_AUDITRESULTS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuditResults extends LongIdObject {

    private static final long serialVersionUID = 1L;

    /**
     * 指标名称
     */
    private String            targetName;

    /**
     * 所属应用
     */
    private String            beApply;

    /**
     * 所属流程名称
     */
    private String  flowName;
    /**
     * 所属流程ID
     */
    private String  flowId;

    /**
     * 创建人
     */
    private String            auditResult;

    /**
     * 审计时间
     */
    private Date           auditTime;

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getBeApply() {
		return beApply;
	}

	public void setBeApply(String beApply) {
		this.beApply = beApply;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}


}
