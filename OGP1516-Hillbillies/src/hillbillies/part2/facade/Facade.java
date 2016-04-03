package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.State;
import hillbillies.model.Unit;
import hillbillies.model.Utils;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		return new Unit(name, initialPosition, weight, agility, strength, toughness, enableDefaultBehavior);
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		return Utils.unboxArray(unit.getPosition().toArray());
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		return Utils.unboxArray(unit.getPosition().toCoordinate().toArray());
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try{
			unit.setName(newName);
		}catch(IllegalArgumentException w){
			throw new ModelException();
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		try{
			unit.setWeight(newValue);
		}catch(IllegalArgumentException w){
			throw new ModelException();
		}
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		try{
			unit.setStrength(newValue);
		}catch(IllegalArgumentException w){
			throw new ModelException();
		}
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		try {
			unit.setAgility(newValue);
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		try{
			unit.setToughness(newValue);
		}catch(IllegalArgumentException w){
			throw new ModelException();
		}
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return unit.getMaxHP();
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return unit.getHP();
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return unit.getMaxStamina();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return unit.getStamina();
	}

	@Override @Deprecated
	public void advanceTime(Unit unit, double dt) throws ModelException {
		unit.advanceTime(dt);
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		try {
			unit.moveToAdjacent(dx, dy, dz);
		} catch (IllegalArgumentException | BadFSMStateException e) {
			throw new ModelException();
		}	
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		if (unit.getState() == State.FALLING){
			return 3;
		}else{
			try{
				return unit.determineVelocity();
			}catch(IllegalArgumentException w){
				throw new ModelException();
			}
		}
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		return unit.isMoving();
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		
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
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

}
