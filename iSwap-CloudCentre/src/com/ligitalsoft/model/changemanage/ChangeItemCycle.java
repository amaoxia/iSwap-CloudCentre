/*
 * @(#)ChangItemCycle.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.changemanage;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 指标交换规则
 * @author zhangx
 * @since Aug 16, 2011 11:27:35 AM
 * @name com.ligitalsoft.model.changemanage.ChangItemCycle.java
 * @version 1.0
 */
@Entity
@Table(name = "CHANGE_ITEM_CYCLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChangeItemCycle extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = -6426252561019613868L;

    /**
     * 交换周期 0-周 1--月 2--季 3 年
     */
    private String exchangeCycleValue;
    /**
     * 规定交换日期规则
     */
    private String exchangeDateRule;
    /**
     * 是否采用系统默认规则 0---false 1 ---- true
     */
    private String useDefaultRule;
    /**
     * 绿灯规则
     */
    private String ruleGreenNotify;
    /**
     * 黄灯规则
     */
    private String ruleYellowNotify;
    /**
     * 红灯规则
     */
    private String ruleRedNotify;
    /**
     * 是否全量发送 0--false 1-true
     */
    private String isAllSend;

    public String makeExchangeCycleDate() {
        StringBuffer buffer = new StringBuffer();
        String[] args = exchangeDateRule.split(",");
        if (!StringUtils.isBlank(exchangeCycleValue) && !StringUtils.isBlank(exchangeDateRule)) {
            if (exchangeCycleValue.equals("0")) {
                buffer.append("本周数据在");
                if (args[0].equals("0")) {
                    buffer.append("本周");
                } else {
                    buffer.append("下周");
                }
                buffer.append("周" + args[1] + "日执行");
            } else if (exchangeCycleValue.equals("1")) {
                buffer.append("本月数据在");
                if (args[0].equals("0")) {
                    buffer.append("本月");
                } else {
                    buffer.append("下月");
                }
                buffer.append("第" + args[1] + "日执行");
            } else if (exchangeCycleValue.equals("2")) {
                buffer.append("本季数据在");
                if (args[0].equals("0")) {
                    buffer.append("本季");
                } else {
                    buffer.append("下季");
                }
                if (args[1].equals("1")) {
                    buffer.append("第一个月");
                } else if (args[1].equals("2")) {
                    buffer.append("第二个月");
                } else {
                    buffer.append("第三个月");
                }
                buffer.append(args[2] + "日执行");
            } else if (exchangeCycleValue.equals("3")) {
                buffer.append("本年数据在");
                if (args[0].equals("0")) {
                    buffer.append("本年");
                } else {
                    buffer.append("下年");
                }
                buffer.append("第" + args[1] + "月" + args[2] + "日执行");
            }
        }
        return buffer.toString();
    }

    public String getExchangeCycleValue() {
        return exchangeCycleValue;
    }

    public void setExchangeCycleValue(String exchangeCycleValue) {
        this.exchangeCycleValue = exchangeCycleValue;
    }

    public String getExchangeDateRule() {
        return exchangeDateRule;
    }

    public void setExchangeDateRule(String exchangeDateRule) {
        this.exchangeDateRule = exchangeDateRule;
    }

    public String getUseDefaultRule() {
        return useDefaultRule;
    }

    public void setUseDefaultRule(String useDefaultRule) {
        this.useDefaultRule = useDefaultRule;
    }

    public String getRuleGreenNotify() {
        return ruleGreenNotify;
    }

    public void setRuleGreenNotify(String ruleGreenNotify) {
        this.ruleGreenNotify = ruleGreenNotify;
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

    public String getIsAllSend() {
        return isAllSend;
    }

    public void setIsAllSend(String isAllSend) {
        this.isAllSend = isAllSend;
    }
}
