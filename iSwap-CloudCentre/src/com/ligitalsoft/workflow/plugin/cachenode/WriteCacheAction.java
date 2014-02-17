package com.ligitalsoft.workflow.plugin.cachenode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import net.sf.ehcache.Cache;

import org.jbpm.api.activity.ActivityExecution;

import com.common.cachetool.CacheTool;
import com.ligitalsoft.help.cache.CacheConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

/**
 * 将数据写入缓存
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-21 下午02:21:30
 *@Team 研发中心
 */
public class WriteCacheAction extends PluginActionHandler {
	private static final long serialVersionUID = 8744746412712678791L;
	
	public String data_inputVar;
	public String keyName;
	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
//		try{
//			CacheTool tool = CacheTool.init();
//			Cache dataInfoCache = tool.findCache(CacheConstant.dataInfoCache);
//			List<DataPackInfo> dpInfoList = (List<DataPackInfo>)context.getVariable(data_inputVar);
//			for(DataPackInfo dpInfo:dpInfoList){
//				String key_Name = this.keyName+"_"+System.currentTimeMillis()+"_"+this.genRandomNum(6);
//				tool.putCacheInfo(dataInfoCache, key_Name, dpInfo);
//			}
//			log.info("成功的将数据写入缓存！");
//		}catch(Exception e){
//			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
//			 e.printStackTrace(new PrintStream(bo));
//			 log.error("读取缓存的信息失败！",e);
//			 throw new ActionException(e);
//		}
	}
	
	
	/**
	 * 生成随即数
	 * @param pwd_len
	 * @return 
	 * @author  hudaowan
	 * @date 2011-9-5 下午12:42:41
	 */
	public String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}
}
