package com.ligitalsoft.cloudcenter.service;

import java.util.List;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.cloudcenter.CouldNodeDept;

public interface ICouldNodeDeptService extends IBaseServices<CouldNodeDept> {
	public List<CouldNodeDept> findDeptByDeptId(Long deptId);

}
