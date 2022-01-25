package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SerializeCommand extends Command {

	protected final static String name = "serialize";
	private final static String details = "[z]serialize";
	protected final static String shortcut = "z";
	private final static String help = "serialize";
	
	public SerializeCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(game.serialize());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equalsIgnoreCase("") || commandWords[0].equalsIgnoreCase("n") || commandWords[0].equalsIgnoreCase("none"))
			commandWords[0] = shortcut;
		return parseNoParamsCommand(commandWords);
	}

}
