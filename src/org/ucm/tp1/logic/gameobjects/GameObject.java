package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public abstract class GameObject implements IAttack {
	
	protected Game game;
	
	protected int health;
	protected int damage;
	protected int posx;
	protected int posy;

	public GameObject(int health, int damage, int posx, int posy, Game game) {
		this.health = health;
		this.damage = damage;
		this.posx = posx; 
		this.posy = posy;
		this.game = game;
	}
	
	protected abstract boolean move();

	public abstract String toString();
	
	public void loseHealth(int damageTaken) {
		health -= damageTaken;
	}
	
	protected abstract boolean dead();

	protected abstract boolean left();

	protected abstract void substractCounter();

	protected abstract String serialize();

	
}
