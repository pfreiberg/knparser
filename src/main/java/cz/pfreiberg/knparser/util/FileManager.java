package cz.pfreiberg.knparser.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

/**
 * Vytváří soubory pro ukládání dat a konfiguračních souborů. Následně tyto
 * soubory naplní daty.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class FileManager {

	private final static String END_OF_LINE = "\n";

	public static void writeToDataFile(File file, String encoding,
			Collection<?> lines) throws IOException {
		OutputStream output = null;
		try {

			boolean isNotFirst = file.exists();
			output = FileUtils.openOutputStream(file, true);
			BufferedOutputStream buffer = new BufferedOutputStream(output);
			writeData(lines, buffer, Charset.forName(encoding), isNotFirst);
			buffer.flush();
			output.close();
		} finally {
			output.close();
		}
	}

	public static void writeToConfigFile(File file, String data, String encoding)
			throws IOException {
		OutputStream output = null;
		try {
			output = FileUtils.openOutputStream(file, true);
			writeConfig(data, output, Charset.forName(encoding));
			output.close();
		} finally {
			output.close();
		}
	}

	private static void writeData(Collection<?> data, OutputStream output,
			Charset encoding, boolean isNotFirst) throws IOException {

		if (data.size() == 0) {
			return;
		}

		if (isNotFirst) {
			output.write(END_OF_LINE.getBytes(encoding));
		}

		Iterator<?> itr = data.iterator();
		while (itr.hasNext()) {

			Object line = itr.next();

			if (line != null) {
				output.write(line.toString().getBytes(encoding));
			}

			if (itr.hasNext()) {
				output.write(END_OF_LINE.getBytes(encoding));
			}
		}

	}

	private static void writeConfig(String config, OutputStream output,
			Charset encoding) throws IOException {
		if (config != null) {
			output.write(config.getBytes(encoding));
		}
	}

}
