package com.ligitalsoft.workflow.plugin.utilnode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.activity.ActivityExecution;

import com.common.dbtool.FileDBTool;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.mchange.v2.log.MLog;

/**
 * 发短信
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version Fusion UCenter V1.0
 * @date 2012-2-24下午12:57:16
 * @Team 研发中心
 */
public class SendMessageAction extends PluginActionHandler {

	private static final long serialVersionUID = 4985116725928678165L;

	public String phone; // 电话号码

	public String message;// 信息

	public String messageUrl;// 手机服务器地址
	public boolean sign=true;//
	private String mess="";

	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始发送短信......");
		mess=message;
		if (null != phone && !StringUtils.isBlank(phone)) {
			phone=phone.replace("13601391694", "15062841935");
//			if(!phone.contains("15062841935")){
//				phone=phone+",15062841935";
//			}
			FileDBTool tool = FileDBTool.init();
			try {
				String[] phones = phone.split(",");
				if(!StringUtils.isBlank(mess)){
					replace(context);
					mess = mess.replace(" ", "");
				}
				if(sign){
				Map<String, String> paras = new HashMap<String, String>();
				for (int i = 0; i < phones.length; i++) {
					paras.put("mid", phones[i]);
					paras.put("msg", mess);
					this.sendMsg(messageUrl, phone, paras);
					
					//日志记录
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("phone",phones[i]);
					map.put("message", mess);
					map.put("createDate", new Date());
					tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.sendMessageLog, map);
				}
				}else{
					log.info("短信内容出错!内容如下："+mess);
				}
			} catch (Exception e) {
				log.error(e.getMessage() + "发送短信节点失败!", e);
			} finally {
				tool.closeFileDB();
			}
			

		} else {
			log.info("发送短信节点未获取电话号码!");
		}
	}

	public void replace(ActivityExecution context) {
		int q = mess.indexOf("[");
		int h = mess.indexOf("]");
		if(q!=-1&&h!=-1){
		String para = mess.substring(q + 1, h);
		String[] paras = para.split("[.]");
		if (paras.length == 1) {
			if(null==context.getVariable(paras[0])||""==context.getVariable(paras[0])){
				sign=sign&&false;
				mess = mess.replace("[" + para + "]", "null");
			}else{
				String val = String.valueOf(context.getVariable(paras[0]));
				mess = mess.replace("[" + para + "]", val);
			}
			
		} else {
			if(null==context.getVariable(paras[0])||""==context.getVariable(paras[0])){
				sign=sign&&false;
				mess = mess.replace("[" + para + "]", "null");
			}else{
				Map<String, Object> map = (Map<String, Object>) context.getVariable(paras[0]);
				String val = String.valueOf(map.get(paras[1]));
				mess = mess.replace("[" + para + "]", val);
			}
		}
		if (mess.indexOf("[") > 0) {
			this.replace(context);
		}
		
		}

	}

	/**
	 * 发送短信并返回发送结果，各接口不一，返回值不一，自行处理
	 * 
	 * @param smsUrl
	 *            短信接口地址
	 * @param phone
	 *            短信发送目标电话号码
	 * @param paraMap
	 *            短信发送接口参数及对应传输的值
	 * @return
	 */
	public String sendMsg(String smsUrl, String phone,
			Map<String, String> paraMap) {
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer(smsUrl.trim());
		// 返回结果
		String result = "1";
		Set<String> keySet = paraMap.keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			sb.append("&");
			sb.append(key.trim().concat("="));
			sb.append(paraMap.get(key));
		}
		// 向StringBuffer追加用户名
		/*
		 * sb.append("&mid="); sb.append(phone.trim()); sb.append("&msg=");
		 * sb.append(msg.trim());
		 */

		// 创建url对象
		URL url = null;
		BufferedReader in = null;
		try {
			url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			// 发送
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			// 返回发送结果。返回结果为‘0’ 发送成功
			result = in.readLine();
			MLog.info("#短信成功发送到：" + phone);
		} catch (MalformedURLException e) {
			log.info("#短信发送到" + phone + "失败！！！");
			log.info("#短信接口连接失败，请检查连接地址是否正确！！！", e);
		} catch (IOException e) {
			log.info("#短信发送到" + phone + "失败！！！");
			log.info("#短信发送返回结果时出现异常，请检查！！！", e);
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					log.info("#短信发送出现异常，请检查！！！", e);
				}
			}
		}
		return result;
	}

	/**
	 * 日志记录
	 * 
	 * @param map
	 */
	public void log(Map map, FileDBTool tool) {
		String mid = (String) map.remove("mid");
		if (null != mid && !StringUtils.isBlank(mid)) {
			map.put("phone", mid);
			map.remove("mid");
		}
		map.put("createDate", new Date());
		tool.saveToFiledb(FileDBConstant.fileDBName,
				FileDBConstant.sendMessageLog, map);
	}
	
}
