package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.UcastniciTyp;

public class UcastniciTypOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "UCASTNICI_TYP";

	public UcastniciTypOracleDatabaseJdbcExporter(
			List<UcastniciTyp> ucastniciTyp,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(ucastniciTyp, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			UcastniciTyp record = (UcastniciTyp) rawRecord;
			preparedStatement.setObject(1, record.getUcastId());
			preparedStatement.setObject(2, record.getTypucaKod());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
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
