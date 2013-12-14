package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TSouradSys;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TSouradSysParser {

	public static TSouradSys parse(String[] tokens) {
		int i = 0;

		TSouradSys tSouradSys = new TSouradSys();
		tSouradSys.setKod(VfkUtil.getInteger(tokens[i++]));
		tSouradSys.setNazev(VfkUtil.getString(tokens[i++]));
		tSouradSys.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		tSouradSys.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return tSouradSys;
	}

}
