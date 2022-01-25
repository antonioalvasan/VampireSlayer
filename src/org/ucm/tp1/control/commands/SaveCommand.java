package org.ucm.tp1.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.ucm.tp1.control.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SaveCommand extends Command {

	protected final static String name = "save";
	private final static String details = "[s]ave";
	protected final static String shortcut = "s";
	private final static String help = "save";
	
	private String filename;
	
	public SaveCommand() {
		super(name, shortcut, details, help);
	}
	
	public SaveCommand(String s) {
		super(name, shortcut, details, help);
		this.filename = s;
	}

	@Override
	public boolean execute(Game game) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(filename + ".dat"));
			out.write("Buffy the Vampire Slayer v3.0\n\n" + game.serialize());
		}
		catch (IOException e) {
			System.out.println("No se han podido guardar los datos");
		}
		finally {
			try {
				out.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0] == "s" || commandWords[0] == "save") {
			if(commandWords.length == 2) {
				return new SaveCommand(commandWords[1]);
			}
			else {
				throw new CommandParseException("[ERROR] Unvalid arguments for save command, expected [s]ave <filename>");
			}
		}
		else return null;
	}

}
