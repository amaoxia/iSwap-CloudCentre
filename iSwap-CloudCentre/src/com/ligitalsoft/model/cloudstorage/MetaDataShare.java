/*
 * @(#)MetaDataShare.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.model.cloudstorage;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.appitemmgr.AppMsg;

/**
 * 元数据共享表
 * @author zhangx
 * @since Aug 10, 2011 10:01:39 AM
 * @name com.ligitalsoft.model.cloudstorage.MetaDataShare.java
 * @version 1.0
 */
//@Entity
//@Table(name = "CLOUDSTOR_METADATASHARE")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetaDataShare extends LongIdObject {

    /**
     * 
     */
    private static final long serialVersionUID = 2813852739942543034L;
    private MetaData metaData;
    private AppMsg appMsg;
}
