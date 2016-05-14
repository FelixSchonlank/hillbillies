package hillbillies.model.expressions;

import hillbillies.model.expressions.*;

public class False extends Expression<Boolean>{

	@Override
	public Boolean evaluate() {
		return false;
	}
	
	@Override
	public boolean isWellTyped () {
		return true;
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Boolean.class;
	}

}
