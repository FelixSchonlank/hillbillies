package hillbillies.model;

import java.util.Random;
import java.util.Set;

public class Utils {
	
	/**
	 * The Random generator used for this game.
	 */
	private static Random random = new Random();
	
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
	
	/**
	 * Generates a random integer between lo (inclusive) and hi (exclusive).
	 * @param lo
	 * 		The lower bound (inclusive)
	 * @param hi
	 * 		The upper bound (exclusive)
	 * @return
	 * 		A random integer between lo and hi.
	 * @throws IllegalArgumentException
	 * 		If the given hi is not higher than lo.
	 * @effect
	 * 		random.nextInt(...)
	 */
	public static int randomInt(int lo, int hi) throws IllegalArgumentException {
		if (hi <= lo) {
			throw new IllegalArgumentException("hi must be higher than lo.");
		}
		return (random.nextInt(hi-lo) + lo);
	}
	
	/**
	 * Generates a random double between lo (inclusive) and hi (exclusive).
	 * @param lo
	 * 		The lower bound (inclusive)
	 * @param hi
	 * 		The upper bound (exclusive)
	 * @return
	 * 		A random double between lo and hi.
	 * @throws IllegalArgumentException
	 * 		If the given hi is not higher than lo.
	 * @effect
	 * 		randomDouble()
	 */
	public static double randomDouble(double lo, double hi) throws IllegalArgumentException {
		if (hi <= lo) {
			throw new IllegalArgumentException("hi must be higher than lo.");
		}
		return (randomDouble() * (hi - lo) + lo);
	}
	
	/**
	 * Generates a random double just like Random.nextDouble().
	 * @return
	 * 		A random double ∈ [0; 1[
	 */
	public static double randomDouble() {
		return random.nextDouble();
	}
	
	/**
	 * Generates a random boolean with a given chance.
	 * @param chance
	 * 		The chance the returned boolean will be true.
	 * @return
	 * 		true or false, with a given chance for true, and (1-chance) for
	 * 		false.
	 * @throws IllegalArgumentException
	 * 		If the given chance is not ∈ [0; 1].
	 * @effect
	 * 		randomDouble()
	 */
	public static boolean randomBoolean(double chance) throws IllegalArgumentException {
		if (chance < 0 || 1 < chance) {
			throw new IllegalStateException("chance must be ∈ [0; 1]");
		}
		return (randomDouble() < chance);
	}
	
	/**
	 * Generates a random boolean with a 50% chance for either option.
	 * @return
	 * 		true or false.
	 * @effect
	 * 		random.nextDouble()
	 */
	public static boolean randomBoolean() {
		return randomBoolean(0.5d);
	}
	
	/**
	 * Gives back a random element in the given Set.
	 * @return
	 * 		A random element in the given Set, or null if the Set is empty or
	 * 		null itself.
	 */
	public static <T> Object getRandomElement(Set<T> set) {
		if (set != null) {
			int n = randomInt(0, set.size());
			int i = 0;
			for (Object object : set) {
				if (i == n) {
					return object;
				}
				i += 1;
			}
		}
		return null;
	}
	
	/**
	 * Gives back the given array, but of primitive type double[] instead of
	 * Double[].
	 * @param array
	 * 		The array to have unboxed.
	 * @return
	 * 		A new array containing the same items as the given array, only they
	 * 		are primitive doubles instead of Doubles.
	 */
	public static double[] unboxArray(Double[] array) {
		double[] result = new double[array.length];
		for (int i=0; i<array.length; i++) {
			result[i] = array[i].doubleValue();
		}
		return result;
	}
	
	/**
	 * Gives back the given array, but of primitive type int[] instead of
	 * Integer[].
	 * @param array
	 * 		The array to have unboxed.
	 * @return
	 * 		A new array containing the same items as the given array, only they
	 * 		are primitive ints instead of Integers.
	 */
	public static int[] unboxArray(Integer[] array) {
		int[] result = new int[array.length];
		for (int i=0; i<array.length; i++) {
			result[i] = array[i];
		}
		return result;
	}
}
