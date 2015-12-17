package analyzer.jcommander;

import com.beust.jcommander.Parameter;

import analyzer.command.Command;

public class MyJCommander {

	@Parameter(names = { "--input", "-i" })
	private String inputFIleName;

	@Parameter(names = { "--task", "-t" }, converter = CommandConverter.class)
	private Command task;

	@Parameter(names = { "--parallel","-p" }, required = false)
	private boolean parallel = false;
	
	@Parameter(names = { "--help" }, required = false)
	private boolean help = false;
	
	/**
	 * @return the inputFIleName
	 */
	public String getInputFIleName() {
		return inputFIleName;
	}

	/**
	 * @param inputFIleName
	 *            the inputFIleName to set
	 */
	public void setInputFIleName(String inputFIleName) {
		this.inputFIleName = inputFIleName;
	}

	/**
	 * @return the task
	 */
	public Command getTask() {
		return task;
	}

	/**
	 * @param task
	 *            the task to set
	 */
	public void setTask(Command task) {
		this.task = task;
	}

	/**
	 * @return the help
	 */
	public boolean isHelp() {
		return help;
	}
	
	/**
	 * @return the parallel
	 */
	public boolean isParallel() {
		return parallel;
	}
}