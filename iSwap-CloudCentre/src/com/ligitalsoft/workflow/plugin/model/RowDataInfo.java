package com.ligitalsoft.workflow.plugin.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 一个数据的信息
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-21 下午09:55:04
 *@Team 研发中心
 */
public class RowDataInfo  implements java.io.Serializable{
	private static final long serialVersionUID = -8146402602387244447L;

	private FiledDataInfo filedDataInfo = new FiledDataInfo();
	
	//各个字段的信息
	private List<FiledDataInfo> filedDataInfos  = new ArrayList<FiledDataInfo>(); 

	
	public List<FiledDataInfo> getFiledDataInfos() {
		return filedDataInfos;
	}
	public void setFiledDataInfos(List<FiledDataInfo> filedDataInfos) {
		this.filedDataInfos = filedDataInfos;
	}
	public FiledDataInfo getFiledDataInfo() {
		return filedDataInfo;
	}
	public void setFiledDataInfo(FiledDataInfo filedDataInfo) {
		this.filedDataInfo = filedDataInfo;
	}
	
	
	
}
