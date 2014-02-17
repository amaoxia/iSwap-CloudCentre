package com.ligitalsoft.datasharexchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.ExcelToDbinfoDao;
import com.ligitalsoft.datasharexchange.service.IExcelToDbinfoService;
import com.ligitalsoft.model.changemanage.ExcelToDbinfo;

@Service("excelToDbinfoService")
public class ExcelToDbinfoServiceImpl extends BaseSericesImpl<ExcelToDbinfo>
		implements IExcelToDbinfoService {

	private ExcelToDbinfoDao excelToDbinfoDao;

	@Override
	public EntityHibernateDao<ExcelToDbinfo> getEntityDao() {
		return excelToDbinfoDao;
	}

	@Autowired
	public void setExcelToDbinfoDao(ExcelToDbinfoDao excelToDbinfoDao) {
		this.excelToDbinfoDao = excelToDbinfoDao;
	}
}
