package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.HraniceParcel;
import cz.pfreiberg.knparser.util.VfkUtil;

public class HraniceParcelParser {

	public static HraniceParcel parse(String[] tokens) {
		int i = 0;

		HraniceParcel hraniceParcel = new HraniceParcel();
		hraniceParcel.setId(VfkUtil.getLong(tokens[i++]));
		hraniceParcel.setStavDat(VfkUtil.getInteger(tokens[i++]));
		hraniceParcel.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		hraniceParcel.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		hraniceParcel.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		hraniceParcel.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		hraniceParcel.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		hraniceParcel.setTypppdKod(VfkUtil.getInteger(tokens[i++]));
		hraniceParcel.setParId1(VfkUtil.getLong(tokens[i++]));
		hraniceParcel.setParId2(VfkUtil.getLong(tokens[i++]));

		return hraniceParcel;
	}

}
