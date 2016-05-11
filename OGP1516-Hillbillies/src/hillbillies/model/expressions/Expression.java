package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Task;

/**
* @Invar  The Task of each Expression must be a valid Task for any
*         Expression.
*       | isValidTask(getTask())
*/
public abstract class Expression<T> {
	
	/**
	 * Return the Task of this Expression.
	 */
	@Basic @Raw
	public Task getTask() {
		return this.task;
	}
	
	/**
	 * Check whether the given Task is a valid Task for
	 * any Expression.
	 *  
	 * @param  Task
	 *         The Task to check.
	 * @return true always
	 *       | result == true
	*/
	public static boolean isValidTask(Task task) {
		return true;
	}
	
	/**
	 * Set the Task of this Expression to the given Task.
	 * 
	 * @param  task
	 *         The new Task for this Expression.
	 * @post   The Task of this new Expression is equal to
	 *         the given Task.
	 *       | new.getTask() == task
	 * @throws IllegalAgumentException
	 *         The given Task is not a valid Task for any
	 *         Expression.
	 *       | ! isValidTask(getTask())
	 */
	@Raw
	public void setTask(Task task) 
			throws IllegalArgumentException {
		if (! isValidTask(task))
			throw new IllegalArgumentException();
		this.task = task;
	}
	
	/**
	 * Variable registering the Task of this Expression.
	 */
	private Task task;
		
	/**
	 * Return a Position, boolean or Unit depending on the type of Expression
	 */
	public abstract T evaluate();
		
	public boolean isVarriable(){
		return false;
	}
}
