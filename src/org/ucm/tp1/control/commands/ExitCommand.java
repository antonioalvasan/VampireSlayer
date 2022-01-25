package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ExitCommand extends Command {

	protected final static String name = "exit";
	private final static String details = "[e]xit";
	protected final static String shortcut = "e";
	private final static String help = " exit";
	
	public ExitCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.exit();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equalsIgnoreCase("exit") || commandWords[0].equalsIgnoreCase("e"))
			commandWords[0] = shortcut;
		return parseNoParamsCommand(commandWords);
	}

}
