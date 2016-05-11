package hillbillies.model.statements;

import hillbillies.model.expressions.*;
import hillbillies.model.Task;

public class WhileStatement extends Statement {
	
	private final Expression<Boolean> condition;
	private final Statement body;
	
	public WhileStatement (Expression<Boolean> expression, Statement statement) {
		this.condition = expression;
		this.body = statement;
		this.body.setNext(this);
	}
	
	public Statement getBody(){
		return this.getBody();
	}
	@Override
	public Statement getNextStatement () {
		if (this.condition.evaluate()) {
			return this.body;
		} else {
			return this.getNext();
		}
	}
	
	@Override
	public void setTask (Task task) {
		super.setTask(task);
		this.body.setTask(task);
	}
	
	@Override
	public void execute () {
		
	}
	
}
