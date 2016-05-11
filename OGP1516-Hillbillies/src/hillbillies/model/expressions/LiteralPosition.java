package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Expression;
import hillbillies.model.Position;

public class LiteralPosition extends Expression<Position> {

	
	/**
	 * Initialize this new LiteralPosition with given X.
	 * 
	 * @param  X
	 *         The X for this new LiteralPosition.
	 * @post   If the given X is a valid X for any LiteralPosition,
	 *         the X of this new LiteralPosition is equal to the given
	 *         X. Otherwise, the X of this new LiteralPosition is equal
	 *         to 1.
	 *       | if (isValidX(X))
	 *       |   then new.getX() == X
	 *       |   else new.getX() == 1
	 */
	public LiteralPosition(int X, int Y, int Z) {
		this.setX(X);
		this.setY(Y);
		this.setZ(Z);
	}
	
	/* X */
	
	/**
	 * Return the X of this LiteralPosition.
	 */
	@Basic @Raw
	public int getX() {
		return this.X;
	}
	
	/**
	 * Check whether the given X is a valid X for
	 * any LiteralPosition.
	 *  
	 * @param  X
	 *         The X to check.
	 * @return 
	 *       | result == true	
	*/
	public static boolean isValidX(int X) {
		return false;
	}
	
	/**
	 * Set the X of this LiteralPosition to the given X.
	 * 
	 * @param  X
	 *         The new X for this LiteralPosition.
	 * @post   If the given X is a valid X for any LiteralPosition,
	 *         the X of this new LiteralPosition is equal to the given
	 *         X.
	 *       | if (isValidX(X))
	 *       |   then new.getX() == X
	 */
	@Raw
	public void setX(int X) {
		if (isValidX(X))
			this.X = X;
	}
	
	/**
	 * Variable registering the X of this LiteralPosition.
	 */
	private int X;

	/* Y */
	
	public int getY(){
		return this.Y;
	}
	
	/**
	 * Check whether the given X is a valid X for
	 * any LiteralPosition.
	 *  
	 * @param  X
	 *         The X to check.
	 * @return 
	 *       | result == true	
	*/
	public static boolean isValidY(int X) {
		return false;
	}
	
	/**
	 * Set the X of this LiteralPosition to the given X.
	 * 
	 * @param  X
	 *         The new X for this LiteralPosition.
	 * @post   If the given X is a valid X for any LiteralPosition,
	 *         the X of this new LiteralPosition is equal to the given
	 *         X.
	 *       | if (isValidX(X))
	 *       |   then new.getX() == X
	 */
	@Raw
	public void setY(int X) {
		if (isValidY(X))
			this.Y = X;
	}
	
	/**
	 * Variable registering the X of this LiteralPosition.
	 */
	private int Y;
	
	/* Z */
	
	/**
	 * Return the X of this LiteralPosition.
	 */
	@Basic @Raw
	public int getZ() {
		return this.Z;
	}
	
	/**
	 * Check whether the given X is a valid X for
	 * any LiteralPosition.
	 *  
	 * @param  X
	 *         The X to check.
	 * @return 
	 *       | result == true	
	*/
	public static boolean isValidZ(int X) {
		return false;
	}
	
	/**
	 * Set the X of this LiteralPosition to the given X.
	 * 
	 * @param  X
	 *         The new X for this LiteralPosition.
	 * @post   If the given X is a valid X for any LiteralPosition,
	 *         the X of this new LiteralPosition is equal to the given
	 *         X.
	 *       | if (isValidX(X))
	 *       |   then new.getX() == X
	 */
	@Raw
	public void setZ(int X) {
		if (isValidZ(X))
			this.Z = X;
	}
	
	/**
	 * Variable registering the X of this LiteralPosition.
	 */
	private int Z;
	
	/* Evaluate */
	
	@Override
	public Position evaluate() {
		return new Position(this.getX(), this.getY(), this.getZ());
	}

}
