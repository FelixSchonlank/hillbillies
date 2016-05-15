package hillbillies.model.expressions;

import hillbillies.model.VariableNotAssignedException;
import hillbillies.model.WrongTypeException;
import hillbillies.model.expressions.*;

public class Not extends Expression<Boolean>{

	public Not (Expression<Boolean> expression){
		this.setExpression(expression);
	}
	
	public void setExpression(Expression<Boolean> expression){
		this.expression = expression;
	}
	
	public Expression<Boolean> getExpression(){
		return this.expression;
	}
	
	private Expression<Boolean> expression;
	
	@Override
	public Boolean evaluate() throws WrongTypeException, VariableNotAssignedException {
		return ! this.getExpression().evaluate();
	}
	
	@Override
	public boolean isWellTyped () {
		return this.getExpression().isWellTyped();
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Boolean.class;
	}
	
}
