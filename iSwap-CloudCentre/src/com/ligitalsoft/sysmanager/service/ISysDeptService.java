package com.ligitalsoft.sysmanager.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.common.framework.dao.SortPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.system.SysDept;

/**
 * 部门信息SERVICE
 * 
 * @author zhangx
 * @since May 16, 2011 11:15:39 AM
 * @name com.ligitalsoft.sysmanager.service.ISysDeptService.java
 * @version 1.0
 */
public interface ISysDeptService extends IBaseServices<SysDept> {

	/**
	 * 创建部门树
	 * 
	 * @return
	 * @author zhangx
	 */
	public JSONArray depTree();
	
	/**
	 * 查询当前部门下级部门json
	 * @param id
	 * @return
	 * @author HuJun
	 */
	public JSONArray getChildDeptById(Long id);
	
	/**
	 * 根据部门id查询该部门层级结构
	 * @param id
	 * @return
	 */
	public JSONArray getDeptTreeById(Long id);

	/**
	 * 合并部门对象
	 * 
	 * @param entity
	 * @author zhangx
	 */
	public void merge(SysDept entity);

	/**
	 * 根据部门指标个数据对部门排名
	 * 
	 * @author fangbin
	 * @return
	 */
	public List<Map<String, String>> deptRanking();

	/**
	 * 查询当前部门是否有子类
	 * 
	 * @param id
	 * @return
	 */
	public List<SysDept> findByParentId(Long id);

	/**
	 * 排序
	 * 
	 * @return
	 */
	public List<SysDept> findDeptOrderByLevel();

	/**
	 * 分页显示所有的数据
	 * 
	 * @author huwanshan
	 * @date 2010-12-11 下午09:19:00
	 * @param first
	 * @param max
	 * @return
	 */
	public List<Object[]> findDeptItemListByPage(PageBean pageBean,
			Map<String, String> map, List<SortPara> sortParas);

	/**
	 * 设置部门排序
	 * 
	 * @param ids
	 * @param parentId
	 */
	public void setOrder(String ids)throws ServiceException;
}