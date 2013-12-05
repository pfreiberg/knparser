package cz.pfreiberg.knparser.exporterfactory;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ParcelyOracleLoaderFileExporter;

public class OracleLoaderExporterFactory implements ExporterFactory {

	String characterSet;
	String output;

	public OracleLoaderExporterFactory(String characterSet, String output) {
		this.characterSet = characterSet;
		this.output = output;
	}

	@Override
	public Exporter getParcelyExporter(List<Parcely> parcely) {
		return new ParcelyOracleLoaderFileExporter(parcely, characterSet, output);
	}

}
