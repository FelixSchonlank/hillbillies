package hillbillies.model;


import java.util.HashSet;

import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/** 
 * @Invar   Each Task must have proper schedulers.
 *        | hasProperSchedulers()
 */
public class Task {

	/* Schedulers */
	
	/**
	 * Initialize this new Task as a non-terminated Task with 
	 * no scheduler yet.
	 * 
	 * @post   This new Task has no schedulers yet.
	 *       | new.getNbSchedulers() == 0
	 */
	@Raw
	public Task(Scheduler scheduler) throws IllegalArgumentException {
		
	}
	
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
