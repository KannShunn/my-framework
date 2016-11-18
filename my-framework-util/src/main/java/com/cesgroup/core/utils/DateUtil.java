package com.cesgroup.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期工具
 * 
 * @author 国栋
 *
 */
public class DateUtil
{

	/**
	 * 日期和字符串之间的转换格式：年月日时分 yyyy-MM-dd HH:mm
	 */
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm";

	public static final String DATETIME_NO_UNDERLINED_PATTERN = "yyyyMMddHHmmssSSS";
	public static final String DATE_FILE_SEPARATOR_SPATTERN = "yyyy/MM/dd";
	/** DATETIME_PATTERN(String):yyyy-MM-dd HH:mm:ss. */
	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	  
	/** DATE_PATTERN(String):yyyy-MM-dd. */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
      
	/** MONTH_PATTERN(String):yyyy-MM. */
	public static final String MONTH_PATTERN = "yyyy-MM";


	/** YEAR_PATTERN(String):yyyy. */
	public static final String YEAR_PATTERN = "yyyy";
	
	/**
	 * 此方法用于获得指定日期的上一周的第一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreWeekDate(Date date)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1);
		calendar.set(Calendar.DAY_OF_WEEK, 1);

		return calendar.getTime();
	}

	/**
	 * 此方法用于获得指定日期的下一周的第一天时间。
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextWeekDate(Date date)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + 1);
		calendar.set(Calendar.DAY_OF_WEEK, 1);

		return calendar.getTime();
	}

	/**
	 * 将指定日期转换为以周为维度的特定字符串形式。
	 * 
	 * @param taskStartTime
	 * @return
	 */
	public static List<Integer> getWorkTimeStr(Date date)
	{
		//1、首先获取 本周第一天
		Date weekStartDate = getFirstDayOfWeek(date);
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(weekStartDate);
		
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(Calendar.MONDAY);
		d.setMinimalDaysInFirstWeek(7);
		d.setTime(date);
		//判断 本周第一天是否 在 当前月
		if (c.get(Calendar.MONTH) == d.get(Calendar.MONTH))
		{
			int year = d.get(Calendar.YEAR);
			int month = d.get(Calendar.MONTH);
			int week = d.get(Calendar.WEEK_OF_MONTH);
			List<Integer> result = new ArrayList<Integer>(3);
			result.add(year);
			result.add(month + 1);
			result.add(week);

			return result;
		}
		else
		{
			return getWorkTimeStr(weekStartDate);
		}
	}

	/**
	 * 返回指定日期的自然周起止日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> getWeekStartAndEnd(Date date)
	{
		List<Date> result = new ArrayList<Date>(2);

		Date s = getFirstDayOfWeek(date);
		Date e = getLastDayOfWeek(date);
		result.add(s);
		result.add(e);
		return result;
	}

	public static Date getFirstDayOfWeek(Date date)
	{
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	public static Date getLastDayOfWeek(Date date)
	{
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
	

  
	/**
	 * 转换为java.util.Date对象.
	 * @param value 带转换对象
	 * @return 对应的Date对象
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @throws Exception 
	 * @date 2013-04-17  08:42:05
	 */
	public static final Date toDate(Object value) throws Exception {
		Date result = null;
		  
        if (value instanceof String) {
        	if (StringUtils.isNotEmpty((String)value)) {
	            result = org.apache.commons.lang3.time.DateUtils.parseDate((String) value, new String[] { DATE_PATTERN, DATETIME_PATTERN, MONTH_PATTERN });
	            
	            if (result == null && StringUtils.isNotEmpty((String)value)) {
	                try {
	                    result = new Date(new Long((String) value).longValue());
	                } catch (Exception e) {
	                    throw e;
	                }
	            }
        	}
        } else if (value instanceof Object[]) {
            Object[] array = (Object[]) value;
            
            if ((array != null) && (array.length >= 1)) {
                value = array[0];
                result = toDate(value);
            }
        } else if (Date.class.isAssignableFrom(value.getClass())) {
            result = (Date) value;
        }
        
        return result;
	}
	
	/**
	 * 转换为字符串.
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-06-18  18:04:48
	 */
	public static final String toString(Date date, String pattern) {
		String result = null;
		
		if (date != null) {
			result = DateFormatUtils.format(date, pattern);
		}
		
		return result;
	}


	
	public static String getDateNoUnderlined(){
		return toString(new Date(), DATETIME_NO_UNDERLINED_PATTERN);
	}
	
	/**
	 * 获取当前时间，格式：yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static String getCurrentDateTime(){
		return toString(new Date(),DATETIME_PATTERN);
	}
	/**
	 * 
	 * <p>Title: getDateFileSeparator</p>
	 * <p>Description: 获取日期路径 yyyy/MM/dd</p>
	 * @return
	 */
	public static String getDateFileSeparator(){
		return toString(new Date(), DATE_FILE_SEPARATOR_SPATTERN);
	}
	/**
	 * 
	 * <p>Title: getDateFileSeparator</p>
	 * <p>Description: 获取日期路径 yyyy/MM/dd</p>
	 * @return
	 * @throws Exception 
	 */
	public static String getDateFileSeparator(String dateStr) throws Exception{
		return toString(toDate(dateStr), DATE_FILE_SEPARATOR_SPATTERN);
	}
	
	
	/**
	 * 判断某一时间是否在开始时间和结束时间内, 常用来判断当前时间是否处于两个时间内
	 * @param date 给定的时间的时间
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return true : 给定的时间在范围内
	 *         false : 给定的时间不在范围内
	 */
	public static boolean isBetweenStartDateAndEndDate(Date date,Date startDate,Date endDate){
		if(date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 日期字符串，转换为dateFormat格式
	 * @param dateStr
	 * @param dateFormat
	 * @return
	 * @throws Exception
	 */
	public static String toDate(String dateStr,String dateFormat) throws Exception{
		String result = null;
		if(StringUtils.isEmpty(dateFormat)){
			dateFormat = DATETIME_PATTERN;
		}
		if (StringUtils.isNotEmpty(dateStr)) {
			Date date = DateUtil.toDate(dateStr);
			result = DateUtil.toString(date, dateFormat);
		}
		return result;
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getCurrentDate(){
		return new Date();
	}
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurrentStringDate(){
		return toString(new Date(),DATE_PATTERN);
	}
	/**
	 * 获取当前月
	 * @return
	 */
	public static String getCurrentStringMonth(){
		return toString(new Date(),MONTH_PATTERN);
	}

	/**
	 * 获取当前年
	 * @return
	 */
	public static String getCurrentStringYear(){
		return toString(new Date(),YEAR_PATTERN);
	}

	/**
	 * 计算天数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String dateDiff(String startTime, String endTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date one;  
        Date two;  
        long day = 0;  
        long hour = 0;  
        long min = 0;
        String mark = null;
        //long sec = 0;  
        try {  
            one = df.parse(startTime);  
            two = df.parse(endTime);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000) - day * 24);  
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60); 
            if(Integer.parseInt(String.valueOf(day))>0){
            	mark = Integer.parseInt(String.valueOf(day))+"天前";
            }else if(Integer.parseInt(String.valueOf(hour))>0){
            	mark = Integer.parseInt(String.valueOf(hour))+"小时前";
            }else{
            	mark = Integer.parseInt(String.valueOf(min))+"分钟前";
            }
            //sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
		return mark;
	}

	/**
	 * 获取某个时间段内的所有日期
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}
	
	/**
	 * 比较两个时间的大小
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compareTwoDate(String DATE1, String DATE2) {	        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	/**
	 * 解析字符串为日期，格式为年月日
	 *
	 * @param str
	 * @return
	 */
	public static Date parseDate(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = DateUtils.parseDate(str,
						new String[] { DATE_PATTERN });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 解析字符串为日期，格式为年月日时分
	 *
	 * @param str
	 * @return
	 */
	public static Date parseDateTime(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = DateUtils.parseDate(str,
						new String[] { PATTERN_DATETIME });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 解析字符串为日期，格式为年月日时分秒
	 *
	 * @param str
	 * @return
	 */
	public static Date parseDateTimess(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = DateUtils.parseDate(str,
						new String[] { DATETIME_PATTERN });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 对字符串进行解析，判断是否为月份
	 * @param month
	 * @return
	 */
	public static boolean isValidDate(String month) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("MM");
		try {
			//设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(month);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

}