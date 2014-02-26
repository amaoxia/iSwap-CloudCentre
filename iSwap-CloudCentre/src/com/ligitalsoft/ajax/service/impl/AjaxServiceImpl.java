package com.ligitalsoft.ajax.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.tree.ztree.Node;
import com.ligitalsoft.ajax.service.IAjaxService;
import com.ligitalsoft.appitemmgr.dao.AppItemExchangeConfDao;
import com.ligitalsoft.appitemmgr.service.AppItemExchangeConfService;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConf;
import com.ligitalsoft.model.appitemmgr.AppItemExchangeConfDetails;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.dao.SysDeptDao;

/**
 * 部门实现类
 * 
 * @author zhangx
 * @since May 16, 2011 1:05:40 PM
 * @name com.ligitalsoft.sysmanager.service.impl.SysDeptServiceImpl.java
 * @version 1.0
 */
@Service("ajaxService")
public class AjaxServiceImpl implements IAjaxService {

	private SysDeptDao sysDeptDao;
	private AppItemExchangeConfService appItemExchangeConfService;
	
	@Transactional(readOnly = true)
	public JSONArray depTree4AppItemExchangeConf(Long appMsgId, Long AppItemId, String[] sendDeptIdsArray) {
		List<Node> nodes = new ArrayList<Node>();
		//过滤掉对应指标下对应发送部门
		Set<SysDept> filterDeptSet = new HashSet<SysDept>();
		List<SysDept> depts = sysDeptDao.findDeptOrderByLevel();
		if(sendDeptIdsArray!=null){
			AppItemExchangeConf appItemExchangeConf = appItemExchangeConfService.findAppItemExchangeConfBySendDept(appMsgId, AppItemId, sendDeptIdsArray);
			if(appItemExchangeConf!=null){
				List<AppItemExchangeConfDetails> appItemExchangeConfDetailsList = appItemExchangeConf.getAppItemExchangeConfDetails();
				if(appItemExchangeConfDetailsList!=null&&appItemExchangeConfDetailsList.size()>0){
					for(AppItemExchangeConfDetails appItemExchangeConfDetails : appItemExchangeConfDetailsList){
						filterDeptSet.add(appItemExchangeConfDetails.getReceiveDept());
					}
				}
			}
		}
		
		Node root = new Node();
		root.setName("部门");
		root.setId(-1 + "");
		root.setPid(0 + "");
		root.setOpen(true);
		nodes.add(root);
		for (SysDept dept : depts) {
			if(filterDeptSet.contains(dept)||ArrayUtils.contains(sendDeptIdsArray, dept.getId().toString()))continue;
			Node node = new Node();
			node.setId(dept.getId() + "");
			node.setName(dept.getDeptName());
			if (dept.getSysDept() == null) {
				node.setPid("-1");
			} else {
				node.setPid(dept.getSysDept().getId() + "");
			}
			nodes.add(node);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {

				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("url")
						|| name.equals("open")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, jsonConfig);
	}
	
	@Autowired
	public void setSysDeptDao(SysDeptDao sysDeptDao) {
		this.sysDeptDao = sysDeptDao;
	}
	
	@Autowired
	public void setAppItemExchangeConfService(
			AppItemExchangeConfService appItemExchangeConfService) {
		this.appItemExchangeConfService = appItemExchangeConfService;
	}
}