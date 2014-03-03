package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.TypyUcastniku;

public class TypyUcastnikuOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "TYPY_UCASTNIKU";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?)";

	public TypyUcastnikuOracleDatabaseJdbcExporter(
			List<TypyUcastniku> typyUcastniku,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(typyUcastniku, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		TypyUcastniku record = (TypyUcastniku) rawRecord;
		preparedStatement.setObject(1, record.getKod());
		preparedStatement.setObject(2, record.getNazev());
		preparedStatement.setObject(3, record.getPopis());
	}
}
