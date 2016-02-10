package com.videri.core.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Simple utility for converting between <code>java.util.Date</code> objects and their ISO8601 String representation.
 * Uses the ISO8601 standard date format with millisecond precision.
 */
@SuppressLint("SimpleDateFormat")
public class ISO8601Formatter {

	private static final String ISO8601Format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static DateFormat formatter;

	private static DateFormat getFormatter(){
		if(formatter == null) {
			formatter = new SimpleDateFormat(ISO8601Format);
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		return formatter;
	}

	/**
	 * Get the input <code>java.util.Date</code> as an ISO8601-formatted String.
	 * 
	 * @param input
	 *            the <code>Date</code> object to convert.
	 * @return the String representation of the input <code>Date</code> object.
	 */
	public static String asString(Date input) {
		if (input == null) {
			return null;
		}

		InternalLogger.d("Formatting date as an ISO String.");		
		return getFormatter().format(input);
	}

	/**
	 * Get the input ISO8601-formatted String as a <code>java.util.Date</code>.
	 * 
	 * @param input
	 *            the ISO8601-formatted date String.
	 * @return the <code>java.util.Date</code> object representation of the input date String.
	 */
	public static Date asDate(String input) {
		if (input == null) {
			return null;
		}

		Date date;
		try {
			date = asDateUnchecked(input);
		}
		catch (ParseException e) {
			InternalLogger.e("Unable to parse String to Date using ISO format.", e);
			date = null;
		}

		return date;
	}
	
	/**
	 * Get the input ISO8601-formatted String as a <code>java.util.Date</code>, without catching exceptions.
	 * 
	 * @param input
	 *            the ISO8601-formatted date String.
	 * @return the <code>java.util.Date</code> object representation of the input date String.
	 * @throws ParseException if input cannot be parsed to a Date
	 */
	public static Date asDateUnchecked(String input) throws ParseException {
		InternalLogger.d("Parsing String to Date using ISO format.");
		return getFormatter().parse(input);
	}
}
