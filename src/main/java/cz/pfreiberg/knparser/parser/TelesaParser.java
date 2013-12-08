package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.vlastnictvi.Telesa;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TelesaParser {

	public static Telesa parse(String[] tokens) {
		int i = 0;

		Telesa telesa = new Telesa();
		telesa.setId(VfkUtil.getLong(tokens[i++]));
		telesa.setStavDat(VfkUtil.getInteger(tokens[i++]));
		telesa.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		telesa.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		telesa.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		telesa.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		telesa.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		telesa.setKatuzeKod(VfkUtil.getInteger(tokens[i++]));
		telesa.setCisloTel(VfkUtil.getInteger(tokens[i++]));

		return telesa;
	}

}
