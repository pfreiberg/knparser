package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.Obce;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParserObce {

	public static Obce parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		Obce obec = new Obce();
		obec.setKod(VfkUtil.getInteger(tokens[i++]));
		obec.setOkresyKod(VfkUtil.getInteger(tokens[i++]));
		obec.setNazev(VfkUtil.getString(tokens[i++]));
		obec.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		obec.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return obec;
	}

}
