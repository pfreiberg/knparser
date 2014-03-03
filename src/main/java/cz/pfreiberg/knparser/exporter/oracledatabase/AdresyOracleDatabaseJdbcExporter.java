package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Adresy;

public class AdresyOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "ADRESY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public AdresyOracleDatabaseJdbcExporter(List<Adresy> adresy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(adresy, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		Adresy record = (Adresy) rawRecord;
		psInsert.setObject(1, record.getUcastId());
		psInsert.setObject(2, record.getTypAdresy());
		psInsert.setObject(3, record.getOkres());
		psInsert.setObject(4, record.getObec());
		psInsert.setObject(5, record.getCastObce());
		psInsert.setObject(6, record.getCisloDomovni());
		psInsert.setObject(7, record.getNazevUlice());
		psInsert.setObject(8, record.getCisloOrientacni());
		psInsert.setObject(9, record.getPsc());
		psInsert.setObject(10, record.getStat());
		psInsert.setObject(11, record.getTelefon());
		psInsert.setObject(12, record.getFax());
		psInsert.setObject(13, record.getMestskaCast());
		psInsert.setObject(14, record.getCpCe());
		psInsert.setObject(15, record.getKodAdrm());
	}

}
