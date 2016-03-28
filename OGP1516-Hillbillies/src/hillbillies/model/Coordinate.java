package hillbillies.model;

import be.kuleuven.cs.som.annotate.Immutable;

public class Coordinate extends Vector3<Integer>{
	
	/**
	 * Obvious constructor
	 * @param x
	 * @param y
	 * @param z
	 */
	public Coordinate(Integer x, Integer y, Integer z) {
		super(x, y, z);
	}
	
	/**
	 * Obvious constructor with arguments as ints
	 * @param x
	 * @param y
	 * @param z
	 */
	public Coordinate(int x, int y, int z) {
		super((Integer) x, (Integer) y, (Integer) z);
	}

	/**
	 * Constructs a Coordinate out of an array of three Integers
	 */
	public Coordinate(Integer[] array) {
		super(array);
	}

	/**
	 * Constructs a Coordinate out of an array of three ints
	 */
	public Coordinate(int[] array) {
		super(wrapIntArray(array));
	}
	
	public static Coordinate ZERO = new Coordinate(0, 0, 0);
	public static Coordinate ONE_X = new Coordinate(1, 0, 0);
	public static Coordinate ONE_Y = new Coordinate(0, 1, 0);
	public static Coordinate ONE_Z = new Coordinate(0, 0, 1);
	public static Coordinate ONE = new Coordinate(1, 1, 1);
	
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
