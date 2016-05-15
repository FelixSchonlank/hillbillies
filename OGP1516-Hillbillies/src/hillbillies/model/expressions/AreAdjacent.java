package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Coordinate;
import hillbillies.model.VariableNotAssignedException;
import hillbillies.model.WrongTypeException;

public class AreAdjacent extends Expression<Boolean>{

	
	/**
	 * Initialize this new AreAjacent with given First.
	 * 
	 * @param  first
	 *         The First for this new AreAjacent.
	 * @pre    The given First must be a valid First for any AreAjacent.
	 *       | isValidFirst(First)
	 * @post   The First of this new AreAjacent is equal to the given
	 *         First.
	 *       | new.getFirst() == first
	 */
	public AreAdjacent(Expression<Coordinate> first, Expression<Coordinate> second) {
		this.setFirst(first);
		this.setSecond(second);
	}
	
	/* Second */
	
	/**
	 * Return the second of this exp.
	 */
	@Basic @Raw
	public Expression<Coordinate> getSecond() {
		return this.second;
	}
	
	/**
	 * Check whether the given second is a valid second for
	 * any exp.
	 *  
	 * @param  second
	 *         The second to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidSecond(Expression<Coordinate> second) {
		return true;
	}
	
	/**
	 * Set the second of this exp to the given second.
	 * 
	 * @param  second
	 *         The new second for this exp.
	 * @pre    The given second must be a valid second for any
	 *         exp.
	 *       | isValidSecond(second)
	 * @post   The second of this exp is equal to the given
	 *         second.
	 *       | new.getSecond() == second
	 */
	@Raw
	public void setSecond(Expression<Coordinate> second) {
		assert isValidSecond(second);
		this.second = second;
	}
	
	/**
	 * Variable registering the second of this exp.
	 */
	private Expression<Coordinate> second;
	
	/* First */
	
	/**
	 * Return the First of this AreAjacent.
	 */
	@Basic @Raw
	public Expression<Coordinate> getFirst() {
		return this.first;
	}
	
	/**
	 * Check whether the given First is a valid First for
	 * any AreAjacent.
	 *  
	 * @param  First
	 *         The First to check.
	 * @return 
	 *       | result == true	
	*/
	public static boolean isValidFirst(Expression<Coordinate> first) {
		return true;
	}
	
	/**
	 * Set the First of this AreAjacent to the given First.
	 * 
	 * @param  first
	 *         The new First for this AreAjacent.
	 * @Pre    The given First must be a valid First for any
	 *         AreAjacent.
	 *       | isValidFirst(first)
	 * @post   The First of this AreAjacent is equal to the given
	 *         First.
	 *       | new.getFirst() == first
	 */
	@Raw
	public void setFirst(Expression<Coordinate> first) {
		assert isValidFirst(first);
		this.first = first;
	}
	
	/**
	 * Variable registering the First of this AreAjacent.
	 */
	private Expression<Coordinate> first;
	
	/* Methods */
	
	@Override
	public Boolean evaluate() throws WrongTypeException, IllegalArgumentException, VariableNotAssignedException {
		return this.getTask().getUnit().getWorld().areAdjacentCubes(this.first.evaluate(), this.second.evaluate());
	}

	@Override
	public boolean isWellTyped() {
		return this.first.isWellTyped() && this.second.isWellTyped();
	}

	@Override
	public Class<?> getReturningClass() {
		return Boolean.class;
	}

}
