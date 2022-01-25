package org.ucm.tp1.control.exceptions;

public class UnvalidPositionException extends CommandExecuteException{

	public UnvalidPositionException(String s, GameException ge) {
		super(s, ge);
		// TODO Auto-generated constructor stub
	}

	public UnvalidPositionException(String s) {
		super(s);
	}
	
}
