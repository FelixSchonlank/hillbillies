package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Coordinate;
import hillbillies.model.Expression;
import hillbillies.model.Position;
import hillbillies.model.World;
import hillbillies.model.World.TerrainType;

public class Boulder extends Expression<Position>{

	@Override
	public Position evaluate() {
		return getClosestBoulder(this.getTask().getUnit().getPosition());
	}

	public Position getClosestBoulder(Position position) {
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
				.getPosition();
	}
}
