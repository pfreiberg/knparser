package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPravnichVztOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<TPravnichVzt> tPravnichVzt;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "T_PRAVNICH_VZT";

	public TPravnichVztOracleDatabaseJdbcExporter(
			List<TPravnichVzt> tPravnichVzt,
			ConnectionParameters connectionParameters) {
		this.tPravnichVzt = tPravnichVzt;
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
		for (TPravnichVzt record : tPravnichVzt) {
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
						.forName("cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((TPravnichVzt) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(TPravnichVzt record) {
		String platnostOd = VfkUtil.formatValueDatabase(record.getPlatnostOd());
		if (find(name, "PLATNOST_OD", platnostOd, "=")) {
			return;
		} else if (find(name, "PLATNOST_OD", platnostOd, "!=")) {
			update(name, record.getPlatnostOd());
			insert(name, record, true);
		} else {
			insert(name, record, true);
		}
	}

	@Override
	public boolean find(String table, String date, String dateValue,
			String operation) {
		String select = "SELECT * FROM " + table + " WHERE *pk* AND " + date
				+ operation + dateValue;

		for (int i = 0; i < primaryKeys.size(); i++) {
			select = select.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		select = select.replace(" AND *pk*", "");
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(select);
			boolean isFound = preparedStatement.executeQuery().next();
			preparedStatement.close();
			return isFound;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public void update(String table, Date platnostOd) {
		platnostOd = subtractOneSecond(platnostOd);
		String select = "UPDATE " + table + " SET PLATNOST_DO = "
				+ VfkUtil.formatValueDatabase(platnostOd)
				+ " WHERE PLATNOST_DO is NULL AND *pk*";

		for (int i = 0; i < primaryKeys.size(); i++) {
			select = select.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		select = select.replace(" AND *pk*", "");
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(select);
			preparedStatement.executeQuery();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			TPravnichVzt record = (TPravnichVzt) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getTprKod());
			preparedStatement.setObject(3, record.getNazev());
			preparedStatement.setObject(4, record.getVlastnictvi());
			preparedStatement.setObject(5, record.getProOs());
			preparedStatement.setObject(6, record.getProNemovitost());
			preparedStatement.setObject(7, record.getKNemovitosti());
			preparedStatement.setObject(8,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(9, record.getSekce());
			preparedStatement.setObject(10,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setObject(11, record.getVlvztah());
			preparedStatement.setObject(12, record.getKOs());
			preparedStatement.setObject(13, record.getPodilVeritele());
			preparedStatement.setObject(14, record.getPoradi());
			
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
	public void delete(String table, String date, String dateValue,
			String operation) {
	}
}
