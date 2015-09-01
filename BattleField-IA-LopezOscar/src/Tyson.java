import ia.battle.camp.BattleField;
import ia.battle.camp.FieldCell;
import ia.battle.camp.FieldCellType;
import ia.battle.camp.Warrior;
import ia.battle.camp.actions.Action;
import ia.battle.camp.actions.Attack;
import ia.exceptions.RuleException;

import java.util.ArrayList;
import java.util.List;

import com.ia.pathfinder.PathFinder;


public class Tyson extends Warrior{

	public Tyson(String name, int health, int defense, int strength, int speed,
			int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		System.out.println(this.getName()+" move to "+this.getPosition());
		MoveWarrior move = new MoveWarrior();
//		ArrayList<FieldCell> path = new ArrayList<FieldCell>();
//		
//		List<FieldCell> adjacentCells = BattleField.getInstance().getAdjacentCells(this.getPosition());
//		for (FieldCell fieldCell : adjacentCells) {
//			if(fieldCell.getX() > this.getPosition().getX() && fieldCell.getFieldCellType().equals(FieldCellType.NORMAL)){
//				path.add(fieldCell);
//			}else if(fieldCell.getY() > this.getPosition().getY() && fieldCell.getFieldCellType().equals(FieldCellType.NORMAL)){
//				path.add(fieldCell);
//			}
//			
//			if(fieldCell.getFieldCellType().equals(FieldCellType.BLOCKED)){
//				System.out.println("ROMPIO PARED "+this.getName());
//				return new Attack(fieldCell);
//			}
//		}
		
		ArrayList<FieldCell> path = PathFinder.getInstance().findPath(this.getPosition(), BattleField.getInstance().getEnemyData().getFieldCell());
		System.out.println(path);
		
		move.setMoves(path);
		
		
		return move;
	}

	@Override
	public void wasAttacked(int damage, FieldCell source) {
		System.out.println("DAMAGE "+damage);
		System.out.println("FIELD SOURCE "+source);
	}

	@Override
	public void enemyKilled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub
		
	}
	

}
