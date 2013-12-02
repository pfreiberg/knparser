package cz.pfreiberg.knparser.parser;

import java.util.Scanner;

import cz.pfreiberg.knparser.domain.Vfk;

public class VfkHeadParser {
	
	public static void parse(Scanner scanner, Vfk vfk) throws ParserException
	{
		while (scanner.hasNext()) {
			String nextToken = scanner.next();
			if (nextToken.contains("&HZMENY")) {
				try {
					int hzmenyValue = Integer.parseInt(nextToken.split(";")[1]);
					if (hzmenyValue == 1) {
						vfk.setZmeny(1);
					} else if (hzmenyValue == 0) {
						vfk.setZmeny(0);
					} else
						throw new ParserException("HZMENY has undefine value.");
				} catch (NumberFormatException e) {
					throw new ParserException(
							"HZMENY must have number value (0 | 1).");
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new ParserException("HZMENY has undefine value.");
				}
			}
		}
	}

}
