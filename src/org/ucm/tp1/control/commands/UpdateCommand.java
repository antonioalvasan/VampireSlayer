package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class UpdateCommand extends Command {

	protected final static String name = "update";
	private final static String details = "[n]one | []";
	protected final static String shortcut = "n";
	private final static String help = "update";
	
	public UpdateCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equalsIgnoreCase("") || commandWords[0].equalsIgnoreCase("n") || commandWords[0].equalsIgnoreCase("none"))
			commandWords[0] = shortcut;
		return parseNoParamsCommand(commandWords);
	}

}
