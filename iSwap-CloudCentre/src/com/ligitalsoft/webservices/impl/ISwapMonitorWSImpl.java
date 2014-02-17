package com.ligitalsoft.webservices.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.common.utils.common.StringUtils;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudcenter.service.AppCloudNodeService;
import com.ligitalsoft.cloudcenter.service.CloudNodeInfoService;
import com.ligitalsoft.cloudstorage.service.IMetaDataAppMsgService;
import com.ligitalsoft.cloudstorage.service.IMetaDataService;
import com.ligitalsoft.datasharexchange.service.IChangeItemService;
import com.ligitalsoft.datasharexchange.service.IExchangeSendTaskService;
import com.ligitalsoft.datasharexchange.service.IReceTaskService;
import com.ligitalsoft.datasharexchange.service.ISendResultService;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ReceiveResult;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.cloudstorage.MetaData;
import com.ligitalsoft.model.cloudstorage.MetaDataAppMsg;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.webservices.ISwapMonitorWS;

@WebService
@Service("iSwapMonitor")
@Transactional
public class ISwapMonitorWSImpl implements ISwapMonitorWS {
	@Autowired
	private AppMsgService appMsgService;
	@Autowired
	private CloudNodeInfoService cloudNodeInfoService;
	@Autowired
    private AppCloudNodeService appCloudNodeService;
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private IChangeItemService changeItemService;
	@Autowired
	private ISendResultService sendResultService;
	@Autowired
	private IExchangeSendTaskService exchangeSendTaskService;
	@Autowired
	private IReceTaskService receTaskService;
	@Autowired
	private IMetaDataService metaDataService;
	@Autowired
	private IMetaDataAppMsgService metaDataAppMsgService;
	/**
	 * 
	 * 获得所有应用
	 * @author fangbin
	 * @return 
	 */
	@Override
	public String getAllApp() {
		List<AppMsg> appList= appMsgService.findAll();
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <root>";
		for(AppMsg app:appList){
			xml+="<app id=\""+app.getId()+"\" name=\""+app.getAppName()+"\"/>";
		}
		xml+="</root>";
		return xml;
	}

	/**
	 * 
	 * 获得所有云端节点
	 * @author fangbin
	 * @param appId
	 * @return
	 */
	@Override
	public String getCloudNode(String appId) {
		InetAddress address;
		List<CloudNodeInfo> cniList=new ArrayList<CloudNodeInfo>();
		if(!StringUtils.isBlank(appId)){
			cniList=appCloudNodeService.findNodeInfoListByApp(Long.parseLong(appId));
		}else{
			cniList=cloudNodeInfoService.findAll();
		}
		
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <root>";
		for(CloudNodeInfo cni:cniList){
			String bool="0";
			try {
				//判断服务是否正常运行
				address = InetAddress.getByName(cni.getAddress());
				bool=address.isReachable(Integer.parseInt(cni.getPort()))?"1":"0";
			} catch (Exception e) {
				 bool="0";
				e.printStackTrace();
			}
			
			xml+="<cloudNode id=\""+cni.getId()+"\" name=\""+cni.getNodesName()+"\" ip=\""+cni.getAddress()+"\" port=\""+cni.getPort()+"\" status=\""+bool+"\"/>";
		}
		xml+="</root>";
		return xml;
	}
	
	/**
	 * 
	 * 数据共享情况
	 * 
	 * @author fangbin
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public String dataShare(String startDate, String endDate) {
		List<SysDept> deptList=sysDeptService.findAll();
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <root>";
		for(SysDept dept:deptList){
			int i=0;
			int total=0;
			List<ChangeItem> cilist=changeItemService.findListByDeptId(dept.getId());
			for(ChangeItem ci:cilist){
				Map<String,String> map=new HashMap<String,String>();
				Map<String,String> mapTotal=new HashMap<String,String>();
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				map.put("itemId", ci.getId().toString());
				i+=sendResultService.getDataNum(map);
				mapTotal.put("itemId", ci.getId().toString());
				total+=sendResultService.getDataNum(mapTotal);
			}
			xml+="<target deptname=\""+dept.getDeptName()+"\" targetsize=\""+cilist.size()+"\" dataTotal=\""+total+"\" newData=\""+i+"\"/>";
			
		}
		xml+="</root>";
		return xml;
	}
	
	/**
	 * 数据共享详情
	 * 
	 * @author fangbin
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public String dataShareInfo(String deptId, String startDate, String endDate) {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <root>";
		List<ChangeItem> cilist=changeItemService.findListByDeptId(Long.parseLong(deptId));
		for(ChangeItem ci:cilist){
			Map<String,String> map=new HashMap<String,String>();
			Map<String,String> mapTotal=new HashMap<String,String>();
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("itemId", ci.getId().toString());
			mapTotal.put("itemId", ci.getId().toString());
			int newData=sendResultService.getDataNum(map);
			int totalData =sendResultService.getDataNum(mapTotal);
			xml+="<target targetName=\""+ci.getItemName()+"\" dataTotal=\""+totalData+"\" newData=\""+newData+"\"/>";
		}
		xml+="</root>";
		return xml;
	}
	
	/**
	 * 数据共享排名——部门
	 * @author fangbin
	 * 
	 */
	@Override
	public String deptDataShare() {
		List<Map<String,String>> deptList=sysDeptService.deptRanking();
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <root>";
		for(Map<String,String> dept:deptList){
			xml+="<dept id=\""+dept.get("deptId")+"\" deptName=\""+dept.get("deptName")+"\" targetsize=\""+dept.get("targetsize")+"\"/>";
		}
		xml+="</root>";
		return xml;
	}
	/**
	 * 
	 * 数据共享排名——指标
	 * @author fangbin
	 */
	@Override
	public String targetDataShare(String deptId) {
		List<Map<String,String>> maplist=changeItemService.targetDataShare(deptId);
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <root>";
		for(Map<String,String> target:maplist){
			xml+="<target id=\""+target.get("targetId")+"\" targetName=\""+target.get("targetName")+"\" ontimeCount=\""+target.get("ontimeCount")+"\" dataNum=\""+target.get("dataNum")+"\"/>";
		}
		xml+="</root>";
		return xml;
	}
	
	/**
	 * 数据发送任务监控
	 * @author fangbin
	 */
	@Override
	public String dataSendTask(String appId) {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <root>";
		String greenXml="";
		String yellowXml="";
		String redXml="";
		Date now=new Date();
		String date=DateUtils.format(now, "yyyy-MM-dd");
		List<Object[]> objList=exchangeSendTaskService.getSendDeptList(date, date, appId);
		for(Object[] obj:objList){
			if("3".equals(obj[2])){
				redXml+="<deng id=\""+obj[0]+"\" name=\""+obj[1]+"\"/> ";
			}
			if("2".equals(obj[2])){
				yellowXml+="<deng id=\""+obj[0]+"\" name=\""+obj[1]+"\"/> ";
			}
			if("1".equals(obj[2])){
				greenXml+="<deng id=\""+obj[0]+"\" name=\""+obj[1]+"\"/> ";
			}
			
		}
		xml+="<hong_deng>"+redXml+"</hong_deng>";
		xml+="<huang_deng>"+yellowXml+"</huang_deng>";
		xml+="<lv_deng>"+greenXml+"</lv_deng>";
		xml+="</root>";
		return xml;
	}
	
	/**
	 * 获得所有部门
	 * @author fangbin
	 */
	@Override
	public String getAllDept() {
		List<SysDept> deptList=sysDeptService.findAll();
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>";
		for(SysDept dept:deptList){
			xml+="<dept id=\""+dept.getId()+"\" name=\""+dept.getDeptName()+"\"/>";
		}
		xml+="</root>";
		return xml;
	}
	
	/**
	 * 数据利用情况
	 * @author fangbin
	 * @param type 1部门视图，2应用视图
	 */
	@Override
	public String dataUse(String type,String deptid,String receiveDeptId,String startDate,String endDate,String appId) {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>";
		if("1".equals(type)){//部门视图
			List<MetaData> mdList=metaDataService.getAllByDeptId(deptid);
			for(MetaData md:mdList){
				Map<String,String> map=new HashMap<String,String>();
				
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				map.put("itemId", md.getId().toString());
				map.put("receiveDeptId", receiveDeptId);
				int newDataNum=receTaskService.getDataNum(map);
				Map<String,String> mapTotal=new HashMap<String,String>();
				mapTotal.put("itemId", md.getId().toString());
				mapTotal.put("receiveDeptId", receiveDeptId);
				int totalDataNum=receTaskService.getDataNum(mapTotal);
				xml+="<target targetId='"+md.getId()+"' targetName='"+md.getTargetName()+"'dataTotal='"+totalDataNum+"' newData='"+newDataNum+"'/>";
			}
		}
		if("2".equals(type)){
			List<MetaDataAppMsg> mdaList=metaDataAppMsgService.findListByAppId(Long.parseLong(appId));
			for(MetaDataAppMsg mda:mdaList){
				Map<String,String> map=new HashMap<String,String>();
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				map.put("itemId", mda.getMetaData().getId().toString());
				int newDataNum=receTaskService.getDataNum(map);
				Map<String,String> mapTotal=new HashMap<String,String>();
				mapTotal.put("itemId",mda.getMetaData().getId().toString());
				int totalDataNum=receTaskService.getDataNum(mapTotal);
				xml+="<target targetId='"+mda.getMetaData().getId()+"' targetName='"+mda.getMetaData().getTargetName()+"' dataTotal='"+totalDataNum+"' newData='"+newDataNum+"'/>";
				
			}
		}
		
		xml+="</root>";
		return xml;
	}
	
	/**
	 * 数据接收详细
	 * @author fangbin
	 * @param targetId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public String dataReceiveInfo(String targetId,String receiveDeptId,String startDate,String endDate) {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>";
		Map<String,String> map=new HashMap<String,String>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("itemId", targetId);
		List<ReceiveResult> mdList=receTaskService.findReceiveResult(map);
		for(ReceiveResult rr:mdList){
			xml+="<target targetName='"+rr.getItemName()+"' receiveDeptName='"+rr.getReceiveDeptName()+"' dataNum='"+rr.getDataNum()+"' />";
		}
		xml+="</root>";
		return xml;
	}
	/**
	 * 
	 * 数据利用排名——按应用
	 * @author fangbin
	 */
	@Override
	public String dataUseRankByApp() {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>";
		List<Object[]> objList=metaDataAppMsgService.appRank();
		for(Object[] obj:objList){
			xml+="<app id='"+obj[0]+"' appName='"+obj[1]+"' targetSize='"+obj[2]+"'/>";
		}
		xml+="</root>";
		return xml;
	}

	@Override
	public String dataUseRankByTarget(String appId) {
		Map<String,String> map =new HashMap<String,String>();
		map.put("appId", appId);
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>";
		List<Object[]> objList=metaDataAppMsgService.targetRank(map);
		for(Object[] obj:objList){
			xml+="<app id='"+obj[0]+"' targetName='"+obj[2]+"' dataNum='"+obj[1]+"'/>";
		}
		xml+="</root>";
		return xml;
	}

	public static void main(String arg[]){
//			Runtime.getRuntime().exec("ping 192.168.801.117:8080");
		InetAddress address;
		try {
			address = InetAddress.getByName("192.168.12.117");
			boolean bool=address.isReachable(5000);
			System.out.println(bool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
}
