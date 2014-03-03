package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.TPredmetuR;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPredmetuROracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "T_PREDMETU_R";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?)";

	public TPredmetuROracleDatabaseJdbcExporter(List<TPredmetuR> tPredmetuR,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(tPredmetuR, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		TPredmetuR record = (TPredmetuR) rawRecord;
		preparedStatement.setObject(1, record.getKod());
		preparedStatement.setObject(2, record.getNazev());
		preparedStatement.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		preparedStatement.setObject(4, record.getPopis());
		preparedStatement.setObject(5,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
	}

}
