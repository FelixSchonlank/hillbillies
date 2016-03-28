package hillbillies.model;

import be.kuleuven.cs.som.annotate.Immutable;

public class Position extends VectorD {

	public Position(Double x, Double y, Double z) {
		super(x, y, z);
	}
	
	public Position(double x, double y, double z) {
		super(x, y, z);
	}

	public Position(Double[] array) {
		super(array);
	}
	
	public Position(double[] array) {
		super(array);
	}
	
	/**
	 * Returns a Coordinate representing the cube that this position is located in.
	 * 
	 * @return
	 * 		A coordinate.
	 */
	@Immutable
	public Coordinate toCoordinate() {
		return new Coordinate(this.getX().intValue(), this.getY().intValue(), this.getZ().intValue());
	}

}
