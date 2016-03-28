package hillbillies.model;

import be.kuleuven.cs.som.annotate.Immutable;

public class Position extends Vector3<Double> {
	
	/**
	 * Obvious constructor
	 * @param x
	 * @param y
	 * @param z
	 */
	public Position (Double x, Double y, Double z) {
		super(x, y, z);
	}
	
	/**
	 * Obvious constructor with arguments as ints
	 * @param x
	 * @param y
	 * @param z
	 */
	public Position(double x, double y, double z) {
		super((Double) x, (Double) y, (Double) z);
	}

	/**
	 * Constructs a Position out of an array of three Doubles
	 */
	public Position (Double[] array) {
		super(array);
	}
	
	/**
	 * Constructs a Coordinate out of an array of three doubles
	 */
	public Position(double[] array) {
		super(wrapDoubleArray(array));
	}

	public static Position ZERO = new Position(0d, 0d, 0d);
	public static Position ONE_X = new Position(1d, 0d, 0d);
	public static Position ONE_Y = new Position(0d, 1d, 0d);
	public static Position ONE_Z = new Position(0d, 0d, 1d);
	public static Position ONE = new Position(1d, 1d, 1d);

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
	
	/**
	 * Private helper method to transform an array of double into an array of Doubles
	 * @param array
	 * @return
	 * 		The exact same array, with all elements cast to Doubles.
	 */
	private static Double[] wrapDoubleArray(double[] array) {
		Double[] result = new Double[array.length];
		for(int i=0; i<array.length; i++) {
			result[i] = (Double) array[i];
		}
		return result;
	}
}
