package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParcelyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Parcely> parcely;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "PARCELY";

	public ParcelyOracleDatabaseJdbcExporter(List<Parcely> parcely,
			ConnectionParameters connectionParameters) {
		this.parcely = parcely;
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
		for (Parcely record : parcely) {
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
						.forName("cz.pfreiberg.knparser.domain.nemovitosti.Parcely");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((Parcely) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(Parcely record) {
		
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

	private void processHistoricalRecord(Parcely record) {

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
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Parcely record = (Parcely) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getPknId());
			preparedStatement.setObject(9, record.getParType());
			preparedStatement.setObject(10, record.getKatuzeKod());
			preparedStatement.setObject(11, record.getKatuzeKodPuv());
			preparedStatement.setObject(12, record.getDruhCislovaniPar());
			preparedStatement.setObject(13, record.getKmenoveCisloPar());
			preparedStatement.setObject(14, record.getZdpazeKod());
			preparedStatement.setObject(15, record.getPoddeleniCislaPar());
			preparedStatement.setObject(16, record.getDilParcely());
			preparedStatement.setObject(17, record.getMaplisKod());
			preparedStatement.setObject(18, record.getZpurvyKod());
			preparedStatement.setObject(19, record.getDrupozKod());
			preparedStatement.setObject(20, record.getZpvypaKod());
			preparedStatement.setObject(21, record.getTypParcely());
			preparedStatement.setObject(22, record.getVymeraParcely());
			preparedStatement.setObject(23, record.getCenaNemovitosti());
			preparedStatement.setObject(24, record.getDefiniciniBodPar());
			preparedStatement.setObject(25, record.getTelId());
			preparedStatement.setObject(26, record.getParId());
			preparedStatement.setObject(27, record.getBudId());
			preparedStatement.setObject(28, record.getIdentBud());
			preparedStatement.setObject(29, record.getSoucasti());
			preparedStatement.setObject(30, record.getPsId());
			preparedStatement.setObject(31, record.getIdentPs());

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

		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(SEQ_PARCELY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Parcely record = (Parcely) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getPknId());
			preparedStatement.setObject(9, record.getParType());
			preparedStatement.setObject(10, record.getKatuzeKod());
			preparedStatement.setObject(11, record.getKatuzeKodPuv());
			preparedStatement.setObject(12, record.getDruhCislovaniPar());
			preparedStatement.setObject(13, record.getKmenoveCisloPar());
			preparedStatement.setObject(14, record.getZdpazeKod());
			preparedStatement.setObject(15, record.getPoddeleniCislaPar());
			preparedStatement.setObject(16, record.getDilParcely());
			preparedStatement.setObject(17, record.getMaplisKod());
			preparedStatement.setObject(18, record.getZpurvyKod());
			preparedStatement.setObject(19, record.getDrupozKod());
			preparedStatement.setObject(20, record.getZpvypaKod());
			preparedStatement.setObject(21, record.getTypParcely());
			preparedStatement.setObject(22, record.getVymeraParcely());
			preparedStatement.setObject(23, record.getCenaNemovitosti());
			preparedStatement.setObject(24, record.getDefiniciniBodPar());
			preparedStatement.setObject(25, record.getTelId());
			preparedStatement.setObject(26, record.getParId());
			preparedStatement.setObject(27, record.getBudId());
			preparedStatement.setObject(28, record.getIdentBud());
			preparedStatement.setObject(29, record.getSoucasti());
			preparedStatement.setObject(30, record.getPsId());
			preparedStatement.setObject(31, record.getIdentPs());

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
