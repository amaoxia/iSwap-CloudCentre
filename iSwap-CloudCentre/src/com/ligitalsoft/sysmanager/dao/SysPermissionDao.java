package com.ligitalsoft.sysmanager.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.ligitalsoft.model.system.SysPermission;
import com.ligitalsoft.sysmanager.util.Costant;

/**
 * 菜单DAO
 * @author zhangx
 * @since May 16, 2011 11:03:41 AM
 * @name com.ligitalsoft.sysmanager.dao.SysPermissionDao.java
 * @version 1.0
 */
@Repository
public class SysPermissionDao extends EntityHibernateDao<SysPermission> {

    /**
     * 根据ID,角色ID查询菜单
     * @param roleId
     *            角色ID
     * @param menuLevle
     *            菜单顺序
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<SysPermission> findListByRoleId(Long roleId, Integer menuLevle) {
        String hql = "SELECT p from SysPermission p,SysRolePermisson e where e.permissionId=p.id and e.roleId=? and p.menuLevel=?";
        Object[] obj = { roleId, menuLevle };
        return powerHibernateDao.findListByHql(hql, obj);
    }

    /**
     * 根据ID,角色ID查询菜单
     * @param roleId
     *            角色ID
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<SysPermission> findListByRoleId(Long roleId) {
        String hql = "SELECT p from SysPermission p,SysRolePermisson e where e.permissionId=p.id and e.roleId=?";
        return powerHibernateDao.findListByHql(hql, roleId);
    }
    /**
     * 查询某权限下菜单
     * @param roleId
     * @param permissionId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<SysPermission> findListByRoleId(Long roleId, Long permissionId) {
    	List<SysPermission> sysPermissionList = new ArrayList<SysPermission>();
    	String hql = " from SysPermission p,SysRolePermisson e where p.id=e.permissionId and e.roleId=? and p.permission.id=? and p.enabled=1 order by  levlel asc";
        Object[] obj = { roleId, permissionId };
        List<SysPermission> lst = powerHibernateDao.findListByHql(hql, obj);
        if(lst!=null&&lst.size()>0){
        	for(Object object : lst){
        		if(object instanceof Object[]){
        			sysPermissionList.add((SysPermission)(((Object[])object)[0]));
        		}else{
        			sysPermissionList = lst;
        			break;
        		}
        	}
        }
        
        return sysPermissionList;
    }
    /**
     * 查询菜单下的子结构
     * @param fatherId
     *            父类ID
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<SysPermission> findListByFatherId(Long fatherId) {
        String hql = "SELECT p from SysPermission p where p.permission.id =?";
        return powerHibernateDao.findListByHql(hql, fatherId);
    }
    /**
     * 查找根菜单
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<SysPermission> findListRoot() {
        String hql = "from SysPermission p where p.enabled=1 and p.sysCode=? and p.permission.id is null order by p.levlel";
        return powerHibernateDao.findListByHql(hql, Costant.SYS_CODE);
    }
    /**
     * 查询菜单下的子结构 显示部分字段
     * @param fatherId
     *            父类ID
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> findListByFatherIdPart(Long fatherId) {
        String hql = "SELECT p.id,p.menuName,p.permission.id from SysPermission p where p.permission.id =? ";
        return powerHibernateDao.findListByHql(hql, fatherId);
    }
    /**
     * 查找根菜单 显示部分字段
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> findListRootByPart() {
        String hql = "SELECT p.id,p.menuName,p.permission.id from SysPermission p where p.permission.id is null";
        return powerHibernateDao.findListByHql(hql);
    }
    /**
     * 根据角色Id查找根菜单
     * @param roleId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<SysPermission> findListRootByRoleId(Long roleId) {
        String hql = "SELECT p from SysPermission p,SysRolePermisson e where p.id= e.permissionId and e.roleId=? and p.enabled=1 and p.sysCode=? and p.permission.id is null order by p.levlel asc";
        return powerHibernateDao.findListByHql(hql, roleId, Costant.SYS_CODE);
    }
    /**
     * 查询当前角色没有权限拥有的菜单
     * @param roleId
     * @return
     * @author zhangx
     */
    @SuppressWarnings("unchecked")
    public List<SysPermission> findListExcludeByRoleId(Long roleId) {
        String hql="select e from SysPermission e where e.id not in(select permissionId from SysRolePermisson where roleId=?)";
        return powerHibernateDao.findListByHql(hql, roleId);
    }
}