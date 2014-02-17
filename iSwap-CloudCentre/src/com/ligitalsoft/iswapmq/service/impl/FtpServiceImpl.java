package com.ligitalsoft.iswapmq.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dbtool.FileDBTool;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.iswapmq.dao.FtpServiceDao;
import com.ligitalsoft.iswapmq.service.IFtpService;
import com.ligitalsoft.model.serverinput.FtpServerInfo;

@Transactional
@Service(value = "ftpService")
public class FtpServiceImpl extends BaseSericesImpl<FtpServerInfo> implements
		IFtpService {

	@Autowired
	private FtpServiceDao ftpServiceDao;

	@Override
	public EntityHibernateDao<FtpServerInfo> getEntityDao() {
		return this.ftpServiceDao;
	}

	/**
	 * ftp测试连接
	 * 
	 * @author fangbin
	 */
	@Override
	public boolean testFTP(FtpServerInfo ftpserver) {
		boolean flag=false;
		String hostName = ftpserver.getAddress();
		String port = ftpserver.getPort();
		String username = ftpserver.getUserName();
		String password = ftpserver.getPassword();
		FTPClient ftpclient = new FTPClient();

		try {
			if (!StringUtils.isBlank(hostName) && !StringUtils.isBlank(port)) {
				ftpclient.connect(hostName, Integer.parseInt(port));
			}
		} catch (Exception e) {
			logger.error("FTP服务的端口或IP不正确!");
		}
		ftpclient.setControlEncoding("GBK");
		try {
			ftpclient.login(username, password);
			flag= true;
		} catch (IOException e) {
			logger.error("FTP服务的用户名或密码不正确", e);
		}
		return flag;
	}

	@Override
	public void updateStatus(FtpServerInfo ftpserver) {
		if(null!=ftpserver){
			FileDBTool tool = FileDBTool.init();
			tool.getMongoConn();
			if(1==ftpserver.getStatus()){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("ftp_ip", ftpserver.getAddress()); 
				map.put("ftp_port", ftpserver.getPort());
				map.put("filePath", ftpserver.getFilePath());
				map.put("userName", ftpserver.getUserName());
				map.put("password", ftpserver.getPassword());
				map.put("key", ftpserver.getFtpServerName()+"#"+ftpserver.getId());
				tool.saveToFiledb(FileDBConstant.fileDBName, FileDBConstant.ftpServerDB, map);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("key", ftpserver.getFtpServerName()+"#"+ftpserver.getId());
				tool.deleteToFiledb(FileDBConstant.fileDBName, FileDBConstant.ftpServerDB, map);
			}
			tool.closeFileDB();
		}
		
	}

}
