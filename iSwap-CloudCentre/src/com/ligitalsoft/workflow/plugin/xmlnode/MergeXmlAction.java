package com.ligitalsoft.workflow.plugin.xmlnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

/***
 *做XML的合并操作
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-11-26下午07:50:46
 *@Team 研发中心
 */
public class MergeXmlAction  extends PluginActionHandler{
	private static final long serialVersionUID = 8926425895851192785L;

	public String target_inputVar;
	public String src_inputVar;
	public String sourceXPath;
	public String targetXPath;
	public String xml_outVar;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		List<DataPackInfo> dpInfoList = (List<DataPackInfo>)this.getCacheInfo(src_inputVar);
		String target_xml = (String)context.getVariable(target_inputVar);
		try{
			List<DataPackInfo> dpi_taget = new ArrayList<DataPackInfo>();
			for(DataPackInfo dpi:dpInfoList){
				Document targetDoc =  DocumentHelper.parseText(target_xml);
				targetDoc.setXMLEncoding("gb2312");
	    		Element targetElem = (Element)targetDoc.selectSingleNode(targetXPath);
	    		Document src_Doc = DocumentHelper.parseText(new String(dpi.getByteVal()));
	    		List<Node> sourceList = src_Doc.selectNodes(sourceXPath);
	    		for(Node sNode : sourceList){
	    			 targetElem.add(strTOxml(sNode.asXML()));
	    		}
	    		DataPackInfo target_doc = new DataPackInfo();
	    		target_doc.setByteVal(targetDoc.asXML().getBytes());
	    		dpi_taget.add(target_doc);
		   }
		  this.putCacheInfo(xml_outVar, dpi_taget);
		}catch(Exception e){
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("XML的合并节点失败!",e);
			throw new ActionException(e);
		}
	}

	/**
	 * 将字符转换成xml
	 *@author hudaowan
	 *@date  Oct 27, 2008 11:01:51 AM
	 *@param str
	 *@return
	 *@throws Exception
	 */
	private Element strTOxml(String str) throws Exception{
		Document doc =  DocumentHelper.parseText(str);
		doc.setXMLEncoding("gb2312");
		Element ele = doc.getRootElement();
		return ele;
	}
}
