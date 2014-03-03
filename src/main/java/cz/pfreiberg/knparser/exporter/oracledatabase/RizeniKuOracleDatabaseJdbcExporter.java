package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.RizeniKu;

public class RizeniKuOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "RIZENI_KU";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?)";

	public RizeniKuOracleDatabaseJdbcExporter(List<RizeniKu> rizeniKu,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(rizeniKu, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		RizeniKu record = (RizeniKu) rawRecord;
		preparedStatement.setObject(1, record.getKatuzeKod());
		preparedStatement.setObject(2, record.getRizeniId());
	}

}
