package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParcelyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "PARCELY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO "
			+ name
			+ "_MIN"
			+ " VALUES"
			+ "(SEQ_PARCELY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public ParcelyOracleDatabaseJdbcExporter(List<Parcely> parcely,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(parcely, name);
	}

	protected void insertRecord(String table, Object rawRecord)
			throws SQLException {
		Parcely record = (Parcely) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getPknId());
		psInsert.setObject(9, record.getParType());
		psInsert.setObject(10, record.getKatuzeKod());
		psInsert.setObject(11, record.getKatuzeKodPuv());
		psInsert.setObject(12, record.getDruhCislovaniPar());
		psInsert.setObject(13, record.getKmenoveCisloPar());
		psInsert.setObject(14, record.getZdpazeKod());
		psInsert.setObject(15, record.getPoddeleniCislaPar());
		psInsert.setObject(16, record.getDilParcely());
		psInsert.setObject(17, record.getMaplisKod());
		psInsert.setObject(18, record.getZpurvyKod());
		psInsert.setObject(19, record.getDrupozKod());
		psInsert.setObject(20, record.getZpvypaKod());
		psInsert.setObject(21, record.getTypParcely());
		psInsert.setObject(22, record.getVymeraParcely());
		psInsert.setObject(23, record.getCenaNemovitosti());
		psInsert.setObject(24, record.getDefiniciniBodPar());
		psInsert.setObject(25, record.getTelId());
		psInsert.setObject(26, record.getParId());
		psInsert.setObject(27, record.getBudId());
		psInsert.setObject(28, record.getIdentBud());
		psInsert.setObject(29, record.getSoucasti());
		psInsert.setObject(30, record.getPsId());
		psInsert.setObject(31, record.getIdentPs());
	}

	protected void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		Parcely record = (Parcely) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getPknId());
		psHisInsert.setObject(9, record.getParType());
		psHisInsert.setObject(10, record.getKatuzeKod());
		psHisInsert.setObject(11, record.getKatuzeKodPuv());
		psHisInsert.setObject(12, record.getDruhCislovaniPar());
		psHisInsert.setObject(13, record.getKmenoveCisloPar());
		psHisInsert.setObject(14, record.getZdpazeKod());
		psHisInsert.setObject(15, record.getPoddeleniCislaPar());
		psHisInsert.setObject(16, record.getDilParcely());
		psHisInsert.setObject(17, record.getMaplisKod());
		psHisInsert.setObject(18, record.getZpurvyKod());
		psHisInsert.setObject(19, record.getDrupozKod());
		psHisInsert.setObject(20, record.getZpvypaKod());
		psHisInsert.setObject(21, record.getTypParcely());
		psHisInsert.setObject(22, record.getVymeraParcely());
		psHisInsert.setObject(23, record.getCenaNemovitosti());
		psHisInsert.setObject(24, record.getDefiniciniBodPar());
		psHisInsert.setObject(25, record.getTelId());
		psHisInsert.setObject(26, record.getParId());
		psHisInsert.setObject(27, record.getBudId());
		psHisInsert.setObject(28, record.getIdentBud());
		psHisInsert.setObject(29, record.getSoucasti());
		psHisInsert.setObject(30, record.getPsId());
		psHisInsert.setObject(31, record.getIdentPs());
	}

}
