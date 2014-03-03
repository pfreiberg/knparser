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
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, record.getStavNz());
		psInsert.setObject(3, record.getNzType());
		psInsert.setObject(4, record.getPorizeniDatNz());
		psInsert.setObject(5, record.getRizeniId());
		psInsert.setObject(6, record.getCisloPlanu());
		psInsert.setObject(7, record.getVyhotovil());
		psInsert.setObject(8, record.getOznaceniMapovehoListu());
	}

}
