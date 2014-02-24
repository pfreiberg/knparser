package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.vlastnictvi.Vlastnictvi;
import cz.pfreiberg.knparser.util.VfkUtil;

public class VlastnictviOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "VLASTNICTVI";

	public VlastnictviOracleDatabaseJdbcExporter(List<Vlastnictvi> vlastnictvi,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(vlastnictvi, name);
	}

	protected void insertRecord(String table, Object rawRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Vlastnictvi record = (Vlastnictvi) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getOpsubId());
			preparedStatement.setObject(9, record.getTypravKod());
			preparedStatement.setObject(10, record.getTelId());
			preparedStatement.setObject(11, record.getPodilCitatel());
			preparedStatement.setObject(12, record.getPodilJmenovatel());
			preparedStatement.setObject(13,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(14, record.getRizeniIdVzniku2());
			preparedStatement.setObject(15, record.getParId());
			preparedStatement.setObject(16, record.getBudId());
			preparedStatement.setObject(17, record.getJedId());
			preparedStatement.setObject(18, record.getPsId());

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

	protected void insertHistoricalRecord(String table, Object rawRecord) {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(SEQ_VLASTNICTVI_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Vlastnictvi record = (Vlastnictvi) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getOpsubId());
			preparedStatement.setObject(9, record.getTypravKod());
			preparedStatement.setObject(10, record.getTelId());
			preparedStatement.setObject(11, record.getPodilCitatel());
			preparedStatement.setObject(12, record.getPodilJmenovatel());
			preparedStatement.setObject(13,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(14, record.getRizeniIdVzniku2());
			preparedStatement.setObject(15, record.getParId());
			preparedStatement.setObject(16, record.getBudId());
			preparedStatement.setObject(17, record.getJedId());
			preparedStatement.setObject(18, record.getPsId());

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
