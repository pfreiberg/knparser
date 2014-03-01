package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.ZdrojeParcelZe;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZdrojeParcelZeOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "ZDROJE_PARCEL_ZE";

	public ZdrojeParcelZeOracleDatabaseJdbcExporter(
			List<ZdrojeParcelZe> zdrojeParcelZe,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(zdrojeParcelZe, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			ZdrojeParcelZe record = (ZdrojeParcelZe) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getNazev());
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setObject(5, record.getZkratka());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
