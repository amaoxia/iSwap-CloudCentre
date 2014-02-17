package com.ligitalsoft.cloudstorage.service;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;
import com.ligitalsoft.model.cloudstorage.MongoDBDataSource;

/**
 * mongodb服务层接口
 * @Company 中海纪元
 * @author  HuJun
 * @mail    moujunhu@163.com
 * @since   2012-8-30 下午4:53:35
 * @name    com.ligitalsoft.cloudstorage.service.IMongoDBSourceService.java
 * @version iSwap V6.1 数据交换平台
 * @Team    研发中心
 */
public interface IMongoDBDataSourceService  extends IBaseServices<MongoDBDataSource>{
	 /**
     * 测试数据源
     * @param id
     * @return
     */
    public String testDataSource(Long id) throws ServiceException;
    
    
    /**
     * 查询当前数据源是否存在
     * @param ip
     * @param port
     * @param dbName
     * @return
     */
    public CouldDataSource findDatasourceIsExit(String ip,String port,String dbName);
}
