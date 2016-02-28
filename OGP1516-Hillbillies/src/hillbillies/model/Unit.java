package hillbillies.model;
import java.util.*;

import ogp.framework.util.*;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.ModelException;
/**
 * 
 * @author Willem Seynaeve and Felix Schönlank
 * 
 *
 */
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
	 * @throws ModelException if one of the initial attributes are not valid
	 * 		|if (notValidInitialToughness( toughness ) || notValidInitialSterngth( strength ) || notValidInitialAgility( agility ) || notValidInitialWeight( weight ))
     *		|		throw ModelException
	 */
	@Raw
	public Unit (String name, double[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException{
			if (notValidInitialToughness( toughness ) || notValidInitialSterngth( strength ) || notValidInitialAgility( agility ) || notValidInitialWeight( weight ))
				throw new ModelException();
			setName( name );
			setPosition( initialPosition );
			setAgility( agility );
			setStrength( strength );
			setToughness( toughness );
			setWeight( weight ); /* Weight must be set after strength and agility */ 
				
			}
	/**
	 * 
	 * @return true if and only if the given toughness is larger then 100 or smaller then 25
	 * 		| toughness > 100 || toughness < 25
	 */
	public boolean notValidInitialToughness(int toughness ){
		return toughness > 100 || toughness < 25;
	}
	
	/**
	 * 
	 * @return true if and only if the given strength is larger then 100 or smaller then 25
	 * 		| strength > 100 || strength < 25
	 */
	public boolean notValidInitialSterngth(int strength ){
		return strength > 100 || strength < 25;
	}
	
	/**
	 * 
	 * @return true if and only if the given agility is larger then 100 or smaller then 25
	 * 		| agility > 100 || agility < 25
	 */
	public boolean notValidInitialAgility(int agility ){
		return agility > 100 || agility < 25;
	}
	
	/**
	 * 
	 * @return true if and only if the given weight is larger then 100 or smaller then 25
	 * 		| weight > 100 || weight < 25
	 */
	public boolean notValidInitialWeight(int weight ){
		return weight > 100 || weight < 25;
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
	
	/**
	 * 
	 * @param name
	 * @return True if and only if the name is longer then 2 and the first character is uppercase
	 * 			| if (!(name == null) && isUpperCase(name.charAt(0) && name.lenth() >= 2)
	 * 			| 		return true 
	 */
	public boolean isValidName(String name) {
		return (!(name == null) && (Character.isUpperCase(name.charAt(0)) && (name.length() >= 2)));
	}
	

	
	/* Position */

 	     
	/**
	 * return the position of the center of the unit
	 *
	 */
	@Basic 
	public double[] getPosition(){     
	    return this.Position;          
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
	    if (!isValidPosition(Position)){
	    	throw new ModelException();
	    }
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
	    	if (!(coordinate >= getMinCoordinate() && (coordinate <= getMaxCoordinate()))){ 
	    		result = false;
	    	}
	    }
	    return result;                             
	}
	
	/**
	 * Return the maximum coordinate in every direction of the Game World
	 */
	public int getMaxCoordinate(){
		return 50;
	}
	
	/**
	 * Return the minimum coordinate in every direction of the board
	 */
	public int getMinCoordinate(){
		return 0;
	}
	
	
	/* Weight */
	
	/**
	 * Get the weight of this unit
	 */
	@Basic 
	public int getWeight(){
		return this.weight;
	}
	
	/**
	 * Set the weight of this unit to a given weight 
	 * 
	 * @param weight
	 * @post if the unit can have weight as its weight its weight it set to the given weight
	 * 		|if (isValidWeight(weight))
	 * 		|	new.getWeight() == weight
	 */
	@Raw
	public void setWeight(int weight){
		if (isValidWeight(weight))
			this.weight = weight;
	}

	/**
	 * 
	 * @param weight
	 * 			the weight you would like to check
	 * @return true if and only if the weight is between 0 and 200 inclusively and the weight is not larger then the maximum weight 
	 * 			| weight <= getMaxWeight() && weight >= 0 && weight <= 200
	 */
	private boolean isValidWeight(int weight) {
		return weight <= getMaxWeight() && weight >= getMinWeight()  ;
	}
	
	/**
	 * @return the maximum weight this unit can have
	 */
	private int getMaxWeight() {
		return 200;
	}

	/**
	 * 
	 * @return the minimum weight of this unit 
	 */
	private int getMinWeight(){
		return ((this.getStrength() + this.getAgility()) / 2);
	}

	/* Agility */
	/** 
	 * Get the agility of the given unit
	 */
	@Basic 
	public int getAgility(){
		return this.agility;
	}
	
	/**
	 * Set the agility of this unit to a given agility
	 * 
	 * @param agility
	 * 			The agility you want to given this unit 
	 * @Post if the agility is a valid agility for this unit the agility of this unit is set to agility
	 * 		|if (isValidAgility( agility ))
	 * 		|	new.getAgility() == agility
	 */
	public void setAgility(int agility ){
		if (isValidAgility(agility))
			this.agility = agility;
	}
	
	/**
	 * 
	 * @param agility
	 * @return true if and only if the given agility is larger then the minimum agility for a unit and is smaller then the maximum agility 
	 * 		| agility >= getMinAgility() && agility <= getMaxAgility() 
	 */
	public boolean isValidAgility(int agility ){
		return agility >= getMinAgility() && agility <= getMaxAgility();
	}
	
	/**
	 * @return The minimum agility for a unit 
	 */
	public int getMinAgility(){
		return 0;
	}
	
	/**
	 * @return the maximum agility a unit can have 
	 */
	public int getMaxAgility(){
		return 200;
	}
	
	/* toughness */
	
	/**
	 * Return the toughness of this unit 
	 */
	public int getToughness(){
		return this.toughness;
	}
	
	/**
	 * Set the toughness of a unit to a given toughness
	 * @param toughness
	 * 			the toughness you would like to give the unit 
	 * @post if the given toughness is a valid toughness for this unit, the toughness of this unit is set to toughness
	 * 		|if (isValidToughness( toughness )
	 * 		|	new.getToughness() == toughness
	 */
	public void setToughness(int toughness ){
		if (isValidToughness(toughness))
			this.toughness = toughness;
	}
	
	/**
	 * 
	 * @param toughness
	 * @return true if and only if the toughness is larger then the minimum toughness and smaller then the maximum toughness
	 * 			| if toughness >= getMinToughness() && toughness <= getMaxToughness() 
	 */
	public boolean isValidToughness(int toughness ){
		return toughness >= getMinToughness() && toughness <= getMaxToughness();
	}
	
	/**
	 * @return the minimum toughness a unit can have
	 */
	public int getMinToughness(){
		return 0;
	}
	
	/**
	 * @return The maximum toughness a unit can have
	 */
	public int getMaxToughness(){
		return 200;
	}
	
	/* Strength */
	
	@Basic 
	public int getStrength(){
		return this.strength;
	}
	
	/**
	 * Set the strength of this unit to a given strength 
	 * 
	 * @param strength
	 * @post if the given strength is a valid strength for this unit the strength of the unit is set to the given strength 
	 * 		| if (isValidSStrength(strength)) 
	 * 		|	new.getStrength == strength
	 */
	private void setStrength(int strength){
		if (isValidStrength(strength))
				this.strength = strength;
	}
	
	/**
	 * 
	 * @param strength
	 * 		The strength you want to check
	 * @return true if and only if the given strength is between the maximum and the minimum strength of a unit 
	 * 			| (strength >= getMinStrength() && strength <= getMaxStrength())
	 */
	private boolean isValidStrength(int strength ){
		return strength >= getMinStrength() && strength <= getMaxStrength();
	}
	
	/**
	 * @return The minimum strength of a unit 
	 */
	public int getMinStrength(){
		return 0;
	}
	
	public int getMaxStrength(){
		return 200;
	}
	 
	private String name;
	private double[] Position;
	private int weight;
	private int agility;
	private int strength;
	private int toughness;
	private boolean enableDefaultBehavior;

	
}
