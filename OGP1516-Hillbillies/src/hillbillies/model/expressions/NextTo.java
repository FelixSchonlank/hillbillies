package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Coordinate;
import hillbillies.model.expressions.*;
import hillbillies.model.Position;
import hillbillies.model.VariableNotAssignedException;
import hillbillies.model.WrongTypeException;

public class NextTo extends Expression<Coordinate>{
		
	/**
	 * Initialize this new NextToPosition with given Position.
	 * 
	 * @param  position
	 *         The Position for this new NextToPosition.
	 * @Pre    The given Position must be a valid Position for any NextToPosition.
	 *       | isValidPosition(Position)
	 * @post   The Position of this new NextToPosition is equal to the given
	 *         Position.
	 *       | new.getPosition() == position
	 */
	public NextTo(Expression<Coordinate> position) {
		this.setPosition(position);
	}
	
	/**
	 * Return the Position of this NextToPosition.
	 */
	@Basic @Raw
	public Expression<Coordinate> getPosition() {
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
	public static boolean isValidPosition(Expression<Coordinate> position) {
		return true;
	}
	
	/**
	 * Set the Position of this NextToPosition to the given Position.
	 * 
	 * @param  position
	 *         The new Position for this NextToPosition.
	 * @Pre    The given Position must be a valid Position for any
	 *         NextToPosition.
	 *       | isValidPosition(position)
	 * @post   The Position of this NextToPosition is equal to the given
	 *         Position.
	 *       | new.getPosition() == position
	 */
	@Raw
	public void setPosition(Expression<Coordinate> position) {
		assert isValidPosition(position);
		this.position = position;
	}
	
	/**
	 * Variable registering the Position of this NextToPosition.
	 */
	private Expression<Coordinate> position;

	@Override
	public Coordinate evaluate() throws IllegalArgumentException, WrongTypeException, VariableNotAssignedException{
		return this.getTask().getUnit().getWorld().getNeighbors(this.getPosition().evaluate()).stream()
		.filter((Coordinate c) -> this.getTask().getUnit().isReachable(c)).findAny().get();
	}

	@Override
	public boolean isWellTyped () {
		return this.getPosition().isWellTyped();
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Coordinate.class;
	}
	
}
