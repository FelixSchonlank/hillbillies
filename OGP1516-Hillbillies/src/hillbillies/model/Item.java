package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**	 
  * @invar  Each Item can have its weight as weight.
  *       | canHaveAsWeight(this.getWeight())
  */
public class Item extends GameObject{
	/**
	 * Initialize a new Item with a given position and a random weight
	 */
	public Item(Position position){
		super(position);
		int weight = random.nextInt(this.getMaxWeight() - this.getMinWeight()) + getMinWeight();
		this.weight = weight;
	}

/* Weight */

/**
 * Return the weight of this Item.
 */
@Basic @Raw @Immutable
public int getWeight() {
	return this.weight;
}

/**
 * Check whether this Item can have the given weight as its weight.
 *  
 * @param  weight
 *         The weight to check.
 * @return 
 *       | result == true 
*/
@Raw
public boolean canHaveAsWeight(int weight) {
	return true;
}

/**
 * return the minimum weight of an Item 
 */
public int getMinWeight(){
	return this.MinWeight;
}

/**
 * Variable referencing the minimum weight for an Item
 */
private int MinWeight = 50;

/**
 * return the maximum weight of an Item 
 */
public int getMaxWeight(){
	return this.MaxWeight;
}

/**
 * Variable referencing the maximum weight for an Item
 */
private int MaxWeight = 100;

/**
 * Variable registering the weight of this Item.
 */
private final int weight;
	
public void advanceTime(double dt) throws IllegalArgumentException{
	
}

}
