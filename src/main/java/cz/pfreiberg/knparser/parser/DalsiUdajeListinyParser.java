package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.DalsiUdajeListiny;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DalsiUdajeListinyParser {

	public static DalsiUdajeListiny parse(String[] tokens) {
		int i = 0;

		DalsiUdajeListiny dalsiUdajeListiny = new DalsiUdajeListiny();
		dalsiUdajeListiny.setKod(VfkUtil.getString(tokens[i++]));
		dalsiUdajeListiny.setNazev(VfkUtil.getString(tokens[i++]));
		dalsiUdajeListiny.setPlatnostOd(VfkUtil.getDate(tokens[i++]));
		dalsiUdajeListiny.setPlatnostDo(VfkUtil.getDate(tokens[i++]));

		return dalsiUdajeListiny;
	}

}
