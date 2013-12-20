package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.Adresy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class AdresyParser {

	public static Adresy parse(String[] tokens) {
		int i = 0;

		Adresy adresy = new Adresy();
		adresy.setUcastId(VfkUtil.getLong(tokens, i++));
		adresy.setTypAdresy(VfkUtil.getInteger(tokens, i++));
		adresy.setOkres(VfkUtil.getString(tokens, i++));
		adresy.setObec(VfkUtil.getString(tokens, i++));
		adresy.setCastObce(VfkUtil.getString(tokens, i++));
		adresy.setCisloDomovni(VfkUtil.getInteger(tokens, i++));
		adresy.setNazevUlice(VfkUtil.getString(tokens, i++));
		adresy.setCisloOrientacni(VfkUtil.getString(tokens, i++));
		adresy.setPsc(VfkUtil.getInteger(tokens, i++));
		adresy.setStat(VfkUtil.getString(tokens, i++));
		adresy.setTelefon(VfkUtil.getString(tokens, i++));
		adresy.setFax(VfkUtil.getString(tokens, i++));
		adresy.setMestskaCast(VfkUtil.getString(tokens, i++));
		adresy.setCpCe(VfkUtil.getInteger(tokens, i++));
		adresy.setKodAdrm(VfkUtil.getInteger(tokens, i++));

		return adresy;
	}

}
