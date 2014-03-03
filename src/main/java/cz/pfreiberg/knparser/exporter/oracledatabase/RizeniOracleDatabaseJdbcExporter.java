package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Rizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RizeniOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "RIZENI";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public RizeniOracleDatabaseJdbcExporter(List<Rizeni> rizeni,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(rizeni, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		Rizeni record = (Rizeni) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, record.getTyprizKod());
		psInsert.setObject(3, record.getPoradoveCislo());
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getRok()));
		psInsert.setObject(5, record.getStav());
		psInsert.setObject(6, record.getFunkceKod());
		psInsert.setObject(7, record.getTypopeKod());
		psInsert.setObject(8, record.getFunkceKodVyznamna());
		psInsert.setObject(9, record.getTypopeKodVyznamna());
		psInsert.setObject(10, record.getUzisysUsername());
		psInsert.setObject(11, record.getUzirolNazev());
		psInsert.setObject(12, record.getOsvobozeno());
		psInsert.setObject(13, record.getHodnotaKolku());
		psInsert.setObject(14,
				VfkUtil.convertToDatabaseDate(record.getDatum()));
		psInsert.setObject(15,
				VfkUtil.convertToDatabaseDate(record.getDatum2()));
		psInsert.setObject(16, record.getPopis());
		psInsert.setObject(17,
				VfkUtil.convertToDatabaseDate(record.getDatumUzavreni()));
		psInsert.setObject(18, record.getPraresKod());
	}
}
