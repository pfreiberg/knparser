package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiPoz;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpVyuzitiPozParser {

	public static ZpVyuzitiPoz parse(String tokens[]) {
		int i = 0;

		ZpVyuzitiPoz zpVyuzitiPoz = new ZpVyuzitiPoz();
		zpVyuzitiPoz.setKod(VfkUtil.getInteger(tokens, i++));
		zpVyuzitiPoz.setNazev(VfkUtil.getString(tokens, i++));
		zpVyuzitiPoz.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		zpVyuzitiPoz.setTypppKod(VfkUtil.getLong(tokens, i++));
		zpVyuzitiPoz.setPlatnostDo(VfkUtil.getDate(tokens, i++));
		zpVyuzitiPoz.setZkratka(VfkUtil.getString(tokens, i++));

		return zpVyuzitiPoz;
	}

}
