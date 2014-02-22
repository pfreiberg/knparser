package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Adresy;

public class AdresyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Adresy> adresy;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "ADRESY";

	public AdresyOracleDatabaseJdbcExporter(List<Adresy> adresy,
			ConnectionParameters connectionParameters) {
		this.adresy = adresy;
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
		for (Adresy record : adresy) {
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

	private void processRecord(Adresy record) {
		
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
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Adresy record = (Adresy) rawRecord;
			preparedStatement.setObject(1, record.getUcastId());
			preparedStatement.setObject(2, record.getTypAdresy());
			preparedStatement.setObject(3, record.getOkres());
			preparedStatement.setObject(4, record.getObec());
			preparedStatement.setObject(5, record.getCastObce());
			preparedStatement.setObject(6, record.getCisloDomovni());
			preparedStatement.setObject(7, record.getNazevUlice());
			preparedStatement.setObject(8, record.getCisloOrientacni());
			preparedStatement.setObject(9, record.getPsc());
			preparedStatement.setObject(10, record.getStat());
			preparedStatement.setObject(11, record.getTelefon());
			preparedStatement.setObject(12, record.getFax());
			preparedStatement.setObject(13, record.getMestskaCast());
			preparedStatement.setObject(14, record.getCpCe());
			preparedStatement.setObject(15, record.getKodAdrm());
		
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
