package com.ligitalsoft.workflow.plugin.cachenode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;

import org.jbpm.api.activity.ActivityExecution;

import com.common.cachetool.CacheTool;
import com.ligitalsoft.help.cache.CacheConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

/**
 * 读取缓存的数据
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-21 下午02:18:35
 *@Team 研发中心
 */
public class ReadCacheAction  extends PluginActionHandler {
	private static final long serialVersionUID = 6267195375322882432L;
	public String keyName;
	public String cacheData_outVar;
	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
//		try{
//			CacheTool tool = CacheTool.init();
//			Cache dataInfoCache = tool.findCache(CacheConstant.dataInfoCache);
//			List<String> keyList = (List<String>)dataInfoCache.getKeys();
//			List<DataPackInfo> dpInfoList = new ArrayList<DataPackInfo>();
//			int n = 0;
//			for(String key:keyList){
//				if(key.indexOf(keyName)!=-1&&n<100){
//					DataPackInfo dpInfo = (DataPackInfo)tool.getCacheInfo(dataInfoCache, key);
//					dpInfoList.add(dpInfo);
//					n++;
//					log.info("从缓存中成功读取【第"+n+"个数据包】。");
//					tool.deleteCacheInfo(dataInfoCache, key);
//				}
//				if(n>=100){
//					break;
//				}
//			}
//			context.setVariable(cacheData_outVar, dpInfoList);
//		}catch(Exception e){
//			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
//			 e.printStackTrace(new PrintStream(bo));
//			 log.error("读取缓存的信息失败！",e);
//			 throw new ActionException(e);
//		}
	}

}
