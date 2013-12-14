package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyBoduBp;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyBoduBpParser {

	public static ObrazyBoduBp parse(String[] tokens) {
		int i = 0;

		ObrazyBoduBp obrazyBoduBp = new ObrazyBoduBp();
		obrazyBoduBp.setId(VfkUtil.getLong(tokens[i++]));
		obrazyBoduBp.setStavDat(VfkUtil.getInteger(tokens[i++]));
		obrazyBoduBp.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		obrazyBoduBp.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		obrazyBoduBp.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		obrazyBoduBp.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		obrazyBoduBp.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		obrazyBoduBp.setTypppdKod(VfkUtil.getInteger(tokens[i++]));
		obrazyBoduBp.setSouradniceX(VfkUtil.getDouble(tokens[i++]));
		obrazyBoduBp.setSouradniceY(VfkUtil.getDouble(tokens[i++]));
		obrazyBoduBp.setText(VfkUtil.getString(tokens[i++]));
		obrazyBoduBp.setVelikost(VfkUtil.getDouble(tokens[i++]));
		obrazyBoduBp.setUhel(VfkUtil.getDouble(tokens[i++]));
		obrazyBoduBp.setBpId(VfkUtil.getLong(tokens[i++]));
		obrazyBoduBp.setObbpType(VfkUtil.getString(tokens[i++]));
		obrazyBoduBp.setVztaznyBod(VfkUtil.getInteger(tokens[i++]));

		return obrazyBoduBp;
	}

}
