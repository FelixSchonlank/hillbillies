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

/* Unit */

/**
 * return the unit that is carrying this Item
 */
public Unit getUnit(){
	return this.unit;
}

/**
 * Set the unit that is carrying this Item to a given Unit
 */
public void setUnit(Unit unit){
	this.unit = unit;
}

/**
 * check whether the unit is being carried 
 */
public boolean hasUnit(){
	return this.getUnit() != null;
}

/**
 * a variable referencing the Unit that is carrying this Item
 */
private Unit unit;



/* Terminating */

/**
 * Terminate this Item
 * 
 * @Post this Item is terminated
 * @Post The Item is no longer part f any World and is no longer carried by a Unit
 * @Post If the Item had a World or Unit the World no longer has this Item as Its Item or the Unit no longer has an Item 
 */
public void terminate(){
	if(! isTerminated()){
		if (this.hasWorld()){
			this.getWorld().removeItem(this);
		}else if (this.hasUnit()){
			//TODO the assotiation for unit
		}
		this.isTerminated = true;
	}
}

/**
 * check whether this Item is terminated
 */
public boolean isTerminated(){
	return this.isTerminated;
}

/**
 * Variable registering whether this Item is terminated
 */
private boolean isTerminated;

public void advanceTime(double dt) throws IllegalArgumentException{
	
}

}
