package hillbillies.model.statements;

import java.util.List;

import hillbillies.model.Task;

public class Sequence extends Statement {
	
	public List<Statement> getBody(){
		return this.body;
	}
	private final List<Statement> body;
	
	public Sequence (List<Statement> body) {
		this.body = body;
		for (int i=0; i<this.body.size()-1; i++) {
			this.body.get(i).setNext(this.body.get(i+1));
		}
		// This leaves the last element unset, and thus setNext should be
		// called.
	}
	
	@Override
	public Statement getNextStatement () {
		if (this.body.size() == 0) {
			return this.getNext();
		} else {
			return this.body.get(0);
		}
	}
	
	@Override
	public void setNext (Statement next) {
		super.setNext(next);
		this.body.get(this.body.size() - 1).setNext(next);
	}
	
	@Override
	protected void setLinearNext (Statement linearNext) {
		super.setLinearNext(this.body.get(0));
		for (int i=0; i<this.body.size()-1; i++) {
			this.body.get(i).setLinearNext(this.body.get(i+1));
		}
		this.body.get(this.body.size()-1).setLinearNext(linearNext);
	}
	
	@Override
	public void setTask (Task task) {
		super.setTask(task);
		for (Statement subStatement : this.body) {
			subStatement.setTask(task);
		}
	}
	
	@Override
	public void execute () {
		
	}
	
}
