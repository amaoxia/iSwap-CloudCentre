

package com.ligitalsoft.model.changemanage;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 交换系统配置表
 * @author daic
 * @since 2011-08-16 16:16:38
 * @name com.ligitalsoft.model.changemanage.ExchangeSystemConfig.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_SystemConfig")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExchangeSystemConfig extends LongIdObject {

	private static final long serialVersionUID = -3899748623842439512L;
	private String mailAddress;
	private String mailAccount;
	private String mailPwd;
	private String mailSmtp;
	private String smsUrl;
	private String smsAccount;
	private String smsPwd;
	private String ruleNotify;
	private String ruleYellowNotify;
	private String ruleRedNotify;
	
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getMailAccount() {
		return mailAccount;
	}
	public void setMailAccount(String mailAccount) {
		this.mailAccount = mailAccount;
	}
	public String getMailPwd() {
		return mailPwd;
	}
	public void setMailPwd(String mailPwd) {
		this.mailPwd = mailPwd;
	}
	public String getMailSmtp() {
		return mailSmtp;
	}
	public void setMailSmtp(String mailSmtp) {
		this.mailSmtp = mailSmtp;
	}
	public String getSmsUrl() {
		return smsUrl;
	}
	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}
	public String getSmsAccount() {
		return smsAccount;
	}
	public void setSmsAccount(String smsAccount) {
		this.smsAccount = smsAccount;
	}
	public String getSmsPwd() {
		return smsPwd;
	}
	public void setSmsPwd(String smsPwd) {
		this.smsPwd = smsPwd;
	}
	public String getRuleNotify() {
		return ruleNotify;
	}
	public void setRuleNotify(String ruleNotify) {
		this.ruleNotify = ruleNotify;
	}
	public String getRuleYellowNotify() {
		return ruleYellowNotify;
	}
	public void setRuleYellowNotify(String ruleYellowNotify) {
		this.ruleYellowNotify = ruleYellowNotify;
	}
	public String getRuleRedNotify() {
		return ruleRedNotify;
	}
	public void setRuleRedNotify(String ruleRedNotify) {
		this.ruleRedNotify = ruleRedNotify;
	}


	
}
