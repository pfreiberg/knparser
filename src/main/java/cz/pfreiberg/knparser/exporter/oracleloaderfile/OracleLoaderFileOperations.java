package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.Collection;

public interface OracleLoaderFileOperations {

	public String makeControlFile();

	public void appendLoadFile(String name, String characterSet, Collection<?> lines);

}
