package cz.pfreiberg.knparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.domain.Vfk;
import cz.pfreiberg.knparser.exporterfactory.ExporterFactory;
import cz.pfreiberg.knparser.exporterfactory.OracleDatabaseJdbcExporterFactory;
import cz.pfreiberg.knparser.exporterfactory.OracleLoaderFileExporterFactory;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.EncodingCzech;

/**
 * Řídící část programu. Využívá parser k naplnění doménových tříd ze souboru.
 * Následně iniciuje jejich zpracování a uložení do load filu, nebo databáze.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Controller {

	private static final Logger log = Logger.getLogger(Controller.class);

	private final Configuration configuration;
	private final int BUFFER_SIZE = 9;
	private final Parser parser;
	private long seconds;

	public Controller(Configuration configuration)
			throws FileNotFoundException, ParserException, IOException {
		this.configuration = configuration;
		parser = new Parser(configuration);
	}

	Object object;

	public void run() {

		ScheduledExecutorService executor = getTimer();

		BlockingQueue<Vfk> queue = new ArrayBlockingQueue<>(BUFFER_SIZE);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		log.info("Parsing started.");
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			log.fatal("Fatal error during parsing.");
			log.debug("Stack trace:", e);
		}
		log.info(parser.getEscapedRows() + " row/s was escaped.");
		log.info("Parsing finished.");

		executor.shutdown();
	}

	private ScheduledExecutorService getTimer() {

		Runnable runnableTime = new Runnable() {
			public void run() {
				log.info(seconds + " seconds...");
				seconds += 10;
			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(runnableTime, 0, 10, TimeUnit.SECONDS);
		return executor;
	}

	private class Producer implements Runnable {

		private BlockingQueue<Vfk> queue;

		public Producer(BlockingQueue<Vfk> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			Vfk vfk;
			try {
				do {
					vfk = parseBatch();
					log.debug("Batch buffer size ~ " + (queue.size() + 1));
					queue.put(vfk);
				} while (!vfk.isLastBatch());
			} catch (IOException e) {
				log.fatal("Error during reading input file.");
				log.debug("Stack trace:", e);
			} catch (InterruptedException e) {
				log.fatal("Last parsed batch is lost.");
				log.debug("Stack trace:", e);
			}
		}
	}

	private class Consumer implements Runnable {

		private BlockingQueue<Vfk> queue;

		public Consumer(BlockingQueue<Vfk> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			Vfk vfk = null;
			while (true) {
				try {
					vfk = queue.take();
					storeParsedData(vfk);
					Parser.setFirstBatchToFalse();
					log.info("Batch is stored.");

					if (vfk.isLastBatch()) {
						break;
					}

				} catch (InterruptedException e) {
					log.error("Error during storing last batch. Trying again.");
					log.debug("Stack trace:", e);
					Thread.currentThread().interrupt();
				}
			}

		}

	}

	private Vfk parseBatch() throws IOException {
		parser.parseFile();
		return parser.getBatch();
	}

	private void storeParsedData(Vfk vfk) {

		ExporterFactory exporterFactory;
		if (configuration.isConnectionParametersValid()) {
			exporterFactory = new OracleDatabaseJdbcExporterFactory(
					configuration.getConnection());
		} else {
			exporterFactory = new OracleLoaderFileExporterFactory(
					vfk.getZmeny(), EncodingCzech.windows1250.getEncodingVfk(),
					configuration.getOutput());
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
