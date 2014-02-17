package com.ligitalsoft.webservices.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dbtool.FileDBTool;
import com.common.utils.json.JsonHelper;
import com.ligitalsoft.cloudnode.service.IWorkFlowService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudnode.WorkFlow;
import com.ligitalsoft.webservices.ISwapWorkFlowWS;

@Service("iSwapWorkFlow")
@Transactional
public class ISwapWorkFlowWSImpl implements ISwapWorkFlowWS{
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IWorkFlowService workFlowService;
	
	public String findWorkFlowXml(String id, String enName) {
		String msg = "true";
		try {
			WorkFlow wsInfo = workFlowService.findById(new Long(id));
			byte[] bty = wsInfo.getShowXml();
			String wfCnName= wsInfo.getWorkFlowName();
			String wfEnName = wsInfo.getWorkFlowCode();
			String wfStatus = wsInfo.getStatus();
			String xml = "";
			if(bty!=null){
				xml = new String(bty);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("wfCnName", wfCnName);
			map.put("wfEnName", wfEnName);
			map.put("wfStatus", wfStatus);
			map.put("xml", xml);
			msg = JsonHelper.toJsonString(map);
		} catch (Exception e) {  
			msg = "false";
			log.error("获取流程xml失败", e);
		}
		return msg;
	}

	public String saveWorkFlowXml(String id, String enName, String xml) {
		String msg = "true";
		try {
			WorkFlow wsInfo = workFlowService.findById(new Long(id));
			wsInfo.setShowXml(xml.getBytes());
			String wrokXml = this.showXmlToWorkXml(xml);
			wsInfo.setWorkFlowXml(wrokXml);
			workFlowService.saveOrUpdate(wsInfo);
		}catch (Exception e) {
			msg = "false";
			log.error("保存流程xml失败", e);
		}
		return msg;
	}

	@Override
	public String findDataSource(String deptId) {
		String msg = "";
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			Map<String,Object> varMap = new HashMap<String,Object>();
			varMap.put("deptId", deptId);
			List<Map<String,Object>> map_list = tool.findAllFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB,varMap);
			msg = JsonHelper.toJsonString(map_list);
		} catch (Exception e) {
			msg = "false";
			log.error("保存流程xml失败", e);
		}finally{
			tool.closeFileDB();
		}
		return msg;
	}
	
	@Override
	public String findMapping(String deptId) {
		String msg = "";
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			Map<String,Object> varMap = new HashMap<String,Object>();
			varMap.put("deptId", deptId);
			List<Map<String,Object>> map_list = tool.findAllFiledb(FileDBConstant.fileDBName, FileDBConstant.mappingDB,varMap);
			msg = JsonHelper.toJsonString(map_list);
		} catch (Exception e) {
			msg = "false";
			log.error("保存流程xml失败", e);
		}finally{
			tool.closeFileDB();
		}
		return msg;
	}
	
	/**
	 * 得到FTPServer的信息
	 * @author hudaowan
	 * @date 2011-12-19下午02:19:27
	 * @return
	 */
	public String findFTPServer(){
		String msg = "";
		FileDBTool tool = FileDBTool.init();
		try {
			tool.getMongoConn();
			List<Map<String,Object>> map_list = tool.findToFiledb(FileDBConstant.fileDBName, FileDBConstant.ftpServerDB);
			msg = JsonHelper.toJsonString(map_list);
		} catch (Exception e) {
			msg = "false";
			log.error("保存流程xml失败", e);
		}finally{
			tool.closeFileDB();
		}
		return msg;
	}
	
	
	
	/**
	 * 将展示的xml转成工作的xml
	 * @author hudaowan
	 * @date 2011-10-17下午07:00:47
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String showXmlToWorkXml(String xml){
		String  ws_xml = "";
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			List list = root.elements();
			String g_val = "1,1,1,1";
			for(int i=0;i<list.size();i++){
				Element  element = (Element)list.get(i);
				if(!element.getName().equals("variable")){
					Attribute attr_id = element.attribute("id");
					Attribute attr_type = element.attribute("type");
					Attribute attr_g = element.attribute("g");
					if(attr_id!=null){   
						element.remove(attr_id);
					}  
					if(attr_type!=null){
						element.remove(attr_type);
					}
					if(attr_g!=null){
						element.remove(attr_g);
						element.addAttribute("g",g_val);
					}
				}else{
					Attribute attr_mapp = element.attribute("mappingname");
					if(attr_mapp!=null){
						element.remove(attr_mapp);
					}
				}
			
				
				//删除子节点
				List child_list = element.elements();
				for(int n=0;n<child_list.size();n++){
					Element child_elem = (Element)child_list.get(n);
					Attribute child_attr_id = child_elem.attribute("id");
					Attribute child_attr_type = child_elem.attribute("type");
					if(child_attr_id!=null){
						child_elem.remove(child_attr_id);
					}
					if(child_attr_type!=null){
						child_elem.remove(child_attr_type);
					}
				}
			}
			ws_xml = root.asXML();
		} catch (Exception e) {
			log.error("XML转换失败！", e);
		}
		return ws_xml;
	}

}
