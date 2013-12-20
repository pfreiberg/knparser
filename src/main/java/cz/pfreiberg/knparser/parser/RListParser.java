package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.RList;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RListParser {

	public static RList parse(String[] tokens) {
		int i = 0;

		RList rList = new RList();
		rList.setId(VfkUtil.getLong(tokens[i++]));
		rList.setStavDat(VfkUtil.getInteger(tokens[i++]));
		rList.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		rList.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		rList.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		rList.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		rList.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		rList.setListinId(VfkUtil.getLong(tokens[i++]));
		rList.setParId(VfkUtil.getLong(tokens[i++]));
		rList.setBudId(VfkUtil.getLong(tokens[i++]));
		rList.setJedId(VfkUtil.getLong(tokens[i++]));
		rList.setOpsubId(VfkUtil.getLong(tokens[i++]));
		rList.setJpvId(VfkUtil.getLong(tokens[i++]));
		rList.setDatumVzniku2(VfkUtil.getDate(tokens[i++]));
		rList.setRizeniIdVzniku2(VfkUtil.getLong(tokens[i++]));
		rList.setPsId(VfkUtil.getLong(tokens[i++]));

		return rList;
	}

}
