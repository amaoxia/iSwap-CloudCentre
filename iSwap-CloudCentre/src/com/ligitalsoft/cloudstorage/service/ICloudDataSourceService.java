package com.ligitalsoft.cloudstorage.service;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;

/**
 * 中心数据源管理接口
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-10-17下午05:50:37
 *@Team 研发中心
 */
public interface ICloudDataSourceService  extends IBaseServices<CouldDataSource>{
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
