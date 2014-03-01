package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiPoz;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpVyuzitiPozOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "ZP_VYUZITI_POZ";

	public ZpVyuzitiPozOracleDatabaseJdbcExporter(
			List<ZpVyuzitiPoz> zpVyuzitiPoz,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(zpVyuzitiPoz, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			ZpVyuzitiPoz record = (ZpVyuzitiPoz) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getNazev());
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(4, record.getTypppKod());
			preparedStatement.setObject(5,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setObject(6, record.getZkratka());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
