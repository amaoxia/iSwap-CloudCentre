package test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.dbtool.FileDBTool;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.HeadInfo;
import com.ligitalsoft.workflow.plugin.model.ObjectTool;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

public class TestFiledb {

    public static String dbName = "hudw";
    public static String tableName = "user1";
	public static void main(String[] args) {
		FileDBTool tool = FileDBTool.init();
//		tool.getMongoConn();
//		Map<String,Object> dpiMap = new HashMap<String,Object>();
//		
//		Map<String,Object> rfiMap_1 = new HashMap<String,Object>();
//		
//        Map<String,Object> fdiMap_1 = new HashMap<String,Object>();
//        fdiMap_1.put("filedName", "userName1");
//        fdiMap_1.put("type", "varchar2");
//        fdiMap_1.put("ispk", "true");
//        fdiMap_1.put("isbuild", "true");
//        fdiMap_1.put("filedValue", "hudaowan1");
//        fdiMap_1.put("fileBytes", "aaaaaaaaaaaaaaaaaaaaaaaa".getBytes());
//        
//        Map<String,Object> fdiMap_2 = new HashMap<String,Object>();
//        fdiMap_2.put("filedName", "userName2");
//        fdiMap_2.put("type", "varchar2");
//        fdiMap_2.put("ispk", "true");
//        fdiMap_2.put("isbuild", "true");
//        fdiMap_2.put("filedValue", "hudaowan2");
//        fdiMap_2.put("fileBytes", "bbbbbbbbbbbbbbbbbbbbbbb".getBytes());
//        
//        
//        Map<String,Object> fdiMap_3 = new HashMap<String,Object>();
//        fdiMap_3.put("filedName", "userName3");
//        fdiMap_3.put("type", "varchar2");
//        fdiMap_3.put("ispk", "true");
//        fdiMap_3.put("isbuild", "true");
//        fdiMap_3.put("filedValue", "hudaowan3");
//        fdiMap_3.put("fileBytes", "ccccccccccccccccccccccc".getBytes());
//        
//        Map<String,Object> fdiMap_4 = new HashMap<String,Object>();
//        fdiMap_4.put("filedName", "userName4");
//        fdiMap_4.put("type", "varchar2");
//        fdiMap_4.put("ispk", "true");
//        fdiMap_4.put("isbuild", "true");
//        fdiMap_4.put("filedValue", "hudaowan4");
//        fdiMap_4.put("fileBytes", "ddddddddddddddddddddddddd".getBytes());
//        
//        rfiMap_1.put("row_1", fdiMap_1);
//        rfiMap_1.put("row_2", fdiMap_2);
//        rfiMap_1.put("row_3", fdiMap_3);
//        rfiMap_1.put("row_4", fdiMap_4);
//        
//        dpiMap.put("rows",rfiMap_1);
//        dpiMap.put("type", "Xml");
//        dpiMap.put("name", "国税局_1");
//        
//        dpiMap.put("value", "hudw");
//        tool.saveToFiledb(dbName, tableName,dpiMap);
		
		Map<String,String> r_map_1 = new HashMap<String,String>();
		r_map_1.put("dept_name", "财政局");
		Map<String,String> r_map_2 = new HashMap<String,String>();
		r_map_2.put("dept_name", "财政局_2");
		HeadInfo head = new HeadInfo();
		head.setData_source("国税局");
		head.setExchangeType("MQ");
		head.getData_targets().add(r_map_1);
		head.getData_targets().add(r_map_2);
		
		DataPackInfo dpi = new DataPackInfo();
		dpi.setHeadInfo(head);
		dpi.setByteVal("ddddddd".getBytes());
		dpi.setName("hudw");
		dpi.setType("excle");
		dpi.setSendDate(new Date());
		dpi.setValue("value_11111111");
		dpi.setFileName("filename_xml12312");
		
		RowDataInfo rdi = new RowDataInfo();
		FiledDataInfo fdi_1 = new FiledDataInfo();
		fdi_1.setFiledName("username");
		fdi_1.setFiledValue("中海纪元");
		fdi_1.setIsbuild("true");
		fdi_1.setIspk("true");
		fdi_1.setType("varchar2");
		
		FiledDataInfo fdi_2 = new FiledDataInfo();
		fdi_2.setFiledName("username");
		fdi_2.setFiledValue("中海纪元");
		fdi_2.setIsbuild("true");
		fdi_2.setIspk("true");
		fdi_2.setType("varchar2");
		
		rdi.getFiledDataInfos().add(fdi_1);
		rdi.getFiledDataInfos().add(fdi_2);
		
		dpi.getRowDataList().add(rdi);
	
		ObjectTool obj_tool = ObjectTool.init();
		
		Map<String,Object> map = obj_tool.dataPackToMap(dpi);;
//		map.put("name", "hudw_2");
		tool.saveToFiledb(dbName, tableName,map);
		
//		Map<String,Object> tar_map = new HashMap<String,Object>();
//		tar_map.put("name", "hudw_2");
//		
//		List<Map<String,Object>> list = tool.findToFiledb(dbName,tableName,map);
//		DataPackInfo ddd = obj_tool.mapToDataPack(list.get(0));
//		tool.deleteToFiledb(dbName,tableName, map);
//		tool.updateToFiledb(dbName,tableName,map,tar_map);
        tool.closeFileDB();
	}

}
