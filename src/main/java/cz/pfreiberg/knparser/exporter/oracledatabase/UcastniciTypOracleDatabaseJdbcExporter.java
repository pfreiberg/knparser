package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.UcastniciTyp;

public class UcastniciTypOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<UcastniciTyp> ucastniciTyp;
	private final static String name = "UCASTNICI_TYP";

	public UcastniciTypOracleDatabaseJdbcExporter(List<UcastniciTyp> ucastniciTyp,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.ucastniciTyp = ucastniciTyp;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (UcastniciTyp record : ucastniciTyp) {
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

	private void processRecord(UcastniciTyp record) {
		
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
				+ "(?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			UcastniciTyp record = (UcastniciTyp) rawRecord;
			preparedStatement.setObject(1, record.getUcastId());
			preparedStatement.setObject(2, record.getTypucaKod());

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
