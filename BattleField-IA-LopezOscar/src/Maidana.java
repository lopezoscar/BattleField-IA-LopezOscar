import ia.battle.camp.BattleField;
import ia.battle.camp.FieldCell;
import ia.battle.camp.Warrior;
import ia.battle.camp.actions.Action;
import ia.battle.camp.actions.Attack;
import ia.battle.camp.actions.Skip;
import ia.exceptions.RuleException;

import java.util.ArrayList;

import com.ia.pathfinder.PathFinder;



public class Maidana extends Warrior{
	
	private Manager wm;
	
	public Maidana(String name, int health, int defense, int strength,
			int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
	}

	private int wasKilled = 0;
	
	private boolean alreadyAttack; 
	
	private FieldCell lastPos;
	
	@Override
	public Action playTurn(long tick, int actionNumber) {
		if(actionNumber == 0){
			System.out.println("LAST POS "+lastPos);
			lastPos = this.getPosition();	
		}
		
		if(wasKilled < 15){
//			System.out.println("SOY "+this.getName()+" y estoy en "+this.getPosition());
			MoveWarrior move = new MoveWarrior();
			if(BattleField.getInstance().getEnemyData().getInRange()){
//				System.out.println("ATTACK TO "+BattleField.getInstance().getEnemyData().getFieldCell());
				if(alreadyAttack && actionNumber > 1){
					System.out.println("YA ATAQUE ME ESCAPO");
					System.out.println("VOLVIENDO A "+lastPos);
					ArrayList<FieldCell> path = PathFinder.getInstance().findPath(this.getPosition(), lastPos);
					move.setMoves(path);
					alreadyAttack = false;
					return move;
				}else{
					System.out.println("ATACO a "+BattleField.getInstance().getEnemyData().getName());
					alreadyAttack = true;
					return new Attack(BattleField.getInstance().getEnemyData().getFieldCell());	
				}
			}else{
//				System.out.println("MOVES TO "+BattleField.getInstance().getEnemyData().getFieldCell());
				ArrayList<FieldCell> path = PathFinder.getInstance().findPath(this.getPosition(), BattleField.getInstance().getEnemyData().getFieldCell());
				move.setMoves(path);
				return move;
			}			
		}else{
			System.out.println("SOY  "+this.getName()+" mate a "+wasKilled);
			return new Skip();
		}
		
	}

	@Override
	public void wasAttacked(int damage, FieldCell source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enemyKilled() {
		wasKilled++;
		wm.addEnemyKilled();
		System.out.println("SOY "+this.getName()+" TOTAL ENEMIES KILLED "+wm.getEnemiesKilled());
//		System.out.println("MATE A "+BattleField.getInstance().getEnemyData().getName()+" "+wasKilled+" veces sin morir");
		
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub
		
	}
	
	public void setWarriorManager(Manager wm){
		this.wm = wm;
	}

}
