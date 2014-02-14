package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.JinePravVztahy;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JinePravVztahyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<JinePravVztahy> jinePravVztahy;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "JINE_PRAV_VZTAHY";

	public JinePravVztahyOracleDatabaseJdbcExporter(
			List<JinePravVztahy> jinePravVztahy,
			ConnectionParameters connectionParameters) {
		this.jinePravVztahy = jinePravVztahy;
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
		int i = 0;
		for (JinePravVztahy record : jinePravVztahy) {
			i++;
			if (i % 100 == 0)
				System.out.println("JPV: " + i);
			
			primaryKeysValues = getPrimaryKeysValues(record);
			if (record.getDatumZaniku() == null) {
				processRecord(record);
			} else {
				processHistoricalRecord(record);
			}
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
						.forName("cz.pfreiberg.knparser.domain.jinepravnivztahy.JinePravVztahy");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((JinePravVztahy) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(JinePravVztahy record) {
		
		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, 
				"DATUM_VZNIKU", record.getDatumVzniku());
		
		if (newFind(parameters, Operations.lessThan, true)) {
			newDelete(parameters, Operations.lessThan, true);
			insert(name, record, true);
		} else if (newFind(parameters, Operations.greaterThanOrEqual, true)) {
			return;
		} else {
			insert(name, record, true);
		}
		
	}

	private void processHistoricalRecord(JinePravVztahy record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name + "_MIN", primaryKeys, primaryKeysValues, 
				"DATUM_VZNIKU", record.getDatumVzniku());
		
		if (!newFind(parameters, Operations.equal, true)) {
			insert(name + "_MIN", record, false);
			parameters.setTable(name);
			if (newFind(parameters, Operations.equal, true)) {
				newDelete(parameters, Operations.equal, true);
			}
		}
		
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		if (isRecord) {
			insertRecord(table, rawRecord);
		} else
			insertHistoricalRecord(table, rawRecord);
	}

	public void insertRecord(String table, Object rawRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			JinePravVztahy record = (JinePravVztahy) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getParIdPro());
			preparedStatement.setObject(9, record.getBudIdPro());
			preparedStatement.setObject(10, record.getJedIdPro());
			preparedStatement.setObject(11, record.getParIdK());
			preparedStatement.setObject(12, record.getBudIdK());
			preparedStatement.setObject(13, record.getJedIdK());
			preparedStatement.setObject(14, record.getTypravKod());
			preparedStatement.setObject(15, record.getPopisPravnihoVztahu());
			preparedStatement.setObject(16, record.getTelId());
			preparedStatement.setObject(17, record.getOpsubIdPro());
			preparedStatement.setObject(18, record.getOpsubIdK());
			preparedStatement.setObject(19, record.getPodilPohledavka());
			preparedStatement.setObject(20, record.getHjpvId());
			preparedStatement.setObject(21, VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(22, record.getRizeniIdVzniku2());
			preparedStatement.setObject(23, record.getOpsubId2Pro());
			preparedStatement.setObject(24, record.getPsIdPro());
			preparedStatement.setObject(25, record.getPopis2());
			preparedStatement.setObject(26, record.getPoradiCas());
			preparedStatement.setObject(27, record.getPoradiText());
			preparedStatement.setObject(28, VfkUtil.convertToDatabaseDate(record.getDatumUkonceni()));

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

	public void insertHistoricalRecord(String table, Object rawRecord) {

		String insert = "INSERT INTO " + table + " VALUES"
				+ "(SEQ_JINE_PRAV_VZTAHY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			JinePravVztahy record = (JinePravVztahy) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getParIdPro());
			preparedStatement.setObject(9, record.getBudIdPro());
			preparedStatement.setObject(10, record.getJedIdPro());
			preparedStatement.setObject(11, record.getParIdK());
			preparedStatement.setObject(12, record.getBudIdK());
			preparedStatement.setObject(13, record.getJedIdK());
			preparedStatement.setObject(14, record.getTypravKod());
			preparedStatement.setObject(15, record.getPopisPravnihoVztahu());
			preparedStatement.setObject(16, record.getTelId());
			preparedStatement.setObject(17, record.getOpsubIdPro());
			preparedStatement.setObject(18, record.getOpsubIdK());
			preparedStatement.setObject(19, record.getPodilPohledavka());
			preparedStatement.setObject(20, record.getHjpvId());
			preparedStatement.setObject(21, VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(22, record.getRizeniIdVzniku2());
			preparedStatement.setObject(23, record.getOpsubId2Pro());
			preparedStatement.setObject(24, record.getPsIdPro());
			preparedStatement.setObject(25, record.getPopis2());
			preparedStatement.setObject(26, record.getPoradiCas());
			preparedStatement.setObject(27, record.getPoradiText());
			preparedStatement.setObject(28, VfkUtil.convertToDatabaseDate(record.getDatumUkonceni()));
		
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
