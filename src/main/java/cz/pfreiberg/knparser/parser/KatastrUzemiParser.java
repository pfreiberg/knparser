package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.KatastrUzemi;
import cz.pfreiberg.knparser.util.VfkUtil;

public class KatastrUzemiParser {
	
	public static KatastrUzemi parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		KatastrUzemi katastrUzemi = new KatastrUzemi();
		katastrUzemi.setKod(VfkUtil.getInteger(tokens[i++]));
		katastrUzemi.setObceKod(VfkUtil.getInteger(tokens[i++]));
		katastrUzemi.setNazev(VfkUtil.getString(tokens[i++]));
		katastrUzemi.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		katastrUzemi.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return katastrUzemi;
	}

}
