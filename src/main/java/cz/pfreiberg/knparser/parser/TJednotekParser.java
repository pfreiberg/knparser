package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.jednotky.TJednotek;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TJednotekParser {

	public static TJednotek parse(String[] tokens) {
		int i = 0;

		TJednotek tJednotek = new TJednotek();
		tJednotek.setKod(VfkUtil.getInteger(tokens, i++));
		tJednotek.setNazev(VfkUtil.getString(tokens, i++));
		tJednotek.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		tJednotek.setPlatnostDo(VfkUtil.getDate(tokens, i++));
		tJednotek.setZkratka(VfkUtil.getString(tokens, i++));

		return tJednotek;
	}

}
