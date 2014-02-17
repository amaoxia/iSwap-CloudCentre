package com.ligitalsoft.workflow.plugin.cachenode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import net.sf.ehcache.Cache;

import org.jbpm.api.activity.ActivityExecution;

import com.common.cachetool.CacheTool;
import com.ligitalsoft.help.cache.CacheConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

/**
 * 修改缓存中对象的状态
 * @author hudaowan
 */
public class UpdateCahceAction extends PluginActionHandler {
	private static final long serialVersionUID = -5494378938688516567L;
	public String dataList_inputVar;
	public String keyName;
	public String statusVal;
	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
//		try{
//			CacheTool tool = CacheTool.init();
//			Cache dataInfoCache = tool.findCache(CacheConstant.dataInfoCache);
//			List<DataPackInfo> dpInfoList = (List<DataPackInfo>)context.getVariable(dataList_inputVar);
//			List<String> keyList = (List<String>)dataInfoCache.getKeys();
//			int n = 1;
//			for(String key:keyList){
//				if(key.indexOf(keyName)!=-1&&n<100){
//					DataPackInfo dpInfo = (DataPackInfo)tool.getCacheInfo(dataInfoCache, key);
//					for(DataPackInfo dpi:dpInfoList){
//						if(dpi.equals(dpInfo)){
//							tool.putCacheInfo(dataInfoCache, key, dpInfo);
//							log.info("改变缓存中【第"+n+"个数据包】成功。");
//							n++;
//						}
//					}
//				}
//			}
//		}catch(Exception e){
//			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
//			 e.printStackTrace(new PrintStream(bo));
//			 log.error("修改缓存中对象的状态失败！",e);
//			 throw new ActionException(e);
//		}
	}


}
