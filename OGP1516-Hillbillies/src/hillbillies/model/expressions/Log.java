package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Coordinate;
import hillbillies.model.expressions.*;
import hillbillies.model.Position;
import hillbillies.model.World;
import hillbillies.model.World.TerrainType;

public class Log extends Expression<Coordinate>{

	@Override
	public Coordinate evaluate() {
		return getClosestLog(this.getTask().getUnit().getPosition().toCoordinate());
	}

	public Coordinate getClosestLog(Coordinate position) {
		return
				this
				.getTask()
				.getUnit()
				.getWorld()
				.listAllLogs()
				.stream()
				.filter((l) -> this.getTask().getUnit().isReachable(l.getPosition().toCoordinate()))
				.min(
						(l1, l2)
						-> (int) l1.getPosition().distance(l2.getPosition())
						)
				.get()
				.getPosition()
				.toCoordinate();
	}
	
	@Override
	public boolean isWellTyped () {
		return true;
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Coordinate.class;
	}
	
}
