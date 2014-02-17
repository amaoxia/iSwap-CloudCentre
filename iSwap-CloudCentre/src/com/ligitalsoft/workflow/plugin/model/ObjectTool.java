package com.ligitalsoft.workflow.plugin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-11-5下午03:23:29
 *@Team 研发中心
 */
public class ObjectTool {
	private static ObjectTool object = null;
	
	public static ObjectTool init(){
		if(object == null){
			object = new ObjectTool();
		}
		return object;
	}
	
	/**
	 * 把数据包转换成Map
	 * @author hudaowan
	 * @date 2011-11-5下午03:29:22
	 * @param dpf
	 * @return
	 */
	public Map<String,Object> dataPackToMap(DataPackInfo dpf){
		Map<String,Object> dpf_map = new HashMap<String,Object>();
		Map<String,Object> rows_map = new HashMap<String,Object>();
		int n = 1;
		for(RowDataInfo rdf:dpf.getRowDataList()){
			Map<String,Object> rdf_map = new HashMap<String,Object>();
			for(FiledDataInfo fdi:rdf.getFiledDataInfos()){
				Map<String,Object> fdi_map = new HashMap<String,Object>();
				fdi_map.put("filedName", fdi.getFiledName());
				fdi_map.put("type", fdi.getType());
				fdi_map.put("ispk", fdi.getIspk());
				fdi_map.put("isbuild", fdi.getIsbuild());
				fdi_map.put("filedValue", fdi.getFiledValue());
				fdi_map.put("fileBytes", fdi.getFileBytes());
				rdf_map.put(fdi.getFiledName(), fdi_map);
			}
			rows_map.put("rowInfo_"+(n++), rdf_map);
		}
		
		dpf_map.put("name", dpf.getName());
		dpf_map.put("sendDate", new Date());
		dpf_map.put("fileName", dpf.getFileName());
		dpf_map.put("alias", dpf.getAlias());
		dpf_map.put("type", dpf.getType());
		dpf_map.put("value", dpf.getValue());
		dpf_map.put("status", dpf.getStatus());
		dpf_map.put("byteVal", dpf.getByteVal());
		dpf_map.put("errorInfo", dpf.getErrorInfo());
		dpf_map.put("headInfo", this.headToMap(dpf));
		dpf_map.put("rows_data", rows_map);
		return dpf_map;
	}
	
	/**
	 * 将头转换成Map
	 * @author hudaowan
	 * @date 2011-11-5下午04:02:23
	 * @param dpf
	 * @return
	 */
	private Map<String,Object> headToMap(DataPackInfo dpf){
		Map<String,Object> head_map = new HashMap<String,Object>();
		HeadInfo headInfo  = dpf.getHeadInfo();
		head_map.put("data_source", headInfo.getData_source());
		head_map.put("exchangeType", headInfo.getExchangeType());
		Map<String,Object> tagets_map  = new HashMap<String,Object>();
		int n = 0;
		for(Map<String,String> tag_map:headInfo.getData_targets()){
			tagets_map.put("route_id_"+(n++), tag_map);
		}
		head_map.put("route_info", tagets_map);
		return head_map;
	}
	
	
	/**
	 *  将Map转成数据包
	 * @author hudaowan
	 * @date 2011-11-5下午03:32:03
	 * @param map
	 * @return
	 */
	public DataPackInfo mapToDataPack(Map<String,Object> map){
		DataPackInfo dpf = new DataPackInfo();
		ObjectId id=(ObjectId)map.get("_id");
		String name = (String)map.get("name");
		Date sendDate = (Date)map.get("sendDate");
		String fileName = (String)map.get("fileName");
		String type = (String)map.get("type");
		String alias = (String)map.get("alias");
		String value = (String)map.get("value");
		String status = (String)map.get("status");
		String errorInfo = (String)map.get("errorInfo");
		byte[] byteVal = (byte[])map.get("byteVal");
	    HeadInfo head = this.mapToHead(map);
	    List<RowDataInfo>  rows = this.mapTorRowDataInfo(map);
	    dpf.setId(id.toString());
	    dpf.setName(name);
	    dpf.setFileName(fileName);
	    dpf.setAlias(alias);
	    dpf.setSendDate(sendDate);
	    dpf.setType(type);
	    dpf.setValue(value);
	    dpf.setByteVal(byteVal);
	    dpf.setHeadInfo(head);
	    dpf.setRowDataList(rows);
	    dpf.setErrorInfo(errorInfo);
	    dpf.setStatus(status);
		return dpf;
	}
	
	/**
	 *  得到数据包的头
	 * @author hudaowan
	 * @date 2011-11-5下午04:33:21
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private HeadInfo mapToHead(Map<String,Object> map){
		HeadInfo head = new HeadInfo();
		Map<String,Object>  head_map = (Map<String,Object>)map.get("headInfo");
		String data_source = (String)head_map.get("data_source");
		String exchangeType = (String)head_map.get("exchangeType");
		head.setData_source(data_source);
		head.setExchangeType(exchangeType);
		Map<String,Object> route_map = (Map<String,Object>)head_map.get("route_info");
		for(Map.Entry<String, Object> entry:route_map.entrySet()){
			Map<String,String> tagets_map = (Map<String,String>)entry.getValue();
			head.getData_targets().add(tagets_map);
		}
		return head;
	}
	
	/**
	 * 得到行的数据
	 * @author hudaowan
	 * @date 2011-11-5下午04:43:32
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<RowDataInfo> mapTorRowDataInfo(Map<String,Object> map){
		List<RowDataInfo> rdf_list = new ArrayList<RowDataInfo>();
		Map<String,Object> rows_map = (Map<String,Object>)map.get("rows_data");
		for(Map.Entry<String, Object> entry:rows_map.entrySet()){
			RowDataInfo rdi = new RowDataInfo();
			Map<String,Object> row_map = (Map<String,Object>)entry.getValue();
			for(Map.Entry<String, Object> entry_filed:row_map.entrySet()){
				Map<String,Object> fdi_map = (Map<String,Object>)entry_filed.getValue();
				FiledDataInfo fdi = this.mapToFiledDataInfo(fdi_map);
				rdi.getFiledDataInfos().add(fdi);
			}
			rdf_list.add(rdi);
		}
		return rdf_list;
	}
	
	/**
	 * 得到一个字段的数据
	 * @author hudaowan
	 * @date 2011-11-5下午04:50:27
	 * @param fdi_map
	 * @return
	 */
	private FiledDataInfo mapToFiledDataInfo(Map<String,Object> fdi_map){
		FiledDataInfo fdinfo = new FiledDataInfo();
		String filedName = (String)fdi_map.get("filedName");
		String type = (String)fdi_map.get("type");
		String ispk = (String)fdi_map.get("ispk");
		String isbuild = (String)fdi_map.get("isbuild");
		String filedValue = (String)fdi_map.get("filedValue");
		byte[] fileBytes = (byte[])fdi_map.get("fileBytes");
		fdinfo.setFiledName(filedName);
		fdinfo.setFiledValue(filedValue);
		fdinfo.setIsbuild(isbuild);
		fdinfo.setIspk(ispk);
		fdinfo.setType(type);
		fdinfo.setFileBytes(fileBytes);
		return fdinfo;
	}
}
