package cz.pfreiberg.knparser.exporterfactory;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Budovy;
import cz.pfreiberg.knparser.domain.nemovitosti.CastiBudov;
import cz.pfreiberg.knparser.domain.nemovitosti.CastiObci;
import cz.pfreiberg.knparser.domain.nemovitosti.DPozemku;
import cz.pfreiberg.knparser.domain.nemovitosti.KatastrUzemi;
import cz.pfreiberg.knparser.domain.nemovitosti.Kraje;
import cz.pfreiberg.knparser.domain.nemovitosti.MapoveListy;
import cz.pfreiberg.knparser.domain.nemovitosti.NoveKraje;
import cz.pfreiberg.knparser.domain.nemovitosti.Obce;
import cz.pfreiberg.knparser.domain.nemovitosti.Okresy;
import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.domain.nemovitosti.RZpochr;
import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;
import cz.pfreiberg.knparser.domain.nemovitosti.ZdrojeParcelZe;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpOchranyNem;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpUrceniVymery;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiBud;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiPoz;
import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.BudovyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CastiBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CastiObciOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DPozemkuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KatastrUzemiOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KrajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.MapoveListyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.NoveKrajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObceOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.OkresyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ParcelyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RZpochrOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZdrojeParcelZeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpOchranyNemOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpUrceniVymeryOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiBudOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiPozOracleLoaderFileExporter;

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

	@Override
	public Exporter getBudovyExporter(List<Budovy> budovy) {
		return new BudovyOracleLoaderFileExporter(budovy, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getCastiBudovExporter(List<CastiBudov> castiBudov) {
		return new CastiBudovOracleLoaderFileExporter(castiBudov, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZpOchranyNemExporter(List<ZpOchranyNem> zpOchranyNem) {
		return new ZpOchranyNemOracleLoaderFileExporter(zpOchranyNem, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getDPozemkuExporter(List<DPozemku> dPozemku) {
		return new DPozemkuOracleLoaderFileExporter(dPozemku, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZpVyuzitiPozExporter(List<ZpVyuzitiPoz> zpVyuzitiPoz) {
		return new ZpVyuzitiPozOracleLoaderFileExporter(zpVyuzitiPoz, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZdrojeParcelZeExporter(
			List<ZdrojeParcelZe> zdrojeParcelZe) {
		return new ZdrojeParcelZeOracleLoaderFileExporter(zdrojeParcelZe, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZpUrceniVymeryExporter(
			List<ZpUrceniVymery> zpUrceniVymery) {
		return new ZpUrceniVymeryOracleLoaderFileExporter(zpUrceniVymery, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getTBudovExporter(List<TBudov> tBudov) {
		return new TBudovOracleLoaderFileExporter(tBudov, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getMapoveListyExporter(List<MapoveListy> mapoveListy) {
		return new MapoveListyOracleLoaderFileExporter(mapoveListy, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getKatastrUzemiExporter(List<KatastrUzemi> katastrUzemi) {
		return new KatastrUzemiOracleLoaderFileExporter(katastrUzemi, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getObceExporter(List<Obce> obce) {
		return new ObceOracleLoaderFileExporter(obce, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getCastiObciExporter(List<CastiObci> castiObci) {
		return new CastiObciOracleLoaderFileExporter(castiObci, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getOkresyExporter(List<Okresy> okresy) {
		return new OkresyOracleLoaderFileExporter(okresy, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getKrajeExporter(List<Kraje> kraje) {
		return new KrajeOracleLoaderFileExporter(kraje, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getNoveKrajeExporter(List<NoveKraje> noveKraje) {
		return new NoveKrajeOracleLoaderFileExporter(noveKraje, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getRZpochrExporter(List<RZpochr> rZpochr) {
		return new RZpochrOracleLoaderFileExporter(rZpochr, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZpVyuzitiBudExporter(List<ZpVyuzitiBud> zpVyuzitiBud) {
		return new ZpVyuzitiBudOracleLoaderFileExporter(zpVyuzitiBud, prefix,
				characterSet, output);
	}
	
}
