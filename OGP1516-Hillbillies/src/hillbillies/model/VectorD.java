package hillbillies.model;

import be.kuleuven.cs.som.annotate.Immutable;

public class VectorD extends Vector3<Double> {
	
	/**
	 * Obvious constructor
	 * @param x
	 * @param y
	 * @param z
	 */
	public VectorD (Double x, Double y, Double z) {
		super(x, y, z);
	}
	
	/**
	 * Obvious constructor with arguments as doubles
	 * @param x
	 * @param y
	 * @param z
	 */
	public VectorD(double x, double y, double z) {
		super((Double) x, (Double) y, (Double) z);
	}

	/**
	 * Constructs a DVector out of an array of three Doubles
	 * @param array
	 */
	public VectorD (Double[] array) {
		super(array);
	}
	
	/**
	 * Constructs a DVector out of an array of three doubles
	 * @param array
	 */
	public VectorD(double[] array) {
		super(wrapDoubleArray(array));
	}

	/**
	 * Function to add 2 vectors
	 * @param vector1
	 * @param vector2
	 * @return
	 */
	public static VectorD add(VectorD vector1, VectorD vector2){
		 double X = vector1.getX() + vector2.getX();
		 double Y = vector1.getY() + vector2.getY();
		 double Z = vector1.getZ() + vector2.getZ();
		 return new VectorD(X, Y, Z);
	}
	
	/**
	 * Function calculating the result you get when you multiply a vector with 
	 * a given number
	 * @param vector
	 * @param x
	 * @return
	 */
	public static VectorD multiply(VectorD vector, double x){
		double X = vector.getX() * x;
		double Y = vector.getY() * x;
		double Z = vector.getZ() * x;
		return new VectorD(X, Y, Z);
	}
	
	/**
	 * Subtract a vector from an other vector
	 * @param vector1
	 * @param vector2
	 * @return
	 */
	public static VectorD subtract(VectorD vector1, VectorD vector2){
		return VectorD.add(vector2, invert(vector2));
	}
	
	/**
	 * Invert a given vector
	 * @param vector
	 * @return
	 */
	private static VectorD invert(VectorD vector) {
		return VectorD.multiply(vector, -1);
	}
	
	/**
	 * return a position with the same properties of this vectorD
	 * @return
	 */
	public Position toPosition(){
		double X = this.getX();
		double Y = this.getY();
		double Z = this.getZ();
		return new Position(X, Y, Z);
	}

	public static VectorD ZERO = new VectorD(0d, 0d, 0d);
	public static VectorD ONE_X = new VectorD(1d, 0d, 0d);
	public static VectorD ONE_Y = new VectorD(0d, 1d, 0d);
	public static VectorD ONE_Z = new VectorD(0d, 0d, 1d);
	public static VectorD ONE = new VectorD(1d, 1d, 1d);

	/**
	 * Gives back the given object, but normalized to have magnitude 1.
	 * @param vector
	 * 		The vector to have normalized.
	 * @return
	 * 		The normalized version of the vector
	 */
	public static VectorD normalize(VectorD vector) {
		
		double magnitude = Math.sqrt(vector.getX()*vector.getX() + vector.getY()*vector.getY() + vector.getZ()*vector.getZ());
		
		return new VectorD(vector.getX()/magnitude, vector.getY()/magnitude, vector.getZ()/magnitude);
	}
	
	/**
	 * Tells whether the prime object is between first and second given VectorD
	 * (or the other way around: the order of first and second doesn't matter) 
	 * @param first
	 * @param second
	 * @return
	 * 		Whether the prime object is in between the other two.
	 */
	public boolean between(VectorD first, VectorD second) {
		return (Utils.between(this.getX(), first.getX(), second.getX())
				|| Utils.between(this.getY(), first.getY(), second.getY())
				|| Utils.between(this.getZ(), first.getZ(), second.getZ()));
	}
	
	/**
	 * Private helper method to transform an array of doubles into an array of Doubles
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

	/**
	 * Returns an array of three doubles, representing this Position.
	 * @return
	 * 		The array 
	 */
	@Immutable
	public Double[] toArray() {
		// Type cast will always be possible
		return new Double[] {this.getX(), this.getY(), this.getZ()};
	}
}
