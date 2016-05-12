package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.expressions.*;
import hillbillies.model.Position;

public class MoveTo extends Action {

	public final Expression<Position> destination;

	public MoveTo (Expression<Position> expression) {
		this.destination = expression;
	}

	@Override
	public void execute () throws BadFSMStateException {
		this.getTask().getUnit().moveTo(this.destination.evaluate().toCoordinate());
	}
	
}
