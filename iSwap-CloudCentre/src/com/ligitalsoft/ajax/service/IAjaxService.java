package com.ligitalsoft.ajax.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.common.framework.dao.SortPara;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.IBaseServices;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.system.SysDept;

/**
 * Ajax SERVICE
 * 
 * @author zhangx
 * @since May 16, 2011 11:15:39 AM
 * @name com.ligitalsoft.ajax.service.IAjaxService.java
 * @version 1.0
 */
public interface IAjaxService {

	public JSONArray depTree4AppItemExchangeConf(Long appMsgId, Long AppItemId, Long sendDeptId);
}