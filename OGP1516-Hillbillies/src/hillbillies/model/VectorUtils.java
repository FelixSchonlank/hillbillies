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
	 */
	public static boolean between(Comparable x, Comparable y, Comparable z){
		return ((y.compareTo(x)<0 && x.compareTo(z)<0) || 
				(z.compareTo(x)<0 && x.compareTo(y)<0));
	}
}
