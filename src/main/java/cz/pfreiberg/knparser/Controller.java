package cz.pfreiberg.knparser;

import java.io.FileNotFoundException;
import java.io.IOException;

import cz.pfreiberg.knparser.domain.Vfk;
import cz.pfreiberg.knparser.exporterfactory.OracleLoaderExporterFactory;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;

/**
 * Řídící část programu. Využívá parser k naplnění doménových tříd ze souboru.
 * Následně iniciuje jejich zpracování a převedení do load filu, nebo databáze.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Controller {

	Configuration configuration;

	public Controller(Configuration configuration) {
		this.configuration = configuration;
	}

	public void run() {
		try {
			Parser parser = new Parser(configuration);
			System.out.println(parser.parse() + " row/s was escaped.");
			System.out.println("Is parsed. Starting the storage sequence.");

			Vfk vfk = parser.getVfk();
			OracleLoaderExporterFactory loaderExporterFactory = new OracleLoaderExporterFactory(
					vfk.getZmeny(), "UTF8",
					configuration.getDestinationOfOutput());
			exportBonitniDilParcely(vfk, loaderExporterFactory);
			exportJednotky(vfk, loaderExporterFactory);
			exporterJinePravniVztahy(vfk, loaderExporterFactory);
			exportNemovitosti(vfk, loaderExporterFactory);
			exportRizeni(vfk, loaderExporterFactory);
			exporterVlastnictvi(vfk, loaderExporterFactory);
			exporterPrvkyKatastralniMapy(vfk, loaderExporterFactory);
			exporterBonitovanePudneEkologickeJednotky(vfk,
					loaderExporterFactory);
			exporterGeometrickyPlan(vfk, loaderExporterFactory);
			exporterRezervovanaCisla(vfk, loaderExporterFactory);
			exporterDefinicniBody(vfk, loaderExporterFactory);
			exporterAdresniMista(vfk, loaderExporterFactory);
			System.out.println("Parsing finished.");
		} catch (FileNotFoundException e) {
			System.out.println("Input file was NOT found.");
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void exportBonitniDilParcely(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getBonitDilyParcExporter(vfk.getBonitDilyParc());
	}

	private void exportJednotky(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getJednotkyExporter(vfk.getJednotky());
		loaderExporterFactory.getTJednotekExporter(vfk.getTJednotek());
		loaderExporterFactory.getZpVyuzitiJedExporter(vfk.getZpVyuzitiJed());
	}

	private void exporterJinePravniVztahy(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory
				.getJinePravVztahyExporter(vfk.getJinePravVztahy());
		loaderExporterFactory.getTPravnichVztExporter(vfk.getTPravnichVzt());
		loaderExporterFactory.getRJpvExporter(vfk.getRJpv());
	}

	private void exportNemovitosti(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getParcelyExporter(vfk.getParcely());
		loaderExporterFactory.getBudovyExporter(vfk.getBudovy());
		loaderExporterFactory.getCastiBudovExporter(vfk.getCastiBudov());
		loaderExporterFactory.getZpOchranyNemExporter(vfk.getZpOchranyNem());
		loaderExporterFactory.getDPozemkuExporter(vfk.getDPozemku());
		loaderExporterFactory.getZpVyuzitiPozExporter(vfk.getZpVyuzitiPoz());
		loaderExporterFactory
				.getZdrojeParcelZeExporter(vfk.getZdrojeParcelZe());
		loaderExporterFactory
				.getZpUrceniVymeryExporter(vfk.getZpUrceniVymery());
		loaderExporterFactory.getTBudovExporter(vfk.getTBudov());
		loaderExporterFactory.getMapoveListyExporter(vfk.getMapoveListy());
		loaderExporterFactory.getKatastrUzemiExporter(vfk.getKatastrUzemi());
		loaderExporterFactory.getObceExporter(vfk.getObce());
		loaderExporterFactory.getCastiObciExporter(vfk.getCastiObci());
		loaderExporterFactory.getOkresyExporter(vfk.getOkresy());
		loaderExporterFactory.getKrajeExporter(vfk.getKraje());
		loaderExporterFactory.getNoveKrajeExporter(vfk.getNoveKraje());
		loaderExporterFactory.getRZpochrExporter(vfk.getRZpochr());
		loaderExporterFactory.getZpVyuzitiBudExporter(vfk.getZpVyuzitiBud());
		loaderExporterFactory.getPravaStavbyExporter(vfk.getPravaStavby());
		loaderExporterFactory.getRUcelNemExporter(vfk.getRUcelNem());
		loaderExporterFactory.getUcelExporter(vfk.getUcel());
	}

	private void exportRizeni(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getAdresyExporter(vfk.getAdresy());
		loaderExporterFactory.getDalsiUdajeListinyExporter(vfk
				.getDalsiUdajeListiny());
		loaderExporterFactory.getListinyExporter(vfk.getListiny());
		loaderExporterFactory.getListinyDalsiUdajeExporter(vfk
				.getListinyDalsiUdaje());
		loaderExporterFactory.getObeslaniMfExporter(vfk.getObeslaniMf());
		loaderExporterFactory.getObjektyRizeniExporter(vfk.getObjektyRizeni());
		loaderExporterFactory
				.getPredmetyRizeniExporter(vfk.getPredmetyRizeni());
		loaderExporterFactory.getRizeniExporter(vfk.getRizeni());
		loaderExporterFactory.getRizeniKuExporter(vfk.getRizeniKu());
		loaderExporterFactory.getRListExporter(vfk.getRList());
		loaderExporterFactory.getTListinExporter(vfk.getTListin());
		loaderExporterFactory.getTPredmetuRExporter(vfk.getTPredmetuR());
		loaderExporterFactory.getTypyRizeniExporter(vfk.getTypyRizeni());
		loaderExporterFactory.getTypyUcastnikuExporter(vfk.getTypyUcastniku());
		loaderExporterFactory.getUcastniciExporter(vfk.getUcastnici());
		loaderExporterFactory.getUcastniciTypExporter(vfk.getUcastniciTyp());
	}

	private void exporterVlastnictvi(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getCharOsExporter(vfk.getCharOs());
		loaderExporterFactory.getOpravSubjektyExporter(vfk.getOpravSubjekty());
		loaderExporterFactory.getTelesaExporter(vfk.getTelesa());
		loaderExporterFactory.getVlastnictviExporter(vfk.getVlastnictvi());
	}

	private void exporterPrvkyKatastralniMapy(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory
				.getDalsiPrvkyMapyExporter(vfk.getDalsiPrvkyMapy());
		loaderExporterFactory.getHraniceParcelExporter(vfk.getHraniceParcel());
		loaderExporterFactory.getKodyCharQBoduExporter(vfk.getKodyCharQBodu());
		loaderExporterFactory.getObrazyBoduBpExporter(vfk.getObrazyBoduBp());
		loaderExporterFactory.getObrazyBudovExporter(vfk.getObrazyBudov());
		loaderExporterFactory.getObrazyParcelExporter(vfk.getObrazyParcel());
		loaderExporterFactory.getPrvkyOMapyExporter(vfk.getPrvkyOMapy());
		loaderExporterFactory.getSouradniceObrazuExporter(vfk
				.getSouradniceObrazu());
		loaderExporterFactory.getSouradnicePolohyExporter(vfk
				.getSouradnicePolohy());
		loaderExporterFactory.getSpojeniBMapyExporter(vfk.getSpojeniBMapy());
		loaderExporterFactory.getSpojeniBPolohExporter(vfk.getSpojeniBPoloh());
		loaderExporterFactory.getSpojeniPoMapyExporter(vfk.getSpojeniPoMapy());
		loaderExporterFactory.getTPrvkuPDatExporter(vfk.getTPrvkuPDat());
		loaderExporterFactory.getTSouradSysExporter(vfk.getTSouradSys());
		loaderExporterFactory.getZobrazeniVbExporter(vfk.getZobrazeniVb());
	}

	private void exporterBonitovanePudneEkologickeJednotky(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getHraniceBpejExporter(vfk.getHraniceBpej());
		loaderExporterFactory.getOznaceniBpejExporter(vfk.getOznaceniBpej());
	}

	private void exporterGeometrickyPlan(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getNavrhyZmenKmExporter(vfk.getNavrhyZmenKm());
		loaderExporterFactory.getNzZpmzExporter(vfk.getNzZpmz());
		loaderExporterFactory.getZpmzExporter(vfk.getZpmz());
	}

	private void exporterRezervovanaCisla(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getDotcenaParCislaExporter(vfk
				.getDotcenaParCisla());
		loaderExporterFactory.getDotHistParCislaExporter(vfk
				.getDotHistParCisla());
		loaderExporterFactory.getRezCislaPbppExporter(vfk.getRezCislaPbpp());
		loaderExporterFactory.getRezParcelniCislaExporter(vfk
				.getRezParcelniCisla());
	}

	private void exporterDefinicniBody(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getObrazyDefBoduExporter(vfk.getObrazyDefBodu());
	}

	private void exporterAdresniMista(Vfk vfk,
			OracleLoaderExporterFactory loaderExporterFactory) {
		loaderExporterFactory.getAdresaExporter(vfk.getAdresa());
		loaderExporterFactory.getBudObjExporter(vfk.getBudObj());
	}

}
