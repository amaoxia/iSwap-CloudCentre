package com.ligitalsoft.workflow.plugin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据包的头
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-11-5下午03:11:28
 *@Team 研发中心
 */
public class HeadInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 交换的类型
	 */
	private String exchangeType;
	/**
	 * 数据源
	 */
	private String data_source;
	/**
	 * 路由的信息
	 */
	private List<Map<String,String>> data_targets = new ArrayList<Map<String,String>>();

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public String getData_source() {
		return data_source;
	}

	public void setData_source(String data_source) {
		this.data_source = data_source;
	}

	public List<Map<String, String>> getData_targets() {
		return data_targets;
	}

	public void setData_targets(List<Map<String, String>> data_targets) {
		this.data_targets = data_targets;
	}
}
