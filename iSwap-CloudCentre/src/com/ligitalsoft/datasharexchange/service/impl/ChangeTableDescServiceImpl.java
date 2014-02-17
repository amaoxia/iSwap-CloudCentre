/*
 * @(#)ChangeTableDescServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.DBConntTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDao;
import com.ligitalsoft.datasharexchange.dao.ChangeTableDescDao;
import com.ligitalsoft.datasharexchange.service.IChangeTableDescService;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;
import com.ligitalsoft.model.cloudnode.DataSource;

/**
 * 交换指标_表结构 实现类
 * @author zhangx
 * @since Jun 14, 2011 5:34:20 PM
 * @name com.ligitalsoft.datasharexchange.service.impl.ChangeTableDescServiceImpl.java
 * @version 1.0
 */
@Service("changeTableDescService")
public class ChangeTableDescServiceImpl extends BaseSericesImpl<ChangeTableDesc> implements IChangeTableDescService {

    private ChangeTableDescDao changeTableDescDao;
    private ChangeItemDao changeItemDao;

    @Override
    public void createTableDescData(Long itemId) throws SQLException {
        List<ChangeTableDesc> datas = new ArrayList<ChangeTableDesc>();
        ChangeItem changeItem = changeItemDao.findById(itemId);
        DBConntTool dbTool=null;
    	dbTool= DBConntTool.bcpoolInit();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
        
	        if (changeItem != null) {// 指标不为空
	            DataSource dataSource = changeItem.getDatSource();
	            if (dataSource != null) {// 数据源不为空
                    if (!StringUtils.isBlank(dataSource.getDriveName())
                                    && !StringUtils.isBlank(dataSource.getAddress())
                                    && !StringUtils.isBlank(dataSource.getUserName())
                                    && !StringUtils.isBlank(dataSource.getPassWord())) {
                        conn = dbTool.getConn(dataSource.getDriveName(), dataSource.getAddress(),dataSource.getUserName(), dataSource.getPassWord());
                        if (conn != null) {
                            if (!StringUtils.isBlank(changeItem.getTableName())) {
                            	DatabaseMetaData databaseMetaData = conn.getMetaData();  
                            	ResultSet resultSet = databaseMetaData.getColumns(null,null,changeItem.getTableName().toUpperCase(),"%");     
                            	while(resultSet.next()){     
                                     ChangeTableDesc tableDesc = new ChangeTableDesc();
                                     tableDesc.setDataType(resultSet.getString("TYPE_NAME"));
                                     tableDesc.setFiledcode(resultSet.getString("COLUMN_NAME"));
                                     tableDesc.setName(resultSet.getString("REMARKS"));
                                     tableDesc.setFiledLength(resultSet.getInt("COLUMN_SIZE"));
                                     tableDesc.setIsNull(resultSet.getString("NULLABLE"));
                                     tableDesc.setIsPk(isPK(conn, changeItem.getTableName().toUpperCase(), resultSet.getString("COLUMN_NAME")));
                                     tableDesc.setChangeItem(changeItem);
                                     datas.add(tableDesc);
                                }     
                               /* statement = conn.prepareStatement("select * from " + changeItem.getTableName());
                                rs = statement.executeQuery();
                                ResultSetMetaData data = rs.getMetaData();// 数据结构集合
                                int length = data.getColumnCount();
                                for (int i = 1; i <= length; i++) {
                                    ChangeTableDesc tableDesc = new ChangeTableDesc();
                                    tableDesc.setDataType(data.getColumnTypeName(i) + "");
                                    tableDesc.setFiledcode(data.getColumnName(i));
                                    tableDesc.setName(data.getColumnLabel(i));
                                    tableDesc.setFiledLength(data.getColumnDisplaySize(i));
                                    tableDesc.setIsNull(data.isNullable(i) + "");
                                    tableDesc.setIsPk(isPK(conn, changeItem.getTableName().toUpperCase(), data.getColumnName(i)));
                                    tableDesc.setChangeItem(changeItem);
                                    datas.add(tableDesc);
                                }*/
                            }
                        }
                    }
	            }
	        }
	        List<ChangeTableDesc> list = changeTableDescDao.findListByItemId(itemId);
	        List<ChangeTableDesc> desc = excludeProperty(list, datas);
	        for (ChangeTableDesc changeTableDesc : desc) {
	            changeItemDao.saveOrUpdate(changeTableDesc);
	            changeItemDao.getSession().flush();
	        }
        } catch (Exception e) {
            logger.error("数据库连接失败!", e);
            throw new SQLException();
        }finally{
        	dbTool.closeConn(conn);
        	dbTool.shutdownConnPool();
        }
    }
    
    /**
     * 判断是否主键
     * @param conn
     * @param tableName
     * @param fileName
     * @return
     * @throws Exception
     * @author zhangx
     */
    private String isPK(Connection conn, String tableName, String fileName) throws Exception {
        DatabaseMetaData dsData = conn.getMetaData();
        ResultSet rss = dsData.getPrimaryKeys(null, null, tableName.toUpperCase());
        while (rss.next()) {
            if (fileName.equals(rss.getString("column_name"))) {
                return "1";
            }
        }
        if (rss != null) {
            rss.close();
        }
        return "0";
    }
    @Override
    public void updateStatus(Long[] ids, String status) {
        changeTableDescDao.updateStatus(ids, status);
    }

    /**
     * 查找指标项目
     * @param source
     * @param now
     * @return
     * @author zhangx
     */
    private List<ChangeTableDesc> excludeProperty(List<ChangeTableDesc> source, List<ChangeTableDesc> now) {
        boolean falg = false;
        List<ChangeTableDesc> datas = new ArrayList<ChangeTableDesc>();
        if (source == null || source.size() ==0) {
            return now;
        }
        if(now==null||source.size()==0){
            return datas;
        }
        for (ChangeTableDesc desc : now) {
            for (ChangeTableDesc changeTableDesc : source) {
                if (!StringUtils.isBlank(changeTableDesc.getFiledcode()) && !StringUtils.isBlank(desc.getFiledcode())) {
                    if (changeTableDesc.getFiledcode().toString().equals(desc.getFiledcode().toString())) {
                        falg = true;
                        break;
                    }
                }
            }
            if (!falg) {
                datas.add(desc);
            }
            falg=false;
        }
        return datas;
    }

    @Override
    public EntityHibernateDao<ChangeTableDesc> getEntityDao() {
        return changeTableDescDao;
    }

    @Autowired
    public void setChangeItemDao(ChangeItemDao changeItemDao) {
        this.changeItemDao = changeItemDao;
    }

    @Autowired
    public void setChangeTableDescDao(ChangeTableDescDao changeTableDescDao) {
        this.changeTableDescDao = changeTableDescDao;
    }
    public static void main(String[] args) {
        ChangeTableDesc changeTableDesc = new ChangeTableDesc();
        changeTableDesc.setId(1L);
        changeTableDesc.setDataType("type");
        changeTableDesc.setFiledcode("NAME");
        ChangeTableDesc changeTableDesc2 = new ChangeTableDesc();
        changeTableDesc2.setId(2L);
        changeTableDesc2.setDataType("TYPE");
        changeTableDesc.setFiledcode("name");
        ChangeTableDesc changeTableDesc3 = new ChangeTableDesc();
        changeTableDesc2.setId(2L);
        changeTableDesc2.setDataType("TYPE");
        changeTableDesc.setFiledcode("name");
        List<ChangeTableDesc> one = new ArrayList<ChangeTableDesc>();
        List<ChangeTableDesc> two = new ArrayList<ChangeTableDesc>();
        two.add(changeTableDesc);
        two.add(changeTableDesc2);
        one.add(changeTableDesc3);
        two.removeAll(one);
        for (ChangeTableDesc change : two) {
            System.out.println(change.getId());
        }
        ;
    }

	@Override
	public List<ChangeTableDesc> getTableDescByitemId(Long itemId) throws SQLException {
		return changeTableDescDao.findListByItemId(itemId);		
	}
}
