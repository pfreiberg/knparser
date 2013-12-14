package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.KodyCharQBodu;
import cz.pfreiberg.knparser.util.VfkUtil;

public class KodyCharQBoduParser {

	public static KodyCharQBodu parse(String[] tokens) {
		int i = 0;

		KodyCharQBodu kodyCharQBodu = new KodyCharQBodu();
		kodyCharQBodu.setKod(VfkUtil.getInteger(tokens[i++]));
		kodyCharQBodu.setNazev(VfkUtil.getString(tokens[i++]));
		kodyCharQBodu.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		kodyCharQBodu.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return kodyCharQBodu;
	}

}
