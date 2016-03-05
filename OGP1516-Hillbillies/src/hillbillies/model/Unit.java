package hillbillies.model;

import java.util.*;

import java.lang.Math;
import java.util.Random;

import ogp.framework.util.*;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.BadFSMStateException;

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
 * @invar  The sprinting of each Unit must be a valid sprinting for any
 *         Unit.
 *       | isValidSprinting(getSprinting())
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
	 * @param initialCoordinates
     *     The coordinates for this new Unit.                                                                                                                       
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
	 * @throws IllegalArgumentException
	 *         This new Unit cannot have the given default behavior as its default behavior.
	 *       | !isValidDefaultBehaviorEnabled(this.getDefaultBehaviorEnabled())
	 * @throws IllegalArgumentException if either name or initialPosition are not valid 
	 * 		| (!isValidName(name) || !isValidPosition(position) || !isValidDefaultBehaviorEnabled(enableDefaultBehavior)
	 */
	@Raw
	public Unit (String name, int[] initialCoordinates, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws IllegalArgumentException{
		
		double[] initialPosition = cubeCenter(initialCoordinates);
		
		if (!isValidName(name) || !isValidPosition(initialPosition) || !isValidDefaultBehaviorEnabled(enableDefaultBehavior)){
			throw new IllegalArgumentException("Name, position, or enableDefaultBehavior are not valid");
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
		
		this.immediateTarget = null;
		this.previousPosition = this.getPosition();
		this.path = new ArrayList<double[]>();
		this.setState(State.NOTHING);
		
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
	 * Advances the time for the Unit by the given time. This includes basically all the Unit's behavior,
	 * except for defending against an attack, which is instantaneous and outside of the finite state
	 * machine model, which is used for the Unit's behavior.
	 * @param dt
	 */
	public void advanceTime(double dt) throws IllegalArgumentException{
		if(dt > getMaxDT()){
			throw new IllegalArgumentException("dt went over its maximum of " + getMaxDT() + ": dt = " + dt);
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
	
	/**
	 * Gives back the coordinates of the cube which the Unit is currently in.
	 * @return
	 * 		The coordinates of the cube which the Unit is currently in.
	 * 		| result == cubeCoordinates(this.getPosition());
	 */
	public int[] getCubeCoordinate() {
		return cubeCoordinates(this.getPosition());
	}
	
	/**
	 * Determines the magnitude of the velocity ("speed" in English I think)
	 * @return
	 * 		The speed the Unit should be going at at this moment
	 */
	public double determineVelocity() {
		double baseSpeed;
		baseSpeed = 1.5 * (this.getStrength() + this.getAgility()) / (2 * this.getWeight());
		double walkingSpeed;
		double realSpeed;
		double dz;
		try{
			dz = this.immediateTarget[2] - this.getPosition()[2];
		}catch(NullPointerException e){
			dz = 0;
		}
		if(dz < 0){
			walkingSpeed = 0.5 * baseSpeed;
		}else if(dz > 0){
			walkingSpeed = 1.2 * baseSpeed;
		}else{
			walkingSpeed = baseSpeed;
		}
		if(this.sprinting){
			realSpeed = 2 * walkingSpeed;
		}else{
			realSpeed = walkingSpeed;
		}
		return realSpeed;
	}
	
	/**
	 * Tells whether the Unit is currently moving.
	 * @return
	 * 		true iff the Unit is currently moving.
	 * 		| result == (this.getState() == State.MOVING)
	 */
	public boolean isMoving() {
		return this.getState() == State.MOVING;
	}
	
	
	
	/* Movement */

	/**
	 * Tells the Unit to move to an adjacent cube.
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
	 * @throws IllegalArgumentException
	 * 		If at least one of the parameters is not -1, 0, or 1
	 * 		| !(dx==-1 || dx==0 || dx==1) || !(dy==-1 || dy==0 || dy==1) || !(dz==-1 || dz==0 || dz==1)
	 * @throws IllegalArgumentException
	 * 		If the calculated destination is out of bounds
	 * 		| destination[0] < getMinCoordinate() || destination[0] >= getMaxCoordinate() ||
	 * 		| destination[1] < getMinCoordinate() || destination[1] >= getMaxCoordinate() ||
	 * 		| destination[2] < getMinCoordinate() || destination[2] >= getMaxCoordinate()
	 * @throws BadFSMStateException
	 * 		If the current FSM state of this Unit is not right.
	 * 		| !(this.getState() == State.NOTHING ||
	 * 		| this.getState() == State.RESTING_HP ||
	 * 		| this.getState() == State.RESTING_STAMINA ||
	 * 		| this.getState() == State.WORKING)
	 */
	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException, BadFSMStateException {
		if (!(this.getState() == State.NOTHING ||
				this.getState() == State.RESTING_HP ||
				this.getState() == State.RESTING_STAMINA ||
				this.getState() == State.WORKING)){
			throw new BadFSMStateException("Cannot do moveToAdjacent from state: " + this.getState());
		}else if(!(dx==-1 || dx==0 || dx==1) || !(dy==-1 || dy==0 || dy==1) || !(dz==-1 || dz==0 || dz==1)){
			throw new IllegalArgumentException("One of the parameters not -1, 0, or 1.");
		}else{
			int[] currentCube = cubeCoordinates(this.getPosition());
			int[] destination = new int[] {currentCube[0]+dx, currentCube[1]+dy, currentCube[2]+dz};
			if(destination[0] < getMinCoordinate() || destination[0] >= getMaxCoordinate() ||
					destination[1] < getMinCoordinate() || destination[1] >= getMaxCoordinate() ||
					destination[2] < getMinCoordinate() || destination[2] >= getMaxCoordinate()){
				throw new IllegalArgumentException("One of the coordinates is out of bounds.");
			}
			immediateTarget = cubeCenter(destination);
		}
	}
	
	/**
	 * Calculates a path for the Unit to follow towards the destination.
	 * @param destination
	 * 		The coordinate of the cube to go to
	 * @throws IllegalArgumentException
	 * 		If the destination is not within the bounds.
	 * 		| !withinBounds(destination)
	 */
	public void moveTo(int[] destination) throws IllegalArgumentException {
		if(!withinBounds(destination)){
			throw new IllegalArgumentException("destination out of bounds.");
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
			position[0] += dx;
			if(position[1] == destination[1]){
				dy = 0;
			}else if(position[1] < destination[1]){
				dy = 1;
			}else{
				dy = -1;
			}
			position[1] += dy;
			if(position[2] == destination[2]){
				dz = 0;
			}else if(position[2] < destination[2]){
				dz = 1;
			}else{
				dz = -1;
			}
			position[2] += dz;
			path.add(cubeCenter(position));
		}
	}
	
	
	
	/* Sprinting */

	/**
	 * Tells the Unit to start sprinting, if possible
	 * @post
	 * 		When the unit can start sprinting, it will.
	 * 		| if this.canStartSprinting() then
	 * 		| 	new.getSprinting()
	 */
	public void startSprinting() {
		if(this.canStartSprinting()){
			this.setSprinting(true);
		}
	}
	
	/**
	 * Tells the Unit to stop sprinting, if possible
	 * @post
	 * 		When the unit can stop sprinting, it will.
	 * 		| if this.canStopSprinting() then
	 * 		| 	!new.getSprinting()
	 */
	public void stopSprinting() {
		if(this.canStopSprinting()){
			this.setSprinting(false);
		}
	}
	
	/**
	 * Tells whether the Unit can start sprinting.
	 * @return
	 * 		true iff the Unit can start sprinting.
	 * 		| result == (this.getState() == State.NOTHING && this.getStamina() != this.getMinStamina());
	 */
	private boolean canStartSprinting() {
		return (this.getState() == State.NOTHING && this.getStamina() != this.getMinStamina());
	}
	
	/**
	 * Tells whether the Unit can stop sprinting.
	 * @return
	 * 		true iff the Unit can stop sprinting.
	 * 		| result == true;
	 */
	private boolean canStopSprinting() {
		return true;
	}

	/**
	 * Return the sprinting of this Unit.
	 */
	@Basic @Raw
	public boolean getSprinting() {
		return this.sprinting;
	}

	/**
	 * Check whether the given sprinting is a valid sprinting for
	 * any Unit.
	 *  
	 * @param  sprinting
	 *         The sprinting to check.
	 * @return 
	 *       | result == true;
	 */
	public static boolean isValidSprinting(boolean sprinting) {
		return true;
	}

	/**
	 * Set the sprinting of this Unit to the given sprinting.
	 * 
	 * @param  sprinting
	 *         The new sprinting for this Unit.
	 * @post   If the given sprinting is a valid sprinting for any Unit,
	 *         the sprinting of this new Unit is equal to the given
	 *         sprinting.
	 *       | if (isValidSprinting(sprinting))
	 *       |   then new.getSprinting() == sprinting
	 */
	@Raw
	public void setSprinting(boolean sprinting) {
		if (isValidSprinting(sprinting))
			this.sprinting = sprinting;
	}


	
	/* Combat */
	
	/**
	 * Set the shouldAttack flag high and set this.victim to the given victim
	 * @param victim 
	 * @Post the shouldAttack flag is set high and this.victim is set to the given victim
	 * 		|this.shouldAttack && this.victim == victim
	 * @throws BadFSMStateException if state is not NOTHING, RESTING_HP, RESTING_STAMINA or WORKING
	 * 		| !(this.getState() == NOTHING || this.getState() == RESTING_HP || this.getState() == RESTING_STAMINA || this.getState() == WORKING)
	 * @throws IllegalArgumentException
	 * 		If the given victim is null
	 * 		| victim == null
	 */
	public void attack(Unit victim) throws BadFSMStateException, IllegalArgumentException {
		if (!(this.getState() == state.NOTHING || this.getState() == state.RESTING_HP || this.getState() == state.RESTING_STAMINA || this.getState() == state.WORKING)){
			throw new BadFSMStateException("Can not attack in this state");
		}else if (victim == null){
			throw new IllegalArgumentException("Cannot attack null.");
		}else{
			this.pointAt(victim);
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
	public void defend(Unit attacker) throws IllegalArgumentException {
		if(attacker == null){
			throw new IllegalArgumentException("Attacker shouldn't be null.");
		}
		
		pointAt(attacker);
		
		if(dodgeSucceeds(attacker)){
			dodge();
		}else if(!blockSucceeds(attacker)){
			takeDamage(attacker.getStrength() / 10);
		}
	}
	
	/**
	 * Tells whether the Unit is currently attacking.
	 * @return
	 * 		true iff the Unit is currently attacking.
	 * 		| result == (this.getState() == State.ATTACKING);
	 */
	public boolean isAttacking() {
		return this.getState() == State.ATTACKING;
	}
	
	
	/* Resting */
	
	/**
	 * Set shouldRest flag to high
	 * @Post The shouldRest flag is set to high
	 * 		| new.shouldRest
	 * @throws BadFSMStateException If the state is not NOTHING or WORKING
	 * 		| !(state == state.NOTHING || state == state.WORKING)
	 */
	public void rest() throws BadFSMStateException{
		if (!(this.getState() == state.NOTHING || this.getState() == state.WORKING)){
			throw new BadFSMStateException("Can not go to resting from this state");
		}else{
			this.shouldRest = true;
		}
	}
	
	/**
	 * Tells whether the Unit is resting.
	 * @return
	 * 		true iff the Unit is resting.
	 * 		| result == (
	 * 		| 	this.getState() == State.RESTING_INIT || 
	 * 		| 	this.getState() == State.RESTING_HP || 
	 * 		| 	this.getState() == State.RESTING_STAMINA
	 * 		| );
	 */
	public boolean isResting() {
		return (this.getState() == State.RESTING_INIT || this.getState() == State.RESTING_HP || this.getState() == State.RESTING_STAMINA);
	}
	
	
	
	/* Working */
	
	/**
	 * Set the shouldWork flag  to high
	 * @Post ShouldWork is set to true
	 * 		|this.shouldWork
	 * @throws BadFSMException if the state of the unit is not NOTHING, RESTING_HP or RESTING_STAMINA 
	 * 		| !(this.getState() == NOTHING || this.getState() == RESTING_HP || this.getState() == RESTING_STAMINA)
	 */
	public void work() throws BadFSMStateException{
		if (!(this.getState() == state.NOTHING || this.getState() == state.RESTING_HP || this.getState() == state.RESTING_STAMINA))
			throw new BadFSMStateException("Can not go to working from this state");
		else{
			this.shouldWork = true;
		}
	}
	
	/**
	 * Tells whether the Unit is currently working.
	 * @return
	 * 		true iff the Unit is currently working.
	 * 		| result == (this.getState() == State.WORKING);
	 */
	public boolean isWorking() {
		return this.getState() == State.WORKING;
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
	 * @throws IllegalArgumentException                                                                                                                                                        
	 *         The given name is not a valid name for any                                                                                                      
	 *         Unit.                                                                                                                                                          
	 *       | !isValidName(getName())                                                                                                                        
	 */
	@Raw
	public void setName(String name) throws IllegalArgumentException {
		if (!isValidName(name))
			throw new IllegalArgumentException(name + " is not a valid name.");
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
		return (name != null) && (Character.isUpperCase(name.charAt(0)) && (name.length() >= 2));
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
	 * @throws IllegalArgumentException                                                                                                                                                        
	 *         The given position is not a valid position for any                                                                                                      
	 *         Unit.                                                                                                                                                          
	 *       | ! isValidPosition(position)                                                                                                                        
	 */
	public void setPosition(double[] position)
			throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException(position + " is not a valid position.");
		this.previousPosition = this.getPosition();
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
	 *         Weight. Else, it is set to the maximum.
	 *       | if (canHaveAsWeight(weight))                                                                                                                                                
	 *       | 	then new.getWeight() == weight 
	 *       | else 
	 *       | 	new.getWeight() == getMaxWeight()                                                                                                                                         
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
	public void setStrength(int strength){
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
	public static boolean isValidStrength(int strength ){
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
	 * Tells whether the given orientation is valid
	 * @param orientation
	 * 		The orientation to check
	 * @return
	 * 		true iff the given orientation is valid
	 * 		| result == (orientation > getMinOrientation() && orientation < getMaxOrientation());
	 */
	public static boolean isValidOrientation(double orientation) {
		return orientation > getMinOrientation() && orientation < getMaxOrientation();
	}
	
	/**
	 * Set the orientation of the unit to a given orientation
	 * 
	 * @param orientation
	 * @post
	 * 		If the given orientation is valid, the orientation is set to it.
	 * 		| If isValidOrientation(orientation) then
	 * 		| 	new.getOrientation() == orientation;
	 * @post
	 * 		If the given orientation is not valid, the orientation is set to an angle that points the same
	 * 		way, but lies within the valid range.
	 * 		| If !isValidOrientation(orientation) then
	 * 		| 	new.getOrientation() == orientation<0?
	 * 		| 		getMaxOrientation()-(orientation%getMaxOrientation())
	 * 		| 		:orientation%getMaxOrientation()
	 */
	public void setOrientation(double orientation){
		if(isValidOrientation(orientation)){
			this.orientation = orientation;
		}else{
			if(orientation < 0){
				this.orientation = getMaxOrientation() - (orientation % getMaxOrientation());
			}else{
				this.orientation = orientation % getMaxOrientation();
			}
		}
		
	}
	
	
	/**
	 * Gives the maximum possible orientation 
	 */
	@Basic @Immutable
	public static double getMaxOrientation(){
		return maxOrientation;
	}
	
	 
	/**
	 * Gives the minimum possible orientation 
	 */
	@Basic @Immutable
	public static double getMinOrientation(){
		return minOrientation;
	}
	
	
	
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
	 * @Pre    The given HP must be a valid HP for any                                                                                                                                   
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
	public int getMaxHP() {
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
	public int getMaxStamina() {
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
	 * @throws IllegalArgumentException
	 * 		If the given parameter is not valid
	 * 		| !isValidDefaultBehaviorEnabled(defaultBehaviorEnabled)
	 */
	public void setDefaultBehaviorEnabled(boolean defaultBehaviorEnabled) throws IllegalArgumentException {
		if(!isValidDefaultBehaviorEnabled(defaultBehaviorEnabled)){
			throw new IllegalArgumentException("Invalid defaultBehaviorEnabled given: " + defaultBehaviorEnabled);
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
	 * Give the time between two decreases in stamina points due to sprinting.
	 * @return
	 * 		The time between two decreases in stamina points due to sprinting.
	 * 		| result == 0.1d;
	 */
	@Basic @Immutable
	private double getSprintingStaminaDecreaseTime() {
		return 0.1d;
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
	 * Gives the time it takes for a unit to go through the initial resting phase
	 * @return
	 * 		The time it takes for a unit to go through the initial resting phase
	 * 		| result == this.getRestingHPTime();
	 */
	@Basic
	private float getRestingInitTime(){
		return getRestingHPTime();
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
	 * Gives the time it takes for a unit to restore one HP
	 * @return
	 * 		The time it takes for a unit to restore one HP
	 * 		| result == 200/this.getStrength();
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
	@Basic  @Immutable
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
	private double getWorkingTime(){
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
	private double getAttackingTime() {
		return 1d;
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
	private static boolean between(double x, double y, double z){
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
		this.sprinting = false;
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
	private void dodge(){
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
		} catch (IllegalArgumentException e) {
			
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
			this.setState(State.RESTING_INIT);
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
				}catch(IllegalArgumentException e){}
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
		if(reachedImmediateTarget()){
			if(!path.isEmpty()){
				try{this.setPosition(immediateTarget);}catch(IllegalArgumentException e){}
				immediateTarget = path.remove(0);
			}else{
				try{this.setPosition(immediateTarget);}catch(IllegalArgumentException e){}
				immediateTarget = null;
				this.transitionToNothing();
			}
		}else{
			double velocity = this.determineVelocity();
			double[] position = this.getPosition();
			double[] deltaPosition = new double[] {
					immediateTarget[0] - position[0],
					immediateTarget[1] - position[1],
					immediateTarget[2] - position[2]
							};
			deltaPosition = new double[] {
					normalize(deltaPosition)[0] * velocity * dt,
					normalize(deltaPosition)[1] * velocity * dt,
					normalize(deltaPosition)[2] * velocity * dt
					};
			try{
				this.setPosition(new double[] {
						position[0] + deltaPosition[0],
						position[1] + deltaPosition[1],
						position[2] + deltaPosition[2]
				});
			}catch(IllegalArgumentException e){}
			
			if(this.sprinting){
				if(this.sprintingStaminaDecreaseCountdown <= 0){
					this.doSprintingStaminaDecrease(this.sprintingStaminaDecreaseCountdown);
				}else{
					this.sprintingStaminaDecreaseCountdown -= dt;
					if(this.getStamina() == this.getMinStamina()){
						this.sprinting = false;
					}
				}
			}
			
		}
	}
	
	/**
	 * Execute the behavior when in the RESTING_INIT state.
	 * @param dt
	 * 		The passed time 
	 */
	private void doBehaviorRestingInit(double dt) {
		if (this.restingInitialCountdown > 0){
			this.restingInitialCountdown -= dt;
		}else{
			this.transitionToRestingHP();
		}
		
	}
	
	/**
	 * Execute the behavior when in the RESTING_HP state.
	 * @param dt
	 * 		The passed time 
	 */
	private void doBehaviorRestingHP(double dt) {
		if (this.shouldWork){
			this.transitionToWorking( );
		}else if (this.shouldAttack){
			this.transitionToAttacking();
		}else if (this.getHP() == getMaxHP()){
			this.transitionToRestingStamina();
		}else {
			if(this.restingHPCountdown <= 0){
				this.doRestingHP(this.restingHPCountdown);
			}else{
				this.restingHPCountdown -= dt;
			}
		}
		
	}
	
	/**
	 * Execute the behavior when in the RESTING_STAMINA state.
	 * @param dt
	 * 		The passed time 
	 */
	private void doBehaviorRestingStamina(double dt) {
		if (this.shouldWork){
			this.transitionToWorking();
		}else if (this.shouldAttack){
			this.transitionToAttacking();
		}else if ( this.getHP() != this.getMaxHP()) {
			this.transitionToRestingHP();
		}else if (this.getStamina() == this.getMaxStamina()){
			this.doBehaviorNothing(dt);
		}else{
			if(this.restingStaminaCountdown <= 0){
				this.doRestingStamina(this.restingStaminaCountdown);
			}else{
				this.restingStaminaCountdown -= dt;
			}
		}
	}

	/**
	 * Execute the behavior when in the WORKING state.
	 * @param dt
	 * 		The passed time 
	 */
	private void doBehaviorWorking(double dt) {
		if (this.shouldRest){
			this.transitionToRestingInit();
		}else if (this.shouldAttack){
			this.transitionToAttacking();
		}else if (this.workingCountdown <= 0){
			this.transitionToNothing();
		}else{
			this.workingCountdown -= dt;
		}
	}
	
	/**
	 * Execute the behavior when in the ATTACKING state.
	 * @param dt
	 * 		The passed time 
	 */
	private void doBehaviorAttacking(double dt) {
		if (this.attackingCountdown > 0){
			this.attackingCountdown -= dt;
		}else if (this.inRangeForAttack(victim)){
			try{
				this.victim.defend(this);
			}catch (IllegalArgumentException e){
			}
		}else{
			this.transitionToNothing();
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
	
	/**
	 * Decrease the stamina of this Unit by the amount it should per time it decreases while sprinting.
	 * Also resets the sprintingStaminaDecreaseCountdown to the maximum, minus the given negative overshoot.
	 * @param overshoot
	 * 		The time in seconds that the countdown has gone below 0.
	 */
	private void doSprintingStaminaDecrease(double overshoot) {
		int newStamina = this.getStamina();
		while(overshoot <= 0){
			newStamina -= 1;
			overshoot += this.getSprintingStaminaDecreaseTime();
		}
		if(this.isValidStamina(newStamina)){
			this.setStamina(newStamina);
		}else{
			this.setStamina(this.getMinStamina());
		}
		this.sprintingStaminaDecreaseCountdown = overshoot;
	}
	
	/**
	 * Increase the HP of this Unit by the amount it should per time it increases while resting.
	 * Also resets the restingHPCountdown to the maximum, minus the given negative overshoot.
	 * @param overshoot
	 * 		The time in seconds that the countdown has gone below 0.
	 */
	private void doRestingHP(double overshoot) {
		int newHP = this.getHP();
		while(overshoot <= 0){
			newHP += 1;
			overshoot += this.getRestingHPTime();
		}
		if(this.isValidHP(newHP)){
			this.setHP(newHP);
		}else{
			this.setHP(this.getMaxHP());
		}
		this.restingHPCountdown = overshoot;
	}
	
	/**
	 * Increase the stamina of this Unit by the amount it should per time it increases while resting.
	 * Also resets the restingStaminaCountdown to the maximum, minus the given negative overshoot.
	 * @param overshoot
	 * 		The time in seconds that the countdown has gone below 0.
	 */
	private void doRestingStamina(double overshoot) {
		int newStamina = this.getStamina();
		while(overshoot <= 0){
			newStamina += 1;
			overshoot += this.getRestingStaminaTime();
		}
		if(this.isValidStamina(newStamina)){
			this.setStamina(newStamina);
		}else{
			this.setStamina(this.getMaxStamina());
		}
		this.restingStaminaCountdown = overshoot;
	}
	
	/**
	 * Gives the maximal time between two calls on advanceTime
	 * @return
	 * 		The maximal time between two calls on advanceTime
	 * 		| result == 0.2d;
	 */
	@Basic
	private static double getMaxDT() {
		return maxDT;
	}
	
	/**
	 * Gives the given vector, but normalized to have magnitude 1.
	 * @param vector
	 * 		The vector to be normalized
	 * @return
	 * 		The normalized version of the vector
	 */
	private static double[] normalize(double[] vector) {
		int length = vector.length;
		
		double magnitude = 0;
		for(int i=0; i<length; i++){
			magnitude += Math.pow(vector[i], 2);
		}
		magnitude = Math.sqrt(magnitude);
		
		double[] result = new double[length];
		for(int i=0; i<length; i++){
			result[i] = vector[i] / magnitude;
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
	private static final double maxDT = 0.2d;
	
	
	
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
	private double sprintingStaminaDecreaseCountdown;
	
	/**
	 * The time it will take before the attack is actually carried out
	 */
	private double restingInitialCountdown;
	
	/**
	 * The time it will take before the next whole point of HP is restored by resting.
	 */
	private double restingHPCountdown;
	
	/**
	 * The time it will take before the next whole point of stamina is restored by resting
	 */
	private double restingStaminaCountdown;
	
	/**
	 * The time it will take before the work is done
	 */
	private double workingCountdown;
	
	/**
	 * The time it will take before the attack is actually carried out
	 */
	private double attackingCountdown;
	
	/**
	 * The unit that will be attacked once the attackingCountdown is done
	 */
	private Unit victim;
	
	private boolean shouldRest;
	private boolean shouldWork;
	private boolean shouldAttack;
	private boolean sprinting;
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
