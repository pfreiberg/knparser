package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.TypyRizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TypyRizeniParser {

	public static TypyRizeni parse(String[] tokens) {
		int i = 0;

		TypyRizeni typyRizeni = new TypyRizeni();
		typyRizeni.setKod(VfkUtil.getString(tokens[i++]));
		typyRizeni.setNazev(VfkUtil.getString(tokens[i++]));
		typyRizeni.setPopis(VfkUtil.getString(tokens[i++]));
		typyRizeni.setZpoplatneni(VfkUtil.getString(tokens[i++]));

		return typyRizeni;
	}

}
