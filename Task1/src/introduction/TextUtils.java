package introduction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка орфографии
 * 
 * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
 * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
 * 
 * Ваша задача: исправить их ошибки.
 * 
 * Что нужно сделать:
 * 
 * 1. Каждое новое предложение должно начинаться с заглавной буквы. 2. После
 * знаков препинания (точка и запятая) должны быть пробелы.
 */
public class TextUtils {

	public String correctText(String text) {
		String result = editSpaces(text);
		result=editFirstLetters(result);
		result=editDot(result);
		return result;
	}

	private String editSpaces(String text) {
		StringBuffer result = new StringBuffer();
		Pattern pat = Pattern.compile("([,.:;?!](?!= ))(?=[a-zA-Zа-яА-ЯёЁїЇіІєЄ])");
		Matcher mat = pat.matcher(text);
		while (mat.find()) {
			mat.appendReplacement(result, mat.group().concat(" "));
		}
		mat.appendTail(result);
		return result.toString();
	}
	
	private String editDot(String text) {
		StringBuffer result = new StringBuffer();
		Pattern pat = Pattern.compile("([a-zA-Zа-яА-ЯёЁїЇіІєЄ])$(?![.?!])");
		Matcher mat = pat.matcher(text);
		while (mat.find()) {
			mat.appendReplacement(result, mat.group().concat("."));
		}
		mat.appendTail(result);
		return result.toString();
	}

	private String editFirstLetters(String text) {
		StringBuffer result = new StringBuffer();
		Pattern pat = Pattern.compile("(?<=^|[.?!] ?)[a-zA-Zа-яА-ЯіІїЇ]");
		Matcher mat = pat.matcher(text);
		while (mat.find()) {
			mat.appendReplacement(result, mat.group().toUpperCase());
		}
		mat.appendTail(result);
		return result.toString();
	}
	
	public static void main(String[] args) {
		TextUtils tu = new TextUtils();
		String testText = "some info.bla,bla,bla?useless text";
		System.out.println("Before: "+testText);
		System.out.println("After: "+tu.correctText(testText));
	}

}