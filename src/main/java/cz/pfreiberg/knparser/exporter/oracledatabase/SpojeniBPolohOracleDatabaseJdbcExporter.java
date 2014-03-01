package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBPoloh;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBPolohOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "SPOJENI_B_POLOH";

	public SpojeniBPolohOracleDatabaseJdbcExporter(
			List<SpojeniBPoloh> spojeniBPoloh,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(spojeniBPoloh, name);
	}

	protected void insertRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			SpojeniBPoloh record = (SpojeniBPoloh) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getBpId());
			preparedStatement.setObject(9, record.getPoradoveCisloBodu());
			preparedStatement.setObject(10, record.getObId());
			preparedStatement.setObject(11, record.getHpId());
			preparedStatement.setObject(12, record.getDpmId());
			preparedStatement.setObject(13, record.getParametrySpojeni());
			preparedStatement.setObject(14, record.getZvbId());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	protected void insertHistoricalRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(SEQ_SPOJENI_B_POLOH_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			SpojeniBPoloh record = (SpojeniBPoloh) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getBpId());
			preparedStatement.setObject(9, record.getPoradoveCisloBodu());
			preparedStatement.setObject(10, record.getObId());
			preparedStatement.setObject(11, record.getHpId());
			preparedStatement.setObject(12, record.getDpmId());
			preparedStatement.setObject(13, record.getParametrySpojeni());
			preparedStatement.setObject(14, record.getZvbId());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
