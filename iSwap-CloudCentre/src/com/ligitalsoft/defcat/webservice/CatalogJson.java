/*
 * @(#)CatalogJson.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.defcat.webservice;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since Apr 19, 2011 2:59:23 PM
 * @name com.ligitalsoft.defcat.webservice.CatalogJson.java
 * @version 1.0
 */

public class CatalogJson {

    private Long id;
    private String catalogName;

    public CatalogJson() {
        super();
    }

    public CatalogJson(Long id, String catalogName) {
        super();
        this.id = id;
        this.catalogName = catalogName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

}
