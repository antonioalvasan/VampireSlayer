package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class Wizard extends GameObject{

	private static final int DAMAGE = 1;
	private static final int INITIALHEALTH = 2;
	public static final int COST = 30;
	
	public Wizard(int posx, int posy, Game game) {
		super(INITIALHEALTH, DAMAGE, posx, posy, game);
		healPeople();
	}

	@Override
	public void attack() {
		
	}

	private void healPeople() {
		for(int i = posy-1; i<=posy+1; i++) {
			for(int j = posx-1; j <= posx+1; j++) {
				game.heal(j, i, damage);
			}
		}
	}
	
	@Override
	protected boolean move() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "W[" + health + "]";
	}

	@Override
	protected boolean dead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean left() {
		return false;
	}

	@Override
	protected void substractCounter() {
	}

	@Override
	protected String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

}
