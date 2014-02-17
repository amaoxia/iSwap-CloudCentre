package com.ligitalsoft.model.cloudstorage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * MongoDB数据源配置
 * @Company 中海纪元
 * @author  HuJun
 * @mail    moujunhu@163.com
 * @since   2012-8-30 下午4:29:04
 * @name    com.ligitalsoft.model.cloudstorage.MongoDBDataSource.java
 * @version iSwap V6.1 数据交换平台
 * @Team    研发中心
 */
@Entity  
@DiscriminatorValue("mongodb")
public class MongoDBDataSource extends AbstractDataSource {

	private static final long serialVersionUID = 1L;
}
