package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rezervovanacisla.RezCislaPbpp;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RezCislaPbppParser {

	public static RezCislaPbpp parse(String[] tokens) {
		int i = 0;

		RezCislaPbpp rezCislaPbpp = new RezCislaPbpp();
		rezCislaPbpp.setCisloBodu(VfkUtil.getInteger(tokens, i++));
		rezCislaPbpp.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		rezCislaPbpp.setRezzpmzCisloZpmz(VfkUtil.getInteger(tokens, i++));
		rezCislaPbpp.setRizeniId(VfkUtil.getLong(tokens, i++));

		return rezCislaPbpp;
	}

}
