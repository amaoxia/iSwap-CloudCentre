package com.ligitalsoft.workflow.plugin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 单个数据包的信息
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-21 下午10:07:51
 *@Team 研发中心
 */
public class DataPackInfo  implements java.io.Serializable{
	private static final long serialVersionUID = 859488106954217634L;
	/**
	 * 数据的头
	 */
	private HeadInfo headInfo = new HeadInfo();
	private List<RowDataInfo> RowDataList = new ArrayList<RowDataInfo>();
	private String id;
	
	private String name;//数据包的名称
	
    private Date sendDate; //发送时间
    
	private String fileName;//文件的名称
	
	private String type; //文件的类型 
	
	private String value;// 文件的内容
	
	private byte[] byteVal;//文件的内容
	
	private String status;//数据包状态 stop，run，succ，error
	
	private String errorInfo;//错误信息
	
	/**
	 * @author fangbin
	 */
	private String alias;//别名;

	public List<RowDataInfo> getRowDataList() {
		return RowDataList;
	}

	public void setRowDataList(List<RowDataInfo> rowDataList) {
		RowDataList = rowDataList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public byte[] getByteVal() {
		return byteVal;
	}

	public void setByteVal(byte[] byteVal) {
		this.byteVal = byteVal;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public HeadInfo getHeadInfo() {
		return headInfo;
	}

	public void setHeadInfo(HeadInfo headInfo) {
		this.headInfo = headInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
