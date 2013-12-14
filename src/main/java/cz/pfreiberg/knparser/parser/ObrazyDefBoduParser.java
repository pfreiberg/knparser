package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.definicnibody.ObrazyDefBodu;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyDefBoduParser {

	public static ObrazyDefBodu parse(String[] tokens) {
		int i = 0;

		ObrazyDefBodu obrazyDefBodu = new ObrazyDefBodu();
		obrazyDefBodu.setId(VfkUtil.getLong(tokens[i++]));
		obrazyDefBodu.setStavDat(VfkUtil.getInteger(tokens[i++]));
		obrazyDefBodu.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		obrazyDefBodu.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		obrazyDefBodu.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		obrazyDefBodu.setParId(VfkUtil.getLong(tokens[i++]));
		obrazyDefBodu.setBudId(VfkUtil.getLong(tokens[i++]));
		obrazyDefBodu.setTypbudKod(VfkUtil.getInteger(tokens[i++]));
		obrazyDefBodu.setCisloDomovni(VfkUtil.getInteger(tokens[i++]));
		obrazyDefBodu.setSouradniceY(VfkUtil.getDouble(tokens[i++]));
		obrazyDefBodu.setSouradniceX(VfkUtil.getDouble(tokens[i++]));

		return obrazyDefBodu;
	}

}
