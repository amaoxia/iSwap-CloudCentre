package com.ligitalsoft.datasharexchange.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.framework.web.pager.PageBean;
import com.common.utils.common.StringUtils;
import com.common.utils.tree.ztree.Node;
import com.ligitalsoft.cloudstorage.dao.MetaDataDao;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDao;
import com.ligitalsoft.datasharexchange.dao.SendItemDeptDao;
import com.ligitalsoft.datasharexchange.service.ISendItemDeptService;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.SendItemDept;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.dao.SysDeptDao;

@Service("sendItemDeptService")
public class SendItemDeptServiceImpl extends BaseSericesImpl<SendItemDept>

implements ISendItemDeptService {
	private SendItemDeptDao sendItemDeptDao;
	private ChangeItemDao changeItemDao;
	private SysDeptDao sysDeptDao;
	private MetaDataDao metaDataDao;

	public List<SendItemDept> findListDeptId(Long deptId) {
		return sendItemDeptDao.findListDeptId(deptId);
	}

	public List<SendItemDept> findListItemId(Long itemId) {
		return sendItemDeptDao.findListItemId(itemId);
	}

	public JSONArray deptExcludeTree() {
		List<Node> nodes = new ArrayList<Node>();
		List<SysDept> depts = sysDeptDao.findDeptOrderByLevel();
		List<SendItemDept> itemDepts = sendItemDeptDao.findAll(0,
				Integer.MAX_VALUE);
		Node root = new Node();
		root.setName("部门");
		root.setId(-1 + "");
		root.setPid(0 + "");
		root.setOpen(true);
		nodes.add(root);
		for (SysDept dept : depts) {
			if (!isEquals(dept, itemDepts)) {
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
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
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

	private boolean isEquals(SysDept dept, List<SendItemDept> itemDepts) {
		boolean falg = false;
		if (itemDepts != null && itemDepts.size() > 0) {
			for (SendItemDept sendItemDept : itemDepts) {
				if (sendItemDept.getSysDept().getId().toString()
						.equals(dept.getId().toString())) {
					falg = true;
					break;
				}
			}
		}
		return falg;
	}

	public JSONArray getDetpItemTree(Long deptId, Long id) {
		List<SysDept> depts = sysDeptDao.findAll(-1, -1);
		SysDept dept = sysDeptDao.findById(deptId);
		// List<SendItemDept> itemDepts =
		// sendItemDeptDao.findListDeptId(deptId);
		// SysDept dept = sysDeptDao.findById(deptId);//
		// String[] dIds = null;
		// if (dept != null) {
		// String deptIds = dept.getDeptIds();
		// if (!StringUtils.isBlank(deptIds)) {
		// dIds = deptIds.split(",");
		// }
		// }
		List<Node> node = new ArrayList<Node>();//
		//Node root = new Node();
		//root.setName("需求目录");
		//root.setId("-01");
		//root.setPid("0");
		//root.setOpen(true);
		//node.add(root);
		for (SysDept sysDept : depts) {
			if (dept.getId() == sysDept.getId()) {
				/*List<ChangeItem> items = changeItemDao.findListByDeptId(sysDept
						.getId());// 当前部门下所有指标
				if (items != null && items.size() > 0) {
					Node deptNode = new Node();
					deptNode.setId(-sysDept.getId() + "");
					deptNode.setName(sysDept.getDeptName());
					deptNode.setPid("-01");
					deptNode.setChecked(true);
					node.add(deptNode);
					for (ChangeItem item : items) {
						Node itemNode = new Node();
						itemNode.setId(item.getId() + "");
						itemNode.setName(item.getItemName());
						itemNode.setPid(-sysDept.getId() + "");
						itemNode.setChecked(true);
						node.add(itemNode);
					}
				}*/
			} else {
				List<ChangeItem> items = changeItemDao.findListByDeptId(sysDept.getId());// 当前部门下所有指标
				//List<MetaData> items = metaDataDao.getAllByDeptId(sysDept.getId()+"");// 当前部门下所有指标
				Node deptNode = new Node();
				deptNode.setId(-sysDept.getId() + "");
				deptNode.setName(sysDept.getDeptName());
				deptNode.setPid(sysDept.getSysDept()!=null?-sysDept.getSysDept().getId()+"":"0");
				node.add(deptNode);
				if (items != null && items.size() > 0) {
					for (ChangeItem item : items) {
						Node itemNode = new Node();
						itemNode.setId(item.getId() + "");
						itemNode.setName(item.getItemName());
						itemNode.setPid(-sysDept.getId() + "");
						node.add(itemNode);
					}
				}else{
					if(!"0".equals(deptNode.getPid())){
						deptNode.setNocheck(true);
					}
				}
				
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {

			public boolean apply(Object source, String name, Object value) {
				if (name.equals("name") || name.equals("id")
						|| name.equals("pid") || name.equals("open")
						|| name.equals("checked")|| name.equals("nocheck")) {
					return false;
				} else {
					return true;
				}
			}
		});
		return JSONArray.fromObject(node, jsonConfig);
	}

	public void saveDeptItem(Long id, String deptIds, String deptNames,
			int deptCount, String itemIds, int itemCounts) {
		SendItemDept sendItemDept = sendItemDeptDao.findById(id);
		sendItemDept.setDeptCount(deptCount);
		sendItemDept.setDeptIds(deptIds);
		sendItemDept.setDeptNames(deptNames);
		sendItemDept.setItemIds(itemIds);
		sendItemDept.setItemCount(itemCounts);
		sendItemDeptDao.update(sendItemDept);
	}

	

	public void add(String tids) {
		String[] ids = tids.split(",");
		for (String id : ids) {
			if (!StringUtils.isBlank(id)) {
				SendItemDept sendItemDept = new SendItemDept();
				SysDept dept = sysDeptDao.findById(Long.parseLong(id));
				sendItemDept.setSysDept(dept);
				sendItemDeptDao.saveOrUpdate(sendItemDept);
				sendItemDeptDao.getSession().flush();
			}
		}
	}
	
	public void addSendItem(Long deptId, String[] idsArray) {
		SysDept sysDept = new SysDept();
		sysDept.setId(deptId);
		
		/** 删除部门对应指标项 **/
		sendItemDeptDao.deleteByDeptId(deptId);
		
		for(String itemid : idsArray){
			if(!StringUtils.isBlank(itemid)){
				ChangeItem changeItem = new ChangeItem();
				changeItem.setId(new Long(itemid));
				
				SendItemDept sendItemDept = new SendItemDept();
				sendItemDept.setSysDept(sysDept);
				sendItemDept.setChangeItem(changeItem);
				
				sendItemDeptDao.saveOrUpdate(sendItemDept);
			}
		}
	}

	@Override
	public List<ChangeItem> getChangeItemListById(SendItemDept sendItemDept, PageBean page) {
		List<SendItemDept> sendItemDeptList = findListDeptId(sendItemDept.getSysDept().getId());
		List<ChangeItem> changeItemList = new ArrayList<ChangeItem>();
		/** 这里所查部门数应该只有一个 **/
		if(sendItemDeptList!=null&&sendItemDeptList.size()>0){
			sendItemDept = sendItemDeptList.get(0);
			String itemIds = sendItemDept.getItemIds();
			if(itemIds!=null&&!"".equals(itemIds)){
				if(itemIds.endsWith(","))itemIds = itemIds.substring(0,itemIds.length()-1);
				changeItemList = changeItemDao.findListByIds(itemIds, page);
			}
		}
		return changeItemList;
	}
	
	@Override
	public int delChangeItemById(SendItemDept sendItemDept,
			String[] itemsArray) {
		int count = 0;
		List<SendItemDept> sendItemDeptList = findListDeptId(sendItemDept.getSysDept().getId());
		if(sendItemDeptList!=null&&sendItemDeptList.size()>0){
			sendItemDept = sendItemDeptList.get(0);
			String itemIds = sendItemDept.getItemIds();
			if(itemIds!=null&&!"".equals(itemIds)){
				String[] idsArray = itemIds.split(",");
				Set<String> idsSet = new HashSet<String>();    
				CollectionUtils.addAll(idsSet, idsArray);   
				
				for(String itemid : itemsArray){
					if(idsSet.contains(itemid)){
						idsSet.remove(itemid);
						count++;
					}
				}
				String ids = "";
				for(String id : idsSet){
					ids += id + ",";
				}
				sendItemDept.setItemIds(ids);
				
				sendItemDeptDao.update(sendItemDept);
			}
		}
		return count;
	}
	
	public EntityHibernateDao<SendItemDept> getEntityDao() {
		return sendItemDeptDao;
	}

	@Autowired
	public void setChangeItemDao(ChangeItemDao changeItemDao) {
		this.changeItemDao = changeItemDao;
	}

	@Autowired
	public void setSysDeptDao(SysDeptDao sysDeptDao) {
		this.sysDeptDao = sysDeptDao;
	}

	@Autowired
	public void setSendItemDeptDao(SendItemDeptDao sendItemDeptDao) {
		this.sendItemDeptDao = sendItemDeptDao;
	}

	@Autowired
	public void setMetaDataDao(MetaDataDao metaDataDao) {
		this.metaDataDao = metaDataDao;
	}

}
