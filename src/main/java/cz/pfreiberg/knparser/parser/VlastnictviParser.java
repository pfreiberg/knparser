package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.vlastnictvi.Vlastnictvi;
import cz.pfreiberg.knparser.util.VfkUtil;

public class VlastnictviParser {

	public static Vlastnictvi parse(String[] tokens) {
		int i = 0;

		Vlastnictvi vlastnictvi = new Vlastnictvi();
		vlastnictvi.setId(VfkUtil.getLong(tokens[i++]));
		vlastnictvi.setStavDat(VfkUtil.getInteger(tokens[i++]));
		vlastnictvi.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		vlastnictvi.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		vlastnictvi.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		vlastnictvi.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		vlastnictvi.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		vlastnictvi.setOpsubId(VfkUtil.getLong(tokens[i++]));
		vlastnictvi.setTypravKod(VfkUtil.getString(tokens[i++]));
		vlastnictvi.setTelId(VfkUtil.getLong(tokens[i++]));
		vlastnictvi.setPodilCitatel(VfkUtil.getLong(tokens[i++]));
		vlastnictvi.setPodilJmenovatel(VfkUtil.getLong(tokens[i++]));
		vlastnictvi.setDatumVzniku2(VfkUtil.getDate(tokens[i++]));
		vlastnictvi.setRizeniIdVzniku2(VfkUtil.getLong(tokens[i++]));

		return vlastnictvi;
	}

}
