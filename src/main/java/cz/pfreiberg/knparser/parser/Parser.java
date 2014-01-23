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

	private Vfk batch;
	private int escapedRows;

	private File file;
	private BufferedReader br;
	private String buffer;
	private long actualRow;

	private String encoding;
	private int zmeny;

	private final long ROWS_PER_BATCH;
	private final char QUOTE_CHARACTER = '"';
	private final char SEPARATOR = ';';

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException, IOException {
		file = new File(configuration.getInput());
		ROWS_PER_BATCH = Integer.parseInt(configuration.getNumberOfRows());
		encoding = VfkUtil.getEncoding(file);
		br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), VfkUtil.convertEncoding(encoding)));
	}

	public Vfk getBatch() {
		return batch;
	}

	public long getEscapedRows() {
		return escapedRows;
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

	public void parseFile() throws IOException {
		batch = new Vfk();

		do {

			String[] values;
			try {
				values = processRow();
			} catch (ParserException e) {
				System.out.println(e);
				escapedRows++;
				continue;
			}

			if (values != null) {
				actualRow++;

				String node = values[0];
				String[] tokens = Arrays.copyOfRange(values, 1, values.length);

				if (tryParseNemovitosti(node, tokens)) {
				} else if (tryParseJednotky(node, tokens)) {
				} else if (tryParseBonitniDilParcely(node, tokens)) {
				} else if (tryParseVlastnictvi(node, tokens)) {
				} else if (tryParseJinePravniVztahy(node, tokens)) {
				} else if (tryParseRizeni(node, tokens)) {
				} else if (tryParsePrvkyKatastralniMapy(node, tokens)) {
				} else if (tryParseBonitovanePudneEkologickeJednotky(node,
						tokens)) {
				} else if (tryParseGeometrickyPlan(node, tokens)) {
				} else if (tryParseRezervovanaCisla(node, tokens)) {
				} else if (tryParseDefinicniBody(node, tokens)) {
				} else if (tryParseAdresniMista(node, tokens)) {
				} else if (tryParseHead(node, tokens)) {
				}

				if ((actualRow % ROWS_PER_BATCH) == 0) {
					System.out.println("Currently parsed row: " + actualRow);
					batch.setZmeny(zmeny);
					return;
				}
			} else
				break;

		} while (true);

		System.out.println("Last row: " + actualRow);
		isParsing = false;
		batch.setZmeny(zmeny);
	}

	private String[] processRow() throws IOException, ParserException {
		String[] row = null;
		do {
			String nextRow = getRow();
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

	private String getRow() throws IOException {
		return br.readLine();
	}

	private String[] parseRow(String row) throws ParserException {

		List<String> tokensOnRow = new ArrayList<String>();
		StringBuilder sb = new StringBuilder(312);
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
					inQuotes = !inQuotes;
				}
				// je v uvozovkách a další znak je "
				else if (isNextCharacterEscapable(row, inQuotes, i)) {
					sb.append("\"\"");
					i++;
				// je v uvozovkách a další znak není "
				} else if (isEndOfText(row, inQuotes, i)) {
					inQuotes = !inQuotes;
				// prázdný text
				} else {
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
			batch.getParcely().add(ParcelyParser.parse(tokens));
			break;
		case "&DBUD":
			batch.getBudovy().add(BudovyParser.parse(tokens));
			break;
		case "&DCABU":
			batch.getCastiBudov().add(CastiBudovParser.parse(tokens));
			break;
		case "&DZPOCHN":
			batch.getZpOchranyNem().add(ZpOchranyNemParser.parse(tokens));
			break;
		case "&DDRUPOZ":
			batch.getDPozemku().add(DPozemkuParser.parse(tokens));
			break;
		case "&DZPVYPO":
			batch.getZpVyuzitiPoz().add(ZpVyuzitiPozParser.parse(tokens));
			break;
		case "&DZDPAZE":
			batch.getZdrojeParcelZe().add(ZdrojeParcelZeParser.parse(tokens));
			break;
		case "&DZPURVY":
			batch.getZpUrceniVymery().add(ZpUrceniVymeryParser.parse(tokens));
			break;
		case "&DTYPBUD":
			batch.getTBudov().add(TBudovParser.parse(tokens));
			break;
		case "&DMAPLIS":
			batch.getMapoveListy().add(MapoveListyParser.parse(tokens));
			break;
		case "&DKATUZE":
			batch.getKatastrUzemi().add(KatastrUzemiParser.parse(tokens));
			break;
		case "&DOBCE":
			batch.getObce().add(ObceParser.parse(tokens));
			break;
		case "&DCASOBC":
			batch.getCastiObci().add(CastiObciParser.parse(tokens));
			break;
		case "&DOKRESY":
			batch.getOkresy().add(OkresyParser.parse(tokens));
			break;
		case "&DKRAJE":
			batch.getKraje().add(KrajeParser.parse(tokens));
			break;
		case "&DNKRAJE":
			batch.getNoveKraje().add(NoveKrajeParser.parse(tokens));
			break;
		case "&DRZO":
			batch.getRZpochr().add(RZpochrParser.parse(tokens));
			break;
		case "&DZPVYBU":
			batch.getZpVyuzitiBud().add(ZpVyuzitiBudParser.parse(tokens));
			break;
		case "&DPS":
			batch.getPravaStavby().add(PravaStavbyParser.parse(tokens));
			break;
		case "&DRU":
			batch.getRUcelNem().add(RUcelNemParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseJednotky(String node, String[] tokens) {
		switch (node) {
		case "&DJED":
			batch.getJednotky().add(JednotkyParser.parse(tokens));
			break;
		case "&DTYPJED":
			batch.getTJednotek().add(TJednotekParser.parse(tokens));
			break;
		case "&DZPVYJE":
			batch.getZpVyuzitiJed().add(ZpVyuzitiJedParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseBonitniDilParcely(String node, String[] tokens) {
		switch (node) {
		case "&DBDP":
			batch.getBonitDilyParc().add(BonitDilyParcParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseVlastnictvi(String node, String[] tokens) {
		switch (node) {
		case "&DOPSUB":
			batch.getOpravSubjekty().add(OpravSubjektyParser.parse(tokens));
			break;
		case "&DVLA":
			batch.getVlastnictvi().add(VlastnictviParser.parse(tokens));
			break;
		case "&DCHAROS":
			batch.getCharOs().add(CharOsParser.parse(tokens));
			break;
		case "&DTEL":
			batch.getTelesa().add(TelesaParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseJinePravniVztahy(String node, String[] tokens) {
		switch (node) {
		case "&DJPV":
			batch.getJinePravVztahy().add(JinePravVztahyParser.parse(tokens));
			break;
		case "&DTYPRAV":
			batch.getTPravnichVzt().add(TPravnichVztParser.parse(tokens));
			break;
		case "&DRJPV":
			batch.getRJpv().add(RJpvParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseRizeni(String node, String[] tokens) {
		switch (node) {
		case "&DRIZENI":
			batch.getRizeni().add(RizeniParser.parse(tokens));
			break;
		case "&DRIZKU":
			batch.getRizeniKu().add(RizeniKuParser.parse(tokens));
			break;
		case "&DOBJRIZ":
			batch.getObjektyRizeni().add(ObjektyRizeniParser.parse(tokens));
			break;
		case "&DPRERIZ":
			batch.getPredmetyRizeni().add(PredmetyRizeniParser.parse(tokens));
			break;
		case "&DUCAST":
			batch.getUcastnici().add(UcastniciParser.parse(tokens));
			break;
		case "&DADRUC":
			batch.getAdresy().add(AdresyParser.parse(tokens));
			break;
		case "&DLISTIN":
			batch.getListiny().add(ListinyParser.parse(tokens));
			break;
		case "&DDUL":
			batch.getDalsiUdajeListiny().add(
					DalsiUdajeListinyParser.parse(tokens));
			break;
		case "&DLDU":
			batch.getListinyDalsiUdaje().add(
					ListinyDalsiUdajeParser.parse(tokens));
			break;
		case "&DTYPLIS":
			batch.getTListin().add(TListinParser.parse(tokens));
			break;
		case "&DTYPPRE":
			batch.getTPredmetuR().add(TPredmetuRParser.parse(tokens));
			break;
		case "&DTYPRIZ":
			batch.getTypyRizeni().add(TypyRizeniParser.parse(tokens));
			break;
		case "&DTYPUCA":
			batch.getTypyUcastniku().add(TypyUcastnikuParser.parse(tokens));
			break;
		case "&DUCTYP":
			batch.getUcastniciTyp().add(UcastniciTypParser.parse(tokens));
			break;
		case "&DRL":
			batch.getRList().add(RListParser.parse(tokens));
			break;
		case "&DOBESMF":
			batch.getObeslaniMf().add(ObeslaniMfParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParsePrvkyKatastralniMapy(String node, String[] tokens) {
		switch (node) {
		case "&DSOBR":
			batch.getSouradniceObrazu().add(
					SouradniceObrazuParser.parse(tokens));
			break;
		case "&DSBP":
			batch.getSpojeniBPoloh().add(SpojeniBPolohParser.parse(tokens));
			break;
		case "&DSBM":
			batch.getSpojeniBMapy().add(SpojeniBMapyParser.parse(tokens));
			break;
		case "&DKODCHB":
			batch.getKodyCharQBodu().add(KodyCharQBoduParser.parse(tokens));
			break;
		case "&DTYPSOS":
			batch.getTSouradSys().add(TSouradSysParser.parse(tokens));
			break;
		case "&DHP":
			batch.getHraniceParcel().add(HraniceParcelParser.parse(tokens));
			break;
		case "&DOP":
			batch.getObrazyParcel().add(ObrazyParcelParser.parse(tokens));
			break;
		case "&DOB":
			batch.getObrazyBudov().add(ObrazyBudovParser.parse(tokens));
			break;
		case "&DDPM":
			batch.getDalsiPrvkyMapy().add(DalsiPrvkyMapyParser.parse(tokens));
			break;
		case "&DOBBP":
			batch.getObrazyBoduBp().add(ObrazyBoduBpParser.parse(tokens));
			break;
		case "&DTYPPPD":
			batch.getTPrvkuPDat().add(TPrvkuPDatParser.parse(tokens));
			break;
		case "&DZVB":
			batch.getZobrazeniVb().add(ZobrazeniVbParser.parse(tokens));
			break;
		case "&DPOM":
			batch.getPrvkyOMapy().add(PrvkyOMapyParser.parse(tokens));
			break;
		case "&DSPOM":
			batch.getSpojeniPoMapy().add(SpojeniPoMapyParser.parse(tokens));
			break;
		case "&DSPOL":
			batch.getSouradnicePolohy().add(
					SouradnicePolohyParser.parse(tokens));
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
			batch.getHraniceBpej().add(HraniceBpejParser.parse(tokens));
			break;
		case "&DOBPEJ":
			batch.getOznaceniBpej().add(OznaceniBpejParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseGeometrickyPlan(String node, String[] tokens) {
		switch (node) {
		case "&DNZ":
			batch.getNavrhyZmenKm().add(NavrhyZmenKmParser.parse(tokens));
			break;
		case "&DZPMZ":
			batch.getZpmz().add(ZpmzParser.parse(tokens));
			break;
		case "&DNZZP":
			batch.getNzZpmz().add(NzZpmzParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseRezervovanaCisla(String node, String[] tokens) {
		switch (node) {
		case "&DRECI":
			batch.getRezParcelniCisla().add(
					RezParcelniCislaParser.parse(tokens));
			break;
		case "&DDOCI":
			batch.getDotcenaParCisla().add(DotcenaParCislaParser.parse(tokens));
			break;
		case "&DDOHICI":
			batch.getDotHistParCisla().add(DotHistParCislaParser.parse(tokens));
			break;
		case "&DREZBP":
			batch.getRezCislaPbpp().add(RezCislaPbppParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseDefinicniBody(String node, String[] tokens) {
		switch (node) {
		case "&DOBDEBO":
			batch.getObrazyDefBodu().add(ObrazyDefBoduParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}

	private boolean tryParseAdresniMista(String node, String[] tokens) {
		switch (node) {
		case "&DBUDOBJ":
			batch.getBudObj().add(BudObjParser.parse(tokens));
			break;
		case "&DADROBJ":
			batch.getAdresa().add(AdresaParser.parse(tokens));
			break;
		default:
			return false;
		}
		return true;
	}
}
