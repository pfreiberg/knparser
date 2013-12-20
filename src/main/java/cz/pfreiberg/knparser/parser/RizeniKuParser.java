package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.RizeniKu;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RizeniKuParser {

	public static RizeniKu parse(String[] tokens) {
		int i = 0;

		RizeniKu rizeniKu = new RizeniKu();
		rizeniKu.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		rizeniKu.setRizeniId(VfkUtil.getLong(tokens, i++));

		return rizeniKu;
	}

}
