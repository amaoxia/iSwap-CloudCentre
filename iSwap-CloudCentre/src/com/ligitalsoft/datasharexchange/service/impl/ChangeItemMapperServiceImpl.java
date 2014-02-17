package com.ligitalsoft.datasharexchange.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.ChangeItemMapperDao;
import com.ligitalsoft.datasharexchange.dao.MapperDetailsDao;
import com.ligitalsoft.datasharexchange.service.IChangeItemMapperService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.changemanage.ChangeItemMapper;
import com.ligitalsoft.model.changemanage.ChangeItemMapperDetails;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;
import com.ligitalsoft.model.changemanage.Mapper;

@Service("changeItemMapperService")
public class ChangeItemMapperServiceImpl extends BaseSericesImpl<ChangeItemMapper>
		implements IChangeItemMapperService {
	@Autowired
	private ChangeItemMapperDao mapperDao;
	@Autowired
	private MapperDetailsDao mapperDetailsDao;
	@Autowired
	protected DozerBeanMapper mapperValue;

	@Override
	public EntityHibernateDao<ChangeItemMapper> getEntityDao() {
		return mapperDao;
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
				ChangeItemMapper mapper = this.findById(ids[i]);
				mapper.setStatus(status);
				String keyName = mapper.getMapName()+"#"+mapper.getMapCode();
				if ("0".equals(status)) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("key", keyName);
					tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.mappingDB, map);
					this.update(mapper);
				} else {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("key", keyName);
					tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.mappingDB, map);
					map.put("type", "node");
					String xml = generateMapperXml(mapper);
					mapper.setContents(xml);
					map.put("xml", mapper.getContents());
					tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.mappingDB, map);
					this.update(mapper);
				}
			}   
		}
		tool.closeFileDB();
	}
	
	private String generateMapperXml(ChangeItemMapper mapper){
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>")
			.append("<Mapping>")
				.append("<MapTable>");
		List<ChangeItemMapperDetails> mapperDetailsList = mapper.getMapperDetailsList();
		if(mapperDetailsList!=null&&mapperDetailsList.size()>0){
			for(ChangeItemMapperDetails mapperDetails : mapperDetailsList){
				ChangeTableDesc srcTableDesc = mapperDetails.getSrcTableDesc();
				ChangeTableDesc tarTableDesc = mapperDetails.getTarTableDesc();
				xml.append("<Link from=\"").append(srcTableDesc.getName()).append("\" ")
				.append("to=\"").append(tarTableDesc.getName()).append("\" ")
				.append("type=\"").append(tarTableDesc.getDataType()).append("\" ")
				.append("isbuild=\"\" ")
				.append("ispk=\"").append(tarTableDesc.getIsPk()).append("\" ")
				.append("value=\"\" />");
			}
		}
		xml.append("</MapTable>")
		.append("</Mapping>");
		return xml.toString();
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
	@Override
	public Mapper save(ChangeItemMapper mapper) {
		if(mapper.getId()==null){
			mapperDao.save(mapper);
		}else{
			Mapper entity = mapperDao.findById(mapper.getId());
			entity.setMapName(mapper.getMapName());
			mapperDetailsDao.removeAllByMapperId(mapper.getId());
		}
		List<ChangeItemMapperDetails> mapperDetailsList = mapper.getMapperDetailsList();
		if(mapperDetailsList!=null&&mapperDetailsList.size()>0){
			for(ChangeItemMapperDetails mapperDetails : mapperDetailsList){
				mapperDetails.setMapper(mapper);
				mapperDetailsDao.save(mapperDetails);
			}
		}
		return mapper;
	}
	
	public MapperDetailsDao getMapperDetailsDao() {
		return mapperDetailsDao;
	}
}
