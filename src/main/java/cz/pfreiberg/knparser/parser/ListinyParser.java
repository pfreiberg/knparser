package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.rizeni.Listiny;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyParser {

	public static Listiny parse(String[] tokens) {
		int i = 0;

		Listiny listiny = new Listiny();
		listiny.setId(VfkUtil.getLong(tokens, i++));
		listiny.setTyplistKod(VfkUtil.getInteger(tokens, i++));
		listiny.setPopis(VfkUtil.getString(tokens, i++));
		listiny.setObsah(VfkUtil.getString(tokens, i++));
		listiny.setStran(VfkUtil.getInteger(tokens, i++));
		listiny.setDatumVyhotoveni(VfkUtil.getDate(tokens, i++));
		listiny.setZhotovitel(VfkUtil.getString(tokens, i++));
		listiny.setPoradoveCisloZhotovitele(VfkUtil.getString(tokens, i++));
		listiny.setRokZhotovitele(VfkUtil.getDate(tokens, i++));
		listiny.setDoplneniZhotovitele(VfkUtil.getString(tokens, i++));
		listiny.setZkratka(VfkUtil.getString(tokens, i++));
		listiny.setRizeniId(VfkUtil.getLong(tokens, i++));
		listiny.setZmenaPravVztahu(VfkUtil.getString(tokens, i++));
		listiny.setDatumPravMoci(VfkUtil.getDate(tokens, i++));
		listiny.setDatumVykonatelnosti(VfkUtil.getDate(tokens, i++));
		listiny.setDatumHistOd(VfkUtil.getDate(tokens, i++));
		listiny.setDatumHistDo(VfkUtil.getDate(tokens, i++));

		return listiny;
	}

}
