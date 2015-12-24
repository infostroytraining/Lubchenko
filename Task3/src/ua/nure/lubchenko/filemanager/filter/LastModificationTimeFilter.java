package ua.nure.lubchenko.filemanager.filter;

import java.io.File;
import java.time.Instant;

public class LastModificationTimeFilter extends Filter {
	private Instant from;
	private Instant to;

	public LastModificationTimeFilter(Filter nextFilter, Instant from, Instant to) {
		super(nextFilter);
		this.from = from;
		this.to = to;
	}

	@Override
	public boolean doFilter(File file) {
		if (file != null) {
			Instant modificationDate = Instant.ofEpochMilli(file.lastModified());
			System.out.println(modificationDate.isAfter(from) && modificationDate.isBefore(to));
			return modificationDate.isAfter(from) && modificationDate.isBefore(to);
		}
		return false;
	}
}
