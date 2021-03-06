package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.VariableNotAssignedException;
import hillbillies.model.World;
import hillbillies.model.WrongTypeException;

public abstract class Statement {
	
	private Statement next;
	/**
	 * This field is used to store what Statement should be returned when we
	 * want to walk through every Statement in the Task in a linear fashion.
	 */
	private Statement linearNext;
	private Task task;
	private static final boolean shouldContinueExecution = true;
	
	public Task getTask () {
		return this.task;
	}
	
	public void setTask (Task task) {
		this.task = task;
	}

	public Unit getUnit () {
		return this.getTask().getUnit();
	}
	
	public World getWorld () {
		return this.getTask().getUnit().getWorld();
	}
	
	/**
	 * Sets the next field of this Statement
	 */
	public void setNext (Statement next) {
		this.next = next;
	}
	
	public Statement getNext () {
		return this.next;
	}
	
	/**
	 * Sets the linearNext field of this Statement
	 * @param linearNext
	 */
	public void setLinearNext (Statement linearNext) {
		this.linearNext = linearNext;
	}
	
	public Statement getLinearNext () {
		return this.linearNext;
	}
	
	/**
	 * Gives back the next statement to execute. This could be the _next_ field
	 * or any other Statement, even null if you want the program to stop.
	 * @throws VariableNotAssignedException 
	 */
	public Statement getNextStatement () throws WrongTypeException, VariableNotAssignedException {
		return this.next;
	}
	
	/**
	 * Executes this Statement. Sometimes this doesn't do anything.
	 * @throws VariableNotAssignedException 
	 */
	public abstract void execute () throws BadFSMStateException, WrongTypeException, VariableNotAssignedException;
	
	/**
	 * Tells whether the execution of Statements in the Task should be
	 * continued after this method.
	 */
	public boolean shouldContinueExecution () {
		return shouldContinueExecution;
	}
	
	public boolean isVariable(){
		return false;
	}
	
	public abstract boolean isWellTyped ();
}
