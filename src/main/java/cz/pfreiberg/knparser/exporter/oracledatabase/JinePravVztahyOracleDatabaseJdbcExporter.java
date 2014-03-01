package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.JinePravVztahy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JinePravVztahyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "JINE_PRAV_VZTAHY";

	public JinePravVztahyOracleDatabaseJdbcExporter(
			List<JinePravVztahy> jinePravVztahy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(jinePravVztahy, name);
	}

	protected void insertRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			JinePravVztahy record = (JinePravVztahy) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getParIdPro());
			preparedStatement.setObject(9, record.getBudIdPro());
			preparedStatement.setObject(10, record.getJedIdPro());
			preparedStatement.setObject(11, record.getParIdK());
			preparedStatement.setObject(12, record.getBudIdK());
			preparedStatement.setObject(13, record.getJedIdK());
			preparedStatement.setObject(14, record.getTypravKod());
			preparedStatement.setObject(15, record.getPopisPravnihoVztahu());
			preparedStatement.setObject(16, record.getTelId());
			preparedStatement.setObject(17, record.getOpsubIdPro());
			preparedStatement.setObject(18, record.getOpsubIdK());
			preparedStatement.setObject(19, record.getPodilPohledavka());
			preparedStatement.setObject(20, record.getHjpvId());
			preparedStatement.setObject(21, VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(22, record.getRizeniIdVzniku2());
			preparedStatement.setObject(23, record.getOpsubId2Pro());
			preparedStatement.setObject(24, record.getPsIdPro());
			preparedStatement.setObject(25, record.getPopis2());
			preparedStatement.setObject(26, record.getPoradiCas());
			preparedStatement.setObject(27, record.getPoradiText());
			preparedStatement.setObject(28, VfkUtil.convertToDatabaseDate(record.getDatumUkonceni()));

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	protected void insertHistoricalRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(SEQ_JINE_PRAV_VZTAHY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			JinePravVztahy record = (JinePravVztahy) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getParIdPro());
			preparedStatement.setObject(9, record.getBudIdPro());
			preparedStatement.setObject(10, record.getJedIdPro());
			preparedStatement.setObject(11, record.getParIdK());
			preparedStatement.setObject(12, record.getBudIdK());
			preparedStatement.setObject(13, record.getJedIdK());
			preparedStatement.setObject(14, record.getTypravKod());
			preparedStatement.setObject(15, record.getPopisPravnihoVztahu());
			preparedStatement.setObject(16, record.getTelId());
			preparedStatement.setObject(17, record.getOpsubIdPro());
			preparedStatement.setObject(18, record.getOpsubIdK());
			preparedStatement.setObject(19, record.getPodilPohledavka());
			preparedStatement.setObject(20, record.getHjpvId());
			preparedStatement.setObject(21, VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(22, record.getRizeniIdVzniku2());
			preparedStatement.setObject(23, record.getOpsubId2Pro());
			preparedStatement.setObject(24, record.getPsIdPro());
			preparedStatement.setObject(25, record.getPopis2());
			preparedStatement.setObject(26, record.getPoradiCas());
			preparedStatement.setObject(27, record.getPoradiText());
			preparedStatement.setObject(28, VfkUtil.convertToDatabaseDate(record.getDatumUkonceni()));
		
			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}

	}
}
