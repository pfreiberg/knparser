package cz.pfreiberg.knparser.util;

import java.sql.SQLException;

public class LogUtil {

	public static String getClassWhichThrowsException(SQLException e) {
		for (StackTraceElement element : e.getStackTrace()) {
			if (element.getClassName().contains("pfreiberg")) {
				String[] tokens = element.getClassName().split("\\.");
				return tokens[tokens.length - 1];
			}
		}
		return "";
	}

	public static String getHisMethodWhichThrowsException(SQLException e) {
		for (StackTraceElement element : e.getStackTrace()) {
			if (element.getClassName().contains("pfreiberg")) {
				return element.getMethodName().equals("insertRecord") ? "record"
						: "historical record";
			}

		}
		return "";
	}

}
