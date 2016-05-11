package hillbillies.model.statements;

import hillbillies.model.Expression;

public class PrintStatement extends Statement {
	
	private final Expression<?> expression;
	
	public PrintStatement (Expression<?> expression) {
		this.expression = expression;
	}
	
	@Override
	public void execute () {
		System.out.println(this.expression.evaluate().toString());
	}
	
}
