package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Ucastnici;

public class UcastniciOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "UCASTNICI";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public UcastniciOracleDatabaseJdbcExporter(List<Ucastnici> ucastnici,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(ucastnici, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		Ucastnici record = (Ucastnici) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, record.getRizeniId());
		psInsert.setObject(3, record.getDruhUcastnika());
		psInsert.setObject(4, record.getJmeno());
		psInsert.setObject(5, record.getJmenoU());
		psInsert.setObject(6, record.getPrijmeni());
		psInsert.setObject(7, record.getPrijmeniU());
		psInsert.setObject(8, record.getTitulPredJmenem());
		psInsert.setObject(9, record.getTitulZaJmenem());
		psInsert.setObject(10, record.getRc());
		psInsert.setObject(11, record.getRodnePrijmeni());
		psInsert.setObject(12, record.getRodinnyStav());
		psInsert.setObject(13, record.getObchodniJmeno());
		psInsert.setObject(14, record.getObchodniJmenoU());
		psInsert.setObject(15, record.getDic());
		psInsert.setObject(16, record.getIco());
		psInsert.setObject(17, record.getDoplnekIco());
		psInsert.setObject(18, record.getOverenPodpis());
		psInsert.setObject(19, record.getOverenProtiRs());
		psInsert.setObject(20, record.getOverenProtiOs());
	}

}
