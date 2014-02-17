package com.ligitalsoft.iswapmq.service;

import com.common.framework.services.IBaseServices;
import com.ligitalsoft.model.serverinput.FtpServerInfo;

public interface IFtpService extends IBaseServices<FtpServerInfo> {
	/**
	 * ftp测试连接
	 * @author fangbin
	 * @param ftpserver
	 */
	public boolean testFTP(FtpServerInfo ftpserver);
	/**
	 * 更改ftp状态
	 * @author fangbin
	 */
	public void updateStatus(FtpServerInfo ftpserver);
}
