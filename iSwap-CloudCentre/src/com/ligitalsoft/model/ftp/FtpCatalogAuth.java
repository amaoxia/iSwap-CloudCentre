/*
 * @(#)FtpUser.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.ftp;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * FTR目录表
 * @author daic
 * @mail daicheng0518@163.com
 * @since May 23, 2011 10:38:42 AM
 * @name com.ligitalsoft.model.ftp.FtpCatalogAuth.java
 * @version 1.0
 */
@Entity
@Table(name = "FTP_CATALOG_AUTH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FtpCatalogAuth extends LongIdObject {

    private static final long serialVersionUID = -8221704646957154237L;
    /**
     * 路径名称
     */
    private String path;
    /**
     * 文件读取
     */
    private boolean fileread = true;
    /**
     * 文件写入
     */
    private boolean filewrite = true;
    /**
     * 文件追加
     */
    private boolean fileappend = true;
    /**
     * 文件删除
     */
    private boolean filedelete = true;
    /**
     * 目录列表
     */
    private boolean catalist = true;
    /**
     * 目录创建
     */
    private boolean catacreate = true;
    /**
     * 目录删除
     */
    private boolean catadelete = true;
    /**
     * 子目录继承
     */
    private boolean childcataextend = true;
    /**
     * 用户ID
     */
    private Long userid;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isFileread() {
		return fileread;
	}
	public void setFileread(boolean fileread) {
		this.fileread = fileread;
	}
	public boolean isFilewrite() {
		return filewrite;
	}
	public void setFilewrite(boolean filewrite) {
		this.filewrite = filewrite;
	}
	public boolean isFileappend() {
		return fileappend;
	}
	public void setFileappend(boolean fileappend) {
		this.fileappend = fileappend;
	}
	public boolean isFiledelete() {
		return filedelete;
	}
	public void setFiledelete(boolean filedelete) {
		this.filedelete = filedelete;
	}
	public boolean isCatalist() {
		return catalist;
	}
	public void setCatalist(boolean catalist) {
		this.catalist = catalist;
	}
	public boolean isCatacreate() {
		return catacreate;
	}
	public void setCatacreate(boolean catacreate) {
		this.catacreate = catacreate;
	}
	public boolean isCatadelete() {
		return catadelete;
	}
	public void setCatadelete(boolean catadelete) {
		this.catadelete = catadelete;
	}
	public boolean isChildcataextend() {
		return childcataextend;
	}
	public void setChildcataextend(boolean childcataextend) {
		this.childcataextend = childcataextend;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
 

  
}
