package com.ligitalsoft.workflow.plugin.eventinfo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jbpm.api.listener.EventListenerExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginEventHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

public class StartEventAction extends PluginEventHandler {
	private static final long serialVersionUID = -2916938977987676661L;

	@Override
	public String doexecute(EventListenerExecution context)throws ActionException {
		List<String> keyList = this.getCacheKey();
		for(String key:keyList){
			Object obj = this.getCacheInfo(key);
			if(obj instanceof String){
				Map<String,Object>   map = this.xmlToMap((String)obj);
				context.setVariables(map);
			}else if(obj instanceof byte[]){
				DataPackInfo dpf = new DataPackInfo();
				dpf.setByteVal((byte[])obj);
				List<DataPackInfo> dpInfoObjs = new ArrayList<DataPackInfo>();
				dpInfoObjs.add(dpf);
				this.putCacheInfo(key, dpInfoObjs);
			}
		}
		
		return "true";
	}

	
	/**
	 * 将xml转换成map
	 * @author hudaowan
	 * @date 2011-10-25下午11:26:31
	 * @param xml
	 * @return
	 */  
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String,Object> xmlToMap(String xml){
		Map<String,Object> var_map = new HashMap<String,Object>();
		try{
			if(!"".equals(xml)&&xml!=null){
				SAXReader sr = new SAXReader();
				Document  doc=sr.read(new StringReader(xml));
				List<Element> list =(List<Element>)doc.selectNodes("root");
				for (Element el:list) { 
				  List nodeList =  el.elements("parameter");
				  for(Iterator it = nodeList.iterator(); it.hasNext();){
					  Element elm  = (Element) it.next();
					  String keys= elm.attributeValue("key");
					  Object values= elm.getData();
					  var_map.put(keys, values);
				  }
				}	
			}
		}catch(Exception e){
			log.error("XML转换成Map失败！", e);
		}
		return var_map;
	}
}
