package analyzer.command;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

public class FrequencyCommand  implements Command{
	String fileName;
	
	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void execute() throws FileNotFoundException {
			System.out.println("Two most common words in the file:");
			String data = Tools.loadData(fileName);
			long startTime = new Date().getTime();
			List<String> bareText = Tools.textArray(data);
			List<Entry<String, Integer>> mostFrequence = Tools.getFrequensy(bareText);
			String result = Tools.entrySetToString(mostFrequence);
			long endTime = new Date().getTime();
			System.out.println(result);
			System.out.println("elapsed time: " + (endTime - startTime) + " millis");
			System.out.println();
	}
}