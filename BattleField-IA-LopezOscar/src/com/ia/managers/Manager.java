package com.ia.managers;
import java.util.Map;


public interface Manager {
	
	void addEnemyKilled();
	int getEnemiesKilled();
	void addEnemyKilledByHunter();
	int getEnemiesKilledByHunter();
	void setEnemyNumber(int enemyNumber);
	int getEnemyNumber();
	
	void addKilledBy(String warrior,Integer enemyNumber);
	Map<String,Integer> getKilledBy();
	
	void addWarriorNumberByTick(Long tick,Integer enemyNumber);
	Map<Long,Integer> getWarriorNumberByTick();
	
	void addMyDeath(String warrior);
	
	

}
