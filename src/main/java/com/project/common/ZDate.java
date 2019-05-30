package com.project.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author ToanNguyen 2019-Jan-18 14:34 (verified)
 *
 */
public class ZDate {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(ZDate.class.getName());

	// end

	// region -- Methods --

	/**
	 * Get day
	 * 
	 * @param d
	 * @return
	 */
	public static int getDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int res = cal.get(Calendar.DATE);
		return res;
	}

	/**
	 * Get month
	 * 
	 * @param d
	 * @return
	 */
	public static int getMonth(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int res = cal.get(Calendar.MONTH);
		return res;
	}

	/**
	 * Get year
	 * 
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int res = cal.get(Calendar.YEAR);
		return res;
	}

	/**
	 * Get date only
	 * 
	 * @return
	 */
	public static Date today() {
		Date res = new Date();
		return removeTime(res);
	}

	/**
	 * Get date and time
	 * 
	 * @return
	 */
	public static Date now() {
		Date res = new Date();
		return res;
	}

	/**
	 * Get start of day
	 * 
	 * @param d
	 * @return
	 */
	public static Timestamp getStartOfDay(Date d) {
		Date t = removeTime(d);
		Timestamp res = new Timestamp(t.getTime());
		return res;
	}

	/**
	 * Get end of day
	 * 
	 * @param d
	 * @return
	 */
	public static Timestamp getEndOfDay(Date d) {
		Calendar cal = removeTime(d, -1);
		cal.add(Calendar.DAY_OF_YEAR, 1);

		Date t = cal.getTime();
		Timestamp res = new Timestamp(t.getTime());
		return res;
	}

	/**
	 * Remove time
	 * 
	 * @param d Date and time
	 * @return
	 */
	public static Date removeTime(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Remove time
	 * 
	 * @param d  Date and time
	 * @param ms Millisecond
	 * @return
	 */
	private static Calendar removeTime(Date d, int ms) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, ms);

		return cal;
	}

	/**
	 * Convert String to Date
	 * 
	 * @param s String date & time
	 * @return
	 */
	public static Date toDate(String s) {
		Date res = null;

		try {
			SimpleDateFormat t = new SimpleDateFormat(Const.DateTime.FULL);
			res = t.parse(s);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return res;
	}

	/**
	 * Convert String to Date
	 * 
	 * @param s String date & time
	 * @param f Format date e.g: dd/MM/yyyy
	 * @return
	 */
	public static Date toDate(String s, String f) {
		Date res = null;

		try {
			SimpleDateFormat t = new SimpleDateFormat(f);
			res = t.parse(s);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return res;
	}

	/**
	 * Convert Date to String
	 * 
	 * @param d Date & time
	 * @param s Format date e.g: dd/MM/yyyy
	 * @return
	 */
	public static String toString(Date d, String s) {
		String res = "";

		try {
			SimpleDateFormat t = new SimpleDateFormat(s);
			res = t.format(d);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Create a date
	 * 
	 * @param year  Year
	 * @param month Month
	 * @param day   Day
	 * @return
	 */
	public static Date create(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, getMonth(month));
		cal.set(Calendar.DATE, day);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Create a date
	 * 
	 * @param year   Year
	 * @param month  Month
	 * @param day    Day
	 * @param hour   Hour
	 * @param minute Minute
	 * @param second Second
	 * @return
	 */
	public static Date create(int year, int month, int day, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, getMonth(month));
		cal.set(Calendar.DATE, day);

		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Convert string to Date
	 * 
	 * @param s Input string
	 * @return
	 */
	public static Date toDatez(String s) {
		Date res = null;

		if (ZString.isBlank(s)) {
			return res;
		}

		try {
			// Get date only
			String[] a = s.split(" ");
			if (a.length < 1) {
				return res;
			}

			String b = getSeparation(s);
			return toDatez(a[0], b);
		} catch (Exception ex) {
		}

		return res;
	}

	/**
	 * Convert string to Date
	 * 
	 * @param s          Input string
	 * @param separation String separation
	 * @return
	 */
	private static Date toDatez(String s, String separation) {
		Date res = null;

		try {
			String[] t = s.split(separation);
			if (t.length < 3) {
				return res;
			}

			Integer day = 0;
			Integer month = Integer.valueOf(t[1]);
			Integer year = 0;

			if (t[0].length() == 4) {
				year = Integer.valueOf(t[0]);
				day = Integer.valueOf(t[2]);
			} else {
				day = Integer.valueOf(t[0]);
				year = Integer.valueOf(t[2]);
			}

			if (month < 1 || month > 12) {
				return res;
			}

			res = create(year, month, day);
		} catch (Exception ex) {
		}

		return res;
	}

	/**
	 * Get separation
	 * 
	 * @param s Input string
	 * @return
	 */
	private static String getSeparation(String s) {
		String res = "";

		if (ZString.isBlank(s)) {
			return res;
		}

		Integer len = s.length();
		for (Integer i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (!Character.isDigit(c)) {
				res = new String(new char[] { s.charAt(i) });
				break;
			}

		}

		return res;
	}

	/**
	 * Correct month with Calendar
	 * 
	 * @param month Month
	 * @return
	 */
	private static int getMonth(int month) {
		month = (month - 1) % 12;

		if (month < 0) {
			month = 0;
		}

		return month;
	}

	// end
}