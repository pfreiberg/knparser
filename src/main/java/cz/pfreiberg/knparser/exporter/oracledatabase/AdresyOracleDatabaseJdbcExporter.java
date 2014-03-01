package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Adresy;

public class AdresyOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "ADRESY";

	public AdresyOracleDatabaseJdbcExporter(List<Adresy> adresy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(adresy, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Adresy record = (Adresy) rawRecord;
			preparedStatement.setObject(1, record.getUcastId());
			preparedStatement.setObject(2, record.getTypAdresy());
			preparedStatement.setObject(3, record.getOkres());
			preparedStatement.setObject(4, record.getObec());
			preparedStatement.setObject(5, record.getCastObce());
			preparedStatement.setObject(6, record.getCisloDomovni());
			preparedStatement.setObject(7, record.getNazevUlice());
			preparedStatement.setObject(8, record.getCisloOrientacni());
			preparedStatement.setObject(9, record.getPsc());
			preparedStatement.setObject(10, record.getStat());
			preparedStatement.setObject(11, record.getTelefon());
			preparedStatement.setObject(12, record.getFax());
			preparedStatement.setObject(13, record.getMestskaCast());
			preparedStatement.setObject(14, record.getCpCe());
			preparedStatement.setObject(15, record.getKodAdrm());
		
			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}
	
}
