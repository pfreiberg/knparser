package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.UcastniciTyp;
import cz.pfreiberg.knparser.util.VfkUtil;

public class UcastniciTypParser {

	public static UcastniciTyp parse(String[] tokens) {
		int i = 0;

		UcastniciTyp ucastniciTyp = new UcastniciTyp();
		ucastniciTyp.setUcastId(VfkUtil.getLong(tokens[i++]));
		ucastniciTyp.setTypucaKod(VfkUtil.getString(tokens[i++]));

		return ucastniciTyp;
	}

}
