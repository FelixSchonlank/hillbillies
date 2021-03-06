package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.VariableNotAssignedException;
import hillbillies.model.expressions.*;

public class ReadVariable extends Expression<Object>{
	
	@Override
	public boolean isVariable(){
		return true;
	}
	
	/**
	 * Initialize this new ReadVarriable with given name.
	 * 
	 * @param  name
	 *         The name for this new ReadVarriable.
	 * @Pre    The given name must be a valid name for any ReadVarriable.
	 *       | isValidName(name)
	 * @post   The name of this new ReadVarriable is equal to the given
	 *         name.
	 *       | new.getName() == name
	 */
	public ReadVariable(String name) {
		this.setName(name);
	}
	
	/**
	 * Return the name of this ReadVarriable.
	 */
	@Basic @Raw
	public String getName() {
		return this.name;
	}
	
	/**
	 * Check whether the given name is a valid name for
	 * any ReadVarriable.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return 
	 *       | result == true	
	*/
	public static boolean isValidName(String name) {
		return true;
	}
	
	/**
	 * Set the name of this ReadVarriable to the given name.
	 * 
	 * @param  name
	 *         The new name for this ReadVarriable.
	 * @Pre    The given name must be a valid name for any
	 *         ReadVarriable.
	 *       | isValidName(name)
	 * @post   The name of this ReadVarriable is equal to the given
	 *         name.
	 *       | new.getName() == name
	 */
	@Raw
	public void setName(String name) {
		assert isValidName(name);
		this.name = name;
	}
	
	/**
	 * Variable registering the name of this ReadVarriable.
	 */
	private String name;
	
	@Override
	public  Object evaluate() throws VariableNotAssignedException {
		return this.getTask().getValue(this.getName());
	}
	
	@Override
	public boolean isWellTyped () {
		return true;
	}
	
	@Override
	public Class<?> getReturningClass () {
		return Object.class;
	}

}
