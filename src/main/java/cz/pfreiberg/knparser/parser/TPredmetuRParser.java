package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.TPredmetuR;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPredmetuRParser {

	public static TPredmetuR parse(String[] tokens) {
		int i = 0;

		TPredmetuR tPredmetuR = new TPredmetuR();
		tPredmetuR.setKod(VfkUtil.getInteger(tokens[i++]));
		tPredmetuR.setNazev(VfkUtil.getString(tokens[i++]));
		tPredmetuR.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		tPredmetuR.setPopis(VfkUtil.getString(tokens[i++]));
		tPredmetuR.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return tPredmetuR;
	}

}
