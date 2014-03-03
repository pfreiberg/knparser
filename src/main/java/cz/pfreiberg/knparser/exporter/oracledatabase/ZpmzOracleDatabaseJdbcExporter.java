package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.geometrickyplan.Zpmz;

public class ZpmzOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "ZPMZ";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?)";

	public ZpmzOracleDatabaseJdbcExporter(List<Zpmz> zpmz,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(zpmz, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		Zpmz record = (Zpmz) rawRecord;
		preparedStatement.setObject(1, record.getKatuzeKod());
		preparedStatement.setObject(2, record.getCisloZpmz());
		preparedStatement.setObject(3, record.getPpzId());
		preparedStatement.setObject(4, record.getStavZpmz());
		preparedStatement.setObject(5, record.getMerickyNacrt());
		preparedStatement.setObject(6, record.getZapisnikPodrobMereni());
		preparedStatement.setObject(7, record.getVypocetProtokolVymer());
		preparedStatement.setObject(8, record.getTypsosKod());
	}
}
