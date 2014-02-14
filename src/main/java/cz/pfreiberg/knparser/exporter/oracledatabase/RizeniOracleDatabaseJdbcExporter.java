package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Rizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RizeniOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Rizeni> rizeni;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "RIZENI";

	public RizeniOracleDatabaseJdbcExporter(List<Rizeni> rizeni,
			ConnectionParameters connectionParameters) {
		this.rizeni = rizeni;
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
		for (Rizeni record : rizeni) {
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
						.forName("cz.pfreiberg.knparser.domain.rizeni.Rizeni");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((Rizeni) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(Rizeni record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (find(parameters, null, false)) {
			delete(parameters, null, false);
		}
		insert(name, record, false);
	}
	
	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Rizeni record = (Rizeni) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getTyprizKod());
			preparedStatement.setObject(3, record.getPoradoveCislo());
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getRok()));
			preparedStatement.setObject(5, record.getStav());
			preparedStatement.setObject(6, record.getFunkceKod());
			preparedStatement.setObject(7, record.getTypopeKod());
			preparedStatement.setObject(8, record.getFunkceKodVyznamna());
			preparedStatement.setObject(9, record.getTypopeKodVyznamna());
			preparedStatement.setObject(10, record.getUzisysUsername());
			preparedStatement.setObject(11, record.getUzirolNazev());
			preparedStatement.setObject(12, record.getOsvobozeno());
			preparedStatement.setObject(13, record.getHodnotaKolku());
			preparedStatement.setObject(14,
					VfkUtil.convertToDatabaseDate(record.getDatum()));
			preparedStatement.setObject(15,
					VfkUtil.convertToDatabaseDate(record.getDatum2()));
			preparedStatement.setObject(16, record.getPopis());
			preparedStatement.setObject(17,
					VfkUtil.convertToDatabaseDate(record.getDatumUzavreni()));
			preparedStatement.setObject(18, record.getPraresKod());

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
