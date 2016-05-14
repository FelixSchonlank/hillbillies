package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Coordinate;


public class Here extends Expression<Coordinate> {
	
	@Override
	public Coordinate evaluate() {
		return this.getTask().getUnit().getPosition().toCoordinate();
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
