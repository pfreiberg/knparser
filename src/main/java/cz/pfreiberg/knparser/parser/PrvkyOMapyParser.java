package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.PrvkyOMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class PrvkyOMapyParser {

	public static PrvkyOMapy parse(String[] tokens) {
		int i = 0;

		PrvkyOMapy prvkyOMapy = new PrvkyOMapy();
		prvkyOMapy.setId(VfkUtil.getLong(tokens[i++]));
		prvkyOMapy.setStavDat(VfkUtil.getInteger(tokens[i++]));
		prvkyOMapy.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		prvkyOMapy.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		prvkyOMapy.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		prvkyOMapy.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		prvkyOMapy.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		prvkyOMapy.setTypppdKod(VfkUtil.getInteger(tokens[i++]));
		prvkyOMapy.setText(VfkUtil.getString(tokens[i++]));
		prvkyOMapy.setVelikost(VfkUtil.getDouble(tokens[i++]));
		prvkyOMapy.setUhel(VfkUtil.getDouble(tokens[i++]));
		prvkyOMapy.setVztaznyBod(VfkUtil.getInteger(tokens[i++]));
		prvkyOMapy.setKatuzeKod(VfkUtil.getInteger(tokens[i++]));

		return prvkyOMapy;
	}

}
