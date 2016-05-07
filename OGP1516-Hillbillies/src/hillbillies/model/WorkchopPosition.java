package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

public class WorkchopPosition extends Expression<Position>{

	@Override
	public Position evaluate() {
		return getClosestWorkShop(this.getTask().getUnit().getPosition());
	}

	public Position getClosestWorkShop(Position position) {
		World world = this.getTask().getUnit().getWorld();
		Set<Coordinate> nextCheck = new HashSet<Coordinate>();
		Set<Coordinate> check = world.getNeighbors(position.toCoordinate());
		while(true){
			for (Coordinate element : check){
				if (world.getCubeAt(element) == World.TerrainType.WORKSHOP){
					return element.toPosition();
				}else{
					Set<Coordinate> willem = world.getNeighbors(element);
					willem.removeAll(check);
					nextCheck.addAll(willem);
				}
			}
			check = nextCheck;
			nextCheck.clear();
		}
	}
}
