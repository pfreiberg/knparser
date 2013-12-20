package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.geometrickyplan.Zpmz;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpmzParser {

	public static Zpmz parse(String[] tokens) {
		int i = 0;

		Zpmz zpmz = new Zpmz();
		zpmz.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		zpmz.setCisloZpmz(VfkUtil.getInteger(tokens, i++));
		zpmz.setPpzId(VfkUtil.getLong(tokens, i++));
		zpmz.setStavZpmz(VfkUtil.getInteger(tokens, i++));
		zpmz.setMerickyNacrt(VfkUtil.getString(tokens, i++));
		zpmz.setZapisnikPodrobMereni(VfkUtil.getString(tokens, i++));
		zpmz.setVypocetProtokolVymer(VfkUtil.getString(tokens, i++));
		zpmz.setTypsosKod(VfkUtil.getInteger(tokens, i++));

		return zpmz;
	}

}
