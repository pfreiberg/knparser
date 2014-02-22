package cz.pfreiberg.knparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cz.pfreiberg.knparser.domain.Vfk;
import cz.pfreiberg.knparser.exporterfactory.ExporterFactory;
import cz.pfreiberg.knparser.exporterfactory.OracleDatabaseExporterFactory;
import cz.pfreiberg.knparser.exporterfactory.OracleLoaderExporterFactory;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;

/**
 * Řídící část programu. Využívá parser k naplnění doménových tříd ze souboru.
 * Následně iniciuje jejich zpracování a uložení do load filu, nebo databáze.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Controller {

	private final Configuration configuration;
	private final boolean toDatabase;
	private Parser parser;
	private long seconds;

	public Controller(Configuration configuration) {
		this.configuration = configuration;
		this.toDatabase = (configuration.getConnection() != null);
		try {
			parser = new Parser(configuration);
		} catch (ParserException | IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		ScheduledExecutorService executor = getTimer();

		try {
			Vfk vfk;
			do {
				vfk = parseBatch();
				System.out
						.println("Batch is parsed. Starting the storage sequence.\n");
				storeParsedData(vfk);
				Parser.setFirstBatchToFalse();
			} while (Parser.isParsing());

			System.out.println(parser.getEscapedRows() + " row/s was escaped.");
			System.out.println("Parsing finished.");

		} catch (FileNotFoundException e) {
			System.out.println("Input file was NOT found.");
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally
		{
			executor.shutdown();
		}
		
	}

	private ScheduledExecutorService getTimer() {

		Runnable runnableTime = new Runnable() {
			public void run() {
				System.out.println("\n" + seconds + " seconds..." + "\n");
				seconds++;
			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(runnableTime, 0, 3, TimeUnit.SECONDS);
		return executor;
	}

	private Vfk parseBatch() throws FileNotFoundException, ParserException,
			IOException {
		parser.parseFile();
		return parser.getBatch();
	}

	private void storeParsedData(Vfk vfk) {

		ExporterFactory exporterFactory;
		if (toDatabase) {
			exporterFactory = new OracleDatabaseExporterFactory(
					configuration.getConnection());
		} else {
			exporterFactory = new OracleLoaderExporterFactory(vfk.getZmeny(),
					"EE8MSWIN1250", configuration.getOutput());
		}

		exportBonitniDilParcely(vfk, exporterFactory);
		exportJednotky(vfk, exporterFactory);
		exporterJinePravniVztahy(vfk, exporterFactory);
		exportNemovitosti(vfk, exporterFactory);
		exportRizeni(vfk, exporterFactory);
		exporterVlastnictvi(vfk, exporterFactory);
		exporterPrvkyKatastralniMapy(vfk, exporterFactory);
		exporterBonitovanePudneEkologickeJednotky(vfk, exporterFactory);
		exporterGeometrickyPlan(vfk, exporterFactory);
		exporterRezervovanaCisla(vfk, exporterFactory);
		exporterDefinicniBody(vfk, exporterFactory);
		exporterAdresniMista(vfk, exporterFactory);
	
	}

	private void exportBonitniDilParcely(Vfk vfk,
			ExporterFactory exporterFactory) {
		exporterFactory.getBonitDilyParcExporter(vfk.getBonitDilyParc());
	}

	private void exportJednotky(Vfk vfk, ExporterFactory exporterFactory) {
		exporterFactory.getJednotkyExporter(vfk.getJednotky());
		exporterFactory.getTJednotekExporter(vfk.getTJednotek());
		exporterFactory.getZpVyuzitiJedExporter(vfk.getZpVyuzitiJed());
	}

	private void exporterJinePravniVztahy(Vfk vfk,
			ExporterFactory exporterFactory) {
		exporterFactory.getJinePravVztahyExporter(vfk.getJinePravVztahy());
		exporterFactory.getTPravnichVztExporter(vfk.getTPravnichVzt());
		exporterFactory.getRJpvExporter(vfk.getRJpv());
	}

	private void exportNemovitosti(Vfk vfk, ExporterFactory exporterFactory) {
		exporterFactory.getParcelyExporter(vfk.getParcely());
		exporterFactory.getBudovyExporter(vfk.getBudovy());
		exporterFactory.getCastiBudovExporter(vfk.getCastiBudov());
		exporterFactory.getZpOchranyNemExporter(vfk.getZpOchranyNem());
		exporterFactory.getDPozemkuExporter(vfk.getDPozemku());
		exporterFactory.getZpVyuzitiPozExporter(vfk.getZpVyuzitiPoz());
		exporterFactory.getZdrojeParcelZeExporter(vfk.getZdrojeParcelZe());
		exporterFactory.getZpUrceniVymeryExporter(vfk.getZpUrceniVymery());
		exporterFactory.getTBudovExporter(vfk.getTBudov());
		exporterFactory.getMapoveListyExporter(vfk.getMapoveListy());
		exporterFactory.getKatastrUzemiExporter(vfk.getKatastrUzemi());
		exporterFactory.getObceExporter(vfk.getObce());
		exporterFactory.getCastiObciExporter(vfk.getCastiObci());
		exporterFactory.getOkresyExporter(vfk.getOkresy());
		exporterFactory.getKrajeExporter(vfk.getKraje());
		exporterFactory.getNoveKrajeExporter(vfk.getNoveKraje());
		exporterFactory.getRZpochrExporter(vfk.getRZpochr());
		exporterFactory.getZpVyuzitiBudExporter(vfk.getZpVyuzitiBud());
		exporterFactory.getPravaStavbyExporter(vfk.getPravaStavby());
		exporterFactory.getRUcelNemExporter(vfk.getRUcelNem());
		exporterFactory.getUcelExporter(vfk.getUcel());
	}

	private void exportRizeni(Vfk vfk, ExporterFactory exporterFactory) {
		exporterFactory.getAdresyExporter(vfk.getAdresy());
		exporterFactory
				.getDalsiUdajeListinyExporter(vfk.getDalsiUdajeListiny());
		exporterFactory.getListinyExporter(vfk.getListiny());
		exporterFactory
				.getListinyDalsiUdajeExporter(vfk.getListinyDalsiUdaje());
		exporterFactory.getObeslaniMfExporter(vfk.getObeslaniMf());
		exporterFactory.getObjektyRizeniExporter(vfk.getObjektyRizeni());
		exporterFactory.getPredmetyRizeniExporter(vfk.getPredmetyRizeni());
		exporterFactory.getRizeniExporter(vfk.getRizeni());
		exporterFactory.getRizeniKuExporter(vfk.getRizeniKu());
		exporterFactory.getRListExporter(vfk.getRList());
		exporterFactory.getTListinExporter(vfk.getTListin());
		exporterFactory.getTPredmetuRExporter(vfk.getTPredmetuR());
		exporterFactory.getTypyRizeniExporter(vfk.getTypyRizeni());
		exporterFactory.getTypyUcastnikuExporter(vfk.getTypyUcastniku());
		exporterFactory.getUcastniciExporter(vfk.getUcastnici());
		exporterFactory.getUcastniciTypExporter(vfk.getUcastniciTyp());
	}

	private void exporterVlastnictvi(Vfk vfk, ExporterFactory exporterFactory) {
		exporterFactory.getCharOsExporter(vfk.getCharOs());
		exporterFactory.getOpravSubjektyExporter(vfk.getOpravSubjekty());
		exporterFactory.getTelesaExporter(vfk.getTelesa());
		exporterFactory.getVlastnictviExporter(vfk.getVlastnictvi());
	}

	private void exporterPrvkyKatastralniMapy(Vfk vfk,
			ExporterFactory exporterFactory) {
		exporterFactory.getDalsiPrvkyMapyExporter(vfk.getDalsiPrvkyMapy());
		exporterFactory.getHraniceParcelExporter(vfk.getHraniceParcel());
		exporterFactory.getKodyCharQBoduExporter(vfk.getKodyCharQBodu());
		exporterFactory.getObrazyBoduBpExporter(vfk.getObrazyBoduBp());
		exporterFactory.getObrazyBudovExporter(vfk.getObrazyBudov());
		exporterFactory.getObrazyParcelExporter(vfk.getObrazyParcel());
		exporterFactory.getPrvkyOMapyExporter(vfk.getPrvkyOMapy());
		exporterFactory.getSouradniceObrazuExporter(vfk.getSouradniceObrazu());
		exporterFactory.getSouradnicePolohyExporter(vfk.getSouradnicePolohy());
		exporterFactory.getSpojeniBMapyExporter(vfk.getSpojeniBMapy());
		exporterFactory.getSpojeniBPolohExporter(vfk.getSpojeniBPoloh());
		exporterFactory.getSpojeniPoMapyExporter(vfk.getSpojeniPoMapy());
		exporterFactory.getTPrvkuPDatExporter(vfk.getTPrvkuPDat());
		exporterFactory.getTSouradSysExporter(vfk.getTSouradSys());
		exporterFactory.getZobrazeniVbExporter(vfk.getZobrazeniVb());
	}

	private void exporterBonitovanePudneEkologickeJednotky(Vfk vfk,
			ExporterFactory exporterFactory) {
		exporterFactory.getHraniceBpejExporter(vfk.getHraniceBpej());
		exporterFactory.getOznaceniBpejExporter(vfk.getOznaceniBpej());
	}

	private void exporterGeometrickyPlan(Vfk vfk,
			ExporterFactory exporterFactory) {
		exporterFactory.getNavrhyZmenKmExporter(vfk.getNavrhyZmenKm());
		exporterFactory.getNzZpmzExporter(vfk.getNzZpmz());
		exporterFactory.getZpmzExporter(vfk.getZpmz());
	}

	private void exporterRezervovanaCisla(Vfk vfk,
			ExporterFactory exporterFactory) {
		exporterFactory.getDotcenaParCislaExporter(vfk.getDotcenaParCisla());
		exporterFactory.getDotHistParCislaExporter(vfk.getDotHistParCisla());
		exporterFactory.getRezCislaPbppExporter(vfk.getRezCislaPbpp());
		exporterFactory.getRezParcelniCislaExporter(vfk.getRezParcelniCisla());
	}

	private void exporterDefinicniBody(Vfk vfk, ExporterFactory exporterFactory) {
		exporterFactory.getObrazyDefBoduExporter(vfk.getObrazyDefBodu());
	}

	private void exporterAdresniMista(Vfk vfk, ExporterFactory exporterFactory) {
		exporterFactory.getAdresaExporter(vfk.getAdresa());
		exporterFactory.getBudObjExporter(vfk.getBudObj());
	}

}
