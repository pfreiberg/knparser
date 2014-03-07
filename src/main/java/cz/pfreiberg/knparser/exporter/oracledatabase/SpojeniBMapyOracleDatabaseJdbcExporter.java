package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Speciální případ historizační tabulky. Primární klíče a jejich hodnoty se
 * určují pro každý záznam zvlášť.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class SpojeniBMapyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "SPOJENI_B_MAPY";
	private static final String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?)";
	private static final String hisInsert = "INSERT INTO " + name + " VALUES"
			+ "(SEQ_SPOJENI_B_MAPY_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";

	public SpojeniBMapyOracleDatabaseJdbcExporter(
			List<SpojeniBMapy> spojeniBMapy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(spojeniBMapy, name);
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

	@Override
	protected void insertRecord(Object rawRecord) throws SQLException {
		SpojeniBMapy record = (SpojeniBMapy) rawRecord;
		psInsert.setObject(1,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(2,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(3, record.getPoradoveCisloBodu());
		psInsert.setObject(4, record.getSouradniceY());
		psInsert.setObject(5, record.getSouradniceX());
		psInsert.setObject(6, record.getOpId());
		psInsert.setObject(7, record.getDpmId());
		psInsert.setObject(8, record.getHbpejId());
		psInsert.setObject(9, record.getParametrySpojeni());
		psInsert.setObject(10, 0);
	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord) throws SQLException {
		SpojeniBMapy record = (SpojeniBMapy) rawRecord;
		psHisInsert.setObject(1,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(2,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(3, record.getPoradoveCisloBodu());
		psHisInsert.setObject(4, record.getSouradniceY());
		psHisInsert.setObject(5, record.getSouradniceX());
		psHisInsert.setObject(6, record.getOpId());
		psHisInsert.setObject(7, record.getDpmId());
		psHisInsert.setObject(8, record.getHbpejId());
		psHisInsert.setObject(9, record.getParametrySpojeni());
		psHisInsert.setObject(10, 0);
	}

}
