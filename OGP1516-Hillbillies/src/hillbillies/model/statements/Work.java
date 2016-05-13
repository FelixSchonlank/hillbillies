
package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.expressions.*;
import hillbillies.model.Position;
import hillbillies.model.WrongTypeException;

public class Work extends Action {
	
	public final Expression<Position> condition;
	
	public Work (Expression<Position> expression) {
		this.condition = expression;
	}
	
	public void execute () throws BadFSMStateException, WrongTypeException {
		this.getTask().getUnit().work(this.condition.evaluate().toCoordinate());
	}
	
}
