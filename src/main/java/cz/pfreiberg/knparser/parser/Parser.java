package cz.pfreiberg.knparser.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.pfreiberg.knparser.Configuration;
import cz.pfreiberg.knparser.domain.Vfk;
import cz.pfreiberg.knparser.util.VfkUtil;

public class Parser {

	private File file;
	private Vfk vfk;
	private BufferedReader br;

	private final char escapeCharacter = '\\';
	private final char quoteCharacter = '"';
	private final char separator = ';';
	private String buffer;
	private long actualRow;
	private int escapedRows;

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException, IOException {
		file = new File(configuration.getPathToFile());
		vfk = new Vfk();
		vfk.setCodepage(VfkUtil.getEncoding(file));
		br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), VfkUtil.convertEncoding(vfk
						.getCodepage())));
		actualRow = 0;
		escapedRows = 0;
	}

	public Vfk getVfk() {
		return vfk;
	}

	public int parse() throws IOException {
		try {
			for (String[] values = processNextRow(); values != null; values = processNextRow()) {
				String node = values[0];
				String[] tokens = Arrays.copyOfRange(values, 1, values.length);

				if (tryParseHead(node, tokens)) {
				} else if (tryParseNemovitosti(node, tokens)) {
				} else if (tryParseJednotky(node, tokens)) {
				} else if (tryParseBonitniDilParcely(node, tokens)) {
				} else if (tryParseVlastnictvi(node, tokens)) {
				} else if (tryParseJinePravniVztahy(node, tokens)) {
				} else if (tryParseRizeni(node, tokens)) {
				}

			}
		} catch (ParserException e) {
			System.out.println(e);
			escapedRows++;
			parse();
		}

		for (int i = 0; i < 3; i++)
			System.out.println(vfk.getObeslaniMf().get(i));

		return escapedRows;
	}

	private String[] processNextRow() throws IOException, ParserException {
		actualRow++;
		if (actualRow % 10000 == 0) {
			System.out.println("Actual row: " + actualRow);
		}

		String[] row = null;
		do {
			String nextRow = getNextRow();
			if (nextRow == null)
				return row;
			String[] processedRow;
			processedRow = parseRow(nextRow);
			if (processedRow.length > 0) {
				if (row == null) {
					row = processedRow;
				} else {
					String[] temp = new String[row.length + processedRow.length];
					System.arraycopy(row, 0, temp, 0, row.length);
					System.arraycopy(processedRow, 0, temp, row.length,
							processedRow.length);
					row = temp;
				}
			}

		} while (isRowProcessing());

		return row;
	}

	private String getNextRow() throws IOException {
		String nextRow = br.readLine();
		return nextRow;
	}

	private String[] parseRow(String row) throws ParserException {

		List<String> tokensOnRow = new ArrayList<String>();
		StringBuilder sb = new StringBuilder(64);
		boolean inQuotes = false;

		if (buffer != null) {
			sb.append(buffer);
			buffer = null;
			inQuotes = true;
		}

		for (int i = 0; i < row.length(); i++) {
			char actualCharacter = getActualCharacter(row, i);

			switch (actualCharacter) {
			case escapeCharacter:
				if (isNextCharacterEscapable(row, inQuotes, i)) {
					sb.append(getNextCharacter(row, i));
					i++;
				}
				break;
			case quoteCharacter:
				sb.append(actualCharacter);
				inQuotes = !inQuotes;
				break;
			case separator:
				if (!inQuotes) {
					tokensOnRow.add(sb.toString());
					sb.setLength(0);
				} else
					sb.append(actualCharacter);
				break;
			default:
				sb.append(actualCharacter);
				break;
			}

		}

		if (inQuotes) {
			buffer = sb.toString();
			if (isLastCharacterValid(buffer)) {
				sb.append("\n");
				sb = null;
			} else {
				String temp = new String(buffer);
				buffer = null;
				throw new ParserException("Row " + actualRow
						+ " is NOT valid! Skipping.\n" + temp + "\n");
			}
		}

		if (sb != null) {
			tokensOnRow.add(sb.toString());
		}

		sb = null;
		return tokensOnRow.toArray(new String[tokensOnRow.size()]);
	}

	private boolean isLastCharacterValid(String buffer) {
		char lastCharacter = buffer.charAt(buffer.length() - 1);
		return (lastCharacter == '¤');
	}

	private boolean isNextCharacterEscapable(String row, boolean inQuotes,
			int position) {
		if (hasNextCharacter(row, position)) {
			char nextCharacter = getNextCharacter(row, position);
			return (inQuotes && (nextCharacter == quoteCharacter) || (nextCharacter == escapeCharacter));
		}
		return false;
	}

	private char getActualCharacter(String row, int position) {
		return row.charAt(position);
	}

	private boolean hasNextCharacter(String row, int position) {
		position++;
		return (row.length() > position);
	}

	private char getNextCharacter(String row, int position) {
		position++;
		return row.charAt(position);
	}

	private boolean isRowProcessing() {
		return buffer != null;
	}

	private boolean tryParseHead(String node, String[] tokens) {
		switch (node) {
		case "&HZMENY":
			vfk.setZmeny(Integer.parseInt(tokens[0]));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseNemovitosti(String node, String[] tokens) {
		switch (node) {
		case "&DPAR":
			vfk.getParcely().add(ParcelyParser.parse(tokens));
			break;
		case "&DBUD":
			vfk.getBudovy().add(BudovyParser.parse(tokens));
			break;
		case "&DCABU":
			vfk.getCastiBudov().add(CastiBudovParser.parse(tokens));
			break;
		case "&DZPOCHN":
			vfk.getZpOchranyNem().add(ZpOchranyNemParser.parse(tokens));
			break;
		case "&DDRUPOZ":
			vfk.getDPozemku().add(DPozemkuParser.parse(tokens));
			break;
		case "&DZPVYPO":
			vfk.getZpVyuzitiPoz().add(ZpVyuzitiPozParser.parse(tokens));
			break;
		case "&DZDPAZE":
			vfk.getZdrojeParcelZe().add(ZdrojeParcelZeParser.parse(tokens));
			break;
		case "&DZPURVY":
			vfk.getZpUrceniVymery().add(ZpUrceniVymeryParser.parse(tokens));
			break;
		case "&DTYPBUD":
			vfk.getTBudov().add(TBudovParser.parse(tokens));
			break;
		case "&DMAPLIS":
			vfk.getMapoveListy().add(MapoveListyParser.parse(tokens));
			break;
		case "&DKATUZE":
			vfk.getKatastrUzemi().add(KatastrUzemiParser.parse(tokens));
			break;
		case "&DOBCE":
			vfk.getObce().add(ObceParser.parse(tokens));
			break;
		case "&DCASOBC":
			vfk.getCastiObci().add(CastiObciParser.parse(tokens));
			break;
		case "&DOKRESY":
			vfk.getOkresy().add(OkresyParser.parse(tokens));
			break;
		case "&DKRAJE":
			vfk.getKraje().add(KrajeParser.parse(tokens));
			break;
		case "&DNKRAJE":
			vfk.getNoveKraje().add(NoveKrajeParser.parse(tokens));
			break;
		case "&DRZO":
			vfk.getRZpochr().add(RZpochrParser.parse(tokens));
			break;
		case "&DZPVYBU":
			vfk.getZpVyuzitiBud().add(ZpVyuzitiBudParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseJednotky(String node, String[] tokens) {
		switch (node) {
		case "&DJED":
			vfk.getJednotky().add(JednotkyParser.parse(tokens));
			break;
		case "&DTYPJED":
			vfk.getTJednotek().add(TJednotekParser.parse(tokens));
			break;
		case "&DZPVYJE":
			vfk.getZpVyuzitiJed().add(ZpVyuzitiJedParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseBonitniDilParcely(String node, String[] tokens) {
		switch (node) {
		case "&DBDP":
			vfk.getBonitDilyParc().add(BonitDilyParcParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseVlastnictvi(String node, String[] tokens) {
		switch (node) {
		case "&DOPSUB":
			vfk.getOpravSubjekty().add(OpravSubjektyParser.parse(tokens));
			break;
		case "&DVLA":
			vfk.getVlastnictvi().add(VlastnictviParser.parse(tokens));
			break;
		case "&DCHAROS":
			vfk.getCharOs().add(CharOsParser.parse(tokens));
			break;
		case "&DTEL":
			vfk.getTelesa().add(TelesaParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseJinePravniVztahy(String node, String[] tokens) {
		switch (node) {
		case "&DJPV":
			vfk.getJinePravVztahy().add(JinePravVztahyParser.parse(tokens));
			break;
		case "&DTYPRAV":
			vfk.getTPravnichVzt().add(TPravnichVztParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseRizeni(String node, String[] tokens) {
		switch (node) {
		case "&DRIZENI":
			vfk.getRizeni().add(RizeniParser.parse(tokens));
			break;
		case "&DRIZKU":
			vfk.getRizeniKu().add(RizeniKuParser.parse(tokens));
			break;
		case "&DOBJRIZ":
			vfk.getObjektyRizeni().add(ObjektyRizeniParser.parse(tokens));
			break;
		case "&DPRERIZ":
			vfk.getPredmetyRizeni().add(PredmetyRizeniParser.parse(tokens));
			break;
		case "&DUCAST":
			vfk.getUcastnici().add(UcastniciParser.parse(tokens));
			break;
		case "&DADRUC":
			vfk.getAdresy().add(AdresyParser.parse(tokens));
			break;
		case "&DLISTIN":
			vfk.getListiny().add(ListinyParser.parse(tokens));
			break;
		case "&DDUL":
			vfk.getDalsiUdajeListiny().add(
					DalsiUdajeListinyParser.parse(tokens));
			break;
		case "&DLDU":
			vfk.getListinyDalsiUdaje().add(
					ListinyDalsiUdajeParser.parse(tokens));
			break;
		case "&DTYPLIS":
			vfk.getTListin().add(TListinParser.parse(tokens));
			break;
		case "&DTYPPRE":
			vfk.getTPredmetuR().add(TPredmetuRParser.parse(tokens));
			break;
		case "&DTYPRIZ":
			vfk.getTypyRizeni().add(TypyRizeniParser.parse(tokens));
			break;
		case "&DTYPUCA":
			vfk.getTypyUcastniku().add(TypyUcastnikuParser.parse(tokens));
			break;
		case "&DUCTYP":
			vfk.getUcastniciTyp().add(UcastniciTypParser.parse(tokens));
			break;
		case "&DRL":
			vfk.getRList().add(RListParser.parse(tokens));
			break;
		case "&DOBESMF":
			vfk.getObeslaniMf().add(ObeslaniMfParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}
}
