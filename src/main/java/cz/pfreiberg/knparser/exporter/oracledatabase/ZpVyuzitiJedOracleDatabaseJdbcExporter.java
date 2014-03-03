package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jednotky.ZpVyuzitiJed;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpVyuzitiJedOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "ZP_VYUZITI_JED";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?)";

	public ZpVyuzitiJedOracleDatabaseJdbcExporter(
			List<ZpVyuzitiJed> zpVyuzitiJed,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(zpVyuzitiJed, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		ZpVyuzitiJed record = (ZpVyuzitiJed) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setObject(5, record.getZkratka());
		psInsert.setObject(6, record.getDoplKod());
	}

}
