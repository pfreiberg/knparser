package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.DPozemku;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DPozemkuParser {

	public static DPozemku parse(String tokens[]) {
		int i = 0;

		DPozemku dPozemku = new DPozemku();
		dPozemku.setKod(VfkUtil.getInteger(tokens, i++));
		dPozemku.setNazev(VfkUtil.getString(tokens, i++));
		dPozemku.setZemedelskaKultura(VfkUtil.getString(tokens, i++));
		dPozemku.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		dPozemku.setTypppdKod(VfkUtil.getInteger(tokens, i++));
		dPozemku.setPlatnostDo(VfkUtil.getDate(tokens, i++));
		dPozemku.setZkratka(VfkUtil.getString(tokens, i++));
		dPozemku.setStavebniParcela(VfkUtil.getString(tokens, i++));

		return dPozemku;
	}

}
