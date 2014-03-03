package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.geometrickyplan.NzZpmz;

public class NzZpmzOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "NZ_ZPMZ";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?)";

	public NzZpmzOracleDatabaseJdbcExporter(List<NzZpmz> nzZpmz,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(nzZpmz, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		NzZpmz record = (NzZpmz) rawRecord;
		preparedStatement.setObject(1, record.getNzId());
		preparedStatement.setObject(2, record.getZpmzCisloZpmz());
		preparedStatement.setObject(3, record.getZpmzKatuzeKod());
	}

}
