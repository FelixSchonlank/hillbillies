package hillbillies.model;

import java.util.Arrays;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class World {
	
	/**
	 * Return the maximum coordinate in every direction of the Game World
	 * @return the maximum coordinate of a unit
	 * 		| result == maxCoordinate
	 */
	@Basic @Immutable
	public static double getMaxCoordinate(){
		return maxCoordinate;
	}
	
	/**
	 * Return the minimum coordinate in every direction of the board
	 * @return The minimum coordinate of a unit
	 * 		| result == minCoordinate
	 */
	@Basic @Immutable
	public static double getMinCoordinate(){
		return minCoordinate;
	}

	/**
	 * Gives back the integer coordinates of the cube that the given position is in.
	 * @param position
	 * 		The given position
	 * @return The integer coordinates of the cube that the given position is in.
	 * 		| result == new int[] {(int)position[0], (int)position[1], (int)position[2]}
	 * @throws IllegalArgumentException
	 * 		If position is not inside any cube
	 * 		| !withinBounds(position)
	 */
	private static int[] cubeCoordinates(double[] position) throws IllegalArgumentException {
		if(!withinBounds(position)){
			throw new IllegalArgumentException("position out of bounds: " + position);
		}
		return new int[] {(int)position[0], (int)position[1], (int)position[2]};
	}
	
	/**
	 * Tells whether the two cubes are adjacent.
	 * @param cube1
	 * 		The first cube to check
	 * @param cube2
	 * 		The second cube to check
	 * @return
	 * 		true iff the two cubes are adjacent.
	 * 		| result == (cube2[0]-cube1[0] == -1 || cube2[0]-cube1[0] == 0 || cube2[0]-cube1[0] == 1) &&
	 * 		| (cube2[1]-cube1[1] == -1 || cube2[1]-cube1[1] == 0 || cube2[1]-cube1[1] == 1) &&
	 * 		| (cube2[2]-cube1[2] == -1 || cube2[2]-cube1[2] == 0 || cube2[2]-cube1[2] == 1);
	 * @throws IllegalArgumentException
	 * 		If the first cube is not within game bounds
	 * 		| !withinBounds(cube1)
	 * @throws IllegalArgumentException
	 * 		If the second cube is not within game bounds
	 * 		| !withinBounds(cube2)
	 * @throws IllegalArgumentException
	 * 		If the first cube doesn't have the right number of coordinates
	 * 		| cube1.length != 3
	 * @throws IllegalArgumentException
	 * 		If the second cube doesn't have the right number of coordinates
	 * 		| cube2.length != 3
	 */
	private static boolean areAdjacentCubes(int[] cube1, int[] cube2) throws IllegalArgumentException {
		if(!withinBounds(cube1)){
			throw new IllegalArgumentException("First cube is not within game bounds: " + cube1.toString());
		}
		if(!withinBounds(cube2)){
			throw new IllegalArgumentException("Second cube is not within game bounds: " + cube2.toString());
		}
		if(cube1.length != 3){
			throw new IllegalArgumentException("First cube has wrong dimension (" + cube1.length + "): " + cube1.toString());
		}
		if(cube2.length != 3){
			throw new IllegalArgumentException("Second cube has wrong dimension (" + cube2.length + "): " + cube2.toString());
		}
		return (cube2[0]-cube1[0] == -1 || cube2[0]-cube1[0] == 0 || cube2[0]-cube1[0] == 1) &&
				(cube2[1]-cube1[1] == -1 || cube2[1]-cube1[1] == 0 || cube2[1]-cube1[1] == 1) &&
				(cube2[2]-cube1[2] == -1 || cube2[2]-cube1[2] == 0 || cube2[2]-cube1[2] == 1);
	}
	
	/**
	 * Gives back the position of the center of the cube with given coordinates
	 * @param coordinates
	 * 		The coordinates of the cube to calculate the center of
	 * @return
	 * 		The position of the center of the cube with given coordinates
	 * 		| result == new double[] {
	 * 		| 	((double)coordinates[0])+0.5,
	 * 		| 	((double)coordinates[1])+0.5,
	 * 		| 	((double)coordinates[2])+0.5
	 * 		| }
	 * @throws IllegalArgumentException
	 * 		If the given coordinates are out of bounds
	 * 		| !withinBounds(coordinates)
	 * @throws IllegalArgumentException
	 * 		If the given coordinates are not of the right dimension
	 * 		| coordinates.length != 3
	 */
	private static double[] cubeCenter(int[] coordinates) throws IllegalArgumentException {
		if(coordinates.length != 3){
			throw new IllegalArgumentException("coordinates dimension should be 3.");
		}
		if(!withinBounds(coordinates)){
			throw new IllegalArgumentException("coordinates out of bounds.");
		}
		return new double[] {((double)coordinates[0])+0.5, ((double)coordinates[1])+0.5, ((double)coordinates[2])+0.5};
	}
	
	/**
	 * Tells whether the given cube coordinate is within the game bounds
	 * @param coordinates
	 * 		The cube you want to check
	 * @return
	 * 		true iff the cube is within the game bounds
	 * 		| result == (coordinates[0] >= getMinCoordinate() && coordinates[0] < getMaxCoordinate() &&
	 * 		| coordinates[1] >= getMinCoordinate() && coordinates[1] < getMaxCoordinate() &&
	 * 		| coordinates[2] >= getMinCoordinate() && coordinates[2] < getMaxCoordinate());
	 */
	private static boolean withinBounds(int[] coordinates) {
		return (coordinates[0] >= getMinCoordinate() && coordinates[0] < getMaxCoordinate() &&
				coordinates[1] >= getMinCoordinate() && coordinates[1] < getMaxCoordinate() &&
				coordinates[2] >= getMinCoordinate() && coordinates[2] < getMaxCoordinate());
	}
	
	/**
	 * Tells whether the given position is within the game bounds
	 * @param position
	 * 		The position you want to check
	 * @return
	 * 		true iff the position is within the game bounds
	 * 		| result == (position[0] >= getMinCoordinate() && position[0] < getMaxCoordinate() &&
	 * 		| position[1] >= getMinCoordinate() && position[1] < getMaxCoordinate() &&
	 * 		| position[2] >= getMinCoordinate() && position[2] < getMaxCoordinate());
	 */
	private static boolean withinBounds(double[] position) {
		return (position[0] >= getMinCoordinate() && position[0] < getMaxCoordinate() &&
				position[1] >= getMinCoordinate() && position[1] < getMaxCoordinate() &&
				position[2] >= getMinCoordinate() && position[2] < getMaxCoordinate());
	}
	
	/**
	 * Tells whether the given cube coordinates represent the same cube
	 * @param coord1
	 * 		The first cube to check
	 * @param coord2
	 * 		The second cube to check
	 * @return
	 * 		true iff the cubes are the same
	 * 		| result == Arrays.equals(coord1, coord2);
	 */
	private static boolean areSameCube(int[] coord1, int[] coord2) {
		return Arrays.equals(coord1, coord2);
	}
	
	/**
	 * Get a random coordinate within the game field.
	 * @return
	 * 		A random coordinate within the game field.
	 */
	private static int[] getRandomCoordinate() {
		int[] result = new int[3];
		for(int i=0; i<result.length; i++){
			int num = random.nextInt((int) (getMaxCoordinate() - getMinCoordinate()));
			num += getMinCoordinate();
			result[i] = num;
		}
		return result;
	}
	
	/**
	 * Static fields
	 */
	private static final double maxCoordinate = 50;
	private static final double minCoordinate = 0;

	/**
	 * A random generator used by this Unit
	 */
	private static Random random;
}
