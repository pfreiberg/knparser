package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.MapoveListy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParserMapoveListy {

	public static MapoveListy parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		MapoveListy mapoveListy = new MapoveListy();
		mapoveListy.setId(VfkUtil.getLong(tokens[i++]));
		mapoveListy.setOznaceniMapovehoListu(VfkUtil.getString(tokens[i++]));
		mapoveListy.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		mapoveListy.setPlatnostDo(VfkUtil.getDate(tokens[i++]));
		mapoveListy.setMapa(VfkUtil.getString(tokens[i++]));

		return mapoveListy;
	}

}
