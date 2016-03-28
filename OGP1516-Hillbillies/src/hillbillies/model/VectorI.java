package hillbillies.model;

import be.kuleuven.cs.som.annotate.Immutable;

public class VectorI extends Vector3<Integer>{
	
	/**
	 * Obvious constructor
	 * @param x
	 * @param y
	 * @param z
	 */
	public VectorI(Integer x, Integer y, Integer z) {
		super(x, y, z);
	}
	
	/**
	 * Obvious constructor with arguments as ints
	 * @param x
	 * @param y
	 * @param z
	 */
	public VectorI(int x, int y, int z) {
		super((Integer) x, (Integer) y, (Integer) z);
	}

	/**
	 * Constructs a VectorI out of an array of three Integers
	 */
	public VectorI(Integer[] array) {
		super(array);
	}

	/**
	 * Constructs a VectorI out of an array of three ints
	 */
	public VectorI(int[] array) {
		super(wrapIntArray(array));
	}
	
	public static VectorI ZERO = new VectorI(0, 0, 0);
	public static VectorI ONE_X = new VectorI(1, 0, 0);
	public static VectorI ONE_Y = new VectorI(0, 1, 0);
	public static VectorI ONE_Z = new VectorI(0, 0, 1);
	public static VectorI ONE = new VectorI(1, 1, 1);
	
	/**
	 * Private helper method to transform an array of ints into an array of Integers
	 * @param array
	 * @return
	 * 		The exact same array, with all elements cast to Integers.
	 */
	private static Integer[] wrapIntArray(int[] array) {
		Integer[] result = new Integer[array.length];
		for(int i=0; i<array.length; i++) {
			result[i] = (Integer) array[i];
		}
		return result;
	}
	
}
