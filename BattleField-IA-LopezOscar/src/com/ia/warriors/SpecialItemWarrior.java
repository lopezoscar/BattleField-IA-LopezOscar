package com.ia.warriors;

import ia.battle.camp.BattleField;
import ia.battle.camp.FieldCell;
import ia.battle.camp.Warrior;
import ia.battle.camp.actions.Action;
import ia.exceptions.RuleException;

import java.util.ArrayList;
import java.util.Map;

import com.ia.game.MoveWarrior;
import com.ia.managers.Manager;
import com.ia.pathfinder.PathFinder;

public class SpecialItemWarrior extends Warrior{

	public SpecialItemWarrior(String name, int health, int defense,
			int strength, int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		MoveWarrior move = new MoveWarrior();
		ArrayList<FieldCell> boxes = BattleField.getInstance().getSpecialItems();
		FieldCell selectedBox = boxes.get(0);
		for(FieldCell sItem : boxes){
			int deltaX = Math.abs(this.getPosition().getX() - sItem.getX());
			int deltaY = Math.abs(this.getPosition().getY() - sItem.getY());
			
			if(deltaX < Math.abs(this.getPosition().getX() - selectedBox.getX()) &&
			   deltaY < Math.abs(this.getPosition().getY() - selectedBox.getY())){
				selectedBox = sItem;
			}
		}
		
		ArrayList<FieldCell> path = PathFinder.getInstance().findPath(this.getPosition(), selectedBox);
		move.setMoves(path);
		return move;
	}

	@Override
	public void wasAttacked(int damage, FieldCell source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enemyKilled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub
		
	}

}
