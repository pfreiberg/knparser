package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPravnichVztParser {

	public static TPravnichVzt parse(String[] tokens) {
		int i = 0;

		TPravnichVzt tPravnichVzt = new TPravnichVzt();
		tPravnichVzt.setKod(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setTprKod(VfkUtil.getInteger(tokens, i++));
		tPravnichVzt.setNazev(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setVlastnictvi(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setProOs(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setProNemovitost(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setKNemovitosti(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		tPravnichVzt.setSekce(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setPlatnostDo(VfkUtil.getDate(tokens, i++));
		tPravnichVzt.setVlvztah(VfkUtil.getInteger(tokens, i++));
		tPravnichVzt.setKOs(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setPodilVeritele(VfkUtil.getString(tokens, i++));
		tPravnichVzt.setPoradi(VfkUtil.getString(tokens, i++));

		return tPravnichVzt;
	}

}
