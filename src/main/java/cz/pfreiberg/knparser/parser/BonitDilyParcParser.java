package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.bonitnidilparcely.BonitDilyParc;
import cz.pfreiberg.knparser.util.VfkUtil;

public class BonitDilyParcParser {

	public static BonitDilyParc parse(String[] tokens) {
		int i = 0;

		BonitDilyParc bonitDilyParc = new BonitDilyParc();
		bonitDilyParc.setStavDat(VfkUtil.getInteger(tokens, i++));
		bonitDilyParc.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		bonitDilyParc.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		bonitDilyParc.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		bonitDilyParc.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		bonitDilyParc.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		bonitDilyParc.setParId(VfkUtil.getLong(tokens, i++));
		bonitDilyParc.setBpejKod(VfkUtil.getString(tokens, i++));
		bonitDilyParc.setVymera(VfkUtil.getInteger(tokens, i++));

		return bonitDilyParc;
	}

}
