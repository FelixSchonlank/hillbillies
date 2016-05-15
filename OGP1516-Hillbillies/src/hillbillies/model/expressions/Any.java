package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.expressions.*;
import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.Utils;

public class Any extends Expression<Unit> {
		
	@Override
	public Unit evaluate() {
		return 
				this
				.getTask()
				.getUnit()
				.getWorld()
				.listAllFactions()
				.stream()
				.findAny()
				.get()
				.listAllUnits()
				.stream()
				.filter((u) -> this.getTask().getUnit().isReachable(u.getPosition().toCoordinate()))
				.findAny()
				.get();
				
	}
	
	@Override
	public boolean isWellTyped () {
		return true;
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Unit.class;
	}
	
}
