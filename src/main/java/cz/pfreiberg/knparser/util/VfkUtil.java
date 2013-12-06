package cz.pfreiberg.knparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
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

	public static String convertEncoding(String encoding) throws ParserException {
		if (EncodingCzech.windows1250.equalsVfk(encoding)) {
			return EncodingCzech.windows1250.getEncoding();
		} else if (EncodingCzech.iso88592.equalsVfk(encoding)) {
			return EncodingCzech.iso88592.getEncoding();
		}
		throw new ParserException("Unsupported encoding.");
	}

	public static Integer getInteger(String value) {
		Integer output = null;
		try {
			output = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
		return output;
	}

	public static Long getLong(String value) {
		Long output = null;
		try {
			output = Long.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
		return output;
	}

	public static Double getDouble(String value) {
		Double output = null;
		try {
			output = Double.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
		return output;
	}

	public static Date getDate(String value) {
		Date output = null;
		SimpleDateFormat format = new SimpleDateFormat(
				"\"dd.MM.yyyy HH:mm:ss\"");
		try {
			output = format.parse(value);
		} catch (ParseException e) {
			return null;
		}
		return output;
	}

	public static String getString(String value) {
		if (value == null || value.equals("\"\""))
			return null;
		return value;
	}

	public static String formatValue(Object value) {
		if (value == null)
			return "\"NULL\"";

		try {
			Date date = (Date) value;
			DateFormat df = new SimpleDateFormat("\"dd.MM.yyyy HH:mm:ss\"");
			return df.format(date);
		} catch (Exception e) {
			try {
				String string = (String) value;
				return string;
			} catch (Exception ee) {
				return "\"" + value + "\"";
			}
		}
	}
	
	public static String getTerminator()
	{
		return "|" + Character.toString((char)21) + "\n";
	}
}
