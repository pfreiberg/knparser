package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyBudovParser {

	public static ObrazyBudov parse(String[] tokens) {
		int i = 0;

		ObrazyBudov obrazyBudov = new ObrazyBudov();
		obrazyBudov.setId(VfkUtil.getLong(tokens, i++));
		obrazyBudov.setStavDat(VfkUtil.getInteger(tokens, i++));
		obrazyBudov.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		obrazyBudov.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		obrazyBudov.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		obrazyBudov.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		obrazyBudov.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		obrazyBudov.setTypppdKod(VfkUtil.getInteger(tokens, i++));
		obrazyBudov.setSouradniceY(VfkUtil.getDouble(tokens, i++));
		obrazyBudov.setSouradniceX(VfkUtil.getDouble(tokens, i++));
		obrazyBudov.setVelikost(VfkUtil.getDouble(tokens, i++));
		obrazyBudov.setUhel(VfkUtil.getDouble(tokens, i++));
		obrazyBudov.setBudId(VfkUtil.getLong(tokens, i++));
		obrazyBudov.setObrbudType(VfkUtil.getString(tokens, i++));

		return obrazyBudov;
	}

}
