package cz.pfreiberg.knparser;

import java.io.FileNotFoundException;
import java.io.IOException;

import cz.pfreiberg.knparser.domain.Vfk;
import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.exporterfactory.OracleLoaderExporterFactory;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;

public class Controller {

	Configuration configuration;

	public Controller(Configuration configuration) {
		this.configuration = configuration;
	}

	public void run() {

		try {
			Parser parser = new Parser(configuration);
			parser.parse();
			
			Vfk vfk = parser.getVfk();
			OracleLoaderExporterFactory loaderExporterFactory = new OracleLoaderExporterFactory(vfk.getCodepage());
			Exporter parcely = loaderExporterFactory.getParcelyExporter(vfk.getParcely());
			parcely.insert();
		} catch (FileNotFoundException e) {
			System.out.println("Input file was NOT found.");
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
