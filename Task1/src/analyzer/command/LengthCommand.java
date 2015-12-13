package analyzer.command;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

public class LengthCommand implements Command {
	String fileName;

	@Override
	public void execute() throws FileNotFoundException {
		System.out.println("Three longest words:");
		String data = Tools.loadData(fileName);
		long startTime = new Date().getTime();
		List<String> bareText = Tools.textArray(data);
		List<Entry<String, Integer>> longestWords = Tools.getLongestWords(bareText);
		String result = Tools.entrySetToString(longestWords);
		long endTime = new Date().getTime();
		System.out.println(result);
		System.out.println("elapsed time: " + (endTime - startTime) + " millis");
		System.out.println();
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
