package hillbillies.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class PathTuple {
	
	/**
	 * Constructor initializing a PathTupel with a weight and a cube
	 * @param cube
	 * @param weight
	 */
	PathTuple(Coordinate cube,int weight){
		this.cube = cube;
		this.weight = weight;
		this.hasBeenChecked = false;
	}
	
	/**
	 * Return the cube in a given path with the smallest weight 
	 * @param current
	 * @param path
	 * @param world
	 * @return
	 */
	public static Coordinate getSmallestAdjacentWeight(Coordinate current, Queue<PathTuple> path, World world){
		Set<Coordinate> neighbours = world.getNeighbors(current);
		LinkedList<PathTuple> neighboursInPath = new LinkedList<PathTuple>();
		for (Coordinate cube: neighbours){
			if (Contains(cube, path)){
				neighboursInPath.add(getPathTuple(cube, path));
			}
		}
		return smallestWeight(neighboursInPath).getCube();
	}
	
	/**
	 * Return the smallest weight of a List of ParhTupels
	 * @param elements
	 * @return
	 */
	private static PathTuple smallestWeight(LinkedList<PathTuple> elements) {
		if (! elements.isEmpty()){
			PathTuple smallest = elements.getFirst();
			for(PathTuple element: elements){
				if (element.getWeight() < smallest.getWeight()){
					smallest = element;
				}
			}
			return smallest;
		}else{
			return null;
		}
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
	
	/**
	 * Check whether there is a next element to check in the pathFindingAlgorithm
	 * @param Path
	 * @return
	 */
	public static boolean hasNext(Queue<PathTuple> Path ){
		if (getNext(Path) == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Return the next tuple to check in the pathFinding Algorithm
	 * @param Path
	 * @return
	 */
	public static PathTuple getNext(Queue<PathTuple> Path){
		LinkedList<PathTuple> Path_NotChecked = new LinkedList<PathTuple>();
		for (PathTuple tuple: Path){
			if (! tuple.hasBeenChecked){
				Path_NotChecked.add(tuple);
			}
		}
		return smallestWeight(Path_NotChecked);
	}
	
	/**
	 * return whether a given Queue of PathTupels contaigns a tuple with a given 
	 * cube as cube
	 * @param cube
	 * @param Q
	 * @return
	 */
 	public static Boolean Contains(Coordinate cube, Queue<PathTuple> Q){
		for (PathTuple tuple: Q){
			if (tuple.getCube().equals(cube)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the pathTuple with a given coordinate in a given Queue
	 * @param cube
	 * @param Q
	 * @return
	 */
	public static PathTuple getPathTuple(Coordinate cube, Queue<PathTuple> Q){
		for (PathTuple tuple: Q){
			if (tuple.getCube().equals(cube)){
				return tuple;
			}
		}
		return null;
	}
	
	public boolean hasBeenChecked;
	private final int weight;
	private final Coordinate cube;
}
