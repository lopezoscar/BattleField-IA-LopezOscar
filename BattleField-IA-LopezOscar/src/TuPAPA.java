import ia.battle.camp.Warrior;
import ia.battle.camp.WarriorManager;
import ia.exceptions.RuleException;


public class TuPAPA extends WarriorManager implements Manager{
	
	private int enemiesKilled = 0;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "TU PAPÁ";
	}

	@Override
	public Warrior getNextWarrior() throws RuleException {
		//String name, int health, int defense, int strength, int speed, int range
		Maidana next = new Maidana("MAYWHEATER",40, 10, 20, 20, 10);
		next.setWarriorManager(this);
		return next;
	}

	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	public void addEnemyKilled(){
		enemiesKilled++;
	}
	
	

}
