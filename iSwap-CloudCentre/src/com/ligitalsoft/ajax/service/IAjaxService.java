package com.ligitalsoft.ajax.service;

import net.sf.json.JSONArray;

/**
 * Ajax SERVICE
 * 
 * @author zhangx
 * @since May 16, 2011 11:15:39 AM
 * @name com.ligitalsoft.ajax.service.IAjaxService.java
 * @version 1.0
 */
public interface IAjaxService {

	public JSONArray depTree4AppItemExchangeConf(Long appMsgId, Long AppItemId, String[] sendDeptIdsArray);
}