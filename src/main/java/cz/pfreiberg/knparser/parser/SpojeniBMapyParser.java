package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBMapyParser {

	public static SpojeniBMapy parse(String[] tokens) {
		int i = 0;

		SpojeniBMapy spojeniBMapy = new SpojeniBMapy();
		spojeniBMapy.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		spojeniBMapy.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		spojeniBMapy.setPoradoveCisloBodu(VfkUtil.getLong(tokens[i++]));
		spojeniBMapy.setSouradniceX(VfkUtil.getDouble(tokens[i++]));
		spojeniBMapy.setSouradniceY(VfkUtil.getDouble(tokens[i++]));
		spojeniBMapy.setOpId(VfkUtil.getLong(tokens[i++]));
		spojeniBMapy.setDmpId(VfkUtil.getLong(tokens[i++]));
		spojeniBMapy.setHbpejId(VfkUtil.getLong(tokens[i++]));
		spojeniBMapy.setParametrySpojeni(VfkUtil.getString(tokens[i++]));
		spojeniBMapy.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		return spojeniBMapy;
	}

}
