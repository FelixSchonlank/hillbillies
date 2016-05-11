package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Expression;
import hillbillies.model.Position;

public class MoveToStatement extends Statement {
	
	public final Expression<Position> destination;
	
	public MoveToStatement (Expression<Position> expression) {
		this.destination = expression;
	}
	
	public boolean execute () {
		try {
			this.getTask().getUnit().moveTo(this.destination.evaluate().toCoordinate());
		} catch (IllegalArgumentException | BadFSMStateException e) {
		}
		return false;
	}
	
}
