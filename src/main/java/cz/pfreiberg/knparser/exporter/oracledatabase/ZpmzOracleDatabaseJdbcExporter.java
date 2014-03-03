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
		psInsert.setObject(1, record.getKatuzeKod());
		psInsert.setObject(2, record.getCisloZpmz());
		psInsert.setObject(3, record.getPpzId());
		psInsert.setObject(4, record.getStavZpmz());
		psInsert.setObject(5, record.getMerickyNacrt());
		psInsert.setObject(6, record.getZapisnikPodrobMereni());
		psInsert.setObject(7, record.getVypocetProtokolVymer());
		psInsert.setObject(8, record.getTypsosKod());
	}
}
