package hillbillies.model;

import java.util.*;

import java.lang.Math;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.BadFSMStateException;
import hillbillies.model.World.TerrainType;

/**
 * 
 * @author Willem Seynaeve and Felix Schönlank
 * 
 * @Invar  The position of each Unit must be a valid position for any
 *         Unit.
 *       | isValidPosition(getPosition())
 *
 * @Invar  The Name of each Unit must be a valid Name for any
 *         unit.
 *       | isValidName(getName())
 * 
 * @Invar  The Weight of each unit must be a valid Weight for any
 *         unit.
 *       | isValidWeight(getWeight())       
 *
 * @Invar  The Strength of each Unit must be a valid Strength for any
 *         Unit.
 *       | isValidSrength(getSrength())
 *
 * @Invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 *       
 * @Invar  The toughness of each unit must be a valid toughness for any unit.
 *       | isValidToughness(getToughness())
 * 
 * @Invar  The HP of each unit must be a valid HP for any
 *         unit.
 *       | isValidHP(getHP())
 *
 * @Invar  The Stamina of each unit must be a valid Stamina for any
 *         unit.
 *       | isValidStamina(getStamina())
 * 
 * @Invar  The state of each unit must be a valid state for any
 *         unit.
 *       | isValidState(getState())
 *
 * @Invar  Each Unit can have its default behavior as default behavior.
 *       | isValidDefaultBehaviorEnabled(this.getDefaultBehaviorEnabled())
 *
 * @Invar  The sprinting of each Unit must be a valid sprinting for any
 *         Unit.
 *       | isValidSprinting(getSprinting())
 *
 * @Invar  The defaultBehaviorRestingCountdown of each Unit must be a valid defaultBehaviorRestingCountdown for any
 *         Unit.
 *       | isValidDefaultBehaviorRestingCountdown(getDefaultBehaviorRestingCountdown())
 *      
 * @Invar  The immediateTarget of each Unit must be a valid immediateTarget for any
 *         Unit.
 *       | isValidImmidiateTarget(getImmidiateTarget())
 *       
 * @Invar  The XP of each Unit must be a valid XP for any
 *         Unit.
 *       | isValidXP(getXP())
 *       
 * @Invar  If a unit has an item, the item of that Unit must be a valid item for this
 *         Unit.
 *       | ! this.hasItem() || canHaveAsItem(getItem())  
 *        
 * @Invar Each Unit has a proper Faction attached to it.
 * 		| this.hasProperFaction()
 * @Invar Each Unit has a proper World attached to it.
 * 		| this.hasProperWorld()
 * @Invar Each Unit has a proper Item attached to it.
 * 		| this.hasProperItem()
 */

public class Unit extends GameObject{
	
	
	
	/* Constructor */
	
	/**
	 * Create a new unit with a given name, position, weight, agility, strength,
	 *  toughness and behavior and initialize the HP to the maximum HP the units
	 *   Stamina to the maximum stamina and the units XP to the minimum XP a unit 
	 *   can have.
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
	 *         to 50.                                                                                                                                                        
	 *       | if (validInitalWeight(weight))                                                                                                                                                
	 *       |   then new.getWeight() == weight                                                                                                                                          
	 *       |   else new.getWeight() == 50
	 * @param  agility
	 *         The agility for this new unit.
	 * @post   If the given agility is a valid initial agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility. Otherwise, the agility of this new unit is equal
	 *         to 50.
	 *       | if (validInitialAgility(agility))
	 *       |   then new.getAgility() == agility
	 *       |   else new.getAgility() == 50
	 * @param  strength
	 *         The Strength for this new Unit.
	 * @post   If the given Strength is a valid initial Strength for any Unit,
	 *         the Strength of this new Unit is equal to the given
	 *         Strength. Otherwise, the Strength of this new Unit is equal
	 *         to 50.
	 *       | if (validInitialSrength(strength))
	 *       |   then new.getStrength() == strength
	 *       |   else new.getStrength() == 50
	 * @param toughness
	 *        The Toughness for this new Unit.
	 * @post   If the given Toughness is a valid initial Toughness for any Unit,
	 * 		   the Toughness of this new Unit is equal to the given Toughness.
	 * 		   Otherwise, the Toughness of this new Unit is equal to 50.
	 * 		 | if (validInitialToughness(toughness))
	 * 		 |   then new.getToughness() == toughness
	 * 		 |   else new.getToughness() == 50
	 * @param  enableDefaultBehavior
	 *         The default behavior for this new Unit.
	 * @post   The default behavior of this new Unit is equal to the given
	 *         default behavior.
	 *       | new.getDefaultBehaviourEnabled() == enabledDefaultBehavior
	 * @effect The XP of this new Unit is set to 0.
	 *       | this.setXP(0)
	 * @throws IllegalArgumentException
	 *         This new Unit cannot have the given default behavior as its default behavior.
	 *       | !isValidDefaultBehaviorEnabled(this.getDefaultBehaviorEnabled())
	 * @throws IllegalArgumentException if either name or initialPosition are not valid 
	 * 		| (!isValidName(name) || !isValidPosition(position) || !isValidDefaultBehaviorEnabled(enableDefaultBehavior)
	 */
	@Raw
	public Unit (String name, int[] initialCoordinates, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws IllegalArgumentException{
		
		super((new Coordinate(initialCoordinates)).toPosition());
		
		if (!isValidName(name) || !isValidDefaultBehaviorEnabled(enableDefaultBehavior)){
			throw new IllegalArgumentException("Name, or enableDefaultBehavior are not valid");
		}
		
		this.setName( name );
		this.setDefaultBehaviorEnabled( enableDefaultBehavior);

		if (!validInitialAgility(agility)){
			setAgility( 50 );
		}else{
			setAgility( agility );
		}

		if (!validInitialStrength( strength )){
			setStrength( 50 );
		}else{
			setStrength( strength );
		}

		if (!validInitialToughness( toughness )){
			setToughness( 50 );
		}else{
			setToughness( toughness );
		}

		if (!validInitialWeight( weight )){
			setWeight( 50 ); 		/* Weight must be set after strength and agility */ 
		}else{
			setWeight( weight );
		}
		
		setHP(getMaxHP());
		setStamina(getMaxStamina());
		this.setXP(Unit.getMinXP());
		
		
		this.immediateTarget = null;
		this.previousPosition = this.getPosition();
		this.setState(State.NOTHING);
		
	}
	
	
	
	/* Destructor */
	
	/**
	 * Terminates this Unit by dropping its Item (if any) at the location it is
	 * currently standing, removing itself from its Faction, and removing
	 * itself from the World. 
	 */
	public void terminate() {
		this.dropItem();
		
		this.isTerminated = true;
		
		Faction faction = this.getFaction();
		this.setFaction(null);
		faction.removeUnit(this);
		
		World world = this.getWorld();
		this.setWorld(null);
		world.removeUnit(this);
	}
	
	/**
	 * Check whether this unit is terminated
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	/**
	 * A variable referencing whether this unit is terminated
	 */
	private boolean isTerminated;
	
	
	
	/* Initial attribute checkers */
	
	/**
	 * Checks whether the given value is a valid initial value for toughness.
	 * @return true if and only if the given toughness is not larger than 100 or smaller than 25
	 * 		| result == (toughness <= 100 && toughness >= 25)
	 */
	private static boolean validInitialToughness(int toughness ){
		return (toughness <= 100 && toughness >= 25);
	}
	
	/**
	 * Checks whether the given value is a valid initial value for strength.
	 * @return true if and only if the given strength is not larger then 100 or smaller then 25
	 * 		| result (strength <= 100 && strength >= 25)
	 */
	private static boolean validInitialStrength(int strength ){
		return (strength <= 100 && strength >= 25);
	}
	
	/**
	 * Checks whether the given value is a valid initial value for agility.
	 * @return true if and only if the given agility is not larger then 100 or smaller then 25
	 * 		| result == (agility <= 100 && agility >= 25)
	 */
	private static boolean validInitialAgility(int agility ){
		return (agility <= 100 && agility >= 25);
	}
	
	/**
	 * Checks whether the given value is a valid initial value for weight.
	 * @return true if and only if the given weight is not larger then 100 or smaller then 25
	 * 		| result == (weight <= 100 && weight >= 25)
	 */
	private static boolean validInitialWeight(int weight ){
		return (weight <= 100 && weight >= 25);
	}
	
        
	
	/* Methods */
	
	/**
	 * Advances the time for the Unit by the given time. This includes basically all the Unit's behavior,
	 * except for defending against an attack, which is instantaneous and outside of the finite state
	 * machine model, which is used for the Unit's behavior.
	 * @param dt
	 */
	@Override
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

		if(this.getDefaultBehaviorRestingCountdown() <= 0){
			this.doDefaultBehaviorResting(this.getDefaultBehaviorRestingCountdown());
		}else{
			this.setDefaultBehaviorRestingCountdown(this.getDefaultBehaviorRestingCountdown() - dt);
		}
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
			dz = this.immediateTarget.getZ() - this.getPosition().getZ();
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
	 * @throws IllegalArgumentException
	 * 		If at least one of the parameters is not -1, 0, or 1
	 * 		| !(dx==-1 || dx==0 || dx==1) || !(dy==-1 || dy==0 || dy==1) || !(dz==-1 || dz==0 || dz==1)
	 * @throws IllegalArgumentException
	 * 		If the calculated destination is out of bounds
	 * 		|! this.getWorld().withinBounds(destination)
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
		}else if(!(dx==-1 || dx==0 || dx==1) || !(dy==-1 || dy==0 || dy==1) || !(dz==-1 || dz==0 || dz==1) || (dx==0 && dy==0 && dz==0)){
			throw new IllegalArgumentException("One of the parameters not -1, 0, or 1. Or all parameters equal to 0. dx: " + dx + "; dy: " + dy + "; dz: " + dz);
		}else{
			Coordinate currentCube = this.getPosition().toCoordinate();
			Coordinate destination = new Coordinate(currentCube.getX()+dx, currentCube.getY()+dy, currentCube.getZ()+dz);
			if(! this.getWorld().withinBounds(destination)){
				throw new IllegalArgumentException("One of the coordinates is out of bounds.");
			}
			immediateTarget = destination.toPosition();
		}
	}
	
	/**
	 * Calculates a path for the Unit to follow towards the destination.
	 * @param destination
	 * 		The coordinate of the cube to go to
	 * @post
	 * 		The given destination will be represented by the last element of the path
	 * 		| new.path[new.path.length] == cubeCenter(destination)
	 * @throws BadFSMStateException
	 * 		If the current FSM state of the Unit is not right.
	 * 		| this.getState() != State.NOTHING &&
	 * 		| this.getState() != State.MOVING &&
	 * 		| this.getState() != State.RESTING_HP &&
	 * 		| this.getState() != State.RESTING_STAMINA &&
	 * 		| this.getState() != State.WORKING
	 * @throws IllegalArgumentException
	 * 		If the destination is not within the bounds.
	 * 		| !withinBounds(destination)
	 */
	public void moveTo(Coordinate destination) throws IllegalArgumentException, BadFSMStateException {
		if(this.getState() != State.NOTHING &&
				this.getState() != State.MOVING &&
				this.getState() != State.RESTING_HP &&
				this.getState() != State.RESTING_STAMINA &&
				this.getState() != State.WORKING){
			throw new BadFSMStateException("Can't execute moveTo from within state: " + this.getState().toString());
		}
		if(!this.getWorld().withinBounds(destination)){
			throw new IllegalArgumentException("destination out of bounds.");
		}
		
		Queue<Coordinate> Path = new LinkedList<Coordinate>();
		List<Coordinate> AjacentCubes = addAll(World.getNeighbors( this.getPosition().toCoordinate()));
		for(Coordinate neighbor: AjacentCubes){
			Boolean hasSolidAjacent = False;
			for(Coordinate ajacent: World.getNeighbors(neighbor) ){
				if(Worls.isSolidTerrain(ajacent)) {
					hasSolidAjacent = True;
				}
			}
			Boolean constraint = False;
			for(Coordinate element: Path){
				if(element == (neighbor, n))// can a queue contaign tuples??
			}
			if(!(World.isPassableTerain(neighbor) && hasSolidAjacent)){
				
			}
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
		return (this.getState() == State.MOVING && this.getStamina() != getMinStamina());
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
	 * 		|this.shouldAttack && this.getVictim() == victim
	 * @throws BadFSMStateException if state is not NOTHING, RESTING_HP, RESTING_STAMINA or WORKING
	 * 		| !(this.getState() == NOTHING || this.getState() == RESTING_HP || this.getState() == RESTING_STAMINA || this.getState() == WORKING)
	 * @throws IllegalArgumentException
	 * 		If the given victim is null
	 * 		| victim == null
	 * @throws IllegalArgumentException
	 * 		If the given victim is this Unit
	 * 		| victim == this
	 * @throws IllegalArgumentException
	 * 		If the given victim is not in range
	 * 		| !this.inRangeForAttack(victim)
	 */
	public void attack(Unit victim) throws BadFSMStateException, IllegalArgumentException {
		if (!(this.getState() == State.NOTHING || this.getState() == State.RESTING_HP || this.getState() == State.RESTING_STAMINA || this.getState() == State.WORKING)){
			throw new BadFSMStateException("Can not attack in this state");
		}else if (victim == null){
			throw new IllegalArgumentException("Cannot attack null.");
		}else if (victim == this){
			throw new IllegalArgumentException("Cannot attack self.");
		}else if (!this.inRangeForAttack(victim)){
			throw new IllegalArgumentException("Victim is not in range.");
		}else{
			this.pointAt(victim);
			this.shouldAttack = true;
			this.setVictim(victim);
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
		
		this.transitionToNothing();
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
		if (!(this.getState() == State.NOTHING || this.getState() == State.WORKING)){
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
	public void work(Coordinate workCube) throws BadFSMStateException{
		if (!(this.getState() == State.NOTHING || this.getState() == State.RESTING_HP 
				|| this.getState() == State.RESTING_STAMINA || workCube.isAdjacentTo(this.getPosition().toCoordinate())))
			throw new BadFSMStateException("Can not go to working from this state");
		else{
			this.workCube = workCube;
			this.shouldWork = true;
		}
	}
	
	/**
	 * Variable referencing the cube this Unit should work at
	 */
	private Coordinate workCube;
	
	/**
	 * Tells whether the Unit is currently working.
	 * @return
	 * 		true iff the Unit is currently working.
	 * 		| result == (this.getState() == State.WORKING);
	 */
	public boolean isWorking() {
		return this.getState() == State.WORKING;
	}
	
	/* XP */

	/**
	 * Return the XP of this Unit.
	 */
	@Basic @Raw
	public long getXP() {
		return this.XP;
	}

	/**
	 * Check whether the given XP is a valid XP for
	 * any Unit.
	 *  
	 * @param  XP
	 *         The XP to check.
	 * @return 
	 *       | result == XP <= Unit.getMinXP();
	 */
	public static boolean isValidXP(long XP) {
		return XP <= Unit.getMinXP();
	}
	
	/**
	 * The minimum XP for a unit
	 */
	public static long getMinXP(){
		return 0;
	}

	/**
	 * Increment the XP of this unit with a given integer
	 * @param dXP
	 * 		| the number you would like to add to XP
	 * @post the new XP is the old XP incremented with the given int 
	 * 		| new.getXP() == this.getXP() + dXP
	 * @effect if the new XP has reached a new ten this units strength agility 
	 * 		and toughness are increased by one
	 * 		| if ((new.getXP()) > (this.getXP() % 10)) then 
	 * 		| setStrength(this.getStrength() +1) ||
	 * 		| setAgility(this.getAgility() + 1) ||
	 * 		| setToughness(this.getToughness() + 1)
	 * @ throws IllegalArgumentException
	 * 		if the given dXP is negative
	 * 		| dXP < 0
	 */
	public void increaseXP(int dXP) throws IllegalArgumentException {
		if (dXP < 0){
			throw new IllegalArgumentException();
		}
		long newXP = this.getXP() + dXP;
		if ((newXP % 10) > (this.getXP() % 10)){
			int randomNum = random.nextInt(3);
			if (randomNum == 0){
				this.setStrength(this.getStrength() + 1);
			}else if (randomNum == 1){
				this.setAgility(this.getAgility() + 1);
			}else{
				this.setToughness(this.getToughness() + 1);
			}
		}
		this.setXP(newXP);
	}
	
	/**
	 * Set the XP of this Unit to the given XP.
	 * 
	 * @param  XP
	 *         The new XP for this Unit.
	 * @post   The XP of this new Unit is equal to
	 *         the given XP.
	 *       | new.getXP() == XP
	 * @throws illegalargumentexception
	 *         The given XP is not a valid XP for any
	 *         Unit.
	 *       | ! isValidXP(getXP())
	 */
	@Raw
	public void setXP(long XP) 
			throws IllegalArgumentException {
		if (! isValidXP(XP))
			throw new IllegalArgumentException();
		this.XP = XP;
	}

	/**
	 * Variable registering the XP of this Unit.
	 */
	private long XP;
	
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
	 * @return
	 * 		True iff the name is not null, at least two characters long, contains only letters,
	 * 		single and double quotes, and spaces, and starts with an Uppercase letter.
	 * 		| result == (name!=null && name.matches("[A-Z][A-Za-z'\" ]+"));
	 */
	@Raw
	public static boolean isValidName(String name) {
		return name != null && name.matches("[A-Z][A-Za-z'\" ]+");
	}
	
		
	
	/* Weight */
	
	/**
	 * Get the weight of this unit and if he is carrying an item his own weight + 
	 * the weight of the item this unit is carrying if any.
	 */
	@Basic @Raw
	public int getWeight(){
		if (this.hasItem()){
			return this.weight + this.getItem().getWeight();
		}else{
			return this.weight;
		}
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
		return weight <= getMaxWeight() && weight >= this.getMinWeight();
	}
	
	
	/**
	 * The maximum weight this unit can have
	 */
	@Basic @Immutable
	public static int getMaxWeight() {
		return maxWeight;
	}
	

	/**
	 * The minimum weight a unit can have 
	 * @return the minimum weight of this unit
	 * 		|result ==  ((this.getStrength() + this.getAgility()) / 2)
	 */
	@Basic
	public int getMinWeight(){
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
	public static boolean isValidAgility(int agility ){
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
	public static boolean isValidToughness(int toughness ){
		return toughness >= getMinToughness() && toughness <= getMaxToughness();
	}
	
	/**
	 * The minimum toughness a unit can have
	 * @return the Minimum toughness a unit can have is 0
	 * 		| result == minToughness
	 */
	@Immutable
	public static int getMinToughness(){
		return minToughness;
	}
	
	/**
	 * The maximum toughness a unit can have
	 * @return The maximum toughness is 200
	 * 		| result == maxToughness
	 */
	@Immutable
	public static int getMaxToughness(){
		return maxToughness;
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
	public static double getDefaultOrientation(){
		return defaultOrientation;
	}
	
	/**
	 * Tells whether the given orientation is valid
	 * @param orientation
	 * 		The orientation to check
	 * @return
	 * 		true iff the given orientation is valid
	 * 		| result == (orientation >= getMinOrientation() && orientation < getMaxOrientation());
	 */
	public static boolean isValidOrientation(double orientation) {
		return orientation >= getMinOrientation() && orientation < getMaxOrientation();
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
		return (HP >= getMinHP() && HP <= this.getMaxHP());
	}
	
	
	/**
	 * the minimum HP a unit can have
	 * @return The maximum HP a unit can have is 200 * weight/100 * toughness/100
	 * 		| result == 200 * weight/100 * toughness/100
	 */
	public int getMaxHP() {
		return (int) Math.ceil(this.getWeight() * this.getToughness() / 50);
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
		return (stamina >= getMinStamina() && stamina <= this.getMaxStamina());
	}
	
	
	/**
	 * the maximum Stamina a unit can have
	 * @return The maximum Stamina a unit can have is 2 * weight * toughness / 100
	 * 		| result == 2 * weight * toughness / 100
	 */
	public int getMaxStamina() {
		return (int) Math.ceil(this.getWeight() * this.getToughness() / 50);
	}
	
	
	/**
	 * The minimum Stamina a unit can have
	 * @return The lowest Stamina a unit can have
	 * 		|result == minStamina	 
	 */
	@Basic @Immutable
	private static int getMinStamina() {
		return minStamina;
	}
	
	
	
	/* World */
	
	/**
	 * Return the world this unit belongs to 
	 * @return this.world
	 */
	@Basic @Raw
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Check whether a Unit can have a given World as its world 
	 * @param world
	 * 		the world to check
	 * @return
	 * 		True iff the given World is not null
	 * 		| result == (world != null)
	 */
	public static boolean isValidWorld(World world){
		return world != null;
	}
	
	/**
	 * Check whether the Unit belongs to a proper World
	 * @return
	 * 		True iff the Unit belongs to a valid World which has this Unit as
	 * 		one of its Units.
	 * 		| result == (isValidWorld(this.getWorld())
	 * 		| 			&& this.getWorld().hasAsUnit(this))
	 */
	@Raw
	public boolean hasProperWorld(){
		return isValidWorld(this.getWorld()) && this.getWorld().hasAsUnit(this);
	}
	
	/**
	 * Set the world this unit belongs to, to a given world
	 * @param world
	 * 		The world you want this unit to belong to
	 * @post
	 * 		The world this Unit belongs to is the given World and this Unit is
	 * 		added to Units in world.
	 * 		| (new this).getWorld() == world
	 * 		| && world.hasAsUnit(this)
	 *		Also if the given World is not the old World, the old World will
	 *		not have this Unit as one of its Units.
	 * @throws IllegalAgumentException 
	 * 		If the given world is not Valid
	 * 		| ! isValidWorld(world)
	 */
	public void setWorld(World world) throws IllegalArgumentException {
		if (! isValidWorld(world)){
			throw new IllegalArgumentException("Given World is invalid: " + world.toString());
		}
		World oldWorld = this.getWorld();
		// Remove reference to old World ... 
		this.world = null;
		// ... so that it will accept the disconnection
		oldWorld.removeUnit(this);
		// Then replace it with new reference ...
		this.world = world;
		// ... and use that new reference so the new World will accept the
		// connection
		world.addUnit(this);
	}
	
	/**
	 * Variable referencing the World this unit belongs to 
	 */
	private World world;
	
	
	
	/* Faction */
	
	/**
	 * Check whether this unit can have a given faction as its faction
	 * @param faction
	 * 		|the faction to check 
	 * @return True if and only if the give faction is a faction of the world
	 * 			this unit belongs to
	 * 		| result == (this.getWorld().hasAsFaction(this)
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction) {
		return this.getWorld().hasAsFaction(faction);
	}
	
	/**
	 * Check whether the faction of this unit if a valid faction for this unit 
	 * @return
	 * 		True iff this unit can have this faction as its faction and the 
	 * 		Faction of this unit has this unit as one of its units
	 * 		| result == (!this.isTerminated()
	 * 		| 				&& canHaveAsFaction(this.getFaction())
	 * 		| 				&& this.getFaction().hasAsUnit(this))
	 * 		| 			|| (this.isTerminated() && this.getFaction() == null)
	 */
	public boolean hasProperFaction() {
		return (!this.isTerminated() && canHaveAsFaction(this.getFaction()) && this.getFaction().hasAsUnit(this)) 
				|| (this.isTerminated() && this.getFaction() == null);
	}
	
	/**
	 * return the faction this unit belongs to 	 
	 */
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * Set the faction of this unit to a given faction
	 * @param faction
	 * 		the faction you want to set the units faction to
	 * @Post if the given faction is not null the faction of this unit is set 
	 * 		to the given faction and this unit is added to Units in Faction
	 * 		| if (! faction == null) then 
	 * 		| 	this.getfaction == faction && faction.hasAsUnit(this)
	 * @Post if the given faction is null the faction of this unit is set to 
	 * 		null iff this unit is terminated  
	 * 		| if (faction == null) then new.getFaction() == null
	 * @throws IllegalArgumentException
	 * 		if the unit is not terminated and the given argument is null
	 * 		| faction == null && !this.isTerminted()		
	 */
	public void setFaction(Faction faction) throws IllegalArgumentException {
		if (faction == null && !this.isTerminated()){
			throw new IllegalArgumentException();
		}else if (faction == null){
			this.faction = null;
		}else{
			this.faction = faction;
			faction.addUnit(this);
		}
	}
	
	/**
	 * a variable referencing the faction this unit belongs to
	 */
	private Faction faction;
	
	
	
	/* Item */
	
	/**
 	* Return the item of this Unit.
 	*/
	@Basic @Raw
	public Item getItem() {
		return this.item;
	}

	/**
 	* Check whether the given item is a valid item for
 	* this Unit.
 	*  
 	* @param item
 	* 		The item to check.
 	* @return
 	* 		True iff this is a valid Unit for Item.
 	*/
	@Raw
	public boolean canHaveAsItem(Item item) {
		return Item.isValidUnit(this);
	}
	
	/**
	 * Tells whether this Unit has a proper Item attached to it.
	 * @return
	 * 		True iff this Unit can have its Item as an Item, and if that Item
	 * 		references this Unit back if it is effective.
	 * 		| result == this.canHaveAsItem(this.getItem()) &&
	 * 		| 			(this.getItem() == null || this.getItem().getUnit() == this)
	 */
	public boolean hasProperItem() {
		return this.canHaveAsItem(this.getItem()) &&
				(this.getItem() == null || this.getItem().getUnit() == this);
	}

	/**
 	* Set the item of this Unit to the given item.
 	* 
 	* @param  item
 	*         The new item for this Unit.
 	* @pre
 	* 		If item is null, this Unit's Item must not reference a Unit.
 	* 		| item != null || this.getItem().getUnit() == null;
 	* @pre
 	* 		If item is not null, it must already reference this Unit.
 	* 		| item == null || item.getUnit() == this;
 	* @post   The item of this new Unit is equal to
 	*         the given item.
 	*       | (new this).getItem() == item
 	* @throws IllegalAgrumentException
 	*         The given item is not a valid item for this
 	*         Unit.
 	*       | ! canHaveAsItem(item)
 	*/
	@Basic @Raw
	public void setItem(Item item) 
			throws IllegalArgumentException {
		if (! canHaveAsItem(item)){
			throw new IllegalArgumentException();
		}
		if (item == null) {
			assert(this.getItem().getUnit() == null);
			this.item = null;
		} else {
			assert(item.getUnit() == this);
			this.item = item;
		}
	}

	/**
	 * check whether the unit is carrying an Item
	 * @return this.getItem() != null
	 */
	public boolean hasItem(){
		return this.getItem() != null;
	}
	
	/**
 	* Variable registering the item of this Unit.
 	*/
	private Item item;
	
	/**
	 * Makes the Unit drop the item it is holding, if any.
	 * If it is holding no item, nothing happens.
	 * The dropped item appears at exactly the position the Unit has when it is
	 * dropping it.
	 * @post
	 * 		If holding an item
	 * 		| if this.hasItem() then
	 * 		... the Item will be associated with this Unit's World
	 * 		| (new (this.getItem()).getWorld()) == this.getWorld()
	 * 		... and no longer with this Unit itself
	 * 		| ! (new (this.getItem()).hasUnit())
	 * 		... and this Unit will no longer hold an item
	 * 		| (new this).getItem() == null
	 */
	public void dropItem() {
		if (this.hasItem()) {
			// Disconnect item from this Unit
			Item item = this.getItem();
			this.getItem().setUnit(null);
			
			// Connect item to this Unit's World
			item.setWorld(this.getWorld());
			world.addItem(item);
			// Don't forget to set the Item's position
			item.setPosition(this.getPosition());
		}
	}
	
	/**
	 * Make the Unit pick up the given item, if not holding one already.
	 * If it is holding an item already, nothing happens.
	 * @param item
	 * 		The Item to pick up
	 * @post
	 * 		If the Unit is not holding an Item, and the given Item is effective
	 * 		| if !this.hasItem() then
	 * 		... the Item will be associated with this Unit
	 * 		| (new item).getUnit() == this
	 * 		... and no longer with this Unit's (or any) World
	 * 		| ! (new item).hasWorld()
	 * 		... and this Unit will hold the given Item
	 * 		| (new this).getItem() == item
	 */
	public void pickUpItem(Item item) {
		if (!this.hasItem() && item != null) {
			// Disconnect item from its (and this Unit's) World
			item.setWorld(null);
			this.getWorld().removeItem(item);
			
			// Connect item to this Unit
			item.setUnit(this);
			this.setItem(item);
		}
	}

	
	
	/* flags */
	
	/**
	 * return the value of the shouldRest flag
	 */
	@Basic
	public boolean getShouldRestFlag(){
		return this.shouldRest;
	}
	
	/**
	 * return the value of the shouldAttack flag
	 */
	@Basic
	public boolean getShouldAttackFlag(){
		return this.shouldAttack;
	}
	
	/**
	 * return the value of the shouldWork flag
	 */
	@Basic
	public boolean getShouldWorkFlag(){
		return this.shouldWork;
	}
	
	
	
	/* Victim */
	
	/**
	 * return the victim
	 */
	@Basic
	public Unit getVictim(){
		return this.victim;
	}
	
	/**
	 * Set the victim to a given victim
	 * @Post the victim is set to the given victim
	 * 		|new.getVictim() == victim
	 */
	public void setVictim(Unit victim){
		this.victim = victim;
	}
	
	
	
	/* ImmediateTarget */
	
	/**
	 * Return the immediateTarget of this Unit.
	 */
	@Basic @Raw
	public Position getImmediateTarget() {
		return this.immediateTarget;
	}

	/**
	 * Check whether the given immediateTarget is a valid immediateTarget for
	 * this Unit.
	 *  
	 * @param  immediateTarget
	 *         The immediateTarget to check.
	 * @return if and only if immediateTarget is a valid Position and ImmidiateTarget is adjacent to the current Position
	 *       | result == this.canHaveAsPosition( immediateTarget ) && 
	 *       this.getPosition().toCoordinate().isAdjacentTo(immediateTarget.toCoordinate());
	 */
	public boolean canHaveAsTarget(Position immediateTarget) {
		return this.canHaveAsPosition( immediateTarget ) && this.getPosition().toCoordinate().isAdjacentTo(immediateTarget.toCoordinate());
	}

	/**
	 * Set the immediateTarget of this Unit to the given immediateTarget.
	 * 
	 * @param  immediateTarget
	 *         The new immediateTarget for this Unit.
	 * @post   The immediateTarget of this new Unit is equal to
	 *         the given immediateTarget.
	 *       | new.getImmidiateTarget() == immediateTarget
	 * @throws IllegalArgumentException
	 *         The given immediateTarget is not a valid immediateTarget for this
	 *         Unit.
	 *       | ! canHaveAsTarget(immidiateTarget())
	 */
	@Raw
	public void setImmediateTarget(Position immediateTarget) 
			throws IllegalArgumentException {
		if (! this.canHaveAsTarget(immediateTarget))
			throw new IllegalArgumentException();
		this.immediateTarget = immediateTarget;
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
		this.state = State.NOTHING;
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
		this.state = State.RESTING_INIT;
		this.restingInitialCountdown = getRestingInitTime();
		this.setFlagsLow();
	}
	
	/**
	 * Gives the time it takes for a unit to go through the initial resting phase
	 * @return
	 * 		The time it takes for a unit to go through the initial resting phase
	 * 		| result == this.getRestingHPTime();
	 */
	@Basic
	private double getRestingInitTime(){
		return getRestingHPTime();
	}
	
	/**
	 * Set the state of this unit to RestingHP
	 */
	private void transitionToRestingHP(){
		this.state = State.RESTING_HP;
		this.restingHPCountdown = getRestingHPTime();
		this.setFlagsLow();
	}
	
	/**
	 * Gives the time it takes for a unit to restore some amount of HP
	 * @return
	 * 		The time it takes for a unit to restore some amount of HP
	 * 		| result == 40 / (double) this.getToughness();
	 */
	private double getRestingHPTime(){
		return 40 / (double) this.getToughness();
	}
	
	/**
	 * Gives back the amount of HP to restore every time this Unit does.
	 * @return
	 * 		The amount of HP to restore every time this Unit does.
	 * 		| result == restingHPAmount;
	 */
	private static double getRestingHPAmount() {
		return restingHPAmount;
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
	 * 		| result == 20 / (double) this.getToughness();
	 */
	private double getRestingStaminaTime() {
		return 20 / (double) this.getToughness();
	}

	
	
	/**
	 * Gives back the amount of stamina to restore every time a Unit does.
	 * @return
	 * 		The amount of stamina to restore every time a Unit does.
	 * 		| result == restingStaminaAmount;
	 */
	private static double getRestingStaminaAmount() {
		return restingStaminaAmount;
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
	 * 		| result == attackingTime;
	 */
	@Basic @Immutable
	private static double getAttackingTime() {
		return attackingTime;
	}
	
	/**
	 * Checks whether this Unit has reached its immediateTarget, or possibly
	 *  overshot it by some distance.
	 * @return true iff reached or overshot the immediateTarget position
	 * 	| result == VectorUtils.between(immediateTarget.getX(), previousPosition.getX(), this.getPosition().getX())
	 *	|		|| VectorUtils.between(immediateTarget.getY(), previousPosition.getY(), this.getPosition().getY())
	 *	|		|| VectorUtils.between(immediateTarget.getZ(), previousPosition.getZ(), this.getPosition().getZ())
	 */
	private boolean reachedImmediateTarget() {
		return (VectorUtils.between(immediateTarget.getX(), previousPosition.getX(), this.getPosition().getX())
				|| VectorUtils.between(immediateTarget.getY(), previousPosition.getY(), this.getPosition().getY())
				|| VectorUtils.between(immediateTarget.getZ(), previousPosition.getZ(), this.getPosition().getZ()));
	}
	
	/**
	 * 
	 * @param victim
	 * 			|The unit you want to attack
	 * @return True if and only if the position of victim is a neighboring cube of the position of this unit 
	 * 			| result == this.getPosition().toCoordinate().isAdjacentTo(victim.getPosition().toCoordinate())
	 */
	private boolean inRangeForAttack(Unit victim){
		return this.getPosition().toCoordinate().isAdjacentTo(victim.getPosition().toCoordinate());
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
	 * @effect
	 * 		The Unit will execute pointAt on other's position
	 * 		| pointAt(other.getPosition())
	 * @throw IllegalArgumentException
	 * 		If other is null
	 * 		| other == null
	 */
	private void pointAt(Unit other) throws IllegalArgumentException {
		if(other == null) {
			throw new IllegalArgumentException("Can't point at null Unit.");
		}
		this.pointAt(other.getPosition());
	}
	
	/**
	 * Make this Unit point directly at a given target position.
	 * @param target
	 * 		The point to look at.
	 * @post
	 * 		Unit will be looking at target.
	 * 		| new.getOrientation() == Math.atan2(target.getY() - this.getPosition()getY(),
	 * 		| 	target.getX() - this.getPosition().getX());
	 * @throw IllegalArgumentException
	 * 		If target is null
	 * 		| target == null 
	 */
	private void pointAt(Position target) {
		if(target == null){
			throw new IllegalArgumentException("Can't point at null location");
		}
		this.setOrientation(Math.atan2(target.getY() - this.getPosition().getY(), target.getX() - this.getPosition().getX()));
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
		double result = random.nextDouble();
		System.out.println(result);
		if(result <= chance){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Moves the Unit to random position in the game world within dodging bounds.
	 */
	private void dodge(){
		Position destination;
		do{
			destination = new Position(
					this.getPosition().getX() + (random.nextBoolean()?1:-1) * random.nextDouble(),
					this.getPosition().getY() + (random.nextBoolean()?1:-1) * random.nextDouble(),
					this.getPosition().getZ() + (random.nextBoolean()?1:-1) * random.nextDouble()
					);
		}while((destination.getX()==0 && destination.getY()==0 && destination.getZ()==0) || !this.getWorld().withinBounds(destination));
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
		if(random.nextDouble() <= chance){
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
	 * Execute the behavior when in the NOTHING state.
	 * @param dt
	 * 		The passed time since last update.
	 */
	private void doBehaviorNothing(double dt) {
		if(immediateTarget != null){
			this.setState(State.MOVING);
			this.setFlagsLow();
		}else if(!this.getPath().isEmpty()){
			this.immediateTarget = this.extractFromPath();
			this.setState(State.MOVING);
			this.setFlagsLow();
		}else if(this.shouldRest){
			this.transitionToRestingInit();
		}else if(this.shouldWork){
			this.transitionToWorking();
		}else if(this.shouldAttack){
			this.transitionToAttacking();
		}else if(this.getDefaultBehaviorEnabled()){
			int result = random.nextInt(3);
			if(result == 0){
				try{
					this.moveTo(getRandomCoordinate());
				}catch(IllegalArgumentException e){
				}catch(BadFSMStateException f){
				}
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
			if(!this.getPath().isEmpty()){
				try{this.setPosition(immediateTarget);}catch(IllegalArgumentException e){}
				immediateTarget = this.extractFromPath();
			}else{
				try{this.setPosition(immediateTarget);}catch(IllegalArgumentException e){}
				immediateTarget = null;
				this.transitionToNothing();
			}
		}else{
			
			if(this.defaultBehaviorEnabled){
				if(random.nextDouble() <= getDefaultBehaviorSprintingThreshold() * dt){
					this.startSprinting();
				}
			}
			
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
			
			this.setOrientation(Math.atan2(deltaPosition[1], deltaPosition[0]));
			
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
					if(this.getStamina() == getMinStamina()){
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
		}else if (this.getHP() == this.getMaxHP()){
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
			this.transitionToNothing();
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
			if (this.hasItem()){
				Item oldItem = this.getItem();
				this.dropItem();
				oldItem.setPosition(this.workCube.toPosition());
			}else if (this.getWorld().getCubeAt(this.workCube) == World.TerrainType.WORKSHOP){
				//TODO One boulder and log will be consumed from target cube
			}else if (this.hasItemOnWorkCube()){
				this.pickUpItem(this.getItemAtWorkCube());
			}else if (this.getWorld().getCubeAt(this.workCube) == World.TerrainType.TREE){
				//TODO cube disappears and leaves a log
			}else if (this.getWorld().getCubeAt(this.workCube) == World.TerrainType.ROCK){
				//TODO the cube disappears leaving a boulder 
			}
			this.setXP(this.getXP() + 10);
			this.transitionToNothing();
		}else{
			this.workingCountdown -= dt;
		}
	}
	
	/**
	 * Check whether the workCube has a Item on it
	 */
	private boolean hasItemOnWorkCube(){
		if (this.getItemAtWorkCube() == null){
			return false;
		}return true;
	}
	
	/**
	 * return the Item that is at the workCube or null if nothing is on the 
	 * gameCube. If the gameCube is null the result is also null.
	 */
	private Item getItemAtWorkCube(){
		if (this.workCube == null){
			return null;
		}
		for (Item item: this.getWorld().getItems()){
			if (item.getPosition().toCoordinate() == this.workCube){
				return item;
			}
		}return null;
	}
	
	/**
	 * Execute the behavior when in the ATTACKING state.
	 * @param dt
	 * 		The passed time 
	 */
	private void doBehaviorAttacking(double dt) {
		if (this.attackingCountdown > 0){
			this.attackingCountdown -= dt;
		}else if (this.inRangeForAttack(this.getVictim())){
			try{
				this.getVictim().defend(this);
			}catch (IllegalArgumentException e){
			}
			this.transitionToNothing();
		}else{
			this.transitionToNothing();
		}
		
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
			this.setStamina(getMinStamina());
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
			newHP += getRestingHPAmount();
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
			newStamina += getRestingStaminaAmount();
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
	 * Order this Unit to rest, and reset the timer which will tell it to do so the next time.
	 * @param overshoot
	 * 		The overshoot by which the defaultBehaviorRestingCountdown has gone below zero.
	 * @effect
	 * 		The Unit will be ordered to rest.
	 * 		| this.rest();
	 * @post
	 * 		The new defaultBehaviorRestingCountdown will be reset
	 * 		| new.getDefaultBehaviorRestingCountdown() == getDefaultBehaviorRestingTime() + overshoot;
	 */
	private void doDefaultBehaviorResting(double overshoot) {
		this.setDefaultBehaviorRestingCountdown(getDefaultBehaviorRestingTime() + overshoot);
		try{
			this.rest();
		}catch(BadFSMStateException e){
		}
	}
	
	@Basic @Immutable
	private static double getDefaultBehaviorRestingTime() {
		return defaultBehaviorRestingTime;
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
	 * Gives back the defaultBehaviorSprintingThreshold
	 * @return
	 * 		The defaultBehaviorSprintingThreshold
	 * 		| result == this.defaultBehaviorSprintingThreshold
	 */
	@Basic
	private static double getDefaultBehaviorSprintingThreshold() {
		return defaultBehaviorSprintingThreshold;
	}

	/**
	 * Return the defaultBehaviorRestingCountdown of this Unit.
	 */
	@Basic @Raw
	private double getDefaultBehaviorRestingCountdown() {
		return this.defaultBehaviorRestingCountdown;
	}

	/**
	 * Check whether the given defaultBehaviorRestingCountdown is a valid defaultBehaviorRestingCountdown for
	 * any Unit.
	 *  
	 * @param  defaultBehaviorRestingCountdown
	 *         The defaultBehaviorRestingCountdown to check.
	 * @return 
	 *       | result == Double.isFinite(defaultBehaviorRestingCountdown); 
	 */
	private static boolean isValidDefaultBehaviorRestingCountdown(double defaultBehaviorRestingCountdown) {
		return Double.isFinite(defaultBehaviorRestingCountdown);
	}

	/**
	 * Set the defaultBehaviorRestingCountdown of this Unit to the given defaultBehaviorRestingCountdown.
	 * 
	 * @param  defaultBehaviorRestingCountdown
	 *         The new defaultBehaviorRestingCountdown for this Unit.
	 * @post   If the given defaultBehaviorRestingCountdown is a valid defaultBehaviorRestingCountdown for any Unit,
	 *         the defaultBehaviorRestingCountdown of this new Unit is equal to the given
	 *         defaultBehaviorRestingCountdown.
	 *       | if (isValidDefaultBehaviorRestingCountdown(defaultBehaviorRestingCountdown))
	 *       |   then new.getDefaultBehaviorRestingCountdown() == defaultBehaviorRestingCountdown
	 */
	@Raw
	private void setDefaultBehaviorRestingCountdown(double defaultBehaviorRestingCountdown) {
		if (isValidDefaultBehaviorRestingCountdown(defaultBehaviorRestingCountdown))
			this.defaultBehaviorRestingCountdown = defaultBehaviorRestingCountdown;
	}
	
	
	
	/* Constants */


	private static final int maxWeight = 200;
	private static final int minAgility = 0;
	private static final int maxAgility = 200;
	private static final int minStrength = 0;
	private static final int maxStrength = 200;
	private static final int minToughness = 0;
	private static final int maxToughness = 200;
	private static final double defaultOrientation = Math.PI / 2;
	private static final double minOrientation = 0;
	private static final double maxOrientation = Math.PI * 2;
	private static final int minHP = 0;
	private static final int minStamina = 0;
	private static final double maxDT = 0.2d;
	
	private static final int restingHPAmount = 1;
	private static final int restingStaminaAmount = 1;
	
	private static final double attackingTime = 1d;
	
	/**
	 * A helper variable to hold the chance that a Unit starts.
	 */
	private static final double defaultBehaviorSprintingThreshold = 0.1d;
	
	/**
	 * A variable to hold the time between two mandatory scheduled resting sessions
	 */
	private static final double defaultBehaviorRestingTime = 180d;
	
	
	
	/* Variables */
	
		
	/**
	 * The place that the Unit is currently going
	 */
	private Position immediateTarget;
	
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
	 * The time we have left until the next automatic, scheduled resting session
	 */
	private double defaultBehaviorRestingCountdown;
	
	/**
	 * The unit that will be attacked once the attackingCountdown is done
	 */
	private Unit victim;
	
	private boolean shouldRest;
	private boolean shouldWork;
	private boolean shouldAttack;
	private boolean sprinting;
	private String name;
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
