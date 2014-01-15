package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;

public class ParcelyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Parcely> parcely;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "PARCELY";

	public ParcelyOracleDatabaseJdbcExporter() {
		prefix = null;
		characterSet = null;
		output = null;
		getConnection();
	}

	public ParcelyOracleDatabaseJdbcExporter(List<Parcely> parcely,
			String prefix, String characterSet, String output) {
		this.parcely = parcely;
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;
		getConnection();

	}

	@Override
	public void getConnection() {
		// TODO Auto-generated method stub
		super.getConnection();
	}

}
