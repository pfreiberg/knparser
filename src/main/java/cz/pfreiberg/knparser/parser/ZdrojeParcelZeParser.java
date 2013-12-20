package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.ZdrojeParcelZe;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZdrojeParcelZeParser {

	public static ZdrojeParcelZe parse(String tokens[]) {
		int i = 0;

		ZdrojeParcelZe zdrojeParcelZe = new ZdrojeParcelZe();
		zdrojeParcelZe.setKod(VfkUtil.getInteger(tokens, i++));
		zdrojeParcelZe.setNazev(VfkUtil.getString(tokens, i++));
		zdrojeParcelZe.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		zdrojeParcelZe.setPlatnostDo(VfkUtil.getDate(tokens, i++));
		zdrojeParcelZe.setZkratka(VfkUtil.getString(tokens, i++));

		return zdrojeParcelZe;
	}

}
