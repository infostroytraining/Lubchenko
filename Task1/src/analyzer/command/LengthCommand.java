package analyzer.command;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import analyzer.command.functionalinterface.CommandFunction;

public class LengthCommand implements Command {
	String fileName;

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private CommandFunction commandImplementatin = () -> {
//		System.out.println("Three longest words:");
//		String data = Tools.loadData(fileName);
//		long startTime = new Date().getTime();
//		List<String> bareText = Tools.textArray(data);
//		List<Entry<String, Integer>> longestWords = Tools.getLongestWords(bareText);
//		String result = Tools.entrySetToString(longestWords);
//		long endTime = new Date().getTime();
//		System.out.println(result);
//		System.out.println("elapsed time: " + (endTime - startTime) + " millis");
//		System.out.println();
	};

	private CommandFunction parallelCommandImplementatin = () -> {
		String data = Tools.loadData(fileName);
		long startTime = new Date().getTime();
		List<String> bareText = Arrays.asList(data.toLowerCase()
				.split("[^(a-zA-Zа-яА-ЯЁёіІїІєЄ0-9)]"));
		bareText = bareText.stream()
				.filter(item -> !item.isEmpty())
				.collect(Collectors.toList());
		
		List<String> values = 
				bareText.stream()
			.sorted(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return b.length() - a.length();
			}
		})
			.limit(3)
			.collect(Collectors.toList());
		
		values.spliterator().forEachRemaining(item -> System.out.println(item +" ==> "+item.length()));
		long endTime = new Date().getTime();
		System.out.println("elapsed time: " + (endTime - startTime) + " millis");
	};

	@Override
	public void execute() throws FileNotFoundException {
		callLambdaMethod(commandImplementatin);
	}

	@Override
	public void executeInParallel() throws FileNotFoundException {
		callLambdaMethod(parallelCommandImplementatin);
	}

	private void callLambdaMethod(CommandFunction instanse) throws FileNotFoundException {
		instanse.execute();
	}
}
