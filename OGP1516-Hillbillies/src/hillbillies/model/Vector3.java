package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A value class to represent vectors of dimension 3
 * @Invar
 * 		All three components are valid
 * 		|    isValidX(this.getX())
 * 		| && isValidY(this.getY())
 * 		| && isValidZ(this.getZ())
 */
@Value
public class Vector3<T> {
	
private final T x, y, z;
	
	/**
	 * The obvious constructor.
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3(T x, T y, T z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Constructs a Vector3 out of an array of three doubles
	 */
	public Vector3(T[] array) throws IllegalArgumentException {
		if (array.length != 3) {
			throw new IllegalArgumentException("Not the right array length (3): " + array.length);
		}
		this.x = array[0];
		this.y = array[1];
		this.z = array[2];
	}

	/**
	 * Returns this Vector3's x component
	 * @return
	 * 		The x component
	 */
	@Basic @Immutable
	public T getX() {
		return this.x;
	}

	/**
	 * Tells whether the given x component is valid
	 * @param x
	 * @return
	 * 		True
	 */
	@Immutable
	public boolean isValidX(T x) {
		return true;
	}

	/**
	 * Returns this Vector3's y component
	 * @return
	 * 		The y component
	 */
	@Basic @Immutable
	public T getY() {
		return this.y;
	}

	/**
	 * Tells whether the given y component is valid
	 * @param y
	 * @return
	 * 		True
	 */
	@Immutable
	public boolean isValidY(T y) {
		return true;
	}

	/**
	 * Returns this Vector3's z component
	 * @return
	 * 		The z component
	 */
	@Basic @Immutable
	public T getZ() {
		return this.z;
	}

	/**
	 * Tells whether the given z component is valid
	 * @param z
	 * @return
	 * 		True
	 */
	@Immutable
	public boolean isValidZ(T z) {
		return true;
	}
	
	/**
	 * Tells whether the prime and given object are to be considered equal.
	 * 
	 * @return
	 * 		True iff the given object is effective, of the same type,
	 * 		and has equal x, y, and z components.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		Vector3 otherVector3 = (Vector3) other;
		return (this.getX() == otherVector3.getX()
				&& this.getY() == otherVector3.getY()
				&& this.getZ() == otherVector3.getZ());
	}
	
	/**
	 * Returns a hash code of this
	 * 
	 * @return
	 * 		The integer rounding of the sum of the components' hashCodes.
	 */
	@Override
	public int hashCode() {
		return (int) (this.getX().hashCode() + this.getY().hashCode() + this.getZ().hashCode());
	}
	
	/**
	 * Returns a human-readable representation of this
	 * 
	 * @return
	 * 		The x, y, and z components concatenated.
	 */
	@Override
	public String toString() {
		return "Position(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
	}
}
