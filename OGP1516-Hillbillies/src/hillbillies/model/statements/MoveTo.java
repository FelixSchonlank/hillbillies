package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Coordinate;
import hillbillies.model.expressions.*;
import hillbillies.model.WrongTypeException;

public class MoveTo extends Action {

	public final Expression<Coordinate> destination;

	public MoveTo (Expression<Coordinate> expression) {
		this.destination = expression;
	}

	@Override
	public void execute () throws BadFSMStateException, WrongTypeException {
		this.getTask().getUnit().moveTo(this.destination.evaluate());
	}
	
	@Override
	public boolean isWellTyped () {
		return this.destination.isWellTyped();
	}
	
}
