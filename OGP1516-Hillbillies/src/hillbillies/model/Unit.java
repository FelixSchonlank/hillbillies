package hillbillies.model;
import java.util.*;

import java.lang.Math;

import ogp.framework.util.*;

import be.kuleuven.cs.som.annotate.Basic;
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
 *
 */
public class Unit {
	
	/* Constructor*/
	/**
	 * Create a new unit with a given name, Position, weight, agility, strength, toughness and behavior
	 * 
	 * @param  name                                                                                                                                                         
     *      The name for this new Unit.                                                                                                                       
  	 * @effect The name of this new unit is set to                                                                                                               
     *      the given name.                                                                                                                                              
     *    | this.setName(name) 
	 * @param initialPosition
     *     The position for this new Unit.                                                                                                                       
  	 * @effect The Position of this new Unit is set to                                                                                                               
     *     the given initialPosition.                                                                                                                                              
     *   | this.setPosition(initialPosition) 
	 * @param  weight                                                                                                                                                                    
	 *         The Weight for this new unit.                                                                                                                                             
	 * @post   If the given Weight is a valid initial Weight for any unit,                                                                                                                       
	 *         the Weight of this new unit is equal to the given                                                                                                                         
	 *         Weight. Otherwise, the Weight of this new unit is equal                                                                                                                   
	 *         to getMaxWeight().                                                                                                                                                        
	 *       | if (!notValidInitalWeight(weight))                                                                                                                                                
	 *       |   then new.getWeight() == weight                                                                                                                                          
	 *       |   else new.getWeight() == getMaxWeight()
	 * @param  agility
	 *         The agility for this new unit.
	 * @post   If the given agility is a valid initial agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility. Otherwise, the agility of this new unit is equal
	 *         to getMaxAgility().
	 *       | if (!notValidInitialAgility(agility))
	 *       |   then new.getAgility() == agility
	 *       |   else new.getAgility() == getMaxAgility()
	 * @param  strength
	 *         The Strength for this new Unit.
	 * @post   If the given Strength is a valid initial Strength for any Unit,
	 *         the Strength of this new Unit is equal to the given
	 *         Strength. Otherwise, the Strength of this new Unit is equal
	 *         to getMaxStength().
	 *       | if (!notValidInitialSrength(strength))
	 *       |   then new.getSrength() == strength
	 *       |   else new.getSrength() == getMaxStength()
	 * @param toughness
	 * @param enableDefaultBehavior
	 * @return
	 * @throws ModelException if either name or initialPosition are not valid 
	 * 		|if (!isValidName(name) || !isValidPosition(position)
     *		|		throw ModelException
     *
	 */
	@Raw
	public Unit (String name, double[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException{
			if (!isValidName(name) || !isValidPosition(initialPosition)){
				throw new ModelException("Name or position are not valid");
			}
			setName( name );
			setPosition( initialPosition );
			if (notValidInitialAgility(agility)){
				setAgility( getMaxAgility() );
			}
			else{
				setAgility( agility );
			}
			if (notValidInitialStrength( strength )){
				setStrength( getMaxStrength() );
			}
			else{
				setStrength( strength );
			}
			if (notValidInitialToughness( toughness )){
				setToughness( getMaxToughness() );
			}
			else{
				setToughness( toughness );
			}
			if (notValidInitialWeight( weight )){
				setWeight( getMaxWeight() ); 		/* Weight must be set after strength and agility */ 
			}
			else{
				setWeight( weight );
			}
			
			/*Should we initialize the HP and stamina and if yes with what? */
				
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
	public boolean notValidInitialStrength(int strength ){
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
	@Basic /*@Raw*/
	public String getName(){
		return this.name;
	}
	
	
	
	/**
	 * Set the name of this unit to a given name
                                                                                                 
	 *                                                                                                                                                                                   
	 * @param  name                                                                                                                                                         
	 *         The new name for this unit.                                                                                                                       
	 * @post   The name of this unit is equal to                                                                                                             
	 *         the given name.                                                                                                                                              
	 *       | new.getName() == name                                                                                                                           
	 * @throws ModelException                                                                                                                                                        
	 *         The given name is not a valid name for any                                                                                                      
	 *         Unit.                                                                                                                                                          
	 *       | ! isValidName(getName())                                                                                                                        
	 */
	public void setName(String name) throws ModelException {
		if (!isValidName(name))
			throw new ModelException();
		this.name = name;
	}
	
	/**
	 *  Check whether the given name is a valid name for                                                                                                        
	 * any Unit.
	 *   
	 * @param name
	 * 			The name to check
	 * @return True if and only if the name is longer then 2 and the first character is upper case
	 * 			| if (!(name == null) && isUpperCase(name.charAt(0) && name.lenth() >= 2)
	 * 			| 		result == true 
	 */
	public boolean isValidName(String name) {
		return (!(name == null) && (Character.isUpperCase(name.charAt(0)) && (name.length() >= 2)));
	}
	

	
	/* Position */


	/**
	 * return the position of the center of the unit
	 */
	@Basic /*@Raw*/
	public double[] getPosition(){     
	    return this.Position;          
	}
	
	/**                                                                                                                                                                                  
	 * Set the Position of this  unit to the given Position.                                                                                                 
	 *                                                                                                                                                                                   
	 * @param  Position
	 *         The new Position for this Unit.                                                                                                                       
	 * @post   The Position of this new Unit is equal to                                                                                                             
	 *         the given Position.                                                                                                                                              
	 *       | new.getPosition() == position                                                                                                                           
	 * @throws ModelException                                                                                                                                                        
	 *         The given Position is not a valid Position for any                                                                                                      
	 *         Unit.                                                                                                                                                          
	 *       | ! isValidPosition(getPropertyName_Java())                                                                                                                        
	 */
	@Raw
	    public void setPosition(double[] position)
	    throws ModelException {
	    if (! isValidPosition(position))
	        throw new ModelException();
	    this.Position = position;
	}
	

	/**
	 *Checks whether the given position is a valid position for any Unit.
	 *
	 *@param: Position 
	 *		The position to check
	 *@return True if and only if 
	 *             Position contains exactly 3 elements
	 *             all tree coordinates are positive and smaller or equal to 50
	 *             |Position.lenth == 3
	 *             |for each i in Position.lenth:
	 *             |    if (! (Position[i] >=0 and Position <= 50))
	 *             |		result == false
	 *             | result == result && Position.lenth == 3
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
	 * @return the maximum coordinate of a unit is 50
	 * 		| result == 50
	 */
	@Basic
	public int getMaxCoordinate(){
		return 50;
	}
	
	/**
	 * Return the minimum coordinate in every direction of the board
	 * @return The minimum coordinate of a unit is 0
	 * 		| result == 0
	 */
	@Basic
	public int getMinCoordinate(){
		return 0;
	}
	
	
	/* Weight */
	
	/**
	 * Get the weight of this unit
	 */
	@Basic /*@Raw*/
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
	 *       | if (isValidWeight(weight))                                                                                                                                                
	 *       |   then new.getWeight() == weight 
	 *       |else 
	 *       |		new.getWeight() == getMaxWeight()                                                                                                                                         
	 */
	/*@Raw*/
	    public void setWeight(int weight) {
	    if (isValidWeight(weight))
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
	private boolean isValidWeight(int weight) {
		return weight <= getMaxWeight() && weight >= getMinWeight()  ;
	}
	
	/**
	 * The maximum weight this unit can have
	 * @return The maximum Weight of a unit is 200
	 * 		| result == 200
	 */
	@Basic
	private int getMaxWeight() {
		return 200;
	}

	/**
	 * The minimum weight a unit can have 
	 * @return the minimum weight of this unit is 0
	 * 		|result ==  ((this.getStrength() + this.getAgility()) / 2)
	 */
	@Basic
	private int getMinWeight(){
		return ((this.getStrength() + this.getAgility()) / 2);
	}

	/* Agility */
	/** 
	 * Get the agility of the given unit
	 */
	@Basic /*@Raw*/
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
	 * 		|else
	 * 		|	new.getAgility() == getMaxAgility()
	 */
	public void setAgility(int agility ){
		if (isValidAgility(agility))
			this.agility = agility;
		else{
			this.agility = this.getMaxAgility();
		}
	}
	
	/**
	 * 
	 * @param agility
	 * 			The agility to check
	 * @return true if and only if the given agility is larger then the minimum agility for a unit and is smaller then the maximum agility 
	 * 		| result == agility >= getMinAgility() && agility <= getMaxAgility() 
	 */
	public boolean isValidAgility(int agility ){
		return agility >= getMinAgility() && agility <= getMaxAgility();
	}
	
	/**
	 * The minimum agility for a unit
	 * @return The minimum Agility  
	 */
	@Basic
	public int getMinAgility(){
		return 0;
	}
	
	/**
	 * @return the maximum agility a unit can have 
	 */
	@Basic
	public int getMaxAgility(){
		return 200;
	}
	
	/* toughness */
	
	/**
	 * Return the toughness of this unit 
	 */
	@Basic /*@Raw*/
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
	 * 		|	this.getToughness == getMaxToughness() 
	 */
	public void setToughness(int toughness ){
		if (isValidToughness(toughness))
			this.toughness = toughness;
		else{
			this.toughness = toughness;
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
	 */
	@Basic
	public int getMinToughness(){
		return 0;
	}
	
	/**
	 * The maximum toughness a unit can have
	 * @return The maximum toughness is 200
	 */
	@Basic
	public int getMaxToughness(){
		return 200;
	}
	
	/* Strength */
	
	
	/**                                                                                                                                                                                  
	 * Return the Strength of this Unit.                                                                                                                                                 
	 */
	@Basic /*@Raw*/
	public int getStrength(){
		return this.strength;
	}
	
	/**
	 * Set the strength of this unit to a given strength 
	 * 
	 * @param strength
	 * 		| The new strength for this unit 
	 * @post if the given strength is a valid strength for this unit the strength of the unit is set to the given strength 
	 * 		| if (isValidSStrength(strength)) 
	 * 		|	new.getStrength == strength
	 * @Post if the given strength is not a valid strength the strength is set to getMaxStength()
	 * 		|else	
	 * 		|	this.getStrength() == getMaxStength() 
	 */
	private void setStrength(int strength){
		if (isValidStrength(strength))
				this.strength = strength;
		else{
			this.strength = this.getMaxStrength();
		}
	}
	
	/**
	 * 
	 * @param strength
	 * 		The strength you want to check
	 * @return true if and only if the given strength is between the maximum and the minimum strength of a unit 
	 * 			| result == (strength >= getMinStrength() && strength <= getMaxStrength())
	 */
	private boolean isValidStrength(int strength ){
		return strength >= getMinStrength() && strength <= getMaxStrength();
	}
	
	/**
	 * @return The minimum strength of a unit 
	 */
	@Basic
	public int getMinStrength(){
		return 0;
	}
	/**
	 * 
	 * @return The maximum strength a unit an have 
	 */
	@Basic
	public int getMaxStrength(){
		return 200;
	}
	
	/*Orientation*/
	
	/**
	 * @return The Orientation of a Unit in radian 
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;
	}
	
	@Basic
	public double getDefaultOrientation(){
		return Math.PI/2;
	}
	
	/**
	 * Set the orientation of the unit to a given orientation
	 * 
	 * @param Orientation
	 * @Post if the orientation is larger then getMaxOrientation() the given orientation is decremented by getMaxOrientation() until it is smaller then getMaxOrientation()
	 * 		|if (this.getOrientation() >= getMaxOrientation())
	 * 		|	new.getOrientation() == orientation % getMaxOrientation()
	 * @Post If the orientation smaller then getMinOrientation()
	 * 		|if (this.getOrientation() >= getMinOrientation())
	 * 		|	new.getOrientation() == getMaxOrientation() - (Orientation % getMaxOrientation())
	 * @Post If the orientation is non of the previous the orientation is set to the given orientation   
	 * 		|else 
	 * 		|	new.getOrientation() == orientation 
	 */
	public void setOrientation(double Orientation){
		Orientation %=  Orientation;
		if (Orientation < 0 ){
			this.setOrientation(this.getMaxOrientation() - Orientation);
		}
		else{
			this.setOrientation(Orientation);
		}
	}
	
	
	/**
	 * @return The maximum possible orientation 
	 */
	@Basic
	public double getMaxOrientation(){
		return 2 * Math.PI;
	}
	 
	/**
	 * @return the minimum possible orientation 
	 */
	@Basic
	public double getMinOrientation(){
		return 0.0;
	}
	
	/* HP */
	
	/**
	 * @return The HP of this unit 
	 */
	@Basic /*@Raw*/
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
		assert isValidHP( HP );
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
		return 200 * weight/100 * toughness/100;
	}
	
	/**
	 * The minimum HP a unit can have
	 * @return The lowest HP a unit can have is 0
	 * 		|result == 0
	 * 		 
	 */
	private int getMinHP() {
		return 0;
	}
	
	/* Stamina */
	
	/**
	 * @return The Stamina of this unit 
	 */
	@Basic /*@Raw*/
	public int getStamina(){
		return this.stamina;
	}
	
	/**
	 * Set the Stamina of this unit to a given Stamina
	 * @Pre  The given Stamina must be a valid Stamina for any unit.     
	 *         |isValidStamina(Stamina)
	 * @param Stamina
	 * 			The Stamina you would like to give to this unit
	 * @Post The HP is set the given Stamina
	 * 		|new.getStamina() == Stamina 
	 */
	public void setStamina(int Stamina){
		assert isValidStamina( Stamina );
		this.stamina = Stamina;
	}
	
	/**
	 * Check whether a given Stamina is valid 
	 * @param Stamina
	 * 			The stamina to check
	 * @return True is and only if Stamina is between getMinStamina() and getMaxStamina()
	 * 		| result == Stamina >= getMinStamina() && Stamina <= getMaxStamina()
	 */
	public boolean isValidStamina(int Stamina){
		return (Stamina >= getMinStamina() && Stamina <= getMaxStamina());
	}
	
	
	/**
	 * the minimum Stamina a unit can have
	 * @return The maximum Stamina a unit can have is 200 * weight/100 * toughness/100
	 * 		| result == 200 * weight/100 * toughness/100
	 */
	private int getMaxStamina() {
		return 200 * weight/100 * toughness/100;
	}
	
	/**
	 * The minimum Stamina a unit can have
	 * @return The lowest Stamina a unit can have is 0
	 * 		|result == 0
	 * 		 
	 */
	private int getMinStamina() {
		return 0;
	}
	


	/**
	 * Return the state of this unit.
	 */
	@Basic @Raw
	public State getState() {
		return this.state;
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
	public static boolean isValidState(State state) {
		return state != null;
		
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

/* Helper methods */
	
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
	 * Set the state of this unit to RESTING
	 */
	private void transitionToRestingSamina(){
		this.state = state.RESTING_STAMINA;
		this.setFlagsLow();
		this.restingStaminaCountdown = getRestingStaminaTime();
	}
	
	/**
	 * The time needed to get one stamina point
	 * @return
	 */
	private float getRestingStaminaTime() {
		return toughness/100;
	}
	/**
	 * Set the state of this unit to Attacking
	 */
	private void transitionToAttacking(){
		this.state = state.ATTACKING;
		this.setFlagsLow();
		this.attackingCountdown = getAttacktime();
	}
	
	
	/**
	 * The time it takes for a unit to attack
	 * @return
	 */
	private float getAttacktime() {
		return 1;
	}
	
	/**
	 * Set the state of this unit to a given state  
	 */
	private void transitionToRestingHP(){
		this.state = state.RESTING_HP;
		this.restingHPCountdown = 200/this.getMaxStrength();
		this.setFlagsLow();
	}
	
	/**
	 * Set the state of this unit to Attacking
	 */
	private void transitionToWorking(){
		this.state = state.WORKING;
		this.workingCountdown = getWorkTime();
		this.setFlagsLow();
	}
	
	/**
	 * The worktime for this unit
	 */
	public float getWorkTime(){
		return 500/this.getStrength();
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
	 * Variable registering the state of this unit.
	 */
	private boolean shouldRest;
	private boolean shouldWork;
	private boolean shouldAttack;
	private String name;
	private double[] Position;
	private int weight;
	private int agility;
	private int strength;
	private int toughness;
	private boolean enableDefaultBehavior;
	private double orientation;
	private int HP;
	private int stamina;
	private State state;

}
