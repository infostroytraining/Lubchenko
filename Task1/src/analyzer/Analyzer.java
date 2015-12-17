package analyzer;

import java.io.FileNotFoundException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import analyzer.jcommander.MyJCommander;

public class Analyzer {
	public static void main(String[] args) throws FileNotFoundException {
		readInfoFromCMD(args);
	}

	public static void readInfoFromCMD(String[] args) throws FileNotFoundException {
		final String APP_HELP_INFO = "\n>>> Aplication scans and analize text from input file. \n"
				+ "Searches for the most long, frequent and duplicated words.\n\n" + "input:\n"
				+ "-i|--input sourseFileName.txt -t|--task length|duplicates|frequency [--help, --parallel]\n" + "Tasks discription:\n"
				+ "LENGTH -  Finds first three longest words and print this words along with the their\nlength sorted them in a descend order by the total number of letters each word contains\n"
				+ "DUPLICATES - finds first three words which have duplicates and print them inversely\n(e.g. map -> pam) in the upper case sorted by length in ascending order\n"
				+ "FREQUENCY - finds the most two frequent words and print them out sorted alphabetically in a reversed order.";

		MyJCommander jcom = new MyJCommander();
		try {
			new JCommander(jcom, args);
			jcom.getTask().setFileName(jcom.getInputFIleName());
			if (jcom.isParallel()) {
				jcom.getTask().executeInParallel();
			} else {
				jcom.getTask().execute();
			}
			
			if (jcom.isHelp()) {
				System.out.println(APP_HELP_INFO);
			}
		} catch (NullPointerException | ParameterException e) {
			System.err.println(">>> Wrong command!");
			System.out.println(APP_HELP_INFO);
		} catch (FileNotFoundException e) {
			System.err.println(">>> Can not find file by given dir: \"" + jcom.getInputFIleName() + "\"");
		}
	}
}
