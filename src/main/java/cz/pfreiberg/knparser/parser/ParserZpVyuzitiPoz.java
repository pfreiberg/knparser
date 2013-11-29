package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiPoz;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParserZpVyuzitiPoz {

	public static ZpVyuzitiPoz parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		ZpVyuzitiPoz zpVyuzitiPoz = new ZpVyuzitiPoz();
		zpVyuzitiPoz.setKod(VfkUtil.getInteger(tokens[i++]));
		zpVyuzitiPoz.setNazev(VfkUtil.getString(tokens[i++]));
		zpVyuzitiPoz.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		zpVyuzitiPoz.setTypppKod(VfkUtil.getInteger(tokens[i++]));
		zpVyuzitiPoz.setPlatnostDo(VfkUtil.getDate(tokens[i++]));
		zpVyuzitiPoz.setZkratka(VfkUtil.getString(tokens[i++]));

		return zpVyuzitiPoz;
	}

}
