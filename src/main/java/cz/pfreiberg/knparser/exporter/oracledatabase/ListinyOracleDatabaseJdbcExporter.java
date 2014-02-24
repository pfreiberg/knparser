package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Listiny;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Listiny> listiny;
	private final static String name = "LISTINY";

	public ListinyOracleDatabaseJdbcExporter(List<Listiny> listiny,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.listiny = listiny;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Listiny record : listiny) {
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

	private void processRecord(Listiny record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (find(parameters, null, false)) {
			delete(parameters, null, false);
		}
		insert(name, record, false);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Listiny record = (Listiny) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getTyplistKod());
			preparedStatement.setObject(3, record.getPopis());
			preparedStatement.setObject(4, record.getObsah());
			preparedStatement.setObject(5, record.getStran());
			preparedStatement.setObject(6,
					VfkUtil.convertToDatabaseDate(record.getDatumVyhotoveni()));
			preparedStatement.setObject(7, record.getZhotovitel());
			preparedStatement.setObject(8, record.getPoradoveCisloZhotovitele());
			preparedStatement.setObject(9,
					VfkUtil.convertToDatabaseDate(record.getRokZhotovitele()));
			preparedStatement.setObject(10, record.getDoplneniZhotovitele());
			preparedStatement.setObject(11, record.getZkratka());
			preparedStatement.setObject(12, record.getRizeniId());
			preparedStatement.setObject(13, record.getZmenaPravVztahu());
			preparedStatement.setObject(14,
					VfkUtil.convertToDatabaseDate(record.getDatumPravMoci()));
			preparedStatement.setObject(15,
					VfkUtil.convertToDatabaseDate(record.getDatumVykonatelnosti()));
			preparedStatement.setObject(16,
					VfkUtil.convertToDatabaseDate(record.getDatumHistOd()));
			preparedStatement.setObject(17,
					VfkUtil.convertToDatabaseDate(record.getDatumHistDo()));
		

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
