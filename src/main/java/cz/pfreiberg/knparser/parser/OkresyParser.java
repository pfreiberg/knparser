package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.Okresy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OkresyParser {

	public static Okresy parse(String tokens[]) {
		int i = 0;

		Okresy okres = new Okresy();
		okres.setKod(VfkUtil.getInteger(tokens[i++]));
		okres.setKrajeKod(VfkUtil.getInteger(tokens[i++]));
		okres.setNazev(VfkUtil.getString(tokens[i++]));
		okres.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		okres.setPlatnostDo(VfkUtil.getDate(tokens[i++]));
		okres.setNuts4(VfkUtil.getString(tokens[i++]));
		okres.setNkrajeKod(VfkUtil.getInteger(tokens[i++]));

		return okres;
	}

}
