package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.RList;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RListOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {
	
	private final static String name = "R_LIST";

	public RListOracleDatabaseJdbcExporter(List<RList> rList,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(rList, name);
	}

	public void insertRecord(String table, Object rawRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			RList record = (RList) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getListinId());
			preparedStatement.setObject(9, record.getParId());
			preparedStatement.setObject(10, record.getBudId());
			preparedStatement.setObject(11, record.getJedId());
			preparedStatement.setObject(12, record.getOpsubId());
			preparedStatement.setObject(13, record.getJpvId());
			preparedStatement.setObject(14,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(15, record.getRizeniIdVzniku2());
			preparedStatement.setObject(16, record.getPsId());

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
				+ "(SEQ_R_LIST_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			RList record = (RList) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getListinId());
			preparedStatement.setObject(9, record.getParId());
			preparedStatement.setObject(10, record.getBudId());
			preparedStatement.setObject(11, record.getJedId());
			preparedStatement.setObject(12, record.getOpsubId());
			preparedStatement.setObject(13, record.getJpvId());
			preparedStatement.setObject(14,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(15, record.getRizeniIdVzniku2());
			preparedStatement.setObject(16, record.getPsId());

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
