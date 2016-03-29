package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Immutable;

public class Coordinate extends VectorI {
	
	public Coordinate(int x, int y, int z) {
		super(x, y, z);
	}

	public Coordinate(int[] array) {
		super(array);
	}

	public Coordinate(Integer x, Integer y, Integer z) {
		super(x, y, z);
	}

	public Coordinate(Integer[] array) {
		super(array);
	}

	/**
	 * Returns a Position representing the center of the cube with this coordinate.
	 * 
	 * @return
	 * 		A coordinate.
	 */
	@Immutable
	public Position toPosition() {
		return new Position(this.getX()+0.5d, this.getY()+0.5d, this.getZ()+0.5d);
	}
	
	/**
	 * Tells whether the prime object represents a cube adjacent to the given one.
	 * Equal cubes are NOT adjacent!
	 * @return
	 * 		true iff the prime object represents a cube adjacent to the given one.
	 * 		Equal cubes are NOT adjacent!
	 */
	public boolean isAdjacentTo(Coordinate other) {
		int dx = other.getX() - this.getX();
		int dy = other.getY() - this.getY();
		int dz = other.getZ() - this.getZ();
		return (dx == -1 || dx == 0 || dx == 1) &&
				(dy == -1 || dy == 0 || dy == 1) &&
				(dz == -1 || dz == 0 || dz == 1) &&
				!(dx == 0 && dy == 0 && dz == 0);
	}
	
	/**
	 * Gives back a Set of Coordinates of all neighbors of the cube that this
	 * Coordinate represents. Existingness of cubes for returned coordinates
	 * is not guaranteed.
	 * @return
	 * 		the set of neighbor coordinates.
	 */
	@Immutable
	public Set<Coordinate> getNeighbors() {
		Set<Coordinate> result = new HashSet<Coordinate>();
		for (int x=-1; x<=1; x++) {
			for (int y=-1; y<=1; y++) {
				for (int z=-1; z<=1; z++) {
					if (!(x==0 && y==0 && z==0)) {
						result.add(new Coordinate(this.getX() + x, this.getY() + y, this.getZ() + z));
					}
				}
			}
		}
		return result;
	}
}
