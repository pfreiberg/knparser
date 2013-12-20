package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.Rizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RizeniParser {

	public static Rizeni parse(String[] tokens) {
		int i = 0;

		Rizeni rizeni = new Rizeni();
		rizeni.setId(VfkUtil.getLong(tokens, i++));
		rizeni.setTyprizKod(VfkUtil.getString(tokens, i++));
		rizeni.setPoradoveCislo(VfkUtil.getInteger(tokens, i++));
		rizeni.setRok(VfkUtil.getDate(tokens, i++));
		rizeni.setStav(VfkUtil.getString(tokens, i++));
		rizeni.setFunkceKod(VfkUtil.getInteger(tokens, i++));
		rizeni.setTypopeKod(VfkUtil.getInteger(tokens, i++));
		rizeni.setFunkceKodVyznamna(VfkUtil.getInteger(tokens, i++));
		rizeni.setTypopeKodVyznamna(VfkUtil.getInteger(tokens, i++));
		rizeni.setUzisysUsername(VfkUtil.getString(tokens, i++));
		rizeni.setUzirolNazev(VfkUtil.getString(tokens, i++));
		rizeni.setOsvobozeno(VfkUtil.getString(tokens, i++));
		rizeni.setHodnotaKolku(VfkUtil.getInteger(tokens, i++));
		rizeni.setDatum(VfkUtil.getDate(tokens, i++));
		rizeni.setDatum2(VfkUtil.getDate(tokens, i++));
		rizeni.setPopis(VfkUtil.getString(tokens, i++));
		rizeni.setDatumUzavreni(VfkUtil.getDate(tokens, i++));
		rizeni.setPraresKod(VfkUtil.getInteger(tokens, i++));

		return rizeni;
	}

}
