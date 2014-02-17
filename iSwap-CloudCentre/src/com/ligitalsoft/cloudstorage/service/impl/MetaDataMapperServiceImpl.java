package com.ligitalsoft.cloudstorage.service.impl;

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
import com.ligitalsoft.cloudstorage.dao.MetaDataMapperDao;
import com.ligitalsoft.cloudstorage.dao.MetaDataMapperDetailsDao;
import com.ligitalsoft.cloudstorage.service.IMetaDataMapperService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.changemanage.ChangeTableDesc;
import com.ligitalsoft.model.changemanage.Mapper;
import com.ligitalsoft.model.cloudstorage.MetaDataMapper;
import com.ligitalsoft.model.cloudstorage.MetaDataMapperDetails;
import com.ligitalsoft.model.cloudstorage.TableInfo;

@Service("metaDataMapperService")
public class MetaDataMapperServiceImpl extends BaseSericesImpl<MetaDataMapper>
		implements IMetaDataMapperService {
	@Autowired
	private MetaDataMapperDao mapperDao;
	@Autowired
	private MetaDataMapperDetailsDao mapperDetailsDao;
	@Autowired
	protected DozerBeanMapper mapperValue;

	@Override
	public EntityHibernateDao<MetaDataMapper> getEntityDao() {
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
				MetaDataMapper mapper = this.findById(ids[i]);
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
	
	private String generateMapperXml(MetaDataMapper mapper){
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>")
			.append("<Mapping>")
				.append("<MapTable>");
		List<MetaDataMapperDetails> mapperDetailsList = mapper.getMapperDetailsList();
		if(mapperDetailsList!=null&&mapperDetailsList.size()>0){
			for(MetaDataMapperDetails mapperDetails : mapperDetailsList){
				ChangeTableDesc srcTableDesc = mapperDetails.getSrcTableDesc();
				TableInfo tarTableInfo = mapperDetails.getTarTableInfo();
				xml.append("<Link from=\"").append(srcTableDesc.getName()).append("\" ")
				.append("to=\"").append(tarTableInfo.getName()).append("\" ")
				.append("type=\"").append(tarTableInfo.getDataType()).append("\" ")
				.append("isbuild=\"\" ")
				.append("ispk=\"").append(tarTableInfo.getIsPk()).append("\" ")
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
	public Mapper save(MetaDataMapper mapper) {
		if(mapper.getId()==null){
			mapperDao.save(mapper);
		}else{
			Mapper entity = mapperDao.findById(mapper.getId());
			entity.setMapName(mapper.getMapName());
			mapperDetailsDao.removeAllByMapperId(mapper.getId());
		}
		List<MetaDataMapperDetails> mapperDetailsList = mapper.getMapperDetailsList();
		if(mapperDetailsList!=null&&mapperDetailsList.size()>0){
			for(MetaDataMapperDetails mapperDetails : mapperDetailsList){
				mapperDetails.setMapper(mapper);
				mapperDetailsDao.save(mapperDetails);
			}
		}
		return mapper;
	}
	
	public MetaDataMapperDetailsDao getMapperDetailsDao() {
		return mapperDetailsDao;
	}
}
