package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class NextToPosition extends Expression<Position>{

		
	/**
	 * Initialize this new NextToPosition with given Position.
	 * 
	 * @param  position
	 *         The Position for this new NextToPosition.
	 * @pre    The given Position must be a valid Position for any NextToPosition.
	 *       | isValidPosition(Position)
	 * @post   The Position of this new NextToPosition is equal to the given
	 *         Position.
	 *       | new.getPosition() == position
	 */
	public NextToPosition(Expression<Position> position) {
		this.setPosition(position);
	}
	
	/**
	 * Return the Position of this NextToPosition.
	 */
	@Basic @Raw
	public Expression<Position> getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given Position is a valid Position for
	 * any NextToPosition.
	 *  
	 * @param  Position
	 *         The Position to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidPosition(Expression<Position> position) {
		return true;
	}
	
	/**
	 * Set the Position of this NextToPosition to the given Position.
	 * 
	 * @param  position
	 *         The new Position for this NextToPosition.
	 * @pre    The given Position must be a valid Position for any
	 *         NextToPosition.
	 *       | isValidPosition(position)
	 * @post   The Position of this NextToPosition is equal to the given
	 *         Position.
	 *       | new.getPosition() == position
	 */
	@Raw
	public void setPosition(Expression<Position> position) {
		assert isValidPosition(position);
		this.position = position;
	}
	
	/**
	 * Variable registering the Position of this NextToPosition.
	 */
	private Expression<Position> position;

	@Override
	public Position evaluate() {
		for(Coordinate element : this.getTask().getUnit().getWorld().getNeighbors(this.getPosition().evaluate().toCoordinate())){
			return element.toPosition();
		}return null;
	}

	
}
