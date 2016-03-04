package hillbillies.model;

import java.util.*;

import java.lang.Math;
import java.util.Random;

import ogp.framework.util.*;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.ModelException;

/**
 * 
 * @author Willem Seynaeve and Felix Schönlank
 * 
 * @invar  The position of each Unit must be a valid position for any
 *         Unit.
 *       | isValidPosition(getPosition())
 *
 * @invar  The Name of each Unit must be a valid Name for any
 *         unit.
 *       | isValidName(getName())
 * 
 * @invar  The Weight of each unit must be a valid Weight for any
 *         unit.
 *       | isValidWeight(getWeight())       
 *
 * @invar  The Strength of each Unit must be a valid Strength for any
 *         Unit.
 *       | isValidSrength(getSrength())
 *
 * @invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 *       
 * @invar  The toughness of each unit must be a valid toughness for any unit.
 *       | isValidToughness(getToughness())
 * 
 * @invar  The HP of each unit must be a valid HP for any
 *         unit.
 *       | isValidHP(getHP())
 *
 * @invar  The Stamina of each unit must be a valid Stamina for any
 *         unit.
 *       | isValidStamina(getStamina())
 * 
 * @invar  The state of each unit must be a valid state for any
 *         unit.
 *       | isValidState(getState())
 *
 * @invar  Each Unit can have its default behavior as default behavior.
 *       | isValidDefaultBehaviorEnabled(this.getDefaultBehaviorEnabled())
 *       
 */
public class Unit {
	
	
	
	/* Constructor */
	
	/**
	 * Create a new unit with a given name, position, weight, agility, strength, toughness and behavior
	 * 
	 * @param  name                                                                                                                                                         
     *      The name for this new Unit.                                                                                                                       
  	 * @effect The name of this new unit is set to                                                                                                               
     *      the given name.                                                                                                                                              
     *    | this.setName(name)
	 * @param initialPosition
     *     The position for this new Unit.                                                                                                                       
  	 * @effect The position of this new Unit is set to                                                                                                               
     *     the given initialPosition.                                                                                                                                              
     *   | this.setPosition(initialPosition) 
	 * @param  weight                                                                                                                                                                    
	 *         The Weight for this new unit.                                                                                                                                             
	 * @post   If the given Weight is a valid initial Weight for any unit,                                                                                                                       
	 *         the Weight of this new unit is equal to the given                                                                                                                         
	 *         Weight. Otherwise, the Weight of this new unit is equal                                                                                                                   
	 *         to getMaxWeight().                                                                                                                                                        
	 *       | if (validInitalWeight(weight))                                                                                                                                                
	 *       |   then new.getWeight() == weight                                                                                                                                          
	 *       |   else new.getWeight() == getMaxWeight()
	 * @param  agility
	 *         The agility for this new unit.
	 * @post   If the given agility is a valid initial agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility. Otherwise, the agility of this new unit is equal
	 *         to getMaxAgility().
	 *       | if (validInitialAgility(agility))
	 *       |   then new.getAgility() == agility
	 *       |   else new.getAgility() == getMaxAgility()
	 * @param  strength
	 *         The Strength for this new Unit.
	 * @post   If the given Strength is a valid initial Strength for any Unit,
	 *         the Strength of this new Unit is equal to the given
	 *         Strength. Otherwise, the Strength of this new Unit is equal
	 *         to getMaxStength().
	 *       | if (validInitialSrength(strength))
	 *       |   then new.getStrength() == strength
	 *       |   else new.getStrength() == getMaxStength()
	 * @param toughness
	 *        The Toughness for this new Unit.
	 * @post   If the given Toughness is a valid initial Toughness for any Unit,
	 * 		   the Toughness of this new Unit is equal to the given Toughness.
	 * 		   Otherwise, the Toughness of this new Unit is equal to getMaxToughness().
	 * 		 | if (validInitialToughness(tougness))
	 * 		 |   then new.getToughness() == toughness
	 * 		 |   else new.getToughness() == getMaxToughness()
	 * @param  enableDefaultBehavior
	 *         The default behavior for this new Unit.
	 * @post   The default behavior of this new Unit is equal to the given
	 *         default behavior.
	 *       | new.getDefaultBehaviourEnabled() == enabledDefaultBehavior
	 * @throws ModelException
	 *         This new Unit cannot have the given default behavior as its default behavior.
	 *       | ! isValidDefaultBehaviorEnabled(this.getDefaultBehaviorEnabled())
	 * @throws ModelException if either name or initialPosition are not valid 
	 * 		|if (!isValidName(name) || !isValidPosition(position) || !isValidDefaultBehaviorEnabled(enableDefaultBehavior))
     *		|		throw ModelException
     *
	 */
	@Raw
	public Unit (String name, double[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException{
			
		if (!isValidName(name) || !isValidPosition(initialPosition) || !isValidDefaultBehaviorEnabled(enableDefaultBehavior)){
			throw new ModelException("Name, position, or enableDefaultBehavior are not valid");
		}

		this.setName( name );
		this.setPosition( initialPosition );
		this.setDefaultBehaviorEnabled( enableDefaultBehavior);

		if (!validInitialAgility(agility)){
			setAgility( getMaxAgility() );
		}else{
			setAgility( agility );
		}

		if (!validInitialStrength( strength )){
			setStrength( getMaxStrength() );
		}else{
			setStrength( strength );
		}

		if (!validInitialToughness( toughness )){
			setToughness( getMaxToughness() );
		}else{
			setToughness( toughness );
		}

		if (!validInitialWeight( weight )){
			setWeight( getMaxWeight() ); 		/* Weight must be set after strength and agility */ 
		}else{
			setWeight( weight );
		}
		
		setHP(getMaxHP());
		setStamina(getMaxStamina());

	}
	
	
	
	/* Initial attribute checkers */
	
	/**
	 * Checks whether the given value is a valid initial value for toughness.
	 * @return true if and only if the given toughness is not larger than 100 or smaller than 25
	 * 		| result == (toughness <= 100 && toughness >= 25)
	 */
	private boolean validInitialToughness(int toughness ){
		return (toughness <= 100 && toughness >= 25);
	}
	
	/**
	 * Checks whether the given value is a valid initial value for strength.
	 * @return true if and only if the given strength is not larger then 100 or smaller then 25
	 * 		| result (strength <= 100 && strength >= 25)
	 */
	private boolean validInitialStrength(int strength ){
		return (strength <= 100 && strength >= 25);
	}
	
	/**
	 * Checks whether the given value is a valid initial value for agility.
	 * @return true if and only if the given agility is not larger then 100 or smaller then 25
	 * 		| result == (agility <= 100 && agility >= 25)
	 */
	private boolean validInitialAgility(int agility ){
		return (agility <= 100 && agility >= 25);
	}
	
	/**
	 * Checks whether the given value is a valid initial value for weight.
	 * @return true if and only if the given weight is not larger then 100 or smaller then 25
	 * 		| result == (weight <= 100 && weight >= 25)
	 */
	private boolean validInitialWeight(int weight ){
		return (weight <= 100 && weight >= 25);
	}
	
        
	
	/* Methods */
	
	/**
	 * 
	 * @param dt
	 */
	public void advanceTime(double dt) throws ModelException{
		if(dt >= getMaxDT()){
			throw new ModelException("dt went over its maximum of " + getMaxDT());
		}
		State state = this.getState();
		if(state == State.NOTHING){
			doBehaviorNothing(dt);
		}else if(state == State.MOVING){
			doBehaviorMoving(dt);
		}else if(state == State.RESTING_INIT){
			doBehaviorRestingInit(dt);
		}else if(state == State.RESTING_HP){
			doBehaviorRestingHP(dt);
		}else if(state == State.RESTING_STAMINA){
			doBehaviorRestingStamina(dt);
		}else if(state == State.WORKING){
			doBehaviorWorking(dt);
		}else if(state == State.ATTACKING){
			doBehaviorAttacking(dt);
		}
	}
	
	
	
	/* Movement */
	
	/**
	 * 
	 * @param dx
	 * 		The difference in cubes to go in x direction
	 * @param dy
	 * 		The difference in cubes to go in y direction 
	 * @param dz
	 * 		The difference in cubes to go in z direction
	 * @throws ModelException
	 * 		If not in the right state to perform this action
	 * 		| !(this.getState() == State.NOTHING ||
	 * 		| 	this.getState() == State.RESTING_HP ||
	 *		| 	this.getState() == State.RESTING_STAMINA ||
	 *		| 	this.getState() == State.WORKING)
	 * @throws ModelException
	 * 		If at least one of the parameters is not -1, 0, or 1
	 * 		| !(dx==-1 || dx==0 || dx==1) || !(dy==-1 || dy==0 || dy==1) || !(dz==-1 || dz==0 || dz==1)
	 * @throws ModelException
	 * 		If the calculated destination is out of bounds
	 * 		| destination[0] < getMinCoordinate() || destination[0] >= getMaxCoordinate() ||
	 * 		| destination[1] < getMinCoordinate() || destination[1] >= getMaxCoordinate() ||
	 * 		| destination[2] < getMinCoordinate() || destination[2] >= getMaxCoordinate()
	 */
	public void moveToAdjacent(int dx, int dy, int dz) throws ModelException {
		if (!(this.getState() == State.NOTHING ||
				this.getState() == State.RESTING_HP ||
				this.getState() == State.RESTING_STAMINA ||
				this.getState() == State.WORKING)){
			throw new ModelException("Cannot do moveToAdjacent from this state");
		}else if(!(dx==-1 || dx==0 || dx==1) || !(dy==-1 || dy==0 || dy==1) || !(dz==-1 || dz==0 || dz==1)){
			throw new ModelException("One of the parameters not -1, 0, or 1.");
		}else{
			int[] currentCube = cubeCoordinates(this.getPosition());
			int[] destination = new int[] {currentCube[0]+dx, currentCube[1]+dy, currentCube[2]+dz};
			if(destination[0] < getMinCoordinate() || destination[0] >= getMaxCoordinate() ||
					destination[1] < getMinCoordinate() || destination[1] >= getMaxCoordinate() ||
					destination[2] < getMinCoordinate() || destination[2] >= getMaxCoordinate()){
				throw new ModelException("One of the coordinates is out of bounds.");
			}
			immediateTarget = cubeCenter(destination);
		}
	}
	
	/**
	 * Calculates a path for the Unit to follow towards the destination.
	 * @param destination
	 * 		The coordinate of the cube to go to
	 * @throws ModelException
	 * 		If the destination is not within the bounds.
	 * 		| !withinBounds(destination)
	 */
	public void moveTo(int[] destination) throws ModelException {
		if(!withinBounds(destination)){
			throw new ModelException("destination out of bounds.");
		}
		
		path.clear();
		
		int[] position = cubeCoordinates(this.getPosition());
		int dx, dy, dz;
		while(!areSameCube(position, destination)){
			if(position[0] == destination[0]){
				dx = 0;
			}else if(position[0] < destination[0]){
				dx = 1;
			}else{
				dx = -1;
			}
			if(position[1] == destination[1]){
				dy = 0;
			}else if(position[1] < destination[1]){
				dy = 1;
			}else{
				dy = -1;
			}
			if(position[2] == destination[2]){
				dz = 0;
			}else if(position[2] < destination[2]){
				dz = 1;
			}else{
				dz = -1;
			}
			path.add(cubeCenter(new int[] {dx, dy, dz}));
		}
	}
	
	/**
	 * Set the shouldAttack flag high and set this.victim to the given victim
	 * @param victim 
	 * @Post the shouldAttack flag is set high and this.victim is set to the given victim
	 * 		|this.shouldAttack && this.victim == victim
	 * @throws modelexception if state is not NOTHING, RESTING_HP, RESTING_STAMINA or WORKING or if the victim is null 
	 * 		|if (victim == null || !(this.getState() == NOTHING || this.getState() == RESTING_HP || this.getState() == RESTING_STAMINA || this.getState() ==WORKING))
	 * 		|	throw modelException
	 */
	public void attack(Unit victim) throws ModelException{
		if (victim == null || !(this.getState() == state.NOTHING || this.getState() == state.RESTING_HP || this.getState() == state.RESTING_STAMINA || this.getState() == state.WORKING)){
			throw new ModelException("Can not attack in this state");
		}else{
			this.shouldAttack = true;
			this.victim = victim;
		}
	}
	
	/**
	 * An instantaneous response to the attack. Everything is handled immediately:
	 * dodging, blocking, damage taking, teleportation.
	 * @param attacker
	 * 		The attacking unit. This parameter is used to get information about
	 * 		the damage that should be done.
	 * @throws IllegalArgumentException
	 * 		If attacker is null
	 * 		| attacker == null
	 */
	public void defend(Unit attacker) throws IllegalArgumentException, ModelException {
		if(attacker == null){
			throw new IllegalArgumentException("Attacker shouldn't be null.");
		}
		
		pointAt(attacker);
		
		if(dodgeSucceeds(attacker)){
			try{
				dodge();
			}catch(ModelException e){
				throw e;
			}
		}else if(!blockSucceeds(attacker)){
			takeDamage(attacker.getStrength() / 10);
		}
	}
	
	/**
	 * Set shouldRest flag to high
	 * @Post The shouldRest flag is set to high
	 * 		|new.shouldRest
	 * @throws ModelException if the state is not NOTHING or WORKING
	 * 		|if (!(state == state.NOTHING || state == state.WORKING))
	 * 		|	throw ModelException
	 */
	public void rest() throws ModelException{
		if (!(this.getState() == state.NOTHING || this.getState() == state.WORKING)){
			throw new ModelException("Can not go to resting from this state");
		}else{
			this.shouldRest = true;
		}
	}
	
	/**
	 * Set the shouldWork flag  to high
	 * @Post ShouldWork is set to true
	 * 		|this.shouldWork
	 * @throws ModelException if the state of the unit is not NOTHING, RESTING_HP or RESTING_STAMINA 
	 * 		|if (!(this.getState() == NOTHING || this.getState() == RESTING_HP || this.getState() == RESTING_STAMINA))
	 * 		|		throw ModelException
	 */
	public void work() throws ModelException{
		if (!(this.getState() == state.NOTHING || this.getState() == state.RESTING_HP || this.getState() == state.RESTING_STAMINA))
			throw new ModelException("Can not go to working from this state");
		else{
			this.shouldWork = true;
		}
	}
        


	/* Name */
	
	/**
	 * Give the name of this unit
	 */
	@Basic @Raw
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set the name of this unit to a given name
	 * @param  name                                                                                                                                                         
	 *         The new name for this unit.                                                                                                                       
	 * @post   The name of this unit is equal to                                                                                                             
	 *         the given name.                                                                                                                                              
	 *       | new.getName() == name                                                                                                                           
	 * @throws ModelException                                                                                                                                                        
	 *         The given name is not a valid name for any                                                                                                      
	 *         Unit.                                                                                                                                                          
	 *       | !isValidName(getName())                                                                                                                        
	 */
	@Raw
	public void setName(String name) throws ModelException {
		if (!isValidName(name))
			throw new ModelException();
		this.name = name;
	}
	
	/**
	 * Check whether the given name is a valid name for                                                                                                        
	 * any Unit.
	 *   
	 * @param name
	 * 			The name to check
	 * @return True if and only if the name is longer then 2 and the first character is upper case
	 * 			| result == (!(name == null) && isUpperCase(name.charAt(0) && name.length() >= 2)
	 */
	@Raw
	public static boolean isValidName(String name) {
		return ((name != null) && (Character.isUpperCase(name.charAt(0)) && (name.length() >= 2)));
	}
	
	
	
	/* Position */
	
	/**
	 * return the position of the center of the unit
	 */
	@Basic
	public double[] getPosition(){     
	    return this.position;
	}
	
	/**                                                                                                                                                                                  
	 * Set the position of this  unit to the given position.                                                                                                 
	 *                                                                                                                                                                                   
	 * @param  position
	 *         The new position for this Unit.                                                                                                                       
	 * @post   The position of this new Unit is equal to                                                                                                             
	 *         the given position.                                                                                                                                              
	 *       | new.getPosition() == position                                                                                                                           
	 * @throws ModelException                                                                                                                                                        
	 *         The given position is not a valid position for any                                                                                                      
	 *         Unit.                                                                                                                                                          
	 *       | ! isValidPosition(position)                                                                                                                        
	 */
	public void setPosition(double[] position)
			throws ModelException {
		if (! isValidPosition(position))
			throw new ModelException();
		this.position = position;
	}
	
	/**
	 * Checks whether the given position is a valid position for any Unit.
	 *
	 *@param position 
	 *		The position to check
	 *@return True if and only if 
	 *             position contains exactly 3 elements
	 *             all tree coordinates are positive and smaller than or equal to getMaxCoordinate()
	 *             |for each i in position.length:
	 *             |    if (! (position[i] >= getMinCoordinate() and position < getMaxCoordinate()))
	 *             |		result == false
	 *             | result == (result && position.length == 3)
	 */
	@Raw
	public static boolean isValidPosition(double[] position){     
	    if (position.length != 3){
	    	return false;
	    }
	    for (double coordinate : position){              
	    	if (!(coordinate >= getMinCoordinate() && (coordinate < getMaxCoordinate()))){ 
	    		return false;
	    	}
	    }
	    return true;                             
	}
	
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
	
	
	
	/* Weight */
	
	/* Weight */
	
	/**
	 * Get the weight of this unit
	 */
	@Basic @Raw
	public int getWeight(){
		return this.weight;
	}

	
	/**                                                                                                                                                                                  
	 * Set the Weight of this unit to the given Weight.                                                                                                                                  
	 *                                                                                                                                                                                   
	 * @param  weight                                                                                                                                                                    
	 *         The new Weight for this unit.                                                                                                                                             
	 * @post   If the given Weight is a valid Weight for any unit,                                                                                                                       
	 *         the Weight of this new unit is equal to the given                                                                                                                         
	 *         Weight.                                                                                                                                                                   
	 *       | if (canHaveAsWeight(weight))                                                                                                                                                
	 *       |   then new.getWeight() == weight 
	 *       |else 
	 *       |		new.getWeight() == getMaxWeight()                                                                                                                                         
	 */
	@Raw
	public void setWeight(int weight) {
		if (canHaveAsWeight(weight))
			this.weight = weight;
		else{
			this.weight = getMaxWeight();
		}
	}

	
	/**
	 * Check whether the given Weight is a valid Weight for                                                                                                                              
	 * any unit.                                                                                                                                                                         
	 *                                                                                                                                                                                   
	 * @param  weight                                                                                                                                                                    
	 *         The Weight to check.
	 * @return true if and only if the weight is between 0 and 200 inclusively and the weight is not larger then the maximum weight 
	 * 			| result == weight <= getMaxWeight() && weight >= 0 && weight <= 200
	 */
	public boolean canHaveAsWeight(int weight) {
		return weight <= getMaxWeight() && weight >= getMinWeight()  ;
	}
	
	
	/**
	 * The maximum weight this unit can have
	 */
	@Basic @Immutable
	private static int getMaxWeight() {
		return maxWeight;
	}
	

	/**
	 * The minimum weight a unit can have 
	 * @return the minimum weight of this unit
	 * 		|result ==  ((this.getStrength() + this.getAgility()) / 2)
	 */
	@Basic
	private int getMinWeight(){
		return ((this.getStrength() + this.getAgility()) / 2);
	}

	
	
	
	/* Agility */
	
	/* Agility */
	
	/** 
	 * Get the agility of the given unit
	 * @return The agility of the unit
	 * 		| result == this.agility
	 */
	@Basic @Raw
	public int getAgility(){
		return this.agility;
	}
	
	
	/**
	 * Set the agility of this unit to the given agility
	 * 
	 * @param agility
	 * 			The agility you want to give this unit 
	 * @Post if the agility is a valid agility for this unit the agility of this unit is set to agility
	 * 		|if (isValidAgility( agility ))
	 * 		|	new.getAgility() == agility
	 * @Post if the agility is not a valid agility for the unit the agility is set to getMaxAgility()
	 * 		|if (!isValidAgility(agility))
	 * 		|	new.getAgility() == getMaxAgility()
	 */
	@Raw
	public void setAgility(int agility ){
		if (isValidAgility(agility))
			this.agility = agility;
		else{
			this.agility = getMaxAgility();
		}
	}
	
	
	/**
	 * Checks whether a given agility is valid.
	 * @param agility
	 * 			The agility to check
	 * @return true if and only if the given agility is larger than the minimum agility for a unit and is smaller then the maximum agility 
	 * 		| result == (agility >= getMinAgility() && agility <= getMaxAgility())
	 */
	@Raw
	public boolean isValidAgility(int agility ){
		return agility >= getMinAgility() && agility <= getMaxAgility();
	}
	
	
	/**
	 * The minimum agility for a unit
	 */
	@Basic @Immutable
	public static int getMinAgility(){
		return minAgility;
	}
	
	
	/**
	 * Gives the highest agility a unit can have
	 */
	@Basic @Immutable
	public static int getMaxAgility(){
		return maxAgility;
	}
	
	
	
	
	/* Toughness */
	
	/* toughness */
	
	/**
	 * Return the toughness of this unit
	 * @return The toughness of this unit
	 * 		| result == this.toughness 
	 */
	@Basic @Raw
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
	 * @Post if the given toughness is not a valid toughness for a unit the Toughness is set to getMaxToughness
	 * 		|else
	 * 		|	new.getToughness() == getMaxToughness() 
	 */
	public void setToughness(int toughness ){
		if (isValidToughness(toughness))
			this.toughness = toughness;
		else{
			this.toughness = getMaxToughness();
		}
	}
	
	/**
	 * 
	 * @param toughness
	 * 			the toughness to check
	 * @return true if and only if the toughness is larger then the minimum toughness and smaller then the maximum toughness
	 * 			|result == if toughness >= getMinToughness() && toughness <= getMaxToughness() 
	 */
	public boolean isValidToughness(int toughness ){
		return toughness >= getMinToughness() && toughness <= getMaxToughness();
	}
	
	/**
	 * The minimum toughness a unit can have
	 * @return the Minimum toughness a unit can have is 0
	 * 		| result == 0 
	 */
	@Immutable
	public int getMinToughness(){
		return 0;
	}
	
	/**
	 * The maximum toughness a unit can have
	 * @return The maximum toughness is 200
	 * 		| result == 200
	 */
	@Immutable
	public int getMaxToughness(){
		return 200;
	}
	
	
	
	
	/* Strength */
	
	
	/**                                                                                                                                                                                  
	 * Return the Strength of this Unit.                                                                                                                                                 
	 */
	@Basic @Raw
	public int getStrength(){
		return this.strength;
	}
	
	
	/**
	 * Set the strength of this unit to a given strength 
	 * 
	 * @param strength
	 * 		| The new strength for this unit 
	 * @post if the given strength is a valid strength for this unit the strength of the unit is set to the given strength 
	 * 		| if (isValidStrength(strength)) 
	 * 		|	new.getStrength() == strength
	 * @Post if the given strength is not a valid strength the strength is set to getMaxStrength()
	 * 		|else	
	 * 		|	new.getStrength() == getMaxStrength() 
	 */
	@Raw
	private void setStrength(int strength){
		if (isValidStrength(strength))
				this.strength = strength;
		else{
			this.strength = getMaxStrength();
		}
	}
	
	
	/**
	 * Checks if a given strength is valid
	 * @param strength
	 * 		The strength you want to check
	 * @return true if and only if the given strength is between the maximum and the minimum strength of a unit 
	 * 			| result == (strength >= getMinStrength() && strength <= getMaxStrength())
	 */
	@Raw
	private static boolean isValidStrength(int strength ){
		return strength >= getMinStrength() && strength <= getMaxStrength();
	}
	
	
	/**
	 * Gives the minimal strength of a unit
	 */
	@Basic @Immutable
	public static int getMinStrength(){
		return minStrength;
	}
	
	/**
	 * Gives the maximal strength of a unit
	 */
	@Basic @Immutable
	public static int getMaxStrength(){
		return maxStrength;
	}
	
	
	
	
	/* Orientation */
	
	/* Orientation */
	
	/**
	 * Gives the Orientation of a Unit in radians 
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;
	}
	
	
	/**
	 * Give the default orientation of a Unit.
	 */
	@Basic @Immutable
	public double getDefaultOrientation(){
		return defaultOrientation;
	}
	
	
	/**
	 * Set the orientation of the unit to a given orientation
	 * 
	 * @param orientation
	 * @post if the orientation is larger then getMaxOrientation() the given orientation is decremented by getMaxOrientation() until it is smaller then getMaxOrientation()
	 * 		|if (this.getOrientation() >= getMaxOrientation())
	 * 		|	new.getOrientation() == orientation % getMaxOrientation()
	 * @post If the orientation smaller then getMinOrientation()
	 * 		|if (this.getOrientation() <= getMinOrientation())
	 * 		|	new.getOrientation() == getMaxOrientation() - (Orientation % getMaxOrientation())
	 * @post If the orientation is none of the previous the orientation is set to the given orientation   
	 * 		|else 
	 * 		|	new.getOrientation() == orientation 
	 */
	public void setOrientation(double orientation){
		orientation %=  orientation;
		if (orientation < 0 ){
			this.setOrientation(getMaxOrientation() - orientation);
		}
		else{
			this.setOrientation(orientation);
		}
	}
	
	
	/**
	 * Gives the maximum possible orientation 
	 */
	@Basic @Immutable
	public double getMaxOrientation(){
		return maxOrientation;
	}
	
	 
	/**
	 * Gives the minimum possible orientation 
	 */
	@Basic @Immutable
	public double getMinOrientation(){
		return minOrientation;
	}
	
	
	
	
	/* HP */
	
	/* HP */
	
	/**
	 * Gives the HP of this unit 
	 */
	@Basic
	public int getHP(){
		return this.HP;
	}
	
	
	/**
	 * Set the HP of this unit to the given HP.                                                                                                                                          
	 *                                                                                                                                                                                   
	 * @param  HP                                                                                                                                                                        
	 *         The new HP for this unit.                                                                                                                                                 
	 * @pre    The given HP must be a valid HP for any                                                                                                                                   
	 *         unit.                                                                                                                                                                     
	 *       | isValidHP(HP)                                                                                                                                                             
	 * @post   The HP of this unit is equal to the given                                                                                                                                 
	 *         HP.                                                                                                                                                                       
	 *       | new.getHP() == HP 
	 */
	public void setHP(int HP){
		assert isValidHP(HP);
		this.HP = HP;
	}
	
	
	/**
	 * Check whether a given HP is valid 
	 * @param HP
	 * @return True is and only if HP is between getMinHP() and getMaxHP()
	 * 		| result ==  HP >= getMinHP() && HP <= getMaxHP
	 */
	public boolean isValidHP(int HP){
		return (HP >= getMinHP() && HP <= getMaxHP());
	}
	
	
	/**
	 * the minimum HP a unit can have
	 * @return The maximum HP a unit can have is 200 * weight/100 * toughness/100
	 * 		| result == 200 * weight/100 * toughness/100
	 */
	private int getMaxHP() {
		return 2 * this.getWeight() * this.getToughness() / 100;
	}
	
	
	/**
	 * The minimum HP a unit can have
	 */
	@Basic @Immutable
	private static int getMinHP() {
		return minHP;
	}
	
	
	
	
	/* Stamina */
	
	
	/**
	 * Gives the Stamina of this unit 
	 */
	@Basic
	public int getStamina(){
		return this.stamina;
	}
	
	
	/**
	 * Set the Stamina of this unit to a given Stamina
	 * @param stamina
	 * 			The Stamina you would like to give to this unit
	 * @Pre  The given Stamina must be a valid Stamina for any unit.     
	 *         |isValidStamina(stamina)
	 * @post The stamina is set the given Stamina
	 * 		|new.getStamina() == stamina 
	 */
	public void setStamina(int stamina){
		assert isValidStamina( stamina );
		this.stamina = stamina;
	}
	
	
	/**
	 * Check whether a given Stamina is valid 
	 * @param stamina
	 * 			The stamina to check
	 * @return True if and only if Stamina is between getMinStamina() and getMaxStamina()
	 * 		| result == stamina >= getMinStamina() && stamina <= getMaxStamina()
	 */
	public boolean isValidStamina(int stamina){
		return (stamina >= getMinStamina() && stamina <= getMaxStamina());
	}
	
	
	/**
	 * the maximum Stamina a unit can have
	 * @return The maximum Stamina a unit can have is 2 * weight * toughness / 100
	 * 		| result == 2 * weight * toughness / 100
	 */
	private int getMaxStamina() {
		return 2 * weight * toughness /100;
	}
	
	
	/**
	 * The minimum Stamina a unit can have
	 * @return The lowest Stamina a unit can have
	 * 		|result == minStamina	 
	 */
	@Basic @Immutable
	private int getMinStamina() {
		return minStamina;
	}
	
	
	
	/* Default behavior */

	/**
	 * Return the default behavior of this Unit.
	 */
	@Basic @Raw
	public boolean getDefaultBehaviorEnabled() {
		return this.defaultBehaviorEnabled;
	}

	/**
	 * Set whether the default behavior is enabled
	 * @param defaultBehaviorEnabled
	 * 		Whether or not is should be enabled
	 * @post
	 * 		| new.getDefaultBehaviorEnabled() == defaultBehaviorEnabled;
	 * @throws ModelException
	 * 		If the given parameter is not valid
	 * 		| !isValidDefaultBehaviorEnabled(defaultBehaviorEnabled)
	 */
	public void setDefaultBehaviorEnabled(boolean defaultBehaviorEnabled) throws ModelException {
		if(!isValidDefaultBehaviorEnabled(defaultBehaviorEnabled)){
			throw new ModelException("Invalid defaultBehaviorEnabled given.");
		}
		this.defaultBehaviorEnabled = defaultBehaviorEnabled;
	}

	/**
	 * Check whether this Unit can have the given default behavior as its default behavior.
	 *  
	 * @param  defaultBehaviorEnabled
	 *         The default behavior to check.
	 * @return 
	 *       | result == true
	 */
	@Raw
	public static boolean isValidDefaultBehaviorEnabled(boolean defaultBehaviorEnabled) {
		return true;
	}
	
	
	
	/* State */
	
	/**
	 * Return the state of this unit.
	 */
	@Basic @Raw
	public State getState() {
		return this.state;
	}
	
	/**
	 * Set the state of this unit to the given state.
	 * 
	 * @param  state
	 *         The new state for this unit.
	 * @post   The state of this new unit is equal to
	 *         the given state.
	 *       | new.getState() == state
	 * @throws IllegalArgumentException
	 *         The given state is not a valid state for any
	 *         unit.
	 *       | ! isValidState(getState())
	 */
	@Raw
	public void setState(State state) 
			throws IllegalArgumentException {
		if (! isValidState(state))
			throw new IllegalArgumentException();
		this.state = state;
	}
	

	/**
	 * Check whether the given state is a valid state for
	 * any unit.
	 *  
	 * @param  state
	 *         The state to check.
	 * @return True iff the state is not null.
	 *       | result == state != null
	 */
	@Raw
	public static boolean isValidState(State state) {
		return state != null;
	}
	
	
	
	/* Helper methods */
	
	/**
	 * Set the state of this unit to Nothing 
	 */
	private void transitionToNothing(){
		this.state = state.NOTHING;
		this.setFlagsLow();
	}

	/**
	 * Set the state of this unit to Resting_init
	 */
	private void transitionToRestingInit(){
		this.state = state.RESTING_INIT;
		this.restingInitialCountdown = this.getRestingHPTime();
		this.setFlagsLow();
	}
	
	/**
	 * Set the state of this unit to RestingHP
	 */
	private void transitionToRestingHP(){
		this.state = state.RESTING_HP;
		this.restingHPCountdown = this.getRestingHPTime();
		this.setFlagsLow();
	}
	
	/**
	 * The time it takes for a unit to restore one HP
	 * @return
	 */
	@Basic  
	private float getRestingHPTime(){
		return 200/this.getStrength();
	}
	
	/**
	 * Set the state of this unit to RESTING
	 */
	private void transitionToRestingStamina(){
		this.state = State.RESTING_STAMINA;
		this.setFlagsLow();
		this.restingStaminaCountdown = getRestingStaminaTime();
	}
	
	/**
	 * Gives the time it takes for a unit to restore some amount of stamina
	 * @return The time it takes for a unit to restore some amount of stamina
	 * 		| result == 0.2f
	 */
	@Basic 
	private float getRestingStaminaTime() {
		return 0.2f;
	}

	/**
	 * Set the state of this unit to Working
	 * @effect The new state will be State.WORKING
	 * 		| new.state == State.WORKING
	 */
	private void transitionToWorking(){
		this.state = State.WORKING;
		this.workingCountdown = getWorkingTime();
		this.setFlagsLow();
	}

	/**
	 * Gives the time it takes for this unit to carry out some work.
	 * @return The time it takes for this unit to carry out some work, namely
	 * 		   500/this.getStrength()
	 * 		| result == 500/this.getStrength()
	 */
	@Basic 
	public float getWorkingTime(){
		return 500/this.getStrength();
	}
	
	/**
	 * Set the state of this unit to Attacking
	 */
	private void transitionToAttacking(){
		this.state = State.ATTACKING;
		this.setFlagsLow();
		this.attackingCountdown = getAttackingTime();
	}
	
	/**
	 * Gives the time it takes for a unit to attack
	 * @return The time it takes for a unit to attack
	 * 		| result == 1f
	 */
	@Basic @Immutable
	private float getAttackingTime() {
		return 1f;
	}
	
	/**
	 * Checks whether this Unit has reached its immediateTarget, or possibly
	 *  overshot it by some distance.
	 * @return true iff reached or overshot the immediateTarget position
	 * 	| result == between(immediateTarget[0], previousPosition[0], position[0])
	 *	|		|| between(immediateTarget[1], previousPosition[1], position[1])
	 *	|		|| between(immediateTarget[2], previousPosition[2], position[2])
	 */
	private boolean reachedImmediateTarget() {
		return (between(immediateTarget[0], previousPosition[0], position[0])
				|| between(immediateTarget[1], previousPosition[1], position[1])
				|| between(immediateTarget[2], previousPosition[2], position[2]));
	}
	
	/**
	 * Checks whether x lies in between y and z. It doesn't matter if y is to
	 * its left and z to its right or the other way around; both situations
	 * count.
	 * @param x
	 *        The value that should be in the middle
	 * @param y
	 *        One of the ends
	 * @param z
	 *        The other end
	 * @return true iff x is between y and z.
	 *        | result == (y < x && x < z) || (z < x && x < y)
	 */
	private boolean between(double x, double y, double z){
		return (y < x && x < z) || (z < x && x < y);
	}
	
	/**
	 * 
	 * @param victim
	 * 			|The unit you want to attack
	 * @return True if and only if the position of victim is a neighbouring cube of the position of this unit 
	 * 			|for (i = 0; i < this.getPosition().length ; i++)
	 * 			|	this.getPosition()[i] == victim.getPosition()[i] + 1 || this.getPositon()[i] == victim.getPosition()[i] - 1
	 * 			|
	 */
	private boolean inRangeForAttack(Unit victim){
		for ( int i = 0; i < this.getPosition().length; i++){
			if (this.getPosition()[i] == victim.getPosition()[i] + 1 || this.getPosition()[i] == victim.getPosition()[i] - 1)
				return true;
		}return false;
	}
	
	/**
	 * set all the flags low
	 */
	private void setFlagsLow(){
		this.shouldWork = false;
		this.shouldRest = false;
		this.shouldAttack = false;
	}
	
	
	/**
	 * Makes this Unit point directly at the other Unit.
	 * @param other
	 * 		The other Unit to look at.
	 * @post Unit will be looking at other.
	 * 		| new.getOrientation() == Math.atan2(other.getPosition()[1] - this.getPosition()[1],
	 * 								  other.getPosition()[0] - this.getPosition()[0])
	 * @throw IllegalArgumentException
	 * 		If other is null
	 * 		| other == null
	 */
	private void pointAt(Unit other) throws IllegalArgumentException {
		if(other == null){
			throw new IllegalArgumentException("other shouldn't be null.");
		}
		this.setOrientation(Math.atan2(other.getPosition()[1] - this.getPosition()[1], other.getPosition()[0] - this.getPosition()[0]));
	}
	
	/**
	 * Returns whether the dodge succeeded or not (by chance)
	 * @param attacker
	 * 		The attacking Unit, used for information
	 * @return true or false based on chance.
	 * 		The chance is equal to 0.20 * this.getAgility() / attacker.getAgility()
	 * 		If the chance is >= 1, it always succeeds.
	 * @throws IllegalArgumentException
	 * 		If the attacker is null
	 * 		| attacker == null
	 */
	private boolean dodgeSucceeds(Unit attacker) throws IllegalArgumentException {
		if(attacker == null){
			throw new IllegalArgumentException();
		}
		double chance = 0.20 * this.getAgility() / attacker.getAgility();
		Random random = new Random();
		if(random.nextDouble() > chance){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Moves the Unit to random position in the game world within dodging bounds.
	 */
	private void dodge() throws ModelException{
		Random random = new Random();
		double[] destination;
		do{
			destination = new double[] {
					(random.nextBoolean()?1:-1) * random.nextDouble(),
					(random.nextBoolean()?1:-1) * random.nextDouble(),
					(random.nextBoolean()?1:-1) * random.nextDouble()
					};
		}while((destination[0]==0 && destination[1]==0 && destination[2]==0) || 
				destination[0] > getMaxCoordinate() || destination[0] <= getMinCoordinate() || 
				destination[1] > getMaxCoordinate() || destination[1] <= getMinCoordinate() ||
				destination[2] > getMaxCoordinate() || destination[2] <= getMinCoordinate());
		try {
			this.setPosition(destination);
		} catch (ModelException e) {
			throw e;
		}
	}
	
	/**
	 * Returns whether the block succeeded or not (by chance)
	 * @param attacker
	 * 		The attacking Unit, used for information
	 * @return true or false based on chance.
	 * 		The chance is equal to 0.25 * (this.getAgility() + this.getStrength()) / (attacker.getAgility() + attacker.getStrength())
	 * @throws IllegalArgumentException
	 * 		If the attacker is null
	 * 		| attacker == null
	 */
	private boolean blockSucceeds(Unit attacker) throws IllegalArgumentException {
		if(attacker == null){
			throw new IllegalArgumentException();
		}
		double chance = 0.25 * (this.getAgility() + this.getStrength()) / (attacker.getAgility() + attacker.getStrength());
		Random random = new Random();
		if(random.nextDouble() > chance){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Reduces HP by given number of points. If that would bring the Unit below
	 * the minimum, HP is instead set to the minimum
	 * @param damage
	 * 		The number of HP to reduce by
	 * @post If the HP can be reduced properly, it is
	 * 		| if(isValidHP(this.getHP() - damage))
	 * 		|   then this.setHP(this.getHP() - damage);
	 * 		| else
	 * 		|   this.setHP(getMinHP());
	 */
	private void takeDamage(int damage){
		if(isValidHP(this.getHP() - damage)){
			this.setHP(this.getHP() - damage);
		}else{
			this.setHP(getMinHP());
		}
	}
	
	/**
	 * Gives back the integer coordinates of the cube that the given position is in.
	 * @param position
	 * 		The given position
	 * @return The integer coordinates of the cube that the given position is in.
	 * 		| result == new int[] {(int)position[0], (int)position[1], (int)position[2]}
	 * @throws ModelException
	 * 		If position is not inside any cube
	 * 		| position[0] < getMinCoordinate() || position[0] >= getMaxCoordinate() ||
	 * 		| position[1] < getMinCoordinate() || position[1] >= getMaxCoordinate() ||
	 * 		| position[2] < getMinCoordinate() || position[2] >= getMaxCoordinate()
	 */
	private static int[] cubeCoordinates(double[] position) throws ModelException {
		if(position[0] < getMinCoordinate() || position[0] >= getMaxCoordinate() ||
				position[1] < getMinCoordinate() || position[1] >= getMaxCoordinate() ||
				position[2] < getMinCoordinate() || position[2] >= getMaxCoordinate()){
			throw new ModelException("position out of bounds.");
		}
		return new int[] {(int)position[0], (int)position[1], (int)position[2]};
	}
	
	private static double[] cubeCenter(int[] position) throws ModelException {
		if(position.length != 3){
			throw new ModelException("position dimension should be 3.");
		}
		if(position[0] < getMinCoordinate() || position[0] >= getMaxCoordinate() ||
				position[1] < getMinCoordinate() || position[1] >= getMaxCoordinate() ||
				position[2] < getMinCoordinate() || position[2] >= getMaxCoordinate()){
			throw new ModelException("position out of bounds.");
		}
		return new double[] {((double)position[0])+0.5, ((double)position[1])+0.5, ((double)position[2])+0.5};
	}
	
	/**
	 * Tells whether the given cube coordinate is within the game bounds
	 * @param coordinates
	 * 		The cube you want to check
	 * @return
	 * 		true iff the cube is within the game bounds
	 * 		| result == (coordinates[0] < getMinCoordinate() || coordinates[0] >= getMaxCoordinate() ||
	 * 		| coordinates[1] < getMinCoordinate() || coordinates[1] >= getMaxCoordinate() ||
	 * 		| coordinates[2] < getMinCoordinate() || coordinates[2] >= getMaxCoordinate());
	 */
	private static boolean withinBounds(int[] coordinates) {
		return (coordinates[0] < getMinCoordinate() || coordinates[0] >= getMaxCoordinate() ||
				coordinates[1] < getMinCoordinate() || coordinates[1] >= getMaxCoordinate() ||
				coordinates[2] < getMinCoordinate() || coordinates[2] >= getMaxCoordinate());
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
	 * Execute the behavior when in the NOTHING state.
	 * @param dt
	 * 		The passed time since last update.
	 */
	private void doBehaviorNothing(double dt) {
		if(immediateTarget != null){
			this.setState(State.MOVING);
			this.setFlagsLow();
		}else if(!path.isEmpty()){
			this.immediateTarget = path.remove(0);
			this.setState(State.MOVING);
			this.setFlagsLow();
		}else if(this.shouldRest){
			this.setState(State.RESTING);
			this.setFlagsLow();
		}else if(this.shouldWork){
			this.setState(State.WORKING);
			this.setFlagsLow();
		}else if(this.shouldAttack){
			this.setState(State.ATTACKING);
			this.setFlagsLow();
		}else if(this.getDefaultBehaviorEnabled()){
			Random random = new Random();
			int result = random.nextInt(3);
			if(result == 0){
				try{
					this.moveTo(this.getRandomCoordinate());
				}catch(ModelException e){}
			}else if(result == 1){
				this.shouldWork = true;
			}else{
				this.shouldRest = true;
			}
		}
	}

	/**
	 * Execute the behavior when in the MOVING state.
	 * @param dt
	 * 		The passed time since last update.
	 */
	private void doBehaviorMoving(double dt) {
		if(reachedImmediateTarget())
			if(!path.isEmpty()){
				try{this.setPosition(immediateTarget);}catch(ModelException e){}
				immediateTarget = path.remove(0);
			}else{
				try{this.setPosition(immediateTarget);}catch(ModelException e){}
				this.transitionToNothing();
			}
		}else{
			double velocity = this.determineVelocity();
			double[] position = 
			this.setPosition(this.getPosition());
			
		}
	}
	
	/**
	 * Get a random coordinate within the game field.
	 * @return
	 * 		A random coordinate within the game field.
	 */
	private static int[] getRandomCoordinate() {
		int[] result = new int[3];
		Random random = new Random();
		for(int i=0; i<result.length; i++){
			int num = random.nextInt((int) (getMaxCoordinate() - getMinCoordinate()));
			num += getMinCoordinate();
			result[i] = num;
		}
		return result;
	}
	
	
	
	/* Constants */
	
	private static final double maxCoordinate = 50;
	private static final double minCoordinate = 0;
	private static final int maxWeight = 200;
	private static final int minAgility = 0;
	private static final int maxAgility = 200;
	private static final int minStrength = 0;
	private static final int maxStrength = 200;
	private static final double defaultOrientation = Math.PI / 2;
	private static final double minOrientation = 0;
	private static final double maxOrientation = Math.PI * 2;
	private static final int minHP = 0;
	private static final int minStamina = 0;
	
	
	
	/* Variables */
	
	/**
	 * Holds the previous position of the Unit. Very important to determine whether it has reached its destination
	 */
	private double[] previousPosition;
	
	/**
	 * The place that the Unit is currently going
	 */
	private double[] immediateTarget;
	
	/**
	 * A list of positions that the Unit should walk towards, in correct order
	 */
	private List<double[]> path;
	
	/**
	 * The time it will take before the next whole point of stamina is subtracted from the Unit's stamina gauge
	 */
	private float sprintingStaminaDecreaseCountdown;
	
	/**
	 * The time it will take before the attack is actually carried out
	 */
	private float restingInitialCountdown;
	
	/**
	 * The time it will take before the next whole point of HP is restored by resting.
	 */
	private float restingHPCountdown;
	
	/**
	 * The time it will take before the next whole point of stamina is restored by resting
	 */
	private float restingStaminaCountdown;
	
	/**
	 * The time it will take before the work is done
	 */
	private float workingCountdown;
	
	/**
	 * The time it will take before the attack is actually carried out
	 */
	private float attackingCountdown;
	
	/**
	 * The unit that will be attacked once the attackingCountdown is done
	 */
	private Unit victim;
	
	private boolean shouldRest;
	private boolean shouldWork;
	private boolean shouldAttack;
	private String name;
	private double[] position;
	private int weight;
	private int agility;
	private int strength;
	private int toughness;
	private double orientation;
	private int HP;
	private int stamina;
	
	/**
	 * Variable registering the state of this unit.
	 */
	private State state;
	

	/**
	 * Variable registering the default behavior of this Unit.
	 */
	private boolean defaultBehaviorEnabled;


}
