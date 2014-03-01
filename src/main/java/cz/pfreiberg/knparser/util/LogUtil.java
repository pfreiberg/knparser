package cz.pfreiberg.knparser.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogUtil {
	
	public static String getClassWhichThrowsException(String stackTrace) {
		String inBrackets = "\\((.*?)\\)";
		Pattern pattern = Pattern.compile(inBrackets);
		Matcher matcher = pattern.matcher(stackTrace);
		if (matcher.find()) {
			return stackTrace.substring(matcher.start() + 1, matcher.end() - 1)
					.split("Oracle")[0];
		}
		return "";
	}
	
	public static String getMethodWhichThrowsException(String stackTrace) {
		return (stackTrace.contains("insertRecord")) ? "record"
				: "historical record";
	}
	
}
