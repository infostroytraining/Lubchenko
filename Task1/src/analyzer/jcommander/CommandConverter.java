package analyzer.jcommander;

import com.beust.jcommander.IStringConverter;

import analyzer.command.Command;
import analyzer.command.DuplicatesCommand;
import analyzer.command.FrequencyCommand;
import analyzer.command.LengthCommand;

public class CommandConverter implements IStringConverter<Command> {

	@Override
	public Command convert(String value) {

		Command convertedValue = null;

		switch (value) {
		case "length": {
			convertedValue = new LengthCommand();
			break;
		}
		case "frequency": {
			convertedValue = new FrequencyCommand();
			break;
		}
		case "duplicates": {
			convertedValue = new DuplicatesCommand();
			break;
		}
		default: {
			System.out.println(">>> Value " + value + "can not be converted to Command. "
					+ "Available values are: length, frequency, duplicates.");
		}
		}

		return convertedValue;
	}
}