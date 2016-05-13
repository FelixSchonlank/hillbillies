package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.expressions.*;
import hillbillies.model.Task;
import hillbillies.model.WrongTypeException;
import hillbillies.model.statements.Statement;

public class If extends Statement {
	
	private final Expression<Boolean> condition;
	private final Statement ifBody, elseBody;
	
	public Statement getIfBody(){
		return this.ifBody;
	}
	
	public Statement getElseBody(){
		return this.elseBody;
	}	
	
	public If (Expression<Boolean> condition, Statement ifBody, Statement elseBody) {
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	@Override
	public Statement getNextStatement () throws WrongTypeException {
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
	protected void setLinearNext (Statement linearNext) {
		super.setLinearNext(this.getIfBody());
		if (this.getElseBody() != null) {
			this.getIfBody().setLinearNext(this.getElseBody());
			this.getElseBody().setLinearNext(linearNext);
		} else {
			this.getIfBody().setLinearNext(linearNext);
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
