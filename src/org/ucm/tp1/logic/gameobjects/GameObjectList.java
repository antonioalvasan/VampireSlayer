package org.ucm.tp1.logic.gameobjects;

import java.util.ArrayList;

public class GameObjectList {
	
	private ArrayList<GameObject> gameobjects;
	
	public GameObjectList() {
		this.gameobjects = new ArrayList <GameObject>();
	}
	
	public void move() {
		for(int i = 0; i< gameobjects.size(); i++) {
			if(!gameobjects.get(i).dead())
				gameobjects.get(i).move();
		}
	}

	private void removeObject(int pos) {
		gameobjects.get(pos).substractCounter();
		gameobjects.remove(pos);
	}
	
	public void removeDeadObjects() {
		for(int i = 0; i<gameobjects.size(); i++) {
			if(gameobjects.get(i).dead()) {
				removeObject(i);
				i--;
			}
		}
	}

	public IAttack getAttackableInPosition(int posx, int posy) {
		int i = 0;
		boolean found = false;
		while(i<gameobjects.size() && !found) {
			if(gameobjects.get(i).posx == posx && gameobjects.get(i).posy == posy)
				found = true;
			else
				i++;
		}
		
		if(found)
			return gameobjects.get(i);
		else
			return null;
	}

	public void attack() {
		for(int i = 0; i<gameobjects.size(); i++) {
			if(!gameobjects.get(i).dead())
				gameobjects.get(i).attack();
		}
	}

	public void add(GameObject o) {
		gameobjects.add(o);
	}

	public boolean Left() {
		int i = 0;
		boolean found = false;
		while(i < gameobjects.size() && !found) {
			if(gameobjects.get(i).left())
				found = true;
			
			i++;
		}
		return found;
	}
	
	public String getPositionToString(int i, int j) {
		int cont = 0;
		boolean found = false;
		
		while(cont < gameobjects.size() && !found) {
			if(gameobjects.get(cont).posx == i && gameobjects.get(cont).posy == j)
				found = true;
			else
				cont++;
		}
		if(found)
			return gameobjects.get(cont).toString();
		else
			return " ";
	}

	public void receiveLightFlash() {
		for(int i = 0; i<gameobjects.size(); i++) {
			gameobjects.get(i).receiveLightFlash();
		}
	}

	public void garlicPush() {
		for(int i = gameobjects.size()-1; i>=0; i--) {
			gameobjects.get(i).receiveGarlicPush();
		}
	}

	public String serialize() {
		String cadena = "";
		
		for(int i = 0; i<gameobjects.size(); i++) {
			cadena += gameobjects.get(i).serialize() + "\n";
		}
		
		return cadena;
	}

	public void heal(int posx, int posy, int amount) {
		IAttack obj = getAttackableInPosition(posx, posy);
		if(obj != null)
			obj.heal(amount);
	}

}
