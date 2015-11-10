package com.ia.warriors;
import ia.battle.camp.BattleField;
import ia.battle.camp.FieldCell;
import ia.battle.camp.Warrior;
import ia.battle.camp.actions.Action;
import ia.battle.camp.actions.Attack;
import ia.battle.camp.actions.Skip;
import ia.exceptions.RuleException;

import java.util.ArrayList;

import com.ia.game.MoveWarrior;
import com.ia.managers.Manager;
import com.ia.pathfinder.PathFinder;
import com.ia.strategies.IStrategy;
import com.ia.strategies.Survivor;


public class Mayweather extends Warrior{
	
	
	/**
	 * SURVIVOR -> Buscar cajas con vida hasta tener mas vida que el otro
	 * 
	 * FULL_ATTACK -> Si está en rango el enemigo, atacarlo con 3 acciones
	 * HALF_ATTACK -> Si está en rango el enemigo, atacarlo con 2 acciones
	 * LOW_ATTACK -> Si está en rango el enemigo, atacarlo con 1 accion
	 * 
	 * DEFENSE -> Buscar cajas mientras el otro está siendo atacado por el Hunter.
	 * IDLE -> No hacer nada cuando el otro está siendo atacado por el hunter
	 * 
	 */
	private enum STATUS {
		ATTACK,SURVIVOR,DEFENSE,IDLE
	};
	
	/**
	 * 
	 * 
	 * 		Si el otro está siendo atacado por el hunter, yo IDLE o DEFENSE/SURVIVOR
		 * 		Si yo tengo poca vida SURVIVOR
		 * 		Si tengo  mas vida que el enemigo -> IDLE, espero a que terminen		
		 *  sino(está solo)
		 *  	Si tiene mas vida que yo -> Survivor
		 *  	Sino 
		 *  		ATTACK
	 * 
	 */
	
	private Manager wm;
	
	public Mayweather(String name, int health, int defense, int strength,
			int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
	}

	private int wasKilled = 0;
	
	private boolean alreadyAttack; 
	
	private FieldCell lastPos;
	
	private int lastWarriorNumberKilledByMe = 0;
	
	@Override
	/*Tick 1 Hunter - Tick 2 WM1 - Tick 3 WM2*/
	public Action playTurn(long tick, int actionNumber) {
		if(actionNumber == 0){
//			wm.setEnemyNumber(BattleField.getInstance().getEnemyData().getWarriorNumber());
//			System.out.println("TICK "+tick);
			wm.addWarriorNumberByTick(tick, BattleField.getInstance().getEnemyData().getWarriorNumber());
			lastPos = this.getPosition();	
		}
		
		ArrayList<FieldCell> specialItems = BattleField.getInstance().getSpecialItems();
		
		if(this.getHealth() < 20 && specialItems.size() > 1){
			IStrategy strategy = new Survivor();
			Action action = strategy.playMove(this);
			if(action != null){
				return action;
			}
			//Sino continua evaluando
		}
		
		if(BattleField.getInstance().getHunterData().getInRange() &&
				!BattleField.getInstance().getEnemyData().getInRange()){
			IStrategy strategy = new Survivor();
			Action action = strategy.playMove(this);
			if(action != null){
				return action;
			}
			//Sino continua evaluando
		}
		
		/*Si aumenta el warrior Number del enemigo y yo no lo mate -> lo mato el hunter*/
//		System.out.println("WN Actual"+BattleField.getInstance().getEnemyData().getWarriorNumber());
//		System.out.println("WN Inicial"+wm.getEnemyNumber());
		if(BattleField.getInstance().getEnemyData().getWarriorNumber() > wm.getEnemyNumber()){
//			System.out.println("Lo mato el warrior !!!!!!!!!!!!!!!!!!!");
//			return new Skip();
		}
		
//		else{
//			System.out.println("tengo vida");
//			
//			//TODO ATTACK MODE
			MoveWarrior move = new MoveWarrior();
			if(BattleField.getInstance().getEnemyData().getInRange()){
				if(alreadyAttack && actionNumber > 1){
					ArrayList<FieldCell> path = PathFinder.getInstance().findPath(this.getPosition(), lastPos);
					move.setMoves(path);
					alreadyAttack = false;
					return move;
				}else{
					System.out.println("ATACO a "+BattleField.getInstance().getEnemyData().getName());
					alreadyAttack = true;
					return new Attack(BattleField.getInstance().getEnemyData().getFieldCell());	
				}
			}else{
				ArrayList<FieldCell> path = PathFinder.getInstance().activateSpecialItems().findPath(this.getPosition(), BattleField.getInstance().getEnemyData().getFieldCell());
				move.setMoves(path);
				return move;
			}	
		
	}

	@Override
	/**
	 * Minimo hace daño de 25% sobre la defensa.
	 * Lo mismo con mi ataque  
	 * @param damage
	 * @param source
	 */
	public void wasAttacked(int damage, FieldCell source) {
		if(source.equals(BattleField.getInstance().getEnemyData().getFieldCell())){
			System.out.println("Me atacó "+BattleField.getInstance().getEnemyData().getName());
			if(this.getHealth() <= 0){
				System.out.println("Me mató el enemigo");
			}
		}else{
			System.out.println("Me atacó el hunter");
			if(this.getHealth() <= 0){
				System.out.println("Me mató el hunter");
				wm.addMyDeath(this.getName());
			}
			//Fue el hunter
		}
	}
	
	

	@Override
	public void enemyKilled() {
		wasKilled++;
		wm.addEnemyKilled();
		wm.addKilledBy(this.getName(), BattleField.getInstance().getEnemyData().getWarriorNumber());
		
		lastWarriorNumberKilledByMe = BattleField.getInstance().getEnemyData().getWarriorNumber();
		System.out.println("SOY "+this.getName()+" TOTAL ENEMIES KILLED "+wm.getEnemiesKilled());
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub
		
	}
	
	public void setWarriorManager(Manager wm){
		this.wm = wm;
	}

	public boolean isAlreadyAttack() {
		return alreadyAttack;
	}

	public void setAlreadyAttack(boolean alreadyAttack) {
		this.alreadyAttack = alreadyAttack;
	}

	public FieldCell getLastPos() {
		return lastPos;
	}

	public void setLastPos(FieldCell lastPos) {
		this.lastPos = lastPos;
	}
	
	
	
	

}
