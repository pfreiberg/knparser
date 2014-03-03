package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ListinyDalsiUdaje;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyDalsiUdajeOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "LISTINY_DALSI_UDAJE";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?)";

	public ListinyDalsiUdajeOracleDatabaseJdbcExporter(
			List<ListinyDalsiUdaje> listinyDalsiUdaje,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(listinyDalsiUdaje, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		ListinyDalsiUdaje record = (ListinyDalsiUdaje) rawRecord;
		psInsert.setObject(1, record.getListinId());
		psInsert.setObject(2, record.getDulKod());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getCreateDate()));
	}
}
