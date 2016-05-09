package hillbillies.model;

public class AttackStatement extends Statement {
	
	private final Expression<Unit> victim;
	
	public AttackStatement (Expression<Unit> expression) {
		this.victim = expression;
	}
	
	public boolean execute () {
		try {
			this.getTask().getUnit().attack(this.victim.evaluate());
		} catch (IllegalArgumentException | BadFSMStateException e) {
		}
		return false;
	}
	
}
