package com.ligitalsoft.help.systemInit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.cachetool.CacheTool;
import com.common.framework.help.SpringContextHolder;
import com.common.jobtool.JobTool;
import com.ligitalsoft.help.ISwapESBStart;
import com.ligitalsoft.help.task.DeleteReceiveDataLogJob;
import com.ligitalsoft.help.task.ReceiveDataLogJob;
import com.ligitalsoft.help.task.SendDataFomatResultJob;
import com.ligitalsoft.help.task.SendDataLogJob;
import com.ligitalsoft.help.task.SendMessageLogJob;

/**
 * 初始化iSwap
 * 
 * @Company 北京光码软件有限公司
 * @author hudaowan
 * @version iSwap V5.0
 * @date Aug 14, 2008 5:56:43 PM
 * @Team 数据交换平台研发小组
 */
@Service("initiSwap")
public class InitiSwap {
	private final Log log = LogFactory.getLog(this.getClass());
	private static InitiSwap obj = null;
	@Autowired
	private ISwapESBStart iSwapESBStart;
	@Autowired
    private SpringContextHolder springContextHolder;
	private JobTool jobTool = JobTool.init();

	public static InitiSwap init() {
		if (obj == null) {
			obj = new InitiSwap();
		}
		return obj;
	}

	/**
	 * 初始化系统
	 * 
	 * @author hudaowan
	 * @date Aug 14, 2008 5:58:47 PM
	 */
	public void initSystem() {
		try {
			log.info("开始启动云中心......");
			log.info("---------------------------------------------------");
			springContextHolder.getApplicationContext();
			log.info("开始启动分布式缓存......");
			CacheTool.init();
			log.info("缓存启动完毕！");
			log.info("----------------------------------------------------");
			log.info("启动任务.......");
			this.InitJob();
			log.info("任务启动完毕！");
			iSwapESBStart.startiSwapESB();
			log.info("----------------------------------------------------");
			log.info("云中心服务启动成功！");

			log.info("----------------------------------------------------");
		} catch (Exception e) {
			log.error("系统初始化失败！", e);
			System.exit(1);
		}
	}

	/**
	 * 初始化任务类
	 */
	public void InitJob() {
		jobTool.addJob("receiveDataLogJob", "0 10 4 * * ?",
				ReceiveDataLogJob.class);
		log.info("接收日志任务##ReceiveDataLogJob##每天凌晨4:10分开始执行");
		jobTool.addJob("sendDataLogJob", "0 30 4 * * ?", SendDataLogJob.class);
		log.info("发送日志任务##SendDataLogJob##每天凌晨4:30分开始执行");
		jobTool.addJob("sendDataFomatResultJob", "0 50 4 * * ? ",
				SendDataFomatResultJob.class);
		log.info("数据格式日志任务##SendDataFomatResultJob##每天凌晨4:50分开始执行");
		jobTool.addJob("deleteReceiveDataLogJob", "0 0 3 * * ? ",
				DeleteReceiveDataLogJob.class);
		log.info("删除接收数据任务##DeleteReceiveDataLogJob##每天凌晨3:00分开始执行");
		jobTool.addJob("sendMessageLogJob", "0 0 2 * * ? ",
				SendMessageLogJob.class);
		log.info("短信日志任务##SendMessageLogJob##每天凌晨2:00分开始执行");
	}
}
