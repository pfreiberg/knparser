package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.TypyUcastniku;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TypyUcastnikuParser {

	public static TypyUcastniku parse(String[] tokens) {
		int i = 0;

		TypyUcastniku typyUcastniku = new TypyUcastniku();
		typyUcastniku.setKod(VfkUtil.getString(tokens[i++]));
		typyUcastniku.setNazev(VfkUtil.getString(tokens[i++]));
		typyUcastniku.setPopis(VfkUtil.getString(tokens[i++]));

		return typyUcastniku;
	}

}
