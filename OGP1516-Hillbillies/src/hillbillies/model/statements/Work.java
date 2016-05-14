
package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Coordinate;
import hillbillies.model.expressions.*;
import hillbillies.model.WrongTypeException;

public class Work extends Action {
	
	public final Expression<Coordinate> condition;
	
	public Work (Expression<Coordinate> expression) {
		this.condition = expression;
	}
	
	@Override
	public void execute () throws BadFSMStateException, WrongTypeException {
		this.getTask().getUnit().work(this.condition.evaluate());
	}
	
	@Override
	public boolean isWellTyped () {
		return this.condition.isWellTyped();
	}
	
}
