package analyzer.command.functionalinterface;

import java.io.FileNotFoundException;

@FunctionalInterface
public interface CommandFunction {
	public void execute() throws FileNotFoundException;
}
