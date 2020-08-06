package com.tubaoapi.modules.utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils2 {
	
	public static final long ONEDAY = 24 * 3600 * 1000;
	public static final long ONEHOUR = 3600 * 1000; 
	public static final long ONEMINUTE = 60 * 1000; 

	// month string in one year
	public static final String MONTHS[] = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December", };

	public static final String DAYSOFWEEK_FULL[] = { "Sunday", "Monday",
			"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	public static final String DAYSOFWEEK[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六" };
	

	// Calendar month variable in one year.
	private static final int MONTHS_CALENDAR[] = { Calendar.JANUARY,
			Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY,
			Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER,
			Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER };

	
	private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE = "yyyy-MM-dd";
	private static final String TIME = "HH:mm:ss";

	
	/**
	 * get current date
	 * 
	 */
	public static Date newDay() {
		return dt2d(new Date());
		
	}
	
	
	public static Date dt2d(Date d){
		return s2d(d2s(d)) ;
	}
	
	


	/**
	 * get a new date, by year, month and day.
	 * 
	 */
	public static Date newDay(int year, int month, int day) {

		if (month < 1) {
			month = 1;
		}
		if (month > 12) {
			month = 12;

		}
		GregorianCalendar cal = new GregorianCalendar(year,	MONTHS_CALENDAR[month - 1], day);
		return cal.getTime();

	}
	
	public static String d2s(Date date, String format) {

		try {

			if (date == null) {
				return "";
			}

			SimpleDateFormat dateFormatter = new SimpleDateFormat();
			dateFormatter.applyPattern(format);
			return dateFormatter.format(date);

		} catch (Exception e) {
			return "";
		}
	}	
	
	public static Date s2d(String str, String format) {

		try {
			if (str == null || str.equals("")
					|| str.indexOf("0000-00-00") != -1 || str.length() < 6) {
				return null;
			}

			char[] validChars = { 'a', 'm', 'p', 'A', 'M', 'P', '-', '/', ' ',
					':', '.' };
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c >= '0' && c <= '9') {
					continue;
				}

				boolean isValid = false;
				for (int j = 0; j < validChars.length; j++) {
					char validC = validChars[j];
					if (c == validC) {
						isValid = true;
						break;
					}
				}

				if (!isValid) {
					return null;
				}

			}

			SimpleDateFormat dateFormatter = new SimpleDateFormat();
			dateFormatter.applyPattern(format);

			return dateFormatter.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	

	public static Date s2dt(String s) {
		return s2d(s, DATETIME);
	}

	public static Date s2d(String s) {
		return s2d(s, DATE);
	}

	public static Date s2t(String s) {
		return s2d(s, TIME);
	}

	public static String dt2s(Date date) {
		return d2s(date, DATETIME);
	}

	public static String d2s(Date date) {
		return d2s(date, DATE);
	}

	public static String t2s(Date date) {
		return d2s(date, TIME);
	}

	/**
	 * Get the year part from a date
	 * 
	 */
	public static int getYear(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		int i = cal.get(Calendar.YEAR);
		cal = null;
		return i;
	}

	/**
	 * Get the month part from a date
	 * 
	 */
	public static int getMonth(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		int i = cal.get(Calendar.MONTH) + 1; // Calendar.JANUARY==0
		cal = null;
		return i;
	}

	public static String getMonthFullStr(Date d) {
		return MONTHS[getMonth(d) - 1];
	}

	/**
	 * Get the day part from a date
	 * 
	 */

	public static int getDay(Date date) {
		if (date == null) {
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}
	

	public static boolean isWeekend(Date date){
		int i = getDayOfWeek(date);
		return i == 6 || i == 7;
	}

	// 1=Sunday, 2=Monday,3=Tuesday, 4=Wednesday, 5=Thursday, 6=Friday,
	// 7=Saturday
	public static int getDayOfWeek(Date date) {
		if (date == null) {
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static String getDayOfWeekToStr(Date date) {
		int dayOfWeek = getDayOfWeek(date);
		return DAYSOFWEEK[dayOfWeek - 1];
	}

	public static String getDayOfWeekToFullStr(Date date) {
		int dayOfWeek = getDayOfWeek(date);
		return DAYSOFWEEK_FULL[dayOfWeek - 1];
	}



	/**
	 * Get the hour part from a date
	 * 
	 */
	public static int getHour(Date date) {
		if (date == null) {
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR);

	}

	/**
	 * Get the minute part from a date
	 * 
	 */
	public static int getMinute(Date date) {
		if (date == null) {
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * Get the second part from a date
	 * 
	 * @param d
	 *            A date value to be extracted
	 * 
	 * @return the second part
	 * 
	 */
	public static int getSecond(Date date) {
		if (date == null) {
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.SECOND);

	}

	/**
	 * Get the millisecond part from a date
	 * 
	 * @param d
	 *            A date value to be extracted
	 * 
	 * @return the millisecond part
	 * 
	 */
	public static int getMillisecond(Date date) {
		if (date == null) {
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.MILLISECOND);

	}

	/**
	 * Get month name by a month id for example : 6 >> January
	 * 
	 */

	public static String intToMonth(int month) {

		if (month < 1 || month > 12) {
			return "";
		}

		return MONTHS[month - 1];
	}

	public static Date firstDayOfMonth(int year, int month) {
		return newDay(year, month, 1);
	}

	public static Date lastDayOfMonth(int year, int month) {
		month++;
		if (month > 12) {
			year++;
			month = 1;
		}
		// 1st day, of next month
		// Date lastDay =DateUtil.newDate(year,month,1);

		// first day of next month
		GregorianCalendar cal = new GregorianCalendar(year,
				MONTHS_CALENDAR[month - 1], 1);
		// last day of this month
		cal.add(GregorianCalendar.DATE, -1);

		// Mountain Standard Time (MST) = GMT-7
		// Mountain Daylight Time (MDT) = GMT-6
		// To avoid MDT problem:
		cal.add(GregorianCalendar.HOUR, 1);

		return cal.getTime();

	}

	public static int totalDaysOfMonth(int year, int month) {

		Date firstDay = firstDayOfMonth(year, month);
		Date lastDay = lastDayOfMonth(year, month);

		int total = (int) ((lastDay.getTime() - firstDay.getTime()) / ONEDAY) + 1;

		return total;
	}


	


	public static int dayOfWeek(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_WEEK); // 4=Wednesday
	}






	/**
	 * Get the first Day of a year which the date exists
	 * 
	 */
	public static Date firstDayOfYear(Date d) {
		int year = getYear(d);
		return s2d(year + "-01-01");
	}

	/**
	 * Get the last day of a year which the date exists
	 */
	public static Date lastDayOfYear(Date d) {
		int year = getYear(d);
		return s2d(year + "-12-31");
	}





	/**
	 * Get the first Day of a month which the date exists
	 */
	public static Date firstDayOfMonth(Date d) {
		int year = getYear(d);
		int month = getMonth(d);
		return s2d(year + "-" + month + "-01");
	}

	/**
	 * Get the last Day of a month which the date exists
	 * 
	 */
	public static Date lastDayOfMonth(Date d) {
		int year = getYear(d);
		int month = getMonth(d);

		// Firstly get the First day of next month:
		month++;
		if (month > 12) {
			year++;
			month = 1;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1); // Calendar.JANUARY==0
		cal.set(Calendar.DATE, 1);

		// Then get last day of this month:
		cal.add(Calendar.DATE, -1);

		Date newD = cal.getTime();
		cal = null;
		return newD;
	}

	public static Date previousDay(Date d) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.add(Calendar.DATE, -1);

		Date newD = cal.getTime();
		cal = null;
		return newD;
	}

	public static Date nextDay(Date d) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.add(Calendar.DATE, 1);

		Date newD = cal.getTime();
		cal = null;
		return newD;
	}

	public static Date addDay(Date d, int amount) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.add(Calendar.DATE, amount);

		Date newD = cal.getTime();
		cal = null;
		return newD;
	}
	
	public static Date addHour(Date d, int amount) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.add(Calendar.HOUR, amount);

		Date newD = cal.getTime();
		cal = null;
		return newD;
	}
	
	public static Date addMinute(Date d, int amount) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.add(Calendar.MINUTE, amount);

		Date newD = cal.getTime();
		cal = null;
		return newD;
	}
	

	public static int daysOfPeriod(Date start, Date end) {
		if (start == null || end == null) {
			return 0;
		}
		return (int) ((end.getTime() - start.getTime()) / ONEDAY);
	}

	
	public static int getAge(Date birthday) {
        Calendar c1 = Calendar.getInstance();
        long nowmillSeconds = c1.getTimeInMillis();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(birthday);
        long birmillSeconds = c2.getTimeInMillis();
        Calendar c3 = Calendar.getInstance();
        long millis = nowmillSeconds - birmillSeconds;
        c3.setTimeInMillis(millis);
        
        int year = c3.get(Calendar.YEAR);
        //int month = c3.get(Calendar.MONTH);
        //int day = c3.get(Calendar.DAY_OF_MONTH);
        //int hour = c3.get(Calendar.HOUR_OF_DAY);
        
        if (year > 1970) {
            return year - 1970 ;
        } else{
        	return 0;
        } 
    }
	
	
	
	/**
	 * 设置date的时间为当天的结束时间, 即23:59:59
	 * @param date
	 * @return
	 */
	public static Date setTimeToTheEndOfTheDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	/**
	 * 设置date的时间为当天的开始时间, 即00:00:00
	 * @param date
	 * @return
	 */
	public static Date setTimeToTheStartOfTheDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 设置date的时间为当月的开始时间
	 * @param date
	 * @return
	 */
	public static Date setTimeToTheStartOfTheMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);  
		
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 设置date的时间为当月的结束时间
	 * @param date
	 * @return
	 */
	public static Date setTimeToTheEndOfTheMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));   
		
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	public static void main(String args[]){
		
		Calendar cal = Calendar.getInstance();
		
		//cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		cal.set(2009, 1,3,0,0,0);
		System.out.println(cal.getTime());
	}


}
