package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rezervovanacisla.RezParcelniCisla;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RezParcelniCislaParser {

	public static RezParcelniCisla parse(String[] tokens) {
		int i = 0;

		RezParcelniCisla rezParcelniCisla = new RezParcelniCisla();
		rezParcelniCisla.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		rezParcelniCisla.setKmenoveCisloPar(VfkUtil.getInteger(tokens, i++));
		rezParcelniCisla.setDruhCislovaniPar(VfkUtil.getInteger(tokens, i++));
		rezParcelniCisla.setPoddeleniCislaPar(VfkUtil.getInteger(tokens, i++));
		rezParcelniCisla.setRezzpmzCisloZpmz(VfkUtil.getInteger(tokens, i++));

		return rezParcelniCisla;
	}

}
