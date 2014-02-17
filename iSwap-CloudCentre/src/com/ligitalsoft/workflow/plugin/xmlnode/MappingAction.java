package com.ligitalsoft.workflow.plugin.xmlnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.FileDBTool;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

public class MappingAction extends PluginActionHandler {

	private static final long serialVersionUID = 5006060236661380643L;

	public String xml_inputVar;
	public String mapperName;
	public String xml_outVar;
	
	private Map<String,Map<String,String>> map_maptable = new HashMap<String,Map<String,String>>();//mapping关系
	private Map<String,Map<String,String>> map_newtable = new HashMap<String,Map<String,String>>();//新加字段
	
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		if(null!=this.getCacheInfo(xml_inputVar)){
		
		FileDBTool tool = FileDBTool.init();
		try{
			tool.getMongoConn();
	    	Map<String,Object> find_map = new HashMap<String,Object>();
	    	find_map.put("key", mapperName);
	    	List<Map<String,Object>> entityList = tool.findToFiledb(FileDBConstant.fileDBName, FileDBConstant.mappingDB, find_map);
	    	if(null!=entityList&&entityList.size()>0){
	    	
	    	Map<String,Object> entity = entityList.get(0);
	    	String xml = (String)entity.get("xml");
			@SuppressWarnings("unchecked")
			List<DataPackInfo> dpInfoList = (List<DataPackInfo>)this.getCacheInfo(xml_inputVar);
			List<DataPackInfo> rs_dpInfoList = new ArrayList<DataPackInfo>();
			for(DataPackInfo dpf:dpInfoList){
				this.paserXML(xml);
				this.mappingXMl(dpf);
				this.newFiledInfo(dpf);
				rs_dpInfoList.add(dpf);
			}
			this.putCacheInfo(xml_outVar, rs_dpInfoList);
	    	}else{
	    		log.info("Mapper转换节点时未获得对应的mapper文件！");
	    	}
		}catch(Exception e){
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("Mapping转换节点失败!",e);
			throw new ActionException(e);
		}
		}else{
			log.info("Mapper转换节点时未获得【"+xml_inputVar+"】变量的值！");
		}
	}
	
	/**
	 * 执行mapping
	 * @author hudaowan
	 * @date 2011-10-28下午03:48:19
	 * @param dpf
	 * @throws ActionException
	 */
	private void mappingXMl(DataPackInfo dpf) throws ActionException{
		List<RowDataInfo> rs_RowDataList = new ArrayList<RowDataInfo>();;
		for(RowDataInfo rdf:dpf.getRowDataList()){
			List<FiledDataInfo> filedDataInfos  = rdf.getFiledDataInfos();
			List<FiledDataInfo> rs_filedDataInfos  = new ArrayList<FiledDataInfo>();
			for(FiledDataInfo fdf:filedDataInfos){
				String name = fdf.getFiledName();
				Map<String,String> map = map_maptable.get(name);
				if(null!=map){
				String to = map.get("to");
				String type = map.get("type");
				String isbuild = map.get("isbuild");
				String ispk = map.get("ispk");
				String value = map.get("value");
				fdf.setFiledName(to);
				if(!StringUtils.isBlank(type)){
					fdf.setType(type);
				}
				if(!StringUtils.isBlank(isbuild)){
					fdf.setIsbuild(isbuild);
				}
				if(!StringUtils.isBlank(ispk)){
					fdf.setIspk(ispk);
				}
				if(!StringUtils.isBlank(value)){
					fdf.setFiledValue(value);
				}
				rs_filedDataInfos.add(fdf);
				}else{
					rs_filedDataInfos.add(fdf);
				}
			}
			rdf.getFiledDataInfos().clear();
			rdf.getFiledDataInfos().addAll(rs_filedDataInfos);
			rs_RowDataList.add(rdf);
		}
		dpf.getRowDataList().clear();
		dpf.getRowDataList().addAll(rs_RowDataList);
	}
	
	/**
	 * 新添字段信息
	 * @author hudaowan
	 * @date 2011-10-28下午04:00:58
	 * @param dpf
	 * @throws ActionException
	 */
	private void newFiledInfo(DataPackInfo dpf)throws ActionException{
		List<FiledDataInfo> fdf_list = new ArrayList<FiledDataInfo>();
		for(Map.Entry<String, Map<String,String>> entry:map_newtable.entrySet()){
			 String key = entry.getKey();
			 Map<String,String> map = entry.getValue();
			 if(null!=map){
				 String type = map.get("type");
				 String isbuild = map.get("isbuild");
				 String ispk = map.get("ispk");
				 String value = map.get("value");
				 FiledDataInfo fdf = new FiledDataInfo();
				 fdf.setFiledName(key);
				 fdf.setType(type);
				 fdf.setIspk(ispk);
				 fdf.setIsbuild(isbuild);
				 fdf.setFiledValue(value);
				 fdf_list.add(fdf);
			 }
			
		}
		
		List<RowDataInfo> rs_RowDataList = new ArrayList<RowDataInfo>();
		for(RowDataInfo rdf:dpf.getRowDataList()){
			rdf.getFiledDataInfos().addAll(fdf_list);
			rs_RowDataList.add(rdf);
		}
		
		dpf.getRowDataList().clear();
		dpf.getRowDataList().addAll(rs_RowDataList);
	}
	
	/**
	 * 解析xml
	 * @author hudaowan
	 * @date 2011-10-28下午02:23:11
	 * @param xml
	 * @return
	 * @throws ActionException
	 */
	@SuppressWarnings("unchecked")
	private void paserXML(String xml) throws ActionException{
		try{
			Document document = DocumentHelper.parseText(xml);
			List<Node> maptab_list = (List<Node>)document.selectNodes("//Link");//映射关系
			for(int i=0;i<maptab_list.size();i++){
				Map<String,String> link_map = new HashMap<String,String>();
				Node node = maptab_list.get(i);
				String from = node.valueOf("@from");
				String to = node.valueOf("@to");
				String type = node.valueOf("@type");
				String isbuild = node.valueOf("@isbuild");
				String ispk = node.valueOf("@ispk");
				String value = node.valueOf("@value");
				link_map.put("to", to);
				link_map.put("type", type);
				link_map.put("isbuild", isbuild);
				link_map.put("ispk", ispk);
				link_map.put("value", value);
				map_maptable.put(from, link_map);
			}
			
		    List<Node> newtab_list = (List<Node>)document.selectNodes("//FiledINfo");// 新增节点
		    for(int n=0;n<newtab_list.size();n++){
		    	Map<String,String> newtab_map = new HashMap<String,String>();
		    	Node node = newtab_list.get(n);
		    	String name = node.valueOf("@name");
				String type = node.valueOf("@type");
				String isbuild = node.valueOf("@isbuild");
				String ispk = node.valueOf("@ispk");
				String value = node.valueOf("@value");
				newtab_map.put("type", type);
				newtab_map.put("isbuild", isbuild);
				newtab_map.put("ispk", ispk);
				newtab_map.put("value", value);
				map_newtable.put(name, newtab_map);
		    }
		    
		    
		}catch(Exception e){
			log.error("解析XMl失败！",e);
			throw new ActionException(e);
		}
	
	}
	
}
