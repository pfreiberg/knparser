package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.CastiObci;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParserCastiObci {

	public static CastiObci parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		CastiObci castiObce = new CastiObci();
		castiObce.setKod(VfkUtil.getInteger(tokens[i++]));
		castiObce.setObceKod(VfkUtil.getInteger(tokens[i++]));
		castiObce.setNazev(VfkUtil.getString(tokens[i++]));
		castiObce.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		castiObce.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return castiObce;
	}
}
