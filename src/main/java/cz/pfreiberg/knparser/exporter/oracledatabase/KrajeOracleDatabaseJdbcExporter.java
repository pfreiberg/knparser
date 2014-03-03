package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.Kraje;
import cz.pfreiberg.knparser.util.VfkUtil;

public class KrajeOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "KRAJE";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?)";

	public KrajeOracleDatabaseJdbcExporter(List<Kraje> kraje,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(kraje, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		Kraje record = (Kraje) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
	}

}
