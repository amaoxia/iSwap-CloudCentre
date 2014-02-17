/*
 * @(#)TableInfoServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.DBConntTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.cloudstorage.dao.MetaDataDao;
import com.ligitalsoft.cloudstorage.dao.MetaDataTableAuthDao;
import com.ligitalsoft.cloudstorage.dao.TableInfoDao;
import com.ligitalsoft.cloudstorage.service.ITableInfoService;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.cloudstorage.MetaDataTableAuth;
import com.ligitalsoft.model.cloudstorage.TableInfo;
import com.ligitalsoft.util.Node;

/**
 * 元数据_指标项的信息_SERIVCE
 * @author zhangx
 * @since Jun 16, 2011 8:44:43 PM
 * @name com.ligitalsoft.cloudstorage.service.impl.TableInfoServiceImpl.java
 * @version 1.0
 */
@Service
public class TableInfoServiceImpl extends BaseSericesImpl<TableInfo> implements ITableInfoService {

    private TableInfoDao tableInfoDao;
    private MetaDataDao metaDataDao;
    private MetaDataTableAuthDao metaDataTableAuthDao;

    public void createTableDescData(Long itemId) throws SQLException {
        List<TableInfo> datas = new ArrayList<TableInfo>();
        DBConntTool dbTool=null;
      	dbTool= DBConntTool.bcpoolInit();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
      
        	MetaData metaData = metaDataDao.findById(itemId);
	        if (metaData != null) {// 指标不为空
	        	CouldDataSource dataSource = metaData.getCouldDataSource();
	            if (dataSource != null) {// 数据源不为空
	                if (!StringUtils.isBlank(dataSource.getDriveName())
	                                && !StringUtils.isBlank(dataSource.getAddress())
	                                && !StringUtils.isBlank(dataSource.getUserName())
	                                && !StringUtils.isBlank(dataSource.getPassWord())) {
	                    conn = dbTool.getConn(dataSource.getDriveName(), dataSource.getAddress(),dataSource.getUserName(), dataSource.getPassWord());
	                    if (conn != null) {
	                        if (!StringUtils.isBlank(metaData.getTableName())) {
	                            statement = conn.prepareStatement("select * from " + metaData.getTableName());
	                            rs = statement.executeQuery();
	                            ResultSetMetaData data = rs.getMetaData();// 数据结构集合
	                            int length = data.getColumnCount();
	                            for (int i = 1; i <= length; i++) {
	                                TableInfo tableDesc = new TableInfo();
	                                tableDesc.setDataType(data.getColumnTypeName(i) + "");
	                                tableDesc.setFiledcode(data.getColumnName(i));
	                                tableDesc.setName(data.getColumnLabel(i));
	                                tableDesc.setFiledLength(data.getColumnDisplaySize(i) + "");
	                                tableDesc.setIsNull(data.isNullable(i) + "");
	                                tableDesc.setIsPk(isPK(conn, metaData.getTableName().toUpperCase(), data
	                                                .getColumnName(i)));
	                                tableDesc.setMetaData(metaData);
	                                datas.add(tableDesc);
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        List<TableInfo> list = tableInfoDao.findListByItemId(itemId);
	        List<TableInfo> desc = excludeProperty(list, datas);
	        for (TableInfo info : desc) {
	            tableInfoDao.saveOrUpdate(info);
	            tableInfoDao.getSession().flush();
	        }
        } catch (Exception e) {
            logger.error("数据库连接失败!", e);
            throw new SQLException();
        }finally{
        	if(dbTool!=null){
        		dbTool.closeConn(conn);
            	dbTool.shutdownConnPool();
        	}
        }
    }
    
    public JSONArray getAuthTree(Long applyId,Long itemId, Long deptId, Long appId, String dataShareState) {
        // 创建节点
        List<Node> nodes = new ArrayList<Node>();
        List<MetaDataTableAuth> dataList =metaDataTableAuthDao.findByFiledAuthApplyId(applyId,"1");
        List<MetaDataTableAuth> auth=new ArrayList<MetaDataTableAuth>();
        if(appId!=null&&appId!=0L){
            auth=metaDataTableAuthDao.findByMetaId(itemId, deptId,appId,dataShareState);
        }else{
            auth= metaDataTableAuthDao.findByMetaId(itemId, deptId,dataShareState);// 当前部门申请指标
        }
        boolean fa = false;
        if (auth != null && auth.size() > 0) {
            fa = true;
        }
        Node root = new Node();
        root.setName("元数据项");
        root.setId(-1L);
        root.setPid(0L);
        root.setOpen(true);
        nodes.add(root);
        for (MetaDataTableAuth tableAuth : dataList) {
            Node node = new Node();
            node.setId(tableAuth.getTableInfo().getId());
            node.setPid(-1L);
            node.setName(tableAuth.getTableInfo().getName());
            if (fa) {//此前是否申请过
                for (MetaDataTableAuth metaDataTableAuth : auth) {
                    if (metaDataTableAuth.getTableInfo().getId() == tableAuth.getTableInfo().getId()) {
                        node.setChecked(true);// 存在当前节点就选中
                    }
                }
            }
            nodes.add(node);
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {

            @Override
            public boolean apply(Object source, String name, Object value) {
                if (name.equals("name") || name.equals("id") || name.equals("open") || name.equals("pid")||name.equals("checked")) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        return JSONArray.fromObject(nodes, jsonConfig);
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

    /**
     * 查找指标项目
     * @param source
     * @param now
     * @return
     * @author zhangx
     */
    private List<TableInfo> excludeProperty(List<TableInfo> source, List<TableInfo> now) {
        boolean falg = false;
        List<TableInfo> datas = new ArrayList<TableInfo>();
        if (source == null || source.size() == 0) {
            return now;
        }
        if (now == null || now.size() == 0) {
            return datas;
        }
        for (TableInfo desc : now) {
            for (TableInfo changeTableDesc : source) {
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
            falg = false;
        }
        return datas;
    }

    /*
     * (non-Javadoc)
     * @see com.ligitalsoft.cloudstorage.service.ITableInfoService#getTree()
     */
    /*
     * (non-Javadoc)
     * @see com.ligitalsoft.cloudstorage.service.ITableInfoService#getTree()
     */
    /*
     * (non-Javadoc)
     * @see com.ligitalsoft.cloudstorage.service.ITableInfoService#getTree(java.lang.Long, java.lang.Long)
     */
    @Override
    public JSONArray getTree(Long itemId, Long deptId,Long appId) {
        // 创建节点
        List<Node> nodes = new ArrayList<Node>();
        List<TableInfo> tables = tableInfoDao.findListByItemId(itemId);
        List<MetaDataTableAuth> auth=new ArrayList<MetaDataTableAuth>();
        if(appId!=null&&appId!=0L){
            auth=metaDataTableAuthDao.findByMetaId(itemId, deptId,appId,null);
        }else{
            auth= metaDataTableAuthDao.findByMetaId(itemId, deptId,null);// 当前部门申请指标
        }
        boolean fa = false;
        if (auth != null && auth.size() > 0) {
            fa = true;
        }
        Node root = new Node();
        root.setName("元数据项");
        root.setId(-1L);
        root.setPid(0L);
        root.setOpen(true);
        nodes.add(root);
        for (TableInfo tableInfo : tables) {
            Node node = new Node();
            node.setId(tableInfo.getId());
            node.setPid(-1L);
            node.setName(tableInfo.getName());
            if (fa) {//此前是否申请过
                for (MetaDataTableAuth metaDataTableAuth : auth) {
                    if (metaDataTableAuth.getTableInfo().getId() == tableInfo.getId()) {
                        node.setChecked(true);// 存在当前节点就选中
                    }
                }
            }
            nodes.add(node);
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            @Override
            public boolean apply(Object source, String name, Object value) {
                if (name.equals("name") || name.equals("id") || name.equals("open") || name.equals("pid")||name.equals("checked")) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        return JSONArray.fromObject(nodes, jsonConfig);
    }
    @Override
    public EntityHibernateDao<TableInfo> getEntityDao() {
        return tableInfoDao;
    }

    @Autowired
    public void setMetaDataTableAuthDao(MetaDataTableAuthDao metaDataTableAuthDao) {
        this.metaDataTableAuthDao = metaDataTableAuthDao;
    }
    @Autowired
    public void setMetaDataDao(MetaDataDao metaDataDao) {
        this.metaDataDao = metaDataDao;
    }
    @Autowired
    public void setTableInfoDao(TableInfoDao tableInfoDao) {
        this.tableInfoDao = tableInfoDao;
    }

	@Override
	public List<TableInfo> getTableInfoByMetaDataId(Long metaDataId)
			throws SQLException {
		return tableInfoDao.findListByItemId(metaDataId);		
	}
}
