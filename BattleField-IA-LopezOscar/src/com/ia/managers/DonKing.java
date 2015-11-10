package com.ia.managers;
import ia.battle.camp.Warrior;
import ia.battle.camp.WarriorManager;
import ia.exceptions.RuleException;

import java.util.HashMap;
import java.util.Map;

import com.ia.warriors.Tyson;


public class DonKing extends WarriorManager implements Manager{
	
	private int enemiesKilled;
	private int enemiesKilledByHunter;
	private int enemyNumber;
	private Map<String,Integer> killedBy = new HashMap<String,Integer>();

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Don King";
	}

	@Override
	public Warrior getNextWarrior() throws RuleException {
		// TODO Auto-generated method stub
		System.out.println("GETTING NEXT WARRIOR");
		return createWarrior();
	}
	
	private Warrior createWarrior() throws RuleException{
		//String name, int health, int defense, int strength, int speed, int range
		Tyson next = new Tyson("TYSON",40, 10, 10, 20, 20);
		next.setWarriorManager(this);
		return next;
	}

	@Override
	public void addEnemyKilled() {
		enemiesKilled++;
		
	}

	@Override
	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	@Override
	public void addEnemyKilledByHunter() {
		enemiesKilledByHunter++;
	}

	@Override
	public int getEnemiesKilledByHunter() {
		// TODO Auto-generated method stub
		return enemiesKilledByHunter;
	}

	@Override
	public void setEnemyNumber(int enemyNumber) {
		this.enemyNumber = enemyNumber;
	}

	@Override
	public int getEnemyNumber() {
		return enemyNumber;
	}

	@Override
	public void addKilledBy(String warrior, Integer enemyNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Integer> getKilledBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addWarriorNumberByTick(Long tick, Integer enemyNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Long, Integer> getWarriorNumberByTick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMyDeath(String warrior) {
		// TODO Auto-generated method stub
		
	}

}
