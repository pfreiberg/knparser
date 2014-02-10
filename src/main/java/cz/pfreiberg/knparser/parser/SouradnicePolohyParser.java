package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradnicePolohy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SouradnicePolohyParser {

	public static SouradnicePolohy parse(String[] tokens) {
		int i = 0;

		SouradnicePolohy souradnicePolohy = new SouradnicePolohy();
		souradnicePolohy.setId(VfkUtil.getLong(tokens, i++));
		souradnicePolohy.setStavDat(VfkUtil.getInteger(tokens, i++));
		souradnicePolohy.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		souradnicePolohy.setCisloZpmz(VfkUtil.getInteger(tokens, i++));
		souradnicePolohy.setCisloTl(VfkUtil.getInteger(tokens, i++));
		souradnicePolohy.setCisloBodu(VfkUtil.getLong(tokens, i++));
		souradnicePolohy.setUplneCislo(VfkUtil.getLong(tokens, i++));
		souradnicePolohy.setSouradniceY(VfkUtil.getDouble(tokens, i++));
		souradnicePolohy.setSouradniceX(VfkUtil.getDouble(tokens, i++));
		souradnicePolohy.setKodchbKod(VfkUtil.getInteger(tokens, i++));
		souradnicePolohy.setKatuzeKodMer(VfkUtil.getInteger(tokens, i++));
		souradnicePolohy.setCisloZpmzMer(VfkUtil.getInteger(tokens, i++));

		return souradnicePolohy;
	}

}
