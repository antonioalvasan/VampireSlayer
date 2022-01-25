package org.ucm.tp1.control.commands;

import java.lang.NumberFormatException;
import org.ucm.tp1.control.exceptions.*;
import org.ucm.tp1.logic.Game;

public class AddCommand extends Command {

	private int x;
	private int y;
	
	protected final static String name = "add";
	private final static String details = "[a]dd";
	protected final static String shortcut = "a";
	private final static String help = "add";
	private final static String notAddedMsg = "Failed to add slayer";
	
	public AddCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}
	
	public AddCommand(int x, int y) {
		super(name, shortcut, details, help);
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.addSlayer(x, y);
			game.update();
			return true;
		}
		catch(UnvalidPositionException upe) {
			System.out.println(upe.getMessage());
	    	 throw new CommandExecuteException(String.format("[ERROR]: %s", notAddedMsg), upe);
		}
		catch(NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
	    	 throw new CommandExecuteException(String.format("[ERROR]: %s", notAddedMsg), e);
		}

	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("add") || commandWords[0].equalsIgnoreCase("a"))
			if(commandWords.length == 3) {
				try {
					Integer.parseInt(commandWords[1]);
					Integer.parseInt(commandWords[2]);
					return new AddCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
				}
				catch(NumberFormatException e) {
					System.out.println("[ERROR]: Unvalid argument for add slayer command, number expected: [a]dd <x> <y>");
					return null;
				}
			}
			else
				throw new CommandParseException("[ERROR]: Incorrect number of arguments for add command: [a]dd <x> <y>");
		else
			return null;
	}
}
