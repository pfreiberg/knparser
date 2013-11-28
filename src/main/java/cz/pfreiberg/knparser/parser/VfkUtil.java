package cz.pfreiberg.knparser.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
}
