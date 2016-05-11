
package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.expressions.*;
import hillbillies.model.Position;

public class WorkStatement extends ActionStatement {
	
	public final Expression<Position> condition;
	
	public WorkStatement (Expression<Position> expression) {
		this.condition = expression;
	}
	
	public void execute () throws BadFSMStateException {
		this.getTask().getUnit().work(this.condition.evaluate().toCoordinate());
	}
	
}
