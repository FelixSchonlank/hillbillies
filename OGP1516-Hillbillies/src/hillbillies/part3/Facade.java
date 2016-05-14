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

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		return new World(terrainTypes, modelListener);
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		return world.getMaxXCoordinate() * world.getMaxYCoordinate() * world.getMaxZCoordinate();
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		return world.getMaxYCoordinate();
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		return world.getMaxZCoordinate();
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		world.advanceTime(dt);
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		Coordinate cube = new Coordinate(x, y, z);
		World.TerrainType terrain = TerrainType.fromInt(value);
		world.setCubeAt(cube, terrain);
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		return world.isSolidConnectedToBorder(new Coordinate(x, y, z));
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		return world.spawnUnit(enableDefaultBehavior);
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		world.addUnit(unit);
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.listAllUnits();
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		return unit.isCarryingLog();
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		return unit.isCarryingBoulder();
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		return ! unit.isTerminated();
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		return (int) unit.getXP();
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		try {
			unit.work(new Coordinate(x, y, z));
		} catch (BadFSMStateException e) {
			throw new ModelException();
		}
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		return faction.listAllUnits();
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		return world.getFactions();
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		Double[] pos = boulder.getPosition().toArray();
		double[] result = new double[3];
		result[0] = pos[0];
		result[1] = pos[1];
		result[2] = pos[2];
		return result;
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		return world.listAllBoulders();
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		Double[] pos = log.getPosition().toArray();
		double[] result = new double[3];
		result[0] = pos[0];
		result[1] = pos[1];
		result[2] = pos[2];
		return result;
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		return world.listAllLogs();
	}

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		Double[] pos = unit.getPosition().toArray();
		double[] result = new double[3];
		result[0] = pos[0];
		result[1] = pos[1];
		result[2] = pos[2];
		return result;
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		//TODO
		return null; 
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public void work(Unit unit) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITaskFactory<?, ?, Task> createTaskFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWellFormed(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Scheduler getScheduler(Faction faction) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void schedule(Scheduler scheduler, Task task) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public void replace(Scheduler scheduler, Task original, Task replacement) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean areTasksPartOf(Scheduler scheduler, Collection<Task> tasks) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Task> getAllTasksIterator(Scheduler scheduler) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Scheduler> getSchedulersForTask(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unit getAssignedUnit(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task getAssignedTask(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPriority(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

}
