package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniPoMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniPoMapyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "SPOJENI_PO_MAPY";
	private final static String insert = "INSERT INTO " + name + " VALUES" + "(?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES" + "(SEQ_SPOJENI_PO_MAPY_MIN.nextval,?,?,?,?,?,?,?,?,?)";

	public SpojeniPoMapyOracleDatabaseJdbcExporter(
			List<SpojeniPoMapy> spojeniPoMapy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(spojeniPoMapy, name);
	}

	@Override
	protected void insertRecord(Object rawRecord)
			throws SQLException {
		SpojeniPoMapy record = (SpojeniPoMapy) rawRecord;
		psInsert.setObject(1, 0);
		psInsert.setObject(2,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(4, 0);
		psInsert.setObject(5, record.getPoradoveCisloBodu());
		psInsert.setObject(6, record.getSouradniceY());
		psInsert.setObject(7, record.getSouradniceX());
		psInsert.setObject(8, record.getPomId());
		psInsert.setObject(9, record.getParametrySpojeni());

	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		SpojeniPoMapy record = (SpojeniPoMapy) rawRecord;
		psHisInsert.setObject(1, 0);
		psHisInsert.setObject(2,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(4, 0);
		psHisInsert.setObject(5, record.getPoradoveCisloBodu());
		psHisInsert.setObject(6, record.getSouradniceY());
		psHisInsert.setObject(7, record.getSouradniceX());
		psHisInsert.setObject(8, record.getPomId());
		psHisInsert.setObject(9, record.getParametrySpojeni());
	}

}
