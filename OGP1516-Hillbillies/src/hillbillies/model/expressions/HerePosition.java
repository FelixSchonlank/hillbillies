package hillbillies.model.expressions;

import hillbillies.model.Expression;
import hillbillies.model.Position;

public class HerePosition extends Expression<Position> {
	
	@Override
	public Position evaluate() {
		return this.getTask().getUnit().getPosition();
	}

}
