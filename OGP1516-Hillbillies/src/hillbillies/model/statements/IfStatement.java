package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Expression;
import hillbillies.model.Task;
import hillbillies.model.statements.Statement;

public class IfStatement extends Statement {
	
	private final Expression<Boolean> condition;
	private final Statement ifBody, elseBody;
	
	public IfStatement (Expression<Boolean> condition, Statement ifBody, Statement elseBody) {
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	@Override
	public Statement getNextStatement () {
		if (this.condition.evaluate()) {
			return ifBody;
		} else if (this.elseBody != null) {
			return elseBody;
		} else {
			return this.getNext();
		}
	}
	
	@Override
	public void setNext (Statement next) {
		super.setNext(next);
		this.ifBody.setNext(next);
		if (elseBody != null) {
			this.elseBody.setNext(next);
		}
	}
	
	@Override
	public void setTask (Task task) {
		super.setTask(task);
		this.ifBody.setTask(task);
		if (this.elseBody != null) {
			this.elseBody.setTask(task);
		}
	}

	@Override
	public void execute() throws BadFSMStateException {
		
	}
	
}
