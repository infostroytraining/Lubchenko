package ua.nure.lubchenko.filemanager.filter;

import java.io.File;

public abstract class Filter {
	private Filter nextFilter;

	public Filter(Filter nextFilter) {
		this.nextFilter = nextFilter;
	}

	public boolean conveyFile(File file) {
		boolean result = doFilter(file);
		if (result && nextFilter != null) {
			return nextFilter.conveyFile(file);
		}
		return result;
	}

	public abstract boolean doFilter(File file);

	/**
	 * @return the nextFilter
	 */
	public Filter getNextFilter() {
		return nextFilter;
	}

}
