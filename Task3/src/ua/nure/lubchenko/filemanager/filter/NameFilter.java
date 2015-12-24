package ua.nure.lubchenko.filemanager.filter;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameFilter extends Filter {

	private String partOfName;

	public NameFilter(Filter nextFilter, String partOfName) {
		super(nextFilter);
		this.partOfName = partOfName;
	}

	@Override
	public boolean doFilter(File file) {
		if (file != null) {
			Pattern pattern = Pattern.compile(partOfName);
			Matcher matcher = pattern.matcher(file.getName());
			return matcher.find();
		}
		return false;
	}
}
