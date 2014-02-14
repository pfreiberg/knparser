package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.Collection;

public interface OracleLoaderFileOperations {

	public String makeControlFile();
	
	public void appendControlFile(String output, String characterSet, String controlFile);

	public void appendLoadFile(String output, String characterSet, Collection<?> lines);

}
