package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Coordinate;
import hillbillies.model.expressions.*;
import hillbillies.model.Position;
import hillbillies.model.World;
import hillbillies.model.World.TerrainType;

public class Workshop extends Expression<Coordinate>{

	@Override
	public Coordinate evaluate() {
		return getClosestWorkShop(this.getTask().getUnit().getPosition().toCoordinate());
	}

	public Coordinate getClosestWorkShop(Coordinate position) {
		return
				this
				.getTask()
				.getUnit()
				.getWorld()
				.listAllBoulders()
				.stream()
				.filter((w) -> this.getTask().getUnit().isReachable(w.getPosition().toCoordinate()))
				.min(
						(w1, w2)
						-> (int) w1.getPosition().distance(w2.getPosition())
						)
				.get()
				.getPosition().toCoordinate();
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
