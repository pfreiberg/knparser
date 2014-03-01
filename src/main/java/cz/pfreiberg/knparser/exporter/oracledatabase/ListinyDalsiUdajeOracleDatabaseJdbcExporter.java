package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ListinyDalsiUdaje;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyDalsiUdajeOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "LISTINY_DALSI_UDAJE";

	public ListinyDalsiUdajeOracleDatabaseJdbcExporter(
			List<ListinyDalsiUdaje> listinyDalsiUdaje,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(listinyDalsiUdaje, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			ListinyDalsiUdaje record = (ListinyDalsiUdaje) rawRecord;
			preparedStatement.setObject(1, record.getListinId());
			preparedStatement.setObject(2, record.getDulKod());
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getCreateDate()));

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}
}
