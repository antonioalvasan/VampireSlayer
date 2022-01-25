package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class HelpCommand extends Command {

	protected final static String name = "help";
	private final static String details = "[h]elp";
	protected final static String shortcut = "h";
	private final static String help = "help";
	private static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n"+
			"[g]arlic : pushes back vampires%n"+
			"[l]ight: kills all the vampires%n"+
			"[b]ank <x> <y> <z>: add a blood bank with cost z in position x, y%n"+
			"[c]oins: add 1000 coins%n"+
			"[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y%n" +
			"[s]ave <filename>: Save the state of the game to a file.%n" +
			"[w]izard <x> <y>: add a wizard in position x, y%n" +
			"Seriali[z]e: Serializes the board.%n");
	
	public HelpCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(helpMsg);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("help") || commandWords[0].equalsIgnoreCase("h"))
			commandWords[0] = shortcut;
		return parseNoParamsCommand(commandWords);
	}

}
