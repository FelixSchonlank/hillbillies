package hillbillies.model.expressions;

import hillbillies.model.expressions.*;

public class Or  extends Expression<Boolean>{
	public Or (Expression<Boolean> firstExpression ,Expression<Boolean> secondExpression){
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
	public Boolean evaluate() {
		return this.getFirstExpression().evaluate() || this.getSecondExpression().evaluate();
	}
}
