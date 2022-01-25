//HECHO POR ANTONIO �LVAREZ S�NCHEZ Y ENEKO RETOLAZA ARDANAZ
//GRUPO ROBB STARK 

package org.ucm.tp1.logic;

import java.util.Random;

import org.ucm.tp1.control.exceptions.*;
import org.ucm.tp1.logic.gameobjects.*;
import org.ucm.tp1.view.*;

public class Game implements IPrintable{
	private static final int INITIALCYCLES = 0;
	
	private Random random;
	private long seed;
	private int cycles;
	private Level level;
	private GameObjectBoard gameObjectBoard;
	private Player player;
	private GamePrinter gamePrinter;
	private boolean exit;

	public Game(long seed, Level level) {
		this.seed = seed;
		this.level = level;
		this.cycles = INITIALCYCLES;
		this.gameObjectBoard = new GameObjectBoard();
		this.random = new Random(seed);
		this.player = new Player(random);
		this.gamePrinter = new GamePrinter(this, level.getDimX(), level.getDimY()); //CAMBIADO
		this.exit = false;
		
		initializeVampire();
	}
	
	public void update() {
		player.updateCoins();
		gameObjectBoard.move();
		attack();
		addVampireIfNeeded();
		addDraculaIfNeeded();
		addExplosiveVampireIfNeeded();
		removeDeadObjects();
		cycles++;
	} 
	
	private void attack() {
		gameObjectBoard.attack();
	}
	
	private void addVampireIfNeeded() {
		if(Vampire.shouldAddVampire()) {
			
			int x = level.getDimX()-1; 
			int y = random.nextInt(level.getDimY()); 
			
			int aux = random.nextInt(1);
			boolean auxB;
			if(aux == 1) auxB = true;
			else auxB = false;
			
			if(isPositionEmpty(x,y)) {
				gameObjectBoard.add(new Vampire (x, y, this, auxB));
				Vampire.remainingVampires--;
			}

		}
	} 
	
	private void addDraculaIfNeeded() {
		if(Dracula.shouldAddVampire()) {
			
			int x = level.getDimX()-1; 
			int y = random.nextInt(level.getDimY()); 
			
			
			if(isPositionEmpty(x,y)) {
				gameObjectBoard.add(new Dracula (x, y, this));
				Vampire.remainingVampires--;
			}
		}
	} 
	
	private void addExplosiveVampireIfNeeded() {
		if(Vampire.shouldAddVampire()) {
			
			int x = level.getDimX()-1; 
			int y = random.nextInt(level.getDimY()); 
			
			if(isPositionEmpty(x,y)) {
				gameObjectBoard.add(new ExplosiveVampire (x, y, this));
				Vampire.remainingVampires--;
			}

		}
	} 
	
	private boolean isPositionEmpty(int x, int y) {
		return getAttackableInPosition(x, y) == null;
	} 
	
	private void removeDeadObjects() {
		gameObjectBoard.removeDeadObjects();
	} 
	
	public String toString() {
		return gamePrinter.toString();
	}
	
	public boolean checkEnd() {
		return gameObjectBoard.Left() || (Vampire.remainingVampires == 0 && Vampire.numVampiresAlive == 0) || exit;
	}
	
	public String getWinnerMessage () {
		
		if(gameObjectBoard.Left()) 
			 return "[GAME OVER] Vampires win!";
		else if (Vampire.remainingVampires == 0 && Vampire.numVampiresAlive == 0)
			return "[GAME OVER] Player wins";
		else if (exit) 
			return "[GAME OVER] Nobody wins...";
		
		return null;
	}
	
	public void addSlayer(int x, int y) throws CommandExecuteException{
		
		if(player.enoughCoins(Slayer.COST) && gameObjectBoard.getAttackableInPosition(x, y) == null && validPositionSlayer(x, y)) { //CAMBIADO 
			gameObjectBoard.add(new Slayer(x, y, this));
			player.loseCoins(Slayer.COST);
		}
		
		else if(!validPositionSlayer(x, y) || gameObjectBoard.getAttackableInPosition(x, y) != null)
			throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
		else if(!player.enoughCoins(Slayer.COST))
			throw new NotEnoughCoinsException("[ERROR]: Defender cost is " + Slayer.COST + ": Not enough coins");
		
	}
	
	private boolean validPositionSlayer(int x, int y) {
		return (x < level.getDimX()-1 && y < level.getDimY() && x >= 0 && y >=0);
	}
	
	public void reset() {
		cycles = 0;
		random = new Random (seed);
		this.player = new Player(random);
		initializeVampire();
		this.gameObjectBoard = new GameObjectBoard();
	}
	
	public String getStringAt(int i, int j) {
		return gameObjectBoard.getPositionToString(i, j);
	}
	
	public void exit() {
		exit = true;
	}
	
	public IAttack getAttackableInPosition(int posx, int posy) {
		return gameObjectBoard.getAttackableInPosition(posx, posy);
	}
	
	private void initializeVampire() {
		Vampire.remainingVampires = level.getNumberOfVampires();
		Vampire.level = this.level;
		Vampire.rand = this.random;
		Vampire.numVampiresAlive = 0;
		Dracula.draculaOnBoard = false;
	}
	
	@Override
	public String getPositionToString(int x, int y) {
		return gameObjectBoard.getPositionToString(x, y);
	}

	@Override
	public String getInfo() {
		String s =  "Number of cycles: " + cycles + "\n" +
		"Coins: " + player.getCoins() + "\n" +
		"Remaining vampires: " + Vampire.remainingVampires + "\n" +
		"Vampires on the board: " + Vampire.numVampiresAlive + "\n";
		
		if(Dracula.draculaOnBoard)
			s = s + "Dracula is alive\n";
		
		return s;
			
	}

	public int dimx() {
		return level.getDimX();
	}

	public void addBloodBank(int posx, int posy, int cost) throws CommandExecuteException{

		if(player.enoughCoins(cost) && gameObjectBoard.getAttackableInPosition(posx, posy) == null && validPositionSlayer(posx, posy)) {
			gameObjectBoard.add(new BloodBank(posx, posy, cost, this));
			player.loseCoins(cost);
		}
		
		else if(!validPositionSlayer(posx, posy) || getAttackableInPosition(posx, posy) != null || gameObjectBoard.getAttackableInPosition(posx, posy) != null) //CAMBIADO
			throw new UnvalidPositionException("[ERROR]: Position (" + posx + ", " + posy + "): Unvalid position");
		else if(!player.enoughCoins(cost))
			throw new NotEnoughCoinsException("[ERROR] Defender cost is " + cost + ": Not enough coins");

	}

	public void addPlayerCoins(double coins) {
		player.addCoins(coins);
	}

	public void lightFlash() {
		gameObjectBoard.lightFlash();
	}

	public void garlicPush() {
		gameObjectBoard.garlicPush();
	}

	public int coins() {
		return player.getCoins();
	}

	public String serialize() {
		
		String cadena = "Cycles: " + cycles + "\n" +
		"Coins: " + player.getCoins() + "\n" +
		"Level: " + level.name() + "\n" +
		"Remaining Vampires: " + Vampire.remainingVampires + "\n"+
		"Vampires on Board: " + Vampire.numVampiresAlive + "\n\n" +
		"Game Object List: \n" + gameObjectBoard.serialize();
		
		return cadena;
	}

	public void addVampire(int x, int y) throws CommandExecuteException {
		if(Vampire.remainingVampires > 0) {
			if(gameObjectBoard.getAttackableInPosition(x, y) == null && validPositionVampire(x, y)) {
				gameObjectBoard.add(new Vampire(x, y, this));
				Vampire.remainingVampires--;
			}
			else
				throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
		}
		else
			throw new NoMoreVampiresException("[ERROR]: No more remaining vampires left");
	}

	public void addDracula(int x, int y) throws CommandExecuteException{
		if(Vampire.remainingVampires > 0) {
			if(!Dracula.draculaOnBoard && validPositionVampire(x, y) && gameObjectBoard.getAttackableInPosition(x, y) == null) {
				gameObjectBoard.add(new Dracula(x, y, this));
				Vampire.remainingVampires--;
			}
			else if (Dracula.draculaOnBoard)
				throw new DraculaIsAliveException("[ERROR]: Dracula is already on board");
			else
				throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
		}
		else
			throw new NoMoreVampiresException("[ERROR]: No more remaining vampires left");
	}

	public void addExplosiveVampire(int x, int y) throws CommandExecuteException{
		if(Vampire.remainingVampires > 0) {
			if(gameObjectBoard.getAttackableInPosition(x, y) == null && validPositionVampire(x, y)) {
				gameObjectBoard.add(new Vampire(x, y, this));
				Vampire.remainingVampires--;
			}
			else
				throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
		}
		else
			throw new NoMoreVampiresException("[ERROR]: No more remaining vampires left");
	}


	public void executeGarlicPush(int cost) throws CommandExecuteException{
		if(coins()>= cost) {
			garlicPush();
			addPlayerCoins(-cost);
			update();
		}
		else {
			throw new NotEnoughCoinsException("[ERROR]: Garlic Push cost is " + cost + ": Not enough coins");
		}
	}

	public void executeLightFlash(int cost) throws CommandExecuteException{
		if(coins()>= cost) {
			lightFlash();
			addPlayerCoins(-cost);
			update();
		}
		else {
			throw new NotEnoughCoinsException("[ERROR]: Light Flash cost is " + cost + ": Not enough coins");
		}
	}
	
	private boolean validPositionVampire(int x, int y) {
		return (x < level.getDimX() && y < level.getDimY() && x >= 0 && y >=0);
	}

	public void heal(int posx, int posy, int amount) {

		gameObjectBoard.heal(posx, posy, amount);
		
	}

	public void addWizard(int x, int y) throws CommandExecuteException{
		if(player.enoughCoins(Wizard.COST) && gameObjectBoard.getAttackableInPosition(x, y) == null && validPositionSlayer(x, y)) { //CAMBIADO 
			gameObjectBoard.add(new Wizard(x, y, this));
			player.loseCoins(Wizard.COST);
		}
		
		else if(!validPositionSlayer(x, y) || gameObjectBoard.getAttackableInPosition(x, y) != null)
			throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
		else if(!player.enoughCoins(Wizard.COST))
			throw new NotEnoughCoinsException("[ERROR]: Wizard cost is " + Wizard.COST + ": Not enough coins");
		
	}
	
}
