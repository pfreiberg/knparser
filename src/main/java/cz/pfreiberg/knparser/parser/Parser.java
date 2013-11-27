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
		// TODO bude třeba promyslet jak to číst
		Scanner scanner = new Scanner(file, "windows-1250");
		scanner.useDelimiter("\r\n");
		while (scanner.hasNext()) {
			String nextToken = scanner.next();
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
		else if (EncodingCzech.windows1250.equalsVfk(encoding)) {
			return new Scanner(file, EncodingCzech.windows1250.getEncoding());
		} else if (EncodingCzech.iso88592.equalsVfk(encoding)) {
			return new Scanner(file, EncodingCzech.iso88592.getEncoding());
		}
		throw new ParserException("Unsupported encoding.");
	}

}
