package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @Invar Each faction must have proper units.
 * 		| this.hasProperUnits()
 * @Invar Each faction must have a valid scheduler 
 * 		| this.canHaveAsScheduler(this.getScheduler())
 */
public class Faction {

	/* Unit */
	
	/**
	 * Check whether this faction has the given unit as one of its
	 * units.
	 * @param  unit
	 *         The unit to check.
	 */
	@Basic
	@Raw
	public boolean hasAsUnit(@Raw Unit unit) {
		return units.contains(unit);
	}
	
	/**
	 * Check whether this faction can have the given unit
	 * as one of its units.
	 * 
	 * @param  unit
	 *         The unit to check.
	 * @return True if and only if the given unit is effective
	 *         and that unit is a valid unit for a faction.
	 *       | result ==
	 *       |   (unit != null) &&
	 *       |   Unit.isValidFaction(this)
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit) {
		return (unit != null) && (unit.canHaveAsFaction(this));
	}

	/**
	 * Check whether this faction has proper units attached to it.
	 * 
	 * @return True if and only if this faction can have each of the
	 *         units attached to it as one of its units,
	 *         and if each of these units references this faction as
	 *         the faction to which they are attached.
	 *       | for each unit in Unit:
	 *       |   if (hasAsUnit(unit))
	 *       |     then canHaveAsUnit(unit) &&
	 *       |          (unit.getFaction() == this)
	 */
	public boolean hasProperUnits() {
		for (Unit unit : units) {
			if (!canHaveAsUnit(unit))
				return false;
			if (unit.getFaction() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of units associated with this faction.
	 *
	 * @return  The total number of units collected in this faction.
	 *        | result ==
	 *        |   card({unit:Unit | hasAsUnit({unit)})
	 */
	public int getNbUnits() {
		return units.size();
	}

	/**
	 * Add the given unit to the set of units of this faction.
	 * 
	 * @param  unit
	 *         The unit to be added.
	 * @Pre    The given unit is effective and already references
	 *         this faction.
	 *       | (unit != null) && (unit.getFaction() == this) && this.getNbUnits() < this.MaxNbUnits()
	 * @post   This faction has the given unit as one of its units.
	 *       | new.hasAsUnit(unit)
	 */
	public void addUnit(@Raw Unit unit) {
		assert (unit != null) && (unit.getFaction() == this) && this.getNbUnits() < this.getMaxUnits();
		units.add(unit);
	}

	/**
	 * Remove the given unit from the set of units of this faction.
	 * 
	 * @param  unit
	 *         The unit to be removed.
	 * @Pre    This faction has the given unit as one of
	 *         its units, and the given unit does not
	 *         reference any faction.
	 *       | this.hasAsUnit(unit) &&
	 *       | (unit.getFaction() == null)
	 * @post   This faction no longer has the given unit as
	 *         one of its units.
	 *       | ! new.hasAsUnit(unit)
	 */
	@Raw
	public void removeUnit(Unit unit) {
		assert this.hasAsUnit(unit) && (unit.getFaction() == null);
		units.remove(unit);
	}

	/**
	 * Variable referencing a set collecting all the units
	 * of this faction.
	 * 
	 * @Invar  The referenced set is effective.
	 *       | units != null
	 * @Invar  Each unit registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each unit in units:
	 *       |   ( (unit != null) &&
	 *       |     (! unit.isTerminated()) )
	 */
	private final Set<Unit> units = new HashSet<Unit>();
	
	/**
	 * Gives back all the Units in this Faction.
	 * @return
	 * 		A new (Hash)Set of all the Units in this Faction.
	 * 		That makes this a shallow copy.
	 */
	public Set<Unit> listAllUnits() {
		Set<Unit> result = new HashSet<Unit>();
		for (Unit unit : this.units) {
			result.add(unit);
		}
		return result;
	}
	
	public int getMaxUnits(){
		return Faction.MaxUnits;
	}
	
	/**
	 * Returns the first Unit of this faction
	 * @return
	 */
	public Unit getRandomUnit(){
		for (Unit unit : this.units){
			return unit;
		}
		return null;
	}
	
	/**
	 * Return the list of units of this faction not a copy but the actual list
	 */
	@Basic
	public Set<Unit> getAllUnits(){
		return this.listAllUnits();
	}
	
	/**
	 * A variable referencing the maximum units a faction can have 
	 */
	private static final int MaxUnits = 50;
	
	/* Scheduler */
	
	/**
	 * Check whether this faction can have a given scheduler as its scheduler 
	 * @param scheduler
	 * 		| the scheduler to check
	 * @return true if and only iff the given scheduler has this Faction as its Faction
	 * 		| result == (scheduler.getFaction() == this)
	 */
	public boolean canHaveAsScheduler(Scheduler scheduler){
		return  scheduler.getFaction() == this;
	}
	
	/**
	 * Set the scheduler of this unit to a given scheduler
	 * @param scheduler
	 * 		| the scheduler to set the scheduler to
	 * @post the scheduler of this Faction is set to the given scheduler 
	 * 		| this.getScheduler() == scheduler
	 * @throws IllegalArgumentException if this Faction can not have the given 
	 * 		Scheduler as its scheduler
	 * 		| !this.canHaveAsScheduler(scheduler)
	 */
	public void setScheduler(Scheduler scheduler) throws IllegalArgumentException{
		if (!this.canHaveAsScheduler(scheduler)){
			throw new IllegalArgumentException();
		}
		this.scheduler = scheduler;
	}
	
	/**
	 * return the scheduler of this Faction
	 */
	public Scheduler getScheduler(){
		return this.scheduler;
	}
	
	/**
	 * A variable referencing the scheduler of this faction 
	 */
	public Scheduler scheduler;
}
