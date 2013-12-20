package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.Kraje;
import cz.pfreiberg.knparser.util.VfkUtil;

public class KrajeParser {

	public static Kraje parse(String tokens[]) {
		int i = 0;

		Kraje kraj = new Kraje();
		kraj.setKod(VfkUtil.getInteger(tokens, i++));
		kraj.setNazev(VfkUtil.getString(tokens, i++));
		kraj.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		kraj.setPlatnostDo(VfkUtil.getDate(tokens, i++));

		return kraj;
	}

}
