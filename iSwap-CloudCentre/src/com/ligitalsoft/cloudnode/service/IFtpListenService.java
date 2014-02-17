package com.ligitalsoft.cloudnode.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.cloudnode.FtpListen;
/**
 * 远程目录监听
 * 
 * @Company 北京光码软件有限公司
 *@author fangbin
 *@version iSwap V6.0 数据交换平台
 *@date 2011-06-14
 *@Team 研发中心
 */
public interface IFtpListenService extends IBaseServices<FtpListen> {
	/**
	 * 状态改变时修改缓存中的部署的FTP信息
	 * @author fangbin
	 * @param entity
	 */
	public void updateStatus(FtpListen entity) ;
	
	/**
     * 根据部门信息查询数据源
     * @param status
     * @param deptId
     * @return
     */
    public List<FtpListen> findFtpDataSourcesByDept(String status,Long deptId);
}
