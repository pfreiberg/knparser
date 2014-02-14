package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.vlastnictvi.OpravSubjekty;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OpravSubjektyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<OpravSubjekty> opravSubjekty;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "OPRAV_SUBJEKTY";

	public OpravSubjektyOracleDatabaseJdbcExporter(
			List<OpravSubjekty> opravSubjekty,
			ConnectionParameters connectionParameters) {
		this.opravSubjekty = opravSubjekty;
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
		for (OpravSubjekty record : opravSubjekty) {
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
						.forName("cz.pfreiberg.knparser.domain.vlastnictvi.OpravSubjekty");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((OpravSubjekty) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(OpravSubjekty record) {
		
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

	private void processHistoricalRecord(OpravSubjekty record) {

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

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		if (isRecord) {
			insertRecord(table, rawRecord);
		} else
			insertHistoricalRecord(table, rawRecord);
	}

	public void insertRecord(String table, Object rawRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			OpravSubjekty record = (OpravSubjekty) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getIdJe1PartnerBsm());
			preparedStatement.setObject(9, record.getIdJe2PartnerBsm());
			preparedStatement.setObject(10, record.getIdZdroj());
			preparedStatement.setObject(11, record.getOpsubType());
			preparedStatement.setObject(12, record.getCharosKod());
			preparedStatement.setObject(13, record.getIco());
			preparedStatement.setObject(14, record.getDoplnekIco());
			preparedStatement.setObject(15, record.getNazev());
			preparedStatement.setObject(16, record.getNazevU());
			preparedStatement.setObject(17, record.getRodneCislo());
			preparedStatement.setObject(18, record.getTitulPredJmenem());
			preparedStatement.setObject(19, record.getJmeno());
			preparedStatement.setObject(20, record.getJmenoU());
			preparedStatement.setObject(21, record.getPrijmeni());
			preparedStatement.setObject(22, record.getPrijmeniU());
			preparedStatement.setObject(23, record.getTitulZaJmenem());
			preparedStatement.setObject(24, record.getCisloDomovni());
			preparedStatement.setObject(25, record.getCisloOrientacni());
			preparedStatement.setObject(26, record.getNazevUlice());
			preparedStatement.setObject(27, record.getCastObce());
			preparedStatement.setObject(28, record.getObec());
			preparedStatement.setObject(29, record.getOkres());
			preparedStatement.setObject(30, record.getStat());
			preparedStatement.setObject(31, record.getPsc());
			preparedStatement.setObject(32, record.getCpCe());
			preparedStatement.setObject(33, record.getMestskaCast());
			preparedStatement.setObject(34, VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(35, record.getRizeniIdVzniku2());
			preparedStatement.setObject(36, record.getKodAdrm());
			preparedStatement.setObject(37, record.getIdNadrizenePo());

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
				+ "(SEQ_OPRAV_SUBJEKTY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			OpravSubjekty record = (OpravSubjekty) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getIdJe1PartnerBsm());
			preparedStatement.setObject(9, record.getIdJe2PartnerBsm());
			preparedStatement.setObject(10, record.getIdZdroj());
			preparedStatement.setObject(11, record.getOpsubType());
			preparedStatement.setObject(12, record.getCharosKod());
			preparedStatement.setObject(13, record.getIco());
			preparedStatement.setObject(14, record.getDoplnekIco());
			preparedStatement.setObject(15, record.getNazev());
			preparedStatement.setObject(16, record.getNazevU());
			preparedStatement.setObject(17, record.getRodneCislo());
			preparedStatement.setObject(18, record.getTitulPredJmenem());
			preparedStatement.setObject(19, record.getJmeno());
			preparedStatement.setObject(20, record.getJmenoU());
			preparedStatement.setObject(21, record.getPrijmeni());
			preparedStatement.setObject(22, record.getPrijmeniU());
			preparedStatement.setObject(23, record.getTitulZaJmenem());
			preparedStatement.setObject(24, record.getCisloDomovni());
			preparedStatement.setObject(25, record.getCisloOrientacni());
			preparedStatement.setObject(26, record.getNazevUlice());
			preparedStatement.setObject(27, record.getCastObce());
			preparedStatement.setObject(28, record.getObec());
			preparedStatement.setObject(29, record.getOkres());
			preparedStatement.setObject(30, record.getStat());
			preparedStatement.setObject(31, record.getPsc());
			preparedStatement.setObject(32, record.getCpCe());
			preparedStatement.setObject(33, record.getMestskaCast());
			preparedStatement.setObject(34, VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(35, record.getRizeniIdVzniku2());
			preparedStatement.setObject(36, record.getKodAdrm());
			preparedStatement.setObject(37, record.getIdNadrizenePo());

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
		String delete = "DELETE FROM " + table + " WHERE *pk* AND " + date
				+ operation + dateValue;
		for (int i = 0; i < primaryKeys.size(); i++) {
			delete = delete.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		delete = delete.replace(" AND *pk*", "");
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
}
