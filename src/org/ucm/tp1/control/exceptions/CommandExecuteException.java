package org.ucm.tp1.control.exceptions;

public class CommandExecuteException extends GameException {

	protected String notAddedMsg;
	
	public CommandExecuteException(String s, GameException ge) {
		super(s);
	}

	public CommandExecuteException(String s) {
		super(s);
	}
	
}
