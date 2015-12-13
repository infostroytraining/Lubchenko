package analyzer.command;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DuplicatesCommand implements Command {
	String fileName;

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void execute() throws FileNotFoundException {
		System.out.println("First two words which have a duplicate (reverse, in upper case):");
		String data = Tools.loadData(fileName);
		long startTime = new Date().getTime();
		List<String> bareText = Tools.textArray(data);
		Set<String> firstDuplicatedWords = Tools.getfirstDuplicatedWords(bareText);
		String result = Tools.setToUpperReverseString(firstDuplicatedWords);
		long endTime = new Date().getTime();
		System.out.println(result);
		System.out.println("elapsed time: " + (endTime - startTime) + " millis");
		System.out.println();
	}
}