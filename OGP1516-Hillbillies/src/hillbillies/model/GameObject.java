package hillbillies.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @Invar  The Position of each GameObject must be a valid Position for any
 *         GameObject.
 *       | isValidPosition(getPosition())
 */
public abstract class GameObject implements IAdvanceable{
	/**
	 * Constructor initializing a GameObject with a given position
	 * 
	 * @param  position
	 *         The Position for this new GameObject.
	 * @effect The Position of this new GameObject is set to
	 *         the given Position.
	 *		| this.setPosition(position)
	 */
	public GameObject(Position position) throws IllegalArgumentException {
		if (! this.canHaveAsPosition(position)){
			throw new IllegalArgumentException();
		}
		this.setPosition(position);
	}


	/**
	 * Initialize this new GameObject with given Position.
	 *
	 * @param  position
	 *         The Position for this new GameObject.
	 * @effect The Position of this new GameObject is set to
	 *         the given Position.
	 *       | this.setPosition(position)
	 */
	public GameObject(double[] position)
			throws IllegalArgumentException {
		if (! this.canHaveAsPosition(position)){
			throw new IllegalArgumentException();
		}
		this.setPosition(position);
	}

	/* Position */

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
	 * @return true if and only if the position is within bounds of the units game world
	 * 			| this.getWorld().withinBouds(position) ???
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
	 * @post   The Position of this new GameObject is equal to
	 *         the given Position.
	 *       | new.getPosition() == position
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
		if (! this.canHaveAsPosition(position))
			throw new IllegalArgumentException(position + " is not a valid position.");
		this.previousPosition = this.getPosition();
		this.position = new Position(position);
	}

	/**
	 * Variable registering the Position of this GameObject.
	 */
	private Position position;

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


	public void advanceTime(double dt) throws IllegalArgumentException{

	}

	/**
	 * return the world this unit belongs to
	 */
	public World getWorld(){
		return this.world;
	}
	
	protected World world;
	
	/**
	 * 
	 */
	protected Random random = new Random();

}
