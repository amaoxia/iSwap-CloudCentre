package com.ligitalsoft.workflow.plugin.dabasenode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.DBConntTool;
import com.common.dbtool.FileDBTool;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

/**
 * 将数据库的数据写到缓存中
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-4 下午03:30:40
 *@Team 研发中心
 */
public class SelectDBAction  extends PluginActionHandler{

	private static final long serialVersionUID = -6182294606381470164L;

	public String dataSourceType;//数据库的类型  mysql   oracle    sqlserver  db
	public String dataSource;   //数据源
	public String sql;
	public String whereSql;
	public String dataRange;//数据范围
	public String pageSize;//发包大小
	public String selectData_outVar;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		DBConntTool dbTool = DBConntTool.bcpoolInit();
		Connection conn = this.connDB(dbTool);
		try{
	    	String data_r = "0#0";
	    	if(!"".equals(dataRange)&&dataRange.indexOf("#")>-1){
	    		data_r = dataRange;
	    	}else{
	    		data_r = (String)context.getVariable(dataRange);
	    	}
			if(!"".equals(data_r)){
				String[] dataArray = data_r.split("#");
				int dataCount = Integer.parseInt(dataArray[1])-Integer.parseInt(dataArray[0]);
				int pageCount = dataCount/Integer.parseInt(pageSize);
				if(dataCount%Integer.parseInt(pageSize)>0){
					pageCount = pageCount+1;
					
				}
				List<DataPackInfo> dpiList = new ArrayList<DataPackInfo>();
				
				for(int i=0;i<pageCount;i++){
					int start = Integer.parseInt(dataArray[0])+i*Integer.parseInt(pageSize);
					String sqStr = sql+whereSql;
					int endRow =  Integer.parseInt(pageSize);
					String page_sql = this.getSql(sqStr, dataSourceType, start,endRow);
					DataPackInfo dpInfo = this.getTableData(conn, page_sql);
					if(dpInfo.getRowDataList().size()>0){
						dpiList.add(dpInfo);
						int n = i+1;
						log.info("第【"+n+"】个数据包数据查询完成！");
					}else{
						break;
					}
				}
				this.putCacheInfo(selectData_outVar, dpiList);
			}else{
				log.info("没有设置数据的取值范围！");
			}
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("查询数据节点失败！",e);
			 throw new ActionException(e);
		}finally{
			 dbTool.closeConn(conn);
			 dbTool.shutdownConnPool();
		}
	}
	
	/**
	 * 得到数据库的连接
	 * @return
	 * @throws ActionException 
	 * @author  hudaowan
	 * @date 2011-9-5 上午11:08:39
	 */
	public Connection connDB(DBConntTool dbTool) throws ActionException{
		Connection conn = null;
		FileDBTool tool = FileDBTool.init();
		try{
	    	tool.getMongoConn();
	    	Map<String,Object> find_map = new HashMap<String,Object>();
	    	find_map.put("key", dataSource);
	    	List<Map<String,Object>> entityList = tool.findToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, find_map);
			Map<String,Object> entity = entityList.get(0);
			String driveName = (String)entity.get("driveName");
	    	String address =  (String)entity.get("address");
	    	String userName = (String)entity.get("userName");
	    	String passWord = (String)entity.get("passWord");
	        conn = dbTool.getConn(driveName,address,userName,passWord);
	    	
		}catch(Exception e){
			log.error("连接数据库失败!", e);
			throw new ActionException(e);
		}finally{
			tool.closeFileDB();
		}
		return conn;
	}
	
	
	/**
	 * 得到得到表的数据
	 * @param conn
	 * @param sql
	 * @return
	 * @throws ActionException 
	 * @author  hudaowan
	 * @date 2011-8-13 下午06:49:07
	 */
	private DataPackInfo getTableData(Connection conn,String sql) throws ActionException{
		DataPackInfo dpInfo = new DataPackInfo();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.createStatement();
			log.info("select ："+sql);
			Map<String,String> filedMap = this.getFieldName(conn, sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				RowDataInfo rdInfo = new RowDataInfo();
				for(Entry<String,String> entry:filedMap.entrySet()){
					if(!"ROWNUM".equals(entry.getKey())){
					String name = entry.getKey();
					String type = entry.getValue();
					String obj = rs.getString(name);
					FiledDataInfo tabInfo = new FiledDataInfo();
					tabInfo.setFiledName(name);
					tabInfo.setFiledValue(obj);
					tabInfo.setType(type);
					rdInfo.getFiledDataInfos().add(tabInfo);
					}
				}
				dpInfo.getRowDataList().add(rdInfo);
		   }
		}catch(Exception e){
			log.error("查询数据失败", e);
			throw new ActionException(e);
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				log.error("关闭结果集失败！");
				throw new ActionException(e);
			}
			
		}
		return dpInfo;
	}
	
	/**
	 * 得到表的字段类型
	 * @param conn
	 * @param sql
	 * @return
	 * @throws ActionException 
	 * @author  hudaowan
	 * @date 2011-8-13 下午06:22:48
	 */
	private Map<String,String> getFieldName(Connection conn,String sql)throws ActionException{
		Map<String,String> map = new HashMap<String,String>();
		ResultSet rs = null;
		PreparedStatement ptmt = null;
		try { 
			ptmt = conn.prepareStatement(sql);
		    rs = ptmt.executeQuery();
	    	ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=0;i<rsmd.getColumnCount();i++){
				String colName = rsmd.getColumnName(i + 1);
				if(!"iswaprn".equals(colName.toLowerCase())){
					String type = getJdbcTypeName(rsmd.getColumnType(i + 1));
					map.put(colName, type);
				}
			}
		} catch (Exception e) {
			log.error("获取数据库字段的名称失败！");
			throw new ActionException(e);
		}finally{
			try {
				ptmt.close();
				rs.close();
			} catch (SQLException e) {
				log.error("关闭结果集失败！");
				throw new ActionException(e);
			}
		}
		return map;
	}
	
	
	/**
	 * 得到分页的sql
	 * @param sqlStr
	 * @param type
	 * @param startRow
	 * @param count
	 * @return 
	 * @author  hudaowan
	 * @date 2011-9-3 下午09:34:30
	 */
	private String getSql(String sqlStr,String type,int startRow,int count){
		String sql = "";
		int endRow = startRow+count;
		if(type.indexOf("mysql")!=-1){
			sql = "select * from ("+sqlStr+")as a limit  "+startRow+","+endRow+" ";
		}else if(type.indexOf("oracle")!=-1){
			sql = "select *  from (select a. * ,rownum iswaprn from ("+sqlStr+") a where rownum<="+endRow+")  where iswaprn>"+startRow+"";
		}else if(type.indexOf("db2")!=-1){
			sql="select * from (select ROW_NUMBER() OVER() AS ROWNUM ,a.*  from ("+sqlStr+") as a)  as r where r.ROWNUM<="+endRow+" and r.ROWNUM>"+startRow+"";
		}else if(type.indexOf("jtds")!=-1){
			if(sqlStr.indexOf("#")!=-1){
				sqlStr = sqlStr.replace("#1",""+count);
				sql = sqlStr.replace("#2",""+startRow);
			}else{
				sql = sqlStr;
			}
		}else  if(type.indexOf("sqlserver")!=-1){
			if(sqlStr.indexOf("#")!=-1){
				sqlStr = sqlStr.replace("#1",""+count);
				sql = sqlStr.replace("#2",""+startRow);
			}else{
				sql = sqlStr;
			}
          //  sql="select top 10 * from test1 where (id not in (select top 10 id from test1 order by id desc)) order by id desc";
		
			//sql = "select * from (select TOP "+startRow+", * from ( SELECT TOP "+endRow+", *  from "+sqlStr+" ) as aSysTable)";
		}else{
			sql = sqlStr;
		}
		return sql;
	}
	
	/**
	 * 得到字段的类型
	 * @param jdbcType
	 * @return 
	 * @author  hudaowan
	 * @date 2011-8-13 下午06:42:45
	 */
	private  String getJdbcTypeName(int jdbcType) {
	        return (String)jdbcTypeMap.get(new Integer(jdbcType));
    }
	
    /**
     * 初始化字段类型
     */
	private static Map<Integer, String> jdbcTypeMap = new HashMap<Integer, String>( );
	static{
		 Field[] fields = java.sql.Types.class.getFields();
	      for (int i=0; i<fields.length; i++) {
	          try {
	              String name = fields[i].getName();
	              Integer value = (Integer)fields[i].get(null);
	              jdbcTypeMap.put(value, name);
	          } catch (IllegalAccessException e) {
	          }
	      }
	}
}
