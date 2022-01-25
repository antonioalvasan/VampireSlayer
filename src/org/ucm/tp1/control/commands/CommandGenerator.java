package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.*;

public class CommandGenerator {
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new AddBloodBankCommand(),
			new SuperCoinsCommand(),
			new LightFlashCommand(),
			new GarlicPushCommand(),
			new AddVampireCommand(),
			new SerializeCommand(),
			new SaveCommand(),
			new AddWizard()

			};

	public static Command parse(String[] parameters) throws CommandParseException{
		Command com = null;
		for(Command c : availableCommands) {
			com = c.parse(parameters);
			if(com != null) 
				return com;
		}
		throw new CommandParseException("[ERROR]: Unknown command");
	}
}
