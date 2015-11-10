package com.ia.managers;
import ia.battle.camp.Warrior;
import ia.battle.camp.WarriorManager;
import ia.exceptions.RuleException;

import java.util.HashMap;
import java.util.Map;

import com.ia.warriors.Maravilla;


public class TuPAPA extends WarriorManager implements Manager{
	
	private int enemiesKilled = 0;
	
	private int enemiesKilledByHunter = 0;
	
	private int enemyNumber = 0;
	private Map<String,Integer> killedBy = new HashMap<String,Integer>();
	
	private Map<Long,Integer> warriorNumberByTick = new HashMap<Long,Integer>();
	
	
	private Map<String,Integer> myDeaths = new HashMap<String,Integer>();
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "TU PAPA";
	}

	
	/**
	 * 
	 * Primer Estrategia 
	 * 
	 * Primer luchador: Maravilla
	 * Si maravilla pierde por diferencia > 5 -> se envía a Maywheater
	 * Si Mayweather pierde por dif > 3 -> se envía a Tyson
	 * 
	 * Segunda Estrategia
	 * Siempre Maravilla
	 * 
	 * 
	 * 
	 */
	@Override
	public Warrior getNextWarrior() throws RuleException {
//		Integer deaths = myDeaths.get("Maravilla");
		
//		int diff = 0;
//		if(deaths != null){
//			System.out.println("UNO Maravilla Muertes "+myDeaths.get("Maravilla"));
//			diff = deaths - enemiesKilled; 
//		}
		
		/**
		 * Si no morí o morí menos de 10 veces y está parejo.(Si la diferencia es mayor a cinco está desparejo).
		 */
//		if( (deaths == null || deaths < 10) && diff < 5 ){
////			String  name, int health, int defense, int strength, int speed, int range
//			System.out.println("DALE MARAVILLA PONE HUEVO");
//			Maravilla next = new Maravilla("Maravilla",30, 10, 40, 10, 10);
//			next.setWarriorManager(this);
//			return next;
//		}
//		deaths = myDeaths.get("Maywheater");
//		if( (deaths == null || deaths < 3) && diff < 3){
//			System.out.println("Maywheater hacelo comer guiso");
//			Mayweather next = new Mayweather("Maywheater",40, 5, 30, 15, 10);
//			next.setWarriorManager(this);
//			return next;
//		}
		
//		System.out.println("Ahi va el Maravilla Otra vez");
//		Tyson next = new Tyson("TYSON",40, 10, 20, 20, 10);
//		Maravilla next = new Maravilla("Maravilla 2",35, 5, 40, 10, 10);//  Gano 16 a 10 -> ("Maravilla",30, 10, 40, 10, 10); 
		
//		Maravilla next = new Maravilla("Maravilla 2",40, 5, 25, 20, 10);
		
		/**
		 * Después de muchas pruebas determiné que la estrategia de Maravilla es la más efectiva contra los otros adversarios.
		 */
		Maravilla next = new Maravilla("Maravilla",40, 5, 30, 15, 10);
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
	
	public void addMyDeath(String warrior){
		if(myDeaths.get(warrior) != null){
			int deaths = myDeaths.get(warrior);
			myDeaths.put(warrior,deaths+1);	
		}else{
			myDeaths.put(warrior,1);
		}
	}
	
	

}
