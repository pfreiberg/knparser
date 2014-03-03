package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.TypyRizeni;

public class TypyRizeniOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "TYPY_RIZENI";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?)";

	public TypyRizeniOracleDatabaseJdbcExporter(List<TypyRizeni> typyRizeni,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(typyRizeni, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {

		TypyRizeni record = (TypyRizeni) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3, record.getPopis());
		psInsert.setObject(4, record.getZpoplatneni());
	}

}
