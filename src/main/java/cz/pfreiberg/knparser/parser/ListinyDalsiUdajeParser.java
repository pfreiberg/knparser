package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.ListinyDalsiUdaje;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyDalsiUdajeParser {

	public static ListinyDalsiUdaje parse(String[] tokens) {
		int i = 0;

		ListinyDalsiUdaje listinyDalsiUdaje = new ListinyDalsiUdaje();
		listinyDalsiUdaje.setListinId(VfkUtil.getLong(tokens[i++]));
		listinyDalsiUdaje.setDulKod(VfkUtil.getString(tokens[i++]));
		listinyDalsiUdaje.setCreateDate(VfkUtil.getDate(tokens[i++]));

		return listinyDalsiUdaje;
	}

}
