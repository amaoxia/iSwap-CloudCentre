package com.ligitalsoft.model.changemanage;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.common.framework.domain.StringIdObject;
/**
 * 数据发送记录表
 * @author liuxd
 * @mail liuxiaodong_315@163.com
 * @since 2011-04-16
 * @name com.ligitalsoft.model.SendTask.java
 * @version 1.0
 */
@Entity
@Table(name = "data_send")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataSend extends StringIdObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045916657945244485L;
	/**
	 * 数据时间段
	 */
	private String dataTimeZone;
	/**
	 * 发送数据量
	 */
	private Long sendCount;
	/**
	 * 实际发送日期
	 */
	private Date realSendDate;
	/**
	 * 开始执行时间
	 */
	private Date startExecDate;
	/**
	 * 结束执行时间
	 */
	private Date endExecDate;
	/**
	 * 发送任务
	 */
	private SendTask sendTask;
	public String getDataTimeZone() {
		return dataTimeZone;
	}
	public void setDataTimeZone(String dataTimeZone) {
		this.dataTimeZone = dataTimeZone;
	}
	public Long getSendCount() {
		return sendCount;
	}
	public void setSendCount(Long sendCount) {
		this.sendCount = sendCount;
	}
	public Date getRealSendDate() {
		return realSendDate;
	}
	public void setRealSendDate(Date realSendDate) {
		this.realSendDate = realSendDate;
	}
	public Date getStartExecDate() {
		return startExecDate;
	}
	public void setStartExecDate(Date startExecDate) {
		this.startExecDate = startExecDate;
	}
	public Date getEndExecDate() {
		return endExecDate;
	}
	public void setEndExecDate(Date endExecDate) {
		this.endExecDate = endExecDate;
	}
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "SEND_TASK_ID")
	public SendTask getSendTask() {
		return sendTask;
	}
	public void setSendTask(SendTask sendTask) {
		this.sendTask = sendTask;
	}
	
}
