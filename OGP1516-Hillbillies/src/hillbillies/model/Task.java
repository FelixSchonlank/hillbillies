package hillbillies.model;


import java.util.HashSet;

import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

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
 */
public class Task {

	
	/**
	 * Initialize this new Task with given name and priority.
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
	 */
	public Task(String name, int priority) throws IllegalArgumentException {
		if (! canHaveAsName(name))
			throw new IllegalArgumentException();
		this.name = name;
		this.setPriority(priority);
	}
	
	/* Activities */
	
	//TODO the association with activities unidirectional
	
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

}
