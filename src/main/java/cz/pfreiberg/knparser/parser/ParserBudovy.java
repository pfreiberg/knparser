package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.Budovy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParserBudovy {

	public static Budovy parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		Budovy budova = new Budovy();
		budova.setId(VfkUtil.getLong(tokens[i++]));
		budova.setStavDat(VfkUtil.getInteger(tokens[i++]));
		budova.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		budova.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		budova.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		budova.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		budova.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		budova.setTypBudKod(VfkUtil.getInteger(tokens[i++]));
		budova.setCaoObceKod(VfkUtil.getInteger(tokens[i++]));
		budova.setCisloDomovni(VfkUtil.getInteger(tokens[i++]));
		budova.setCenaNemovitosti(VfkUtil.getInteger(tokens[i++]));
		budova.setZpvybuKod(VfkUtil.getInteger(tokens[i++]));
		budova.setTelId(VfkUtil.getLong(tokens[i++]));
		
		return budova;
	}

}
