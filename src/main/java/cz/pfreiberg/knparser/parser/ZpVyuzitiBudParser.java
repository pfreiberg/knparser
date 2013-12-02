package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiBud;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpVyuzitiBudParser {

	public static ZpVyuzitiBud parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		ZpVyuzitiBud zpVyuzitiBud = new ZpVyuzitiBud();
		zpVyuzitiBud.setKod(VfkUtil.getInteger(tokens[i++]));
		zpVyuzitiBud.setNazev(VfkUtil.getString(tokens[i++]));
		zpVyuzitiBud.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		zpVyuzitiBud.setPlastnostDo(VfkUtil.getDate(tokens[i++]));
		zpVyuzitiBud.setZkratka(VfkUtil.getString(tokens[i++]));

		return zpVyuzitiBud;
	}

}
