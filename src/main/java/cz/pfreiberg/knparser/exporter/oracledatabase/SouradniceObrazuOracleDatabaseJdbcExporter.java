package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradniceObrazu;

public class SouradniceObrazuOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<SouradniceObrazu> souradniceObrazu;
	private final static String name = "SOURADNICE_OBRAZU";

	public SouradniceObrazuOracleDatabaseJdbcExporter(List<SouradniceObrazu> souradniceObrazu,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.souradniceObrazu = souradniceObrazu;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (SouradniceObrazu record : souradniceObrazu) {
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

	private void processRecord(SouradniceObrazu record) {
		
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
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			SouradniceObrazu record = (SouradniceObrazu) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getStavDat());
			preparedStatement.setObject(3, record.getKatuzeKod());
			preparedStatement.setObject(4, record.getCisloZpmz());
			preparedStatement.setObject(5, record.getCisloTl());
			preparedStatement.setObject(6, record.getCisloBodu());
			preparedStatement.setObject(7, record.getUplneCislo());
			preparedStatement.setObject(8, record.getSouradniceY());
			preparedStatement.setObject(9, record.getSouradniceX());
			preparedStatement.setObject(10, record.getKodchbKod());
			preparedStatement.setNull(11, Types.STRUCT, "MDSYS.SDO_GEOMETRY");

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
