package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.vlastnictvi.OpravSubjekty;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OpravSubjektyParser {
	
	public static OpravSubjekty parse(String[] tokens) {
		int i = 0;

		OpravSubjekty opravSubjekty = new OpravSubjekty();
		opravSubjekty.setId(VfkUtil.getLong(tokens, i++));
		opravSubjekty.setStavDat(VfkUtil.getInteger(tokens, i++));
		opravSubjekty.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		opravSubjekty.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		opravSubjekty.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		opravSubjekty.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		opravSubjekty.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		opravSubjekty.setIdJe1PartnerBsm(VfkUtil.getLong(tokens, i++));
		opravSubjekty.setIdJe2PartnerBsm(VfkUtil.getLong(tokens, i++));
		opravSubjekty.setIdZdroj(VfkUtil.getLong(tokens, i++));
		opravSubjekty.setOpsubType(VfkUtil.getString(tokens, i++));
		opravSubjekty.setCharosKod(VfkUtil.getInteger(tokens, i++));
		opravSubjekty.setIco(VfkUtil.getInteger(tokens, i++));
		opravSubjekty.setDoplnekIco(VfkUtil.getInteger(tokens, i++));
		opravSubjekty.setNazev(VfkUtil.getString(tokens, i++));
		opravSubjekty.setNazevU(VfkUtil.getString(tokens, i++));
		opravSubjekty.setRodneCislo(VfkUtil.getString(tokens, i++));
		opravSubjekty.setTitulPredJmenem(VfkUtil.getString(tokens, i++));
		opravSubjekty.setJmeno(VfkUtil.getString(tokens, i++));
		opravSubjekty.setJmenoU(VfkUtil.getString(tokens, i++));
		opravSubjekty.setPrijmeni(VfkUtil.getString(tokens, i++));
		opravSubjekty.setPrijmeniU(VfkUtil.getString(tokens, i++));
		opravSubjekty.setTitulZaJmenem(VfkUtil.getString(tokens, i++));
		opravSubjekty.setCisloDomovni(VfkUtil.getInteger(tokens, i++));
		opravSubjekty.setCisloOrientacni(VfkUtil.getString(tokens, i++));
		opravSubjekty.setNazevUlice(VfkUtil.getString(tokens, i++));
		opravSubjekty.setCastObce(VfkUtil.getString(tokens, i++));
		opravSubjekty.setObec(VfkUtil.getString(tokens, i++));
		opravSubjekty.setOkres(VfkUtil.getString(tokens, i++));
		opravSubjekty.setStat(VfkUtil.getString(tokens, i++));
		opravSubjekty.setPsc(VfkUtil.getInteger(tokens, i++));
		opravSubjekty.setMestskaCast(VfkUtil.getString(tokens, i++));
		opravSubjekty.setCpCe(VfkUtil.getString(tokens, i++));
		opravSubjekty.setDatumVzniku2(VfkUtil.getDate(tokens, i++));
		opravSubjekty.setRizeniIdVzniku2(VfkUtil.getLong(tokens, i++));
		opravSubjekty.setKodAdrm(VfkUtil.getInteger(tokens, i++));
		opravSubjekty.setIdNadrizenePo(VfkUtil.getLong(tokens, i++));

		return opravSubjekty;
	}

}
