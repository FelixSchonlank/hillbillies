package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class PositionOf extends Expression<Position>{

	

	/**
	 * Initialize this new PositionOf with given Unit.
	 * 
	 * @param  unit
	 *         The Unit for this new PositionOf.
	 * @Pre    The given Unit must be a valid Unit for any PositionOf.
	 *       | isValidUnit(Unit)
	 * @post   The Unit of this new PositionOf is equal to the given
	 *         Unit.
	 *       | new.getUnit() == unit
	 */
	public PositionOf(Expression<Unit> unit) {
		this.setUnit(unit);
	}
	
	/**
	 * Return the Unit of this PositionOf.
	 */
	@Basic @Raw
	public Expression<Unit> getUnit() {
		return this.unit;
	}
	
	/**
	 * Check whether the given Unit is a valid Unit for
	 * any PositionOf.
	 *  
	 * @param  Unit
	 *         The Unit to check.
	 * @return 
	 *       | result == true	
	*/
	public static boolean isValidUnit(Expression<Unit> unit) {
		return false;
	}
	
	/**
	 * Set the Unit of this PositionOf to the given Unit.
	 * 
	 * @param  unit
	 *         The new Unit for this PositionOf.
	 * @Pre    The given Unit must be a valid Unit for any
	 *         PositionOf.
	 *       | isValidUnit(unit)
	 * @post   The Unit of this PositionOf is equal to the given
	 *         Unit.
	 *       | new.getUnit() == unit
	 */
	@Raw
	public void setUnit(Expression<Unit> unit) {
		assert isValidUnit(unit);
		this.unit = unit;
	}
	
	/**
	 * Variable registering the Unit of this PositionOf.
	 */
	private Expression<Unit> unit;
		
	@Override
	public Position evaluate() {
		return this.getUnit().evaluate().getPosition();
	}
	
	
	
}
