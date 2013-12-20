package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.PredmetyRizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class PredmetyRizeniParser {

	public static PredmetyRizeni parse(String[] tokens) {
		int i = 0;

		PredmetyRizeni predmetyRizeni = new PredmetyRizeni();
		predmetyRizeni.setRizeniId(VfkUtil.getLong(tokens, i++));
		predmetyRizeni.setTyppreKod(VfkUtil.getInteger(tokens, i++));

		return predmetyRizeni;
	}

}
