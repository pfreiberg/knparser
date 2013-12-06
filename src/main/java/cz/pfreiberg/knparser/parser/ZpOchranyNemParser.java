package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpOchranyNem;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpOchranyNemParser {

	public static ZpOchranyNem parse(String tokens[]) {
		int i = 0;

		ZpOchranyNem zpOchranyNem = new ZpOchranyNem();
		zpOchranyNem.setKod(VfkUtil.getInteger(tokens[i++]));
		zpOchranyNem.setNazev(VfkUtil.getString(tokens[i++]));
		zpOchranyNem.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		zpOchranyNem.setPlatnostDo(VfkUtil.getDate(tokens[i++]));
		zpOchranyNem.setPozemek(VfkUtil.getString(tokens[i++]));
		zpOchranyNem.setBudova(VfkUtil.getString(tokens[i++]));
		zpOchranyNem.setJednotka(VfkUtil.getString(tokens[i++]));
		zpOchranyNem.setNemochr(VfkUtil.getInteger(tokens[i++]));

		return zpOchranyNem;
	}

}
