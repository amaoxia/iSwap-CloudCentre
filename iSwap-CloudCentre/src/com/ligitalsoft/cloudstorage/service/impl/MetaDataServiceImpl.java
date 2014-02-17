/*
 * @(#)MetaDataServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.cloudstorage.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.common.StringUtils;
import com.ligitalsoft.cloudstorage.dao.MetaDataAppMsgDao;
import com.ligitalsoft.cloudstorage.dao.MetaDataDao;
import com.ligitalsoft.cloudstorage.service.IMetaDataService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.cloudstorage.MetaDataAppMsg;
import com.ligitalsoft.util.Node;

/**
 * 元数据的管理_SERVICE实现类
 * 
 * @author zhangx
 * @since Jun 16, 2011 8:40:31 PM
 * @name com.ligitalsoft.cloudstorage.service.impl.MetaDataServiceImpl.java
 * @version 1.0
 */
@Service
public class MetaDataServiceImpl extends BaseSericesImpl<MetaData> implements
		IMetaDataService {

	private MetaDataDao metaDataDao;
	private MetaDataAppMsgDao metaDataAppMsgDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ligitalsoft.cloudstorage.service.IMetaDataService#save(com.ligitalsoft
	 * .model.cloudstorage.MetaData, java.lang.Long[])
	 */
	@Override
	public void save(MetaData metaData, String[] appId) {
		metaDataDao.save(metaData);// 保存元数据对象
		if (appId != null && appId.length > 0) {
			for (int i = 0; i < appId.length; i++) {
				MetaDataAppMsg metaDataAppMsg = new MetaDataAppMsg();
				AppMsg appMsg = new AppMsg();// 创建服务应用对象
				if (!StringUtils.isBlank(appId[i])) {
					appMsg.setId(Long.parseLong(appId[i]));// 设置服务ID
				}
				metaDataAppMsg.setAppMsg(appMsg);
				metaDataAppMsg.setMetaData(metaData);
				metaDataAppMsgDao.save(metaDataAppMsg);
				metaDataAppMsgDao.getSession().flush();// 连续写入操作
			}
		}
	}

	@Override
	public void update(MetaData metaData, String[] appId) {
		metaDataDao.getSession().clear();
		metaDataDao.saveOrUpdate(metaData);// 要清空session缓存 可以直接使用update方法
		metaDataAppMsgDao.deleteByMetaId(metaData.getId());
		if (appId != null && appId.length > 0) {
			for (int i = 0; i < appId.length; i++) {
				MetaDataAppMsg metaDataAppMsg = new MetaDataAppMsg();
				AppMsg appMsg = new AppMsg();// 创建服务应用对象
				if (!StringUtils.isBlank(appId[i])) {
					appMsg.setId(Long.parseLong(appId[i]));// 设置服务ID
				}
				metaDataAppMsg.setAppMsg(appMsg);
				metaDataAppMsg.setMetaData(metaData);
				metaDataAppMsgDao.saveOrUpdate(metaDataAppMsg);
				metaDataAppMsgDao.getSession().flush();// 连续写入操作
			}
		}
	}

	@Override
	public JSONArray getTypeTree(String type, String url) {
		// 创建节点
		List<Node> nodes = new ArrayList<Node>();
		Node root = new Node();
		root.setName("元数据库");
		root.setId(1L);
		root.setPid(0L);
		// 原始库
		Node ysNode = new Node();
		ysNode.setId(2L);
		ysNode.setPid(1L);
		ysNode.setName("原始库");
		ysNode.setTarget("right_content");
		ysNode.setUrl(url + "?type=1");
		// 基础库
		Node jcNode = new Node();
		jcNode.setId(3L);
		jcNode.setPid(1L);
		jcNode.setName("基础库");
		jcNode.setTarget("right_content");
		jcNode.setUrl(url + "?type=2");// metadata!listMeta.action
		if (!StringUtils.isBlank("1")) {
			// 主题库
			Node ztNode = new Node();
			ztNode.setName("主题库");
			ztNode.setId(4L);
			ztNode.setPid(1L);
			ztNode.setTarget("right_content");
			ztNode.setUrl(url + "?type=3");
		}
		// /
		nodes.add(root);
		nodes.add(ysNode);
		nodes.add(jcNode);
		// nodes.add(ztNode);
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("target")
						|| name.equals("url")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, config);
	}

	/**
	 * 通过部门ID查询指标项
	 * 
	 * @author fangbin
	 */
	@Override
	public List<MetaData> getAllByDeptId(String deptId) {

		return metaDataDao.getAllByDeptId(deptId);
	}

	@Override
	public void updateStatus(Long[] ids, String status) {
		metaDataDao.updateStatus(ids, status);
	}

	@Override
	public EntityHibernateDao<MetaData> getEntityDao() {
		return metaDataDao;
	}

	@Autowired
	public void setMetaDataDao(MetaDataDao metaDataDao) {
		this.metaDataDao = metaDataDao;
	}

	@Autowired
	public void setMetaDataAppMsgDao(MetaDataAppMsgDao metaDataAppMsgDao) {
		this.metaDataAppMsgDao = metaDataAppMsgDao;
	}

}
