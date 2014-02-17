package com.ligitalsoft.model.performanage;

import java.util.ArrayList;
import java.util.List;

import com.ligitalsoft.model.performanage.CheckScore;
import com.ligitalsoft.model.performanage.Department;

/**
 * 考核汇总
 * @author liuxd
 *
 */
public class CheckGather{

	/**
	 * 部门
	 */
	private Department department;
	/**
	 * 考核评分
	 */	
	private List<CheckScore> csList = new ArrayList<CheckScore>();
	/**
	 * 总分
	 */	
	private int totalPoints;
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<CheckScore> getCsList() {
		return csList;
	}
	public void setCsList(List<CheckScore> csList) {
		this.csList = csList;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}	
	
}
