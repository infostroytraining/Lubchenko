package analyzer.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	private static final String ENCODING = "UTF-8";

	 static List<Entry<String, Integer>> getFrequensy(List<String> aList) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String element : aList) {
			int frequency = 0;
			for (String listElem : aList) {
				if (element.equals(listElem)) {
					frequency++;
				}
			}
			map.put(element, frequency);
		}

		List<Entry<String, Integer>> valuesAndFrequences = new ArrayList<Entry<String, Integer>>(map.entrySet());
		sortByValue(valuesAndFrequences);
		List<Entry<String, Integer>> mostFrequence;
		if (valuesAndFrequences.size() < 2) {
			mostFrequence = valuesAndFrequences.subList(0, valuesAndFrequences.size());
		} else {
			mostFrequence = valuesAndFrequences.subList(0, 2);
		}
		sortByABC(mostFrequence);
		return mostFrequence;
	}

	 static List<Entry<String, Integer>> getLongestWords(List<String> aList) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String element : aList) {
			map.put(element, element.length());
		}

		List<Entry<String, Integer>> valuesAndLengths = new ArrayList<Entry<String, Integer>>(map.entrySet());
		sortByValue(valuesAndLengths);
		List<Entry<String, Integer>> longest;
		if (valuesAndLengths.size() < 3) {
			longest = valuesAndLengths.subList(0, valuesAndLengths.size());
		} else {
			longest = valuesAndLengths.subList(0, 3);
		}
		sortByValue(longest);
		return longest;
	}

	 static Set<String> getfirstDuplicatedWords(List<String> aList) {
		Set<String> duplicates = new HashSet<String>();
		for (String element : aList) {
			int counter = 0;
			for (String listElem : aList) {
				if (element.equals(listElem)) {
					counter++;
				}
				if (counter == 2) {
					duplicates.add(element);
					break;
				}
			}
			if (duplicates.size() == 3) {
				break;
			}
		}
		return duplicates;
	}

	 static List<String> textArray(String text) {
		List<String> bareText = new ArrayList<String>();
		Pattern pat = Pattern.compile("([a-zA-Zа-яА-ЯЁёіІїІєЄ0-9]+)");
		Matcher mat = pat.matcher(text);
		while (mat.find()) {
			bareText.add(mat.group().toLowerCase());
		}
		return bareText;
	}

	 static String loadData(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName), ENCODING);
		StringBuilder sb = new StringBuilder();
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine()).append(System.lineSeparator());
		}
		scanner.close();
		return sb.toString().trim();
	}

	 static void sortByValue(List<Entry<String, Integer>> list) {
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				if (a.getValue().equals(b.getValue())) {
					return a.getKey().compareTo(b.getKey());
				}
				return b.getValue() - a.getValue();
			}
		});
	}

	static void sortByABC(List<Entry<String, Integer>> list) {
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return b.getKey().compareTo(a.getKey());

			}
		});
	}

	public static String entrySetToString(List<Entry<String, Integer>> aList) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Integer> entry : aList) {
			sb.append(entry.getKey());
			sb.append(" ==> ");
			sb.append(entry.getValue());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public static String setToUpperReverseString(Set<String> aSet) {
		StringBuilder sb = new StringBuilder();
		StringBuilder reverse = new StringBuilder();
		for (String element : aSet) {
			reverse.append(element.toUpperCase());
			sb.append(reverse.reverse());
			sb.append(System.lineSeparator());
			reverse.setLength(0);
		}
		return sb.toString();
	}
}
