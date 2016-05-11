package hillbillies.model.statements;

import hillbillies.model.expressions.*;;

public class PrintStatement extends Statement {
	
	public Expression<?> getExpression(){
		return this.expression;
	}
	
	private final Expression<?> expression;
	
	public PrintStatement (Expression<?> expression) {
		this.expression = expression;
	}
	
	@Override
	public void execute () {
		System.out.println(this.expression.evaluate().toString());
	}
	
}
