package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Coordinate;
import hillbillies.model.expressions.*;
import hillbillies.model.Position;
import hillbillies.model.World;
import hillbillies.model.World.TerrainType;

public class Boulder extends Expression<Coordinate>{
	
	@Override
	public Coordinate evaluate() {
		return getClosestBoulder(this.getTask().getUnit().getPosition());
	}

	public Coordinate getClosestBoulder(Position position) {
		return 
				this
				.getTask()
				.getUnit()
				.getWorld()
				.listAllBoulders()
				.stream()
				.min(
						(b1, b2)
						-> (int) b1.getPosition().distance(b2.getPosition())
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
