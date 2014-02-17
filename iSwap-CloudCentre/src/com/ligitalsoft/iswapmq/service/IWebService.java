package com.ligitalsoft.iswapmq.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.model.serverinput.WebServerInfo;

public interface IWebService extends IBaseServices<WebServerInfo> {

	 public List<WebServerInfo> findWSDataSourcesByDept(String status,Long deptId);
}
