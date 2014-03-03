package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradnicePolohy;

public class SouradnicePolohyOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "SOURADNICE_POLOHY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?)";

	public SouradnicePolohyOracleDatabaseJdbcExporter(
			List<SouradnicePolohy> souradnicePolohy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(souradnicePolohy, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		SouradnicePolohy record = (SouradnicePolohy) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, record.getStavDat());
		psInsert.setObject(3, record.getKatuzeKod());
		psInsert.setObject(4, record.getCisloZpmz());
		psInsert.setObject(5, record.getCisloTl());
		psInsert.setObject(6, record.getCisloBodu());
		psInsert.setObject(7, record.getUplneCislo());
		psInsert.setObject(8, record.getSouradniceY());
		psInsert.setObject(9, record.getSouradniceX());
		psInsert.setObject(10, record.getKodchbKod());
		psInsert.setObject(11, record.getKatuzeKodMer());
		psInsert.setObject(12, record.getCisloZpmzMer());
	}

}
