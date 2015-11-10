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


public class Tyson extends Warrior{
	
	private Manager wm;

	public Tyson(String name, int health, int defense, int strength, int speed,
			int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
	}
	
	private int wasKilled = 0;
	

	@Override
	/**
	 * Estrategia 3 -> Fuerza Bruta.
	 * 
	 * Busco siempre al enemigo, si hay regalos los tomo.
	 * 
	 */
	public Action playTurn(long tick, int actionNumber) {
		
		
			MoveWarrior move = new MoveWarrior();
			if(BattleField.getInstance().getEnemyData().getInRange()){
				return new Attack(BattleField.getInstance().getEnemyData().getFieldCell());	
			}else{
				ArrayList<FieldCell> path = PathFinder.getInstance().activateSpecialItems().findPath(this.getPosition(), BattleField.getInstance().getEnemyData().getFieldCell());
				move.setMoves(path);
				return move;
			}			
	}

	@Override
	public void wasAttacked(int damage, FieldCell source) {
//		System.out.println("DAMAGE "+damage);
//		System.out.println("FIELD SOURCE "+source);
	}

	@Override
	public void enemyKilled() {
		wasKilled++;
		wm.addEnemyKilled();
		System.out.println("SOY "+this.getName()+" TOTAL ENEMIES KILLED "+wm.getEnemiesKilled());
//		System.out.println("MATE A "+BattleField.getInstance().getEnemyData().getName()+" "+wasKilled+" veces");
		
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub
		
	}
	
	public void setWarriorManager(Manager wm){
		this.wm = wm;
	}
	

}
