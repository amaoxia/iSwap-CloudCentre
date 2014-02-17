package com.ligitalsoft.sysmanager.service;

import java.util.List;

import net.sf.json.JSONArray;

import org.dom4j.Document;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.system.SysPermission;

/**
 * 权限菜单SERVICE
 * 
 * @author zhangx
 * @since May 16, 2011 11:23:49 AM
 * @name com.ligitalsoft.sysmanager.service.ISysPermissionService.java
 * @version 1.0
 */
public interface ISysPermissionService extends IBaseServices<SysPermission> {

	/**
	 * 创建树形菜单
	 * 
	 * @return
	 * @author zhangx
	 */
	public JSONArray permissionTree(Long roleId);

	/**
	 * 根据角色查询对应的菜单
	 * 
	 * @param PermissionId
	 *            菜单ID
	 * @param roleId
	 * @return
	 * @author zhangx
	 */
	public List<SysPermission> findListByRoleId(Long roleId, Long PermissionId);

	/**
	 * 根据父类ID查询列表
	 * 
	 * @param fatherId
	 * @return
	 * @author zhangx
	 */
	public List<SysPermission> findListByFatherId(Long fatherId);

	/**
	 * 创建菜单document对象
	 * 
	 * @param roleId
	 * @return
	 * @author zhangx
	 */
	public Document createPermissionXml(Long roleId);

	/**
	 * 权限对应对象的菜单列表
	 * 
	 * @param roleId
	 * @return
	 * @author zhangx
	 */
	public List<SysPermission> findListByRoleId(Long roleId);

	/**
	 * 查询当前角色没有权限拥有的菜单
	 * 
	 * @param roleId
	 * @return
	 * @author zhangx
	 */
	public List<SysPermission> findListExcludeByRoleId(Long roleId);

	/**
	 * 查询所有根节点
	 * 
	 * @return
	 */
	public List<SysPermission> findListRoot();
	
	/**
	 * 根据角色查询权限菜单根节点
	 * @param roleId
	 * @return
	 */
	public List<SysPermission> findListRootByRoleId(Long roleId);
	
	/**
	 *  生成前置机菜单树
	 * @return
	 */
	public JSONArray cloudNodeMenu(Long id,Long deptId);
	
	/**
	 * 前置机子菜单
	 * @param parentId
	 * @return
	 */
	public JSONArray cloudNodeChildrenMenu(String parentId, String sysNum, Long roleId);
	
	/**
	 * 中心控制菜单
	 * @param id
	 * @return
	 */
	public JSONArray centerControlMenu(Long parentId,Long roleId);
}