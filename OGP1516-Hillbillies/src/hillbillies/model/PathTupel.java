package hillbillies.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class PathTupel {
	
	/**
	 * Constructor initializing a PathTupel with a weight and a cube
	 * @param cube
	 * @param weight
	 */
	PathTupel(Coordinate cube,int weight){
		this.cube = cube;
		this.weight = weight;
	}
	
	/**
	 * Return the cube in a given path with the smallest weight 
	 * @param current
	 * @param path
	 * @param world
	 * @return
	 */
	public Coordinate getSmallestAjacentWeight(Coordinate current, Queue<PathTupel> path, World world){
		Set<Coordinate> neighbours = world.getNeighbors(current);
		LinkedList<PathTupel> neighboursInPath = new LinkedList<PathTupel>();
		for (Coordinate cube: neighbours){
			for (PathTupel block: path){
				if (block.getCube() == cube){
					neighboursInPath.add(block);
				}
			}
		}
		return smallestWeight(neighboursInPath).getCube();
	}
	
	/**
	 * Return the smallest weight of a List of ParhTupels
	 * @param elements
	 * @return
	 */
	private PathTupel smallestWeight(LinkedList<PathTupel> elements) {
		PathTupel smallest = elements.getFirst();
		for(PathTupel element: elements){
			if (element.getWeight() < smallest.getWeight()){
				smallest = element;
			}
		}
		return smallest;
	}

	/**
	 * Return the weight of this PathTupel
	 */
	public int getWeight(){
		return this.weight;
	}
	
	/**
	 * Return the cube of this PathTupel
	 */
	public Coordinate getCube(){
		return this.cube;
	}
	
	private final int weight;
	private final Coordinate cube;
}
