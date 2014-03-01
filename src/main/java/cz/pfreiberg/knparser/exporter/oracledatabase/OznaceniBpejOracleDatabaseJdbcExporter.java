package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky.OznaceniBpej;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OznaceniBpejOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "OZNACENI_BPEJ";

	public OznaceniBpejOracleDatabaseJdbcExporter(
			List<OznaceniBpej> oznaceniBpej,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(oznaceniBpej, name);
	}

	protected void insertRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			OznaceniBpej record = (OznaceniBpej) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getTypppdKod());
			preparedStatement.setObject(9, record.getSouradniceY());
			preparedStatement.setObject(10, record.getSouradniceX());
			preparedStatement.setObject(11, record.getText());
			preparedStatement.setObject(12, record.getVelikost());
			preparedStatement.setObject(13, record.getUhel());
			preparedStatement.setObject(14, record.getBpejKod());
			preparedStatement.setObject(15, record.getKatuzeKod());
			preparedStatement.setObject(16, record.getVztaznyBod());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	protected void insertHistoricalRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(SEQ_OZNACENI_BPEJ_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			OznaceniBpej record = (OznaceniBpej) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getTypppdKod());
			preparedStatement.setObject(9, record.getSouradniceY());
			preparedStatement.setObject(10, record.getSouradniceX());
			preparedStatement.setObject(11, record.getText());
			preparedStatement.setObject(12, record.getVelikost());
			preparedStatement.setObject(13, record.getUhel());
			preparedStatement.setObject(14, record.getBpejKod());
			preparedStatement.setObject(15, record.getKatuzeKod());
			preparedStatement.setObject(16, record.getVztaznyBod());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}

	}
}
