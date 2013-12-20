package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradniceObrazu;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SouradniceObrazuParser {

	public static SouradniceObrazu parse(String[] tokens) {
		int i = 0;

		SouradniceObrazu souradniceObrazu = new SouradniceObrazu();
		souradniceObrazu.setId(VfkUtil.getLong(tokens, i++));
		souradniceObrazu.setStavDat(VfkUtil.getInteger(tokens, i++));
		souradniceObrazu.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		souradniceObrazu.setCisloZpmz(VfkUtil.getInteger(tokens, i++));
		souradniceObrazu.setCisloTl(VfkUtil.getInteger(tokens, i++));
		souradniceObrazu.setCisloBodu(VfkUtil.getLong(tokens, i++));
		souradniceObrazu.setUplneCislo(VfkUtil.getLong(tokens, i++));
		souradniceObrazu.setSouradniceX(VfkUtil.getDouble(tokens, i++));
		souradniceObrazu.setSouradniceY(VfkUtil.getDouble(tokens, i++));
		souradniceObrazu.setKodchbKod(VfkUtil.getInteger(tokens, i++));
		return souradniceObrazu;
	}

}
