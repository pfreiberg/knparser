package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpUrceniVymery;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpUrceniVymeryOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "ZP_URCENI_VYMERY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?)";

	public ZpUrceniVymeryOracleDatabaseJdbcExporter(
			List<ZpUrceniVymery> zpUrceniVymery,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(zpUrceniVymery, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		ZpUrceniVymery record = (ZpUrceniVymery) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));

	}

}
