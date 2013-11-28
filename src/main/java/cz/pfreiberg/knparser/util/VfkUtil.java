package cz.pfreiberg.knparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.pfreiberg.knparser.parser.ParserException;

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

	public static Float getFloat(String value) {
		Float output = null;
		try {
			output = Float.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
		return output;
	}

	public static Date getDate(String value) {
		Date output  = null;
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

}
