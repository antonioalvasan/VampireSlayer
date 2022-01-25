package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.*;
import org.ucm.tp1.logic.Game;

public class ResetCommand extends Command {

	protected final static String name = "reset";
	private final static String details = "[r]eset";
	protected final static String shortcut = "r";
	private final static String help = "reset";
	
	public ResetCommand() {
		super(name, shortcut, details, help);
	}
	
	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equalsIgnoreCase("reset") || commandWords[0].equalsIgnoreCase("r"))
			commandWords[0] = shortcut;
		return parseNoParamsCommand(commandWords);
	}

}
