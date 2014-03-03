package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TBudovOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "T_BUDOV";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?)";

	public TBudovOracleDatabaseJdbcExporter(List<TBudov> tBudov,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(tBudov, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		TBudov record = (TBudov) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setObject(5, record.getZadaniCd());
		psInsert.setObject(6, record.getZkratka());
	}

}
