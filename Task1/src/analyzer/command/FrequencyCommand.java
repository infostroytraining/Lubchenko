package analyzer.command;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import analyzer.command.functionalinterface.CommandFunction;

public class FrequencyCommand  implements Command{
	String fileName;
	
	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
//	private CommandFunction oldCommandImplementatin = () -> {
//		System.out.println("Two most common words in the file:");
//		String data = Tools.loadData(fileName);
//		long startTime = new Date().getTime();
//		List<String> bareText = Tools.textArray(data);
//		List<Entry<String, Integer>> mostFrequence = Tools.getFrequensy(bareText);
//		String result = Tools.entrySetToString(mostFrequence);
//		long endTime = new Date().getTime();
//		System.out.println(result);
//		System.out.println("elapsed time: " + (endTime - startTime) + " millis");
//		System.out.println();
//	};
	
	private CommandFunction commandImplementatin = () -> {
		
		System.out.println("Two most common words in the file:");
		String data = Tools.loadData(fileName);
		long startTime = new Date().getTime();
		List<String> bareText = Arrays.asList(data.toLowerCase()
				.split("[^(a-zA-Zа-яА-ЯЁёіІїІєЄ0-9)]"));
		bareText = bareText.stream().parallel()
				.filter(item -> !item.isEmpty())
				.parallel()
				.collect(Collectors.toList());
		List<Entry<String, Integer>> wordAndFrequensy = Tools.getFrequensy(bareText);
		String result = Tools.entrySetToString(wordAndFrequensy);
		long endTime = new Date().getTime();
		System.out.println(result);
		System.out.println("elapsed time: " + (endTime - startTime) + " millis");
		System.out.println();
	};
	
	private CommandFunction parallelCommandImplementatin = () -> {
	
	};

	@Override
	public void execute() throws FileNotFoundException {
		callLambdaMethod(commandImplementatin);
	}

	@Override
	public void executeInParallel() throws FileNotFoundException {
		callLambdaMethod(parallelCommandImplementatin);
	}
	
	private void callLambdaMethod(CommandFunction instanse) throws FileNotFoundException{
		instanse.execute();
	}
}