package cz.pfreiberg.knparser.parser;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TPrvkuPDat;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPrvkuPDatParser {

	public static TPrvkuPDat parse(String tokens[]) {
		int i = 0;

		TPrvkuPDat tPrvkuPDat = new TPrvkuPDat();
		tPrvkuPDat.setKod(VfkUtil.getInteger(tokens, i++));
		tPrvkuPDat.setPolohopis(VfkUtil.getString(tokens, i++));
		tPrvkuPDat.setEditovatelny(VfkUtil.getString(tokens, i++));
		tPrvkuPDat.setPlatnostOd(VfkUtil.getDate(tokens, i++));
		tPrvkuPDat.setVyznam(VfkUtil.getString(tokens, i++));
		tPrvkuPDat.setKrivka(VfkUtil.getString(tokens, i++));
		tPrvkuPDat.setTypPrvku(VfkUtil.getInteger(tokens, i++));
		tPrvkuPDat.setPlatnostDo(VfkUtil.getDate(tokens, i++));

		return tPrvkuPDat;
	}

}
