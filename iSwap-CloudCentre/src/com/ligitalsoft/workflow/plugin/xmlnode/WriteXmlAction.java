package com.ligitalsoft.workflow.plugin.xmlnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

/**
 * 生成Xml文件
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-9-21 下午02:09:32
 * @Team 研发中心
 */
public class WriteXmlAction extends PluginActionHandler {

	private static final long serialVersionUID = -9206605125110292166L;

	public String data_inputVar; // 输入数据的变量
	public String root;
	public String nodeInfo;// 节点信息
	public String writeXml_outVar;
	@Override
	@SuppressWarnings("unchecked")
	public void doexecute(ActivityExecution context) throws ActionException {
		long start = System.currentTimeMillis();
		log.info("开始解析Excle文件【Head:" + nodeInfo + "】......");
		try {
			List<DataPackInfo> dpInfoList = (List<DataPackInfo>)this.getCacheInfo(data_inputVar);
			List<DataPackInfo> dpInfoObjs = new ArrayList<DataPackInfo>();
			int n = 1;
			for (DataPackInfo dpInfo : dpInfoList) {
				DataPackInfo dp = this.createXml(dpInfo);
				dpInfoObjs.add(dp);
				log.info("第【" + n + "】个Excel文件解析成功！");
				n++;
			}
			this.putCacheInfo(writeXml_outVar, dpInfoObjs);
			long end = System.currentTimeMillis();
			log.info("完成解析:Excel文件【Head:" + nodeInfo + "】使用时间：" + (end - start)+ " ms");
		} catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("生成Excel出错!", e);
			throw new ActionException(e);
		}
	}

	/**
	 * 生成xml
	 * 
	 * @param dpInfo
	 * @return
	 * @throws ActionException
	 */
	public DataPackInfo createXml(DataPackInfo dpInfo) throws ActionException {
		String node = "row";
		DataPackInfo dpi = new DataPackInfo();
		String args[] = null;
		if (!StringUtils.isBlank(nodeInfo)) {
			args = nodeInfo.split(",");
		}
		Document document = DocumentHelper.createDocument();// 创建dom对象
		Element rootNode = document.addElement(root);// 添加root根节点
		List<RowDataInfo> dataInfos = dpInfo.getRowDataList();// 得到所有数据集合
		for (RowDataInfo rowDataInfo : dataInfos) {// 循环每条数据
			Element dataRow = rootNode.addElement(node);// 创建子节点
			List<FiledDataInfo> filedDataInfos = rowDataInfo
					.getFiledDataInfos();
			for (FiledDataInfo filedDataInfo : filedDataInfos) {
				if (args != null && args.length > 0) {//  存在过滤条件并进行过滤
					for (String nodeName : args) {
						if (nodeName.equals(filedDataInfo.getFiledName()
								.toString())) {
							Element filed = dataRow.addElement(filedDataInfo
									.getFiledName());
							filed.addText(filedDataInfo.getFiledValue());
						}
					}
				} else {// 不做任何过滤
					Element filed = dataRow.addElement(filedDataInfo
							.getFiledName());
					filed.addText(filedDataInfo.getFiledValue());
				}
			}
		}
		String strXml = document.asXML();
		byte[] bytes = strXml.getBytes();
		dpi.setByteVal(bytes);
		return dpi;
	}

}
