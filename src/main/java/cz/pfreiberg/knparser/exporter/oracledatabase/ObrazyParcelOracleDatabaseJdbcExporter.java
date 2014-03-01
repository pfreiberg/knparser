package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyParcel;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyParcelOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private static final String name = "OBRAZY_PARCEL";

	public ObrazyParcelOracleDatabaseJdbcExporter(
			List<ObrazyParcel> obrazyParcel,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(obrazyParcel, name);
	}

	public void insertRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			ObrazyParcel record = (ObrazyParcel) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getStavDat());
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
			preparedStatement.setObject(13, record.getParId());
			preparedStatement.setObject(14, record.getOparType());
			preparedStatement.setObject(15, record.getVztaznyBod());
			preparedStatement.setObject(16, record.getUhel());
			preparedStatement.setNull(17, Types.STRUCT, "MDSYS.SDO_GEOMETRY");

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	public void insertHistoricalRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(SEQ_OBRAZY_PARCEL_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			ObrazyParcel record = (ObrazyParcel) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getStavDat());
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
			preparedStatement.setObject(13, record.getParId());
			preparedStatement.setObject(14, record.getOparType());
			preparedStatement.setObject(15, record.getVztaznyBod());
			preparedStatement.setObject(16, record.getUhel());
			preparedStatement.setNull(17, Types.STRUCT, "MDSYS.SDO_GEOMETRY");

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}

	}

}
