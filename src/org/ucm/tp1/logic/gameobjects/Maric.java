package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class Maric extends GameObject{

	 
	
	public Maric(int health, int damage, int posx, int posy, Game game) {
		super(1, 1, posx, posy, game);
	}

	@Override
	public void attack() {
		IAttack obj = game.getAttackableInPosition(posx+1, posy);
		if(obj != null) {
			obj.receivetxupadita(damage);
			health = 0;
		}
	}

	@Override
	protected boolean move() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean dead() {
		if(health == 0) return true;
		else return false;
	}

	@Override
	protected boolean left() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void substractCounter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

}
