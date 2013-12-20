package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.jednotky.ZpVyuzitiJed;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpVyuzitiJedParser {

	public static ZpVyuzitiJed parse(String[] tokens) {
		int i = 0;

		ZpVyuzitiJed zpVyuzitiJed = new ZpVyuzitiJed();
		zpVyuzitiJed.setKod(VfkUtil.getInteger(tokens, i++));
		zpVyuzitiJed.setNazev(VfkUtil.getString(tokens, i++));
		zpVyuzitiJed.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		zpVyuzitiJed.setPlatnostDo(VfkUtil.getDate(tokens, i++));
		zpVyuzitiJed.setZkratka(VfkUtil.getString(tokens, i++));
		zpVyuzitiJed.setDoplKod(VfkUtil.getInteger(tokens, i++));

		return zpVyuzitiJed;
	}

}
