package com.ligitalsoft.workflow.plugin.model;

/**
 * 单个字段的信息
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-21 下午10:06:46
 *@Team 研发中心
 */
public class FiledDataInfo   implements java.io.Serializable{
	private static final long serialVersionUID = -7840278258611457866L;
	private String filedName;
	private String type;
	private String ispk;
	private String isbuild;//是否自动生成数据
	private String filedValue;
	private byte[] fileBytes;
	public String getFiledName() {
		return filedName;
	}
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	public String getIsbuild() {
		return isbuild;
	}
	public void setIsbuild(String isbuild) {
		this.isbuild = isbuild;
	}
	public String getIspk() {
		return ispk;
	}
	public void setIspk(String ispk) {
		this.ispk = ispk;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFiledValue() {
		return filedValue;
	}
	public void setFiledValue(String filedValue) {
		this.filedValue = filedValue;
	}
	public byte[] getFileBytes() {
		return fileBytes;
	}
	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
}
