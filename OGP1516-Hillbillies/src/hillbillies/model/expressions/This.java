package hillbillies.model.expressions;

import hillbillies.model.Expression;
import hillbillies.model.Unit;

public class This extends Expression<Unit> {

	@Override
	public Unit evaluate() {
		return this.getTask().getUnit();
	}

}
