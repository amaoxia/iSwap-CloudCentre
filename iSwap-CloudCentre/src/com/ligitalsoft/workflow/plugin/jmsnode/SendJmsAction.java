package com.ligitalsoft.workflow.plugin.jmsnode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.jbpm.api.activity.ActivityExecution;

import com.common.jmstool.JMSAttr;
import com.common.jmstool.JMSConntect;
import com.common.jmstool.JMSTool;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

/**
 * 发送消息
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-10-29下午03:06:44
 *@Team 研发中心
 */     
public class SendJmsAction extends PluginActionHandler {
	private static final long serialVersionUID = 7224115608546167119L;
	
	private String initFactory;// 初始化工作
	private String url;// 连接地址
	private String userName;
	private String passWord;
	private String queFactory;// 连接工厂
	private String squeName;// 发送消息的队列
	public String data_inputVar;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		JMSAttr attr = new JMSAttr(initFactory, url, userName, passWord,queFactory);
		try {
			attr.setSqueName(squeName);
			JMSTool jmsTool = JMSTool.init();
			JMSConntect conn = jmsTool.createSendConnect(attr);
			List<DataPackInfo> dpInfoList = (List<DataPackInfo>) this.getCacheInfo(data_inputVar);
			for(DataPackInfo dpi:dpInfoList){
				byte[] b_str = dpi.getByteVal();
				if(b_str!=null){
					String  msg = new String(b_str);
					jmsTool.sendAsyn(conn, msg);
				}
			}
		} catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("发送JMS消息失败!",e);
			throw new ActionException(e);
		}
	}

}
