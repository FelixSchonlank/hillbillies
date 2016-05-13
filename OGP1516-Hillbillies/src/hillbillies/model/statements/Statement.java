package hillbillies.model.statements;

import hillbillies.model.BadFSMStateException;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.WrongTypeException;

public abstract class Statement {
	
	private Statement next;
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
	
	protected Statement getNext () {
		return this.next;
	}
	
	/**
	 * Gives back the next statement to execute. This could be the _next_ field
	 * or any other Statement, even null if you want the program to stop.
	 */
	public Statement getNextStatement () throws WrongTypeException {
		return this.next;
	}
	
	/**
	 * Executes this Statement. Sometimes this doesn't do anything.
	 */
	public abstract void execute () throws BadFSMStateException, WrongTypeException;
	
	/**
	 * Tells whether the execution of Statements in the Task should be
	 * continued after this method.
	 */
	public boolean shouldContinueExecution () {
		return shouldContinueExecution;
	}
	

	public boolean isVarriable(){
		return false;
	}
}
