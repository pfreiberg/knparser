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
			loaderExporterFactory.getParcelyExporter(vfk.getParcely());
			loaderExporterFactory.getBudovyExporter(vfk.getBudovy());
			loaderExporterFactory.getCastiBudovExporter(vfk.getCastiBudov());
			loaderExporterFactory.getZpOchranyNemExporter(vfk.getZpOchranyNem());
			loaderExporterFactory.getDPozemkuExporter(vfk.getDPozemku());
			loaderExporterFactory.getZpVyuzitiPozExporter(vfk.getZpVyuzitiPoz());
			loaderExporterFactory.getZdrojeParcelZeExporter(vfk.getZdrojeParcelZe());
			loaderExporterFactory.getZpUrceniVymeryExporter(vfk.getZpUrceniVymery());
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
			System.out.println("Parsing finished.");
		} catch (FileNotFoundException e) {
			System.out.println("Input file was NOT found.");
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
