package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.RZpochr;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParserRZpochr {

	public static RZpochr parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		RZpochr rZpochr = new RZpochr();
		rZpochr.setId(VfkUtil.getLong(tokens[i++]));
		rZpochr.setStavDat(VfkUtil.getInteger(tokens[i++]));
		rZpochr.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		rZpochr.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		rZpochr.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		rZpochr.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		rZpochr.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		rZpochr.setZpochrKod(VfkUtil.getInteger(tokens[i++]));
		rZpochr.setParId(VfkUtil.getLong(tokens[i++]));
		rZpochr.setBudId(VfkUtil.getLong(tokens[i++]));
		rZpochr.setJedId(VfkUtil.getLong(tokens[i++]));

		return rZpochr;
	}

}
