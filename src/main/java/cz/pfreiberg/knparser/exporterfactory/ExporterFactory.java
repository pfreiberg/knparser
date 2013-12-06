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

public interface ExporterFactory {

	public Exporter getParcelyExporter(List<Parcely> parcely);
	public Exporter getBudovyExporter(List<Budovy> budovy);
	public Exporter getCastiBudovExporter(List<CastiBudov> castiBudov);
	public Exporter getZpOchranyNemExporter(List<ZpOchranyNem> zpOchranyNem);
	public Exporter getDPozemkuExporter(List<DPozemku> dPozemku);
	public Exporter getZpVyuzitiPozExporter(List<ZpVyuzitiPoz> zpVyuzitiPoz);
	public Exporter getZdrojeParcelZeExporter(List<ZdrojeParcelZe> zdrojeParcelZe);
	public Exporter getZpUrceniVymeryExporter(List<ZpUrceniVymery> zpUrceniVymery);
	public Exporter getTBudovExporter(List<TBudov> tBudov);
	public Exporter getMapoveListyExporter(List<MapoveListy> mapoveListy);
	public Exporter getKatastrUzemiExporter(List<KatastrUzemi> katastrUzemi);
	public Exporter getObceExporter(List<Obce> obce);
	public Exporter getCastiObciExporter(List<CastiObci> castiObci);
	public Exporter getOkresyExporter(List<Okresy> okresy);
	public Exporter getKrajeExporter(List<Kraje> Kraje);
	public Exporter getNoveKrajeExporter(List<NoveKraje> noveKraje);
	public Exporter getRZpochrExporter(List<RZpochr> rZpochr);
	public Exporter getZpVyuzitiBudExporter(List<ZpVyuzitiBud> zpVyuzitiBud);

}