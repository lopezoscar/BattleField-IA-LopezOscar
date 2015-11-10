package com.ia.warriors;
import ia.battle.camp.BattleField;
import ia.battle.camp.FieldCell;
import ia.battle.camp.Warrior;
import ia.battle.camp.actions.Action;
import ia.battle.camp.actions.Attack;
import ia.exceptions.RuleException;

import java.util.ArrayList;

import com.ia.game.MoveWarrior;
import com.ia.managers.Manager;
import com.ia.pathfinder.PathFinder;
import com.ia.strategies.IStrategy;
import com.ia.strategies.Survivor;



public class Maravilla extends Warrior{
	
	private Manager wm;
	
	public Maravilla(String name, int health, int defense, int strength,
			int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
	}

	private int wasKilled = 0;
	
	private boolean alreadyAttack; 
	
	private FieldCell lastPos;
	
	@Override
	/**
	 * Estrategia 2 -> Golpeo 2 Veces y Retrocedo.
	 * 
	 *
	 * El warrior busca siempre al enemigo. Cuando lo encuentra le pega en 2 movidas 
	 * y en la 3era se escapa.
	 * 
	 * Si el enemigo no está en rango, mientras lo está buscando, también tomá los regalos
	 * si el mismo está en alguna de las cajas adyacentes.
	 * 
	 */
	public Action playTurn(long tick, int actionNumber) {
		if(actionNumber == 0){
			lastPos = this.getPosition();
		}
		
		if(BattleField.getInstance().getHunterData().getInRange() && !BattleField.getInstance().getEnemyData().getInRange()){
			IStrategy strategy = new Survivor();
			Action action = strategy.playMove(this);
			if(action != null){
				return action;
			}
			//Sino continua evaluando
		}
		
		MoveWarrior move = new MoveWarrior();
		if(BattleField.getInstance().getEnemyData().getInRange()){
			if(alreadyAttack && actionNumber > 1){
				ArrayList<FieldCell> path = PathFinder.getInstance().findPath(this.getPosition(), lastPos);
				move.setMoves(path);
				alreadyAttack = false;
				return move;
			}else{
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
			System.out.println("SOY "+this.getName()+" Me atacó "+BattleField.getInstance().getEnemyData().getName());
			if(this.getHealth() <= 0){
				System.out.println("SOY "+this.getName()+" Me mató el enemigo");
				wm.addMyDeath(this.getName());
			}
		}else{
			System.out.println("SOY "+this.getName()+" Me atacó el hunter");
			if(this.getHealth() <= 0){
				System.out.println("SOY "+this.getName()+" Me mató el hunter");
				wm.addMyDeath(this.getName());
			}
			//Fue el hunter
		}
	}
	
	

	@Override
	public void enemyKilled() {
		wasKilled++;
		wm.addEnemyKilled();
		System.out.println("SOY "+this.getName()+" TOTAL ENEMIES KILLED "+wm.getEnemiesKilled());
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub
		
	}
	
	public void setWarriorManager(Manager wm){
		this.wm = wm;
	}

}
