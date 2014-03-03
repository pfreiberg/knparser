package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.MapoveListy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class MapoveListyOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "MAPOVE_LISTY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?)";

	public MapoveListyOracleDatabaseJdbcExporter(List<MapoveListy> mapoveListy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(mapoveListy, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		MapoveListy record = (MapoveListy) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, record.getOznaceniMapovehoListu());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setObject(5, record.getMapa());
	}

}
