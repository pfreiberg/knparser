package cz.pfreiberg.knparser.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
		// TODO bude třeba vyzkoušet, čím to číst
		Scanner scanner = new Scanner(file, "windows-1250");
		scanner.useDelimiter("\r\n");
		while (scanner.hasNext()) {
			String nextToken = scanner.next();
			System.out.println(nextToken);
			if (nextToken.contains("&HCODEPAGE")) {
				scanner.close();
				return nextToken.split("\"")[1];
			}
		}

		scanner.close();
		return "";
	}

	private Scanner setEncoding(File file, String encoding)
			throws ParserException, FileNotFoundException {
		if (encoding == "")
			throw new ParserException("Encoding was NOT found in the file.");
		else if (EncodingCzech.windows1250.equals(encoding)) {
			return new Scanner(file, "windows-1250");
		} else if (EncodingCzech.iso88592.equals(encoding)) {
			return new Scanner(file, "iso-8859-2");
		}
		throw new ParserException("Unsupported encoding.");
	}

}
