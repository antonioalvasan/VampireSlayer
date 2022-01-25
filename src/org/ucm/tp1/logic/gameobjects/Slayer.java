package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject{
	
	public static final int COST = 50;
	private static final int INITIAL_HEALTH = 3;
	private static final int DAMAGE = 1;
	
	public Slayer(int posx, int posy, Game game) {
		super(INITIAL_HEALTH, DAMAGE, posx, posy, game);
	}
	
	@Override
	public void attack() {
		boolean attacked = false;
		int cont = 0;
		
		if (!dead()) {
			while (!attacked && cont < game.dimx()) {
				if(cont != posx) {
					IAttack other = game.getAttackableInPosition(cont, posy);
					if (other != null ) {
					attacked = other.receiveSlayerAttack(DAMAGE);
					}
				}
				cont++;
			}
		}
	}
	
	@Override
	public boolean receiveVampireAttack(int damage) {
		health -= damage;
		return true;
	}

	public boolean receiveDraculaAttack(){
		health = 0;
		return true;
	}
	
	@Override
	protected boolean move() {
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
			return "S [" + health + "]";
	}

	@Override
	protected boolean left() {
		return false;
	}
	
	@Override
	protected boolean dead() {
		return health<=0;
	}

	@Override
	protected void substractCounter() {	
	}
	
	@Override
	protected String serialize() {
		return "S;" + posx + ";" + posy + ";" + health;
	}
	
	public boolean heal(int amount) {
		health+=amount;
		return true;
	}

}
