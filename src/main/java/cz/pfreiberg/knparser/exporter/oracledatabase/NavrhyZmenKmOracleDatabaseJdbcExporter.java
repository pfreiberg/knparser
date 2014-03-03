package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.geometrickyplan.NavrhyZmenKm;

public class NavrhyZmenKmOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "NAVRHY_ZMEN_KM";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?)";

	public NavrhyZmenKmOracleDatabaseJdbcExporter(
			List<NavrhyZmenKm> navrhyZmenKm,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(navrhyZmenKm, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		NavrhyZmenKm record = (NavrhyZmenKm) rawRecord;
		preparedStatement.setObject(1, record.getId());
		preparedStatement.setObject(2, record.getStavNz());
		preparedStatement.setObject(3, record.getNzType());
		preparedStatement.setObject(4, record.getPorizeniDatNz());
		preparedStatement.setObject(5, record.getRizeniId());
		preparedStatement.setObject(6, record.getCisloPlanu());
		preparedStatement.setObject(7, record.getVyhotovil());
		preparedStatement.setObject(8, record.getOznaceniMapovehoListu());
	}

}
