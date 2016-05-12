package hillbillies.model.expressions;

import hillbillies.model.expressions.*;
import hillbillies.model.Unit;

public class This extends Expression<Unit> {

	@Override
	public Unit evaluate() {
		return this.getTask().getUnit();
	}

}
