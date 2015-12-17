package analyzer.command;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import analyzer.command.functionalinterface.CommandFunction;

public class DuplicatesCommand implements Command {
	String fileName;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
//	private CommandFunction oldCommandImplementatin = () -> {
//		System.out.println("First two words which have a duplicate (reverse, in upper case):");
//		String data = Tools.loadData(fileName);
//		long startTime = new Date().getTime();
//		List<String> bareText = Tools.textArray(data);
//		Set<String> firstDuplicatedWords = Tools.getfirstDuplicatedWords(bareText);
//		String result = Tools.setToUpperReverseString(firstDuplicatedWords);
//		long endTime = new Date().getTime();
//		System.out.println(result);
//		System.out.println("elapsed time: " + (endTime - startTime) + " millis");
//		System.out.println();
//	};
	private CommandFunction commandImplementatin = () -> {
		System.out.println("First three words which have a duplicate (reverse, in upper case):");
		String data = Tools.loadData(fileName);
		long startTime = new Date().getTime();
		List<String> bareText = Arrays.asList(data.toLowerCase()
				.split("[^(a-zA-Zа-яА-ЯЁёіІїІєЄ0-9)]"));
		
		bareText = bareText.stream()
				.filter(item -> !item.isEmpty())
				.collect(Collectors.toList());		
		
		Tools.getfirstDuplicatedWords(bareText).stream()
		.map(item->new StringBuilder(item)
				.reverse()
				.toString()
				.toUpperCase())
		.forEach(System.out::println);
		long endTime = new Date().getTime();
		System.out.println("elapsed time: " + (endTime - startTime) + " millis");
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