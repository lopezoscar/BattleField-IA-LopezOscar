import ia.battle.camp.Warrior;
import ia.battle.camp.WarriorManager;
import ia.exceptions.RuleException;


public class DonKing extends WarriorManager implements Manager{
	
	private int enemiesKilled;

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
		Tyson next = new Tyson("TYSON",60, 10, 10, 10, 10);
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

}
