package hillbillies.model.expressions;

import hillbillies.model.VariableNotAssignedException;
import hillbillies.model.WrongTypeException;
import hillbillies.model.expressions.*;

public class And extends Expression<Boolean>{

	public And (Expression<Boolean> firstExpression ,Expression<Boolean> secondExpression){
		this.setFirstExpression(firstExpression);
		this.setSecondExpression(secondExpression);
	}
	
	public void setFirstExpression(Expression<Boolean> expression){
		this.firstExpression = expression;
	}
	
	public Expression<Boolean> getFirstExpression(){
		return this.firstExpression;
	}
	
	private Expression<Boolean> firstExpression;
	
	public void setSecondExpression(Expression<Boolean> expression){
		this.secondExpression = expression;
	}
	
	public Expression<Boolean> getSecondExpression(){
		return this.secondExpression;
	}
	
	private Expression<Boolean> secondExpression;
	
	@Override
	public Boolean evaluate() throws WrongTypeException, VariableNotAssignedException {
		return this.getFirstExpression().evaluate() && this.getSecondExpression().evaluate();
	}
	
	@Override
	public boolean isWellTyped () {
		return this.firstExpression.isWellTyped() && this.secondExpression.isWellTyped();
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Boolean.class;
	}

}
