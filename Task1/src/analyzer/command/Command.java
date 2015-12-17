package analyzer.command;

import java.io.FileNotFoundException;

public interface Command {
	
	public void execute() throws FileNotFoundException;
	public void executeInParallel() throws FileNotFoundException;
	public  void setFileName(String fileName);
}
