package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Coordinate;
import hillbillies.model.expressions.*;
import hillbillies.model.Position;
import hillbillies.model.World;
import hillbillies.model.World.TerrainType;

public class Workshop extends Expression<Position>{

	@Override
	public Position evaluate() {
		return getClosestWorkShop(this.getTask().getUnit().getPosition());
	}

	public Position getClosestWorkShop(Position position) {
		return
				this
				.getTask()
				.getUnit()
				.getWorld()
				.listAllBoulders()
				.stream()
				.min(
						(w1, w2)
						-> (int) w1.getPosition().distance(w2.getPosition())
						)
				.get()
				.getPosition();
	}
}
