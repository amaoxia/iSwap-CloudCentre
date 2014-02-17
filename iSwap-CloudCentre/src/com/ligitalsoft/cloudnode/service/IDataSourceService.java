package com.ligitalsoft.cloudnode.service;

import java.util.List;

import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudnode.DataSource;
/**
 * 数据源管理
 *@Company 北京光码软件有限公司
 *@author fangbin
 *@version  iSwap V6.0 数据交换平台  
 *@date  2011-06-13
 *@Team 研发中心
 */
public interface IDataSourceService extends IBaseServices<DataSource> {
    /**
     *  创建数据源树
     * @return
     * @author zhangx
     */
    public String dataSourceTree();
    
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
    public DataSource findDatasourceIsExit(String ip,String port,String dbName);
    
    /**
     * 根据部门信息查询数据源
     * @param status
     * @param deptId
     * @return
     */
    public List<DataSource> findDataSourcesByDept(String status,Long deptId);
    /**
     * 修改缓存中的数据源信息
     * @param entity 
     * @author  fangbin
     * @date 2011-9-4 下午05:32:50
     */
    public void updateStatus(DataSource entity) ;
    
}
