package org.ucm.tp1.logic.gameobjects;

public interface IAttack {

	void attack();

	default boolean receiveSlayerAttack(int damage) {return false;};
	default boolean receiveVampireAttack(int damage) {return false;};
	default boolean receiveLightFlash() {return false;};
	default boolean receiveGarlicPush() {return false;};
	default boolean receiveDraculaAttack(){return false;}
	default boolean heal(int amount) {return false;}
	default boolean receivetxupadita(int damage) {return false;}
	}
