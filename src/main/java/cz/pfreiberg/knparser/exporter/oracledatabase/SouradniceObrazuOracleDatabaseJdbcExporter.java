package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradniceObrazu;

public class SouradniceObrazuOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "SOURADNICE_OBRAZU";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?)";

	public SouradniceObrazuOracleDatabaseJdbcExporter(
			List<SouradniceObrazu> souradniceObrazu,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(souradniceObrazu, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		SouradniceObrazu record = (SouradniceObrazu) rawRecord;
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
		psInsert.setNull(11, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

}
