package com.ligitalsoft.esb.service.impl;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.esb.dao.EsbWorkFlowDao;
import com.ligitalsoft.esb.service.IEsbWorkFlowService;
import com.ligitalsoft.model.esb.EsbWorkFlow;

@Service("esbWorkFlowService")
public class EsbWorkFlowServiceImpl extends BaseSericesImpl<EsbWorkFlow>
		implements IEsbWorkFlowService {

	@Autowired
	private EsbWorkFlowDao esbWorkFlowDao;

	/**
	 * 工作流程测试
	 * 
	 * @author fangbin
	 * @param workflow
	 * @param Xml
	 * @return
	 */
	@Override
	public String runWorkFlow(Long workflowId, String xml) {
		String msg = "";
		return msg;
	}

	@Override
	public EntityHibernateDao<EsbWorkFlow> getEntityDao() {

		return esbWorkFlowDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> readWorkflowXml(String Xml) {
		Map<String,Object> map=new HashMap<String,Object>();
		SAXReader sr = new SAXReader();
		try {
			 Document  doc=sr.read(new StringReader(Xml));
			 @SuppressWarnings("unchecked")
			List<Element> list =(List<Element>)doc.selectNodes("root");
			 for (Element el:list) { 
				  List nodeList =  el.elements("parameter");
				  for(Iterator it = nodeList.iterator(); it.hasNext();){
					  Element elm  = (Element) it.next();
					  String keys= elm.attributeValue("key");
					  Object values= elm.getData();
					  map.put(keys, values);
				  }
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	

}
