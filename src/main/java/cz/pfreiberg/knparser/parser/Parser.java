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

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException, IOException {
		file = new File(configuration.getPathToFile());
		vfk = new Vfk();
		vfk.setCodepage(VfkUtil.getEncoding(file));
		br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), VfkUtil.convertEncoding(vfk
						.getCodepage())));
	}

	public Vfk getVfk() {
		return vfk;
	}

	public void parse() throws IOException, ParserException {
		for (String[] values = processNextRow(); values != null; values = processNextRow()) {
			String node = values[0];
			String[] tokens = Arrays.copyOfRange(values, 1, values.length);
			switch (node) {
			case "&HZMENY":
				vfk.setZmeny(Integer.parseInt(tokens[0]));
				break;
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
			case "&KRAJE":
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
			}

		}

		/*
		 * // TODO testovací výpis for (int i = 0; i < 5; i++) {
		 * System.out.println(vfk.getRZpochr().get(i)); }
		 */
	}

	private String[] processNextRow() throws IOException {
		String[] row = null;
		do {
			String nextRow = getNextRow();
			if (nextRow == null)
				return row;
			String[] processedRow = parseRow(nextRow);
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

	private String[] parseRow(String row) {

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
			sb.append("\n");
			buffer = sb.toString();
			sb = null;
		}

		if (sb != null) {
			tokensOnRow.add(sb.toString());
		}

		sb = null;
		return tokensOnRow.toArray(new String[tokensOnRow.size()]);
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
}
