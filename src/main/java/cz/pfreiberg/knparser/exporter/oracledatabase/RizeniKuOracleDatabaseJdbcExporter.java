package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.RizeniKu;

public class RizeniKuOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "RIZENI_KU";

	public RizeniKuOracleDatabaseJdbcExporter(List<RizeniKu> rizeniKu,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(rizeniKu, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			RizeniKu record = (RizeniKu) rawRecord;
			preparedStatement.setObject(1, record.getKatuzeKod());
			preparedStatement.setObject(2, record.getRizeniId());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
