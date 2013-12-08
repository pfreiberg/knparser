package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObjektyRizeniParser {

	public static ObjektyRizeni parse(String[] tokens) {
		int i = 0;

		ObjektyRizeni objektyRizeni = new ObjektyRizeni();
		objektyRizeni.setId(VfkUtil.getLong(tokens[i++]));
		objektyRizeni.setRizeniId(VfkUtil.getLong(tokens[i++]));
		objektyRizeni.setParId(VfkUtil.getLong(tokens[i++]));
		objektyRizeni.setBudId(VfkUtil.getLong(tokens[i++]));
		objektyRizeni.setJedId(VfkUtil.getLong(tokens[i++]));
		objektyRizeni.setDatumPlomby(VfkUtil.getDate(tokens[i++]));
		objektyRizeni.setDatumOdstraneniPlomby(VfkUtil.getDate(tokens[i++]));
		objektyRizeni.setDatumHistOd(VfkUtil.getDate(tokens[i++]));
		objektyRizeni.setDatumHistDo(VfkUtil.getDate(tokens[i++]));

		return objektyRizeni;
	}

}
