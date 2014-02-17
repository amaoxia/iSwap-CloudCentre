package com.ligitalsoft.cloudstorage.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.DBConntTool;
import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudstorage.dao.CouldDataSourceDao;
import com.ligitalsoft.cloudstorage.service.ICloudDataSourceService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;

@Service("couldDataSourceService")
public class CouldDataSourceServiceImpl extends
		BaseSericesImpl<CouldDataSource> implements ICloudDataSourceService {

	@Autowired
	private CouldDataSourceDao couldDataSourceDao;

	@Override
	public EntityHibernateDao<CouldDataSource> getEntityDao() {
		return couldDataSourceDao;
	}
	/**
	 * 测试数据源
	 * 
	 * @param id
	 * @return
	 */
	public String testDataSource(Long id) throws ServiceException {
		String falg = "true";
		CouldDataSource dataSource = findById(id);
		if(dataSource==null){
			falg="false";
			return falg;
		}
		DBConntTool tool  = DBConntTool.bcpoolInit();
		Connection connection=tool.getConn(dataSource.getDriveName(),dataSource.getAddress(),dataSource.getUserName(),dataSource.getPassWord());
		if(connection == null){
			falg = "false";
		}
		tool.shutdownConnPool();
		return falg;
	}
	
	protected void onInsertAfter(CouldDataSource entity) {
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		String keyName = entity.getSourceName() + "#" + entity.getSourceCode()+"_center";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("key", keyName);
		map.put("driveName", entity.getDriveName());
		map.put("address", entity.getAddress());
		map.put("userName", entity.getUserName());
		map.put("passWord", entity.getPassWord());
		map.put("type", "center");
		map.put("deptId","000");
		map.put("deptName","中心共享库"+entity.getId());
		tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
		tool.closeFileDB();
	}
	
	protected void onDeleteAfter(CouldDataSource  entity) {
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		String keyName = entity.getSourceName() + "#" + entity.getSourceCode()+"_center";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("key", keyName);
		tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
		tool.closeFileDB();
    }
	
	protected void onUpdateAfter(CouldDataSource entity) {
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		String keyName = entity.getSourceName() + "#" + entity.getSourceCode()+"_center";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("key", keyName);
		tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
		
		map.put("driveName", entity.getDriveName());
		map.put("address", entity.getAddress());
		map.put("userName", entity.getUserName());
		map.put("passWord", entity.getPassWord());
		map.put("type", "center");
		map.put("deptId","000");
		map.put("deptName","中心共享库"+entity.getId());
		
		tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
		tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
		tool.closeFileDB();
    }
	
	public CouldDataSource findDatasourceIsExit(String ip,String port,String dbName){
		return  couldDataSourceDao.findDatasourceIsExit(ip, port, dbName);
	}
}
