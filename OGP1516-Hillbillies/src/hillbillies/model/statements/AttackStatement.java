package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.expressions.*;
import hillbillies.model.Unit;

public class AttackStatement extends Statement {
	
	private final Expression<Unit> victim;
	
	public Expression<Unit> getVictim(){
		return this.victim;
	}
	
	public AttackStatement (Expression<Unit> expression) {
		this.victim = expression;
	}
	
	public boolean execute () {
		try {
			this.getTask().getUnit().attack(this.victim.evaluate());
		} catch (IllegalArgumentException | BadFSMStateException e) {
		}
		return false;
	}
	
}
