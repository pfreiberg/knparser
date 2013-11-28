package cz.pfreiberg.knparser.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;

public class ParserParcely {

	private static int i;

	public static Parcely parse(String actualLine) {
		i = 0;
		String tokens[] = actualLine.split(";");

		Parcely parcela = new Parcely();
		parcela.setId(getLong(tokens[i]));
		parcela.setStavDat(getInteger(tokens[i]));
		parcela.setDatumVzniku(getDate(tokens[i]));
		parcela.setDatumZaniku(getDate(tokens[i]));
		parcela.setPriznakKontextu(getInteger(tokens[i]));
		parcela.setRizeniIdVzniku(getLong(tokens[i]));
		parcela.setRizeniIdZaniku(getLong(tokens[i]));
		parcela.setPknId(getLong(tokens[i]));
		parcela.setPartType(getString(tokens[i]));
		parcela.setKatuzeKod(getInteger(tokens[i]));
		parcela.setKatuzeKodPuv(getInteger(tokens[i]));
		parcela.setDruhCislovaniPar(getInteger(tokens[i]));
		parcela.setKmenoveCisloPar(getInteger(tokens[i]));
		parcela.setZdpazeKod(getInteger(tokens[i]));
		parcela.setPoddeleniCislaPar(getInteger(tokens[i]));
		parcela.setDilParcely(getInteger(tokens[i]));
		parcela.setMaplisKod(getLong(tokens[i]));
		parcela.setZpurvyKod(getInteger(tokens[i]));
		parcela.setDrupozKod(getInteger(tokens[i]));
		parcela.setZpvypaKod(getInteger(tokens[i]));
		parcela.setTypParcely(getInteger(tokens[i]));
		parcela.setVymeraParcely(getInteger(tokens[i]));
		parcela.setCenaNemovitosti(getFloat(tokens[i]));
		parcela.setDefiniciniBodPar(getString(tokens[i]));
		parcela.setTelId(getLong(tokens[i]));
		parcela.setParId(getLong(tokens[i]));
		parcela.setBudId(getLong(tokens[i]));
		parcela.setIdentBud(getString(tokens[i]));

		return parcela;
	}

	private static Integer getInteger(String value) {
		i++;
		Integer output = null;
		try {
			output = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
		return output;
	}

	private static Long getLong(String value) {
		i++;
		Long output = null;
		try {
			output = Long.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
		return output;
	}

	private static Float getFloat(String value) {
		i++;
		Float output = null;
		try {
			output = Float.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
		return output;
	}

	private static Date getDate(String value) {
		i++;
		SimpleDateFormat format = new SimpleDateFormat(
				"\"dd.MM.yyyy HH:mm:ss\"");
		Date output = null;
		try {
			output = format.parse(value);
		} catch (ParseException e) {
			return null;
		}

		return output;
	}

	private static String getString(String value) {
		i++;
		if (value.equals("\"\""))
			return null;
		return value;
	}

}
