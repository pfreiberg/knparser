package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TBudovOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "T_BUDOV";

	public TBudovOracleDatabaseJdbcExporter(List<TBudov> tBudov,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(tBudov, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			TBudov record = (TBudov) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getNazev());
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setObject(5, record.getZadaniCd());
			preparedStatement.setObject(6, record.getZkratka());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
