package com.project.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author ToanNguyen 2019-Jan-18 14:34 (verified)
 *
 */
public class ZString {
	// region -- Fields --

	// end

	// region -- Methods --

	/**
	 * Format string with Proper Case
	 * 
	 * @param s Input string
	 * @return
	 */
	public static String format(String s) {
		String res = "";
		String space = " ";

		if (isBlank(s)) {
			return res;
		}

		s = s.toLowerCase();
		String[] l = s.split(space);

		for (String i : l) {
			Integer len = i.length();

			if (len > 1) {
				res += i.substring(0, 1).toUpperCase() + i.substring(1, len) + space;
			} else if (len > 0) {
				res += i.substring(0, 1).toUpperCase() + space;
			}
		}

		res = removeEnd(res, space);
		return res;
	}

	/**
	 * Convert string with separation ',' to list
	 * 
	 * @param s Input string
	 * @return
	 */
	public static List<String> toList(String s) {
		return toList(s, ",");
	}

	/**
	 * Convert list to string with separation ','
	 * 
	 * @param l Input list string
	 * @return
	 */
	public static String to(List<String> l) {
		return to(l, ",");
	}

	/**
	 * Convert string with separation to list
	 * 
	 * @param s          Input string
	 * @param separation String separation
	 * @return
	 */
	public static List<String> toList(String s, String separation) {
		List<String> res = new ArrayList<String>();

		if (isBlank(s)) {
			return res;
		}

		res = Arrays.asList(s.split(separation));
		return res;
	}

	/**
	 * Convert list to string with separation
	 * 
	 * @param l          Input list string
	 * @param separation String separation
	 * @return
	 */
	public static String to(List<String> l, String separation) {
		String res = "";

		if (l == null) {
			return res;
		}

		for (String i : l) {
			res += i + separation;
		}

		res = removeEnd(res, separation);
		return res;
	}

	/**
	 * Remove a string end of input string
	 * 
	 * @param s      Input string
	 * @param remove String will remove
	 * @return
	 */
	public static String removeEnd(String s, String remove) {
		String res = "";

		if (isBlank(remove)) {
			return s;
		}

		if (s.endsWith(remove)) {
			int size = s.length() - remove.length();
			res = s.substring(0, size);
		}

		return res;
	}

	/**
	 * Checking string is blank
	 * 
	 * @param s Input string
	 * @return
	 */
	public static boolean isBlank(String s) {
		return s == null || s.isEmpty();
	}

	// end
}