package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky.HraniceBpej;
import cz.pfreiberg.knparser.util.VfkUtil;

public class HraniceBpejParser {

	public static HraniceBpej parse(String[] tokens) {
		int i = 0;

		HraniceBpej hraniceBpej = new HraniceBpej();
		hraniceBpej.setId(VfkUtil.getLong(tokens, i++));
		hraniceBpej.setStavDat(VfkUtil.getInteger(tokens, i++));
		hraniceBpej.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		hraniceBpej.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		hraniceBpej.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		hraniceBpej.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		hraniceBpej.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		hraniceBpej.setTypppdKod(VfkUtil.getInteger(tokens, i++));
		hraniceBpej.setBpejKodHranice1(VfkUtil.getString(tokens, i++));
		hraniceBpej.setBpejKodHranice2(VfkUtil.getString(tokens, i++));
		hraniceBpej.setKatuzeKod(VfkUtil.getInteger(tokens, i++));

		return hraniceBpej;
	}

}
