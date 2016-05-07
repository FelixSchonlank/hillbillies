package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

public class LogPosition extends Expression<Position>{

	@Override
	public Position evaluate() {
		return getClosestLog(this.getTask().getUnit().getPosition());
	}

	public Position getClosestLog(Position position) {
		World world = this.getTask().getUnit().getWorld();
		Set<Coordinate> nextCheck = new HashSet<Coordinate>();
		Set<Coordinate> check = world.getNeighbors(position.toCoordinate());
		while(true){
			for (Coordinate element : check){
				if (world.getCubeAt(element) == World.TerrainType.TREE){
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
