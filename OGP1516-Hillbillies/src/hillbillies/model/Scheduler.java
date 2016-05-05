package hillbillies.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/** 
 * @Invar  The faction of each faction must be a valid faction for any
 *         faction.
 *       | isValidFaction(getFaction())
 */
public class Scheduler {
	
	/* Tasks */ 
	
	/** 
	 * Check whether this scheduler has the given task as one of its
	 * tasks.
	 * @param  task
	 *         The task to check.
	 */
	@Basic
	@Raw
	public boolean hasAsTask(@Raw Task task) {
		return tasks.contains(task) || scheduledTasks.contains(task);
	}
	
	/**
	 * Check whether this scheduler can have the given task
	 * as one of its tasks.
	 * 
	 * @param  task
	 *         The task to check.
	 * @return true if and only if the given task is effective
	 * 		| result == task != null
	 */
	@Raw
	public boolean canHaveAsTask(Task task) {
		return task != null;
	}

	/**
	 * Check whether this scheduler has proper tasks attached to it.
	 * 
	 * @return True if and only if this scheduler can have each of the
	 *         tasks attached to it as one of its tasks
	 *       | for each task in tasks:
	 *       |   if (hasAsTask(task))
	 *       |     then canHaveAsTask(task) 
	 */
	public boolean hasProperTasks() {
		for (Task task : tasks) {
			if (!canHaveAsTask(task))
				return false;
		}
		return true;
	}

	/**
	 * Return the number of tasks associated with this faction.
	 *
	 * @return  The total number of tasks collected in this faction.
	 *        | result == this.tasks.size() + this.scheduledTasks.size()
	 */
	public int getNbTasks() {
		return tasks.size() + scheduledTasks.size();
	}

	/**
	 * Add the given task to the set of tasks of this scheduler.
	 * 
	 * @param  task
	 *         The task to be added.
	 * @post   This scheduler has the given task as one of its tasks.
	 *       | new.hasAsTask(task)
	 *       | task.hasAsScheduler(this)
	 */
	public void addTask(@Raw Task task) {
		tasks.add(task);
		task.addScheduler(this);
	}

	/**
	 * Remove the given task from the set of tasks of this scheduler.
	 * 
	 * @param  task
	 *         The task to be removed.
	 * @post   if the given task is one of the tasks of this scheduler, this 
	 * 		scheduler no longer has the given task as one of its tasks.
	 *       | ! new.hasAsUnit(unit)
	 * @post if this task is not a task of this scheduler the nothing changes
	 */
	@Raw
	public void removeTask(Task task) {
		tasks.remove(task);
		scheduledTasks.remove(task);
		task.removeScheduler(this);
	}

	/**
	 * Variable referencing a priorityQueue collecting all the tasks
	 * of this scheduler that are not scheduled.
	 * 
	 * @Invar  The referenced priorityQueue is effective.
	 *       | this.tasks != null
	 * @Invar  Each task registered in the referenced queue is
	 *         effective and not yet scheduled.
	 *       | for each task in tasks:
	 *       |   ( (task != null) &&
	 *       |     (! task.hasUnit()) )
	 */
	private final PriorityQueue<Task> tasks = new PriorityQueue<Task>();
	
	/**
	 * Variable referencing a list collecting all the tasks
	 * of this scheduler that are scheduled.
	 * 
	 * @Invar  The referenced list is effective.
	 *       | this.tasks != null
	 * @Invar  Each task registered in the referenced list is
	 *         effective and scheduled.
	 *       | for each task in scheduledTasks:
	 *       |   ( (task != null) &&
	 *       |     (task.hasUnit()) )
	 */
	private final LinkedList<Task> scheduledTasks = new LinkedList<Task>();
	
	/**
	 * Gives back all the tasks in this scheduler.
	 * @return
	 * 		A new (Hash)Set of all the tasks in this Faction.
	 * 		That makes this a shallow copy.
	 */
	public Set<Task> listAllTasks() {
		Set<Task> result = new HashSet<Task>();
		for (Task task : this.tasks) {
			result.add(task);
		}
		for (Task task : this.scheduledTasks){
			result.add(task);
		}
		return result;
	}
	
	public int getMinTaks(){
		return Scheduler.MinTasks;
	}
	
	/**
	 * A variable referencing the maximum units a faction can have 
	 */
	private static final int MinTasks = 1;
	
	/* faction */
		
	/**
	 * Initialize this new Scheduler with given faction.
	 *
	 * @param  faction
	 *         The faction for this new faction.
	 * @effect The faction of this new faction is set to
	 *         the given faction.
	 *       | this.setFaction(faction)
	 */
	public Scheduler(Faction faction)
			throws IllegalArgumentException {
		this.setFaction(faction);
	}
	
	/**
	 * Return the faction of this faction.
	 */
	@Basic @Raw
	public Faction getFaction() {
		return this.faction;
	}
	
	/**
	 * Check whether the given faction is a valid faction for
	 * any faction.
	 *  
	 * @param  faction
	 *         The faction to check.
	 * @return 
	 *       | result == (faction != null)
	*/
	public static boolean isValidFaction(Faction faction) {
		return faction != null;
	}
	
	/**
	 * Set the faction of this faction to the given faction.
	 * 
	 * @param  faction
	 *         The new faction for this faction.
	 * @post   The faction of this new faction is equal to
	 *         the given faction.
	 *       | new.getFaction() == faction
	 * @throws IllegalArgumentException
	 *         The given faction is not a valid faction for any
	 *         faction.
	 *       | ! isValidFaction(getFaction())
	 */
	@Raw
	public void setFaction(Faction faction) 
			throws IllegalArgumentException {
		if (! isValidFaction(faction))
			throw new IllegalArgumentException();
		this.faction = faction;
		faction.setScheduler(this);
	}
	
	/**
	 * Variable registering the faction of this faction.
	 */
	private Faction faction;
	
	
		
		
}
