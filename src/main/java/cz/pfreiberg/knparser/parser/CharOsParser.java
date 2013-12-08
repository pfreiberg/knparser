package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.vlastnictvi.CharOs;
import cz.pfreiberg.knparser.util.VfkUtil;

public class CharOsParser {

	public static CharOs parse(String[] tokens) {
		int i = 0;

		CharOs charOs = new CharOs();
		charOs.setKod(VfkUtil.getInteger(tokens[i++]));
		charOs.setNazev(VfkUtil.getString(tokens[i++]));
		charOs.setOpsubType(VfkUtil.getString(tokens[i++]));
		charOs.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		charOs.setPlatnostDo(VfkUtil.getDate(tokens[i++]));
		charOs.setZkratka(VfkUtil.getString(tokens[i++]));

		return charOs;
	}
}
