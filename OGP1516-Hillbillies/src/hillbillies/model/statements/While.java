package hillbillies.model.statements;

import hillbillies.model.expressions.*;
import hillbillies.model.Task;
import hillbillies.model.WrongTypeException;

public class While extends Statement {
	
	private final Expression<Boolean> condition;
	private final Statement body;
	
	public While (Expression<Boolean> expression, Statement statement) {
		this.condition = expression;
		this.body = statement;
		this.body.setNext(this);
	}
	
	public Statement getBody(){
		return this.getBody();
	}
	@Override
	public Statement getNextStatement () throws WrongTypeException {
		if (this.condition.evaluate()) {
			return this.body;
		} else {
			return this.getNext();
		}
	}
	
	@Override
	public void setLinearNext (Statement linearNext) {
		super.setLinearNext(this.body);
		this.body.setLinearNext(linearNext);
	}
	
	@Override
	public void setTask (Task task) {
		super.setTask(task);
		this.body.setTask(task);
	}
	
	@Override
	public void execute () {
		
	}
	
	@Override
	public boolean isWellTyped () {
		return this.getBody().isWellTyped();
	}
	
}
