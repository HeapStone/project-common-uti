 package com.wanglei.baseservlet.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;


/**
 * <p>Title: 日期工具类</p>
 * <p>Description: </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2017年12月16日
 */
public class DateUtil {
    
    public  final static String datePatten1 = "yyyy-MM-dd";
    public  final static String datePatten2 = "yyyy-MM-dd HH:mm";
    public  final static String datePatten3 = "yyyy-MM-dd HH:mm:ss";
    public  final static String datePatten4 = "yyyy-MM-dd HH:mm:ss.S";
    public  final static String datePatten5 = "yyyy年MM月dd日 HH时mm分";
	// DATE，日期 DATE_TIME，时间
	public final static SimpleDateFormat dateFormat = new SimpleDateFormat(datePatten1);
	public final static SimpleDateFormat timeFormat = new SimpleDateFormat(datePatten3);
	public final static SimpleDateFormat otherFormat = new SimpleDateFormat("yyyy/MM/dd");
	public final static SimpleDateFormat chineseFormat = new SimpleDateFormat("yyyy年MM月dd日");

	
	
	/**
	 * 获取yyyyMMdd当前时间字符串
	 */
	 public static String getYYYYMMDDString() {
		 	Date date = new Date();
			SimpleDateFormat s=new SimpleDateFormat(datePatten4);
			 return s.format(date.getTime()); 
	}
	// private final static SimpleDateFormat yearFormat=new
	// SimpleDateFormat("yyyy");

	public static Timestamp getOtherFormatTime(String date) {
		if (date != null && !"".equals(date)) {
			try {
				Date dateTime = dateFormat.parse(date);
				Timestamp timestamp = new Timestamp(dateTime.getTime());
				return timestamp;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	 

	/***
	 * <p>Description:格式化数据<p>
	 * @param date
	 * @return
	 * @author 陈波 2015-5-26
	 */
    public static java.sql.Date formateSqlDate(java.sql.Date date){
        try {
            java.util.Date d1 = dateFormat.parse(getTimeString(date));
            java.sql.Date d = new java.sql.Date(d1.getTime());
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	public static String getDateString(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String getDateString(Date date) {
		if (date == null) {
			return null;
		}
		String time = dateFormat.format(date);
		return time;
	}
	
	public static String getDateString(Timestamp time) {
		if (time == null) {
			return null;
		}
		Date date = new Date(time.getTime());
		return dateFormat.format(date);
	}

	public static String getTimeString(Date date) {
		if (date == null) {
			return null;
		}
		String time = timeFormat.format(date);
		return time;
	}

	/**
	 * 将sql TimeStamp转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String getTimeString(Timestamp time) {
		if (time == null) {
			return null;
		}
		Date date = new Date(time.getTime());
		return timeFormat.format(date);
	}

	public static String getTimeString(Timestamp time, String format) {
		if (time == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date(time.getTime());
		return df.format(date);
	}

	public static Date getDate(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		Date time = null;
		try {
			time = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static Date getDate(String dateStr,String pattern) throws ParseException {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(dateStr);
    }
	
	public static Timestamp getTime(String timeStr) {
		if (StringUtils.isBlank(timeStr)) {
			return null;
		}
		Timestamp ts = null;
		try {
			ts = Timestamp.valueOf(timeStr);
		} catch (Exception e) {
			try {
				ts = Timestamp.valueOf(timeStr + " 00:00:00");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return ts;
	}

	public static String getDateYYYYMMDD(Date date) {
		return getDateString(date).replaceAll("-", "");
	}
	
	/**
	 * 将sql TimeStamp转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String getTimeStampString(Timestamp time) {
		if (time == null) {
			return null;
		}
		Date date = new Date(time.getTime());
		return dateFormat.format(date);
	}

	/**
	 * 
	 * <p>
	 * 取当前时间
	 * <p>
	 * 
	 * @return
	 * @author chenji 2014-9-1
	 */
	public static java.sql.Date getCurrentSqlDate() {

		Date date = new Date();
//		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		try {
			java.util.Date d1 = timeFormat.parse(getTimeString(date));
			java.sql.Date d = new java.sql.Date(d1.getTime());
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * 取当前时间
	 * <p>
	 * 
	 * @return
	 * @author chenji 2015-3-9
	 */
	public static java.sql.Date getCurrentDate() {
		/**
		 * 获取存的路径 保存到数据库中的路径 调用上传FTP方法 获取访问路径
		 * */
		Date date = new Date();
//		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");// 24小时制
		try {
			java.util.Date d1 = timeFormat.parse(getTimeString(date));
			java.sql.Date d = new java.sql.Date(d1.getTime());
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static java.sql.Timestamp getCurrentTimestamp() {
		Date date = new Date();
		return new java.sql.Timestamp(date.getTime());

	}

	/***
	 * <p>
	 * Description:获取当前时间加上days天后的java.sql.Date形式
	 * <p>
	 * 
	 * @param days
	 *            ：需要加上的天数
	 * @return
	 * @author 陈波 2015-1-19
	 */
	public static java.sql.Date getCurrentAddSqlDate(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return new java.sql.Date(calendar.getTime().getTime());
	}

	// 获取

	public static String formatSqlDate(java.sql.Date date) {
		if (date != null)
			return dateFormat.format(date);
		return null;
	}

	/**
	 * <p>
	 * Description:字符串转换为sqlDate
	 * <p>
	 * 
	 * @param dateStr
	 * @return
	 * @author 陈波 2014-9-19
	 */
	public static java.sql.Date getStringSqlDate(String dateStr) {
		return getStringSqlDate(dateStr, dateFormat);
	}

	/***
	 * 将字符串转换为Java.sql.Date类型的时间
	 * <p>Description:<p>
	 * @param dateStr
	 * @param dateFormat
	 * @return
	 * @author 陈波 2015-4-7
	 */
	public static java.sql.Date getStringSqlDate(String dateStr, SimpleDateFormat dateFormat) {

		if (null != dateStr && !"".equals(dateStr)) {
			try {
				java.util.Date utilDate = dateFormat.parse(dateStr);
				return new java.sql.Date(utilDate.getTime());
			} catch (ParseException e) { }
		}
		return null;
	}

	public static String getChineseDate(java.sql.Date date) {
		if (date != null)
			return chineseFormat.format(date);
		return null;
	}

	public static String timeFormatSqlDate(java.sql.Date date) {
		if (date != null) {
			return timeFormat.format(date);
		}
		return null;
	}

	/**
	 * <p>
	 * Description:当前年
	 * <p>
	 * 
	 * @return
	 * @author 陈波 2014-10-7
	 */
	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		String year = cal.get(Calendar.YEAR) + "";
		return year;
	}

	/**
	 * <p>
	 * Description:当前月
	 * <p>
	 * 
	 * @param flagZero
	 *            ：当月份小于10的时候是否需要补0,ture的时候补零,否则不处理
	 * @return
	 * @author 陈波 2014-10-7
	 */
	public static String getCurrentMonth(boolean flagZero) {
		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH) + 1;
		String month = m + "";
		if (flagZero && m < 10) {
			month = "0" + month;
		}
		return month;
	}

	/**
	 * 
	 * <p>
	 * Description:当前日
	 * <p>
	 * 
	 * @param flagZero
	 *            ：当天小于10的时候是否需要补0,ture的时候补零,否则不处理
	 * @return
	 * @author 陈波 2014-10-7
	 */
	public static String getCurrentDay(boolean flagZero) {
		Calendar cal = Calendar.getInstance();
		int d = cal.get(Calendar.DAY_OF_MONTH);
		String day = d + "";
		if (flagZero && d < 10) {
			day = "0" + day;
		}
		return day;
	}

	/**
	 * <p>
	 * Description: 获取指定日期N年以后的日期
	 * <p>
	 * 
	 * @param date
	 *            日期
	 * @param yeas
	 *            多少年以后
	 * @return 日期字符串
	 * @author hanlin 2014-10-16
	 */
	public static String getDateByAfterYear(Date date, int yeas) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + yeas);
		return getDateString(calendar.getTime());
	}
	
	/**
	 * <p>
	 * Description: 获取指定日期N月以后的日期
	 * <p>
	 * 
	 * @param date
	 *            日期
	 * @param month
	 *            多少月以后
	 * @return 日期字符串
	 * @author hanlin 2014-10-16
	 */
	public static String getDateByAfterMonth(Date date, int month) {
		if (date == null) {
			return null;
		} 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
		return getDateString(calendar.getTime());
	}

	/**
	 * <p>
	 * Description: 获取指定日期N天以后的日期
	 * <p>
	 * 
	 * @param date
	 *            日期
	 * @param days
	 *            多少天以后
	 * @return 日期
	 * @author hanlin 2014-10-31
	 */
	public static java.sql.Date getDateByAfterDays(Date date, BigDecimal days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days.intValue());
		return new java.sql.Date(calendar.getTime().getTime());
	}

	/**
	 * <p>
	 * Description: 获取两个日期的时间差，精确到天
	 * <p>
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 时间差
	 * @author hanlin 2014-10-21
	 */
	public static BigDecimal getDatesIntervalByDay(Date startDate, Date endDate) {
		return new BigDecimal((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 
	 * <p>
	 * Description:获取N天以后
	 * <p>
	 * 
	 * @param months
	 * @return
	 * @author chenji 2014-10-27
	 */
	public static Date getDayAfter(int days) {
		Calendar calendar = Calendar.getInstance();
		// 获取当前时间的前months个月
		calendar.add(Calendar.DAY_OF_YEAR, days * 1);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 
	 * <p>
	 * Description:获取一月前的日期
	 * <p>
	 * 
	 * @return
	 * @author caolin 2014-12-8
	 */
	public static String getOneMonthBeforeDate() {
		// Date date = new Date();
		// long beforeMon = (date.getTime() / 1000) - (30 * 24 * 60 * 60);
		// date.setTime(beforeMon * 1000);
		// 2015-2-9修改获得上月当前日期
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		return dateFormat.format(c.getTime());
	}

	/**
	 * 
	 * <p>
	 * Description:获取昨天的日期
	 * <p>
	 * 
	 * @return
	 * @author caolin 2014-12-8
	 */
	public static String getYesDate() {
		Date date = new Date();
		long beforeMon = date.getTime() - (24 * 60 * 60 * 1000);
		date.setTime(beforeMon);
		return dateFormat.format(date);
	}

	/**
	 * @desc 根据数值取相对于当前日期的日期数据
	 * @param i
	 *            :0:当天，-1：昨天，1：明天
	 * @param modle
	 *            :yyyyMM;yyyyMMdd;yyyy-MM-dd HH:mm:ss;
	 * @return date
	 */
	public static String getDayByNum(String model, int i) {
		Calendar calendar = Calendar.getInstance();
		if (i != 0) {
			calendar.add(Calendar.DATE, i);
		}
		SimpleDateFormat df = new SimpleDateFormat(model);
		df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 根据传入时间字符串获得需要格式的三种加减时间串
	 * 
	 * @param strDate
	 *            时间字符串
	 * @param model
	 *            格式
	 * @param temp
	 *            加减项
	 * @param i
	 *            加减量
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getDateByStrNum(String strDate, String model, String temp, int i) {
		SimpleDateFormat formatter = new SimpleDateFormat(model);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		Calendar cal = Calendar.getInstance();
		cal.setTime(strtodate);
		if (temp.equals("year")) {
			cal.add(cal.YEAR, i);
		} else if (temp.equals("month")) {
			cal.add(cal.MONTH, i);
		} else if (temp.equals("day")) {
			cal.add(cal.DATE, i);
		}
		return formatter.format(cal.getTime());
	}

	/***
	 * 
	 * <p>
	 * Description:日期比较
	 * <p>
	 * 
	 * @param beforedate
	 *            ：
	 * @param afterdate
	 * @return beforedate==afterdate 0,beforedate>afterdate -1,
	 *         afterdate>beforedate 1
	 * @author 陈波 2014-12-16
	 */
	public static int compareSqlDate(java.sql.Date beforedate, java.sql.Date afterdate) {
		return beforedate.equals(afterdate) ? 0 : beforedate.before(afterdate) ? 1 : -1;
	}

	public static String getCurrentDateStr() {
		 Date date = new Date();
		 String str = null;
//		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 str = timeFormat.format(date);
		 return str;
    }
	/**
	 * 获取当前月第一天
	 * @return
	 */
	public static String getFirstDay(){
		Calendar calendar=Calendar.getInstance();//获取当前日期 
		//calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		String firstDay=dateFormat.format(calendar.getTime());
		return firstDay;
	}
	
	/**
     * 
     * <p>
     * Description:获取第n个月前的日期
     * <p>
     * 
     * @return
     * @author zhangjinbao 2017-02-19
     */
    public static String getNMonthBeforeDate(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -n);
        return dateFormat.format(c.getTime());
    }
    
    /**
     * 
     * <p>
     * Description:获取第n个月前的第一天
     * <p>
     * 
     * @return
     * @author zhangjinbao 2017-02-19
     */
    public static String getNMonthFistDateBeforeDate(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -n);
        c.set(Calendar.DAY_OF_MONTH,1);
        return dateFormat.format(c.getTime());
    }

	
	public static void main(String[] args) {
		// String s=getCurrentMonth(false);
		// System.out.println("s:"+s);
		// java.sql.Date date = getCurrentSqlDate();
		// System.out.println(getDayAfter(10));
		System.out.println(getTime("2015-06-25"));
//		System.out.println(getOneMonthBeforeDate());
//		System.out.println(getYesDate());
//		System.out.println(getCurrentAddSqlDate(1));
//		System.out.println(getCurrentDate().getTime());
//		System.out.println(getCurrentSqlDate().getTime());
		System.out.println(getCurrentDateStr());
	}
}
