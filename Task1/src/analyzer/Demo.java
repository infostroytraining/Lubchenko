package analyzer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Demo {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {

		Analyzer.main(new String[] { "-i", "input.txt", "--task", "length" });

		// wrong file name case
		Analyzer.main(new String[] { "-i", "input111.txt", "--task", "frequency" });

		// wrong command case
		Analyzer.main(new String[] { "-i", "input.txt", "--task", "lalala" });
	}
}