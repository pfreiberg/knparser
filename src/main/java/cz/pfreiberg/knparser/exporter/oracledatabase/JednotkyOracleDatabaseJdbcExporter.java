package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jednotky.Jednotky;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JednotkyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "JEDNOTKY";

	public JednotkyOracleDatabaseJdbcExporter(List<Jednotky> jednotky,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(jednotky, name);
	}

	public void insertRecord(String table, Object rawRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Jednotky record = (Jednotky) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getBudId());
			preparedStatement.setObject(9, record.getTypjedKod());
			preparedStatement.setObject(10, record.getCisloJednotky());
			preparedStatement.setObject(11, record.getCenaNemovitosti());
			preparedStatement.setObject(12, record.getZpvyjeKod());
			preparedStatement.setObject(13, record.getTelId());
			preparedStatement.setObject(14, record.getPodilCitatel());
			preparedStatement.setObject(15, record.getPodilJmenovatel());
			preparedStatement.setObject(16, record.getPopis());

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

	public void insertHistoricalRecord(String table, Object rawRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(SEQ_JEDNOTKY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Jednotky record = (Jednotky) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getBudId());
			preparedStatement.setObject(9, record.getTypjedKod());
			preparedStatement.setObject(10, record.getCisloJednotky());
			preparedStatement.setObject(11, record.getCenaNemovitosti());
			preparedStatement.setObject(12, record.getZpvyjeKod());
			preparedStatement.setObject(13, record.getTelId());
			preparedStatement.setObject(14, record.getPodilCitatel());
			preparedStatement.setObject(15, record.getPodilJmenovatel());
			preparedStatement.setObject(16, record.getPopis());

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
