package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.geometrickyplan.NzZpmz;
import cz.pfreiberg.knparser.util.VfkUtil;

public class NzZpmzParser {

	public static NzZpmz parse(String[] tokens) {
		int i = 0;

		NzZpmz nzZpmz = new NzZpmz();
		nzZpmz.setNzId(VfkUtil.getLong(tokens, i++));
		nzZpmz.setZpmzCisloZpmz(VfkUtil.getInteger(tokens, i++));
		nzZpmz.setZpmzKatuzeKod(VfkUtil.getInteger(tokens, i++));

		return nzZpmz;
	}

}
