package org.ucm.tp1.control.commands;

import java.lang.NumberFormatException;
import org.ucm.tp1.control.exceptions.*;
import org.ucm.tp1.logic.Game;

public class AddWizard extends Command {

	private int x;
	private int y;
	
	protected final static String name = "wizard";
	private final static String details = "[w]izard";
	protected final static String shortcut = "w";
	private final static String help = "wizard";
	private final static String notAddedMsg = "Failed to add wizard";
	
	public AddWizard() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}
	
	public AddWizard(int x, int y) {
		super(name, shortcut, details, help);
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.addWizard(x, y);
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
		if(commandWords[0].equalsIgnoreCase("wizard") || commandWords[0].equalsIgnoreCase("w"))
			if(commandWords.length == 3) {
				try {
					Integer.parseInt(commandWords[1]);
					Integer.parseInt(commandWords[2]);
					return new AddWizard(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
				}
				catch(NumberFormatException e) {
					System.out.println("[ERROR]: Unvalid argument for wizard command, number expected: [w]izard <x> <y>");
					return null;
				}
			}
			else
				throw new CommandParseException("[ERROR]: Incorrect number of arguments for wizard command: [w]izard <x> <y>");
		else
			return null;
	}
}
