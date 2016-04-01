package hillbillies.model;

import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @Invar Every element of the cubes map must be a valid cube.
 * 		| this.hasProperCubes()
 * @Invar Each World must have proper Items.
 *      | this.hasProperItems()
 * @Invar Each World must have a valid cubes (Map).
 * 		| this.canHaveAsCubesMap(this.cubes)
 * @Invar Each World must have proper Units.
 * 		| this.hasProperUnits()
 * @Invar Each World must have proper Factions.
 * 		| this.hasProperFactions()
 */
public class World {
	
	/**
	 * An enum to represent the different terrain types in the World
	 */
	protected enum TerrainType {
		AIR(0, true),
		ROCK(1, false),
		TREE(2, false),
		WORKSHOP(3, true);
		
		/**
		 * An all-out constructor using all possible parameters
		 * @param num
		 * 		The artificially created ordinal for this type of terrain.
		 * @param passable
		 * 		Whether or not this type of terrain is passable.
		 * @post
		 * 		This TerrainType will have the given number and passability as
		 * 		its num and passable fields, respectively.
		 * 		| new.num = num;
		 * 		| new.passable = passable;
		 */
		private TerrainType(int num, boolean passable) {
			this.num = num;
			this.passable = passable;
		}
		
		private final int num;
		private final boolean passable;
		
		/**
		 * Returns the associated integer number of this type of terrain.
		 * @return
		 * 		| result == this.num
		 */
		@Basic @Immutable
		public int toInt() {
			return this.num;
		}
		
		/**
		 * Returns the TerrainType that has the given number as its number.
		 * @param i
		 * 		The given number to look for
		 * @return
		 * 		The TerrainType that has that number, if any. If no such
		 * 		TerrainType exists, null is returned.
		 */
		public static TerrainType fromInt(int i) {
			for (TerrainType terrainType : values()) {
				if (terrainType.toInt() == i) {
					return terrainType;
				}
			}
			return null;
		}
		
		/**
		 * Tells whether this TerrainType is passable.
		 * @return
		 * 		True iff this TerrainType is passable.
		 * 		| result == this.passable
		 */
		@Basic @Immutable
		public boolean isPassable() {
			return this.passable;
		}
	}
	
	/**
	 * Initializes a World with given terrainTypes in threedimensional array,
	 * and terrain change listener for notification purposes.
	 * @param terrainTypes
	 * 		A threedimensional array holding int values that represent the cube
	 * 		types of the World that should be initialized.
	 * @param terrainChangeListener
	 * 		An object with a method to be called every time a cube in the World
	 * 		changes its type.
	 * @post
	 * 		For every element of terrainTypes, this.cubes will hold exactly one
	 * 		entry with a Coordinate corresponding with the location of the
	 * 		element as key, and a TerrainType corresponding with the value of
	 * 		the element as value.
	 * @post
	 * 		this.terrainChangeListener will be equal to the given one.
	 * @post
	 * 		this.min(X|Y|Z)Coordinate will be equal to 0.
	 * @post
	 * 		this.max(X|Y|Z)Coordinate will be equal to the length of the given
	 * 		array in that specific direction.
	 * @post
	 * 		This World will have no Units to begin with
	 * 		| this.getNbUnits() == 0
	 * @post
	 * 		This World will have no Items to begin with
	 * 		| this.getNbItems() == 0
	 * @post
	 * 		This World will have exactly 5 Factions
	 * 		| this.getNbFactions() == 5
	 * @throws IllegalArgumentException
	 * 		If the input array has any dimension of size 0 is jagged
	 * @throws IllegalArgumentException
	 * 		If the given TerrainChangeListener is null.
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener terrainChangeListener)
			throws IllegalArgumentException {
		if (terrainChangeListener == null) {
			throw new IllegalArgumentException("Terrain Change Listener is null");
		}
		// Initialize terrain change listener of this World
		this.terrainChangeListener = terrainChangeListener;
		
		// Initialize the cubes map for this World
		this.cubes = intArrayToCubesMap(terrainTypes);
		
		// Initialize the dimensions of this World
		this.minXCoordinate = 0;
		this.maxXCoordinate = terrainTypes.length;
		this.minYCoordinate = 0;
		this.maxYCoordinate = terrainTypes[0].length;
		this.minZCoordinate = 0;
		this.maxZCoordinate = terrainTypes[0][0].length;
		
		// Initialize the Units Set of this World
		this.units = new HashSet<Unit>();
		
		// Initialize the Items Set of this World
		this.items = new HashSet<Item>();
		
		// Initialize the Factions Set of this World
		this.factions = new HashSet<Faction>();
		for (int i=0; i<5; i++){
			factions.add(new Faction());
		}
		
		// Initialize the ConnectedToBorder object to keep track of
		// border connections.
		this.connectedToBorder = new ConnectedToBorder(
				this.getMaxXCoordinate(),
				this.getMaxYCoordinate(),
				this.getMaxZCoordinate()
				);
		// Using this iteration is sufficient because the cubes map always has
		// no more than exactly one entry per cube in the World.
		for (Coordinate coordinate : this.cubes.keySet()) {
			if (this.isPassableCube(coordinate)) {
				this.connectedToBorder.changeSolidToPassable(coordinate.getX(), 
						coordinate.getY(),
						coordinate.getZ()
						);
			}
		}
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
			if (!isValidCoordinate(cube)) {
				return false;
			}
			if (!isValidTerrainType(cubes.get(cube))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Tells whether the given coordinate is a valid one for the World, and
	 * thus for the cube map.
	 * @param coordinate
	 * 		The coordinate to check.
	 * @return
	 * 		True iff the given coordinate is within the World bounds.
	 */
	public boolean isValidCoordinate(Coordinate coordinate) {
		return withinBounds(coordinate);
	}
	
	/**
	 * Tells whether the given Position is a valid one for the World
	 * @param position
	 * @return
	 * 		True iff the given position's coordinate is valid.
	 */
	public boolean isValidPosition(Position position) {
		return isValidCoordinate(position.toCoordinate());
	}
	
	/**
	 * Tells whether the given Map<Coordinate, TerrainType> is a valid one to
	 * hold the cubes map of this world. 
	 * @param map
	 * @return
	 * 		True iff the size of the map is exactly the product of the sizes of
	 * 		the game world in the three dimensions, AND if for every valid
	 * 		coordinate in the game world an entry in the map can be found.
	 * 		Through the pigeon hole principle, I think, this should mean every
	 * 		valid coordinate has exactly one map entry. 
	 */
	public boolean canHaveAsCubesMap(Map<Coordinate, TerrainType> map) {
		if (map.size() != (this.getMaxXCoordinate()+1)*(this.getMaxYCoordinate()+1)*(this.getMaxZCoordinate()+1)) {
			return false;
		}
		for (int x=0; x<this.getMaxXCoordinate(); x++) {
			for (int y=0; y<this.getMaxYCoordinate(); y++) {
				for (int z=0; z<this.getMaxZCoordinate(); z++) {
					if (map.get(new Coordinate(x, y, z)) == null) {
						return false;
					}
				}
			}
		}
		return true;
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
	
	/**
	 * Turns a threedimensional array of ints into a Map<Coordinate, TerrainType>.
	 * @param array
	 * 		The array to transform.
	 * @return
	 * 		The Map.
	 * @throws IllegalArgumentException
	 * 		If any of the given array's sizes is 0 in a dimension.
	 * @throws IllegalArgumentException
	 * 		If the given array is jagged in any of its dimensions.
	 */
	private Map<Coordinate, TerrainType> intArrayToCubesMap(int[][][] array) 
			throws IllegalArgumentException {
		if (array.length == 0) {
			throw new IllegalArgumentException("X size of input array is 0");
		}
		if (array[0].length == 0) {
			throw new IllegalArgumentException("Y size of input array is 0");
		}
		if (array[0][0].length == 0) {
			throw new IllegalArgumentException("Z size of input array is 0");
		}
		
		Map<Coordinate, TerrainType> result = new HashMap<Coordinate, TerrainType>();
		
		// Within every deeper loop we check if the array has the same length
		// there as before. If false somewhere, the array is jagged, and
		// therefore invalid to us.
		for (int x=0; x<array.length; x++) {
			for (int y=0; y<array[x].length; y++) {
				if (array[x].length != array[0].length) {
					throw new IllegalArgumentException("Input array is jagged.");
				}
				for (int z=0; z<array[x][y].length; z++) {
					if (array[x][y].length != array[0][0].length) {
						throw new IllegalArgumentException("Input array is jagged");
					}
					result.put(new Coordinate(x, y, z), TerrainType.fromInt(array[x][y][z]));
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Turns a Map<Coordinate, TerrainType> into a threedimensional array of ints.
	 * There is no need to make any checks whether the map is correctly filled,
	 * since the map is initialized properly, and never loses its shape.
	 * @param map
	 * 		The Map to transform.
	 * @return
	 * 		The array.
	 */
	private int[][][] cubesMapToIntArray(Map<Coordinate, TerrainType> map) {
		int[][][] result = new int[getMaxXCoordinate()][getMaxYCoordinate()][getMaxZCoordinate()];
		for (int x=0; x<this.getMaxXCoordinate(); x++) {
			for (int y=0; y<this.getMaxYCoordinate(); y++) {
				for (int z=0; z<this.getMaxZCoordinate(); z++) {
					result[x][y][z] = map.get(new Coordinate(x, y, z)).toInt();
				}
			}
		}
		return result;
	}
	
	/**
	 * Return the maximum x coordinate of this World
	 * @return the maximum x coordinate anything can have
	 * 		| result == maxXCoordinate
	 */
	@Basic @Immutable
	public int getMaxXCoordinate(){
		return this.maxXCoordinate;
	}
	
	/**
	 * Return the minimum x coordinate of this World
	 * @return the minimum x coordinate anything can have
	 * 		| result == minXCoordinate
	 */
	@Basic @Immutable
	public int getMinXCoordinate(){
		return this.minXCoordinate;
	}
	
	/**
	 * Return the maximum y coordinate of this World
	 * @return the maximum y coordinate anything can have
	 * 		| result == maxYCoordinate
	 */
	@Basic @Immutable
	public int getMaxYCoordinate(){
		return this.maxYCoordinate;
	}
	
	/**
	 * Return the minimum y coordinate of this World
	 * @return the minimum y coordinate anything can have
	 * 		| result == minYCoordinate
	 */
	@Basic @Immutable
	public int getMinYCoordinate(){
		return this.minYCoordinate;
	}
	
	/**
	 * Return the maximum z coordinate of this World
	 * @return the maximum z coordinate anything can have
	 * 		| result == maxZCoordinate
	 */
	@Basic @Immutable
	public int getMaxZCoordinate(){
		return this.maxZCoordinate;
	}
	
	/**
	 * Return the minimum z coordinate of this World
	 * @return the minimum z coordinate anything can have
	 * 		| result == minZCoordinate
	 */
	@Basic @Immutable
	public int getMinZCoordinate(){
		return this.minZCoordinate;
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
	public Coordinate cubeCoordinates(Position position) throws IllegalArgumentException {
		if(!isValidPosition(position)){
			throw new IllegalArgumentException("Position is not valid: " + position.toString());
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
	public boolean areAdjacentCubes(Coordinate cube1, Coordinate cube2) throws IllegalArgumentException {
		if(!isValidCoordinate(cube1)){
			throw new IllegalArgumentException("First cube is not valid: " + cube1.toString());
		}
		if(!isValidCoordinate(cube2)){
			throw new IllegalArgumentException("Second cube is not valid: " + cube2.toString());
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
	public Position cubeCenter(Coordinate coordinate) throws IllegalArgumentException {
		if(!isValidCoordinate(coordinate)){
			throw new IllegalArgumentException("Coordinate is not valid: " + coordinate.toString());
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
	public boolean withinBounds(Coordinate coordinate) {
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
	public boolean withinBounds(Position position) {
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
	public boolean areSameCube(Coordinate coord1, Coordinate coord2) throws IllegalArgumentException {
		if (!isValidCoordinate(coord1)) {
			throw new IllegalArgumentException("First Coordinate is not valid: " + coord1.toString());
		}
		if (!isValidCoordinate(coord2)) {
			throw new IllegalArgumentException("Second Coordinate is not valid: " + coord2.toString());
		}
		return coord1.equals(coord2);
	}
	
	/**
	 * Get a random coordinate within the game field.
	 * @return
	 * 		A random coordinate within the game field.
	 */
	public Coordinate getRandomCoordinate() {
		
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
		if (!isValidCoordinate(coordinate)) {
			throw new IllegalArgumentException("Coordinate is not valid: " + coordinate.toString());
		}
		Set<Coordinate> neighbors = coordinate.getNeighbors();
		neighbors.removeIf(neighbor -> !withinBounds(neighbor));
		return neighbors;
	}
	
	/**
	 * Tells whether the cube at the given coordinate is of a passable terrain type.
	 * @param coordinate
	 * 		The coordinate to check at.
	 * @return
	 * 		True iff the cube at the given coordinate corresponds to an entry
	 * 		in the cubes map with a TerrainType that isPassable().
	 * @throws IllegalArgumentException
	 * 		If the given coordinate is not valid.
	 */
	public boolean isPassableCube(Coordinate coordinate) throws IllegalArgumentException {
		if (!isValidCoordinate(coordinate)) {
			throw new IllegalArgumentException("Coordinate is not valid: " + coordinate.toString());
		}
		return cubes.get(coordinate).isPassable();
	}
	
	/**
	 * Gives back the terrain type of the cube at the given coordinate.
	 * @param coordinate
	 * 		The coordinate of the cube to check.
	 * @throws IllegalArgumentException
	 * 		If the given coordinate is not valid.
	 */
	public TerrainType getCubeAt(Coordinate coordinate) throws IllegalArgumentException {
		if (!isValidCoordinate(coordinate)) {
			throw new IllegalArgumentException("Given cube coordinate is not valid: " + coordinate.toString());
		}
		return this.cubes.get(coordinate);
	}
	
	/**
	 * Sets the value of an entry with given coordinate in the cubes map to
	 * a given terrain type. 
	 * @param coordinate
	 * 		The coordinate of the cube to set
	 * @param terrainType
	 * 		The terrain type to set it to
	 * @throws IllegalArgumentException
	 * 		If the given cube coordinate is not valid
	 * @throws IllegalArgumentException
	 * 		If the given terrain type is not valid
	 */
	public void setCubeAt(Coordinate coordinate, TerrainType terrainType)
			throws IllegalArgumentException {
		if (!isValidCoordinate(coordinate)) {
			throw new IllegalArgumentException("Given cube coordinate is not valid: " + coordinate.toString());
		}
		if (!isValidTerrainType(terrainType)) {
			throw new IllegalArgumentException("Given terrain type is not valid: " + terrainType.toString());
		}
		this.cubes.remove(coordinate);
		this.cubes.put(coordinate, terrainType);
		
		// Don't forget to inform the GUI
		this.terrainChangeListener.notifyTerrainChanged(coordinate.getX(), coordinate.getY(), coordinate.getZ());
	}
	
	/**
	 * Digs out the cube at the given coordinate, if it is diggable. Throws an
	 * exception if it is not diggable.
	 * @param coordinate
	 * 		The coordinate of the cube to be dug out.
	 * @throws IllegalArgumentException
	 * 		If the cube at the given coordinate is not diggable.
	 */
	public void digOutCube(Coordinate coordinate) throws IllegalArgumentException {
		if (isPassableCube(coordinate)) {
			throw new IllegalArgumentException("Given cube is not diggable: " + coordinate.toString() + ": " + this.getCubeAt(coordinate));
		}
		TerrainType terrainType = this.getCubeAt(coordinate);
		this.setCubeAt(coordinate, TerrainType.AIR);
		switch (terrainType) {
		case ROCK:
			if (random.nextDouble() <= getBoulderDropChance()) {
				createBoulder(cubeCenter(coordinate));
			}
			break;
		case TREE:
			if (random.nextDouble() <= getLogDropChance()) {
				createLog(cubeCenter(coordinate));
			}
			break;
		}
	}
	
	/**
	 * Gives back all GameObjects that are currently present in the cube with
	 * the given coordinate, if any.
	 * @param coordinate
	 * 		The Coordinate of the cube to list the contents of.
	 * @return
	 * 		A new (Hash)Set of all GameObjects with Positions located within
	 * 		the cube which corresponds to the given Coordinate. That makes this
	 * 		a shallow copy.
	 */
	public Set<GameObject> listGameObjectsInCube(Coordinate coordinate) {
		if (!isValidCoordinate(coordinate)) {
			throw new IllegalArgumentException("Given coordinate is invalid: " + coordinate.toString());
		}
		Set<GameObject> result = new HashSet<GameObject>();
		for (Unit unit : this.units) {
			if (this.cubeCoordinates(unit.getPosition()).equals(coordinate)) {
				result.add(unit);
			}
		}
		for (Item item : this.items) {
			if (this.cubeCoordinates(item.getPosition()).equals(coordinate)) {
				result.add(item);
			}
		}
		return result;
	}
	/**
	 * To be called whenever the cubes Map changes.
	 * @effect
	 * 		
	 */
	private void onCubesMapChange() {
		for (Coordinate coordinate : this.cubes.keySet()) {
			if (!this.connectedToBorder.isSolidConnectedToBorder(
					coordinate.getX(),
					coordinate.getY(),
					coordinate.getZ())
					) {
				try {
					this.digOutCube(coordinate);
				} catch(IllegalArgumentException e) {
					// Cube turns out to be air already or something similar.
				}
			}
		}
	}
	
	
	
	/* UNITS */

	/**
	 * Check whether this World has the given Unit as one of its
	 * Units.
	 * 
	 * @param  unit
	 *         The Unit to check.
	 */
	@Basic
	@Raw
	public boolean hasAsUnit(@Raw Unit unit) {
		return units.contains(unit);
	}

	/**
	 * Check whether the given Unit is valid.
	 * @param  unit
	 *         The Unit to check.
	 * @return
	 * 		True iff the given Unit is valid.
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit) {
		return Unit.isValidWorld(this);
	}

	/**
	 * Check whether this World has proper Units attached to it.
	 * 
	 * @return
	 * 		True if and only if each of the Units attached to this World is
	 * 		valid, and if each of these Units references this World as the
	 * 		World to which they are attached.
	 */
	public boolean hasProperUnits() {
		for (Unit unit : this.units) {
			if (!canHaveAsUnit(unit)) {
				return false;
			}
			if (unit.getWorld() != this) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return the number of Units associated with this World.
	 *
	 * @return  The total number of Units collected in this World.
	 *        | result ==
	 *        |   card({unit:Unit | hasAsUnit({unit)})
	 */
	public int getNbUnits() {
		return units.size();
	}

	/**
	 * Add the given Unit to the set of Units of this World.
	 * 
	 * @param  unit
	 *         The Unit to be added.
	 * @pre    The given Unit is effective and already references
	 *         this World.
	 *       | (unit != null) && (unit.getWorld() == this) && this.getNbUnits() < this.getMaxUnits()
	 * @post   This World has the given Unit as one of its Units.
	 *       | (new this).hasAsUnit(unit)
	 */
	public void addUnit(@Raw Unit unit) {
		assert (unit != null) && (unit.getWorld() == this) && (this.getNbUnits() < this.getMaxUnits());
		units.add(unit);
	}

	/**
	 * Remove the given Unit from the set of Units of this World.
	 * 
	 * @param  unit
	 *         The Unit to be removed.
	 * @pre    This World has the given Unit as one of
	 *         its Units, and the given Unit does not
	 *         reference any World.
	 *       | this.hasAsUnit(unit) &&
	 *       | (unit.getWorld() == null)
	 * @post   This World no longer has the given Unit as
	 *         one of its Units.
	 *       | ! new.hasAsUnit(unit)
	 */
	@Raw
	public void removeUnit(Unit unit) {
		assert this.hasAsUnit(unit) && (unit.getWorld() == null);
		units.remove(unit);
	}

	/**
	 * Variable referencing a set collecting all the Units
	 * of this World.
	 * 
	 * @Invar  The referenced set is effective.
	 *       | units != null
	 * @Invar  Each Unit registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each unit in units:
	 *       |   ( (unit != null) &&
	 *       |     (! unit.isTerminated()) )
	 */
	private final Set<Unit> units;
	
	/**
	 * return the maximum number of units a world can have 
	 */
	public int getMaxUnits(){
		return this.maxUnits;
	}
	
	/**
	 * Variable referencing the maximum number of units a World can have
	 */
	private static final int maxUnits = 100;
	
	/**
	 * Gives back all the Units in this World.
	 * @return
	 * 		A new (Hash)Set of all the Units in this World.
	 * 		That makes this a shallow copy
	 */
	public Set<Unit> listAllUnits() {
		Set<Unit> result = new HashSet<Unit>();
		for (Unit unit : this.units) {
			result.add(unit);
		}
		return result;
	}
	
	/**
	 * Gives back all the Units in this World that belong to a given Faction.
	 * @param faction
	 * 		The Faction the Units must all be member of.
	 * @return
	 * 		A new (Hash)Set of all Units in this World who belong to the given
	 * 		Faction. That makes this a shallow copy.
	 */
	public Set<Unit> listAllUnits(Faction faction) {
		Set<Unit> result = new HashSet<Unit>();
		for (Unit unit : this.units) {
			if (unit.getFaction() == faction) {
				result.add(unit);
			}
		}
		return result;
	}
	
	
	
 	/* ITEMS */

	/**
	 * Check whether this World has the given Item as one of its
	 * Items.
	 * 
	 * @param  item
	 *         The Item to check.
	 */
	@Basic
	@Raw
	public boolean hasAsItem(@Raw Item item) {
		return items.contains(item);
	}

	/**
	 * Check whether this World can have the given Item
	 * as one of its Items.
	 * 
	 * @param  item
	 *         The Item to check.
	 * @return True if and only if the given Item is effective
	 *         and that Item is a valid Item for this World.
	 *       | result ==
	 *       |   (item != null) &&
	 *       |   Item.isValidWorld(this)
	 */
	@Raw
	public boolean canHaveAsItem(Item item) {
		return (item != null) && (Item.isValidWorld(this));
	}

	/**
	 * Check whether this World has proper Items attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         items attached to it as one of its Items,
	 *         and if each of these Items references this World as
	 *         the World to which they are attached.
	 *       | for each item in items:
	 *       |   if (hasAsItem(item))
	 *       |     then canHaveAsItem(item) &&
	 *       |          (item.getWorld() == this)
	 */
	public boolean hasProperItems() {
		for (Item item : this.items) {
			if (!canHaveAsItem(item))
				return false;
			if (item.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Items associated with this World.
	 *
	 * @return  The total number of Items collected in this World.
	 *        | result ==
	 *        |   card({item:Item | hasAsItem(item)})
	 */
	public int getNbItems() {
		return items.size();
	}

	/**
	 * Add the given Item to the set of Items of this World.
	 * 
	 * @param  item
	 *         The Item to be added.
	 * @pre    The given Item is effective and already references
	 *         this World.
	 *       | (item != null) && (item.getWorld() == this)
	 * @post   This World has the given Item as one of its items.
	 *       | new.hasAsItem(item)
	 */
	public void addItem(@Raw Item item) {
		assert (item != null) && (item.getWorld() == this);
		items.add(item);
	}

	/**
	 * Remove the given Item from the set of Items of this World.
	 * 
	 * @param  item
	 *         The Item to be removed.
	 * @pre    This World has the given Item as one of
	 *         its items, and the given Item does not
	 *         reference any World.
	 *       | this.hasAsItem(item) &&
	 *       | (item.getWorld() == null)
	 * @post   This World no longer has the given Item as
	 *         one of its items.
	 *       | ! new.hasAsItem(item)
	 */
	@Raw
	public void removeItem(Item item) {
		assert this.hasAsItem(item) && (item.getWorld() == null);
		items.remove(item);
	}

	/**
	 * return the set of Items that belong to a unit
	 */
	protected Set<Item> getItems(){
		return this.items;
	}
	
	/**
	 * Variable referencing a set collecting all the Items
	 * of this World.
	 * 
	 * @Invar  The referenced set is effective.
	 *       | items != null
	 * @Invar  Each Item registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each item in items:
	 *       |   ( (item != null) &&
	 *       |     (! item.isTerminated()) )
	 */
	private final Set<Item> items;
	
	/**
	 * Gives back all Boulders in this World.
	 * @return
	 * 		A new (Hash)Set of all Boulders in this World. That makes this a
	 * 		shallow copy.
	 */
	public Set<Boulder> listAllBoulders() {
		Set<Boulder> result = new HashSet<Boulder>();
		for (Item item : this.items) {
			if (item instanceof Boulder) {
				result.add((Boulder)item);
			}
		}
		return result;
	}
	
	/**
	 * Gives back all Logs in this World.
	 * @return
	 * 		A new (Hash)Set of all Logs in this World. That makes this a
	 * 		shallow copy.
	 */
	public Set<Log> listAllLogs() {
		Set<Log> result = new HashSet<Log>();
		for (Item item : this.items) {
			if (item instanceof Log) {
				result.add((Log)item);
			}
		}
		return result;
	}
	
	
	
	/* ADVANCE TIME */
	
	/**
	 * Invokes the advanceTime method on all GameObjects in this World. That is
	 * (all Units) ∪ (all Items).
	 * @param dt
	 * 		The time by which to advance, expressed in seconds.
	 */
	public void advanceTime(double dt) {
		for (Unit unit : this.units) {
			unit.advanceTime(dt);
		}
		for (Item item : this.items) {
			item.advanceTime(dt);
		}
	}
	
	
	
	/* FACTIONS */

	/**
	 * Check whether this World has the given Faction as one of its
	 * Factions.
	 * 
	 * @param  faction
	 *         The Faction to check.
	 */
	@Basic
	@Raw
	public boolean hasAsFaction(@Raw Faction faction) {
		return factions.contains(faction);
	}

	/**
	 * Check whether the given Faction is valid.
	 * 
	 * @param  faction
	 * 		The Faction to check.
	 * @return
	 * 		True iff the given Faction is not null
	 */
	@Raw
	public boolean isValidFaction(Faction faction) {
		return (faction != null);
	}

	/**
	 * Check whether this World has proper Factions attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         Factions attached to it as one of its Factions.
	 *       | for each faction in Faction:
	 *       |   if (hasAsFaction(faction))
	 *       |     then isValidFaction(faction)
	 */
	public boolean hasProperFactions() {
		for (Faction faction : this.factions) {
			if (!isValidFaction(faction))
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Factions associated with this World.
	 *
	 * @return  The total number of Factions collected in this World.
	 *        | result ==
	 *        |   card({faction:Faction | hasAsFaction({faction)})
	 */
	public int getNbFactions() {
		return factions.size();
	}

	/**
	 * Variable referencing a set collecting all the Factions
	 * of this World.
	 * 
	 * @invar  The referenced set is effective.
	 *       | factions != null
	 * @invar  Each Faction registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each faction in factions:
	 *       |   ( (faction != null) &&
	 *       |     (! faction.isTerminated()) )
	 */
	private final Set<Faction> factions;
	
	/**
	 * Gives back all the Factions in this World.
	 * @return
	 * 		A new (Hash)Set of all Factions in this World.
	 * 		That makes this a shallow copy.
	 */
	public Set<Faction> listAllFactions() {
		Set<Faction> result = new HashSet<Faction>();
		for (Faction faction : this.factions) {
			result.add(faction);
		}
		return result;
	}
	
	
	
	/* FIELDS */
	
	/**
	 * final fields
	 */
	private final int maxXCoordinate;
	private final int minXCoordinate;
	private final int maxYCoordinate;
	private final int minYCoordinate;
	private final int maxZCoordinate;
	private final int minZCoordinate;
	private final TerrainChangeListener terrainChangeListener;
	private final ConnectedToBorder connectedToBorder;

	/**
	 * A random generator used by this Unit
	 */
	private static Random random;
}
