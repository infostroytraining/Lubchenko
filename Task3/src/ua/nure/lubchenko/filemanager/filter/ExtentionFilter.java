package ua.nure.lubchenko.filemanager.filter;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtentionFilter extends Filter {
	private String extention;

	public ExtentionFilter(Filter nextFilter, String extention) {
		super(nextFilter);
		this.extention = extention;
	}

	@Override
	public boolean doFilter(File file) {
		if (file != null) {
			Pattern pattern = Pattern.compile("\\." + extention + "$");
			Matcher matcher = pattern.matcher(file.getName());
			return matcher.find();
		}
		return false;
	}

}
