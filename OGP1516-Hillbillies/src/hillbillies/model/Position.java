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

	/**
	 * Function to add 2 vectors
	 * @param vector1
	 * @param vector2
	 * @return
	 */
	public static Position add(VectorD vector1, VectorD vector2){
		 double X = vector1.getX() + vector2.getX();
		 double Y = vector1.getY() + vector2.getY();
		 double Z = vector1.getZ() + vector2.getZ();
		 return new Position(X, Y, Z);
	}
	
	/**
	 * Function calculating the result you get when you multiply a vector with 
	 * a given number
	 * @param vector
	 * @param x
	 * @return
	 */
	public static Position multiply(VectorD vector, double x){
		double X = vector.getX() * x;
		double Y = vector.getY() * x;
		double Z = vector.getZ() * x;
		return new Position(X, Y, Z);
	}
	
	/**
	 * Subtract a vector from an other vector
	 * @param vector1
	 * @param vector2
	 * @return
	 */
	public static Position subtract(VectorD vector1, VectorD vector2){
		return Position.add(vector1, invert(vector2));
	}
	
	/**
	 * Invert a given vector
	 * @param vector
	 * @return
	 */
	private static VectorD invert(VectorD vector) {
		return VectorD.multiply(vector, -1);
	}
}
