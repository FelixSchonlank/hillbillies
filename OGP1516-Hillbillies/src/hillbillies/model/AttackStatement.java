package hillbillies.model;

public class AttackStatement extends Statement {
	
	private final Expression<Unit> victim;
	
	public AttackStatement (Expression<Unit> expression) {
		this.victim = expression;
	}
	
	public boolean execute () {
		this.getTask().getUnit().attack(this.victim.evaluate());
		return false;
	}
	
}
