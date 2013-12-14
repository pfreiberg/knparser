package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.adresnimista.BudObj;
import cz.pfreiberg.knparser.util.VfkUtil;

public class BudObjParser {

	public static BudObj parse(String[] tokens) {
		int i = 0;

		BudObj budObj = new BudObj();
		budObj.setCisdomHod(VfkUtil.getInteger(tokens[i++]));
		budObj.setIdKn(VfkUtil.getLong(tokens[i++]));
		budObj.setCbKn(VfkUtil.getInteger(tokens[i++]));
		budObj.setIdUa(VfkUtil.getInteger(tokens[i++]));

		return budObj;
	}

}
