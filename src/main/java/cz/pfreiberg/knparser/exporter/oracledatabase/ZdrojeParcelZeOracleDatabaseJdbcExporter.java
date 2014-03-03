package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.ZdrojeParcelZe;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZdrojeParcelZeOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "ZDROJE_PARCEL_ZE";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?)";

	public ZdrojeParcelZeOracleDatabaseJdbcExporter(
			List<ZdrojeParcelZe> zdrojeParcelZe,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(zdrojeParcelZe, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		ZdrojeParcelZe record = (ZdrojeParcelZe) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setObject(5, record.getZkratka());
	}

}
