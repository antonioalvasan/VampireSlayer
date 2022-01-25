package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SuperCoinsCommand extends Command {

	protected final static String name = "coins";
	private final static String details = "[c]oins";
	protected final static String shortcut = "c";
	private final static String help = " coins";
	
	private final static int COINS = 1000;
	
	public SuperCoinsCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.addPlayerCoins(COINS);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equalsIgnoreCase("coins") || commandWords[0].equalsIgnoreCase("c"))
			commandWords[0] = shortcut;
		return parseNoParamsCommand(commandWords);
	}

}
