package com.ligitalsoft.cloudstorage.service.impl;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.cloudstorage.dao.MongoDBDataSourceDao;
import com.ligitalsoft.cloudstorage.service.IMongoDBDataSourceService;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;
import com.ligitalsoft.model.cloudstorage.MongoDBDataSource;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * 
 * @Company 中海纪元
 * @author  HuJun
 * @mail    moujunhu@163.com
 * @since   2012-9-4 下午4:35:24
 * @name    com.ligitalsoft.cloudstorage.service.impl.MongoDBDataSourceServiceImpl.java
 * @version iSwap V6.1 数据交换平台
 * @Team    研发中心
 */
@Service("mongoDBDataSourceService")
public class MongoDBDataSourceServiceImpl extends
		BaseSericesImpl<MongoDBDataSource> implements IMongoDBDataSourceService {

	@Autowired
	private MongoDBDataSourceDao mongoDBDataSourceDao;

	@Override
	public EntityHibernateDao<MongoDBDataSource> getEntityDao() {
		return mongoDBDataSourceDao;
	}
	/**
	 * 测试数据源
	 * 
	 * @param id
	 * @return
	 */
	public String testDataSource(Long id) throws ServiceException {
		String falg = "true";
		MongoDBDataSource dataSource = findById(id);
		if(dataSource==null){
			falg = "false";
			return falg;
		}
		try {
			Mongo db = new Mongo(dataSource.getIp(), new Integer(dataSource.getPort()));
			if(!StringUtils.isBlank(dataSource.getDbName())){
				DB mydb = db.getDB(dataSource.getDbName());
				if(!StringUtils.isBlank(dataSource.getUserName())&&!StringUtils.isBlank(dataSource.getPassWord())){
					if(!mydb.authenticate(dataSource.getUserName(), dataSource.getPassWord().toCharArray())){
						falg = "false";
					}
				}else{
					mydb.getCollectionNames();
				}
			}else{
				db.getVersion();
			}

			db.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return falg;
	}
	
	public CouldDataSource findDatasourceIsExit(String ip,String port,String dbName){
		return  mongoDBDataSourceDao.findDatasourceIsExit(ip, port, dbName);
	}
}
