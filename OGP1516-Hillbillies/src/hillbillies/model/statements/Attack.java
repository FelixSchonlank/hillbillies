package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.expressions.*;
import hillbillies.model.Unit;
import hillbillies.model.WrongTypeException;

public class Attack extends Action {
	
	private final Expression<Unit> victim;
	
	public Expression<Unit> getVictim(){
		return this.victim;
	}
	
	public Attack (Expression<Unit> expression) {
		this.victim = expression;
	}
	
	@Override
	public void execute () throws BadFSMStateException, WrongTypeException {
		this.getTask().getUnit().attack(this.victim.evaluate());
	}
	
}
