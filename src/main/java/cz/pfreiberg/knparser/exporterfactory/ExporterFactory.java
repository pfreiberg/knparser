package cz.pfreiberg.knparser.exporterfactory;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.exporter.Exporter;

public interface ExporterFactory {

	public Exporter getParcelyExporter(List<Parcely> parcely);

}