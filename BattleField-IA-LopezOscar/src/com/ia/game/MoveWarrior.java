package com.ia.game;
import ia.battle.camp.FieldCell;
import ia.battle.camp.actions.Move;

import java.util.ArrayList;


public class MoveWarrior extends Move{
	
	private ArrayList<FieldCell> moves;
	
	public MoveWarrior() {
        moves = new ArrayList<FieldCell>();
	}

	public void setMoves(ArrayList<FieldCell> moves){
		this.moves = moves;
	}
	
	@Override
	public ArrayList<FieldCell> move() {
		return moves;
	}

}
