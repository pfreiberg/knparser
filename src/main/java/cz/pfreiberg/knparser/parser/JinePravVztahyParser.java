package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.JinePravVztahy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JinePravVztahyParser {

	public static JinePravVztahy parse(String[] tokens) {
		int i = 0;

		JinePravVztahy jinePravVztahy = new JinePravVztahy();
		jinePravVztahy.setId(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setStavDat(VfkUtil.getInteger(tokens, i++));
		jinePravVztahy.setDatumVzniku(VfkUtil.getDate(tokens, i++));
		jinePravVztahy.setDatumZaniku(VfkUtil.getDate(tokens, i++));
		jinePravVztahy.setPriznakKontextu(VfkUtil.getInteger(tokens, i++));
		jinePravVztahy.setRizeniIdVzniku(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setRizeniIdZaniku(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setParIdPro(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setBudIdPro(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setJedIdPro(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setParIdK(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setBudIdK(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setJedIdK(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setTypravKod(VfkUtil.getString(tokens, i++));
		jinePravVztahy.setPopisPravnihoVztahu(VfkUtil.getString(tokens, i++));
		jinePravVztahy.setTelId(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setOpsubIdPro(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setOpsubIdK(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setPodilPohledavka(VfkUtil.getString(tokens, i++));
		jinePravVztahy.setHjpvId(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setDatumVzniku2(VfkUtil.getDate(tokens, i++));
		jinePravVztahy.setRizeniIdVzniku2(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setOpsubId2Pro(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setPopis2(VfkUtil.getString(tokens, i++));
		jinePravVztahy.setPoradiCas(VfkUtil.getDate(tokens, i++));
		jinePravVztahy.setPoradiText(VfkUtil.getString(tokens, i++));
		jinePravVztahy.setPsIdPro(VfkUtil.getLong(tokens, i++));
		jinePravVztahy.setDatumUkonceni(VfkUtil.getDate(tokens, i++));

		return jinePravVztahy;
	}

}
