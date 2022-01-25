package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;

public class ExplosiveVampire extends Vampire {

	public ExplosiveVampire(int posx, int posy, Game game) {
		super(posx, posy, game);
	}

	private void blowUp() {
		for(int i = posx-1; i<=posx+1; i++) {
			for(int j = posy-1; j<=posy+1; j++) {
				if(i != posx || j != posy)
					explosionDamage(i, j);
			}
		}
		
	}

	@Override
	public boolean receiveSlayerAttack(int damage) {
		health -= damage;
		
		if(health <= 0) {
			blowUp();
		}
		
		return true;
	}	
	
	
	@Override
	public boolean receiveLightFlash(){
		blowUp();
		health = 0;
		return true;
	}
	
	private void explosionDamage(int x, int y) {
		IAttack other = game.getAttackableInPosition(x, y);
		if (other != null )
			other.receiveSlayerAttack(DAMAGE);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
			return "EV [" + health + "]";
	}
	
	@Override
	protected String serialize() {
		return "EV;" + posx + ";" + posy + ";" + health + ";" + shouldMove;
	}
	
	
}
