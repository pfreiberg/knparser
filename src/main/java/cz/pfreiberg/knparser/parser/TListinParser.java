package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.TListin;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TListinParser {

	public static TListin parse(String[] tokens) {
		int i = 0;

		TListin tListin = new TListin();
		tListin.setKod(VfkUtil.getInteger(tokens, i++));
		tListin.setNazev(VfkUtil.getString(tokens, i++));
		tListin.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		tListin.setPopis(VfkUtil.getString(tokens, i++));
		tListin.setPlatnostDo(VfkUtil.getDate(tokens, i++));
		tListin.setDruhList(VfkUtil.getInteger(tokens, i++));

		return tListin;
	}

}
