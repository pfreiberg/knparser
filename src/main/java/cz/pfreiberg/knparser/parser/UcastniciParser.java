package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.Ucastnici;
import cz.pfreiberg.knparser.util.VfkUtil;

public class UcastniciParser {

	public static Ucastnici parse(String[] tokens) {
		int i = 0;

		Ucastnici ucastnici = new Ucastnici();
		ucastnici.setId(VfkUtil.getLong(tokens[i++]));
		ucastnici.setRizeniId(VfkUtil.getLong(tokens[i++]));
		ucastnici.setDruhUcastnika(VfkUtil.getInteger(tokens[i++]));
		ucastnici.setJmeno(VfkUtil.getString(tokens[i++]));
		ucastnici.setJmenoU(VfkUtil.getString(tokens[i++]));
		ucastnici.setPrijmeni(VfkUtil.getString(tokens[i++]));
		ucastnici.setPrijmeniU(VfkUtil.getString(tokens[i++]));
		ucastnici.setTitulPredJmenem(VfkUtil.getString(tokens[i++]));
		ucastnici.setTitulZaJmenem(VfkUtil.getString(tokens[i++]));
		ucastnici.setRc(VfkUtil.getString(tokens[i++]));
		ucastnici.setRodnePrijmeni(VfkUtil.getString(tokens[i++]));
		ucastnici.setRodinnyStav(VfkUtil.getInteger(tokens[i++]));
		ucastnici.setObchodniJmeno(VfkUtil.getString(tokens[i++]));
		ucastnici.setObchodniJmenoU(VfkUtil.getString(tokens[i++]));
		ucastnici.setDic(VfkUtil.getString(tokens[i++]));
		ucastnici.setIco(VfkUtil.getInteger(tokens[i++]));
		ucastnici.setDoplnekIco(VfkUtil.getInteger(tokens[i++]));
		ucastnici.setOverenPodpis(VfkUtil.getString(tokens[i++]));
		ucastnici.setOverenProtiRs(VfkUtil.getInteger(tokens[i++]));
		ucastnici.setOverenProtiOs(VfkUtil.getInteger(tokens[i++]));

		return ucastnici;
	}

}
