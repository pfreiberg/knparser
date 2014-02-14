package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Listiny;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Listiny> listiny;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "LISTINY";

	public ListinyOracleDatabaseJdbcExporter(List<Listiny> listiny,
			ConnectionParameters connectionParameters) {
		this.listiny = listiny;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = super.getMethods(primaryKeys);
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
			primaryKeysValues = getPrimaryKeysValues(record);
			processRecord(record);
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<Object> getPrimaryKeysValues(Object record) {
		List<Object> primaryKeyValues = new ArrayList<>();
		try {
			for (int i = 0; i < methodsName.size(); i++) {
				Class<?> c = Class
						.forName("cz.pfreiberg.knparser.domain.rizeni.Listiny");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((Listiny) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(Listiny record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (newFind(parameters, null, false)) {
			newDelete(parameters, null, false);
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

	@Override
	public boolean find(String table, String date, String dateValue,
			String operation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String table, String date, String dateValue,
			String operation) {
		// TODO Auto-generated method stub
		
	}
	
}
