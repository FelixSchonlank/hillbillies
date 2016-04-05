package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**	 
 * @Invar  Each Item can have its weight as weight.
 * 		| canHaveAsWeight(this.getWeight())
 * @Invar
 * 		| !this.isTerminated <==> (this.hasUnit() <==> ! this.hasWorld())
 * @Invar
 * 		Each Item has a proper Unit attached to it.
 * 		| this.hasProperUnit()
 * @Invar
 * 		Each Item has a proper World attached to it.
 * 		| this.hasProperWorld()
 */
public class Item extends GameObject{
	
	private enum ItemState {
		NOTHING, FALLING
	}
	
	private ItemState state;
	
	/**
	 * Initialize a new Item with a given position and a random weight, and
	 * that starts by being attached to a given World.
	 */
	public Item( World world, Position position) {
		this.setWorld(world);
		this.setPosition(position);
		this.weight = Utils.randomInt(getMinWeight(), getMaxWeight());
		this.state = ItemState.NOTHING;
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
	 * 		True iff this Item's unit is valid, and
	 * 		If not terminated: this Item's Unit references this Item back if it
	 * 			is effective.
	 * 		If terminated: this Item's Unit is not effective.
	 */
	public boolean hasProperUnit() {
		// The documentation has been translated into Java code masterfully
		// through the use of my unbelievable Logic Skillz.
		return isValidUnit(this.getUnit())
				&& (this.isTerminated() || (this.getUnit() == null || this.getUnit().getItem() == this))
				&& (!this.isTerminated() || this.getUnit() == null);
	}

	/**
	 * Set the unit that is carrying this Item to a given Unit
	 */
	@Raw //@Raw because calling this method can probably make Items disappear if not used properly.
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
	 * 		True iff this Item's World is valid, and
	 * 		If not terminated: this Item's World references this Item back if it
	 * 			is effective.
	 * 		If terminated: this Item's World is not effective.
	 */
	public boolean hasProperWorld() {
		// The documentation has been translated into Java code masterfully
		// through the use of my unbelievable Logic Skillz.
		return isValidWorld(this.getWorld())
				&& (this.isTerminated() || (this.getWorld() == null || this.getWorld().hasAsItem(this)))
				&& (!this.isTerminated() || this.getWorld() == null);
	}
	
	/**
	 * Set the world of this Item to the given world and add this items of the given world
	 * @param world
	 * @throws IllegalArgumentException
	 * 		If the unit can not have the given world as its world or if the world can not have this unit as one of its unit 
	 */
	@Raw //@Raw because calling this method can probably make Items disappear if not used properly.
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
	
	/* Methods */
	
	/**
	 * Makes this Item act out its behavior for one tick of game time.
	 * @param dt
	 * 		The passed time since last tick
	 * @throws IllegalArgumentException
	 * 		If
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {
		if (dt > getMaxDT()) {
			throw new IllegalArgumentException("dt went over its maximum of " + getMaxDT() + ". dt: " + dt);
		}
		if (this.hasWorld()) {
			if (this.state == ItemState.NOTHING) {
				doBehaviorNothing(dt);
			} else if (this.state == ItemState.FALLING) {
				doBehaviorFalling(dt);
			}
		}
	}
	
	/**
	 * Makes this Item transition to the nothing state.
	 * @post
	 * 		This Item will be in the NOTHING state.
	 */
	private void transitionToNothing() {
		this.state = ItemState.NOTHING;
	}
	
	/**
	 * Carries out the behavior this Item should have when it is doing nothing
	 * according to the FSM, which is in this case, well, nothing.
	 * @param dt
	 * 		The time passed since the last tick.
	 * @effect
	 * 		If this Item is not above solid ground, it starts falling. 
	 */
	private void doBehaviorNothing(double dt) {
		if (!this.aboveSolid()) {
			this.transitionToFalling();
		}
	}
	
	/**
	 * Makes this Item transition to the falling state.
	 * @post
	 * 		This Item will be in the FALLING state.
	 */
	private void transitionToFalling() {
		this.state = ItemState.FALLING;
	}
	
	/**
	 * Carries out the behavior this Item should have when it is falling
	 * according to the FSM. This means it will fall down.
	 * @param dt
	 * 		The time passed since the last tick.
	 * @effect
	 * 		If this Item is above solid ground, it stops falling.
	 * 		If it is above solid ground, it keeps on falling down.
	 */
	private void doBehaviorFalling(double dt) {
		if (this.aboveSolid()) {
			this.transitionToNothing();
		} else {
			this.doFalling(dt);
		}
	}
	
	/**
	 * Makes this Item fall down a bit.
	 * @param dt
	 * 		The time passed since last tick.
	 * @post
	 * 		| (new this).getPosition() ==
	 * 		| VectorD.add(this.getPosition(), VectorD.multiply(getFallingVelocity(), dt))
	 */
	private void doFalling(double dt) {
		VectorD velocity = getFallingVelocity();
		VectorD deltaPosition = VectorD.multiply(velocity, dt);
		this.setPosition(Position.add(this.getPosition(), deltaPosition));
	}
	
	/**
	 * Gives back the velocity with which a Unit falls.
	 */
	private VectorD getFallingVelocity() {
		return new VectorD(0, 0, -3);
	}
	
	public static double getMaxDT() {
		return maxDT;
	}
	
	private final static double maxDT = 0.2d;

}
