package ua.nure.lubchenko.filemanager.filter;

import java.io.File;

public class SizeFilter extends Filter {

	private long minSize;
	private long maxSize;

	public SizeFilter(Filter nextFilter, long minSize, long maxSize) {
		super(nextFilter);
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	@Override
	public boolean doFilter(File file) {
		if (file != null) {
			return file.length() <= maxSize && file.length() >= minSize;
		}
		return false;
	}

}
