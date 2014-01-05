package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.jednotky.Jednotky;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JednotkyParser {

	public static Jednotky parse(String[] tokens) {
		int i = 0;

		Jednotky jednotky = new Jednotky();
		jednotky.setId(VfkUtil.getLong(tokens, i++));
		jednotky.setStavDat(VfkUtil.getInteger(tokens, i++));
		jednotky.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		jednotky.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		jednotky.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		jednotky.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		jednotky.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		jednotky.setBudId(VfkUtil.getLong(tokens, i++));
		jednotky.setTypjedKod(VfkUtil.getInteger(tokens, i++));
		jednotky.setCisloJednotky(VfkUtil.getInteger(tokens, i++));
		jednotky.setCenaNemovitosti(VfkUtil.getDouble(tokens, i++));
		jednotky.setZpvyjeKod(VfkUtil.getInteger(tokens, i++));
		jednotky.setTelId(VfkUtil.getLong(tokens, i++));
		jednotky.setPodilCitatel(VfkUtil.getLong(tokens, i++));
		jednotky.setPodilJmenovatel(VfkUtil.getLong(tokens, i++));
		jednotky.setPopis(VfkUtil.getString(tokens, i++));

		return jednotky;
	}

}
