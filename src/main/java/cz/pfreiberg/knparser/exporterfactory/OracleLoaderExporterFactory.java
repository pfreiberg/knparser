package cz.pfreiberg.knparser.exporterfactory;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ParcelyOracleLoaderFileExporter;

public class OracleLoaderExporterFactory implements ExporterFactory {

	String prefix;
	String characterSet;
	String output;

	public OracleLoaderExporterFactory(int zmeny, String characterSet,
			String output) {
		this.prefix = (zmeny == 1) ? "TMP_" : "";
		this.characterSet = characterSet;
		this.output = output;
	}

	@Override
	public Exporter getParcelyExporter(List<Parcely> parcely) {
		return new ParcelyOracleLoaderFileExporter(parcely, prefix,
				characterSet, output);
	}

}
