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

	public static VectorD ZERO = new VectorD(0d, 0d, 0d);
	public static VectorD ONE_X = new VectorD(1d, 0d, 0d);
	public static VectorD ONE_Y = new VectorD(0d, 1d, 0d);
	public static VectorD ONE_Z = new VectorD(0d, 0d, 1d);
	public static VectorD ONE = new VectorD(1d, 1d, 1d);

	/**
	 * Gives back the prime object, but normalized to have magnitude 1.
	 * @return
	 * 		The normalized version of the vector
	 */
	private VectorD normalize() {
		
		double magnitude = Math.sqrt(this.getX() + this.getY() + this.getZ());
		
		return new VectorD(this.getX()/magnitude, this.getY()/magnitude, this.getZ()/magnitude);
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
}
