//HECHO POR ANTONIO ÁLVAREZ SÁNCHEZ Y ENEKO RETOLAZA ARDANAZ
//GRUPO ROBB STARK 
package org.ucm.tp1.logic.gameobjects;

import java.util.Random;

public class Player {

	public static final int COINS = 50;
	private Random rand;
	private int coins;
	
	public Player(Random rand) {
		this.coins = COINS;
		this.rand = rand;
	}
	
	public void updateCoins() {
		if(rand.nextFloat() > 0.5) 
			coins += 10;
	}
	
	public void addCoins(double c) {
		coins += c;
	}
	
	public void loseCoins(int c) {
		coins -= c;
	}
	
	public boolean enoughCoins(int c) {
		return coins >= c;
	}
	
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
}
