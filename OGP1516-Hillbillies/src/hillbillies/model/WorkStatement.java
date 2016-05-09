
package hillbillies.model;

public class WorkStatement extends Statement {
	
	public final Expression<Position> condition;
	
	public WorkStatement (Expression<Position> expression) {
		this.condition = expression;
	}
	
	public boolean execute () {
		try {
			this.getTask().getUnit().work(this.condition.evaluate().toCoordinate());
		} catch (BadFSMStateException e) {
		}
		return false;
	}
	
}
