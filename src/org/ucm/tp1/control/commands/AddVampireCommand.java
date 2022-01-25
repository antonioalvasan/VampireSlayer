package org.ucm.tp1.control.commands;

import java.lang.NumberFormatException;
import org.ucm.tp1.control.exceptions.*;
import org.ucm.tp1.logic.Game;

public class AddVampireCommand extends Command {

	private int x;
	private int y;
	private String type;
	private final static String notAddedMsg = "Failed to add this vampire\r\n";
	
	protected final static String name = "vampire";
	private final static String details = "[v]ampire";
	protected final static String shortcut = "v";
	private final static String help = "vampire";
	
	public AddVampireCommand() {
		super(name, shortcut, details, help);
	}
	
	public AddVampireCommand(String type, int x, int y) {
		super(name, shortcut, details, help);
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	/*public boolean execute(Game game) {	
		//return game.addVampire(type, x, y);
	}*/

	
	public boolean execute(Game game) throws CommandExecuteException {
	     try {
	         //game.tryAddVampire(x, y);
	    	 if (type.equals("")) 
	    		 game.addVampire(x, y);
	    	 if (type.toUpperCase().equals("D")) {
	    		 try {
	    		 		game.addDracula(x, y);
	    		 }
	    		 catch(DraculaIsAliveException dae) {
	    			 System.out.println(dae.getMessage());
	    	    	 throw new CommandExecuteException(String.format("[ERROR]: %s", notAddedMsg), dae);//en la práctica dice no informar al usuario, pero bueno, ponemos esto
	     //No hacemos nada,  se pide eso en el enunciado tratar sin informar
	    		 }
	    	 }
	    	 if (type.toUpperCase().equals("E")) 
	    		 game.addExplosiveVampire(x, y);
	    	 
	    	 return true;
	     }
	     catch(NoMoreVampiresException e) {
	    	 System.out.println(e.getMessage());
	    	 throw new CommandExecuteException(String.format("[ERROR]: %s", notAddedMsg), e);
	     }
	     catch(UnvalidPositionException upe) {
	    	 System.out.println(upe.getMessage());
	    	 throw new CommandExecuteException(String.format("[ERROR]: %s ", notAddedMsg), upe);
	     }
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equalsIgnoreCase("vampire") || commandWords[0].equalsIgnoreCase("v")) {
			if(commandWords.length == 3 || commandWords.length == 4) {
				try {
					if(commandWords.length == 3) {
						Integer.parseInt(commandWords[1]);
						Integer.parseInt(commandWords[2]);
						return new AddVampireCommand("", Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
					}
					else {
						Integer.parseInt(commandWords[2]);
						Integer.parseInt(commandWords[3]);
						if(commandWords[1].equalsIgnoreCase("D") || commandWords[1].equalsIgnoreCase("E"))
							return new AddVampireCommand(commandWords[1], Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
						else
							throw new CommandParseException("[ERROR]: Unvalid type: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}\r\n" + ""); 
					}
				}
				catch(NumberFormatException e) {
					System.out.println("[ERROR]: Unvalid argument for add vampire command, number expected: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}\r\n" + "");
					return null;
				}
			}
			else {
				throw new CommandParseException("[ERROR]: %s" + incorrectArgsMsg);
			}
		}
		else
			return null;
	}
	
}
