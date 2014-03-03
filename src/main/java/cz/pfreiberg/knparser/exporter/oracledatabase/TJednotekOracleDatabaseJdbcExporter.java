package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jednotky.TJednotek;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TJednotekOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "T_JEDNOTEK";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?)";

	public TJednotekOracleDatabaseJdbcExporter(List<TJednotek> tJednotek,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(tJednotek, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		TJednotek record = (TJednotek) rawRecord;
		preparedStatement.setObject(1, record.getKod());
		preparedStatement.setObject(2, record.getNazev());
		preparedStatement.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		preparedStatement.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		preparedStatement.setObject(5, record.getZkratka());
	}

}
