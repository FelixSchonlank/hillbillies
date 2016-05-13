package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Position;

public class Here extends Expression<Position> {
	
	@Override
	public Position evaluate() {
		return this.getTask().getUnit().getPosition();
	}

}