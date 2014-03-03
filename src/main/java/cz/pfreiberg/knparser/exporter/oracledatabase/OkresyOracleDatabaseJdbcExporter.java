package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.Okresy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OkresyOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "OKRESY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?)";

	public OkresyOracleDatabaseJdbcExporter(List<Okresy> okresy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(okresy, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		Okresy record = (Okresy) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getKrajeKod());
		psInsert.setObject(3, record.getNazev());
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(5,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setObject(6, record.getNuts4());
		psInsert.setObject(7, record.getNkrajeKod());
	}

}
