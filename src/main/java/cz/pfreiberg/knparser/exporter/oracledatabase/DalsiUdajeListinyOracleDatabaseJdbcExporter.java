package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.DalsiUdajeListiny;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DalsiUdajeListinyOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "DALSI_UDAJE_LISTINY";

	public DalsiUdajeListinyOracleDatabaseJdbcExporter(List<DalsiUdajeListiny> dalsiUdajeListiny,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(dalsiUdajeListiny, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			DalsiUdajeListiny record = (DalsiUdajeListiny) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getNazev());
			preparedStatement.setObject(3, VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(4, VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		
			preparedStatement.executeUpdate();
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
