package hillbillies.model;

public class VectorUtils {

	/**
	 * Checks whether x lies in between y and z. It doesn't matter if y is to
	 * its left and z to its right or the other way around; both situations
	 * count.
	 * @param x
	 *        The value that should be in the middle
	 * @param y
	 *        One of the ends
	 * @param z
	 *        The other end
	 * @return true iff x is between y and z.
	 *        | result == (y < x && x < z) || (z < x && x < y)
	 */
	private static boolean between(double x, double y, double z){
		return (y < x && x < z) || (z < x && x < y);
	}

	/**
	 * Gives the given vector, but normalized to have magnitude 1.
	 * @param vector
	 * 		The vector to be normalized
	 * @return
	 * 		The normalized version of the vector
	 */
	private static double[] normalize(double[] vector) {
		int length = vector.length;
		
		double magnitude = 0;
		for(int i=0; i<length; i++){
			magnitude += Math.pow(vector[i], 2);
		}
		magnitude = Math.sqrt(magnitude);
		
		double[] result = new double[length];
		for(int i=0; i<length; i++){
			result[i] = vector[i] / magnitude;
		}
		return result;
	}
}
