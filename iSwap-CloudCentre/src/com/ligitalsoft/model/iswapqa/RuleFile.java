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
import java.util.List;

import javax.persistence.Column;
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
@Table(name = "ISWAPQA_RULES")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RuleFile extends LongIdObject {

    private static final long serialVersionUID = 1L;

    /**
     * 规则文件名称
     */
    private String            fileName;

    /**
     * 包名
     */
    private String            packageName;

    /**
     * 函数列表
     */
    private List<Function>    funcList;

    /**
     * 引入对象
     */
    private String            imports;

    /**
     * 全局变量定义
     */
    private String            globals;

    /**
     * 查询条件定义
     */
    private String            queries;

    /**
     * 规则列表
     */
    private List<Rule>        ruleList;
    /**
     * 规则文件字符串
     */
    private String            rulesStr;

    /**
     * 所属应用
     */
    private String            applyTo;

    /**
     * 创建人
     */
    private String            creater;

    /**
     * 创建时间
     */
    private Date              creationTime     = new Date();

    public String getApplyTo() { 
        return applyTo;
    }

    public void setApplyTo(String applyTo) {
        this.applyTo = applyTo;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Column(length = 2000)
    public String getRulesStr() {
        return rulesStr;
    }

    public void setRulesStr(String rulesStr) {
        this.rulesStr = rulesStr;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(length = 2000)
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Transient
    public List<Function> getFuncList() {
        return funcList;
    }

    public void setFuncList(List<Function> funcList) {
        this.funcList = funcList;
    }

    @Column(length = 2000)
    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    @Column(length = 2000)
    public String getGlobals() {
        return globals;
    }

    public void setGlobals(String globals) {
        this.globals = globals;
    }

    @Column(length = 2000)
    public String getQueries() {
        return queries;
    }

    public void setQueries(String queries) {
        this.queries = queries;
    }

    @Transient
    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }
}
