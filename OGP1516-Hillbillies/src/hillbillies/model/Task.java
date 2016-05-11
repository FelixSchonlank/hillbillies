package hillbillies.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.expressions.ReadVarriable;
import hillbillies.model.statements.AttackStatement;
import hillbillies.model.statements.Statement;

/** 
 * @Invar  Each Task can have its name as name.
 *       | canHaveAsName(this.getName())
 * @Invar   Each Task must have proper schedulers.
 *        | hasProperSchedulers()
 * 
 * @Invar  The unit of each Task must be a valid unit for any
 *         Task.
 *       | isValidUnit(getUnit())
 * @Invar  The Priority of each Task must be a valid Priority for any
 *         Task.
 *       | isValidPriority(getPriority())
 * @Invar   Each Task must have proper Activities.
 *        | hasProperStatements()
 * @Invar  The Position of each Task must be a valid Position for any
 *         Task.
 *       | isValidPosition(getPosition())
 */
public class Task {

	
	/**
	 * Initialize this new Task with given name, priority and a given set of activities
	 * 
	 * @param  name
	 *         The name for this new Task.
	 * @post   The name of this new Task is equal to the given
	 *         name.
	 *       | new.getName() == name
	 * @throws IllegalArgumentException
	 *         This new Task cannot have the given name as its name.
	 *       | ! canHaveAsName(this.getName())
	 * @param  priority
	 *         The Priority for this new Task.
	 * @effect The Priority of this new Task is set to
	 *         the given Priority.
	 *       | this.setPriority(priority)
	 * @param activities
	 * 		| A list with the activities of this task
	 * @effect the activities of this task is set to the given activities
	 * 		| this.activities.addAll(activities)
	 * @param  position
	 *         The Position for this new Task.
	 * @effect The Position of this new Task is set to
	 *         the given Position.
	 *       | this.setPosition(position)
	 */
	public Task(String name, int priority, Position position, List<Statement> activities) throws IllegalArgumentException {
		if (! canHaveAsName(name))
			throw new IllegalArgumentException();
		this.name = name;
		this.setPriority(priority);
		this.setPosition(position);
		for(Statement activity : activities){
			this.addActivity(activity);
		}
	}
	
	/* Variables */
	
	/**
	 * return the value of a given variable name
	 * @param name
	 * 		| The name of the variable
	 * @return the value of name
	 * 		| result == this.variables.get(name)
	 */
	public Object getValue(String name){
		return this.variables.get(name);
	} 
	
	/**
	 * Add a give name and value of a variable to the map
	 * @param variable
	 * 		| The name of the variable to add
	 * @param value
	 * 		| the value of the variable to add
	 * @post the tuple (variable, object) is now part of the map of variables
	 * 		| this.varibles.get(variable) == value
	 */
	public void addVariable(String variable, Object value){
		this.variables.put(variable, value);
	}
	
	/**
	 * Map with the name (String) of a variable as key and its value (Object) as Value
	 */
	private Map<String, Object> variables;
	
	/* Activities */

	/**
	 * Return the Activity associated with this Task at the
	 * given index.
	 * 
	 * @param  index
	 *         The index of the Activity to return.
	 * @throws IndexOutOfBoundsException
	 *         The given index is not positive or it exceeds the
	 *         number of Activities for this Task.
	 *       | (index < 1) || (index > getNbStatements())
	 */
	@Basic
	@Raw
	public Statement getActivityAt(int index) throws IndexOutOfBoundsException {
		return activities.get(index - 1);
	}

	/**
	 * Return the number of Activities associated with this Task.
	 */
	@Basic
	@Raw
	public int getNbActivities() {
		return activities.size();
	}

	/**
	 * Check whether this Task can have the given Activity
	 * as one of its Activities.
	 * 
	 * @param  activity
	 *         The Activity to check.
	 * @return True if and only if the given Activity is effective
	 *         and that Activity can have this Task as its Task.
	 *       | result ==
	 *       |   (activity != null) &&
	 *       |   Statement.isValidTask(this)
	 */
	@Raw
	public boolean canHaveAsActivity(Statement activity) {
		return (activity != null);
	}

	/**
	 * Check whether this Task can have the given Activity
	 * as one of its Activities at the given index.
	 * 
	 * @param  activity
	 *         The Activity to check.
	 * @return False if the given index is not positive or exceeds the
	 *         number of Activities for this Task + 1.
	 *       | if ( (index < 1) || (index > getNbStatements()+1) )
	 *       |   then result == false
	 *         Otherwise, false if this Task cannot have the given
	 *         Activity as one of its Activities.
	 *       | else if ( ! this.canHaveAsStatement(activity) )
	 *       |   then result == false
	 *         Otherwise, true if and only if the given Activity is
	 *         not registered at another index than the given index.
	 *       | else result ==
	 *       |   for each I in 1..getNbStatements():
	 *       |     (index == I) || (getStatementAt(I) != activity)
	 */
	@Raw
	public boolean canHaveAsActivityAt(Statement activity, int index) {
		if ((index < 1) || (index > getNbActivities() + 1))
			return false;
		if (!this.canHaveAsActivity(activity))
			return false;
		for (int i = 1; i < getNbActivities(); i++)
			if ((i != index) && (getActivityAt(i) == activity))
				return false;
		return true;
	}

	/**
	 * Check whether this Task has proper Activities attached to it.
	 * 
	 * @return True if and only if this Task can have each of the
	 *         Activities attached to it as a Activitie at the given index,
	 *         and if each of these Activities references this Task as
	 *         the Task to which they are attached.
	 *       | result ==
	 *       |   for each I in 1..getNbStatements():
	 *       |     ( this.canHaveAsStatementAt(getStatementAt(I) &&
	 *       |       (getStatementAt(I).getTask() == this) )
	 */
	public boolean hasProperActivity() {
		for (int i = 1; i <= getNbActivities(); i++) {
			if (!canHaveAsActivityAt(getActivityAt(i), i))
				return false;
			if (getActivityAt(i).getTask() != this)
				return false;
		}
		return true;
	}

	/**
	 * Check whether this Task has the given Activity as one of its
	 * Activities.
	 * 
	 * @param  activity
	 *         The Activity to check.
	 * @return The given Activity is registered at some position as
	 *         a Activity of this Task.
	 *       | for some I in 1..getNbStatements():
	 *       |   getStatementAt(I) == activity
	 */
	public boolean hasAsActivity(@Raw Statement activity) {
		return activities.contains(activity);
	}

	/**
	 * Add the given Activity to the list of Activities of this Task.
	 * 
	 * @param  activitie
	 *         The Activity to be added.
	 * @Pre    The given Activity is effective and already references
	 *         this Task, and this Task does not yet have the given
	 *         Activity as one of its Activities.
	 *       | (activity != null) && (activitie.getTask() == this) &&
	 *       | (! this.hasAsStatement(activity))
	 * @post   The number of Activities of this Task is
	 *         incremented by 1.
	 *       | new.getNbStatements() == getNbStatements() + 1
	 * @post   This Task has the given Activity as its very last Activity.
	 *       | new.getStatementAt(getNbStatements()+1) == activity
	 */
	public void addActivity(@Raw Statement activity) {
		assert (activity != null) && (activity.getTask() == this)
				&& (!this.hasAsActivity(activity));
		activities.add(activity);
	}

	/**
	 * Remove the given Activity from the list of Activities of this Task.
	 * 
	 * @param  activity
	 *         The Activity to be removed.
	 * @Pre    The given Activity is effective, this Task has the
	 *         given Activity as one of its Activities, and the given
	 *         Activity does not reference any Task.
	 *       | (activity != null) &&
	 *       | this.hasAsStatement(activity) &&
	 *       | (activitie.getTask() == null)
	 * @post   The number of Activities of this Task is
	 *         decremented by 1.
	 *       | new.getNbStatements() == getNbStatements() - 1
	 * @post   This Task no longer has the given Activity as
	 *         one of its Activities.
	 *       | ! new.hasAsStatement(activity)
	 * @post   All Activities registered at an index beyond the index at
	 *         which the given Activity was registered, are shifted
	 *         one position to the left.
	 *       | for each I,J in 1..getNbStatements():
	 *       |   if ( (getStatementAt(I) == activity) and (I < J) )
	 *       |     then new.getStatementAt(J-1) == getStatementAt(J)
	 */
	@Raw
	public void removeActivity(Statement activity) {
		assert (activity != null) && this.hasAsActivity(activity)
				&& (activity.getTask() == null);
		activities.remove(activity);
	}

	/**
	 * Variable referencing a list collecting all the Activities
	 * of this Task.
	 * 
	 * @Invar  The referenced list is effective.
	 *       | activities != null
	 * @Invar  Each Activity registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each activity in activities:
	 *       |   ( (activity != null) &&
	 *       |     (! activitie.isTerminated()) )
	 * @Invar  No Activity is registered at several positions
	 *         in the referenced list.
	 *       | for each I,J in 0..activities.size()-1:
	 *       |   ( (I == J) ||
	 *       |     (activities.get(I) != activities.get(J))
	 */
	private final List<Statement> activities = new ArrayList<Statement>();

	
	/* priority */

	/**
	 * Return the Priority of this Task.
	 */
	@Basic @Raw
	public int getPriority() {
		return this.priority;
	}
	
	/**
	 * Check whether the given Priority is a valid Priority for
	 * any Task.
	 *  
	 * @param  Priority
	 *         The Priority to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidPriority(int priority) {
		return true;
	}
	
	/**
	 * Set the Priority of this Task to the given Priority.
	 * 
	 * @param  priority
	 *         The new Priority for this Task.
	 * @post   The Priority of this new Task is equal to
	 *         the given Priority.
	 *       | new.getPriority() == priority
	 * @throws IllegalArgumentException
	 *         The given Priority is not a valid Priority for any
	 *         Task.
	 *       | ! isValidPriority(getPriority())
	 */
	@Raw
	public void setPriority(int priority) 
			throws IllegalArgumentException {
		if (! isValidPriority(priority))
			throw new IllegalArgumentException();
		this.priority = priority;
	}
	
	/**
	 * Variable registering the Priority of this Task.
	 */
	private int priority;
		
	/* name */
	
	/**
	 * Return the name of this Task.
	 */
	@Basic @Raw @Immutable
	public String getName() {
		return this.name;
	}
	
	/**
	 * Check whether this Task can have the given name as its name.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return 
	 *       | result == true				
	*/
	@Raw
	public boolean canHaveAsName(String name) {
		return true;
	}
	
	/**
	 * Variable registering the name of this Task.
	 */
	private final String name;
	
	/* Unit */	
	
	/**
	 * Return the unit of this Task.
	 */
	@Basic @Raw
	public Unit getUnit() {
		return this.unit;
	}
	
	/**
	 * check whether this Task has a unit 
	 * @return true iff the unit of this Task is not null
	 * 		| result == (this.getUnit() != null)
	 */
	public boolean hasUnit(){
		return this.getUnit() != null;
	}
	
	/**
	 * Check whether the given unit is a valid unit for
	 * any Task.
	 *  
	 * @param  unit
	 *         The unit to check.
	 * @return 
	 *       | result == (unit == null || !unit.hasTask() || unit.getTask() == this)
	*/
	public boolean canHaveAsUnit(Unit unit) {
		return (unit == null || !unit.hasTask() || unit.getTask() == this);
	}
	
	/**
	 * Set the unit of this Task to the given unit.
	 * 
	 * @param  unit
	 *         The new unit for this Task.
	 * @post   The unit of this new Task is equal to
	 *         the given unit.
	 *       | new.getUnit() == unit
	 * @effect if the given unit is not null the new task of the give unit is 
	 * 			set to this task
	 * 		| if ( unit != null) then unit.setTask(this)
	 * @throws IllegalArgumentException
	 *         The given unit is not a valid unit for any
	 *         Task.
	 *       | ! isValidUnit(getUnit())
	 */
	@Raw
	public void setUnit(Unit unit) 
			throws IllegalArgumentException {
		if (! canHaveAsUnit(unit))
			throw new IllegalArgumentException();
		this.unit = unit;
		if (unit != null){ 
			unit.setTask(this);
		}	
	}
	
	/**
	 * set the task of this tasks unit to null and the unit of this task to null
	 * @effect If this Task has a Unit the Unit is set to null and the task of 
	 * 			that unit is set to null else nothing changes
	 * 		| if (this.hasUnit()) then
	 * 		| 	this.getUnit().setUnit(null) && this.setUnit(null) 
	 */
	public void removeUnit(){
		this.getUnit().setTask(null);
		this.setUnit(null);
	}
	
	/**
	 * Variable registering the unit of this Task.
	 */
	private Unit unit;
	
	/* Schedulers */
	
	/**
	 * Check whether this Task has the given scheduler as one of its
	 * schedulers.
	 * 
	 * @param  scheduler
	 *         The scheduler to check.
	 */
	@Basic
	@Raw
	public boolean hasAsScheduler(@Raw Scheduler scheduler) {
		return schedulers.contains(scheduler);
	}
	
	/**
	 * Check whether this Task can have the given scheduler
	 * as one of its schedulers.
	 * 
	 * @param  scheduler
	 *         The scheduler to check.
	 * @return True if and only if the given scheduler is effective
	 *         and that scheduler has this Task as one of its tasks
	 *       | result ==
	 *       |   (scheduler != null) &&
	 *       |   Scheduler.hasAsTask(this)
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return (scheduler != null) && (scheduler.hasAsTask(this));
	}
	
	/**
	 * Check whether this Task has proper schedulers attached to it.
	 * 
	 * @return True if and only if this Task can have each of the
	 *         schedulers attached to it as one of its schedulers,
	 *         and if each of these schedulers references this Task as
	 *         one of the tasks to which they are attached.
	 *       | for each scheduler in Scheduler:
	 *       |   if (hasAsScheduler(scheduler))
	 *       |     then canHaveAsScheduler(scheduler) &&
	 *       |          (scheduler.hasAsTask(this))
	 */
	public boolean hasProperSchedulers() {
		for (Scheduler scheduler : schedulers) {
			if (!canHaveAsScheduler(scheduler))
				return false;
			if (!scheduler.hasAsTask(this))
				return false;
		}
		return true;
	}
	
	/**
	 * Return the number of schedulers associated with this Task.
	 *
	 * @return  The total number of schedulers collected in this Task.
	 *        | result ==
	 *        |   card({scheduler:Scheduler | hasAsScheduler({scheduler)})
	 */
	public int getNbSchedulers() {
		return schedulers.size();
	}
	
	/**
	 * Add the given scheduler to the set of schedulers of this Task.
	 * 
	 * @param  scheduler
	 *         The scheduler to be added.
	 * @Pre    The given scheduler is effective and already references
	 *         this Task.
	 *       | (scheduler != null) && (scheduler.getTask() == this)
	 * @post   This Task has the given scheduler as one of its schedulers.
	 *       | new.hasAsScheduler(scheduler)
	 */
	public void addScheduler(@Raw Scheduler scheduler) {
		assert (scheduler != null) && (scheduler.hasAsTask(this));
		schedulers.add(scheduler);
	}
	
	/**
	 * Remove the given scheduler from the set of schedulers of this Task.
	 * 
	 * @param  scheduler
	 *         The scheduler to be removed.
	 * @Pre    This Task has the given scheduler as one of
	 *         its schedulers, and the given scheduler does not
	 *         reference this Task.
	 *       | this.hasAsScheduler(scheduler) &&
	 *       | (scheduler.getTask() == null)
	 * @post   This Task no longer has the given scheduler as
	 *         one of its schedulers.
	 *       | ! new.hasAsScheduler(scheduler)
	 */
	@Raw
	public void removeScheduler(Scheduler scheduler) {
		assert this.hasAsScheduler(scheduler) && (!scheduler.hasAsTask(this));
		schedulers.remove(scheduler);
	}
	
	/**
	 * Variable referencing a set collecting all the schedulers
	 * of this Task.
	 * 
	 * @Invar  The referenced set is effective.
	 *       | schedulers != null
	 * @Invar  Each scheduler registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each scheduler in schedulers:
	 *       |   ( (scheduler != null) &&
	 *       |     (! scheduler.isTerminated()) )
	 */
	private final Set<Scheduler> schedulers = new HashSet<Scheduler>();

	/**
	 * Return one of the schedulers this task belongs to
	 * @return schedulers
	 */
	public Scheduler getScheduler(){
		for (Scheduler element : this.schedulers){
			return element;
		}return null;
	}
	
	/* Position */
	
	/**
	 * Return the Position of this Task.
	 */
	@Basic @Raw
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given Position is a valid Position for
	 * any Task.
	 *  
	 * @param  Position
	 *         The Position to check.
	 * @return 
	 *       | result == this.getScheduler().getFaction().getRandomUnit().getWorld().withinBounds(position.toCoordinate())
	*/
	public boolean canHaveAsPosition(Position position) {
		return this.getScheduler().getFaction().getRandomUnit().getWorld().withinBounds(position.toCoordinate());
	}
	
	/**
	 * Set the Position of this Task to the given Position.
	 * 
	 * @param  position
	 *         The new Position for this Task.
	 * @post   The Position of this new Task is equal to
	 *         the given Position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given Position is not a valid Position for any
	 *         Task.
	 *       | ! canHaveAsPosition(getPosition())
	 */
	@Raw
	public void setPosition(Position position) 
			throws IllegalArgumentException {
		if (! canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable registering the Position of this Task.
	 */
	private Position position;
		
	/* isWelFormed */
	
	public boolean isWelFormed(){
		Set<String> statements = new HashSet<String>();
		for(Statement statement : this.activities){
			if (statement instanceof AttackStatement){
				if((((AttackStatement) statement).getVictim().isVarriable())){
					if(! statements.contains(((ReadVarriable) ((AttackStatement) statement).getVictim()).getName())
				}
			}
		}
	}
	
}
