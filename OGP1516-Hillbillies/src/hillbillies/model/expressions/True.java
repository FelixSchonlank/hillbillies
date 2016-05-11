package hillbillies.model.expressions;

import hillbillies.model.Expression;

public class True extends Expression<Boolean>{

	@Override
	public Boolean evaluate() {
		return true;
	}

}
