package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.*;
import org.ucm.tp1.logic.Game;

public class AddBloodBankCommand extends Command {

	protected final static String name = "bank";
	private final static String details = "[b]ank";
	protected final static String shortcut = "b";
	private final static String help = " bank";
	private final static String notAddedMsg = "Failed to add bank";
	
	private int x;
	private int y;
	private int z;
	
	public AddBloodBankCommand() {
		super(name, shortcut, details, help);
	}

	public AddBloodBankCommand(int x, int y, int z) {
		super(name, shortcut, details, help);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.addBloodBank(x, y, z);
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
	public Command parse(String[] commandWords) {
		if(commandWords[0].equalsIgnoreCase("bank") || commandWords[0].equalsIgnoreCase("b"))
			if(commandWords.length == 4)
				return new AddBloodBankCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
			else {
				return null;
			}
		else
			return null;
	}
}