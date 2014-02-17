package com.ligitalsoft.cloudnode.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.cloudnode.dao.MappingFileDao;
import com.ligitalsoft.cloudnode.service.IMappingFileService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudnode.MappingFile;

@Service("mappingfileService")
public class MappingFileServiceImpl extends BaseSericesImpl<MappingFile>
		implements IMappingFileService {
	@Autowired
	private MappingFileDao mappingfileDao;
	@Autowired
	protected DozerBeanMapper mapperValue;

	@Override
	public EntityHibernateDao<MappingFile> getEntityDao() {
		return mappingfileDao;
	}
	/**
	 * 更改发布状态
	 * @author fangbin
	 * @param ids
	 * @param status
	 * @throws ServiceException
	 */
	public void updateStatus(Long[] ids,String status) throws ServiceException{
		FileDBTool tool = FileDBTool.init();
		tool.getMongoConn();
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				MappingFile mappingFile = this.findById(ids[i]);
				mappingFile.setStatus(status);
				String keyName = mappingFile.getMapName()+"#"+mappingFile.getMapCode();
				if ("0".equals(status)) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("key", keyName);
					tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.mappingDB, map);
					this.update(mappingFile);
				} else {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("key", keyName);
					tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.mappingDB, map);
					map.put("type", "node");
					map.put("deptId",""+mappingFile.getDept().getId().intValue());
					map.put("deptName",mappingFile.getDept().getDeptName());
					map.put("xml", mappingFile.getContents());
					tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.mappingDB, map);
					this.update(mappingFile);
				}
			}   
		}
		tool.closeFileDB();
	}
	/**
	 * 完成对象之间拷贝
	 * 
	 * @author hudaowan
	 *@date 2010-9-15 下午12:46:16
	 *@param source
	 *@param destination
	 */
	protected void doValueCopy(Object source, Object destination) {// 将源对象中的值copy到目标对象中
		this.mapperValue.map(source, destination);
	}
}
