package hillbillies.model.expressions;

import hillbillies.model.expressions.*;

public class True extends Expression<Boolean>{

	@Override
	public Boolean evaluate() {
		return true;
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
