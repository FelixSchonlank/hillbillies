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
		this.hasBeenCheckt = false;
	}
	
	/**
	 * Return the cube in a given path with the smallest weight 
	 * @param current
	 * @param path
	 * @param world
	 * @return
	 */
	public static Coordinate getSmallestAjacentWeight(Coordinate current, Queue<PathTuple> path, World world){
		Set<Coordinate> neighbours = world.getNeighbors(current);
		LinkedList<PathTuple> neighboursInPath = new LinkedList<PathTuple>();
		for (Coordinate cube: neighbours){
			for (PathTuple block: path){
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
	private static PathTuple smallestWeight(LinkedList<PathTuple> elements) {
		PathTuple smallest = elements.getFirst();
		for(PathTuple element: elements){
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
		for (PathTuple tuple: Path){
			if (! tuple.hasBeenCheckt){
				return tuple;
			}
		}
		return null;
	}
	
	/**
	 * return whether a given Queue of PathTupels contaigns a tuple with a given 
	 * cube as cube
	 * @param cube
	 * @param Q
	 * @return
	 */
 	public static Boolean Contaigns(Coordinate cube, Queue<PathTuple> Q){
		for (PathTuple tuple: Q){
			if (tuple.getCube() == cube){
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
			if (tuple.getCube() == cube){
				return tuple;
			}
		}
		return null;
	}
	
	public boolean hasBeenCheckt;
	private final int weight;
	private final Coordinate cube;
}
