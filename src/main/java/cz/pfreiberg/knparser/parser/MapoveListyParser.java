package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.MapoveListy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class MapoveListyParser {

	public static MapoveListy parse(String tokens[]) {
		int i = 0;

		MapoveListy mapoveListy = new MapoveListy();
		mapoveListy.setId(VfkUtil.getLong(tokens[i++]));
		mapoveListy.setOznaceniMapovehoListu(VfkUtil.getString(tokens[i++]));
		mapoveListy.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		mapoveListy.setPlatnostDo(VfkUtil.getDate(tokens[i++]));
		mapoveListy.setMapa(VfkUtil.getString(tokens[i++]));

		return mapoveListy;
	}

}
