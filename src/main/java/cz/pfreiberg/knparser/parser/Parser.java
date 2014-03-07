package cz.pfreiberg.knparser.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

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

	private static final Logger log = Logger.getLogger(Parser.class);

	private static boolean firstBatch = true;

	private boolean isParsing = true;
	private Vfk batch;
	private int escapedRows;

	private BufferedReader br;
	private String buffer;
	private long actualRow;

	private String encoding;
	private int zmeny;

	private final long ROWS_PER_BATCH;
	private final char QUOTE_CHARACTER = '"';
	private final char SEPARATOR = ';';

	private final HashMap<String, Command> methodMap;

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException, IOException {
		File file = new File(configuration.getInput());
		ROWS_PER_BATCH = configuration.getNumberOfRows();
		encoding = VfkUtil.getEncoding(file);
		br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), VfkUtil.convertEncoding(encoding)));
		methodMap = getMethodMap();
	}

	public Vfk getBatch() {
		return batch;
	}

	public long getEscapedRows() {
		return escapedRows;
	}

	public boolean isParsing() {
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
				log.error(e.getMessage());
				log.debug("Stack trace:", e);
				escapedRows++;
				continue;
			}

			if (values != null) {
				actualRow++;

				String node = values[0];
				String[] tokens = Arrays.copyOfRange(values, 1, values.length);

				Command command = methodMap.get(node);
				if (command != null)
					command.runCommand(tokens);

				if ((actualRow % ROWS_PER_BATCH) == 0) {
					log.info("Currently parsed row: " + actualRow);
					batch.setZmeny(zmeny);
					return;
				}
			} else
				break;

		} while (true);

		log.info("Last row: " + actualRow);
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

		return tokensOnRow.toArray(new String[tokensOnRow.size()]);
	}

	private boolean isStartOfText(String row, boolean inQuotes, int position) {
		if (hasNextCharacter(row, position)) {
			char nextCharacter = getNextCharacter(row, position);

			if (inQuotes)
				return false;
			else if (nextCharacter == QUOTE_CHARACTER) {
				position++;
				if (hasNextCharacter(row, position)) {
					nextCharacter = getNextCharacter(row, position);
					return !(getNextCharacter(row, position) == SEPARATOR);
				}
				return false;
			}
			return true;
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

	interface Command {
		void runCommand(String[] tokens);
	}

	private HashMap<String, Command> getMethodMap() {
		Map<String, Command> methodMap = new HashMap<>();

		methodMap.put("&HZMENY", new Command() {
			public void runCommand(String[] tokens) {
				zmeny = Integer.parseInt(tokens[0]);
			};
		});

		methodMap.put("&DPAR", new Command() {
			public void runCommand(String[] tokens) {
				batch.getParcely().add(ParcelyParser.parse(tokens));
			};
		});
		methodMap.put("&DBUD", new Command() {
			public void runCommand(String[] tokens) {
				batch.getBudovy().add(BudovyParser.parse(tokens));
			};
		});
		methodMap.put("&DCABU", new Command() {
			public void runCommand(String[] tokens) {
				batch.getCastiBudov().add(CastiBudovParser.parse(tokens));
			};
		});
		methodMap.put("&DZPOCHN", new Command() {
			public void runCommand(String[] tokens) {
				batch.getZpOchranyNem().add(ZpOchranyNemParser.parse(tokens));
			};
		});
		methodMap.put("&DDRUPOZ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getDPozemku().add(DPozemkuParser.parse(tokens));
			};
		});
		methodMap.put("&DZPVYPO", new Command() {
			public void runCommand(String[] tokens) {
				batch.getZpVyuzitiPoz().add(ZpVyuzitiPozParser.parse(tokens));
			};
		});
		methodMap.put("&DZDPAZE", new Command() {
			public void runCommand(String[] tokens) {
				batch.getZdrojeParcelZe().add(
						ZdrojeParcelZeParser.parse(tokens));
			};
		});
		methodMap.put("&DZPURVY", new Command() {
			public void runCommand(String[] tokens) {
				batch.getZpUrceniVymery().add(
						ZpUrceniVymeryParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPBUD", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTBudov().add(TBudovParser.parse(tokens));
			};
		});
		methodMap.put("&DMAPLIS", new Command() {
			public void runCommand(String[] tokens) {
				batch.getMapoveListy().add(MapoveListyParser.parse(tokens));
			};
		});
		methodMap.put("&DKATUZE", new Command() {
			public void runCommand(String[] tokens) {
				batch.getKatastrUzemi().add(KatastrUzemiParser.parse(tokens));
			};
		});
		methodMap.put("&DOBCE", new Command() {
			public void runCommand(String[] tokens) {
				batch.getObce().add(ObceParser.parse(tokens));
			};
		});
		methodMap.put("&DCASOBC", new Command() {
			public void runCommand(String[] tokens) {
				batch.getCastiObci().add(CastiObciParser.parse(tokens));
			};
		});
		methodMap.put("&DOKRESY", new Command() {
			public void runCommand(String[] tokens) {
				batch.getOkresy().add(OkresyParser.parse(tokens));
			};
		});
		methodMap.put("&DKRAJE", new Command() {
			public void runCommand(String[] tokens) {
				batch.getKraje().add(KrajeParser.parse(tokens));
			};
		});
		methodMap.put("&DNKRAJE", new Command() {
			public void runCommand(String[] tokens) {
				batch.getNoveKraje().add(NoveKrajeParser.parse(tokens));
			};
		});
		methodMap.put("&DRZO", new Command() {
			public void runCommand(String[] tokens) {
				batch.getRZpochr().add(RZpochrParser.parse(tokens));
			};
		});
		methodMap.put("&DZPVYBU", new Command() {
			public void runCommand(String[] tokens) {
				batch.getZpVyuzitiBud().add(ZpVyuzitiBudParser.parse(tokens));
			};
		});
		methodMap.put("&DPS", new Command() {
			public void runCommand(String[] tokens) {
				batch.getPravaStavby().add(PravaStavbyParser.parse(tokens));
			};
		});
		methodMap.put("&DRU", new Command() {
			public void runCommand(String[] tokens) {
				batch.getRUcelNem().add(RUcelNemParser.parse(tokens));
			};
		});

		methodMap.put("&DJED", new Command() {
			public void runCommand(String[] tokens) {
				batch.getJednotky().add(JednotkyParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPJED", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTJednotek().add(TJednotekParser.parse(tokens));
			};
		});
		methodMap.put("&DZPVYJE", new Command() {
			public void runCommand(String[] tokens) {
				batch.getZpVyuzitiJed().add(ZpVyuzitiJedParser.parse(tokens));
			};
		});

		methodMap.put("&DBDP", new Command() {
			public void runCommand(String[] tokens) {
				batch.getBonitDilyParc().add(BonitDilyParcParser.parse(tokens));
			};
		});

		methodMap.put("&DOPSUB", new Command() {
			public void runCommand(String[] tokens) {
				batch.getOpravSubjekty().add(OpravSubjektyParser.parse(tokens));
			};
		});
		methodMap.put("&DVLA", new Command() {
			public void runCommand(String[] tokens) {
				batch.getVlastnictvi().add(VlastnictviParser.parse(tokens));
			};
		});
		methodMap.put("&DCHAROS", new Command() {
			public void runCommand(String[] tokens) {
				batch.getCharOs().add(CharOsParser.parse(tokens));
			};
		});
		methodMap.put("&DTEL", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTelesa().add(TelesaParser.parse(tokens));
			};
		});

		methodMap.put("&DJPV", new Command() {
			public void runCommand(String[] tokens) {
				batch.getJinePravVztahy().add(
						JinePravVztahyParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPRAV", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTPravnichVzt().add(TPravnichVztParser.parse(tokens));
			};
		});
		methodMap.put("&DRJPV", new Command() {
			public void runCommand(String[] tokens) {
				batch.getRJpv().add(RJpvParser.parse(tokens));
			};
		});

		methodMap.put("&DRIZENI", new Command() {
			public void runCommand(String[] tokens) {
				batch.getRizeni().add(RizeniParser.parse(tokens));
			};
		});
		methodMap.put("&DRIZKU", new Command() {
			public void runCommand(String[] tokens) {
				batch.getRizeniKu().add(RizeniKuParser.parse(tokens));
			};
		});
		methodMap.put("&DOBJRIZ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getObjektyRizeni().add(ObjektyRizeniParser.parse(tokens));
			};
		});
		methodMap.put("&DPRERIZ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getPredmetyRizeni().add(
						PredmetyRizeniParser.parse(tokens));
			};
		});
		methodMap.put("&DUCAST", new Command() {
			public void runCommand(String[] tokens) {
				batch.getUcastnici().add(UcastniciParser.parse(tokens));
			};
		});
		methodMap.put("&DADRUC", new Command() {
			public void runCommand(String[] tokens) {
				batch.getAdresy().add(AdresyParser.parse(tokens));
			};
		});
		methodMap.put("&DLISTIN", new Command() {
			public void runCommand(String[] tokens) {
				batch.getListiny().add(ListinyParser.parse(tokens));
			};
		});
		methodMap.put("&DDUL", new Command() {
			public void runCommand(String[] tokens) {
				batch.getDalsiUdajeListiny().add(
						DalsiUdajeListinyParser.parse(tokens));
			};
		});
		methodMap.put("&DLDU", new Command() {
			public void runCommand(String[] tokens) {
				batch.getListinyDalsiUdaje().add(
						ListinyDalsiUdajeParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPLIS", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTListin().add(TListinParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPPRE", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTPredmetuR().add(TPredmetuRParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPRIZ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTypyRizeni().add(TypyRizeniParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPUCA", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTypyUcastniku().add(TypyUcastnikuParser.parse(tokens));
			};
		});
		methodMap.put("&DUCTYP", new Command() {
			public void runCommand(String[] tokens) {
				batch.getUcastniciTyp().add(UcastniciTypParser.parse(tokens));
			};
		});
		methodMap.put("&DRL", new Command() {
			public void runCommand(String[] tokens) {
				batch.getRList().add(RListParser.parse(tokens));
			};
		});
		methodMap.put("&DOBESMF", new Command() {
			public void runCommand(String[] tokens) {
				batch.getObeslaniMf().add(ObeslaniMfParser.parse(tokens));
			};
		});

		methodMap.put("&DSOBR", new Command() {
			public void runCommand(String[] tokens) {
				batch.getSouradniceObrazu().add(
						SouradniceObrazuParser.parse(tokens));
			};
		});
		methodMap.put("&DSBP", new Command() {
			public void runCommand(String[] tokens) {
				batch.getSpojeniBPoloh().add(SpojeniBPolohParser.parse(tokens));
			};
		});
		methodMap.put("&DSBM", new Command() {
			public void runCommand(String[] tokens) {
				batch.getSpojeniBMapy().add(SpojeniBMapyParser.parse(tokens));
			};
		});
		methodMap.put("&DKODCHB", new Command() {
			public void runCommand(String[] tokens) {
				batch.getKodyCharQBodu().add(KodyCharQBoduParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPSOS", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTSouradSys().add(TSouradSysParser.parse(tokens));
			};
		});
		methodMap.put("&DHP", new Command() {
			public void runCommand(String[] tokens) {
				batch.getHraniceParcel().add(HraniceParcelParser.parse(tokens));
			};
		});
		methodMap.put("&DOP", new Command() {
			public void runCommand(String[] tokens) {
				batch.getObrazyParcel().add(ObrazyParcelParser.parse(tokens));
			};
		});
		methodMap.put("&DOB", new Command() {
			public void runCommand(String[] tokens) {
				batch.getObrazyBudov().add(ObrazyBudovParser.parse(tokens));
			};
		});
		methodMap.put("&DDPM", new Command() {
			public void runCommand(String[] tokens) {
				batch.getDalsiPrvkyMapy().add(
						DalsiPrvkyMapyParser.parse(tokens));
			};
		});
		methodMap.put("&DOBBP", new Command() {
			public void runCommand(String[] tokens) {
				batch.getObrazyBoduBp().add(ObrazyBoduBpParser.parse(tokens));
			};
		});
		methodMap.put("&DTYPPPD", new Command() {
			public void runCommand(String[] tokens) {
				batch.getTPrvkuPDat().add(TPrvkuPDatParser.parse(tokens));
			};
		});
		methodMap.put("&DZVB", new Command() {
			public void runCommand(String[] tokens) {
				batch.getZobrazeniVb().add(ZobrazeniVbParser.parse(tokens));
			};
		});
		methodMap.put("&DPOM", new Command() {
			public void runCommand(String[] tokens) {
				batch.getPrvkyOMapy().add(PrvkyOMapyParser.parse(tokens));
			};
		});
		methodMap.put("&DSPOM", new Command() {
			public void runCommand(String[] tokens) {
				batch.getSpojeniPoMapy().add(SpojeniPoMapyParser.parse(tokens));
			};
		});
		methodMap.put("&DSPOL", new Command() {
			public void runCommand(String[] tokens) {
				batch.getSouradnicePolohy().add(
						SouradnicePolohyParser.parse(tokens));
			};
		});

		methodMap.put("&DHBPEJ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getHraniceBpej().add(HraniceBpejParser.parse(tokens));
			};
		});
		methodMap.put("&DOBPEJ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getOznaceniBpej().add(OznaceniBpejParser.parse(tokens));
			};
		});

		methodMap.put("&DNZ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getNavrhyZmenKm().add(NavrhyZmenKmParser.parse(tokens));
			};
		});
		methodMap.put("&DZPMZ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getZpmz().add(ZpmzParser.parse(tokens));
			};
		});
		methodMap.put("&DNZZP", new Command() {
			public void runCommand(String[] tokens) {
				batch.getNzZpmz().add(NzZpmzParser.parse(tokens));
			};
		});

		methodMap.put("&DRECI", new Command() {
			public void runCommand(String[] tokens) {
				batch.getRezParcelniCisla().add(
						RezParcelniCislaParser.parse(tokens));
			};
		});
		methodMap.put("&DDOCI", new Command() {
			public void runCommand(String[] tokens) {
				batch.getDotcenaParCisla().add(
						DotcenaParCislaParser.parse(tokens));
			};
		});
		methodMap.put("&DDOHICI", new Command() {
			public void runCommand(String[] tokens) {
				batch.getDotHistParCisla().add(
						DotHistParCislaParser.parse(tokens));
			};
		});
		methodMap.put("&DREZBP", new Command() {
			public void runCommand(String[] tokens) {
				batch.getRezCislaPbpp().add(RezCislaPbppParser.parse(tokens));
			};
		});

		methodMap.put("&DOBDEBO", new Command() {
			public void runCommand(String[] tokens) {
				batch.getObrazyDefBodu().add(ObrazyDefBoduParser.parse(tokens));
			};
		});

		methodMap.put("&DBUDOBJ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getBudObj().add(BudObjParser.parse(tokens));
			};
		});
		methodMap.put("&DADROBJ", new Command() {
			public void runCommand(String[] tokens) {
				batch.getAdresa().add(AdresaParser.parse(tokens));
			};
		});

		return (HashMap<String, Command>) methodMap;

	}
}
