package com.ligitalsoft.util;

/**
 * @author daic
 */
public interface Constant {
	/**
	 * 发送状态 1 按时发送(绿灯) 2 延时发送(黄灯) 3 超时发送(红灯)
	 */
	int SEND_STATE_NONE = 0;

	int SEND_STATE_GREEN = 1;

	int SEND_STATE_YELLOW = 2;

	int SEND_STATE_RED = 3;

	String NEW_MSG = "1";

	String USE_DEFAULT_RULE = "1";

	String MANAGE_NAME = "系统管理员";

	/**
	 * 交换周期
	 */
	int EXCHANGE_CYCLE_MONTH = 1;

	int EXCHANGE_CYCLE_QUARTER = 2;

	int EXCHANGE_CYCLE_YEAR = 3;

	/**
	 * 接收状态 1接收完成 2接收有问题 3未接收
	 */
	int RECE_STATE_RED = 3;

	int RECE_STATE_YELLOW = 2;

	int RECE_STATE_GREEN = 1;

	/**
	 * 任务状态
	 */
	String TASK_STATE_FINISHED = "1";

	String TASK_STATE_UNFINISHED = "0";

	int[][] QUARTER = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };

	int MONTH = 1;

	int YEAR = 2;

	int TIMEZONE = 3;

	/**
	 * 管理部门的ID
	 */
	String MANAGE_DEPT_ID = "1";
	
	/**
	 * 系统管理员
	 */
	String SYSTEM_MANAGER = "admin";

	String NO_CORRECT = "0";

	String CORRECTED = "1";

	/**
	 * 设置页面大小
	 */
	int PAGESIZE = 10;

	/**
	 * 各催办方式的ID：1 系统消息 2 邮件 3 电话 4 短信
	 */
	int SYSTEMINFOMATION_TRANSACT_ID = 1;

	/**
	 * 各催办方式的ID 2 邮件
	 */
	int EMAIL_TRANSACT_ID = 2;

	/**
	 * 各催办方式的ID：3 电话
	 */
	int PHONE_TRANSACT_ID = 3;//

	/**
	 * 各催办方式的ID：4 短信
	 */
	int NOTE_TRANSACT_ID = 4;

	/** 默认规则 */
	String DEFAULT_RULE = "0";

	/** 非默认规则 */
	String NOT_DEFAULT_RULE = "1";

	/** 交换前 */
	String EXCHANGE_FRONT = "0";

	/** 交换后 */
	String EXCHANGE_BACK = "1";

	/**
	 * 未读消息
	 */
	String MSG_IS_NEW = "1";

	/**
	 * 已读消息
	 */
	String MSG_NOT_NEW = "0";

	/**
	 * 要回复消息
	 */
	String MSG_IS_REPLY = "1";

	/**
	 * 不用回复消息
	 */
	String MSG_NOT_REPLY = "0";
	
	/**
	 * 数据库IP标记
	 */
	String DB_IP_TAG = "<IP>";
	/**
	 * 数据库连接端口标记
	 */
	String DB_PORT_TAG = "<PORT>";
	/**
	 * 数据库实例名标记
	 */
	String DB_NAME_TAG = "<INST>";
	/**
	 * 数据库连接参数模版数组
	 */
	String[] DB_ARGS_ARRAYS = {DB_IP_TAG,DB_PORT_TAG,DB_NAME_TAG};
	
	/**
	 * 交换备份库表表命名规则
	 */
	String EXCHANGE_BAK_TAB_NAME_RULE = "exchange_bak_tab_name_rule";
	
	/**
	 * 已发布
	 */
	String IS_PUBLISHED = "1";
	/**
	 * 未发布，取消发布
	 */
	String IS_NOT_PUBLISHED = "0";
	
	/**
	 * 前置机服务名称
	 */
	String ISWAP_SERVICE_NAME = "/iswap/service/ISwapMonitor?wsdl";
	
	/**前置机监控中心页面显示个数设置*/
	public String ISWAP_STATUS_VIEW_NUMS = "iswap_status_view_nums";
	
	/**系统初始密码*/
	static final String INIT_PASSWORD = "111111";
	/**应用系统代码KEY*/
	static final String SYS_CODE_BY_CODE = "SYS_CODE";	
	
	/**
	 * 删除标记 1:已删除 0:未删除
	 */
	static final Integer ISDELETED = 1;
	static final Integer ISNOTDELETED = 0;
}
