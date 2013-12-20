package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.geometrickyplan.NavrhyZmenKm;
import cz.pfreiberg.knparser.util.VfkUtil;

public class NavrhyZmenKmParser {

	public static NavrhyZmenKm parse(String[] tokens) {
		int i = 0;

		NavrhyZmenKm navrhyZmenKm = new NavrhyZmenKm();
		navrhyZmenKm.setId(VfkUtil.getLong(tokens, i++));
		navrhyZmenKm.setStavNz(VfkUtil.getInteger(tokens, i++));
		navrhyZmenKm.setNzType(VfkUtil.getString(tokens, i++));
		navrhyZmenKm.setPorizeniDatNz(VfkUtil.getString(tokens, i++));
		navrhyZmenKm.setRizeniId(VfkUtil.getLong(tokens, i++));
		navrhyZmenKm.setCisloPlanku(VfkUtil.getString(tokens, i++));
		navrhyZmenKm.setVyhotovil(VfkUtil.getString(tokens, i++));
		navrhyZmenKm.setOznaceniMapovehoListu(VfkUtil.getString(tokens, i++));

		return navrhyZmenKm;
	}

}
