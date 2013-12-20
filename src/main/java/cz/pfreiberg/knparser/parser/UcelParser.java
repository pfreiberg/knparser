package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.Ucel;
import cz.pfreiberg.knparser.util.VfkUtil;

public class UcelParser {

	public static Ucel parse(String[] tokens) {
		int i = 0;

		Ucel ucel = new Ucel();
		ucel.setKod(VfkUtil.getInteger(tokens[i++]));
		ucel.setNazev(VfkUtil.getString(tokens[i++]));
		ucel.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		ucel.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return ucel;
	}

}
