package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.DalsiPrvkyMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DalsiPrvkyMapyParser {

	public static DalsiPrvkyMapy parse(String[] tokens) {
		int i = 0;

		DalsiPrvkyMapy dalsiPrvkyMapy = new DalsiPrvkyMapy();
		dalsiPrvkyMapy.setId(VfkUtil.getLong(tokens[i++]));
		dalsiPrvkyMapy.setStavDat(VfkUtil.getInteger(tokens[i++]));
		dalsiPrvkyMapy.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		dalsiPrvkyMapy.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		dalsiPrvkyMapy.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		dalsiPrvkyMapy.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		dalsiPrvkyMapy.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		dalsiPrvkyMapy.setTypppdKod(VfkUtil.getInteger(tokens[i++]));
		dalsiPrvkyMapy.setSouradniceX(VfkUtil.getDouble(tokens[i++]));
		dalsiPrvkyMapy.setSouradniceY(VfkUtil.getDouble(tokens[i++]));
		dalsiPrvkyMapy.setText(VfkUtil.getString(tokens[i++]));
		dalsiPrvkyMapy.setVelikost(VfkUtil.getDouble(tokens[i++]));
		dalsiPrvkyMapy.setUhel(VfkUtil.getDouble(tokens[i++]));
		dalsiPrvkyMapy.setBpId(VfkUtil.getLong(tokens[i++]));
		dalsiPrvkyMapy.setDpmType(VfkUtil.getString(tokens[i++]));
		dalsiPrvkyMapy.setVztaznyBod(VfkUtil.getInteger(tokens[i++]));
		dalsiPrvkyMapy.setKatuzeKod(VfkUtil.getInteger(tokens[i++]));

		return dalsiPrvkyMapy;
	}

}
