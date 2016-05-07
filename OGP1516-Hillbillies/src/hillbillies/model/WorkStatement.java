package hillbillies.model;

public class WorkStatement extends Statement {
	
	public final Expression<Position> condition;
	
	public WorkStatement (Expression<Position> expression) {
		this.condition = expression;
	}
	
	public boolean execute () {
		this.getTask().getUnit().workAt(this.condition.evaluate());
		return false;
	}
	
}
