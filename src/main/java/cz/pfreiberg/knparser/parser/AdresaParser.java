package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.adresnimista.Adresa;
import cz.pfreiberg.knparser.util.VfkUtil;

public class AdresaParser {

	public static Adresa parse(String[] tokens) {
		int i = 0;

		Adresa adresa = new Adresa();
		adresa.setKod(VfkUtil.getInteger(tokens, i++));
		adresa.setObjektKod(VfkUtil.getInteger(tokens, i++));
		adresa.setUliceKod(VfkUtil.getInteger(tokens, i++));
		adresa.setCisOrient(VfkUtil.getString(tokens, i++));
		adresa.setPsc(VfkUtil.getInteger(tokens, i++));
		adresa.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		adresa.setPlatnostDo(VfkUtil.getDate(tokens, i++));
		adresa.setUliceNazev(VfkUtil.getString(tokens, i++));

		return adresa;
	}

}
