package cz.pfreiberg.knparser.exporterfactory;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ParcelyOracleLoaderFileExporter;

public class OracleLoaderExporterFactory implements ExporterFactory {

	String characterSet;

	public OracleLoaderExporterFactory(String characterSet) {
		this.characterSet = characterSet;
	}

	@Override
	public Exporter getParcelyExporter(List<Parcely> parcely) {
		return new ParcelyOracleLoaderFileExporter(parcely, characterSet);
	}

}
