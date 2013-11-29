package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.Kraje;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParserKraje {

	public static Kraje parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		Kraje kraj = new Kraje();
		kraj.setKod(VfkUtil.getInteger(tokens[i++]));
		kraj.setNazev(VfkUtil.getString(tokens[i++]));
		kraj.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		kraj.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return kraj;
	}

}
