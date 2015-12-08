package analyzer;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Analyzer {
	public static void main(String[] args) throws FileNotFoundException {
		readInfoFromCMD();
	}

	public static void readInfoFromCMD() throws FileNotFoundException {
		String fileName = "";
		Scanner scan = new Scanner(System.in, "Cp1251");
		String command = "";
		final String APP_HELP_INFO = "Aplication scans and analize text from input file. \n"
				+ "Searches for the most long, frequent and duplicated words.\n"
				+ "input:\n"
				+ "-i|--input sourseFileName.txt -t|--task length|duplicates|frequency\n"
				+ "input 'exit' to finish work.\n"
				+ "Tasks discription:\n"
				+ "LENGTH -  Finds first three longest words and print this words along with the their\nlength sorted them in a descend order by the total number of letters each word contains\n"
				+ "DUPLICATES - finds first three words which have duplicates and print them inversely\n(e.g. map -> pam) in the upper case sorted by length in ascending order\n"
				+ "FREQUENCY - finds the most two frequent words and print them out sorted alphabetically in a reversed order.";
		while (!"exit".equals(command = scan.next().toLowerCase())) {
			switch (command) {

			case ("-i"):
			case ("--input"): {
				fileName = scan.next();
				break;
			}
			case ("-t"):
			case ("--task"): {
				String task = scan.next().toLowerCase();
				switch (task) {
				case ("frequency"): {
					Tools.frequency(fileName);
					break;
				}
				case ("length"): {
					Tools.length(fileName);
					break;
				}
				case ("duplicates"): {
					Tools.duplicates(fileName);
					break;
				}
				default: {
					System.out.println("No such task :(");
				}
				}
				break;
			}
			case("--help"):{
					System.out.println(APP_HELP_INFO);
				break;
			}
			default: {System.out.println("No such command :(");}
			}
		}
		scan.close();
	}
}
