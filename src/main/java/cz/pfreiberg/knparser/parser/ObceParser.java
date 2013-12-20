package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.Obce;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObceParser {

	public static Obce parse(String tokens[]) {
		int i = 0;

		Obce obec = new Obce();
		obec.setKod(VfkUtil.getInteger(tokens, i++));
		obec.setOkresyKod(VfkUtil.getInteger(tokens, i++));
		obec.setNazev(VfkUtil.getString(tokens, i++));
		obec.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		obec.setPlatnostDo(VfkUtil.getDate(tokens, i++));

		return obec;
	}

}
