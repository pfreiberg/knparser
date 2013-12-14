package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ZobrazeniVb;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZobrazeniVbParser {

	public static ZobrazeniVb parse(String[] tokens) {
		int i = 0;

		ZobrazeniVb zobrazeniVb = new ZobrazeniVb();
		zobrazeniVb.setId(VfkUtil.getLong(tokens[i++]));
		zobrazeniVb.setStavDat(VfkUtil.getInteger(tokens[i++]));
		zobrazeniVb.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		zobrazeniVb.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		zobrazeniVb.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		zobrazeniVb.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		zobrazeniVb.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		zobrazeniVb.setTypppdKod(VfkUtil.getInteger(tokens[i++]));
		zobrazeniVb.setKatuzeKod(VfkUtil.getInteger(tokens[i++]));
		zobrazeniVb.setHjpvId(VfkUtil.getLong(tokens[i++]));

		return zobrazeniVb;
	}

}
