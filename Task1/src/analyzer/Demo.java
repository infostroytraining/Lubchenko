package analyzer;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Demo {
	private static final String ENCODING = "Cp1251";

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		System.setIn(new ByteArrayInputStream("-i input.txt --task length --input input.txt -t duplicates exit".getBytes(ENCODING)));
		Analyzer.main(args);
	}
}
