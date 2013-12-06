package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TBudovParser {

	public static TBudov parse(String tokens[]) {
		int i = 0;

		TBudov tBudov = new TBudov();
		tBudov.setKod(VfkUtil.getInteger(tokens[i++]));
		tBudov.setNazev(VfkUtil.getString(tokens[i++]));
		tBudov.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		tBudov.setPlatnostDo(VfkUtil.getDate(tokens[i++]));
		tBudov.setZadaniCd(VfkUtil.getString(tokens[i++]));
		tBudov.setZkratka(VfkUtil.getString(tokens[i++]));

		return tBudov;
	}

}
