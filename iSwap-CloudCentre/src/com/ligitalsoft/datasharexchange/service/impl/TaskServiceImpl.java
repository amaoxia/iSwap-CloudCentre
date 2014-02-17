/*
 * @(#)ExchangeSendTaskServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
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
import com.ligitalsoft.datasharexchange.service.ITaskService;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ChangeItemAppMsg;
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

/**
 *任务 实现类
 * @author daic
 * @since 2011-08-17 15:11:00
 * @name com.ligitalsoft.cloudstorage.service.impl.ExchangeSendTaskServiceImpl.java
 * @version 1.0
 */
@Service("taskService")
public class TaskServiceImpl extends BaseSericesImpl<ChangeItem> implements ITaskService ,Constant {
	private ChangeItemDao changeItemDao;
    private ExchangeSendTaskDao exchangeSendTaskDao;
    private ExchangeSystemConfigDao exchangeSystemConfigDao;
    private ExchangeTransactDao exchangeTransactDao;
	private ISysDeptService sysDeptService;
	private ExchangeTransactTypeDao exchangeTransactTypeDao;
	private SysUserDeptDao sysUserDeptDao;
	private SysUserDao sysUserDao;
    @Autowired
    public void setChangeItemDao(ChangeItemDao changeItemDao) {
        this.changeItemDao = changeItemDao;
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
	public void setExchangeTransactTypeDao(
			ExchangeTransactTypeDao exchangeTransactTypeDao) {
		this.exchangeTransactTypeDao = exchangeTransactTypeDao;
	}
    @Autowired
	public void setSysUserDeptDao(SysUserDeptDao sysUserDeptDao) {
		this.sysUserDeptDao = sysUserDeptDao;
	}
    @Autowired
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
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
	@Override
	public List<String[]> buildTask(List<ChangeItem> items, String begin,
			String end) {
		List<String[]> result = new ArrayList<String[]>();
		Date beginDate = ExchangeDateUtil.strToDate(begin);
		Date endDate = ExchangeDateUtil.strToDate(end);
		for (ChangeItem item : items) {
			String edRule[] = item.getChangeItemCycle().getExchangeDateRule().split(",");
			int cycle = Integer.parseInt(item.getChangeItemCycle().getExchangeCycleValue());
			String[] sBegin = begin.split("-");
			Date startDate = null;
			boolean isLastDay = false;
			int baseNum = 1;
			switch (cycle) {
			case EXCHANGE_CYCLE_MONTH: // 1 如果交换周期是月，需要判断是否是最后一天
			{
				baseNum = 1;
				String yyyyMM = sBegin[0] + "-" + sBegin[1];
				startDate = ExchangeDateUtil.strToDate(yyyyMM + "-" + edRule[1]);
				break;
			}
			case EXCHANGE_CYCLE_QUARTER: // 2
			{
				baseNum = 3;
				int q = ExchangeDateUtil.getQuarter(Integer.parseInt(sBegin[1]));
				String yyyyMM = sBegin[0] + "-"
						+ QUARTER[(q - 1)][(Integer.parseInt(edRule[1]) - 1)];
				startDate = ExchangeDateUtil.strToDate(yyyyMM + "-" + edRule[2]);
				break;
			}
			case EXCHANGE_CYCLE_YEAR: // 3
			{
				baseNum = 12;
				String yyyyMM = sBegin[0] + "-" + edRule[1];
				startDate = ExchangeDateUtil.strToDate(yyyyMM + "-" + edRule[2]);
				break;
			}
			}

			// 如果组合出来的日期小于起始日期就加上一个月
			if (startDate.before(beginDate)) {
				startDate = addMonth(isLastDay, startDate, baseNum);
			}
			/*-----------循环生成一个时间段的任务 Begin----------*/
			for (; startDate.before(endDate) || startDate.equals(endDate); startDate = addMonth(
					isLastDay, startDate, baseNum)) {
				ExchangeSendTask sendTask = null;
				String timeZone = getDataTimeZone(cycle, edRule[0], ExchangeDateUtil.getYear(startDate), ExchangeDateUtil.getMonth(startDate));

				if (taskIsExist(item.getId(), startDate)) { // 任务已存在
					// do nothing
					result.add(new String[] { item.getItemName(),
							String.valueOf(cycle),
							ExchangeDateUtil.formatDate(startDate, "yyyy年MM月dd日"), "0",
							timeZone });
					continue;
				} else {
					sendTask = buildSendTask(item, startDate, timeZone);
					result.add(new String[] { item.getItemName(),
							String.valueOf(cycle),
							ExchangeDateUtil.formatDate(startDate, "yyyy年MM月dd日"), "1",
							timeZone });
				}
			}
			/*-----------循环生成一个时间段的任务 End----------*/
		}
		return result;
	}

	/**
	 * 增加一个月
	 * 
	 * @param isLastDay
	 * @param startDate
	 * @return
	 */
	private Date addMonth(boolean isLastDay, Date startDate, int baseNum) {
		if (isLastDay) {
			// startDate格式化成yyyy-MM格式，再加上一个月
			String sDate = ExchangeDateUtil.formatDate(startDate, "yyyy-MM");
			Date dDate = ExchangeDateUtil.addMonth(
					ExchangeDateUtil.strToDate(sDate, "yyyy-MM"), baseNum);
			String ny = ExchangeDateUtil.formatDate(dDate, "yyyy-MM");
			startDate = ExchangeDateUtil.strToDate(ny + "-" + ExchangeDateUtil.getLastDay(ny));
		} else {
			startDate = ExchangeDateUtil.addMonth(startDate, baseNum);
		}

		return startDate;
	}
	/**
	 * 得到数据时间段
	 * 
	 * @param cycle
	 *            周期
	 * @param type
	 *            本月/季/年 或下月/季/年
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return
	 */
	private String getDataTimeZone(int cycle, String type, int year, int month) {
		String result = null;
		switch (cycle) {
		case 1: {
			if (type.equalsIgnoreCase("0")) {
				result = year + "年" + month + "月01日至" + year + "年" + month
						+ "月" + ExchangeDateUtil.getLastDay(year + "-" + month) + "日";
			} else if (type.equalsIgnoreCase("1")) {
				int y = year;
				int m = month;
				if (month - 1 == 0) {
					y--;
					m = 12;
				} else {
					m--;
				}
				result = y + "年" + m + "月01日至" + y + "年" + m + "月"
						+ ExchangeDateUtil.getLastDay(y + "-" + m) + "日";
			}
			break;
		}
		case 2: {
			int quarter = ExchangeDateUtil.getQuarter(month);
			if (type.equalsIgnoreCase("0")) {
				String[] monthAndDay = getQuarterArea(quarter);
				result = year + "年" + monthAndDay[0] + "至" + year + "年"
						+ monthAndDay[1];
			} else if (type.equalsIgnoreCase("1")) {
				int y = year;
				int q = quarter;
				if (quarter == 1) {
					q = 4;
					--y;
				} else {
					q = --quarter;
				}

				String[] monthAndDay = getQuarterArea(q);
				result = y + "年" + monthAndDay[0] + "至" + y + "年"
						+ monthAndDay[1];
			}
			break;
		}
		case 3: {
			// int y = year;
			if (type.equalsIgnoreCase("1")) {
				year--;
			}
			result = year + "年01月01日至" + year + "年12月31日";
			break;
		}
		}
		return result;
	}
	/**
	 * 得到某个季度的日期区间
	 * 
	 * @param n
	 * @return
	 */
	private String[] getQuarterArea(int n) {
		String result[] = null;
		switch (n) {
		case 1: {
			result = new String[] { "01月01日", "03月31日" };
			break;
		}
		case 2: {
			result = new String[] { "04月01日", "06月30日" };
			break;
		}
		case 3: {
			result = new String[] { "07月01日", "09月30日" };
			break;
		}
		case 4: {
			result = new String[] { "10月01日", "12月31日" };
			break;
		}
		}
		return result;
	}
	/**
	 * 判断任务是否存在
	 * 
	 * @param id
	 * @param dDate
	 * @return
	 */
	private boolean taskIsExist(long id, Date dDate) {
		String hql = "select count(*) from ExchangeSendTask st where st.item.id = ? and st.execDate = ?";
		long total = exchangeSendTaskDao.getTotalByHql(hql, new Object[] { id, dDate });
		if (total > 0)
			return true;
		else
			return false;
	}

	/**
	 * 创建发送任务
	 * 
	 * @param item
	 *            指标项
	 * @param execDate
	 *            交换日期
	 * @param timeZone
	 *            数据相对时间段
	 * @param tz
	 *            数据实际交换时间段
	 */
	@SuppressWarnings("unchecked")
	private ExchangeSendTask buildSendTask(ChangeItem item, Date execDate, String timeZone) {
		//int cycle = Integer.parseInt(item.getExchangeCycle().getId().trim());
		ExchangeSystemConfig sc = getSystemConfigInfo();
		String[] greenRule = sc.getRuleNotify().split(",");
		//String[] yellowRule = sc.getRuleYellowNotify().split(",");
		String[] redRule = sc.getRuleRedNotify().split(",");
		

		Date greenDay = getDayFromRule(execDate, greenRule);
		Date redDay = getDayFromRule(execDate, redRule);
		String endDate = ExchangeDateUtil.formatDate(redDay, "yyyy-MM-dd").concat(" 23:59:59");
		String lastDate = ExchangeDateUtil.formatDate(ExchangeDateUtil.addDay(redDay, 1), "yyyy-MM-dd");
		
		
		ExchangeSendTask task = new ExchangeSendTask();
		task.setItem(item);// 相应指标项
		task.setExecDate(execDate);// 执行日期
		task.setFinishedState("0");// 完成状态
		task.setSendState(String.valueOf(SEND_STATE_NONE));// 发送状态
		task.setTransactCount(0);// 催办次数
		
		task.setExecStartDate(greenDay);//设置任务开始时间
		task.setExecEndDate(ExchangeDateUtil.strToDate(endDate, "yyyy-MM-dd HH:mm:ss"));//设置任务结束
		task.setExecLastDate(ExchangeDateUtil.strToDate(lastDate,"yyyy-MM-dd"));//设置任务执行最后日期
		
		List<ChangeItemAppMsg> applist = item.getItemApps();
		String apps = "";
		for(ChangeItemAppMsg app: applist){
			apps=apps+app.getAppMsg().getAppName()+",";
		}
		task.setApps(apps);//所属应用
		
		task.setDataTimeZone(timeZone);// 数据时间段
		task.setTaskName(ExchangeDateUtil.formatDate(execDate, "yyyy年MM月dd日") + "\""
				+ item.getItemName() + "\"交换任务");// 任务名称
		exchangeSendTaskDao.save(task);
		return task;

	}
	private Date getDayFromRule(Date execDate, String[] rule) {
		Date date = null;
		if (rule[0].equalsIgnoreCase(EXCHANGE_BACK)) {// 交换周期后的话加n天
			date = ExchangeDateUtil.addDay(execDate, Long.parseLong(rule[1]));
		} else if (rule[0].equalsIgnoreCase(EXCHANGE_FRONT)) {// 交换周期前的话减n天
			date = ExchangeDateUtil.reduceDay(execDate, Integer.parseInt(rule[1]));
		}
		return date;
	}
	@SuppressWarnings("unchecked")
	private ExchangeSystemConfig getSystemConfigInfo() {
		String hql = "from ExchangeSystemConfig";
		ExchangeSystemConfig sc = null;
		List<ExchangeSystemConfig> list = exchangeSystemConfigDao.findListByHql(hql, null);
		if (list.size() > 0) {
			sc = list.get(0);
		}
		return sc;
	}
	@Override
	public EntityHibernateDao<ChangeItem> getEntityDao() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void buildCuiban(ChangeItem item, ExchangeSendTask st) {
		this.transactTask(item, st);
	}
	/**
	 * 
	 * @param item
	 * @param st
	 * @param lightState
	 */
	public void transactTask(ChangeItem item, ExchangeSendTask st){
		SysDept manageDept = null;
		try {
			manageDept = sysDeptService.findById(Long.valueOf(Constant.MANAGE_DEPT_ID));
		} catch (ServiceException e) {
			e.printStackTrace();
		}// 取出管理部门
		int lightState = Integer.valueOf(st.getSendState());
		// step1 : 更改亮灯状态为绿灯
		//st.setSendState(String.valueOf(lightState));
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
}
