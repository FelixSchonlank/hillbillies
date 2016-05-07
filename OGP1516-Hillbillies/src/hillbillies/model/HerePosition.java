package hillbillies.model;

public class HerePosition extends Expression<Position> {
	
	@Override
	public Position evaluate() {
		return this.getTask().getUnit().getPosition();
	}

}
