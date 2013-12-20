package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniPoMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniPoMapyParser {

	public static SpojeniPoMapy parse(String[] tokens) {
		int i = 0;

		SpojeniPoMapy spojeniPoMapy = new SpojeniPoMapy();
		spojeniPoMapy.setStavDat(VfkUtil.getInteger(tokens, i++));
		spojeniPoMapy.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		spojeniPoMapy.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		spojeniPoMapy.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		spojeniPoMapy.setPoradoveCisloBodu(VfkUtil.getLong(tokens, i++));
		spojeniPoMapy.setSouradniceY(VfkUtil.getDouble(tokens, i++));
		spojeniPoMapy.setSouradniceX(VfkUtil.getDouble(tokens, i++));
		spojeniPoMapy.setPomId(VfkUtil.getLong(tokens, i++));
		spojeniPoMapy.setParametrySpojeni(VfkUtil.getString(tokens, i++));

		return spojeniPoMapy;
	}

}
