package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParcelyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "PARCELY";

	public ParcelyOracleDatabaseJdbcExporter(List<Parcely> parcely,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(parcely, name);
	}

	protected void insertRecord(String table, Object rawRecord)
			throws SQLException {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Parcely record = (Parcely) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getPknId());
			preparedStatement.setObject(9, record.getParType());
			preparedStatement.setObject(10, record.getKatuzeKod());
			preparedStatement.setObject(11, record.getKatuzeKodPuv());
			preparedStatement.setObject(12, record.getDruhCislovaniPar());
			preparedStatement.setObject(13, record.getKmenoveCisloPar());
			preparedStatement.setObject(14, record.getZdpazeKod());
			preparedStatement.setObject(15, record.getPoddeleniCislaPar());
			preparedStatement.setObject(16, record.getDilParcely());
			preparedStatement.setObject(17, record.getMaplisKod());
			preparedStatement.setObject(18, record.getZpurvyKod());
			preparedStatement.setObject(19, record.getDrupozKod());
			preparedStatement.setObject(20, record.getZpvypaKod());
			preparedStatement.setObject(21, record.getTypParcely());
			preparedStatement.setObject(22, record.getVymeraParcely());
			preparedStatement.setObject(23, record.getCenaNemovitosti());
			preparedStatement.setObject(24, record.getDefiniciniBodPar());
			preparedStatement.setObject(25, record.getTelId());
			preparedStatement.setObject(26, record.getParId());
			preparedStatement.setObject(27, record.getBudId());
			preparedStatement.setObject(28, record.getIdentBud());
			preparedStatement.setObject(29, record.getSoucasti());
			preparedStatement.setObject(30, record.getPsId());
			preparedStatement.setObject(31, record.getIdentPs());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	protected void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(SEQ_PARCELY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Parcely record = (Parcely) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getPknId());
			preparedStatement.setObject(9, record.getParType());
			preparedStatement.setObject(10, record.getKatuzeKod());
			preparedStatement.setObject(11, record.getKatuzeKodPuv());
			preparedStatement.setObject(12, record.getDruhCislovaniPar());
			preparedStatement.setObject(13, record.getKmenoveCisloPar());
			preparedStatement.setObject(14, record.getZdpazeKod());
			preparedStatement.setObject(15, record.getPoddeleniCislaPar());
			preparedStatement.setObject(16, record.getDilParcely());
			preparedStatement.setObject(17, record.getMaplisKod());
			preparedStatement.setObject(18, record.getZpurvyKod());
			preparedStatement.setObject(19, record.getDrupozKod());
			preparedStatement.setObject(20, record.getZpvypaKod());
			preparedStatement.setObject(21, record.getTypParcely());
			preparedStatement.setObject(22, record.getVymeraParcely());
			preparedStatement.setObject(23, record.getCenaNemovitosti());
			preparedStatement.setObject(24, record.getDefiniciniBodPar());
			preparedStatement.setObject(25, record.getTelId());
			preparedStatement.setObject(26, record.getParId());
			preparedStatement.setObject(27, record.getBudId());
			preparedStatement.setObject(28, record.getIdentBud());
			preparedStatement.setObject(29, record.getSoucasti());
			preparedStatement.setObject(30, record.getPsId());
			preparedStatement.setObject(31, record.getIdentPs());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
