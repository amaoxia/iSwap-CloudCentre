package com.ligitalsoft.cloudnode.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
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
@Repository
public class FtpListenDao extends EntityHibernateDao<FtpListen> {
	
	@SuppressWarnings("unchecked")
	public List<FtpListen> findFtpDataSourcesByDept(String status,Long deptId){
		String hql = "from FtpListen ds where ds.status=? and ds.sysDept.id=?";
		Object[] obj = { status, deptId };
		List<FtpListen> datas = powerHibernateDao.findListByHql(hql, obj);
		return datas;
	}

}
