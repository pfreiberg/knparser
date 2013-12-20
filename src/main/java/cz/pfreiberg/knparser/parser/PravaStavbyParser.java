package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.PravaStavby;
import cz.pfreiberg.knparser.util.VfkUtil;

public class PravaStavbyParser {

	public static PravaStavby parse(String[] tokens) {
		int i = 0;

		PravaStavby pravaStavby = new PravaStavby();
		pravaStavby.setId(VfkUtil.getLong(tokens, i++));
		pravaStavby.setStavDat(VfkUtil.getInteger(tokens, i++));
		pravaStavby.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		pravaStavby.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		pravaStavby.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		pravaStavby.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		pravaStavby.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		pravaStavby.setDatumPrijeti(VfkUtil.getDate(tokens, i++));
		pravaStavby.setTelId(VfkUtil.getLong(tokens, i++));
		pravaStavby.setDatumUkonceni(VfkUtil.getDate(tokens, i++));

		return pravaStavby;
	}

}
