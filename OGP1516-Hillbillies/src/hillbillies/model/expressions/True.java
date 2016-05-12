package hillbillies.model.expressions;

import hillbillies.model.expressions.*;

public class True extends Expression<Boolean>{

	@Override
	public Boolean evaluate() {
		return true;
	}

}
