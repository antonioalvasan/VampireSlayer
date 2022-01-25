package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class Dracula extends Vampire {

	public static boolean draculaOnBoard;
	
	public Dracula(int posx, int posy, Game game) {
		super(posx, posy, game);
		draculaOnBoard = true;
	}
	
	private static boolean draculaOnBoard() {
		return draculaOnBoard;
	}
	
	public static boolean shouldAddVampire() {
		if (remainingVampires > 0 && rand.nextDouble() < level.getVampireFrequency()) {
			if(!draculaOnBoard())
				return true;
		}
		return false;
	}
	
	@Override
	public void attack() {
		if (!dead()) {
			IAttack other = game.getAttackableInPosition(posx - 1, posy);
			if (other != null )
				other.receiveDraculaAttack();
			}
		}
	
	@Override
	public boolean dead() {
		if(health <= 0) {
			draculaOnBoard = false;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
			return "D [" + health + "]";
	}
	
	@Override
	public boolean receiveGarlicPush() {
		if(posx == game.dimx()-1) {
			draculaOnBoard = false;
			health = 0;
			posx++;
			return true;
		}
		else if (game.getAttackableInPosition(posx+1, posy) == null) {
			posx++;
			shouldMove = 0;
		}
		return false;
	}
	
	@Override
	public boolean receiveLightFlash(){
		return false;
	}
	
	@Override
	protected String serialize() {
		return "D;" + posx + ";" + posy + ";" + health + ";" + shouldMove;
	}
	
}
