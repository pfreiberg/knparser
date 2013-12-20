package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky.OznaceniBpej;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OznaceniBpejParser {

	public static OznaceniBpej parse(String[] tokens) {
		int i = 0;

		OznaceniBpej oznaceniBpej = new OznaceniBpej();
		oznaceniBpej.setId(VfkUtil.getLong(tokens, i++));
		oznaceniBpej.setStavDat(VfkUtil.getInteger(tokens, i++));
		oznaceniBpej.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		oznaceniBpej.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		oznaceniBpej.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		oznaceniBpej.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		oznaceniBpej.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		oznaceniBpej.setTypppdKod(VfkUtil.getInteger(tokens, i++));
		oznaceniBpej.setSouradniceY(VfkUtil.getDouble(tokens, i++));
		oznaceniBpej.setSouradniceX(VfkUtil.getDouble(tokens, i++));
		oznaceniBpej.setText(VfkUtil.getString(tokens, i++));
		oznaceniBpej.setVelikost(VfkUtil.getDouble(tokens, i++));
		oznaceniBpej.setUhel(VfkUtil.getDouble(tokens, i++));
		oznaceniBpej.setBpejKod(VfkUtil.getString(tokens, i++));
		oznaceniBpej.setKatuzeKod(VfkUtil.getInteger(tokens, i++));
		oznaceniBpej.setVztaznyBod(VfkUtil.getInteger(tokens, i++));

		return oznaceniBpej;
	}

}
