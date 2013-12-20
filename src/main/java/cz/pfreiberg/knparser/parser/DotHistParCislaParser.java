package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rezervovanacisla.DotHistParCisla;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DotHistParCislaParser {

	public static DotHistParCisla parse(String[] tokens) {
		int i = 0;

		DotHistParCisla dotHistParCisla = new DotHistParCisla();
		dotHistParCisla.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		dotHistParCisla.setParcis(VfkUtil.getInteger(tokens, i++));
		dotHistParCisla.setParpod(VfkUtil.getInteger(tokens, i++));
		dotHistParCisla.setParskup(VfkUtil.getInteger(tokens, i++));

		return dotHistParCisla;
	}

}
