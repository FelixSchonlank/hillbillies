package hillbillies.model;

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
	public Boolean evaluate() {
		return ! this.getExpression().evaluate();
	}
	
}
