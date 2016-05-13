package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Coordinate;


public class Here extends Expression<Coordinate> {
	
	@Override
	public Coordinate evaluate() {
		return this.getTask().getUnit().getPosition().toCoordinate();
	}

}
