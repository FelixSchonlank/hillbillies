package hillbillies.model.expressions;

import hillbillies.model.expressions.*;

public class False extends Expression<Boolean>{

	@Override
	public Boolean evaluate() {
		return false;
	}

}
