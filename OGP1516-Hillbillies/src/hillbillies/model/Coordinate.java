package hillbillies.model;

import be.kuleuven.cs.som.annotate.Immutable;

public class Coordinate extends VectorI {
	
	public Coordinate(int x, int y, int z) {
		super(x, y, z);
	}

	public Coordinate(int[] array) {
		super(array);
	}

	public Coordinate(Integer x, Integer y, Integer z) {
		super(x, y, z);
	}

	public Coordinate(Integer[] array) {
		super(array);
	}

	/**
	 * Returns a Position representing the center of the cube with this coordinate.
	 * 
	 * @return
	 * 		A coordinate.
	 */
	@Immutable
	public Position toPosition() {
		return new Position(this.getX()+0.5d, this.getY()+0.5d, this.getZ()+0.5d);
	}
}
