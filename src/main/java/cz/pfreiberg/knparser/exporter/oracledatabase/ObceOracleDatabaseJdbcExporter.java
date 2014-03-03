package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.Obce;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObceOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "OBCE";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?)";

	public ObceOracleDatabaseJdbcExporter(List<Obce> obce,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(obce, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		Obce record = (Obce) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getOkresyKod());
		psInsert.setObject(3, record.getNazev());
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(5,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
	}

}
