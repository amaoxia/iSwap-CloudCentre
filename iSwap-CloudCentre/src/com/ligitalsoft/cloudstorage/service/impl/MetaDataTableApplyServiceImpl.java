/*
 * @(#)MetaDataTableApplyServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dbtool.DBConntTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.framework.web.pager.PageBean;
import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudstorage.dao.MetaDataDao;
import com.ligitalsoft.cloudstorage.dao.MetaDataTableApplyDao;
import com.ligitalsoft.cloudstorage.dao.MetaDataTableAuthDao;
import com.ligitalsoft.cloudstorage.service.IMetaDataTableApplyService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudstorage.CouldDataSource;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.cloudstorage.MetaDataTableApply;
import com.ligitalsoft.model.cloudstorage.MetaDataTableAuth;
import com.ligitalsoft.model.cloudstorage.TableInfo;

/**
 * 数据申请查看表_实现类
 * 
 * @author zhangx
 * @since Aug 11, 2011 1:40:55 AM
 * @name 
 *       com.ligitalsoft.cloudstorage.service.impl.MetaDataTableApplyServiceImpl.
 *       java
 * @version 1.0
 */
@Transactional
@Service("applyService")
public class MetaDataTableApplyServiceImpl extends
		BaseSericesImpl<MetaDataTableApply> implements
		IMetaDataTableApplyService {

	private MetaDataTableApplyDao metaDataTableApplyDao;// 共享申请
	private MetaDataDao metaDataDao;// 元数据指标
	private MetaDataTableAuthDao metaDataTableAuthDao;// 申请指标项字段ID
	private AppMsgService appMsgService;

	@Override
	public void saveOrUpdate(Long itemId, Long appId, MetaDataTableApply apply,
			String tids) throws ServiceException {
		if (apply.getId() != null) {// 修改
			MetaDataTableApply oldApply = metaDataTableApplyDao.findById(apply
					.getId());// 原申请记录
			List<MetaDataTableAuth> auth = metaDataTableAuthDao
					.findByTableApplyId(apply.getId());// 原申请字段
			if (apply.getFiledApplyType().equals("0")) {
				apply.setFiledApplyFile(null);// 文件名称
				apply.setFiledApplyName(null);// 文件名字
			}
			apply.setAppMsg(oldApply.getAppMsg());
			apply.setDataSource(oldApply.getDataSource());
			apply.setFiledApplyDate(oldApply.getFiledApplyDate());
			apply.setMetaData(oldApply.getMetaData());
			apply.setTableName(oldApply.getTableName());

			String[] aIds = addFiled(auth, tids).split(",");
			if (aIds != null && aIds.length > 0) {// 添加的有新字段
				if (apply.getFiledApplystate().equals("1")) {// 授权之后
					if (isUpdate(auth, tids)) {// 变更
						apply.setFiledApplystate("2");// 二次申请
					}
				}
				if (!StringUtils.isBlank(apply.getFiledAuthState())
						&& apply.getFiledAuthState().equals("1")) {
					apply.setFiledAuthState("2");// 要进行二次授权
				}
			}
			metaDataTableApplyDao.merge(apply);
			if (!StringUtils.isBlank(tids) && auth != null && auth.size() != 0) {
				String[] dIds = delFiled(auth, tids).split(",");
				for (String id : dIds) {// 删除现在未申请的字段
					if (!StringUtils.isBlank(id)) {
						metaDataTableAuthDao.remove(metaDataTableAuthDao
								.findByTableApplyId(apply.getId(),
										Long.parseLong(id)).get(0));
						this.getEntityDao().getSession().flush();
					}
				}

				for (String id : aIds) {
					if (!StringUtils.isBlank(id)) {
						MetaDataTableAuth th = new MetaDataTableAuth();
						TableInfo tableInfo = new TableInfo();
						tableInfo.setId(Long.parseLong(id));
						th.setTableInfo(tableInfo);
						th.setMetaDataTableApply(apply);
						metaDataTableAuthDao.merge(th);
						this.getEntityDao().getSession().flush();
					}
				}
			}
		} else {// 添加操作
			MetaData data = metaDataDao.findById(itemId);
			apply.setMetaData(data);
			apply.setFiledApplystate("1");// 已申请
			if (data.getCouldDataSource() != null) {
				apply.setDataSource(data.getCouldDataSource());
			}
			if (!StringUtils.isBlank(data.getTableName())) {
				apply.setTableName(data.getTableName());
			}
			if (appId != null && appId != 0L) {
				AppMsg appMsg = appMsgService.findById(appId);
				if (appMsg != null) {
					apply.setAppMsg(appMsg);
				}
			}
			metaDataTableApplyDao.saveOrUpdate(apply);
			if (tids != null && !StringUtils.isBlank(tids)) {
				String ids[] = tids.split(",");
				if (ids != null && ids.length > 0) {
					for (String string : ids) {
						if (!StringUtils.isBlank(string)) {
							MetaDataTableAuth auth = new MetaDataTableAuth();
							TableInfo tableInfo = new TableInfo();
							tableInfo.setId(Long.parseLong(string));
							auth.setTableInfo(tableInfo);
							auth.setMetaDataTableApply(apply);
							metaDataTableAuthDao.saveOrUpdate(auth);
							this.getEntityDao().getSession().flush();
						}
					}
				}
			}
		}
	}

	/**
	 * 添加数据字段申请
	 */
	@Override
	public void addDataApply(MetaDataTableApply apply, String checkids,
			String nocheckids) {
		MetaDataTableApply tableApply = metaDataTableApplyDao.findById(apply
				.getId());
		if (!StringUtils.isBlank(apply.getDataApplyType())
				&& apply.getDataApplyType().equals("1")) {
			if (apply.getDataApplyFile() != null) {
				tableApply.setDataApplyFile(apply.getDataApplyFile());// 设置文件
				tableApply.setDataApplyFileName(apply.getDataApplyFileName());// 设置文件名称
			}
		}
		tableApply.setDataApplyDate(DateUtil.toDate(new Date()));// 设置申请时间
		tableApply.setDataApplyType(apply.getDataApplyType());// 申请类型
		tableApply.setDataApplyContent(apply.getDataApplyContent());// 申请内容
		if ((tableApply.getDataApplyState().equals("0") || apply
				.getDataApplyState() == null)
				&& (tableApply.getDataAuthState() == null || tableApply
						.getDataAuthState().equals("0"))) {// 之前未申请
			tableApply.setDataApplyState("1");// 申请状态 0 未申请 1申请 2 二次申请
		} else {
			if (isAdd(checkids, apply.getId())) {//
				tableApply.setDataApplyState("2");// 申请状态 0 未申请 1申请 2 二次申请
			}
		}
		if (!StringUtils.isBlank(tableApply.getDataAuthState())
				&& tableApply.getDataAuthState().equals("1")) {
			if (isAdd(checkids, apply.getId())) {//
				tableApply.setDataAuthState("2");// 授权状态 0 未授权 1授权 2 需再次授权
			}
		}
		metaDataTableApplyDao.merge(tableApply);
		/**
		 * 设置未选中值状态
		 */
		if (!StringUtils.isBlank(nocheckids)) {
			String[] ids = nocheckids.split(",");
			for (String id : ids) {
				if (!StringUtils.isBlank(id)) {
					MetaDataTableAuth dataTableAuth = metaDataTableAuthDao
							.findByTableApplyTableId(apply.getId(),
									Long.parseLong(id));
					dataTableAuth.setDataAuthState(null);// 未申请
					metaDataTableAuthDao.merge(dataTableAuth);
					metaDataTableAuthDao.getSession().flush();
				}
			}
		}
		/**
		 * 设置选中值状态
		 */
		if (!StringUtils.isBlank(checkids)) {
			String[] ids = checkids.split(",");
			for (String id : ids) {
				if (!StringUtils.isBlank(id)) {
					MetaDataTableAuth dataTableAuth = metaDataTableAuthDao
							.findByTableApplyTableId(apply.getId(),
									Long.parseLong(id));
					dataTableAuth.setDataAuthState("0");// 已申请
					metaDataTableAuthDao.merge(dataTableAuth);
					metaDataTableAuthDao.getSession().flush();
				}
			}
		}
	}

	/**
	 * 判断是否有新的字段申请
	 * 
	 * @param ids
	 * @param dataTableAuth
	 * @return
	 * @author zhangx
	 */
	public boolean isAdd(String tids, Long appId) {
		boolean fa = false;
		String[] ids = tids.split(",");
		if (ids != null && ids.length != 0) {
			for (String id : ids) {
				if (!StringUtils.isBlank(id)) {
					List<MetaDataTableAuth> lis = metaDataTableAuthDao
							.findByTableApplyId(appId, Long.parseLong(id));
					if (lis != null && lis.size() > 0) {
						MetaDataTableAuth auth = lis.get(0);
						if (auth.getDataAuthState() == null) {// 新增申请字段
							fa = true;
							break;
						}
					}
				}
			}
		}
		return fa;
	}

	// 添加授权信息
	@Override
	public void addAuth(Long id, Long[] ids, String noIds) {
		if (ids != null && ids.length > 0) {
			MetaDataTableApply apply = metaDataTableApplyDao.findById(id);
			apply.setFiledAuthDate(DateUtil.toDate(new Date()));// 授权时间
			apply.setFiledApplystate("1");
			apply.setFiledAuthState("1");// 已授权
			metaDataTableApplyDao.update(apply);
			for (Long authId : ids) {
				MetaDataTableAuth auth = metaDataTableAuthDao.findById(authId);
				auth.setFiledAuthState("1");// 授权
				metaDataTableAuthDao.saveOrUpdate(auth);
				this.getEntityDao().getSession().flush();
			}
		}
		// 未选中
		if (!StringUtils.isBlank(noIds)) {
			String[] nids = noIds.split(",");
			for (String nid : nids) {
				if (!StringUtils.isBlank(nid)) {
					MetaDataTableAuth auth = metaDataTableAuthDao.findById(Long
							.parseLong(nid));
					auth.setFiledAuthState("0");// 未授权
					metaDataTableAuthDao.saveOrUpdate(auth);
					this.getEntityDao().getSession().flush();
				}
			}
		}
	}

	/**
	 * 申请字段是否变更
	 * 
	 * @author zhangx
	 */
	private boolean isUpdate(List<MetaDataTableAuth> auth, String tids) {
		boolean fa = false;
		String[] ids = tids.split(",");
		if (ids != null && ids.length > 0 && auth != null && auth.size() > 0) {
			if (ids.length != auth.size()) {
				fa = true;
				return fa;
			}
			for (MetaDataTableAuth metaDataTableAuth : auth) {
				if (!isOldExist(metaDataTableAuth, ids)) {// 是否存在
					fa = true;
					return fa;
				}
			}
		}
		return fa;
	}

	/**
	 * 原申请字段是否存在
	 * 
	 * @param auth
	 * @param ids
	 * @return
	 * @author zhangx
	 */
	private boolean isOldExist(MetaDataTableAuth auth, String[] ids) {
		boolean fa = false;
		for (String id : ids) {
			if (!StringUtils.isBlank(id)) {
				if (auth.getTableInfo().getId().toString().equals(id)) {// 判断是否存在
					fa = true;
					break;
				}
			}
		}
		return fa;
	}

	/**
	 * 原申请字段是否存在
	 * 
	 * @param auth
	 * @param ids
	 * @return
	 * @author zhangx
	 */
	private boolean isNewExist(List<MetaDataTableAuth> auth, String id) {
		boolean fa = false;
		for (MetaDataTableAuth metaDataTableAuth : auth) {
			if (metaDataTableAuth.getTableInfo().getId().toString().equals(id)) {
				fa = true;
				return fa;
			}
		}
		return fa;
	}

	/**
	 * 二次申请添加的字段
	 * 
	 * @return
	 * @author zhangx
	 */
	private String addFiled(List<MetaDataTableAuth> auth, String tids) {
		StringBuffer buffer = new StringBuffer();
		String[] ids = tids.split(",");
		for (String id : ids) {
			if (!StringUtils.isBlank(id)) {
				if (!isNewExist(auth, id)) {
					buffer.append(id + ",");
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 二次申请 删除的字段
	 * 
	 * @return
	 * @author zhangx
	 */
	private String delFiled(List<MetaDataTableAuth> auth, String tids) {
		StringBuffer buffer = new StringBuffer();
		String[] ids = tids.split(",");
		for (MetaDataTableAuth metaDataTableAuth : auth) {
			if (!isOldExist(metaDataTableAuth, ids)) {// 原申请字段不存在
				buffer.append(metaDataTableAuth.getTableInfo().getId() + ",");
			}
		}
		return buffer.toString();
	}

	@Override
	public MetaDataTableApply findByItemDeptId(Long itemId, Long deptId,
			Long appId) {
		if (appId != null && appId != 0L) {
			return metaDataTableApplyDao.findByItemDeptId(itemId, deptId,
					appId, null);
		} else {
			return metaDataTableApplyDao.findByItemDeptId(itemId, deptId, null);
		}
	}

	@Override
	public MetaDataTableApply findByItemDataState(Long itemId, Long deptId,
			Long appId, String dataApplyState) {
		if (appId != null && appId != 0L) {
			return metaDataTableApplyDao.findByItemDeptId(itemId, deptId,
					appId, dataApplyState);
		} else {
			return metaDataTableApplyDao.findByItemDeptId(itemId, deptId,
					dataApplyState);
		}
	}

	@Override
	public void addDataAuth(Long id, Long[] ids, String noIds) {
		if (ids != null && ids.length > 0) {
			MetaDataTableApply apply = metaDataTableApplyDao.findById(id);
			apply.setDataAuthDate(DateUtil.toDate(new Date()));// 授权时间
			apply.setDataAuthState("1");// 已授权
			apply.setDataApplyState("1");// 已申请
			metaDataTableApplyDao.update(apply);
			for (Long authId : ids) {
				MetaDataTableAuth auth = metaDataTableAuthDao.findById(authId);
				auth.setDataAuthState("1");// 授权
				metaDataTableAuthDao.saveOrUpdate(auth);
				this.getEntityDao().getSession().flush();
			}
			if (!StringUtils.isBlank(noIds)) {
				String[] nids = noIds.split(",");
				for (String nid : nids) {
					if (!StringUtils.isBlank(nid)) {
						MetaDataTableAuth auth = metaDataTableAuthDao
								.findById(Long.parseLong(nid));
						auth.setDataAuthState("0");// 未授权
						metaDataTableAuthDao.saveOrUpdate(auth);
						this.getEntityDao().getSession().flush();
					}
				}
			}
		}
	}

	@Override
	public List<Object[]> findDataList(Long id, PageBean page)
			throws ServiceException {
		List<Object[]> list = new ArrayList<Object[]>();
		MetaDataTableApply dataTableApply = findById(id);
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		DBConntTool dbTool = null;
		try {
			dbTool = DBConntTool.bcpoolInit();
			if (!StringUtils.isBlank(dataTableApply.getFiledApplystate())
					&& (dataTableApply.getFiledApplystate().equals("1") || dataTableApply
							.getFiledApplystate().equals("2"))) {// 当前指标已经通过申请
				CouldDataSource dataSource = dataTableApply.getDataSource();
				conn = dbTool.getConn(dataSource.getDriveName(),
						dataSource.getAddress(), dataSource.getUserName(),
						dataSource.getPassWord());// 得到数据连接
				if (conn != null) {
					String dataBaseName = conn.getMetaData()
							.getDatabaseProductName();
					String countSql = "select count(*) as countName from "
							+ dataTableApply.getMetaData().getTableName();
					statement = conn.prepareStatement(countSql);
					rs = statement.executeQuery();
					if (rs.next()) {
						int total = rs.getInt("countName");
						page.setTotal(total);
					}
					statement = null;
					rs = null;
					String sql = getSql(dataTableApply.getMetaData()
							.getTableName(), dataBaseName, page.getStart(),
							page.getPerPage());
					statement = conn.prepareStatement(sql);
					rs = statement.executeQuery();
					//findByTableApplyIdStatus(id, "1")
					List<MetaDataTableAuth> auths = metaDataTableAuthDao
							.findByFiledAuthApplyId(id,"1");// 申请查看字段通过
					while (rs.next()) {
						Object[] obj = new Object[auths.size()];//字段对应的数据
						for (int i = 0; i < auths.size(); i++) {
							TableInfo info = auths.get(i).getTableInfo();
							obj[i] = rs.getString(info.getFiledcode());
						}
						list.add(obj);
					}

				}
			}
		} catch (Exception e) {
			this.logger.error("数据查询出错!");
			throw new ServiceException("查询数据出错,请联系管理员!");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new ServiceException("数据库关闭异常,请联系管理员!");
			}
		}
		return list;
	}

	@Override
	public List<Object[]> findDataList(Long id) throws ServiceException {
		List<Object[]> list = new ArrayList<Object[]>();
		MetaDataTableApply dataTableApply = findById(id);
		java.sql.Connection conn = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		DBConntTool dbTool = null;
		try {
			if (!StringUtils.isBlank(dataTableApply.getFiledApplystate())
					&& (dataTableApply.getFiledApplystate().equals("1") || dataTableApply
							.getFiledApplystate().equals("2"))) {// 当前指标已经通过申请
				CouldDataSource dataSource = dataTableApply.getDataSource();
				dbTool = DBConntTool.bcpoolInit();
				conn = (Connection) dbTool.getConn(dataSource.getDriveName(),
						dataSource.getAddress(), dataSource.getUserName(),
						dataSource.getPassWord());// 得到数据连接
				if (conn != null) {
					statement = conn.prepareStatement("select *  from "
							+ dataTableApply.getMetaData().getTableName());
					rs = statement.executeQuery();
					List<MetaDataTableAuth> auths = metaDataTableAuthDao
							.findByDataAuthStateApplyId(id, "1");// 申请查看字段通过
					while (rs.next()) {
						Object[] obj = new Object[auths.size()];
						for (int i = 0; i < auths.size(); i++) {
							obj[i] = rs.getString(auths.get(i).getTableInfo()
									.getFiledcode());
						}
						list.add(obj);
					}
					if (rs != null) {
						rs.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (conn != null) {
						conn.close();
					}
				}
			}
		} catch (Exception e) {
			this.logger.error("数据查询出错!");
			throw new ServiceException();
		} finally {
			if (dbTool != null) {
			}
			dbTool.shutdownConnPool();
		}
		return list;
	}

	/**
	 * 对应数据库进行分页
	 * 
	 * @param sqlStr
	 * @param type
	 * @param startRow
	 * @param count
	 * @return
	 * @author zhangx
	 */
	private String getSql(String sqlStr, String type, int startRow, int count) {
		String sql = "";
		int endRow = count;// startRow +
		String utype = type.toUpperCase();
		if (utype.equals("MYSQL")) {
			sql = "select * from " + sqlStr + "  as a limit  " + startRow + ","
					+ endRow + " ";
		} else if (utype.equals("ORACLE")) {
			sql = "select *  from (select a. * ,rownum iswaprn from " + sqlStr
					+ " a where rownum<=" + endRow + ")  where iswaprn>"
					+ startRow + "";
		} else if (utype.indexOf("DB2") != -1) {
			sql = "select * from (select ROW_NUMBER() OVER() AS ROWNUM ,a.*  from "
					+ sqlStr
					+ "  as a)  as r where r.ROWNUM>"
					+ startRow
					+ " and r.ROWNUM<=" + endRow;
		} else if (utype.equals("SQLSERVER")) {
			sql = "select * from (select TOP " + startRow
					+ " * FROM ( SELECT TOP " + endRow + " *  from " + sqlStr
					+ " ) as aSysTable)";
		} else {
			sql = sqlStr;
		}
		return sql;
	}

	@Override
	public EntityHibernateDao<MetaDataTableApply> getEntityDao() {
		return metaDataTableApplyDao;
	}

	@Autowired
	public void setMetaDataTableApplyDao(
			MetaDataTableApplyDao metaDataTableApplyDao) {
		this.metaDataTableApplyDao = metaDataTableApplyDao;
	}

	public MetaDataDao getMetaDataDao() {
		return metaDataDao;
	}

	@Autowired
	public void setMetaDataDao(MetaDataDao metaDataDao) {
		this.metaDataDao = metaDataDao;
	}

	@Autowired
	public void setAppMsgService(AppMsgService appMsgService) {
		this.appMsgService = appMsgService;
	}

	@Autowired
	public void setMetaDataTableAuthDao(
			MetaDataTableAuthDao metaDataTableAuthDao) {
		this.metaDataTableAuthDao = metaDataTableAuthDao;
	}
}
