package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**	 
 * @Invar  Each Item can have its weight as weight.
 * 		| canHaveAsWeight(this.getWeight())
 * @Invar
 * 		| this.hasUnit() <==> ! this.hasWorld()
 * @Invar
 * 		Each Item has a proper Unit attached to it.
 * 		| this.hasProperUnit()
 * @Invar
 * 		Each Item has a proper World attached to it.
 * 		| this.hasProperWorld()
 */
public class Item extends GameObject{
	
	/**
	 * Initialize a new Item with a given position and a random weight
	 */
	public Item(Position position){
		super(position);
		int weight = random.nextInt(getMaxWeight() - getMinWeight()) + getMinWeight();
		this.weight = weight;
	}
	
	
	
	/* Weight */

	/**
	 * Return the weight of this Item.
	 */
	@Basic @Raw @Immutable
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Check whether this Item can have the given weight as its weight.
	 *  
	 * @param  weight
	 *         The weight to check.
	 * @return 
	 *       | result == true 
	 */
	@Raw
	public static boolean canHaveAsWeight(int weight) {
		return (getMinWeight() <= weight 
				&& weight <= getMaxWeight());
	}

	/**
	 * return the minimum weight of an Item 
	 */
	@Immutable
	public static int getMinWeight(){
		return MinWeight;
	}

	/**
	 * return the maximum weight of an Item 
	 */
	@Immutable
	public static int getMaxWeight(){
		return MaxWeight;
	}

	/**
	 * Variable referencing the minimum weight for an Item
	 */
	private static final int MinWeight = 10;
	
	/**
	 * Variable referencing the maximum weight for an Item
	 */
	private static final int MaxWeight = 50;

	/**
	 * Variable registering the weight of this Item.
	 */
	private final int weight;
	
	
	
	/* Unit */

	/**
	 * return the unit that is carrying this Item
	 */
	public Unit getUnit(){
		return this.unit;
	}
	
	/**
	 * Tells whether this Item has a proper Unit attached to it.
	 * @return
	 * 		True iff this Item's Unit is valid, and references this Item back
	 * 		if it is effective.
	 */
	public boolean hasProperUnit() {
		return isValidUnit(this.getUnit()) &&
				(this.getUnit() == null || this.getUnit().getItem() == this);
	}

	/**
	 * Set the unit that is carrying this Item to a given Unit
	 */
	public void setUnit(Unit unit) {
		if (! isValidUnit(unit)) {
			throw new IllegalArgumentException("Given unit is invalid. " + unit.toString());
		}
		if (this.getUnit() != null) {
			Unit oldUnit = this.getUnit();
			this.unit = null;
			oldUnit.setItem(null);
			this.unit = unit;
		} else {
			this.unit = unit;
			unit.setItem(this);
		}
	}
	
	/**
	 * Tells whether the given unit is valid.
	 * @param unit
	 * 		The unit to check the validity of.
	 * @return
	 * 		True always.
	 */
	public static boolean isValidUnit(Unit unit){
		return true;
	}

	/**
	 * check whether the Item is being carried 
	 */
	public boolean hasUnit(){
		return this.getUnit() != null;
	}

	/**
	 * a variable referencing the Unit that is carrying this Item
	 */
	private Unit unit;


	/* World */
	
	/**
	 * Gives back this Item's World.
	 */
	@Raw @Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Tells whether the given World is valid
	 * @param world
	 * 		The world to check the validity of.
	 * @return
	 * 		True always.
	 */
	public static boolean isValidWorld(World world) {
		return true;
	}
	
	/**
	 * Tells whether this Item has a proper World attached to it.
	 * @return
	 * 		True iff this Item's World is valid, and references this Item back
	 * 		if it is effective.
	 */
	public boolean hasProperWorld() {
		return isValidWorld(this.getWorld()) &&
				(this.getWorld() == null || this.getWorld().hasAsItem(this));
	}
	
	/**
	 * Set the world of this Item to the given world and add this items of the given world
	 * @param world
	 * @throws IllegalArgumentException
	 * 		If the unit can not have the given world as its world or if the world can not have this unit as one of its unit 
	 */
	public void setWorld(World world) throws IllegalArgumentException{
		if (! this.isValidWorld(world)){
			throw new IllegalArgumentException("Given World is not valid. " + world.toString());
		}
		if (this.getWorld() != null) {
			World oldWorld = this.getWorld();
			this.world = null;
			oldWorld.removeItem(this);
			this.world = world;
		} else {
			this.world = world;
			world.addItem(this);
		}
	}

	/**
	 * check whether this Item has a world
	 */
	public boolean hasWorld(){
		return this.getWorld() != null;
	}


	/* Terminating */

	/**
	 * Terminate this Item
	 * 
	 * @Post this Item is terminated
	 * @Post The Item is no longer part of any World and is no longer carried by a Unit
	 * @Post If the Item had a World or Unit the World no longer has this Item as its Item or the Unit no longer has an Item 
	 */
	public void terminate(){
		if (this.hasWorld()){
			World world = this.getWorld();
			this.setWorld(null);
			this.world.removeItem(this);
		}else if (this.hasUnit()){
			Unit unit = this.getUnit();
			this.setUnit(unit);
			unit.setItem(null);
		}this.isTerminated = true;
	}

	/**
	 * check whether this Item is terminated
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}

	/**
	 * Variable registering whether this Item is terminated
	 */
	private boolean isTerminated;

	public void advanceTime(double dt) throws IllegalArgumentException{

	}

}
