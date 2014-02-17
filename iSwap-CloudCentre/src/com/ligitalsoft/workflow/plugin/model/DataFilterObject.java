package com.ligitalsoft.workflow.plugin.model;

public class DataFilterObject {
	private String name;// 字段名称
	private String value;// 字段值
	private String isNull;// 是否为空
	private String relation;// 逻辑关系
	private String type;// 判断符合条件的数据是否继续通过
	private String dataType;// 数据类型
	private String logic;// 两个表达式之间的关系

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

}
