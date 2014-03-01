package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.PredmetyRizeni;

public class PredmetyRizeniOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "PREDMETY_RIZENI";

	public PredmetyRizeniOracleDatabaseJdbcExporter(
			List<PredmetyRizeni> predmetyRizeni,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(predmetyRizeni, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			PredmetyRizeni record = (PredmetyRizeni) rawRecord;
			preparedStatement.setObject(1, record.getRizeniId());
			preparedStatement.setObject(2, record.getTyppreKod());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}
}
