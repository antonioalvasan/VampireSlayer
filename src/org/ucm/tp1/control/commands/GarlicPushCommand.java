package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.*;
import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends Command{
	protected final static String name = "garlic";
	private final static String details = "[g]arlic";
	protected final static String shortcut = "g";
	private final static String help = " garlic";
	
	private final static String notAddedMsg = "Failed to garlic push";
	private static final int COST = 10;
	
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try{
			game.executeGarlicPush(COST);
			return true;
		}
		catch (NotEnoughCoinsException e) {
			 System.out.println(e.getMessage());
	    	 throw new CommandExecuteException(String.format("[ERROR]: %s", notAddedMsg), e);
		}
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equalsIgnoreCase("garlic") || commandWords[0].equalsIgnoreCase("g"))
			commandWords[0] = shortcut;
		return parseNoParamsCommand(commandWords);
	}
}
