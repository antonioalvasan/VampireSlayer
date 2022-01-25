package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.CommandExecuteException;
import org.ucm.tp1.control.exceptions.CommandParseException;
import org.ucm.tp1.control.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.logic.Game;

public class LightFlashCommand extends Command{
	protected final static String name = "light";
	private final static String details = "[l]ight";
	protected final static String shortcut = "l";
	private final static String help = " light";
	
	private static final int COST = 50;
	private final static String notAddedMsg = "Failed to light flash";
	
	public LightFlashCommand() {
		super(name, shortcut, details, help);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try{
			game.executeLightFlash(COST);
			return true;
		}
		catch (NotEnoughCoinsException e) {
			 System.out.println(e.getMessage());
	    	 throw new CommandExecuteException(String.format("[ERROR]: %s", notAddedMsg), e);
		}
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equalsIgnoreCase("light") || commandWords[0].equalsIgnoreCase("l"))
			commandWords[0] = shortcut;
		return parseNoParamsCommand(commandWords);
	}
}
