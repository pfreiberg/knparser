package cz.pfreiberg.knparser.domain.parser.nemovitosti;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.parser.VfkUtil;

public class ParserParcely {

	public static Parcely parse(String actualLine) {
		int i = 0;
		String tokens[] = actualLine.split(";");

		Parcely parcela = new Parcely();
		parcela.setId(VfkUtil.getLong(tokens[i++]));
		parcela.setStavDat(VfkUtil.getInteger(tokens[i++]));
		parcela.setDatumVzniku(VfkUtil.getDate(tokens[i++]));
		parcela.setDatumZaniku(VfkUtil.getDate(tokens[i++]));
		parcela.setPriznakKontextu(VfkUtil.getInteger(tokens[i++]));
		parcela.setRizeniIdVzniku(VfkUtil.getLong(tokens[i++]));
		parcela.setRizeniIdZaniku(VfkUtil.getLong(tokens[i++]));
		parcela.setPknId(VfkUtil.getLong(tokens[i++]));
		parcela.setPartType(VfkUtil.getString(tokens[i++]));
		parcela.setKatuzeKod(VfkUtil.getInteger(tokens[i++]));
		parcela.setKatuzeKodPuv(VfkUtil.getInteger(tokens[i++]));
		parcela.setDruhCislovaniPar(VfkUtil.getInteger(tokens[i++]));
		parcela.setKmenoveCisloPar(VfkUtil.getInteger(tokens[i++]));
		parcela.setZdpazeKod(VfkUtil.getInteger(tokens[i++]));
		parcela.setPoddeleniCislaPar(VfkUtil.getInteger(tokens[i++]));
		parcela.setDilParcely(VfkUtil.getInteger(tokens[i++]));
		parcela.setMaplisKod(VfkUtil.getLong(tokens[i++]));
		parcela.setZpurvyKod(VfkUtil.getInteger(tokens[i++]));
		parcela.setDrupozKod(VfkUtil.getInteger(tokens[i++]));
		parcela.setZpvypaKod(VfkUtil.getInteger(tokens[i++]));
		parcela.setTypParcely(VfkUtil.getInteger(tokens[i++]));
		parcela.setVymeraParcely(VfkUtil.getInteger(tokens[i++]));
		parcela.setCenaNemovitosti(VfkUtil.getFloat(tokens[i++]));
		parcela.setDefiniciniBodPar(VfkUtil.getString(tokens[i++]));
		parcela.setTelId(VfkUtil.getLong(tokens[i++]));
		parcela.setParId(VfkUtil.getLong(tokens[i++]));
		parcela.setBudId(VfkUtil.getLong(tokens[i++]));
		parcela.setIdentBud(VfkUtil.getString(tokens[i++]));

		return parcela;
	}

}
