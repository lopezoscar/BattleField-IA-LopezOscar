package com.ia.strategies;

import ia.battle.camp.BattleField;
import ia.battle.camp.FieldCell;
import ia.battle.camp.Warrior;
import ia.battle.camp.actions.Action;

import java.util.ArrayList;

import com.ia.game.MoveWarrior;
import com.ia.pathfinder.BoxFinder;

/**
 * SURVIVOR -> Buscar cajas con vida hasta tener mas vida que el otro
 */
public class Survivor implements IStrategy{

	@Override
	public Action playMove(Warrior w) {
		MoveWarrior move = new MoveWarrior();
//		if(w.getHealth() < BattleField.getInstance().getEnemyData().getHealth()){
			ArrayList<FieldCell> path = BoxFinder.getInstance().getPathFrom(w.getPosition());
			if(path != null){
				move.setMoves(path);
				return move;
			}else{
				return null;
			}
		}
//	else{
//			return null;
//		}
//	}

	
	
}
