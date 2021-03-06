package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @Invar  Each gameObject can have its Position as its position
 *       | this.ccacnHaveAsPosition(this.getPosition())
 */
public abstract class GameObject implements IAdvanceable{
	
	public GameObject(World world, Position position) throws IllegalArgumentException {
		this(world);
		this.setPosition(position);
	}
	
	/**
	 * 
	 * @param world
	 */
	// TODO documentation
	public GameObject(World world){
		this.setPosition(this.getRandomValidInitialPosition(world));
	}
	
	/**
	 *
	 */
	// TODO documentation
	public GameObject() {
		
	}
	
	/* Position */

	/**
	 * return a random valid Position
	 */
	protected Position getRandomValidInitialPosition(World world){
		Position position = null;
		do {
			double X = Utils.randomDouble(world.getMinXCoordinate(), world.getMaxXCoordinate()); 
			double Y = Utils.randomDouble(world.getMinYCoordinate(), world.getMaxYCoordinate());
			double Z = Utils.randomDouble(world.getMinZCoordinate(), world.getMaxZCoordinate());
			position = new Position(X, Y, Z);
		} while (!this.canHaveAsPosition(position));
		return position;
	}
	
	/**
	 * Return the Position of this GameObject.
	 */
	@Basic @Raw
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Checks whether the given array representation of a position is a valid position for a Unit of this units world.
	 *
	 *@param position 
	 *		The array representation of a position to check
	 *@return True if and only if 
	 *             the corresponding position is a valid position
	 *             |for each i in position.length:
	 *             |    if (! (position[i] >= getMinCoordinate() and position < getMaxCoordinate()))
	 *             |		result == false
	 *             | result == (result && position.length == 3)
	 */
	@Raw
	public boolean canHaveAsPosition(double[] position){     
		if (position.length != 3){
			return false;
		}
		Position positionToCheck = new Position(position);
		return canHaveAsPosition(positionToCheck);                             
	}

	/**
	 * check whether the given position is a valid position for a unit of this units world
	 * @param position
	 * 			The position to check
	 * @return true if and only if the position is within bounds of the units game
	 * 			world and if the cube corresponding with that Position is passable terrain
	 * 			| result == this.getWorld().withinBounds(position) && 
	 * 			|	this.getWorld().isPassableCube(position.toCoordinate());
	 */
	@Raw
	public boolean canHaveAsPosition(Position position){
		return this.getWorld().withinBounds(position) && this.getWorld().isPassableCube(position.toCoordinate());
	}

	/**
	 * Set the Position of this GameObject to the given Position.
	 * 
	 * @param  position
	 *         The new Position for this GameObject.
	 * @post   The new Position of this GameObject is equal to
	 *         the given Position.
	 *       | new.getPosition() == position
	 * @post
	 * 		The new previousPosition of this GameObject is equal to the
	 * 		previous position of this GameObject.
	 * @throws IllgalArgumentException
	 *         The given Position is not a valid Position for any
	 *         GameObject.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(Position position) 
			throws IllegalArgumentException {
		if (! this.canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.setPreviousPosition(this.getPosition());
		this.position = position;
	}

	/**                                                                                                                                                                                  
	 * Set the position of this  unit to the given position.                                                                                                 
	 *                                                                                                                                                                                   
	 * @param  position
	 *         The new position for this Unit.                                                                                                                       
	 * @post   The position of this new Unit is equal to                                                                                                             
	 *         the given position.                                                                                                                                              
	 *       | new.getPosition() == position                                                                                                                           
	 * @throws IllegalArgumentException                                                                                                                                                        
	 *         The given position is not a valid position for any                                                                                                      
	 *         Unit.                                                                                                                                                          
	 *       | ! isValidPosition(position)                                                                                                                        
	 */
	public void setPosition(double[] position)
			throws IllegalArgumentException {
		this.setPosition(new Position(position));
	}

	/**
	 * Variable registering the Position of this GameObject.
	 */
	protected Position position;
	
	/**
	 * Sets the previous position of this GameObject to the given Position.
	 * @param position
	 * 		The Position to set it to.
	 */
	private void setPreviousPosition(Position position) {
		this.previousPosition = position;
	}

	/**
	 * return the previous position of this unit 
	 */
	public Position getPreviousPosition(){
		return this.previousPosition;
	}
	
	/**
	 * Holds the previous position of the Unit. Very important to determine whether it has reached its destination
	 */
	protected Position previousPosition;

	/**
	 * Tells whether at least one of the neighboring cubes of this GameObject
	 * GameObject is solid (or the GameObject stands on the ground, that's fine too).
	 * @return
	 * 		True iff this GameObject's World says its cube is around a solid one.
	 */
	protected boolean aroundSolid(){
		return this.getWorld().isAroundSolid(this.getWorld().cubeCoordinates(this.getPosition()));
	}
	
	/**
	 * Check whether this GameObject is above a solid cube or the floor of the World.
	 * @return
	 * 		True iff this GameObject's World says its cube is above a solid one.
	 */
	protected boolean aboveSolid(){
		return this.getWorld().isAboveSolid(this.getWorld().cubeCoordinates(this.getPosition()));
	}
	
	/**
	 * Makes the GameObject advance one tick of game time.
	 * @param dt
	 * 		The time passed since the last tick. Should not be getMaxDT() or more.
	 */
	public abstract void advanceTime(double dt) throws IllegalArgumentException;

	/**
	 * return the world this unit belongs to
	 */
	public World getWorld(){
		return this.world;
	}
	
	protected World world;

}
