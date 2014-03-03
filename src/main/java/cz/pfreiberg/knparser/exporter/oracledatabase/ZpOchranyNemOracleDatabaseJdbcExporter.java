package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpOchranyNem;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpOchranyNemOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "ZP_OCHRANY_NEM";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?)";

	public ZpOchranyNemOracleDatabaseJdbcExporter(
			List<ZpOchranyNem> zpOchranyNem,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(zpOchranyNem, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		ZpOchranyNem record = (ZpOchranyNem) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setObject(5, record.getPozemek());
		psInsert.setObject(6, record.getBudova());
		psInsert.setObject(7, record.getJednotka());
		psInsert.setObject(8, record.getNemochr());
	}

}
