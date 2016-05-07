package hillbillies.model;

public class MoveToStatement extends Statement {
	
	public final Expression<Position> destination;
	
	public MoveToStatement (Expression<Position> expression) {
		this.destination = expression;
	}
	
	public boolean execute () {
		this.getTask().getUnit().moveTo(this.destination.evaluate());
		return false;
	}
	
}
