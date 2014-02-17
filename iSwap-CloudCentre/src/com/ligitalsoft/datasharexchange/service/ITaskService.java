/*
 * @(#)ITaskServiceService.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service;


import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ExchangeSendTask;

/**
 * 任务 SERVICE
 * @author daic
 * @since 2011-08-17 15:09:10
 * @name com.ligitalsoft.cloudstorage.service.ITaskService.java
 * @version 1.0
 */
public interface ITaskService extends IBaseServices<ChangeItem> {
	/**
	 * 建立任务
	 * @param items
	 * @param begin
	 * @param end
	 * @return
	 */
	List<String[]> buildTask(List<ChangeItem> items, String begin, String end);
	/**
	 * 建立催办
	 * @param items
	 * @return
	 */
	void buildCuiban(ChangeItem item, ExchangeSendTask st);
}
