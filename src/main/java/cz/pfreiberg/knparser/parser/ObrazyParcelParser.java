package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyParcel;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyParcelParser {

	public static ObrazyParcel parse(String[] tokens) {
		int i = 0;

		ObrazyParcel obrazyParcel = new ObrazyParcel();
		obrazyParcel.setId(VfkUtil.getLong(tokens, i++));
		obrazyParcel.setStavDat(VfkUtil.getInteger(tokens, i++));
		obrazyParcel.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		obrazyParcel.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		obrazyParcel.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		obrazyParcel.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		obrazyParcel.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		obrazyParcel.setTypppdKod(VfkUtil.getLong(tokens, i++));
		obrazyParcel.setSouradniceY(VfkUtil.getDouble(tokens, i++));
		obrazyParcel.setSouradniceX(VfkUtil.getDouble(tokens, i++));
		obrazyParcel.setText(VfkUtil.getString(tokens, i++));
		obrazyParcel.setVelikost(VfkUtil.getDouble(tokens, i++));
		obrazyParcel.setUhel(VfkUtil.getDouble(tokens, i++));
		obrazyParcel.setParId(VfkUtil.getLong(tokens, i++));
		obrazyParcel.setOparType(VfkUtil.getString(tokens, i++));
		obrazyParcel.setVztaznyBod(VfkUtil.getInteger(tokens, i++));

		return obrazyParcel;
	}

}
