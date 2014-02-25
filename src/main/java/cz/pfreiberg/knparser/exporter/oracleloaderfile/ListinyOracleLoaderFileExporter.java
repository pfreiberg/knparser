package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.Listiny;

public class ListinyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "LISTINY";

	public ListinyOracleLoaderFileExporter(List<Listiny> listiny,
			String prefix, String characterSet, String output) {
		super(listiny, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "TYPLIST_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "2000");
		controlFile = super.insertVarcharColumn(controlFile, "OBSAH", "4");
		controlFile = super.insertColumn(controlFile, "STRAN");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VYHOTOVENI");
		controlFile = super.insertVarcharColumn(controlFile, "ZHOTOVITEL", "60");
		controlFile = super.insertVarcharColumn(controlFile, "PORADOVE_CISLO_ZHOTOVITELE", "8");
		controlFile = super.insertDateColumn(controlFile, "ROK_ZHOTOVITELE");
		controlFile = super.insertVarcharColumn(controlFile, "DOPLNENI_ZHOTOVITELE", "60");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "10");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertVarcharColumn(controlFile, "ZMENA_PRAV_VZTAHU", "4");
		controlFile = super.insertDateColumn(controlFile, "DATUM_PRAV_MOCI");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VYKONATELNOSTI");
		controlFile = super.insertDateColumn(controlFile, "DATUM_HIST_OD");
		controlFile = super.insertDateColumn(controlFile, "DATUM_HIST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
