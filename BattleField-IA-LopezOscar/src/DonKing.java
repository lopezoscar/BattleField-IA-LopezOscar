import ia.battle.camp.Warrior;
import ia.battle.camp.WarriorManager;
import ia.exceptions.RuleException;


public class DonKing extends WarriorManager{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Don King";
	}

	@Override
	public Warrior getNextWarrior() throws RuleException {
		// TODO Auto-generated method stub
		return createWarrior();
	}
	
	private Warrior createWarrior() throws RuleException{
		//String name, int health, int defense, int strength, int speed, int range
		return new Tyson("MAIDANA",60, 10, 10, 10, 10);
	}

}
