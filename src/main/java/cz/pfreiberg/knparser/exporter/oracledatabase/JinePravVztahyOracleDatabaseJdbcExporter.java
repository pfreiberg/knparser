package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.JinePravVztahy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JinePravVztahyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "JINE_PRAV_VZTAHY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO "
			+ name
			+ "_MIN"
			+ " VALUES"
			+ "(SEQ_JINE_PRAV_VZTAHY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public JinePravVztahyOracleDatabaseJdbcExporter(
			List<JinePravVztahy> jinePravVztahy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(jinePravVztahy, name);
	}

	protected void insertRecord(Object rawRecord)
			throws SQLException {
		JinePravVztahy record = (JinePravVztahy) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getParIdPro());
		psInsert.setObject(9, record.getBudIdPro());
		psInsert.setObject(10, record.getJedIdPro());
		psInsert.setObject(11, record.getParIdK());
		psInsert.setObject(12, record.getBudIdK());
		psInsert.setObject(13, record.getJedIdK());
		psInsert.setObject(14, record.getTypravKod());
		psInsert.setObject(15, record.getPopisPravnihoVztahu());
		psInsert.setObject(16, record.getTelId());
		psInsert.setObject(17, record.getOpsubIdPro());
		psInsert.setObject(18, record.getOpsubIdK());
		psInsert.setObject(19, record.getPodilPohledavka());
		psInsert.setObject(20, record.getHjpvId());
		psInsert.setObject(21,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
		psInsert.setObject(22, record.getRizeniIdVzniku2());
		psInsert.setObject(23, record.getOpsubId2Pro());
		psInsert.setObject(24, record.getPsIdPro());
		psInsert.setObject(25, record.getPopis2());
		psInsert.setObject(26, record.getPoradiCas());
		psInsert.setObject(27, record.getPoradiText());
		psInsert.setObject(28,
				VfkUtil.convertToDatabaseDate(record.getDatumUkonceni()));
	}

	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		JinePravVztahy record = (JinePravVztahy) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getParIdPro());
		psHisInsert.setObject(9, record.getBudIdPro());
		psHisInsert.setObject(10, record.getJedIdPro());
		psHisInsert.setObject(11, record.getParIdK());
		psHisInsert.setObject(12, record.getBudIdK());
		psHisInsert.setObject(13, record.getJedIdK());
		psHisInsert.setObject(14, record.getTypravKod());
		psHisInsert.setObject(15, record.getPopisPravnihoVztahu());
		psHisInsert.setObject(16, record.getTelId());
		psHisInsert.setObject(17, record.getOpsubIdPro());
		psHisInsert.setObject(18, record.getOpsubIdK());
		psHisInsert.setObject(19, record.getPodilPohledavka());
		psHisInsert.setObject(20, record.getHjpvId());
		psHisInsert.setObject(21,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
		psHisInsert.setObject(22, record.getRizeniIdVzniku2());
		psHisInsert.setObject(23, record.getOpsubId2Pro());
		psHisInsert.setObject(24, record.getPsIdPro());
		psHisInsert.setObject(25, record.getPopis2());
		psHisInsert.setObject(26, record.getPoradiCas());
		psHisInsert.setObject(27, record.getPoradiText());
		psHisInsert.setObject(28,
				VfkUtil.convertToDatabaseDate(record.getDatumUkonceni()));
	}
}
