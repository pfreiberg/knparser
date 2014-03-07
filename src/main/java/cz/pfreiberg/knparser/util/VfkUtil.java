package cz.pfreiberg.knparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.pfreiberg.knparser.parser.ParserException;

/**
 * Pomocná třída s metodami pro práci nad VFK.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class VfkUtil {

	/**
	 * SimpleDateFormat není vláknově bezpečný (jedna instance pro
	 * producenta, druhá pro konzumenta).
	 */
	private final static SimpleDateFormat formatParse = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm:ss");

	private final static SimpleDateFormat formatStoring = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm:ss");

	public static String getEncoding(File file) throws ParserException,
			IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String currentLine;
		try {
			while ((currentLine = br.readLine()) != null) {
				if (currentLine.contains("&HCODEPAGE")) {
					return currentLine.split("\"")[1];
				}
			}
			throw new ParserException("HCODEPAGE was NOT found in the file.");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ParserException("HCODEPAGE has undefine value.");
		} finally {
			br.close();
		}
	}

	public static String convertEncoding(String encoding)
			throws ParserException {
		if (EncodingCzech.windows1250.equalsVfk(encoding)) {
			return EncodingCzech.windows1250.getEncoding();
		} else if (EncodingCzech.iso88592.equalsVfk(encoding)) {
			return EncodingCzech.iso88592.getEncoding();
		}
		throw new ParserException("Unsupported encoding.");
	}

	public static Integer getInteger(String[] value, int i) {

		if (isOutOfIndex(value, i) || isEmptyValue(value, i))
			return null;

		try {
			return Integer.valueOf(value[i]);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Long getLong(String[] value, int i) {

		if (isOutOfIndex(value, i) || isEmptyValue(value, i))
			return null;

		try {
			return Long.valueOf(value[i]);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static BigInteger getBigInteger(String[] value, int i) {
		if (isOutOfIndex(value, i) || isEmptyValue(value, i))
			return null;

		try {
			return new BigInteger(value[i]);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Double getDouble(String[] value, int i) {

		if (isOutOfIndex(value, i) || isEmptyValue(value, i))
			return null;

		try {
			return Double.valueOf(value[i]);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Date getDate(String[] value, int i) {

		if (isOutOfIndex(value, i) || isEmptyValue(value, i))
			return null;

		try {
			return formatParse.parse(value[i]);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String getString(String[] value, int i) {

		if (isOutOfIndex(value, i) || isEmptyValue(value, i))
			return null;

		return value[i];
	}

	public static java.sql.Date convertToDatabaseDate(Date date) {
		if (date == null)
			return null;

		return new java.sql.Date(date.getTime());
	}

	public static String formatValue(Object value) {

		if (value == null) {
			return "\"NULL\"";
		}

		if (value instanceof Date) {
			Date date = (Date) value;
			return "\"" + formatStoring.format(date) + "\"";
		} else {
			return "\"" + value + "\"";
		}

	}

	public static String formatValueDatabase(Object value) {

		if (value == null) {
			return "NULL";
		}

		if (value instanceof String) {
			return "'" + (String) value + "'";
		} else if (value instanceof Date) {
			Date date = (Date) value;
			return "to_date('" + formatStoring.format(date)
					+ "','dd.mm.yyyy hh24:mi:ss')";
		} else {
			return String.valueOf(value);
		}

	}

	public static String getTerminator() {
		return "|" + Character.toString((char) 21);
	}

	private static boolean isOutOfIndex(String[] value, int i) {
		return (value.length <= i);
	}

	private static boolean isEmptyValue(String[] value, int i) {
		return (value[i].length() == 0);
	}

}
