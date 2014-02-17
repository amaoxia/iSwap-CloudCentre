/*
 * @(#)FtpUser.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.ftp;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * FTR用户表
 * @author lifh
 * @mail wslfh2005@163.com
 * @since May 23, 2011 10:38:42 AM
 * @name com.ligitalsoft.model.ftp.FtpUser.java
 * @version 1.0
 */
@Entity
@Table(name = "FTP_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FtpUser extends LongIdObject {

    private static final long serialVersionUID = -8221704646957154237L;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 用户名
     */
    private String userid;
    /**
     * 密码
     */
    private String userpassword;
    /**
     * 主目录
     */
    private String homedirectory;
    /**
     * 用户是否可用
     */
    private boolean enableflag = true;
    /**
     * 是否可写
     */
    private boolean writepermission = true;
    /**
     * 最大空闲时间
     */
    private int idletime;
    /**
     * 上传的速度
     */
    private int uploadrate;
    /**
     * 下载速度
     */
    private int downloadrate;
    /**
     * 最大登陆次数
     */
    private int maxloginnumber;
    /**
     * 同一IP最多登陆
     */
    private int maxloginperip;

    /**
     * 所属机构
     */
    private long deptid;

    /**
     * 注释
     */
    private String annotation;

    /**
     * 失效日期
     */
    private Timestamp failureDate;

    /**
     * 是否允许用户修改密码
     */
    private boolean enableeditpassword;
    /**
     * 任务超时
     */
    private int tasktimeout;

    /**
     * 上传下载限制启用
     */
    private boolean enabletransmissionlimit;
    /**
     * 每个任务文件数
     */
    private int everytaskfiles;
    /**
     * 每个任务大小
     */
    private int everytasksize;
    /**
     * 所有任务文件
     */
    private int alltaskfiles;
    /**
     * 所有任务字节
     */
    private int alltasksize;
    /**
     * 启用磁盘配额
     */
    private boolean enablediskquota;
    /**
     * 最小磁盘配额(MB)
     */
    private double mindiskquota;
    /**
     * 最大磁盘配额(MB)
     */
    private double maxdiskquota;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getHomedirectory() {
        return homedirectory;
    }

    public void setHomedirectory(String homedirectory) {
        this.homedirectory = homedirectory;
    }

    public boolean isEnableflag() {
        return enableflag;
    }

    public void setEnableflag(boolean enableflag) {
        this.enableflag = enableflag;
    }

    public boolean isWritepermission() {
        return writepermission;
    }

    public void setWritepermission(boolean writepermission) {
        this.writepermission = writepermission;
    }

    public int getIdletime() {
        return idletime;
    }

    public void setIdletime(int idletime) {
        this.idletime = idletime;
    }

    public int getUploadrate() {
        return uploadrate;
    }

    public void setUploadrate(int uploadrate) {
        this.uploadrate = uploadrate;
    }

    public int getDownloadrate() {
        return downloadrate;
    }

    public void setDownloadrate(int downloadrate) {
        this.downloadrate = downloadrate;
    }

    public int getMaxloginnumber() {
        return maxloginnumber;
    }

    public void setMaxloginnumber(int maxloginnumber) {
        this.maxloginnumber = maxloginnumber;
    }

    public int getMaxloginperip() {
        return maxloginperip;
    }

    public void setMaxloginperip(int maxloginperip) {
        this.maxloginperip = maxloginperip;
    }

    public long getDeptid() {
        return deptid;
    }

    public void setDeptid(long deptid) {
        this.deptid = deptid;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Timestamp getFailureDate() {
        return failureDate;
    }

    public void setFailureDate(Timestamp failureDate) {
        this.failureDate = failureDate;
    }

    public boolean isEnableeditpassword() {
        return enableeditpassword;
    }

    public void setEnableeditpassword(boolean enableeditpassword) {
        this.enableeditpassword = enableeditpassword;
    }

    public int getTasktimeout() {
        return tasktimeout;
    }

    public void setTasktimeout(int tasktimeout) {
        this.tasktimeout = tasktimeout;
    }

    public boolean isEnabletransmissionlimit() {
        return enabletransmissionlimit;
    }

    public void setEnabletransmissionlimit(boolean enabletransmissionlimit) {
        this.enabletransmissionlimit = enabletransmissionlimit;
    }

    public int getEverytaskfiles() {
        return everytaskfiles;
    }

    public void setEverytaskfiles(int everytaskfiles) {
        this.everytaskfiles = everytaskfiles;
    }

    public int getEverytasksize() {
        return everytasksize;
    }

    public void setEverytasksize(int everytasksize) {
        this.everytasksize = everytasksize;
    }

    public int getAlltaskfiles() {
        return alltaskfiles;
    }

    public void setAlltaskfiles(int alltaskfiles) {
        this.alltaskfiles = alltaskfiles;
    }

    public int getAlltasksize() {
        return alltasksize;
    }

    public void setAlltasksize(int alltasksize) {
        this.alltasksize = alltasksize;
    }

    public boolean isEnablediskquota() {
        return enablediskquota;
    }

    public void setEnablediskquota(boolean enablediskquota) {
        this.enablediskquota = enablediskquota;
    }

    public double getMindiskquota() {
        return mindiskquota;
    }

    public void setMindiskquota(double mindiskquota) {
        this.mindiskquota = mindiskquota;
    }

    public double getMaxdiskquota() {
        return maxdiskquota;
    }

    public void setMaxdiskquota(double maxdiskquota) {
        this.maxdiskquota = maxdiskquota;
    }

}
