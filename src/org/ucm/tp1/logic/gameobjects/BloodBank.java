package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class BloodBank extends GameObject{

	private static final int INITIAL_HEALTH = 1;
	
	private int z;
	
	public BloodBank(int posx, int posy, int z, Game game) {
		super(INITIAL_HEALTH, 0, posx, posy, game);
		this.z = z;
	}

	public boolean receiveVampireAttack(int damage) {
		health -= damage;
		return true;
	}
	
	@Override
	public void attack() {
		game.addPlayerCoins(0.1*z);
	}

	@Override
	protected boolean move() {
		return false;
	}

	@Override
	public String toString() {
		return "B [" + z + "]";
	}

	@Override
	protected boolean left() {
		return false;
	}

	@Override
	protected boolean dead() {
		return health <=0;
	}
	
	public boolean receiveDraculaAttack(){
		health = 0;
		return true;
		}

	@Override
	protected void substractCounter() {
	}

	@Override
	protected String serialize() {
		return "B;" + posx + ";" + posy + ";" + health + ";" + z;
	}
	
}
