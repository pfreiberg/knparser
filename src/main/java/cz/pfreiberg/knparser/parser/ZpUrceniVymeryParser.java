package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpUrceniVymery;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpUrceniVymeryParser {

	public static ZpUrceniVymery parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		ZpUrceniVymery zpUrceniVymery = new ZpUrceniVymery();
		zpUrceniVymery.setKod(VfkUtil.getInteger(tokens[i++]));
		zpUrceniVymery.setNazev(VfkUtil.getString(tokens[i++]));
		zpUrceniVymery.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		zpUrceniVymery.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return zpUrceniVymery;
	}

}
