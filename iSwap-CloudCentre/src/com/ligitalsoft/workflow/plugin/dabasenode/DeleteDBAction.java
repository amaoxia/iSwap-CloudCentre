package com.ligitalsoft.workflow.plugin.dabasenode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.DBConntTool;
import com.common.dbtool.FileDBTool;
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

public class DeleteDBAction extends PluginActionHandler {

	private static final long serialVersionUID = -6312189443939092327L;
	public String dataSource;// 数据源
	public String tableName;// 指标项
	public String dataList_inputVar;// 将数据写入缓存是的 ，缓存的key
	public String deleteData_outVar;// 插入数据库的总数

	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始将数据删除......");
		if(null!=this.getCacheInfo(dataList_inputVar)&&""!=this.getCacheInfo(dataList_inputVar)){
		try {
			List<DataPackInfo> dpInfoList = (List<DataPackInfo>) this
					.getCacheInfo(dataList_inputVar);
			if (null!=dpInfoList&&dpInfoList.size() > 0) {
				if (dpInfoList.get(0).getRowDataList().size() > 0) {
					this.createQuery(dpInfoList, context);
				} else {
					log.error("没有数据需要入库！");
				}
			} else {
				log.error("没有数据需要入库！");
			}

		} catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("删除数据的节点失败!", e);
			throw new ActionException(e);
		} 
		}else{
			log.info("数据删除节点时未获得【"+dataList_inputVar+"】变量的值！");
		}
	}

	/**
	 * 得到数据库的连接
	 * 
	 * @return
	 * @throws ActionException
	 * @author hudaowan
	 * @date 2011-9-5 上午11:08:39
	 */
	public Connection connDB(DBConntTool dbTool) throws ActionException {
		Connection conn = null;
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			Map<String, Object> find_map = new HashMap<String, Object>();
			find_map.put("key", dataSource);
			List<Map<String, Object>> entityList = tool.findToFiledb(
					FileDBConstant.fileDBName, FileDBConstant.dataSourceDB,
					find_map);
			Map<String, Object> entity = entityList.get(0);
			String driveName = (String) entity.get("driveName");
			String address = (String) entity.get("address");
			String userName = (String) entity.get("userName");
			String passWord = (String) entity.get("passWord");
			conn = dbTool.getConn(driveName, address, userName, passWord);

		} catch (Exception e) {
			log.error("连接数据库失败!", e);
			throw new ActionException(e);
		} finally {
			tool.closeFileDB();
		}
		return conn;
	}

	/**
	 * 生成sql语句
	 * 
	 * @author hudaowan
	 * @date Sep 3, 2008 9:50:46 AM
	 * @param session
	 * @param list
	 * @throws Exception
	 */
	private void createQuery(List<DataPackInfo> dpInfoList, ActivityExecution context)
			throws Exception {
		DBConntTool dbTool = DBConntTool.bcpoolInit();
		String sql = null;
		if ("".equals(this.tableName) || this.tableName == null) {
			log.error("表名为空，不能做删除操作！");
			throw new Exception("表名为空!");
		}
		int n = 1;
		for (DataPackInfo dpf : dpInfoList) {
			Connection conn = this.connDB(dbTool);
			conn.setAutoCommit(false);
			PreparedStatement pstmt = null;
			try{
			List<RowDataInfo> RowDataList = dpf.getRowDataList();
			if(RowDataList.size()>0){
				for (RowDataInfo rdi : RowDataList) {
					if (sql == null) {
						sql = this.getSql(rdi.getFiledDataInfos());
						log.info("delete:" + sql);
					}
					if (pstmt == null) {
						pstmt = conn.prepareStatement(sql);
					}
					this.setParameter(pstmt, rdi.getFiledDataInfos());
					pstmt.addBatch();
					log.info("第：【" + n + "】条数据处理完成.");
					n++;
				}
				if (pstmt != null) {
					pstmt.executeBatch();
				}
				conn.commit();
				pstmt.close();
				dbTool.closeConn(conn);
				pstmt = null;
			}
		
		}catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("删除数据的节点失败!", e);
			throw new ActionException(e);
		}finally {
			dbTool.closeConn(conn);
			dbTool.shutdownConnPool();
		}
		}
		context.setVariable(deleteData_outVar, n - 1);
	}

	/**
	 * 拼写值
	 * 
	 * @author hudaowan
	 * @date Sep 4, 2008 10:42:21 AM
	 * @param query
	 * @param tabInfoList
	 * @throws ParseException
	 */
	private void setParameter(PreparedStatement pstmt,
			List<FiledDataInfo> tabInfoList) throws Exception {
		int i = 1;
		for (FiledDataInfo tabInfo : tabInfoList) {
			if (!StringUtils.isBlank(tabInfo.getIspk())) {
				if ("true".equals(tabInfo.getIspk().toLowerCase())) {
					if ("timestamp".equals(tabInfo.getType().toLowerCase())
							&& tabInfo.getFiledValue() != null) {
						Timestamp sqlTimestamp = new Timestamp(
								System.currentTimeMillis());
						pstmt.setTimestamp(i, sqlTimestamp);
					} else if ("date".equals(tabInfo.getType().toLowerCase())
							&& tabInfo.getFiledValue() != null) {
						if(tabInfo.getFiledValue().contains("-")&&tabInfo.getFiledValue().length()>18){
							SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
							Date date = sdf.parse(tabInfo.getFiledValue().trim());
							pstmt.setDate(i++, new java.sql.Date(date.getTime()));
							pstmt.setDate(i, new java.sql.Date(date.getTime()));
						}else if(tabInfo.getFiledValue().contains("年")){
							SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy年MM月dd日");
							Date date = sdf.parse(tabInfo.getFiledValue().trim());
							date=DateUtil.strToDate(DateUtil.formatDate(date, "yyyy-MM-dd"), "yyyy-MM-dd");
							pstmt.setDate(i++, new java.sql.Date(date.getTime()));
							pstmt.setDate(i, new java.sql.Date(date.getTime()));
						}else if(tabInfo.getFiledValue().contains("/")){
							SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy/MM/dd");
							Date date = sdf.parse(tabInfo.getFiledValue().trim());
							date=DateUtil.strToDate(DateUtil.formatDate(date, "yyyy-MM-dd"), "yyyy-MM-dd");
							pstmt.setDate(i++, new java.sql.Date(date.getTime()));
							pstmt.setDate(i, new java.sql.Date(date.getTime()));
						}else{
							SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd");
							Date date = sdf.parse(tabInfo.getFiledValue().trim());
							pstmt.setDate(i++, new java.sql.Date(date.getTime()));
							pstmt.setDate(i, new java.sql.Date(date.getTime()));
						}
						
						
					} else {
						pstmt.setString(i, tabInfo.getFiledValue());
					}
					i++;
				}
			}
		}
	}

	/**
	 * 生成一条sql语句
	 * 
	 * @author hudaowan
	 * @date Sep 3, 2008 10:49:09 AM
	 * @param tabInfoList
	 * @return
	 */
	private String getSql(List<FiledDataInfo> filedDataInfoList) {
		StringBuffer sbFiled = new StringBuffer();
		for (FiledDataInfo fileDataInfo : filedDataInfoList) {
			if (!StringUtils.isBlank(fileDataInfo.getIspk())) {
				if ("true".equals(fileDataInfo.getIspk().toLowerCase())) {
					if (!StringUtils.isBlank(fileDataInfo.getType())&&"date".equals(fileDataInfo.getType().toLowerCase())) {
						sbFiled.append(fileDataInfo.getFiledName())
								.append(">=?").append(" and ")
								.append(fileDataInfo.getFiledName())
								.append("<=?").append(" and ");
					} else {
						sbFiled.append(fileDataInfo.getFiledName())
								.append("=?").append(" and ");
					}
				}
			}
		}
		String sql = "delete from " + this.tableName;
		if (sbFiled.length() > 0) {
			sql += "  where  "
					+ sbFiled.toString().substring(0, sbFiled.length() - 4);
		}
		return sql;
	}
}
