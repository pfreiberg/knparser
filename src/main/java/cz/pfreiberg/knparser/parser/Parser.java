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

/**
 * Parser výměnného formátu. Z konfiguračního souboru je zjištěno, kolik řádků
 * se má najednou maximálně zpracovat a ze souboru se získá jeho kódování. Po
 * načtení a rozparsování jednoho řádku je určeno, do jaké doménové třídy má být
 * uložen. Po zpracování daného počtu řádku (nebo dosažení konce souboru) jsou
 * pak již instance doménových tříd vráceny Controlleru.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Parser {

	private static boolean isParsing = true;
	private static boolean firstBatch = true;

	private File file;
	private Vfk vfk;
	private BufferedReader br;

	private String encoding;
	private int zmeny;

	private String buffer;

	private long actualRow;
	private int escapedRows;

	private final long numberOfRows;
	private final char QUOTE_CHARACTER = '"';
	private final char SEPARATOR = ';';

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException, IOException {
		file = new File(configuration.getInput());
		numberOfRows = Integer.parseInt(configuration.getNumberOfRows());
		encoding = VfkUtil.getEncoding(file);
		br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), VfkUtil.convertEncoding(encoding)));
	}

	public Vfk getVfk() {
		return vfk;
	}

	public static boolean isParsing() {
		return isParsing;
	}

	public static boolean isFirstBatch() {
		return firstBatch;
	}

	public static void setFirstBatchToFalse() {
		if (firstBatch) {
			firstBatch = false;
		}
	}

	public int parseFile() throws IOException {
		vfk = new Vfk();

		for (String[] values = processRow(); values != null; values = processRow()) {

			actualRow++;

			if ((actualRow % numberOfRows) == 0) {
				System.out.println("Actual row: " + actualRow);
				return escapedRows;
			}

			if (values.equals(null)) {
				continue;
			}

			String node = values[0];
			String[] tokens = Arrays.copyOfRange(values, 1, values.length);

			if (tryParseHead(node, tokens)) {
			} else if (tryParseNemovitosti(node, tokens)) {
			} else if (tryParseJednotky(node, tokens)) {
			} else if (tryParseBonitniDilParcely(node, tokens)) {
			} else if (tryParseVlastnictvi(node, tokens)) {
			} else if (tryParseJinePravniVztahy(node, tokens)) {
			} else if (tryParseRizeni(node, tokens)) {
			} else if (tryParsePrvkyKatastralniMapy(node, tokens)) {
			} else if (tryParseBonitovanePudneEkologickeJednotky(node, tokens)) {
			} else if (tryParseGeometrickyPlan(node, tokens)) {
			} else if (tryParseRezervovanaCisla(node, tokens)) {
			} else if (tryParseDefinicniBody(node, tokens)) {
			} else if (tryParseAdresniMista(node, tokens)) {
			}

		}

		System.out.println("Last row: " + actualRow);
		isParsing = false;
		return escapedRows;
	}

	private String[] processRow() throws IOException {
		String[] row = null;

		do {
			String nextRow = getRow();
			if (nextRow == null)
				return row;
			String[] processedRow;
			try {
				processedRow = parseRow(nextRow);
			} catch (ParserException e) {
				System.out.println(e);
				escapedRows++;
				return null;
			}

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

		vfk.setZmeny(zmeny);
		return row;
	}

	private String getRow() throws IOException {
		return br.readLine();
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
			case QUOTE_CHARACTER:
				// není v uvozovkách a další znak není "
				if (isStartOfText(row, inQuotes, i)) {
					sb.append("\"");
					inQuotes = !inQuotes;
				}
				// je v uvozovkách a další znak je "
				else if (isNextCharacterEscapable(row, inQuotes, i)) {
					sb.append("\"\"");
					i++;
					// je v uvozovkách a další znak není "
				} else if (isEndOfText(row, inQuotes, i)) {
					sb.append("\"");
					inQuotes = !inQuotes;
					// prázdný text
				} else {
					sb.append("\"\"");
					i++;
				}
				break;
			case SEPARATOR:
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

	private boolean isStartOfText(String row, boolean inQuotes, int position) {
		if (hasNextCharacter(row, position)) {
			char nextCharacter = getNextCharacter(row, position);
			return (!inQuotes && (nextCharacter != QUOTE_CHARACTER));
		}
		return false;
	}

	private boolean isNextCharacterEscapable(String row, boolean inQuotes,
			int position) {
		if (hasNextCharacter(row, position)) {
			char nextCharacter = getNextCharacter(row, position);
			return (inQuotes && (nextCharacter == QUOTE_CHARACTER));
		}
		return false;
	}

	private boolean isEndOfText(String row, boolean inQuotes, int position) {
		return inQuotes;
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

	private boolean isLastCharacterValid(String buffer) {
		char lastCharacter = buffer.charAt(buffer.length() - 1);
		return (lastCharacter == '\u00A4');
	}

	private boolean isRowProcessing() {
		return (buffer != null);
	}

	private boolean tryParseHead(String node, String[] tokens) {
		switch (node) {
		case "&HZMENY":
			zmeny = Integer.parseInt(tokens[0]);
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
		case "&DPS":
			vfk.getPravaStavby().add(PravaStavbyParser.parse(tokens));
			break;
		case "&DRU":
			vfk.getRUcelNem().add(RUcelNemParser.parse(tokens));
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
		case "&DRJPV":
			vfk.getRJpv().add(RJpvParser.parse(tokens));
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

	private boolean tryParsePrvkyKatastralniMapy(String node, String[] tokens) {
		switch (node) {
		case "&DSOBR":
			vfk.getSouradniceObrazu().add(SouradniceObrazuParser.parse(tokens));
			break;
		case "&DSBP":
			vfk.getSpojeniBPoloh().add(SpojeniBPolohParser.parse(tokens));
			break;
		case "&DSBM":
			vfk.getSpojeniBMapy().add(SpojeniBMapyParser.parse(tokens));
			break;
		case "&DKODCHB":
			vfk.getKodyCharQBodu().add(KodyCharQBoduParser.parse(tokens));
			break;
		case "&DTYPSOS":
			vfk.getTSouradSys().add(TSouradSysParser.parse(tokens));
			break;
		case "&DHP":
			vfk.getHraniceParcel().add(HraniceParcelParser.parse(tokens));
			break;
		case "&DOP":
			vfk.getObrazyParcel().add(ObrazyParcelParser.parse(tokens));
			break;
		case "&DOB":
			vfk.getObrazyBudov().add(ObrazyBudovParser.parse(tokens));
			break;
		case "&DDPM":
			vfk.getDalsiPrvkyMapy().add(DalsiPrvkyMapyParser.parse(tokens));
			break;
		case "&DOBBP":
			vfk.getObrazyBoduBp().add(ObrazyBoduBpParser.parse(tokens));
			break;
		case "&DTYPPPD":
			vfk.getTPrvkuPDat().add(TPrvkuPDatParser.parse(tokens));
			break;
		case "&DZVB":
			vfk.getZobrazeniVb().add(ZobrazeniVbParser.parse(tokens));
			break;
		case "&DPOM":
			vfk.getPrvkyOMapy().add(PrvkyOMapyParser.parse(tokens));
			break;
		case "&DSPOM":
			vfk.getSpojeniPoMapy().add(SpojeniPoMapyParser.parse(tokens));
			break;
		case "&DSPOL":
			vfk.getSouradnicePolohy().add(SouradnicePolohyParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseBonitovanePudneEkologickeJednotky(String node,
			String[] tokens) {
		switch (node) {
		case "&DHBPEJ":
			vfk.getHraniceBpej().add(HraniceBpejParser.parse(tokens));
			break;
		case "&DOBPEJ":
			vfk.getOznaceniBpej().add(OznaceniBpejParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseGeometrickyPlan(String node, String[] tokens) {
		switch (node) {
		case "&DNZ":
			vfk.getNavrhyZmenKm().add(NavrhyZmenKmParser.parse(tokens));
			break;
		case "&DZPMZ":
			vfk.getZpmz().add(ZpmzParser.parse(tokens));
			break;
		case "&DNZZP":
			vfk.getNzZpmz().add(NzZpmzParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseRezervovanaCisla(String node, String[] tokens) {
		switch (node) {
		case "&DRECI":
			vfk.getRezParcelniCisla().add(RezParcelniCislaParser.parse(tokens));
			break;
		case "&DDOCI":
			vfk.getDotcenaParCisla().add(DotcenaParCislaParser.parse(tokens));
			break;
		case "&DDOHICI":
			vfk.getDotHistParCisla().add(DotHistParCislaParser.parse(tokens));
			break;
		case "&DREZBP":
			vfk.getRezCislaPbpp().add(RezCislaPbppParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseDefinicniBody(String node, String[] tokens) {
		switch (node) {
		case "&DOBDEBO":
			vfk.getObrazyDefBodu().add(ObrazyDefBoduParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseAdresniMista(String node, String[] tokens) {
		switch (node) {
		case "&DBUDOBJ":
			vfk.getBudObj().add(BudObjParser.parse(tokens));
			break;
		case "&DADROBJ":
			vfk.getAdresa().add(AdresaParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}
}
