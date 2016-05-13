package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Coordinate;
import hillbillies.model.Position;

public class Selected extends Expression<Coordinate>{

	@Override
	public Coordinate evaluate() {
		return this.getTask().getPosition();
	}

}
