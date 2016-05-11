
package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Expression;
import hillbillies.model.Position;

public class WorkStatement extends Statement {
	
	public final Expression<Position> condition;
	
	public WorkStatement (Expression<Position> expression) {
		this.condition = expression;
	}
	
	public boolean execute () {
		try {
			this.getTask().getUnit().work(this.condition.evaluate().toCoordinate());
		} catch (BadFSMStateException e) {
		}
		return false;
	}
	
}
