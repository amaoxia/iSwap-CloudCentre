package com.ligitalsoft.datasharexchange.service;

import java.util.List;


import net.sf.json.JSONArray;

import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.SendItemDept;

public interface ISendItemDeptService extends IBaseServices<SendItemDept> {
	/**
	 * 根据部门Id来查询路由配置
	 * 
	 * @param DEPTID
	 * @return
	 */
	public List<SendItemDept> findListDeptId(Long deptId);

	/**
	 * 根据部ITEMID来查询路由配置
	 * 
	 * @param deptId
	 * @return
	 */
	public List<SendItemDept> findListItemId(Long itemId);

	/**
	 * 当前部门ID
	 * 
	 * @param deptId
	 * @return
	 */
	public JSONArray getDetpItemTree(Long deptId, Long id);

	/**
	 * 保存对象
	 * 
	 * @param ids
	 * @param deptId
	 */
	public void saveDeptItem(Long id, String deptIds, String deptNames,
			int deptCount, String itemIds, int itemCounts);


	/**
	 * 添加
	 * 
	 * @param tids
	 */
	public void add(String tids);

	public JSONArray deptExcludeTree();
	
	/**
	 * 根据部门id获得该部门需求的指标项列表
	 * @return
	 * @author HuJun
	 */
	public List<ChangeItem> getChangeItemListById(SendItemDept sendItemDept, PageBean page);
	
	/**
	 * 根据部门及对应指标项ids删除其指标项关系信息
	 * @param sendItemDept
	 * @param itemids
	 * @return
	 * @author HuJun
	 */
	public int delChangeItemById(SendItemDept sendItemDept, String[] itemsArray);
	
	/**
	 * 保存部门与指标项关系信息
	 * @param sendItemDept
	 * @param items
	 * @author HuJun
	 */
	public void addSendItem(Long deptId, String[] idsArray);

}
