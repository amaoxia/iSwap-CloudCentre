/*
 * @(#)SysPermission.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.common.framework.domain.LongIdObject;

/**
 * 权限信息
 * @author zhangx
 * @since May 13, 2011 3:58:50 PM
 * @name com.ligitalsoft.model.system.SysPermission.java
 * @version 1.0
 */
@Entity
@Table(name = "SYS_PERMISSION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysPermission extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = -6282920218186450766L;
    /**
     * 菜单名称
     */
    private String menuName;
//    /**
//     * 上级菜单ID
//     */
//    private Long fatherid;
    /**
     * 菜单地址URL
     */
    private String url;
    /**
     * 菜单图标
     */
    private String menuIcon;
    /**
     * 菜单层次
     */
    private Integer menuLevel;
    /**
     * 菜单顺序
     */
    private Integer levlel;
    /**
     * 子系统名称
     */
    private String childSysName;

    private String defaults;
    /**
     * 菜单子项
     */
    private List<SysPermission> childrenPermission = new ArrayList<SysPermission>();

    /**
     * 菜单父类
     */
    private SysPermission permission;
    
    /** 系统代码 **/
    private String sysCode;
    
    /** 是否可用 **/
    private Integer enabled;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

//    public Long getFatherid() {
//        return fatherid;
//    }
//
//    public void setFatherid(Long fatherid) {
//        this.fatherid = fatherid;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getLevlel() {
        return levlel;
    }

    public void setLevlel(Integer levlel) {
        this.levlel = levlel;
    }

    public String getChildSysName() {
        return childSysName;
    }

    public void setChildSysName(String childSysName) {
        this.childSysName = childSysName;
    }

    @OneToMany(mappedBy = "permission", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public List<SysPermission> getChildrenPermission() {
        return childrenPermission;
    }

    public void setChildrenPermission(List<SysPermission> childrenPermission) {
        this.childrenPermission = childrenPermission;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fatherid")
    @Fetch(FetchMode.SELECT)
    public SysPermission getPermission() {
        return permission;
    }

    public void setPermission(SysPermission permission) {
        this.permission = permission;
    }

    @Column(name="default")
    public String getDefaults() {
        return defaults;
    }

    
    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
    
}
