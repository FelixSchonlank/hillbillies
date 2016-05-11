package hillbillies.model.expressions;

import hillbillies.model.Expression;

public class False extends Expression<Boolean>{

	@Override
	public Boolean evaluate() {
		return false;
	}

}
