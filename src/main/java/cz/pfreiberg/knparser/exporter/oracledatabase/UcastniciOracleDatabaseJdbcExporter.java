package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Ucastnici;
import cz.pfreiberg.knparser.util.VfkUtil;

public class UcastniciOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Ucastnici> ucastnici;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "UCASTNICI";

	public UcastniciOracleDatabaseJdbcExporter(List<Ucastnici> ucastnici,
			ConnectionParameters connectionParameters) {
		this.ucastnici = ucastnici;
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
		for (Ucastnici record : ucastnici) {
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
						.forName("cz.pfreiberg.knparser.domain.rizeni.Ucastnici");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((Ucastnici) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(Ucastnici record) {
		
		if (find(name, null, null, null)) {
			delete(name, null, null, null);	
		} 
		insert(name, record, true);
	}

	@Override
	public boolean find(String table, String date, String dateValue,
			String operation) {
		String select = "SELECT * FROM " + table + " WHERE *pk*";
		select = appendPrimaryKeys(select);
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

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Ucastnici record = (Ucastnici) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getRizeniId());
			preparedStatement.setObject(3, record.getDruhUcastnika());
			preparedStatement.setObject(4, record.getJmeno());
			preparedStatement.setObject(5, record.getJmenoU());
			preparedStatement.setObject(6, record.getPrijmeni());
			preparedStatement.setObject(7, record.getPrijmeniU());
			preparedStatement.setObject(8, record.getTitulPredJmenem());
			preparedStatement.setObject(9, record.getTitulZaJmenem());
			preparedStatement.setObject(10, record.getRc());
			preparedStatement.setObject(11, record.getRodnePrijmeni());
			preparedStatement.setObject(12, record.getRodinnyStav());
			preparedStatement.setObject(13, record.getObchodniJmeno());
			preparedStatement.setObject(14, record.getObchodniJmenoU());
			preparedStatement.setObject(15, record.getDic());
			preparedStatement.setObject(16, record.getIco());
			preparedStatement.setObject(17, record.getDoplnekIco());
			preparedStatement.setObject(18, record.getOverenPodpis());
			preparedStatement.setObject(19, record.getOverenProtiRs());
			preparedStatement.setObject(20, record.getOverenProtiOs());
		
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
		String delete = "DELETE FROM " + table + " WHERE *pk*";
		delete = appendPrimaryKeys(delete);
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(delete);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String appendPrimaryKeys(String delete) {
		for (int i = 0; i < primaryKeys.size(); i++) {
			delete = delete.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		delete = delete.replace(" AND *pk*", "");
		return delete;
	}
}
