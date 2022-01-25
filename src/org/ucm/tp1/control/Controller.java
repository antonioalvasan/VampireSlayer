package org.ucm.tp1.control;

import java.util.Scanner;

import org.ucm.tp1.control.commands.*;
import org.ucm.tp1.control.exceptions.CommandParseException;
import org.ucm.tp1.control.exceptions.GameException;
import org.ucm.tp1.logic.Game;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    	this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run() throws GameException {
	    	boolean refreshDisplay = true;

	    	while (!game.checkEnd()) {
	    		
	    		if (refreshDisplay) 
	    			printGame();
	    		
	    		refreshDisplay = false;
	    		
	    		System.out.println(prompt);
	    		String s = scanner.nextLine();
	    		String[] parameters = s.toLowerCase().trim().split(" ");
	    		System.out.println("[DEBUG] Executing: " + s);
	    		
	    		try {
	    			Command command = CommandGenerator.parse(parameters);
	    			refreshDisplay = command.execute(game);
	    		}
	    		catch (GameException ex) {
	    			System.out.format(ex.getMessage() + " %n %n");
	    		}
	    	}
	    	
	    	printGame();
	    	System.out.println(game.getWinnerMessage());
    }
}

