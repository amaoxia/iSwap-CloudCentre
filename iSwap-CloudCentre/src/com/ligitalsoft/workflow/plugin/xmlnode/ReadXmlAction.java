package com.ligitalsoft.workflow.plugin.xmlnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

/**
 * 读取XML文件
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-9-21 下午02:10:55
 * @Team 研发中心
 */
public class ReadXmlAction extends PluginActionHandler {

	private static final long serialVersionUID = -4885561277989178261L;

	public String data_inputVar;
	public String xpath;
	public String nodeNames;
	public String readXml_outVar;

	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		long start = System.currentTimeMillis();
		log.info("开始解析XML文件【XPath:" + xpath + "】......");
		try {
			List<DataPackInfo> dpInfoList = (List<DataPackInfo>) this.getCacheInfo(data_inputVar);
			List<DataPackInfo> dpInfoObjs = new ArrayList<DataPackInfo>();
			int n = 1;
			for (DataPackInfo dpInfo : dpInfoList) {
				byte[] bytes = dpInfo.getByteVal();
				Document doc = this.getDoc(bytes);
				DataPackInfo dp = this.getDataPInfo(doc);
				dpInfoObjs.add(dp);
				log.info("第【" + n + "】个XML文件解析成功！");
				n++;
			}
			this.putCacheInfo(readXml_outVar, dpInfoObjs);
			long end = System.currentTimeMillis();
			log.info("完成解析:XML文件【XPath:" + xpath + "】使用时间：" + (end - start)
					+ " ms");
		} catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("读取文件出错!", e);
			throw new ActionException(e);
		}

	}

	/**
	 * @param bytes
	 * @return
	 * @throws ActionException
	 */
	private Document getDoc(byte[] bytes) throws ActionException {
		Document doc = null;
		String xml = new String(bytes);
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error("序列DOCUMENT对象失败!", e);
		}
		return doc;
	}

	/**
	 * 将doc装换成DataPackInfo
	 * 
	 * @param doc
	 * @return
	 * @throws ActionException
	 * @author hudaowan
	 * @date 2011-9-21 下午11:51:58
	 */
	@SuppressWarnings("unchecked")
	private DataPackInfo getDataPInfo(Document doc) throws ActionException {
		DataPackInfo dataPackInfo = new DataPackInfo();
		List<RowDataInfo> rowDataInfos = new ArrayList<RowDataInfo>();// 数据集合
		String arrayNode[] = null;
		if (!StringUtils.isBlank(nodeNames)) {// 分割过滤条件
			arrayNode = nodeNames.split(",");
		}
		Document document = getDoc(doc.asXML().getBytes());// 序列documebt对象
		List<Node> lst = document.selectNodes(xpath);// 根据xpath得到对象document对象
		for (Node node : lst) {
			RowDataInfo rowDataInfo = new RowDataInfo();
			List<FiledDataInfo> dataInfos = new ArrayList<FiledDataInfo>();
			if (arrayNode != null && arrayNode.length > 0) {// 存在过滤条件并进行过滤
				for (String arg : arrayNode) {
					FiledDataInfo dataInfo = new FiledDataInfo();
					Node cNode = node.selectSingleNode(arg);// 得到唯一node
					String name = cNode.getName();
					String value = cNode.getText();
					if (!StringUtils.isBlank(name)) {
						dataInfo.setFiledName(name);
					}
					dataInfo.setFiledValue(value);
					dataInfos.add(dataInfo);// 添加一条数据
				}
			} else {// 查询指定节点下所有子节点
				List<Node> nodes = node.selectNodes("child::*");// 得到当前Node下所有子节点
				for (Node info : nodes) {// 添加单个数据
					FiledDataInfo dataInfo = new FiledDataInfo();
					String name = info.getName();
					String value = info.getText();
					if (!StringUtils.isBlank(name)) {
						dataInfo.setFiledName(name);
					}
					dataInfo.setFiledValue(value);
					dataInfos.add(dataInfo);// 添加一条数据至数据集合
				}
			}
			rowDataInfo.setFiledDataInfos(dataInfos);// 封装一条数据
			rowDataInfos.add(rowDataInfo);// 数据集合
		}
		dataPackInfo.setRowDataList(rowDataInfos);
		return dataPackInfo;
	}

}
