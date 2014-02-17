package com.ligitalsoft.sysmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.tree.ztree.Node;
import com.ligitalsoft.cloudcenter.dao.CloudNodeInfoDao;
import com.ligitalsoft.cloudcenter.dao.CouldNodeDeptDao;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;
import com.ligitalsoft.model.system.SysPermission;
import com.ligitalsoft.model.system.SysRolePermisson;
import com.ligitalsoft.sysmanager.dao.SysPermissionDao;
import com.ligitalsoft.sysmanager.dao.SysRolePermissonDao;
import com.ligitalsoft.sysmanager.service.ISysPermissionService;
import com.ligitalsoft.sysmanager.util.Costant;

@Service("sysPermissionService")
public class SysPermissionServiceImpl extends BaseSericesImpl<SysPermission>
		implements ISysPermissionService {

	private SysPermissionDao sysPermissionDao;
	private SysRolePermissonDao sysRolePermissonDao;
	private CloudNodeInfoDao cloudNodeInfoDao;
	private CouldNodeDeptDao couldNodeDeptDao;

	@Transactional(readOnly = true)
	public List<SysPermission> findListByRoleId(Long roleId, Long permissionId) {
		List<SysPermission> fathers = sysPermissionDao.findListByRoleId(roleId,
				permissionId);// 查询所有父类菜单
		return fathers;
	}

	@Transactional(readOnly = true)
	public JSONArray permissionTree(Long roleId) {
		// 创建节点
		List<Node> nodes = new ArrayList<Node>();
		List<SysPermission> permissions = null;
		permissions = sysPermissionDao.findListByHql("from SysPermission p where p.sysCode=? and p.enabled=1", Costant.SYS_CODE);
					//findByProperty(new Object[]{"sysCode", "enabled"},new Object[]{Costant.SYS_CODE, "1"});
		if(permissions==null||permissions.size()<=0)return null;
		List<SysRolePermisson> rolePermissons = new ArrayList<SysRolePermisson>();
		if (roleId != null && roleId != 0L) {
			rolePermissons = sysRolePermissonDao.findPermissionByRoleId(roleId);
		}
		Node root = new Node();
		root.setName("菜单项");
		root.setId(-1L + "");
		root.setPid(0L + "");
		root.setOpen(true);
		nodes.add(root);
		for (SysPermission sysPermission : permissions) {
			Node node = new Node();
			node.setId(sysPermission.getId() + "");
			if (sysPermission.getPermission() == null) {
				node.setPid(-1L + "");
			} else {
				node.setPid(sysPermission.getPermission().getId() + "");
			}
			if (rolePermissons.size() > 0) {
				for (SysRolePermisson sysRolePermisson : rolePermissons) {
					if (sysRolePermisson.getPermissionId().equals(
							sysPermission.getId())) {
						node.setChecked(true);
						break;
					}
				}
			}
			node.setName(sysPermission.getMenuName());
			nodes.add(node);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("open") || name.equals("pid")
						|| name.equals("checked")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(nodes, jsonConfig);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SysPermission> findListByFatherId(Long fatherId) {
		return sysPermissionDao.findListByFatherId(fatherId);
	}

	/**
	 * 创建菜单xml
	 * 
	 * @param roleId角色ID
	 * @return
	 * @author zhangx
	 */
	@Transactional(readOnly = true)
	public Document createPermissionXml(Long roleId) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("menu");
		List<SysPermission> list = sysPermissionDao
				.findListRootByRoleId(roleId);// 找到所有根节点
		createDataXml(root, list, roleId);
		return document;
	}

	/**
	 * 递归创建菜单
	 * 
	 * @param root
	 * @param list
	 * @author zhangx
	 */
	@Transactional(readOnly = true)
	public void createDataXml(Element root, List<SysPermission> list,
			Long roleId) {
		int num = 1;
		for (SysPermission sysPermission : list) {
			if(sysPermission.getEnabled().equals(0))break;
			Element element = root.addElement("menu"
					+ sysPermission.getMenuLevel());
			SysPermission permission = sysPermission.getPermission();
			if (permission == null) { // 控制一级标签
				element.addAttribute("tag", num + "");
				element.addAttribute("name", sysPermission.getMenuName());
				element.addAttribute("url", sysPermission.getUrl());
				if (num == 1) {
					element.addAttribute("default", "y");
				} else {
					element.addAttribute("default", "");
				}
				num++;
				/*
				 * if(!StringUtils.isBlank(sysPermission.getDefaults())){
				 * element.addAttribute("default", sysPermission.getDefaults());
				 * }
				 */
				element.addAttribute("default", "y");
			} else {
				String tag = root.attributeValue("tag").toString();
				tag += "_" + sysPermission.getLevlel();
				element.addAttribute("tag", tag);
				element.addAttribute("name", sysPermission.getMenuName());
				element.addAttribute("url", sysPermission.getUrl());
				if (!StringUtils.isBlank(sysPermission.getDefaults())) {
					element.addAttribute("default", sysPermission.getDefaults());
				}
			}
			List<SysPermission> cList = sysPermissionDao.findListByRoleId(
					roleId, sysPermission.getId());
			if (cList != null && cList.size() > 0) {
				createDataXml(element, cList, roleId);
			}
		}
	}

	/**
	 * 判断同级菜单是否有默认菜单
	 * 
	 * @param permissions
	 * @return
	 */
	public boolean isDefault(List<SysPermission> permissions) {
		boolean fa = false;
		for (SysPermission sysPermission : permissions) {
			if (!StringUtils.isBlank(sysPermission.getDefaults())
					&& sysPermission.getDefaults().equals("y")) {
				fa = true;
				break;
			}
		}
		return fa;
	}

	@Override
	public JSONArray cloudNodeMenu(Long id, Long deptId) {
		List<CloudNodeInfo> list = null;
		if (null == deptId) {/* 当前部门为信息中心部门 */
			list = cloudNodeInfoDao.findAll(0, Integer.MAX_VALUE);
		} else {
			list = couldNodeDeptDao.findListByDeptId(deptId);
		}

		List<Node> nodes = new ArrayList<Node>();
		 SysPermission permission = sysPermissionDao.findById(id);
		 Node rootNode = new Node();
		 rootNode.setName(permission.getMenuName());
		 rootNode.setId(id.toString());
		 rootNode.setPid("0");
		 rootNode.setOpen(true);
		 nodes.add(rootNode);
		for (CloudNodeInfo cloudNodeInfo : list) {
			List<CouldNodeDept> depts = cloudNodeInfo.getNodeDept();
			if (null != depts && depts.size() > 0) {
				for (CouldNodeDept couldNodeDept : depts) {
					Node node = new Node();
					node.setIsParent(true);
					node.setPid(id.toString());
					/* 一级菜单ID_deptID */
					node.setId(id + "_" + couldNodeDept.getDept().getId());
					node.setName(couldNodeDept.getDept().getDeptName());
					nodes.add(node);
				}
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("isParent")
						|| name.equals("id") || name.equals("pid")
						|| name.equals("level") || name.equals("open")) {
					return false;
				} else {
					return true;
				}
			}
		});
		JSONArray deptTree = JSONArray.fromObject(nodes, jsonConfig);
		return deptTree;
	}

	@Override
	public JSONArray cloudNodeChildrenMenu(String parentId, String sysNum, Long roleId) {
		List<SysPermission> lst = new ArrayList<SysPermission>();
		List<Node> nodes = new ArrayList<Node>();
		String args[] = parentId.split("_");
		switch (args.length) {
		case 1: {
			SysPermission permiss = sysPermissionDao.findById(new Long(parentId));
			Node rootNode = new Node();
			rootNode.setName(permiss.getMenuName());
			rootNode.setId(parentId.toString());
			rootNode.setPid("0");
			rootNode.setOpen(true);
			nodes.add(rootNode);
			
			if(roleId!=null){
				lst = findListByRoleId(roleId, Long.parseLong(args[0]));
			}else{
				lst = findListByFatherId(Long.parseLong(args[0]));
			}
			for (SysPermission permission : lst) {
				if(permission.getEnabled().equals(0))break;
				Node node = new Node();
				node.setName(permission.getMenuName());
				node.setId(parentId + "_" + permission.getId());
				node.setPid(parentId);
				node.setUri(permission.getUrl());
				if ("3".equals(sysNum)) {
					node.setIsParent(true);
					if (permission.getChildrenPermission() == null
							|| permission.getChildrenPermission().size() <= 0) {
						node.setData("");
					}
				}

				nodes.add(node);
			}
			break;
		}
		case 2: /* 一级菜单ID_dpetID */
			lst = findListByRoleId(roleId, Long.parseLong(args[0]));
			for (SysPermission permission : lst) {
				Node node = new Node();
				node.setName(permission.getMenuName());
				node.setId(parentId + "_" + permission.getId());
				node.setPid(parentId);
				node.setIsParent(true);
				nodes.add(node);
			}
			break;
		case 3: /* 一级菜单ID_deptID_二级菜单Id */
			lst = findListByRoleId(1L, Long.parseLong(args[2]));
			for (SysPermission permission : lst) {
				Node node = new Node();
				node.setName(permission.getMenuName());
				node.setId(parentId + "_" + permission.getId());
				node.setPid(parentId);
				node.setUri(permission.getUrl() + "?deptId=" + args[1]);
				nodes.add(node);
			}
			break;
		default:
			break;
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("isParent")
						|| name.equals("data") || name.equals("uri")) {
					return false;
				} else {
					return true;
				}
			}
		});
		JSONArray tree = JSONArray.fromObject(nodes, jsonConfig);
		return tree;
	}

	private void getChildrenMenu(List<Node> nodes,
			List<SysPermission> permission, Long roleId) {
		for (SysPermission sysPermission : permission) {
			if(sysPermission.getEnabled().equals(0))break;
			Node node = new Node();
			node.setUri(sysPermission.getUrl());
			node.setId(sysPermission.getId().toString());
			node.setPid(sysPermission.getPermission().getId().toString());
			node.setName(sysPermission.getMenuName());
			nodes.add(node);
			List<SysPermission> lst = findListByRoleId(roleId,
					sysPermission.getId());
			if (null != lst && lst.size() > 0) {
				getChildrenMenu(nodes, lst, roleId);
			}
		}
	}

	private void menuRecursive(List<SysPermission> lst, SysPermission sysPermission){
		lst.add(sysPermission);
		if(sysPermission.getChildrenPermission()!=null&&sysPermission.getChildrenPermission().size()>0){
			for(SysPermission sysPerm : sysPermission.getChildrenPermission()){
				menuRecursive(lst, sysPerm);
			}
		}
	}
	
	public JSONArray centerControlMenu(Long parentId, Long roleId) {
		List<Node> nodes = new ArrayList<Node>();
		List<SysPermission> lst = null; 
		if(roleId!=null){
			lst = findListByRoleId(roleId, parentId);
		}else{
			lst = sysPermissionDao.findListByFatherId(parentId);
			/*List<SysPermission> sysPermissionList = new ArrayList<SysPermission>();
			if(lst!=null&&lst.size()>0){
				for(SysPermission sysPerm : lst){
					menuRecursive(sysPermissionList, sysPerm);
				}
				lst.addAll(sysPermissionList);
			}*/
		}
		
		 SysPermission permission = sysPermissionDao.findById(parentId);
		 Node rootNode = new Node();
		 rootNode.setName(permission.getMenuName());
		 rootNode.setId(parentId.toString());
		 rootNode.setPid("0");
		 rootNode.setOpen(true);
		 nodes.add(rootNode);
		for (SysPermission sysPermission : lst) {
			if(sysPermission.getEnabled().equals(0))break;
			Node node = new Node();
			node.setId(sysPermission.getId().toString());
			node.setName(sysPermission.getMenuName());
			node.setUri(sysPermission.getUrl());
			node.setPid(parentId+ "");
			nodes.add(node);
			List<SysPermission> ls = null;
			if(roleId!=null){
				ls = findListByRoleId(roleId,
					sysPermission.getId());
			}else{
				ls = sysPermissionDao.findListByFatherId(sysPermission.getId());
			}
			if (null != ls && lst.size() > 0) {
				getChildrenMenu(nodes, ls, roleId);
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("data")
						|| name.equals("uri")||name.equals("open")) {
					return false;
				} else {
					return true;
				}
			}
		});
		JSONArray tree = JSONArray.fromObject(nodes, jsonConfig);
		System.out.println(tree.toString());
		return tree;
	}

	public List<SysPermission> findListByRoleId(Long roleId) {
		return sysPermissionDao.findListByRoleId(roleId);
	}

	@Override
	public List<SysPermission> findListExcludeByRoleId(Long roleId) {
		return sysPermissionDao.findListExcludeByRoleId(roleId);
	}

	public static void main(String[] args) {

		Document document = DocumentHelper.createDocument();

		Element element2 = document.addElement("menu2");
		element2.addAttribute("tag", "1");
		element2.addAttribute("name", "2");
		element2.addAttribute("url", "3");

		Element element3 = element2.addElement("menu2");
		element3.addAttribute("tag", "2");
		element3.addAttribute("name", "3");
		element3.addAttribute("url", "4");

		Element element = element3.addElement("menu1");

		element.addAttribute("tag", "3");
		element.addAttribute("name", "4");
		element.addAttribute("url", "5");
		//
		Element element4 = document.addElement("test");
		element4.addAttribute("tag", "3");
		element4.addAttribute("name", "4");
		element4.addAttribute("url", "5");
		element.setParent(element4);
	}

	public List<SysPermission> findListRootByRoleId(Long roleId) {

		return sysPermissionDao.findListRootByRoleId(roleId);
	}

	@Override
	public List<SysPermission> findListRoot() {
		return sysPermissionDao.findListRoot();
	}

	@Autowired
	public void setSysPermissionDao(SysPermissionDao sysPermissionDao) {
		this.sysPermissionDao = sysPermissionDao;
	}

	@Autowired
	public void setSysRolePermissonDao(SysRolePermissonDao sysRolePermissonDao) {
		this.sysRolePermissonDao = sysRolePermissonDao;
	}

	@Autowired
	public void setCloudNodeInfoDao(CloudNodeInfoDao cloudNodeInfoDao) {
		this.cloudNodeInfoDao = cloudNodeInfoDao;
	}

	@Autowired
	public void setCouldNodeDeptDao(CouldNodeDeptDao couldNodeDeptDao) {
		this.couldNodeDeptDao = couldNodeDeptDao;
	}

	@Override
	public EntityHibernateDao<SysPermission> getEntityDao() {
		return sysPermissionDao;
	}
}