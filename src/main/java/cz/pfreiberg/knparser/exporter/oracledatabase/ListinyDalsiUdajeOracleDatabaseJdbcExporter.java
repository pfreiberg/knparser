package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ListinyDalsiUdaje;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyDalsiUdajeOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<ListinyDalsiUdaje> listinyDalsiUdaje;
	private final static String name = "LISTINY_DALSI_UDAJE";

	public ListinyDalsiUdajeOracleDatabaseJdbcExporter(
			List<ListinyDalsiUdaje> listinyDalsiUdaje,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.listinyDalsiUdaje = listinyDalsiUdaje;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ListinyDalsiUdaje record : listinyDalsiUdaje) {
			primaryKeysValues = getPrimaryKeysValues(record, methodsName);
			processRecord(record);
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRecord(ListinyDalsiUdaje record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (find(parameters, null, false)) {
			delete(parameters, null, false);
		}
		insert(name, record, false);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
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
			preparedStatement.close();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
