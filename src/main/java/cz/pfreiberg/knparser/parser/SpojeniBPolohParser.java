package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBPoloh;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBPolohParser {

	public static SpojeniBPoloh parse(String[] tokens) {
		int i = 0;

		SpojeniBPoloh spojeniBPoloh = new SpojeniBPoloh();
		spojeniBPoloh.setId(VfkUtil.getLong(tokens[i++]));
		spojeniBPoloh.setStavDat(VfkUtil.getInteger(tokens[i++]));
		spojeniBPoloh.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		spojeniBPoloh.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		spojeniBPoloh.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		spojeniBPoloh.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		spojeniBPoloh.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		spojeniBPoloh.setBpId(VfkUtil.getLong(tokens[i++]));
		spojeniBPoloh.setPoradoveCisloBodu(VfkUtil.getLong(tokens[i++]));
		spojeniBPoloh.setObId(VfkUtil.getLong(tokens[i++]));
		spojeniBPoloh.setHpId(VfkUtil.getLong(tokens[i++]));
		spojeniBPoloh.setDpmId(VfkUtil.getLong(tokens[i++]));
		spojeniBPoloh.setParametrySpojeni(VfkUtil.getString(tokens[i++]));
		spojeniBPoloh.setZvbId(VfkUtil.getLong(tokens[i++]));
		return spojeniBPoloh;
	}

}
