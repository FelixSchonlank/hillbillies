package hillbillies.model;

public class This extends Expression<Unit> {

	@Override
	public Unit evaluate() {
		return this.getTask().getUnit();
	}

}
