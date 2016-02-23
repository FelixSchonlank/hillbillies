package hillbillies.model;
import java.util.*;

import ogp.framework.util.*;

import be.kuleuven.cs.som.annotate.Basic;
import ogp.framework.util.ModelException;

public class Unit {
	
	/* Constructor*/
	/**
	 * Create a new unit with a given name, Position, weight, agility, strength, toughness and behavior
	 * 
	 * @param name
	 * @param initialPosition
	 * @param weight
	 * @param agility
	 * @param strength
	 * @param toughness
	 * @param enableDefaultBehavior
	 * @return
	 * @throws ModelException
	 */
	public Unit createUnit(String name, double[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException;{
				setName(name);
				
			}
			
	/* Name */
	/**
	 * Give the name of this unit
	 */
	@Basic 
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set the name to a given name
	 * 
	 * @param name
	 * 			|The name of the unit 
	 * @post the name of the unit is the given name 
	 * 		|new.getName() == name
	 * @throws ModelException
	 * 			The name is not a valid name for a unit 
	 * 		| if (!isValidName(name)
	 */
	public void setName(String name) throws ModelException {
		if (!isValidName(name))
			throw new ModelException();
		this.name = name;
	}
		
	}
	
	/**
	 * 
	 * @param name
	 * @return True if and only if the name is longer then 2 and the first character is uppercase
	 * 			| if (!(name == null) && isUpperCase(name.charAt(0) && name.lenth() >= 2)
	 * 			| 		return true 
	 */
	public boolean isValidName(String name) {
		return (!(name == null) && (isUpperCase(name.charAt(0)) && (name.length() >= 2));
	}
	

	
	/* Position */



	/**
	 * Get the precise coordinate of the given unit
	 * 
	 * @param unit
	 *            The unit for which to return the position.
	 * @return The coordinate of the center of the unit, as an array with 3 doubles {x, y, z}.
	 *         |unit.Position
	 * @throws ModelException
	 *             the unit is not a valid unit
	 *             |unit == null
	 */
        public double[] getPosition(Unit unit) throws ModelException{     
	     if (unit == null)                                                 
	         throw new ModelException();                             
 	     return unit.Position;              

 	     
	/**
	 * return the position of the center of the unit
	 *
	 */
	@Basic 
	public double[] getPosition(){     
	    return this.position;          
	}

	/**
	 *sets the Position to a given position
	 *@post The new position of the unit is equal to the given position
	 *      |new.position == position
	 *@throw ModelException
	 *        If the Position is not a valid position
	 *       |if !(isValidPosition(Position)
	 */
	public void setPosition(double[] Position)throws ModelException{
	    if (!isValidPosition(Position)
		throw new ModelException();
	    this.Position = Position;      
	}


	/**
	 *Checks whether the given position is a valid position
	 *
	 *@param: Position (The position to check)
	 *@return: True if and only if 
	 *             Position contains exactly 3 elements
	 *             all tree coordinates are positive and smaller or equal to 50
	 *             |Position.lenth == 3
	 *             |for each i in Position.lenth:
	 *             |    Position[i] >=0 and Position <= 50
	 */
	public boolean isValidPosition(double[] Position){     
	    boolean result = (Position.length == 3);             
	    for (double coordinate : Position){              
		if (!((coordinate >= 0) and (coordinate <= 50){ 
		    result = false;                         
	    return result;                             
	    }




	/**
	 * Get the coordinate of the cube occupied by the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the cube coordinate.
	 * @return The coordinate of the cube in which the center of the unit lies,
	 *         as an array with 3 integers {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int[] getCubeCoordinate(Unit unit) throws ModelException;
	
	/* Weight */
	
	/**
	 * Get the weight of this unit
	 */
	@Basic 
	public int getWeight(){
		return this.weight;
	}
	
	/* agility */
	
	/** 
	 * Get the agility of the given unit
	 */
	@basic 
	public int getAgility(){
		return this.agility;
	}
	
	/* toughness */
	
	/**
	 * Return the toughness of this unit 
	 */
	public int getToughness(){
		return this.toughness;
	}
	 
	private String name;
	private double[] Position;
	private int weight;
	private int agility;
	private int strength;
	private int toughness;
	private boolean enableDefaultBehavior;


}
