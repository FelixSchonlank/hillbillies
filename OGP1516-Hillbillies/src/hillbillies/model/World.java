package hillbillies.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @Invar Every element of the cubes map must be a valid cube.
 * 		| this.hasProperCubes()
 * @Invar Each World must have proper Items.
 *      | hasProperItems()
 *
 */
public class World {
	/**
	 * Initialize this new World as a non-terminated World with 
	 * no Items yet.
	 * 
	 * @post   This new World has no Items yet.
	 *       | new.getNbItems() == 0
	 */
	public World(){
		
	}
	
	/**
	 * An enum to represent the different terrain types in the World
	 */
	private enum TerrainType {
		AIR, ROCK, TREE, WORKSHOP;
		
		public int toInt() {
			switch (this) {
			case AIR:
				return 0; break;
			case ROCK:
				return 1; break;
			case TREE:
				return 2; break;
			case WORKSHOP:
				return 3; break;
			default:
				return -1;
			}
		}
	}
	
	/**
	 * Return the maximum x coordinate of this World
	 * @return the maximum x coordinate anything can have
	 * 		| result == maxXCoordinate
	 */
	@Basic @Immutable
	public static int getMaxXCoordinate(){
		return maxXCoordinate;
	}
	
	/**
	 * Return the minimum x coordinate of this World
	 * @return the minimum x coordinate anything can have
	 * 		| result == minXCoordinate
	 */
	@Basic @Immutable
	public static int getMinXCoordinate(){
		return minXCoordinate;
	}
	
	/**
	 * Return the maximum y coordinate of this World
	 * @return the maximum y coordinate anything can have
	 * 		| result == maxYCoordinate
	 */
	@Basic @Immutable
	public static int getMaxYCoordinate(){
		return maxYCoordinate;
	}
	
	/**
	 * Return the minimum y coordinate of this World
	 * @return the minimum y coordinate anything can have
	 * 		| result == minYCoordinate
	 */
	@Basic @Immutable
	public static int getMinYCoordinate(){
		return minYCoordinate;
	}
	
	/**
	 * Return the maximum z coordinate of this World
	 * @return the maximum z coordinate anything can have
	 * 		| result == maxZCoordinate
	 */
	@Basic @Immutable
	public static int getMaxZCoordinate(){
		return maxZCoordinate;
	}
	
	/**
	 * Return the minimum z coordinate of this World
	 * @return the minimum z coordinate anything can have
	 * 		| result == minZCoordinate
	 */
	@Basic @Immutable
	public static int getMinZCoordinate(){
		return minZCoordinate;
	}

	/**
	 * Gives back the integer coordinates of the cube that the given position is in.
	 * @param position
	 * 		The given position
	 * @return The integer coordinates of the cube that the given position is in.
	 * @throws IllegalArgumentException
	 * 		If position is not inside any cube
	 * 		| !withinBounds(position)
	 */
	private static Coordinate cubeCoordinates(Position position) throws IllegalArgumentException {
		if(!withinBounds(position)){
			throw new IllegalArgumentException("Position out of bounds: " + position.toString());
		}
		return position.toCoordinate();
	}
	
	/**
	 * Tells whether the two cubes are adjacent.
	 * @param cube1
	 * 		The first cube to check
	 * @param cube2
	 * 		The second cube to check
	 * @return
	 * 		true iff the two cubes are within the World bounds and adjacent.
	 * @throws IllegalArgumentException
	 * 		If at least one of the given Coordinates is out of bounds.
	 */
	private static boolean areAdjacentCubes(Coordinate cube1, Coordinate cube2) throws IllegalArgumentException {
		if(!withinBounds(cube1)){
			throw new IllegalArgumentException("First cube out of bounds: " + cube1.toString());
		}
		if(!withinBounds(cube2)){
			throw new IllegalArgumentException("Second cube out of bounds: " + cube2.toString());
		}
		
		return cube1.isAdjacentTo(cube2);
	}
	
	/**
	 * Gives back the position of the center of the cube with given coordinate
	 * @param coordinate
	 * 		The coordinate of the cube to calculate the center of
	 * @return
	 * 		The position of the center of the cube with given coordinate
	 * @throws IllegalArgumentException
	 * 		If the given coordinate is out of bounds
	 * 		| !withinBounds(coordinate)
	 */
	private static Position cubeCenter(Coordinate coordinate) throws IllegalArgumentException {
		if(!withinBounds(coordinate)){
			throw new IllegalArgumentException("Coordinate out of bounds: " + coordinate.toString());
		}
		return coordinate.toPosition();
	}
	
	/**
	 * Tells whether the given cube coordinate is within the World bounds
	 * @param coordinates
	 * 		The cube you want to check
	 * @return
	 * 		true iff the cube is within the game bounds
	 */
	private static boolean withinBounds(Coordinate coordinate) {
		return (((Comparable)coordinate.getX()).compareTo((Comparable)getMinXCoordinate()) >= 0 && 
				((Comparable)coordinate.getX()).compareTo((Comparable)getMaxXCoordinate()) < 0 &&
				((Comparable)coordinate.getY()).compareTo((Comparable)getMinYCoordinate()) >= 0 && 
				((Comparable)coordinate.getY()).compareTo((Comparable)getMaxYCoordinate()) < 0 &&
				((Comparable)coordinate.getZ()).compareTo((Comparable)getMinZCoordinate()) >= 0 && 
				((Comparable)coordinate.getZ()).compareTo((Comparable)getMaxZCoordinate()) < 0);
	}
	
	/**
	 * Tells whether the given position is within the World bounds
	 * @param position
	 * 		The position that should be checked
	 * @return
	 * 		true iff the position occupies a cube that is within the game bounds
	 */
	private static boolean withinBounds(Position position) {
		return withinBounds(position.toCoordinate());
	}
	
	/**
	 * Tells whether the given cube coordinates represent the same cube
	 * @param coord1
	 * 		The first cube to check
	 * @param coord2
	 * 		The second cube to check
	 * @return
	 * 		true iff the coordinates represent the same cube
	 * @throws IllegalArgumentException
	 * 		If at least one of the given Coordinates is out of bounds.
	 */
	private static boolean areSameCube(Coordinate coord1, Coordinate coord2) throws IllegalArgumentException {
		if (!withinBounds(coord1)) {
			throw new IllegalArgumentException("First Coordinate out of bounds: " + coord1.toString());
		}
		if (!withinBounds(coord2)) {
			throw new IllegalArgumentException("Second Coordinate out of bounds: " + coord2.toString());
		}
		return coord1.equals(coord2);
	}
	
	/**
	 * Get a random coordinate within the game field.
	 * @return
	 * 		A random coordinate within the game field.
	 */
	private static Coordinate getRandomCoordinate() {
		
		return new Coordinate(
				random.nextInt((int)(getMaxXCoordinate() - getMinXCoordinate())) + getMinXCoordinate(),
				random.nextInt((int)(getMaxYCoordinate() - getMinYCoordinate())) + getMinYCoordinate(),
				random.nextInt((int)(getMaxZCoordinate() - getMinZCoordinate())) + getMinZCoordinate()
				);
	}
	
	/**
	 * Gives back all the neighbors of the cube represented by the given coordinate.
	 * Only existing cube coordinates (within bounds) are returned. 
	 * @param coordinate
	 * 		The coordinate of the cube you want to know the neighbors of.
	 * @return
	 * 		A Set of Coordinates which represent cubes that are neighbors of
	 * 		the cube represented by the given coordinate.
	 * @throws IllegalArgumentException
	 * 		If the given Coordinate is out of bounds.
	 */
	public Set<Coordinate> getNeighbors(Coordinate coordinate) throws IllegalArgumentException {
		if (!withinBounds(coordinate)) {
			throw new IllegalArgumentException("Coordinate out of bounds: " + coordinate.toString());
		}
		Set<Coordinate> neighbors = coordinate.getNeighbors();
		neighbors.removeIf(neighbor -> !withinBounds(neighbor));
		return neighbors;
	}
	
	
	
	/* THE GAME MAP */
	
	/**
	 * @Invar cubes is effective
	 * 		| cubes != null
	 */
	private Map<Coordinate, TerrainType> cubes;
	
	/**
	 * Tells whether this World has proper cubes.
	 * @return
	 * 		True iff this World has proper cubes.
	 */
	public boolean hasProperCubes() {
		for (Coordinate cube : cubes.keySet()) {
			if (!isValidCube(cube)) {
				return false;
			}
			if (!isValidTerrainType(cubes.get(cube))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Tells whether the given cube coordinate is a valid one for the cube map.
	 * @param cube
	 * 		The coordinate to check.
	 * @return
	 * 		True iff the given cube coordinate is within the World bounds.
	 */
	public static boolean isValidCube(Coordinate cube) {
		return withinBounds(cube);
	}
	
	/**
	 * Tells whether the given terrain type is a valid one for any given cube.
	 * @param terrainType
	 * 		The terrain type to check.
	 * @return
	 * 		True iff the given terrain type is not null.
	 */
	public static boolean isValidTerrainType(TerrainType terrainType) {
		return terrainType != null;
	}
	
	/* Items */

	/**
	 * Check whether this World has the given Item as one of its
	 * Items.
	 * 
	 * @param  Item
	 *         The Item to check.
	 */
	@Basic
	@Raw
	public boolean hasAsItem(@Raw Item Item) {
		return Items.contains(Item);
	}

	/**
	 * Check whether this World can have the given Item
	 * as one of its Items.
	 * 
	 * @param  Item
	 *         The Item to check.
	 * @return True if and only if the given Item is effective
	 *         and that Item is a valid Item for a World.
	 *       | result ==
	 *       |   (Item != null) &&
	 *       |   Item.isValidWorld(this)
	 */
	@Raw
	public boolean canHaveAsItem(Item Item) {
		return (Item != null) && (Item.isValidWorld(this));
	}

	/**
	 * Check whether this World has proper Items attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         Items attached to it as one of its Items,
	 *         and if each of these Items references this World as
	 *         the World to which they are attached.
	 *       | for each Item in Item:
	 *       |   if (hasAsItem(Item))
	 *       |     then canHaveAsItem(Item) &&
	 *       |          (Item.getWorld() == this)
	 */
	public boolean hasProperItems() {
		for (Item Item : Items) {
			if (!canHaveAsItem(Item))
				return false;
			if (Item.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Items associated with this World.
	 *
	 * @return  The total number of Items collected in this World.
	 *        | result ==
	 *        |   card({Item:Item | hasAsItem({Item)})
	 */
	public int getNbItems() {
		return Items.size();
	}

	/**
	 * Add the given Item to the set of Items of this World.
	 * 
	 * @param  Item
	 *         The Item to be added.
	 * @pre    The given Item is effective and already references
	 *         this World.
	 *       | (Item != null) && (Item.getWorld() == this)
	 * @post   This World has the given Item as one of its Items.
	 *       | new.hasAsItem(Item)
	 */
	public void addItem(@Raw Item Item) {
		assert (Item != null) && (Item.getWorld() == this);
		Items.add(Item);
	}

	/**
	 * Remove the given Item from the set of Items of this World.
	 * 
	 * @param  Item
	 *         The Item to be removed.
	 * @pre    This World has the given Item as one of
	 *         its Items, and the given Item does not
	 *         reference any World.
	 *       | this.hasAsItem(Item) &&
	 *       | (Item.getWorld() == null)
	 * @post   This World no longer has the given Item as
	 *         one of its Items.
	 *       | ! new.hasAsItem(Item)
	 */
	@Raw
	public void removeItem(Item Item) {
		assert this.hasAsItem(Item) && (Item.getWorld() == null);
		Items.remove(Item);
	}

	/**
	 * Variable referencing a set collecting all the Items
	 * of this World.
	 * 
	 * @Invar  The referenced set is effective.
	 *       | Items != null
	 * @Invar  Each Item registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each Item in Items:
	 *       |   ( (Item != null) &&
	 *       |     (! Item.isTerminated()) )
	 */
	private final Set<Item> Items = new HashSet<Item>();
	
	/**
	 * Static fields
	 */
	private final int maxXCoordinate;
	private final int minXCoordinate;
	private final int maxYCoordinate;
	private final int minYCoordinate;
	private final int maxZCoordinate;
	private final int minZCoordinate;

	/**
	 * A random generator used by this Unit
	 */
	private static Random random;
}
