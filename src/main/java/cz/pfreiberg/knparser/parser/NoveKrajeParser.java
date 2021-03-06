package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.NoveKraje;
import cz.pfreiberg.knparser.util.VfkUtil;

public class NoveKrajeParser {

	public static NoveKraje parse(String tokens[]) {
		int i = 0;

		NoveKraje novyKraj = new NoveKraje();
		novyKraj.setKod(VfkUtil.getInteger(tokens, i++));
		novyKraj.setNazev(VfkUtil.getString(tokens, i++));
		novyKraj.setNuts3(VfkUtil.getString(tokens, i++));
		novyKraj.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		novyKraj.setPlatnostDo(VfkUtil.getDate(tokens, i++));

		return novyKraj;
	}

}
