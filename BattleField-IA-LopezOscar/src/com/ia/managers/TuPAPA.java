package com.ia.managers;
import ia.battle.camp.Warrior;
import ia.battle.camp.WarriorManager;
import ia.exceptions.RuleException;

import java.util.HashMap;
import java.util.Map;

import com.ia.warriors.Mayweather;


public class TuPAPA extends WarriorManager implements Manager{
	
	private int enemiesKilled = 0;
	
	private int enemiesKilledByHunter = 0;
	
	private int enemyNumber = 0;
	private Map<String,Integer> killedBy = new HashMap<String,Integer>();
	
	private Map<Long,Integer> warriorNumberByTick = new HashMap<Long,Integer>();
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "TU PAPÁ";
	}

	@Override
	/**
	 * TODO Hacer la siguiente logica
	 * 
	 * Primer luchador: Maravilla
	 * Si maravilla pierde por diferencia > 5 -> se envía a Maywheater
	 * Si Mayweather pierde por dif > 3 -> se envía a Tyson
	 * 	
	 * 
	 */
	public Warrior getNextWarrior() throws RuleException {
		//String  name, int health, int defense, int strength, int speed, int range
		Mayweather next = new Mayweather("Maywheater",30, 20, 20, 10, 10);
//		Maravilla next = new Maravilla("Maravilla",30, 10, 40, 10, 10);
//		SpecialItemWarrior next = new SpecialItemWarrior("REGALOS",50, 10, 20, 10, 10);
		next.setWarriorManager(this);
		return next;
	}

	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	public void addEnemyKilled(){
		enemiesKilled++;
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
		killedBy.put(warrior, enemyNumber);
	}

	@Override
	public Map<String, Integer> getKilledBy() {
		return killedBy;
	}

	@Override
	public void addWarriorNumberByTick(Long tick, Integer enemyNumber) {
		warriorNumberByTick.put(tick,enemyNumber);
		
	}

	@Override
	public Map<Long, Integer> getWarriorNumberByTick() {
		return warriorNumberByTick;
	}
	
	

}
