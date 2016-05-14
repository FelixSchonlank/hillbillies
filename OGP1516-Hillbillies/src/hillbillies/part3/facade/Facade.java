package hillbillies.part3.facade;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.World.TerrainType;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.part3.TaskFactory;
import hillbillies.part3.facade.IFacade;
import hillbillies.part3.programs.ITaskFactory;
import ogp.framework.util.ModelException;

public class Facade extends hillbillies.part2.facade.Facade implements IFacade {

	
	public ITaskFactory<?, ?, Task> createTaskFactory() {
		return new TaskFactory();
	}

	
	public boolean isWellFormed(Task task) throws ModelException {
		return task.isWelFormed();
	}

	
	public Scheduler getScheduler(Faction faction) throws ModelException {
		return faction.getScheduler();
	}

	
	public void schedule(Scheduler scheduler, Task task) throws ModelException {
		scheduler.addTask(task);
	}

	
	public void replace(Scheduler scheduler, Task original, Task replacement) throws ModelException {
		scheduler.replaceTask(original, replacement);
	}

	
	public boolean areTasksPartOf(Scheduler scheduler, Collection<Task> tasks) throws ModelException {
		return scheduler.hasAsTasks(tasks);
	}

	
	public Iterator<Task> getAllTasksIterator(Scheduler scheduler) throws ModelException {
		return scheduler.taskIterator();
	}

	
	public Set<Scheduler> getSchedulersForTask(Task task) throws ModelException {
		return task.getScheulers();
	}

	
	public Unit getAssignedUnit(Task task) throws ModelException {
		return task.getUnit();
	}

	
	public Task getAssignedTask(Unit unit) throws ModelException {
		return unit.getTask();
	}

	
	public String getName(Task task) throws ModelException {
		return task.getName();
	}

	
	public int getPriority(Task task) throws ModelException {
		return task.getPriority();
	}

}
