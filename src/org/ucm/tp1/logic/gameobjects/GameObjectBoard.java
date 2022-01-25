package org.ucm.tp1.logic.gameobjects;

public class GameObjectBoard {

	private GameObjectList gameobjects;
	
	public GameObjectBoard(){
		this.gameobjects = new GameObjectList();
	}
	
	public void move() {
		gameobjects.move();
	}
	
	public void removeDeadObjects() {
		gameobjects.removeDeadObjects();
	}

	public IAttack getAttackableInPosition(int posx, int posy) {
		return gameobjects.getAttackableInPosition(posx, posy);
	}

	public void attack() {
		gameobjects.attack();
	}
	
	public void add(GameObject o) {
		gameobjects.add(o);
	}

	public boolean Left() {
		return gameobjects.Left();
	}

	public String getPositionToString(int i, int j) {
		return gameobjects.getPositionToString(i,j);
	}

	public void lightFlash() {
		gameobjects.receiveLightFlash();
	}

	public void garlicPush() {
		gameobjects.garlicPush();
	}

	public String serialize() {
		return gameobjects.serialize();
	}

	public void heal(int posx, int posy, int amount) {
		gameobjects.heal(posx, posy, amount);
		
	}

	
}
