package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBMapyOracleDatabaseJdbcExporter extends
		Stav2OracleDatabaseJdbcExporter {

	private final static String name = "SPOJENI_B_MAPY";
	private static final String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?)";
	private static final String hisInsert = "INSERT INTO " + name + "_MIN" + " VALUES"
			+ "(SEQ_SPOJENI_B_MAPY_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";

	public SpojeniBMapyOracleDatabaseJdbcExporter(
			List<SpojeniBMapy> spojeniBMapy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(spojeniBMapy, name);
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
