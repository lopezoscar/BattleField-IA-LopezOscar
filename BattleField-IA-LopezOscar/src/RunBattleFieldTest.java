

import ia.battle.camp.BattleField;
import ia.battle.camp.BattleFieldListener;
import ia.battle.camp.FieldCell;
import ia.battle.camp.Warrior;
import ia.battle.camp.WarriorManager;

import org.junit.Test;

import com.ia.managers.DonKing;


public class RunBattleFieldTest {
	
	private WarriorManager wm1, wm2;
	
	
	@Test
	public void test(){
		wm1 = new DonKing();
		wm2 = new DonKing();
		BattleField.getInstance().addWarriorManager(wm1);
		BattleField.getInstance().addWarriorManager(wm2);
		
		BattleField.getInstance().fight();
		BattleField.getInstance().showResult();

	}

}
