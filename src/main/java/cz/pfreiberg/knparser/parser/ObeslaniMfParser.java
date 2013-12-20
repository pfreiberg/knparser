package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.ObeslaniMf;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObeslaniMfParser {

	public static ObeslaniMf parse(String[] tokens) {
		int i = 0;

		ObeslaniMf obeslaniMf = new ObeslaniMf();
		obeslaniMf.setObeslaniId(VfkUtil.getLong(tokens, i++));
		obeslaniMf.setZpusobObeslani(VfkUtil.getInteger(tokens, i++));
		obeslaniMf.setTypopeKod(VfkUtil.getInteger(tokens, i++));
		obeslaniMf.setUcastId(VfkUtil.getLong(tokens, i++));
		obeslaniMf.setStavObeslani(VfkUtil.getInteger(tokens, i++));
		obeslaniMf.setDatumPrijetiDorucenky(VfkUtil.getDate(tokens, i++));
		obeslaniMf.setOpsubId(VfkUtil.getLong(tokens, i++));

		return obeslaniMf;
	}

}
