package hillbillies.part3;

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
import hillbillies.part3.facade.IFacade;
import hillbillies.part3.programs.ITaskFactory;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {

	
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		return new World(terrainTypes, modelListener);
	}

	
	public int getNbCubesX(World world) throws ModelException {
		return world.getMaxXCoordinate() * world.getMaxYCoordinate() * world.getMaxZCoordinate();
	}

	
	public int getNbCubesY(World world) throws ModelException {
		return world.getMaxYCoordinate();
	}

	
	public int getNbCubesZ(World world) throws ModelException {
		return world.getMaxZCoordinate();
	}

	
	public void advanceTime(World world, double dt) throws ModelException {
		world.advanceTime(dt);
	}

	
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		Coordinate cube = new Coordinate(x, y, z);
		World.TerrainType terrain = TerrainType.fromInt(value);
		world.setCubeAt(cube, terrain);
	}

	
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		return world.isSolidConnectedToBorder(new Coordinate(x, y, z));
	}

	
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		return world.spawnUnit(enableDefaultBehavior);
	}

	
	public void addUnit(Unit unit, World world) throws ModelException {
		world.addUnit(unit);
	}

	
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.listAllUnits();
	}

	
	public boolean isCarryingLog(Unit unit) throws ModelException {
		return unit.isCarryingLog();
	}

	
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		return unit.isCarryingBoulder();
	}

	
	public boolean isAlive(Unit unit) throws ModelException {
		return ! unit.isTerminated();
	}

	
	public int getExperiencePoints(Unit unit) throws ModelException {
		return (int) unit.getXP();
	}

	
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		try {
			unit.work(new Coordinate(x, y, z));
		} catch (BadFSMStateException e) {
			throw new ModelException();
		}
	}

	
	public Faction getFaction(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		return faction.listAllUnits();
	}

	
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		return world.getFactions();
	}

	
	public double[] getPosition(Boulder boulder) throws ModelException {
		Double[] pos = boulder.getPosition().toArray();
		double[] result = new double[3];
		result[0] = pos[0];
		result[1] = pos[1];
		result[2] = pos[2];
		return result;
	}

	
	public Set<Boulder> getBoulders(World world) throws ModelException {
		return world.listAllBoulders();
	}

	
	public double[] getPosition(Log log) throws ModelException {
		Double[] pos = log.getPosition().toArray();
		double[] result = new double[3];
		result[0] = pos[0];
		result[1] = pos[1];
		result[2] = pos[2];
		return result;
	}

	
	public Set<Log> getLogs(World world) throws ModelException {
		return world.listAllLogs();
	}

	
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public double[] getPosition(Unit unit) throws ModelException {
		Double[] pos = unit.getPosition().toArray();
		double[] result = new double[3];
		result[0] = pos[0];
		result[1] = pos[1];
		result[2] = pos[2];
		return result;
	}

	
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		//TODO
		return null; 
	}

	
	public String getName(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setName(Unit unit, String newName) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public int getWeight(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setWeight(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public int getStrength(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setStrength(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public int getAgility(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setAgility(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public int getToughness(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setToughness(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public int getMaxHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void advanceTime(Unit unit, double dt) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public double getCurrentSpeed(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean isMoving(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void startSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public void stopSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public boolean isSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public double getOrientation(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public void work(Unit unit) throws ModelException {
		// TODO Auto-generated method stub

	}

	
	public boolean isWorking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void fight(Unit attacker, Unit defender) throws ModelException {
		attacker.attack(defender);
	}

	
	public boolean isAttacking(Unit unit) throws ModelException {
		return unit.isAttacking();
	}

	
	public void rest(Unit unit) throws ModelException {
		try {
			unit.rest();
		} catch (BadFSMStateException e) {
			throw new ModelException(e);
		}
	}

	
	public boolean isResting(Unit unit) throws ModelException {
		return unit.isResting();
	}

	
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		unit.setDefaultBehaviorEnabled(value);
	}

	
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return unit.getDefaultBehaviorEnabled();
	}

	
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
		return task.getName()
	}

	
	public int getPriority(Task task) throws ModelException {
		return task.getPriority();
	}

}
