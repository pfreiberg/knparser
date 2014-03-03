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
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getObceKod());
		psInsert.setObject(3, record.getNazev());
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(5,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
	}
}
