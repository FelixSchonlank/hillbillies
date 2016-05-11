package hillbillies.model.statements;

import hillbillies.model.Expression;
import hillbillies.model.statements.Statement;

public class IfStatement extends Statement {
	
	private final Expression<Boolean> condition;
	private final Statement ifBody, elseBody;
	
	public IfStatement (Expression<Boolean> condition, Statement ifBody, Statement elseBody) {
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	public Statement getNextStatement () {
		if (this.condition.evaluate()) {
			return ifBody;
		} else if (this.elseBody != null) {
			return elseBody;
		} else {
			return this.getNext();
		}
	}
	
	public void setNext (Statement next) {
		this.next = next;
		this.ifBody.setNext(next);
		if (elseBody != null) {
			this.elseBody.setNext(next);
		}
	}
	
}
