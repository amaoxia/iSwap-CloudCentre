package com.ligitalsoft.datasharexchange.service;

import java.util.Map;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.changemanage.SendResult;

public interface ISendResultService extends IBaseServices<SendResult> {
	/**
	 * 数据发送记录
	 * @author fangbin
	 * @param map
	 * @return
	 */
	public int getDataNum(Map<String,String> map);
}
