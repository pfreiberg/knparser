package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.CastiBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class CastiBudovOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "CASTI_BUDOV";

	public CastiBudovOracleDatabaseJdbcExporter(List<CastiBudov> castiBudov,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(castiBudov, name);
	}

	protected void insertRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			CastiBudov record = (CastiBudov) rawRecord;
			preparedStatement.setObject(1, 0);
			preparedStatement.setObject(2,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(4, 0);
			preparedStatement.setObject(5, record.getRizeniIdVzniku());
			preparedStatement.setObject(6, record.getRizeniIdZaniku());
			preparedStatement.setObject(7, record.getBudId());
			preparedStatement.setObject(8, record.getTypbudKod());
			preparedStatement.setObject(9, record.getCisloDomovni());
			preparedStatement.setObject(10, record.getCenaNemovitosti());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	protected void insertHistoricalRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(SEQ_CASTI_BUDOV_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			CastiBudov record = (CastiBudov) rawRecord;
			preparedStatement.setObject(1, 0);
			preparedStatement.setObject(2,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(4, 0);
			preparedStatement.setObject(5, record.getRizeniIdVzniku());
			preparedStatement.setObject(6, record.getRizeniIdZaniku());
			preparedStatement.setObject(7, record.getBudId());
			preparedStatement.setObject(8, record.getTypbudKod());
			preparedStatement.setObject(9, record.getCisloDomovni());
			preparedStatement.setObject(10, record.getCenaNemovitosti());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}

	}

}
