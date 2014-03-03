package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.CastiObci;
import cz.pfreiberg.knparser.util.VfkUtil;

public class CastiObciOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "CASTI_OBCI";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?)";

	public CastiObciOracleDatabaseJdbcExporter(List<CastiObci> castiObci,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(castiObci, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		CastiObci record = (CastiObci) rawRecord;
		preparedStatement.setObject(1, record.getKod());
		preparedStatement.setObject(2, record.getObceKod());
		preparedStatement.setObject(3, record.getNazev());
		preparedStatement.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		preparedStatement.setObject(5,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
	}
}
