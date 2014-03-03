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
		preparedStatement.setObject(1, record.getKod());
		preparedStatement.setObject(2, record.getNazev());
		preparedStatement.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		preparedStatement.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		preparedStatement.setObject(5, record.getPozemek());
		preparedStatement.setObject(6, record.getBudova());
		preparedStatement.setObject(7, record.getJednotka());
		preparedStatement.setObject(8, record.getNemochr());
	}

}
