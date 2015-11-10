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
//			System.out.println("LAST POS "+lastPos);
			lastPos = this.getPosition();
		}
		
		MoveWarrior move = new MoveWarrior();
		if(BattleField.getInstance().getEnemyData().getInRange()){
			if(alreadyAttack && actionNumber > 1){
				ArrayList<FieldCell> path = PathFinder.getInstance().findPath(this.getPosition(), lastPos);
				move.setMoves(path);
				alreadyAttack = false;
				return move;
			}else{
//				System.out.println("ATACO a "+BattleField.getInstance().getEnemyData().getName());
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
		}else{
			System.out.println("Me atacó el hunter");
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
