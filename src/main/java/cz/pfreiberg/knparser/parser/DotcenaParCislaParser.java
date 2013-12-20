package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rezervovanacisla.DotcenaParCisla;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DotcenaParCislaParser {

	public static DotcenaParCisla parse(String[] tokens) {
		int i = 0;

		DotcenaParCisla dotcenaParCisla = new DotcenaParCisla();
		dotcenaParCisla.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		dotcenaParCisla.setKmenoveCisloPar(VfkUtil.getInteger(tokens, i++));
		dotcenaParCisla.setPoddeleniCislaPar(VfkUtil.getInteger(tokens, i++));
		dotcenaParCisla.setDruhCislovaniPar(VfkUtil.getInteger(tokens, i++));

		return dotcenaParCisla;
	}

}
