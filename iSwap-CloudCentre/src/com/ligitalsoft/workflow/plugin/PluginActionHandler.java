package com.ligitalsoft.workflow.plugin;

import java.util.List;

import net.sf.ehcache.Cache;

import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.api.activity.ActivityExecution;
import org.jbpm.internal.log.Jdk14LogFactory;
import org.jbpm.internal.log.Log;

import com.common.cachetool.CacheTool;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.exception.NodeException;

/**
 * 插件必须继承的类
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-8 下午01:48:47
 *@Team 研发中心
 */
public abstract class PluginActionHandler implements ActivityBehaviour {

	private static final long serialVersionUID = -7018574029991914923L;

	static {
		    Jdk14LogFactory.initializeJdk14Logging(); 
		  }

    protected final Log log = Log.getLog(getClass().getName());
    protected Cache cache  = null;
    protected CacheTool cacheTool = null;
	@Override
	public void execute(ActivityExecution execution) throws NodeException {
		String cacheName = (String)execution.getVariable("wf_chcache_infoname");
		cacheTool = CacheTool.init();
		cache = cacheTool.findCache(cacheName);
        this.doexecute(execution);
	}
	
	
	public abstract void doexecute(ActivityExecution context) throws ActionException ;
	
	/**
	 * 向缓存中添加数据
	 * @author hudaowan
	 * @date 2011-12-14下午05:10:59
	 * @param keyName
	 * @param object
	 */
	protected void putCacheInfo(String keyName,Object object){
		cacheTool.putCacheInfo(cache, keyName, object);
	}
	
	/**
	 * 得到缓存的数据
	 * @author hudaowan
	 * @date 2011-12-14下午05:11:04
	 * @param keyName
	 * @return
	 */
	protected Object getCacheInfo(String keyName){
		return cacheTool.getCacheInfo(cache, keyName);
	}
	
	/**
	 * 删除缓存中的数据
	 * @author hudaowan
	 * @date 2011-12-14下午05:13:12
	 * @param keyName
	 */
    protected void deleteCacheInfo(String keyName){
    	cacheTool.removeCache(keyName);
    }
    
    /**
     *  得到缓存的keys
     * @author hudaowan
     * @date 2011-12-15下午03:52:05
     * @return
     */
    protected List<String>  getCacheKey(){
    	return (List<String>)cache.getKeys();
    }
}
