package com.ligitalsoft.sysmanager.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.framework.dao.SortPara;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.framework.web.pager.PageBean;
import com.common.utils.tree.ztree.Node;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.dao.SysDeptDao;
import com.ligitalsoft.sysmanager.service.ISysDeptService;

/**
 * 部门实现类
 * 
 * @author zhangx
 * @since May 16, 2011 1:05:40 PM
 * @name com.ligitalsoft.sysmanager.service.impl.SysDeptServiceImpl.java
 * @version 1.0
 */
@Service("sysDeptService")
public class SysDeptServiceImpl extends BaseSericesImpl<SysDept> implements
		ISysDeptService {

	private SysDeptDao sysDeptDao;

	@Transactional(readOnly = true)
	public JSONArray depTree() {
		List<Node> nodes = new ArrayList<Node>();
		List<SysDept> depts = sysDeptDao.findDeptOrderByLevel();
		Node root = new Node();
		root.setName("部门");
		root.setId(-1 + "");
		root.setPid(0 + "");
		root.setOpen(true);
		nodes.add(root);
		for (SysDept dept : depts) {
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

	@Transactional(readOnly = true)
	public JSONArray getChildDeptById(Long id){
		List<Node> nodes = new ArrayList<Node>();
		List<SysDept> depts = null;
		if(id!=null){
			depts = sysDeptDao.findListByFatherId(id);
		}else {
			depts = sysDeptDao.findListByRoot();
		}
		for (SysDept dept : depts) {
			Node node = new Node();
			node.setId(dept.getId()+"");
			node.setName(dept.getDeptName());
			node.setPid(id + "");
			node.setIsParent(dept.getChildrenDept()!=null&&dept.getChildrenDept().size()>0);
			node.setData(dept.getId()+"");//由于id参数已被基础类占用,固采用data替代
			nodes.add(node);
		}
		
		return buildJSONArrayByDept(nodes);
	}
	

	@Override
	public JSONArray getDeptTreeById(Long id) {
		List<Node> nodes = new ArrayList<Node>();
		SysDept dept = sysDeptDao.findById(id);
		List<SysDept> depts = new ArrayList<SysDept>();
		depts.add(dept);
		
		while(dept.getSysDept()!=null){
			dept = sysDeptDao.findById(dept.getSysDept().getId());
			depts.add(dept);
		}
		
		for (SysDept dep : depts) {
			Node node = new Node();
			node.setId(dep.getId()+"");
			node.setName(dep.getDeptName());
			node.setPid(dep.getSysDept()!=null?dep.getSysDept().getId()+"":"");
			node.setIsParent(dep.getChildrenDept()!=null&&dep.getChildrenDept().size()>0);
			nodes.add(node);
		}
		
		return buildJSONArrayByDept(nodes);
	}

	private JSONArray buildJSONArrayByDept(List<Node> nodes){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {

				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("url")
						|| name.equals("open") || name.equals("isParent")
						|| name.equals("data")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, jsonConfig);
	}
	
	// /**
	// * 树形递归实现
	// * @param listDepts
	// * @param root
	// * @author zhangx
	// */
	// @SuppressWarnings("unchecked")
	// @Transactional(readOnly = true)
	// private void createTreeNode(List<SysDept> listDepts, Node root) {
	// List<SysDept> list = sysDeptDao.findListRoot();// 查询根节点
	// Node root = new Node();
	// Data data = new Data();
	// root.setState(Node.NODE_STATE_OPEN);
	// data.setTitle("组织机构");
	// root.setData(data);
	// root.getAttr().setId("0");//
	// root.getAttr().setRel(Node.NODE_TYPE_ROOT);// 根节点
	// createTreeNode(list, root);
	// if (root.getChildren().size() == 0) {
	// root.setState(Node.NODE_STATE_CLOSE);
	// }
	// return JsTreeFactory.newInstance().createJsTree(root);
	//
	// for (SysDept sysDept : listDepts) {
	// Node node = new Node();
	// node.getAttr().setId(sysDept.getId() + "");
	// Data data = new Data();
	// if (sysDept.getChildrenDept().size() == 0) {// 无子节点
	// data.getAttr().setLeaf(true);// 判断是否叶子节点
	// }
	// if (sysDept.getSysDept() == null && sysDept.getChildrenDept().size() > 0)
	// {
	// node.getAttr().setRel(Node.NODE_TYPE_FOLDER);
	// }
	// if (sysDept.getSysDept() != null && sysDept.getChildrenDept().size() > 0)
	// {
	// node.getAttr().setRel(Node.NODE_TYPE_FOLDER);
	// }
	// data.setTitle(sysDept.getDeptName());
	// node.setData(data);
	// root.getChildren().add(node);
	// List<SysDept> dt = sysDeptDao.findListByFatherId(sysDept.getId());
	// if (dt.size() > 0) {
	// this.createTreeNode(dt, node);
	// }
	// }
	// }
	@Override
	public void merge(SysDept entity) {
		sysDeptDao.merge(entity);
	}

	@Override
	public void setOrder(String ids) throws ServiceException {
		String[] cids = ids.split(",");
		if (cids != null && cids.length > 0) {
			int i = 1;
			for (String id : cids) {
				if (!StringUtils.isBlank(id)) {
					SysDept dept = findById(Long.parseLong(id));
					dept.setLevel(i);// 设置级别
					update(dept);
					sysDeptDao.getSession().flush();
					i++;
				}
			}
		}
	}

	/**
	 * 
	 * 根据部门指标个数据对部门排名
	 * 
	 * @author fangbin
	 */
	@Override
	public List<Map<String, String>> deptRanking() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = sysDeptDao.deptRanking();
		} catch (SQLException e) {
			new Exception(e);
		}
		return list;
	}

	@Override
	public List<Object[]> findDeptItemListByPage(PageBean pageBean,
			Map<String, String> map, List<SortPara> sortParas) {
		return sysDeptDao.findDeptItemListByPage(pageBean, map, sortParas);
	}

	@Override
	public List<SysDept> findDeptOrderByLevel() {
		return sysDeptDao.findDeptOrderByLevel();
	}

	@Override
	public List<SysDept> findByParentId(Long id) {
		List<SysDept> depts = sysDeptDao.findListByFatherId(id);
		return depts;
	}

	@Autowired
	public void setSysDeptDao(SysDeptDao sysDeptDao) {
		this.sysDeptDao = sysDeptDao;
	}

	@Override
	public EntityHibernateDao<SysDept> getEntityDao() {
		return sysDeptDao;
	}
}