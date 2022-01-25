package org.ucm.tp1.logic.gameobjects;

import java.util.Random;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.Level;

public class Vampire extends GameObject{

	public static int remainingVampires;
	public static int numVampiresAlive;
	public static Level level;
	public static Random rand;
	
	protected static final int DAMAGE = 1;
	private static final int INITIAL_HEALTH = 5;
	private static final int FRECUENCY = 1;
	
	protected int shouldMove;
	protected boolean maric;
	
	public Vampire(int posx, int posy, Game game) {
		super(INITIAL_HEALTH, DAMAGE, posx, posy, game);
		this.shouldMove = 0;
		numVampiresAlive++;
	}
	
	public Vampire(int posx, int posy, Game game, boolean maric) {
		super(INITIAL_HEALTH, DAMAGE, posx, posy, game);
		this.shouldMove = 0;
		numVampiresAlive++;
		this.maric = maric;
	}
	
	@Override
	public void attack() {
		if (!dead()) {
			IAttack other = game.getAttackableInPosition(posx - 1, posy);
			if (other != null )
				other.receiveVampireAttack(damage);
			}
		}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		health -= damage;
		return true;
	}	
	
	public boolean receivetxupadita(int damage) {
		if(maric) health+=damage;
		else health-=damage;
		return true;
	}
	
	@Override
	public boolean receiveLightFlash(){
		health = 0;
		return true;
	}

	
	@Override
	protected boolean move() {
		if(shouldMove == FRECUENCY) {
			if (game.getAttackableInPosition(posx-1, posy) == null && posx >= 0) { //CAMBIADO
			posx = posx-1;//CAMBIADO
			shouldMove = 0;
			}
		}
		else
		shouldMove++;
		
		return true;
	}

	public static boolean shouldAddVampire() {
		return (remainingVampires > 0 && rand.nextDouble() < level.getVampireFrequency());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
			return "V [" + health + "]";
	}

	@Override
	protected boolean left() {
		if(posx < 0)
			return true;
		else
			return false;
	}

	public boolean receiveGarlicPush() {
		if(posx == game.dimx()-1) {
			posx++;
			health = 0;
			return true;
		}
		else if (game.getAttackableInPosition(posx+1, posy) == null) {
			posx++;
			shouldMove = 0;
		}
		return false;
	}

	@Override
	protected boolean dead() {
		return health <=0;
	}

	@Override
	protected void substractCounter() {
		Vampire.numVampiresAlive--;
	}
	
	@Override
	protected String serialize() {
		return "V;" + posx + ";" + posy + ";" + health + ";" + shouldMove;
	}
	
}
