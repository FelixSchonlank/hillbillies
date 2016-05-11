package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;

public abstract class Statement {
	
	protected Statement next;
	private Task task;
	
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
	public Statement getNextStatement () {
		return this.next;
	}
	
	/**
	 * Executes this Statement. Sometimes this doesn't do anything.
	 * @return
	 * 		True iff execution should continue after this Statement.
	 */
	public boolean execute () {
		return true;
	}
	
}
