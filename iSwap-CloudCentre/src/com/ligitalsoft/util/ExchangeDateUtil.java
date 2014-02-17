package com.ligitalsoft.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 对日期的处理
 * @author daic
 * @version
 * @Date 2011-08-18 15:42:27
 */
public class ExchangeDateUtil {
	private static String previousDateTime;

	/**
	 * @author zhaoym
	 * @return 唯一的当前时间，精确到秒
	 */
	public synchronized static String getUniqueDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateTime = sdf.format(new Date());
		while (dateTime.equals(previousDateTime)) {
			dateTime = sdf.format(new Date());
		}
		previousDateTime = dateTime;
		return dateTime;
	}

	/**
	 * 将当前系统时间转换成直至Millisecond的样式
	 */
	public static String getDateTimeZone() {
		return new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
	}

	/**
	 * 将当前系统时间转换成直至HHmmssS的样式
	 * 
	 * @author hudaowan
	 * @date 2008-4-3 上午09:48:56
	 * @return
	 */
	public static String getTimeZone() {
		return new SimpleDateFormat("HHmmssS").format(new Date());
	}

	/**
	 * 根据时间格式取当前时间
	 * 
	 * @author hudaowan 2006-10-19 下午01:58:34
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	/**
	 * 得到当前系统时间和日期
	 * 
	 * @author hudaowan 2006-10-13 上午09:55:24
	 * @return
	 */
	public static String getDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static String getDateTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 获得当前系统日期
	 * 
	 * @author hudaowan Sep 29, 2006 10:43:22 AM
	 * @return
	 */
	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 获得当前系统时间
	 * 
	 * @author hudaowan Sep 29, 2006 11:06:57 AM
	 * @return
	 */
	public static String getTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public static boolean isDateAfter(String timeString, long rating) {
		try {
			Date date = addDay(timeString, rating);
			Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm")
							.format(new Date()));
			long min = date.getTime();
			long nowmin = now.getTime();
			if (nowmin - min > 10 * 60 * 1000)
				return true;
			else
				return false;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 判断传入的日期是否大于当前系统时间额定的时间
	 * 
	 * @author hudaowan Sep 29, 2006 10:47:32 AM
	 * @param timeString
	 * @param rating
	 * @return
	 */
	public static boolean isAfter(String timeString, int rating) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(timeString);
			Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm")
							.format(new Date()));

			long min = date.getTime();
			long nowmin = now.getTime();
			long count = rating * 60 * 1000;
			if (nowmin - min > count)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断传入的日期是否大于当前系统时间额定的时间
	 * 
	 * @author hudaowan Sep 29, 2006 10:56:47 AM
	 * @param timeString
	 * @param rating
	 * @return
	 */
	public static boolean isBefore(String timeString, int rating) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(timeString);
			Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm")
							.format(new Date()));
			long min = date.getTime();
			long nowmin = now.getTime();

			if (nowmin - min < rating * 60 * 1000)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 根据指定的日期格式显示日期
	 * 
	 * @author hudaowan Sep 29, 2006 10:21:46 AM
	 * @param dDate
	 * @param sFormat
	 * @return
	 */
	public static String formatDate(Date dDate, String sFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
		String dateString = formatter.format(dDate);
		return dateString;
	}

	/**
	 * 将字符转换为指定的日期格式输出
	 * 
	 * @author hudaowan Sep 29, 2006 10:26:36 AM
	 * @param s
	 * @param pattern
	 * @return
	 */
	public static Date strToDate(String s, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date date1;
		try {
			Date theDate = formatter.parse(s);
			Date date = theDate;
			return date;
		} catch (Exception ex) {
			date1 = null;
		}
		return date1;
	}

	/**
	 * 将字符转换为日期
	 * 
	 * @author hudaowan Sep 29, 2006 10:27:45 AM
	 * @param s
	 * @return
	 */
	public static Date strToDate(String s) {
		Date date;
		try {
			DateFormat df = DateFormat.getDateInstance();
			Date theDate = df.parse(s);
			Date date1 = theDate;
			return date1;
		} catch (Exception ex) {
			date = null;
		}
		return date;
	}

	/**
	 * 添加小时和分
	 * 
	 * @author hudaowan 2006-10-27 下午02:38:27
	 * @param sDate
	 * @param iNbDay
	 * @return
	 */
	public static Date addMinute(String sDate, long iNbTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(strToDate(sDate, "yyyy-MM-dd HH:mm"));
		cal.add(Calendar.MINUTE, (int) iNbTime);
		Date date = cal.getTime();
		return date;
	}

	public static Date addHour(String sDate, long iNbTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(strToDate(sDate, "yyyy-MM-dd HH:mm"));
		cal.add(Calendar.HOUR_OF_DAY, (int) iNbTime);
		Date date = cal.getTime();
		return date;
	}

	/**
	 * 给当前时间添加天数
	 * 
	 * @author hudaowan Sep 29, 2006 10:11:30 AM
	 * @param dDate
	 * @param iNbDay
	 * @return
	 */
	public static Date addDay(String sDate, long iNbDay) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(strToDate(sDate, "yyyy-MM-dd HH:mm"));
		cal.add(Calendar.DAY_OF_MONTH, (int) iNbDay);
		Date result = cal.getTime();
		return result;
	}
	/**
	 * 给当前时间添加天数
	 * @param dDate
	 * @param iNbDay
	 * @return
	 */
	public static Date addDay(Date dDate, long iNbDay) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(dDate);
		cal.add(Calendar.DAY_OF_MONTH, (int) iNbDay);
		Date result = cal.getTime();
		return result;
	}
	/**
	 * 给当前时间添加 周
	 * 
	 * @author hudaowan Sep 29, 2006 10:13:27 AM
	 * @param dDate
	 * @param iNbWeek
	 * @return
	 */
	public static Date addWeek(String sDate, long iNbWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(strToDate(sDate, "yyyy-MM-dd HH:mm"));
		cal.add(Calendar.WEEK_OF_YEAR, (int) iNbWeek);
		Date result = cal.getTime();
		return result;
	}

	/**
	 * 给当前时间添加 月
	 * 
	 * @author hudaowan Sep 29, 2006 10:15:29 AM
	 * @param dDate
	 * @param iNbMonth
	 * @return
	 */
	public static Date addMonth(String sDate, int iNbMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(strToDate(sDate, "yyyy-MM-dd HH:mm"));
		int month = cal.get(Calendar.MONTH);
		month += iNbMonth;
		int year = month / 12;
		month %= 12;
		cal.set(Calendar.MONTH, month);
		if (year != 0) {
			int oldYear = cal.get(Calendar.YEAR);
			cal.set(Calendar.YEAR, year + oldYear);
		}
		return cal.getTime();
	}

	public static Date addMonth(Date dDate, int iNbMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dDate);
		int month = cal.get(Calendar.MONTH);
		month += iNbMonth;
		int year = month / 12;
		month %= 12;
		cal.set(Calendar.MONTH, month);
		if (year != 0) {
			int oldYear = cal.get(Calendar.YEAR);
			cal.set(Calendar.YEAR, year + oldYear);
		}
		return cal.getTime();
	}
	/**
	 * 给单当前时间添加 年
	 * 
	 * @author hudaowan Sep 29, 2006 10:17:14 AM
	 * @param dDate
	 * @param iNbYear
	 * @return
	 */
	public static Date addYear(Date dDate, int iNbYear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dDate);
		int oldYear = cal.get(1);
		cal.set(1, iNbYear + oldYear);
		return cal.getTime();
	}

	/**
	 * 得到当前日期是星期几
	 * 
	 * @author hudaowan 2006-10-27 下午01:26:32
	 * @param dDate
	 * @return
	 */
	public static String getDayOfWeek(Date date){
		// 设置当前日期
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        // 取当前日期是星期几(week:星期几)
        int result = aCalendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (result == 0)
			result = 7;
        String[] dayOfWeek = {"","一","二","三","四","五","六","日"};
		return "星期"+dayOfWeek[result];
	}
	/**
	 * 得到当前日期是 那个月的几号
	 * 
	 * @author hudaowan 2006-10-27 下午01:34:37
	 * @param dDate
	 * @return
	 */
	public static int getMonthOfDay(Date dDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dDate);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到当前月的最后一天是几号
	 * 
	 * @author fangbin
	 * @param day
	 * @return
	 */
	public static String getMonthOfLastDay(String day) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(strToDate(day));
		int maxDate = rightNow.getActualMaximum(Calendar.DATE);
		String lastDay = Integer.toString(maxDate);
		return lastDay;
	}

	/**
	 * 生成随即数
	 * 
	 * @author hudaowan
	 * @date 2008-4-3 上午09:57:56
	 * @param pwd_len
	 * @return
	 */
	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	public static String genRandNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * 给当前时间增加或减少天数
	 * 
	 * @param sDate
	 * @param iNbDay
	 * @return
	 */
	public static Date reduceDay(String sDate, int iNbDay) {
		/*
		 * Calendar strDate = Calendar.getInstance(); //java.util包
		 * strDate.setTime(strToDate(sDate,"yyyy-MM-dd"));
		 * strDate.add(strDate.DATE, -iNbDay); //日期减 如果不够减会将月变动 //生成 (年-月-日) 字符串
		 * String meStrDate = strDate.get(strDate.YEAR) + "-" +
		 * String.valueOf(strDate.get(strDate.MONTH)+1) + "-" +
		 * strDate.get(strDate.DATE); return meStrDate;
		 */
		Calendar cal = Calendar.getInstance();
		cal.setTime(strToDate(sDate, "yyyy-MM-dd"));
		cal.add(Calendar.DAY_OF_MONTH, (-1)*iNbDay);
		Date result = cal.getTime();
		return result;
	}
	
	public static Date reduceDay(Date dDate, int iNbDay) {
		/*
		 * Calendar strDate = Calendar.getInstance(); //java.util包
		 * strDate.setTime(strToDate(sDate,"yyyy-MM-dd"));
		 * strDate.add(strDate.DATE, -iNbDay); //日期减 如果不够减会将月变动 //生成 (年-月-日) 字符串
		 * String meStrDate = strDate.get(strDate.YEAR) + "-" +
		 * String.valueOf(strDate.get(strDate.MONTH)+1) + "-" +
		 * strDate.get(strDate.DATE); return meStrDate;
		 */
		Calendar cal = Calendar.getInstance();
		cal.setTime(dDate);
		cal.add(Calendar.DAY_OF_MONTH, (-1)*iNbDay);
		Date result = cal.getTime();
		return result;
	}
	/**
	 * 给当前时间增加减少 月
	 * 
	 * @param date
	 * @param iNbMonth
	 * @return
	 */
	public static Date reduceMonth(Date date, int iNbMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		month += iNbMonth;
		int year = month / 12;
		month %= 12;
		cal.set(Calendar.MONTH, month);
		if (year != 0) {
			int oldYear = cal.get(Calendar.YEAR);
			cal.set(Calendar.YEAR, year + oldYear);
		}
		return cal.getTime();
	}
	/**
	 * 给当前时间增加减少 月
	 * 
	 * @param sDate
	 * @param iNbMonth
	 * @return
	 */
	public static Date reduceMonth(String sDate, int iNbMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(strToDate(sDate, "yyyy-MM-dd"));
		int month = cal.get(Calendar.MONTH);
		month += iNbMonth;
		int year = month / 12;
		month %= 12;
		cal.set(Calendar.MONTH, month);
		if (year != 0) {
			int oldYear = cal.get(Calendar.YEAR);
			cal.set(Calendar.YEAR, year + oldYear);
		}
		return cal.getTime();
	}

	/**
	 * 给单当前时间增加减去 年
	 * 
	 * @param dDate
	 * @param iNbYear
	 * @return
	 */

	public static Date reduceYear(Date dDate, int iNbYear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dDate);
		int oldYear = cal.get(1);
		cal.set(1, oldYear + iNbYear);
		return cal.getTime();
	}

	/**
	 * 判断当前月是第几季度
	 * 
	 * @param date
	 *            当前月
	 * @return 第几季度
	 */
	public static int getSeason(String date) {
		int month = Integer.parseInt(ExchangeDateUtil.formatDate(ExchangeDateUtil
				.strToDate(date), "MM"));
		int season = 1;
		if (month >= 1 & month <= 3) {
			season = 1;
		} else if (month >= 4 & month <= 6) {
			season = 2;
		} else if (month >= 7 & month <= 9) {
			season = 3;
		} else if (month >= 10 & month <= 12) {
			season = 4;
		}
		return season;
	}

	/**
	 * 得出当前月的最后一天
	 * 
	 * @param sDate
	 *            当前日期
	 * @return
	 */
	public static int getLastDay(String sDate) {
		Date dDate = ExchangeDateUtil.strToDate(sDate, "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dDate);
		return calendar.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 判断是否是周末
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int n = cal.get(Calendar.DAY_OF_WEEK); 
		if(n == 1 || n == 7) {
			return true;
		} 
		return false;
	}
	
	public static int getMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	public static int getQuarter(int month) {
		if(month >= 1 && month <=3) return 1;
		else if (month >= 4 && month <=6) return 2;
		else if (month >= 7 && month <=9) return 3;
		else if (month >= 10 && month <=12) return 4;
		else return 1;
	}
	
	/**
	 * 检查拼出来的日期是否越界，如果越界就取当月的最大值
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date reCombineDate(String year, String month, String day) {
		//如果拼出来的日期大于当月的最大日期，说明拼出来的日期值越界了
		int lastDay = getLastDay(year + "-" + month);
		if(strToDate(year + "-" + month + "-" + day).after(strToDate(year + "-" + month + "-" + lastDay)) || strToDate(year + "-" + month + "-" + day).equals(strToDate(year + "-" + day + "-" + lastDay)))
			return strToDate(year + "-" + month + "-" + lastDay);
		else
			return strToDate(year + "-" + month + "-" + day);
	}

	public static void main(String[] gta) {
		Date now = new Date();
		Date lastDay = ExchangeDateUtil.strToDate("2009-04-02", "yyyy-MM-dd");
		System.out.println(ExchangeDateUtil.addDay(lastDay, 1));
		System.out.println(now.equals(lastDay));
		System.out.println(ExchangeDateUtil.getMonth(new Date()));
		/*Date date = new Date();
		String currentDate = " "+DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
		currentData+=" ".concat(DateUtil.getDayOfWeek(date));
		currentData+=" ".concat(DateUtil.formatDate(date, "HH:mm:ss")).concat(" ");
		System.out.println(currentData);
		String startDate = "";
		String endDate = "";
		Date[] sDateArrays = new Date[13];//开始时间
		Date[] eDateArrays = new Date[13];//结束时间
		String[] monArrays = new String[13];
		for(int i=0 ;i<12;i++){			
			String currentMonnth = DateUtil.formatDate(DateUtil.reduceMonth(currentDate, -i),"yyyy-MM");
			int lastDay = DateUtil.getLastDay(currentMonnth);
			if (i==0) {
				endDate = currentDate;
			}else{
				endDate = currentMonnth+"-"+lastDay+" 23:59:59";
			}
			startDate = currentMonnth+"-01 00:00:00";
			String mons = DateUtil.formatDate(DateUtil.reduceMonth(currentDate, -i),"yyyy年MM月");			
			
			Date sDate = DateUtil.strToDate(startDate,"yyyy-MM-dd HH:mm:ss");
			Date endDates = DateUtil.strToDate(endDate,"yyyy-MM-dd HH:mm:ss");
			System.out.println(mons+"-"+sDate+"-"+endDates);
			sDateArrays[i]=sDate;
			eDateArrays[i]=endDates;
			monArrays[i]=mons;
		}*/
		/*System.out.println(formatDate(DateUtil.reduceYear(strToDate("2008-10-11 22:12:31"), -2),"yyyy年MM月dd日"));
		System.out.println(formatDate(reduceMonth(strToDate("2008-02-26"), -12),"yyyyMMdd"));*/
//		int month = Integer.parseInt(DateUtil.formatDate(DateUtil
//				.strToDate("2008-09-16"), "MM"));
//		System.out.println("month:" + month);
//		Date date = DateUtil.strToDate("2008-02", "yyyy-MM");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		int daynum = calendar.getActualMaximum(Calendar.DATE);
//		System.out.println("Last day:" + daynum);
//		System.out.println(DateUtil.addYear(DateUtil.strToDate("2008-01-01"), 1));
//		System.out.println(DateUtil.addDay(DateUtil.strToDate("2008-12-31"), 1));
		
		
		/*String endDateStr = DateUtil.getDate().concat(" 23:59:59");
		System.out.println(endDateStr);
		Date date = strToDate(endDateStr,"yyyy-MM-dd HH:mm:ss");
		System.out.println(formatDate(date,"yyyy-MM-dd HH:mm:ss"));
		System.out.println(formatDate(strToDate(getDate()),"yyyy-MM-dd HH:mm:ss"));
		System.out.println(formatDate(addMonth("2009-01-08 22:12:31", 1),"yyyy-MM-dd").concat(" 00:00:00"));
		System.out.println(strToDate("2009-03-03","yyyy-MM-dd" ).equals(strToDate(getDate(),"yyyy-MM-dd")));*/
	}
}
