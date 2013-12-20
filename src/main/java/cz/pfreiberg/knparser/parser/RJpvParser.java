package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.RJpv;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RJpvParser {

	public static RJpv parse(String tokens[]) {
		int i = 0;

		RJpv rJpv = new RJpv();
		rJpv.setId(VfkUtil.getLong(tokens[i++]));
		rJpv.setVerze(VfkUtil.getLong(tokens[i++]));
		rJpv.setStavDat(VfkUtil.getInteger(tokens[i++]));
		rJpv.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		rJpv.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		rJpv.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		rJpv.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		rJpv.setHjpvId1(VfkUtil.getLong(tokens[i++]));
		rJpv.setHjpvId2(VfkUtil.getLong(tokens[i++]));
		rJpv.setTypvazbyJpv(VfkUtil.getInteger(tokens[i++]));

		return rJpv;
	}
}
