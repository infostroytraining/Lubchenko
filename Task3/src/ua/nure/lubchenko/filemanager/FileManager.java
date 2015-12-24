package ua.nure.lubchenko.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.format.DateTimeParseException;

import ua.nure.lubchenko.filemanager.filter.ExtentionFilter;
import ua.nure.lubchenko.filemanager.filter.Filter;
import ua.nure.lubchenko.filemanager.filter.LastModificationTimeFilter;
import ua.nure.lubchenko.filemanager.filter.NameFilter;
import ua.nure.lubchenko.filemanager.filter.SizeFilter;

public class FileManager {

	/**
	 * Возвращает список файлов из каталога,директория которого передана в
	 * параметре
	 */
	public static File[] getFielsFromDir(String dir) {
		File[] files = new File(dir).listFiles();

		return files;
	}

	/**
	 * Возвращает список файлов из текущего каталога
	 */
	public static File[] getFielsFromDir() {
		File[] files = new File(".").listFiles();

		return files;
	}

	/**
	 * Запрашивает у пользователя параметры поиска и выводит на экран
	 * фильтрованый список файлов
	 * 
	 * @throws IOException
	 */
	public static void filterFiles() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Filter filter = null;
		System.out.println("Введите директорию для поиска файла, или нажмите ENTER что бы искать в текущем каталоге.");
		String dir = br.readLine();

		// получаем список всех файлов из казанной дирректории
		// если поле дирректория пустое ищем в текущем каталоге
		File[] files = dir.trim() == "" ? getFielsFromDir() : getFielsFromDir(dir);
		if (files == null || files.length == 0) {
			if (!someErrorOccuredTryAgain("Имя каталога указано неверно или он пуст", br)) {
				return;
			}
		}

		System.out.println(">>Искать по имени файла?  ( + / - )");
		if (br.readLine().trim().equals("+")) {
			System.out.println(">Введите имя");
			String name = br.readLine();
			filter = new NameFilter(filter, name);
		}

		System.out.println(">>Искать по расширению файла?  ( + / - )");
		if (br.readLine().trim().equals("+")) {
			System.out.println(">Введите расширение");
			String extention = br.readLine();
			filter = new ExtentionFilter(filter, extention);
		}

		System.out.println(">>Искать по диапозону размера файла?  ( + / - )");
		if (br.readLine().trim().equals("+")) {
			System.out.print(">Укажите минимальный размер в байтах: ");
			long min = Integer.parseInt(br.readLine());
			System.out.print(">Укажите максимальный размер в байтах: ");
			long max = Integer.parseInt(br.readLine());
			filter = new SizeFilter(filter, min, max);
		}

		System.out.println(">>Искать по диапозону последней модификации файла?  ( + / - )");
		if (br.readLine().trim().equals("+")) {
			System.out.println(
					">Укажите начало диапазона времени последней модификации файла в формате \"yyyy-MM-dd H:mm\"");
			String from = br.readLine();
			System.out.println(
					">Укажите конец диапазона времени последней модификации файла в формате \"yyyy-MM-dd H:mm\"");
			String to = br.readLine();
			try {
				filter = new LastModificationTimeFilter(null, convertDateToLong(from), convertDateToLong(to));
			} catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
				if (!someErrorOccuredTryAgain("Имя каталога указано неверно или он пуст", br)) {
					return;
				}
			}

		}
		try {
			System.out.println("======Результаты поиска=====");
			for (File currentFile : files) {
				System.out.println(currentFile.getName() + "       " + filter.conveyFile(currentFile));
				if (filter.conveyFile(currentFile)) {
					System.out.println(currentFile + "   [" + currentFile.length() + "  байт]");
				}
			}
		} catch (NullPointerException e) {
			if (!someErrorOccuredTryAgain("Имя каталога указано неверно или он пуст", br)) {
				return;
			}
		}
	}

	private static Instant convertDateToLong(String dateString) {
		Instant dateInst = Instant
				.parse(String.format("%sT%s:00.00z", dateString.split(" ")[0], dateString.split(" ")[1])); // bullshit

		return dateInst;
	}

	private static boolean someErrorOccuredTryAgain(String errorMessage, BufferedReader br) throws IOException {
		System.out.println(errorMessage + ", попробовать снова? ( + / - )");
		if (br.readLine().trim().equals("+")) {
			filterFiles();
			return true;
		}
		return false;
	}
}
