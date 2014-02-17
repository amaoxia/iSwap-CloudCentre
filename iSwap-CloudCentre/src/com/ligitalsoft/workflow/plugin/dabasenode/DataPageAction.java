package com.ligitalsoft.workflow.plugin.dabasenode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.DBConntTool;
import com.common.dbtool.FileDBTool;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;

/**
 * 将大数据 分批打包
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 下午09:39:38
 *@Team 研发中心
 */
public class DataPageAction extends PluginActionHandler {
	
	private static final long serialVersionUID = -4454048304932658269L;
	public String dataSource;//数据源
	public String pageSize;
	public String sql;
	public String where;
	public String dataCount_outVar;
	public String pageCount_outVar;
	public String pageText_outVar;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		DBConntTool dbTool = DBConntTool.bcpoolInit();
		Connection conn = this.connDB(dbTool);
		try{
		    int count = this.getDataCount(conn);
		    int page_size = 100000;//10万
			if(!"".equals(pageSize)){
				page_size = Integer.parseInt(pageSize);
			}
		    int pageCount = count/page_size;
		    if(count%page_size>0){
		    	pageCount = pageCount+1;
		    }
		    String[] array = new String[pageCount];
			for(int i=0;i<pageCount;i++){
				int start = i*page_size;
				int end = (i+1)*page_size;
				array[i] = start+"#"+end;
			}
			context.setVariable(dataCount_outVar, String.valueOf(count));//总页数
			context.setVariable(pageCount_outVar, String.valueOf(pageCount));//总页数
			context.setVariable(pageText_outVar, array);//分页的内容
		}catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("获得数据总数节点失败！",e);
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
	public  Connection connDB(DBConntTool dbTool) throws ActionException{
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
	 * 获取数据库的总数条数
	 * @return 
	 * @author  hudaowan
	 * @date 2011-9-3 下午09:53:49
	 */
	private  int getDataCount(Connection conn)throws ActionException{
		int count = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.createStatement();
			String sqStr = sql+" "+where;
			log.info("select ："+sqStr);
			rs = stmt.executeQuery(sqStr);
			while(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			log.error("获取数据总数失败", e);
			throw new ActionException(e);
		}
		return count;
	}

}
