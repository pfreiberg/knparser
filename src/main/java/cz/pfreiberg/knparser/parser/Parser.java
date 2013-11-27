package cz.pfreiberg.knparser.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.pfreiberg.knparser.Configuration;

public class Parser {

	Configuration configuration;

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException {
		this.configuration = configuration;

		File file = new File(configuration.getPathToFile());
		

		String encoding = getEncoding(file);
		Scanner scanner = setEncoding(file, encoding);

	}

	private String getEncoding(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			String nextToken = scanner.next();
			if (nextToken.contains("&HCODEPAGE")) {
				Pattern pattern = Pattern.compile("\"([^\"]*)\"");
				Matcher matcher = pattern.matcher(nextToken);
				scanner.close();
				return matcher.group(1).toString();
			}
		}

		scanner.close();
		return "";
	}

	private Scanner setEncoding(File file, String encoding)
			throws ParserException, FileNotFoundException {
		if (encoding == "")
			throw new ParserException("Encoding was NOT found in the file.");
		else if (EncodingCzech.EE8MSWIN1250.equals(encoding)) {
			System.out.println(EncodingCzech.EE8MSWIN1250.getEncoding());
			return new Scanner(file, EncodingCzech.EE8MSWIN1250.getEncoding());
		} else if (EncodingCzech.WE8ISO8859P2.equals(encoding)) {
			System.out.println(EncodingCzech.WE8ISO8859P2.getEncoding());
			return new Scanner(file, EncodingCzech.WE8ISO8859P2.getEncoding());
		}
		throw new ParserException("Unsupported encoding.");
	}

}
