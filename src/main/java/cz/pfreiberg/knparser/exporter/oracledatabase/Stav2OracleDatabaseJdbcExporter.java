package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;

/**
 * Abstraktní třída poskytující logiku pro stavové tabulky typu 2.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public abstract class Stav2OracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	public Stav2OracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name,
			String insert, String hisInsert) {
		super(connectionParameters, name, insert, hisInsert);
	}

	@Override
	protected List<Object> getPrimaryKeysValues(Object record,
			List<String> methodsName) {
		SpojeniBMapy spojeniBMapy = (SpojeniBMapy) record;
		primaryKeys.clear();
		List<Object> actualPrimaryKeysValues = new ArrayList<>();
		if (spojeniBMapy.getOpId() != null) {
			primaryKeys.add("OP_ID");
			actualPrimaryKeysValues.add(spojeniBMapy.getOpId());
		} else if (spojeniBMapy.getDpmId() != null) {
			primaryKeys.add("DPM_ID");
			actualPrimaryKeysValues.add(spojeniBMapy.getDpmId());
		} else if (spojeniBMapy.getHbpejId() != null) {
			primaryKeys.add("HBPEJ_ID");
			actualPrimaryKeysValues.add(spojeniBMapy.getHbpejId());
		}
		primaryKeys.add("PORADOVE_CISLO_BODU");
		actualPrimaryKeysValues.add(spojeniBMapy.getPoradoveCisloBodu());

		return actualPrimaryKeysValues;
	}

}
