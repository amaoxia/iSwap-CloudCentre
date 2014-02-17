package com.ligitalsoft.cloudnode.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.tree.jstree.Data;
import com.common.utils.tree.jstree.JsTreeFactory;
import com.common.utils.tree.jstree.Node;
import com.ligitalsoft.cloudnode.dao.DataSourceDao;
import com.ligitalsoft.cloudnode.service.IDataSourceService;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.model.cloudnode.DataSource;
import com.ligitalsoft.webservices.ISwapNodeWS;

@Service("datasourceService")
public class DataSourceServiceImpl extends BaseSericesImpl<DataSource> implements IDataSourceService {
	@Autowired
	private DataSourceDao dataSourceDao;
	@Autowired
	protected DozerBeanMapper mapperValue;

	@Override
	public EntityHibernateDao<DataSource> getEntityDao() {
		return dataSourceDao;
	}

	@Transactional(readOnly = true)
	public String dataSourceTree() {
		List<DataSource> list = this.findAll();// 查询根节点
		Node root = new Node();
		Data data = new Data();
		root.setState(Node.NODE_STATE_OPEN);
		data.setTitle("数据源中心");
		root.setData(data);
		root.getAttr().setId("0");//
		root.getAttr().setRel(Node.NODE_TYPE_ROOT);// 根节点
		createTreeNode(list, root);
		if (root.getChildren().size() == 0) {
			root.setState(Node.NODE_STATE_CLOSE);
		}
		return JsTreeFactory.newInstance().createJsTree(root);
	}

	/**
	 * 树形递归实现
	 * 
	 * @param listDataSource
	 * @param root
	 * @author zhangx
	 */
	@Transactional(readOnly = true)
	private void createTreeNode(List<DataSource> listDataSource, Node root) {
		for (DataSource dataSource : listDataSource) {
			Node node = new Node();
			node.getAttr().setId(dataSource.getId() + "");
			Data data = new Data();
			data.setTitle(dataSource.getSourceName());
			node.setData(data);
			root.getChildren().add(node);
		}
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

    /**
     * 修改缓存中的数据源信息
     * @param entity 
     * @author  hudaowan
     * @date 2011-9-4 下午05:32:50
     */
    public void updateStatus(DataSource entity) {
    	String keyName = entity.getSourceName()+"#"+entity.getSourceCode();
    	FileDBTool tool = FileDBTool.init();
    	tool.getMongoConn();
    	if("0".equals(entity.getStatus())){
    		Map<String,Object> map = new HashMap<String,Object>();
			map.put("key", keyName);
			tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
    	}
    	if("1".equals(entity.getStatus())){
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("key", keyName);
    		tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
			map.put("driveName", entity.getDriveName());
			map.put("address", entity.getAddress());
			//如果是MYSQL，可能会遇到编码问题
			if("MYSQL".equals(entity.getType()) && (entity.getCharacterEncoding()!=null || !"".equals(entity.getCharacterEncoding()))){
				map.put("address", entity.getAddress()+"?useUnicode=true&characterEncoding="+entity.getCharacterEncoding());
			}
			map.put("userName", entity.getUserName());
			map.put("passWord", entity.getPassWord());
			map.put("type", "node");
			map.put("deptId",""+entity.getSysDept().getId().longValue());
			map.put("deptName", entity.getSysDept().getDeptName());
			tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.dataSourceDB, map);
    	}
    	tool.closeFileDB();
    } 

	@Override
	public String testDataSource(Long id) throws  ServiceException{
		DataSource entity = dataSourceDao.findById(id);
		String ip = entity.getCloudNodeInfo().getAddress();
		String port = entity.getCloudNodeInfo().getPort();
		String url = "http://" + ip + ":" + port+ "/iswapnode/webservice/iSwapNodeWS";
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ISwapNodeWS.class);
		factory.setAddress(url);
		System.setProperty("org.apache.cxf.bus.factory","org.apache.cxf.bus.CXFBusFactory");
		ISwapNodeWS iswapNodeWs = (ISwapNodeWS)factory.create();
		String keyName = entity.getSourceName()+"#"+entity.getSourceCode();
		String msg = iswapNodeWs.testDataSource(keyName);
		return msg;
	}
	
	public DataSource findDatasourceIsExit(String ip,String port,String dbName){
		return  dataSourceDao.findDatasourceIsExit(ip, port, dbName);
	}
	public List<DataSource> findDataSourcesByDept(String status,Long deptId){
		return dataSourceDao.findDataSourcesByDept(status, deptId);
	}
}
