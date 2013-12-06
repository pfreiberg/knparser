package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.CastiBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class CastiBudovParser {

	public static CastiBudov parse(String tokens[]) {
		int i = 0;

		CastiBudov castiBudovy = new CastiBudov();
		castiBudovy.setStavDat(VfkUtil.getInteger(tokens[i++]));
		castiBudovy.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		castiBudovy.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		castiBudovy.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		castiBudovy.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		castiBudovy.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		castiBudovy.setBudId(VfkUtil.getLong(tokens[i++]));
		castiBudovy.setTypbudKod(VfkUtil.getInteger(tokens[i++]));
		castiBudovy.setCisloDomovni(VfkUtil.getInteger(tokens[i++]));
		castiBudovy.setCenaNemovitosti(VfkUtil.getDouble(tokens[i++]));

		return castiBudovy;
	}

}
