package com.ligitalsoft.workflow.plugin.ftpnode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPClient;
import org.jbpm.api.activity.ActivityExecution;

import com.common.config.ConfigAccess;
import com.common.dbtool.FileDBTool;
import com.ligitalsoft.help.filedb.FileDBConstant;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

public class FTPPutAction extends PluginActionHandler {
	private static final long serialVersionUID = -1679614493161708280L;

	private String ftpserverName;
	private String fileName;
	private String inVarData_inputVar;// 输入的值
	private String putData_outVar;// 传出的值
	private String filePath;// 文件输出路径

	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始将文件写入FTP目录......");
		List<DataPackInfo> dpList = (List<DataPackInfo>) this
				.getCacheInfo(inVarData_inputVar);
		if (null != dpList && dpList.size() > 0) {
			FileDBTool tool = FileDBTool.init();
			FTPClient ftpClient = null;
			Map<String, Object> find_map = new HashMap<String, Object>();
			find_map.put("key", ftpserverName);
			try {
				tool.getMongoConn();
				List<Map<String, Object>> entityList = tool.findToFiledb(
						FileDBConstant.fileDBName, FileDBConstant.ftpServerDB,
						find_map);
				if (entityList.size() > 0) {
					Map<String, Object> entity = entityList.get(0);
					ftpClient = this.connFtpServer(entity);
					String filename = (String) context.getVariable(fileName);
					Matcher m = Pattern.compile("^([^\\.]+)(.*)$").matcher(
							filename);
					if (m.find());
					for (int n = 0; n < dpList.size(); n++) {
						DataPackInfo dpi = dpList.get(n);
						if (dpi.getByteVal().length > 0) {
							this.sendFtpData(ftpClient, m,
									entity.get("filePath").toString(),
									dpi.getByteVal(), n);
						}
					}

					log.info("将文件写入FTP目录完成......");
				} else {
					log.error("【" + ftpserverName + "】没有在【"
							+ FileDBConstant.ftpServerDB + "】中找到！");
				}
			} catch (Exception e) {
				ByteArrayOutputStream bo = new ByteArrayOutputStream();
				e.printStackTrace(new PrintStream(bo));
				log.error("向FTP写文件失败！", e);
				throw new ActionException(e);
			} finally {
				tool.closeFileDB();
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					log.error("向FTP关闭连接失败！", e);
					e.printStackTrace();
				}
			}
		} else {
			log.info("FTP节点没有获得相关数据!");
		}

	}

	/**
	 * 连接FTPServer
	 * 
	 * @author hudaowan
	 * @date 2009-12-6 上午11:27:04
	 * @param ftp
	 * @return
	 * @throws Exception
	 */
	private synchronized FTPClient connFtpServer(Map<String, Object> entity)
			throws Exception {
		FTPClient ftpClient = new FTPClient();
		String hostName = (String) entity.get("ftp_ip");
		String userName = (String) entity.get("userName");
		String password = (String) entity.get("password");
		String port = (String) entity.get("ftp_port");
		try {
			ftpClient.connect(hostName, Integer.parseInt(port));
			ftpClient.setControlEncoding("GBK");
			ftpClient.login(userName, password);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		} catch (Exception e) {
			log.error("连接FTPServer失败！" + e);
			throw new Exception(e);
		}
		return ftpClient;
	}

	/**
	 * 向FTPServer上面发送数据
	 * 
	 * @author hudaowan
	 * @date 2009-12-5 下午04:55:21
	 * @param ftpClient
	 * @param folderName
	 * @param fileName
	 * @param msg
	 * @return
	 */
	private synchronized boolean sendFtpData(FTPClient ftpClient, Matcher m,
			String folderName, byte[] msg, int n) throws Exception {
		boolean flag = false;
		if (msg != null) {
			if (msg.length > 0) {
				String ftpPutPath = ConfigAccess.init().findProp("ftpPutpath");
				InputStream iStream = new ByteArrayInputStream(msg);
				// String uuid = DateUtil.getUniqueDateTime();
				// String ftpFileName = m.group(1) + "_" + uuid + m.group(2);
				String[] folders = ftpPutPath.split("/");
				try {
					for (String folder : folders) {
						ftpClient.makeDirectory(folder);
						ftpClient.changeWorkingDirectory(folder);
					}
					ftpClient.storeFile(m.group(1) + m.group(2), iStream);
					iStream.close();
					log.info("第【" + n + 1 + "】个文件已经发送到FTPSever，文件名：【"
							+ m.group(1) + "】");
					flag = true;
				} catch (Exception e) {
					iStream.close();
					log.error("向FTPServer发送数据失败！" + e);
					throw new Exception(e);
				}
			}
		}
		return flag;
	}
}
