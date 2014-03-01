package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.vlastnictvi.Telesa;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TelesaOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "TELESA";

	public TelesaOracleDatabaseJdbcExporter(List<Telesa> telesa,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(telesa, name);
	}

	protected void insertRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Telesa record = (Telesa) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getKatuzeKod());
			preparedStatement.setObject(9, record.getCisloTel());
			preparedStatement.setObject(10, null);
			preparedStatement.setObject(11, null);

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	protected void insertHistoricalRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(SEQ_TELESA_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Telesa record = (Telesa) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getKatuzeKod());
			preparedStatement.setObject(9, record.getCisloTel());
			preparedStatement.setObject(10, null);
			preparedStatement.setObject(11, null);

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}

	}

}
