package hillbillies.model.statements;

import hillbillies.model.expressions.*;

public class AssignmentStatement extends Statement {

	private final String variableName;
	
	public String getName(){
		return this.variableName;
	}
	
	private final Expression<?> expression; // TODO make sure we use the right generics, and no raw types.
	
	public AssignmentStatement (String variableName, Expression expression) {
		this.variableName = variableName;
		this.expression = expression;
	}
	
	@Override
	public void execute() {
		this.getTask().addVariable(variableName, this.expression.evaluate());
	}
	
}
