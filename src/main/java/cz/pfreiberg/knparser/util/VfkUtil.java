package cz.pfreiberg.knparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

	private final static SimpleDateFormat format = new SimpleDateFormat(
			"\"dd.MM.yyyy HH:mm:ss\"");

	public static String getEncoding(File file) throws ParserException,
			IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));
		String currentLine;

		while ((currentLine = br.readLine()) != null) {
			if (currentLine.contains("&HCODEPAGE")) {
				br.close();
				try {
					return currentLine.split("\"")[1];
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new ParserException("HCODEPAGE has undefine value.");
				}
			}
		}

		br.close();
		throw new ParserException("HCODEPAGE was NOT found in the file.");
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

		if (isOutOfIndex(value, i))
			return null;

		try {
			return Integer.valueOf(value[i]);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Long getLong(String[] value, int i) {

		if (isOutOfIndex(value, i))
			return null;

		try {
			return Long.valueOf(value[i]);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Double getDouble(String[] value, int i) {

		if (isOutOfIndex(value, i))
			return null;

		try {
			return Double.valueOf(value[i]);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Date getDate(String[] value, int i) {

		if (isOutOfIndex(value, i))
			return null;

		try {
			return format.parse(value[i]);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String getString(String[] value, int i) {

		if (isOutOfIndex(value, i))
			return null;

		if (value[i].equals("\"\""))
			return null;

		return value[i];
	}

	public static String formatValue(Object value) {

		if (value == null) {
			return "\"NULL\"";
		}

		if (value instanceof String) {
			return (String) value;
		} else if (value instanceof Date) {
			Date date = (Date) value;
			return format.format(date);
		} else {
			return "\"" + value + "\"";
		}

	}

	public static String getTerminator() {
		return "|" + Character.toString((char) 21);
	}

	public static boolean isControlFileCreated(String path) {
		File controlFile = new File(path);
		return controlFile.exists();
	}

	private static boolean isOutOfIndex(String[] value, int i) {
		return (value.length <= i);
	}

}
