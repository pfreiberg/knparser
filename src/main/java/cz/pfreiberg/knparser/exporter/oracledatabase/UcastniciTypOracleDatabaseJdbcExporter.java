package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.UcastniciTyp;

public class UcastniciTypOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "UCASTNICI_TYP";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?)";

	public UcastniciTypOracleDatabaseJdbcExporter(
			List<UcastniciTyp> ucastniciTyp,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(ucastniciTyp, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		UcastniciTyp record = (UcastniciTyp) rawRecord;
		psInsert.setObject(1, record.getUcastId());
		psInsert.setObject(2, record.getTypucaKod());
	}

}
