package com.ligitalsoft.datasharexchange.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDao;
import com.ligitalsoft.datasharexchange.dao.ExchangeSendTaskDao;
import com.ligitalsoft.datasharexchange.dao.ExchangeSystemConfigDao;
import com.ligitalsoft.datasharexchange.dao.ExchangeTransactDao;
import com.ligitalsoft.datasharexchange.dao.ExchangeTransactTypeDao;
import com.ligitalsoft.datasharexchange.service.IListenerService;
import com.ligitalsoft.datasharexchange.service.ITaskService;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ExchangeSendTask;
import com.ligitalsoft.model.changemanage.ExchangeSystemConfig;
import com.ligitalsoft.model.changemanage.ExchangeTransact;
import com.ligitalsoft.model.system.SysDept;
import com.ligitalsoft.model.system.SysUser;
import com.ligitalsoft.model.system.SysUserDept;
import com.ligitalsoft.sysmanager.dao.SysUserDao;
import com.ligitalsoft.sysmanager.dao.SysUserDeptDao;
import com.ligitalsoft.sysmanager.service.ISysDeptService;
import com.ligitalsoft.util.Constant;
import com.ligitalsoft.util.ExchangeDateUtil;

@Service(value = "listenerService")
public class ListenerServiceImpl extends BaseSericesImpl<ExchangeSendTask> implements IListenerService, Constant {
	private ExchangeSystemConfigDao exchangeSystemConfigDao;
	private ChangeItemDao changeItemDao;
	private ExchangeSendTaskDao exchangeSendTaskDao;
	private ITaskService taskService;
	private ISysDeptService sysDeptService;
	private ExchangeTransactDao exchangeTransactDao;
	private ExchangeTransactTypeDao exchangeTransactTypeDao;
	private SysUserDeptDao sysUserDeptDao;
	private SysUserDao sysUserDao;
	
	@Autowired
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	@Autowired
	public void setSysUserDeptDao(SysUserDeptDao sysUserDeptDao) {
		this.sysUserDeptDao = sysUserDeptDao;
	}
	@Autowired
	public void setExchangeTransactTypeDao(
			ExchangeTransactTypeDao exchangeTransactTypeDao) {
		this.exchangeTransactTypeDao = exchangeTransactTypeDao;
	}
	@Autowired
	public void setExchangeTransactDao(ExchangeTransactDao exchangeTransactDao) {
		this.exchangeTransactDao = exchangeTransactDao;
	}
	@Autowired
	public void setSysDeptService(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}
	@Autowired
	public void setExchangeSendTaskDao(ExchangeSendTaskDao exchangeSendTaskDao) {
		this.exchangeSendTaskDao = exchangeSendTaskDao;
	}
	@Autowired
	public void setExchangeSystemConfigDao(
			ExchangeSystemConfigDao exchangeSystemConfigDao) {
		this.exchangeSystemConfigDao = exchangeSystemConfigDao;
	}
	@Autowired
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
	/**
	 * 根据指定规则获得出是哪一天
	 * 
	 * @param rule
	 * @return
	 */
	protected Date getDayFromRule(Date execDate, String[] rule) {
		Date date = null;
		if (rule[0].equalsIgnoreCase(EXCHANGE_BACK)) {// 交换周期后的话加n天
			date = ExchangeDateUtil.addDay(execDate, Long.parseLong(rule[1]));
		} else if (rule[0].equalsIgnoreCase(EXCHANGE_FRONT)) {// 交换周期前的话减n天
			date = ExchangeDateUtil.reduceDay(execDate, Integer.parseInt(rule[1]));
		}
		return date;
	}

	/**
	 * 分析指标项是否到期
	 * 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public void analyseTask() {
		Date now = ExchangeDateUtil.strToDate(ExchangeDateUtil.getDate(), "yyyy-MM-dd");
		// 取出所有未完成的任务
		List<ExchangeSendTask> taskList = exchangeSendTaskDao.findListByHql(
				"from ExchangeSendTask st where st.finishedState = ?",
				new Object[] { String.valueOf(TASK_STATE_UNFINISHED) });
		ExchangeSystemConfig sc = exchangeSystemConfigDao.getSystemConfigInfo();
		String[] greenRule = sc.getRuleNotify().split(",");
		String[] yellowRule = sc.getRuleYellowNotify().split(",");
		String[] redRule = sc.getRuleRedNotify().split(",");

		for (ExchangeSendTask st : taskList) {

			ChangeItem item = st.getItem();
			Date execDate = st.getExecDate();

			Date greenDay = getDayFromRule(execDate, greenRule);
			Date yellowDay = getDayFromRule(execDate, yellowRule);

			String gDay = ExchangeDateUtil.formatDate(greenDay, "yyyy-MM-dd");
			String yDay = ExchangeDateUtil.formatDate(yellowDay, "yyyy-MM-dd");
			String today = ExchangeDateUtil.formatDate(now, "yyyy-MM-dd");

			// 已有发送数据后不提醒
			if (st.getDataSends() == null || st.getDataSends().isEmpty()) {
				if ("0".equals(yellowRule[0])) {
					// 如果当天有红绿黄灯到期，就发送提醒或催办消息
					if (now.equals(greenDay)) {
						transactTask(item, st, SEND_STATE_GREEN); // 发送催办消息
					} else if (now.equals(yellowDay)) {
						if(!st.getSendState().equalsIgnoreCase(String.valueOf(SEND_STATE_YELLOW)))
						transactTask(item, st, SEND_STATE_YELLOW); // 发送催办消息
					} else if (now.equals(ExchangeDateUtil.addDay(execDate, 1))) {
						 if(!st.getSendState().equalsIgnoreCase(String.valueOf(SEND_STATE_RED)))
						transactTask(item, st, SEND_STATE_RED); // 发送催办消息
					}

					if ((gDay.equals(today) || now.after(greenDay))
							&& now.before(yellowDay)) {
						// 如果不是绿灯就改成绿灯
						if (Integer.parseInt(st.getSendState()) != SEND_STATE_GREEN) {
							st.setSendState(String.valueOf(SEND_STATE_GREEN));
							exchangeSendTaskDao.update(st);
						}
					} else if ((yDay.equals(today) || now.after(yellowDay))
							&& (now.before(execDate) || today.equals(ExchangeDateUtil
									.formatDate(execDate, "yyyy-MM-dd")))) {
						// 如果不是黄灯就改成黄灯
						if (Integer.parseInt(st.getSendState()) != SEND_STATE_YELLOW) {
							st.setSendState(String.valueOf(SEND_STATE_YELLOW));
							exchangeSendTaskDao.update(st);
						}
					} else if (now.after(execDate)) {
						// 如果不是红灯就改成红灯
						if (Integer.parseInt(st.getSendState()) != SEND_STATE_RED) {
							st.setSendState(String.valueOf(SEND_STATE_RED));
							exchangeSendTaskDao.update(st);
						}
					}
				} else if ("1".equals(yellowRule[0])) {// 黄灯交换规则为执行日期后
					// 如果当天有红绿黄灯到期，就发送提醒或催办消息
					if (now.equals(greenDay)) {// 当前日期为绿灯规则日期，发绿灯提醒规则准备数据
					if(!st.getSendState().equalsIgnoreCase(String.valueOf(SEND_STATE_GREEN)))
						transactTask(item, st, SEND_STATE_GREEN); // 发送催办消息
					} else if (now.equals(ExchangeDateUtil.addDay(execDate, 1))) {// 当前日期为执行日期后一天时发黄灯催办消息
						 if(!st.getSendState().equalsIgnoreCase(String.valueOf(SEND_STATE_YELLOW)))
						transactTask(item, st, SEND_STATE_YELLOW); // 发送催办消息
					} else if (now.equals(ExchangeDateUtil.addDay(yellowDay, 1))) {// 当前日期为黄灯规定日期后一天时发红灯催办消息
						 if(!st.getSendState().equalsIgnoreCase(String.valueOf(SEND_STATE_RED)))
						transactTask(item, st, SEND_STATE_RED); // 发送催办消息
					}

					// 当天为绿灯执行日期或在绿灯日期之后并且当前日期在执行日期之前或当前日期就是执行日期
					if ((gDay.equals(today) || now.after(greenDay))
							&& (now.before(execDate) || today.equals(ExchangeDateUtil
									.formatDate(execDate, "yyyy-MM-dd")))) {
						// 如果不是绿灯就改成绿灯
						if (Integer.parseInt(st.getSendState()) != SEND_STATE_GREEN) {
							st.setSendState(String.valueOf(SEND_STATE_GREEN));
							exchangeSendTaskDao.update(st);
						}
					} else if (now.after(execDate)
							&& (now.before(yellowDay) || today.equals(yDay))) {
						// 如果不是黄灯就改成黄灯
						if (Integer.parseInt(st.getSendState()) != SEND_STATE_YELLOW) {
							st.setSendState(String.valueOf(SEND_STATE_YELLOW));
							exchangeSendTaskDao.update(st);
						}
					} else if (now.after(yellowDay)) {
						// 如果不是红灯就改成红灯
						if (Integer.parseInt(st.getSendState()) != SEND_STATE_RED) {
							st.setSendState(String.valueOf(SEND_STATE_RED));
							exchangeSendTaskDao.update(st);
						}
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param item
	 * @param st
	 * @param lightState
	 */
	protected void transactTask(ChangeItem item, ExchangeSendTask st, int lightState){
		switch (lightState) {
		case SEND_STATE_GREEN:
			break;
		case SEND_STATE_YELLOW:
			break;
		case SEND_STATE_RED:
			break;
		}
		SysDept manageDept = null;
		try {
			manageDept = sysDeptService.findById(Long.valueOf(Constant.MANAGE_DEPT_ID) );
		} catch (ServiceException e) {
			e.printStackTrace();
		}// 取出管理部门
		
		// step1 : 更改亮灯状态为绿灯
		st.setSendState(String.valueOf(lightState));
		st.setTransactCount(st.getTransactCount() + 1);
		exchangeSendTaskDao.update(st);

		// step2 : 记录催办消息到数据库
		ExchangeTransact t = new ExchangeTransact();
		t.setDepartmentBySendDeptId(manageDept);
		t.setIsNew(NEW_MSG);
		t.setSendDate(DateUtil.strToDate(DateUtil.getDate()));
		t.setSendTask(st);
		t.setSendUsername(MANAGE_NAME);
		t.setNeedReply("0");
		String title = null;
		String content = null;
		switch (lightState) {
		case SEND_STATE_GREEN:
			title = item.getItemName() + "指标项将在"
					+ DateUtil.formatDate(st.getExecDate(), "yyyy年MM月dd日")
					+ "交换数据，请准备数据！";
			content = "您负责的" + item.getItemName() + "指标项将在"
					+ DateUtil.formatDate(st.getExecDate(), "yyyy年MM月dd日")
					+ "交换数据，请准备数据！\n\t" + MANAGE_NAME;
			break;
		case SEND_STATE_YELLOW:
			title = item.getItemName() + "指标项将在"
					+ DateUtil.formatDate(st.getExecDate(), "yyyy年MM月dd日")
					+ "交换数据，请准备数据！";
			content = "您负责的" + item.getItemName() + "指标项将在"
					+ DateUtil.formatDate(st.getExecDate(), "yyyy年MM月dd日")
					+ "交换数据，请准备数据！\n\t" + MANAGE_NAME;
			break;
		case SEND_STATE_RED:
			title = item.getItemName() + "指标项将在"
					+ DateUtil.formatDate(st.getExecDate(), "yyyy年MM月dd日")
					+ "交换数据，请准备数据！";
			content = "您负责的" + item.getItemName() + "指标项将在"
					+ DateUtil.formatDate(st.getExecDate(), "yyyy年MM月dd日")
					+ "交换数据，请准备数据！\n\t" + MANAGE_NAME;
			break;
		}
		t.setTitle(title);
		t.setContent(content); // get from property file
//		t.setTransactType(exchangeTransactTypeDao.findById(Long.valueOf(String
//				.valueOf(EMAIL_TRANSACT_ID))));// 通过发邮件方式催办
		exchangeTransactDao.save(t);

		// step3 : 发送邮件催办
		if (sendEmail(getEmailByItem(item), title, content))
			System.out.println("给【" + item.getItemName() + "】指标项发送提醒/催办消息成功！");
		else {
			System.out.println("给【" + item.getItemName() + "】指标项发送提醒/催办消息失败！");
		}
	}
	public boolean sendEmail(String target, String title, String content){
		return exchangeSystemConfigDao.sendEmail(target, title, content);
	}
	/**
	 * 通过指标项获得负责人邮箱
	 * 
	 * @param item
	 * @return
	 */
	protected String getEmailByItem(ChangeItem item)  {
		List<SysUserDept> sysUserDepts = sysUserDeptDao.findByDeptId(item.getSysDept().getId());
		String email = null;
		for (SysUserDept sysUserDept : sysUserDepts) {
			SysUser user = sysUserDao.findById(sysUserDept.getUserId());
				email = user.getEmail();
		}
		return email;
	}
	/**
	 * 获得某个月上个季度的起始截止日期
	 * 
	 * @param month
	 * @return
	 */
	protected String[] getQuarterArea(int month) {
		String result[] = null;
		// Date now = new Date();
		int year = Integer.parseInt(ExchangeDateUtil.getCurrentDate("yyyy"));
		switch (month) {
		case 1:
		case 2:
		case 3: {
			--year;
			result = new String[] { year + "-10-01 00:00:00",
					year + "-12-31 23:59:59" };
			break;
		}
		case 4:
		case 5:
		case 6: {
			result = new String[] { year + "-01-01 00:00:00",
					year + "-03-31 23:59:59" };
			break;
		}
		case 7:
		case 8:
		case 9: {
			result = new String[] { year + "-04-01 00:00:00",
					year + "-06-30 23:59:59" };
			break;
		}
		case 10:
		case 11:
		case 12: {
			result = new String[] { year + "-07-01 00:00:00",
					year + "-09-30 23:59:59" };
			break;
		}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void buildAllTask() {
		List<ChangeItem> itemlist = new ArrayList<ChangeItem>();
		itemlist = changeItemDao.findListByHql("from ChangeItem  i where i.sysDept.id is not null", null);

		Date startDate = ExchangeDateUtil.addMonth(new Date(), 1);

		String begin = ExchangeDateUtil.formatDate(startDate, "yyyy-MM");
		begin += "-01";
		int lastDay = ExchangeDateUtil.getLastDay(begin);
		String end = begin.substring(0, 8) + lastDay;
		try {
			taskService.buildTask(itemlist, begin, end);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void checkTaskListener() {
		Date startDate = ExchangeDateUtil.addMonth(new Date(), 1);

		String begin = ExchangeDateUtil.formatDate(startDate, "yyyy-MM");
		boolean flag = exchangeSendTaskDao.checkTask(begin);
		if (!flag) {
			buildAllTask();
		}
	}

	
	
	public void setObjs(List<Object[]> objs) {
		this.objs = objs;
	}

	public List<Object[]> getAllIswapStatus() {
		return this.objs;
	}
	

	public List<Object[]> getObjs() {
		return objs;
	}
	private List<Object[]> objs = new ArrayList<Object[]>();

	@Override
	public EntityHibernateDao<ExchangeSendTask> getEntityDao() {
		return null;
	}
	@Autowired
	public void setChangeItemDao(ChangeItemDao changeItemDao) {
		this.changeItemDao = changeItemDao;
	}
	
}
