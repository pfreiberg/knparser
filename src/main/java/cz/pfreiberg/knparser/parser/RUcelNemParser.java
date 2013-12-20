package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.RUcelNem;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RUcelNemParser {

	public static RUcelNem parse(String[] tokens) {
		int i = 0;

		RUcelNem rUcelNem = new RUcelNem();
		rUcelNem.setId(VfkUtil.getLong(tokens[i++]));
		rUcelNem.setStavDat(VfkUtil.getInteger(tokens[i++]));
		rUcelNem.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		rUcelNem.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		rUcelNem.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		rUcelNem.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		rUcelNem.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		rUcelNem.setPsId(VfkUtil.getLong(tokens[i++]));
		rUcelNem.setUcelKod(VfkUtil.getInteger(tokens[i++]));

		return rUcelNem;
	}

}
