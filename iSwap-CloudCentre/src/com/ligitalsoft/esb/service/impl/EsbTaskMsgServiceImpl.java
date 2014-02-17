package com.ligitalsoft.esb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.QueryPara;
import com.common.framework.dao.SortPara;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.framework.web.pager.PageBean;
import com.common.utils.tree.CreateTree;
import com.common.utils.tree.MenuInfo;
import com.ligitalsoft.appitemmgr.dao.AppMsgDao;
import com.ligitalsoft.esb.dao.EsbTaskMsgDao;
import com.ligitalsoft.esb.dao.EsbWorkFlowDao;
import com.ligitalsoft.esb.service.IEsbTaskMsgService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudnode.NodeTaskMsg;
import com.ligitalsoft.model.cloudnode.WorkFlow;
import com.ligitalsoft.model.esb.EsbTaskMsg;
import com.ligitalsoft.model.esb.EsbWorkFlow;
@Service("esbTaskMsgService")
public class EsbTaskMsgServiceImpl  extends BaseSericesImpl<EsbTaskMsg> implements IEsbTaskMsgService {

	@Autowired
	private EsbTaskMsgDao esbTaskMsgDao;
	@Autowired
	private AppMsgDao appMsgDao;
	@Autowired
	private EsbWorkFlowDao esbWorkFlowDao;
	@Override
	public EntityHibernateDao<EsbTaskMsg> getEntityDao() {
		
		return esbTaskMsgDao;
	}
	@Override
	public String workFlowXTree(Long checkId) {
		CreateTree tree = new CreateTree();
		MenuInfo menu = new MenuInfo();
		menu.setMenuName("业务流程");
		menu.setUrl("javascript:void(0);");
		menu.setValue("0");
		menu.setChecked("");
		List<AppMsg> appMsgList = appMsgDao.findAll(0, Integer.MAX_VALUE);
		for (AppMsg appMsg : appMsgList) {
			MenuInfo menuApp = new MenuInfo();
			menuApp.setMenuName(appMsg.getAppName());
			menuApp.setUrl("javascript:void(0);");
			menuApp.setValue("0");
			menuApp.setName("id");
			menuApp.setChecked("");
			menuApp.setOpenIcon("../../images/xtree/foldericon_chart.gif");
			menuApp.setCloseIcon("../../images/xtree/openfoldericon_chart.gif");
			List<EsbWorkFlow> workflowList = esbWorkFlowDao.getAllByAppMsg(appMsg.getId());
			if (workflowList.size() > 0) {
				menu.getChildMenu().add(menuApp);
			}
			List<MenuInfo> menuList = new ArrayList<MenuInfo>();
			for (EsbWorkFlow workflow : workflowList) {
				MenuInfo menuinfo = new MenuInfo();
				menuinfo.setMenuName(workflow.getWorkFlowName());
				menuinfo.setUrl("javascript:void(0);");
				menuinfo.setName("esbTaskMsg.workFlow.id");
				menuinfo.setValue(workflow.getId().toString());
				if (checkId != null && checkId.equals(workflow.getId())) {
					menuinfo.setChecked("checked");
				} else {
					menuinfo.setChecked("");
				}
				menuinfo.setOpenIcon("../../images/xtree/foldericon_chart.gif");
				menuinfo.setCloseIcon("../../images/xtree/openfoldericon_chart.gif");
				menuApp.getChildMenu().add(menuinfo);
			}
		}
		return tree.getRadioTree(menu);
	}

	@Override
	public List<EsbTaskMsg>  findByProperty(String propertyName, String value) throws ServiceException{
		  List<EsbTaskMsg> esb_list = new ArrayList<EsbTaskMsg>();
		  List<EsbTaskMsg> list = new ArrayList<EsbTaskMsg>();
		  String[] names = new String[1];
		  String[] values = new String[1];
		  names[0] = propertyName;
		  values[0] = value;
		  list = getEntityDao().findListByProperty(names, values, 0, Integer.MAX_VALUE);
		  for(EsbTaskMsg esbt:list){
			  EsbTaskMsg esbtask = new EsbTaskMsg();
			  EsbWorkFlow workFlow = new EsbWorkFlow();
			  esbtask.setCron(esbt.getCron());
			  esbtask.setMessage(esbt.getMessage());
			  esbtask.setTaskName(esbt.getTaskName());
			  workFlow.setWorkFlowName(esbt.getWorkFlow().getWorkFlowName());
			  workFlow.setWorkFlowCode(esbt.getWorkFlow().getWorkFlowCode());
			  esbtask.setWorkFlow(workFlow);
			  esb_list.add(esbtask);
		  }
		  return esb_list;
	}
	@Override
	public List<EsbTaskMsg> findEsbTaskMsgRightJoinWorkFlowList(
			List<QueryPara> queryParas, List<SortPara> sortParas, PageBean page)
			throws ServiceException {
		List<EsbTaskMsg> esbTaskMsglist = new ArrayList<EsbTaskMsg>();
		String mainHql = "from EsbTaskMsg e right join e.workFlow c"; 
		List list = this.findAllByPage(mainHql, queryParas, sortParas, page);
         if(list!=null&&list.size()>0){
         	for(Object obj : list){
         		Object[] objArray = (Object[])obj;
         		EsbTaskMsg esbTaskMsg = new EsbTaskMsg();
         		if(objArray[0]!=null)esbTaskMsg = (EsbTaskMsg)objArray[0];
         		if(objArray[1]!=null)esbTaskMsg.setWorkFlow((EsbWorkFlow)objArray[1]);
         		esbTaskMsglist.add(esbTaskMsg);
         	}
         }
		return esbTaskMsglist;
	}

}
