package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.bonitnidilparcely.BonitDilyParc;
import cz.pfreiberg.knparser.util.VfkUtil;

public class BonitDilyParcOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "BONIT_DILY_PARC";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES" + "(SEQ_BONIT_DILY_PARC_MIN.nextval,?,?,?,?,?,?,?,?,?)";

	public BonitDilyParcOracleDatabaseJdbcExporter(
			List<BonitDilyParc> bonitDilyParc,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(bonitDilyParc, name);
	}

	@Override
	protected void insertRecord(Object rawRecord)
			throws SQLException {
		BonitDilyParc record = (BonitDilyParc) rawRecord;
		psInsert.setObject(1, 0);
		psInsert.setObject(2,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(4, 0);
		psInsert.setObject(5, record.getRizeniIdVzniku());
		psInsert.setObject(6, record.getRizeniIdZaniku());
		psInsert.setObject(7, record.getParId());
		psInsert.setObject(8, record.getBpejKod());
		psInsert.setObject(9, record.getVymera());
	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		BonitDilyParc record = (BonitDilyParc) rawRecord;
		psHisInsert.setObject(1, 0);
		psHisInsert.setObject(2,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(4, 0);
		psHisInsert.setObject(5, record.getRizeniIdVzniku());
		psHisInsert.setObject(6, record.getRizeniIdZaniku());
		psHisInsert.setObject(7, record.getParId());
		psHisInsert.setObject(8, record.getBpejKod());
		psHisInsert.setObject(9, record.getVymera());
	}

}
