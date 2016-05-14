package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Coordinate;
import hillbillies.model.Position;

public class Selected extends Expression<Coordinate>{

	@Override
	public Coordinate evaluate() {
		return this.getTask().getPosition();
	}
	
	@Override
	public boolean isWellTyped () {
		return true;
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Coordinate.class;
	}

}
