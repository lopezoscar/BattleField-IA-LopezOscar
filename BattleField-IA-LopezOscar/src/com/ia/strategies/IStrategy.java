package com.ia.strategies;

import ia.battle.camp.Warrior;
import ia.battle.camp.actions.Action;

public interface IStrategy {
	
	Action playMove(Warrior w);

}
