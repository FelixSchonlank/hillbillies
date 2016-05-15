package hillbillies.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.expressions.Convert;
import hillbillies.model.expressions.ReadVariable;
import hillbillies.model.statements.*;
import hillbillies.model.*;

/** 
 * @Invar  Each Task can have its name as name.
 *       | canHaveAsName(this.getName())
 * @Invar   Each Task must have proper schedulers.
 *        | hasProperSchedulers()
 * 
 * @Invar  The unit of each Task must be a valid unit for any
 *         Task.
 *       | isValidUnit(getUnit())
 * @Invar  The Priority of each Task must be a valid Priority for any
 *         Task.
 *       | isValidPriority(getPriority())
 * @Invar   the activity of a Task must be a valid activity for any task
 * 		| isValidActivity(activity)
 * @Invar  The Position of each Task must be a valid Position for any
 *         Task.
 *       | isValidPosition(getPosition())
 */
public class Task {

	
	/**
	 * Initialize this new Task with given name, priority and a given activity
	 * 
	 * @param  name
	 *         The name for this new Task.
	 * @post   The name of this new Task is equal to the given
	 *         name.
	 *       | new.getName() == name
	 * @throws IllegalArgumentException
	 *         This new Task cannot have the given name as its name.
	 *       | ! canHaveAsName(this.getName())
	 * @param  priority
	 *         The Priority for this new Task.
	 * @effect The Priority of this new Task is set to
	 *         the given Priority.
	 *       | this.setPriority(priority)
	 * @param  activity
	 *         The activity for this new Task.
	 * @effect The activity of this new Task is set to
	 *         the given activity.
	 *       | this.setActivity(activity) 
	 * @param  position
	 *         The Position for this new Task.
	 * @effect The Position of this new Task is set to
	 *         the given Position.
	 *       | this.setPosition(position)
	 */
	public Task(String name, int priority, Coordinate position, Statement activity) throws IllegalArgumentException {
		if (! canHaveAsName(name))
			throw new IllegalArgumentException();
		this.name = name;
		this.setPriority(priority);
		this.setPosition(position);
		this.setActivity(activity);
		this.getActivity().setNext(null);
		this.getActivity().setLinearNext(null);
		this.setBreaksNextStatement();
		this.current = this.getActivity();
	}
	
	public void execute (int num) {
	    while (num > 0 && this.current != null) {
	    	try {
				this.current.execute();
			} catch (Exception e) {
				this.setPriority(this.getPriority() - 1);
				this.getScheduler().resetTask(this);
			}
	    	if (this.current.shouldContinueExecution()) {
	    		num -= 1;
	    		try {
					this.current = this.current.getNextStatement();
				} catch (Exception e) {
					this.setPriority(this.getPriority() - 1);
					this.getScheduler().resetTask(this);
				} 
	    	}else {
	    		break;
			}
	    }
	    if (this.taskIsDone()) {
    		this.setPriority(this.getPriority() - 1);
			this.getScheduler().resetTask(this);
    	}
	}
	

	/**
	 * Check whether this task is done
	 * @return
	 */
	public boolean taskIsDone () {
	    return this.current == null;
	}
	

	/**
	 * The statement to execute first
	 */
	private Statement current;
	
	/* Variables */
	
	/**
	 * return the value of a given variable name
	 * @param name
	 * 		| The name of the variable
	 * @return the value of name
	 * 		| result == this.variables.get(name)
	 * @throws VarriableNotAssigntException 
	 * 		| if the given name is not found as a key in variables
	 * 		| ! this.variables.containsKey(name)
	 */
	public Object getValue(String name) throws VariableNotAssigntException {
		if (! this.variables.containsKey(name)){
			throw new VariableNotAssigntException();
		}else{
			return this.variables.get(name);
		}
	} 
	
	/**
	 * Add a give name and value of a variable to the map
	 * @param variable
	 * 		| The name of the variable to add
	 * @param value
	 * 		| the value of the variable to add
	 * @post the tuple (variable, object) is now part of the map of variables
	 * 		| this.varibles.get(variable) == value
	 */
	public void addVariable(String variable, Object value){
		this.variables.put(variable, value);
	}
	
	/**
	 * Map with the name (String) of a variable as key and its value (Object) as Value
	 */
	private Map<String, Object> variables;

	/**
	 * Return the activity of this Task.
	 */
	@Basic @Raw
	public Statement getActivity() {
		return this.activity;
	}
	
	/**
	 * Check whether the given activity is a valid activity for
	 * any Task.
	 *  
	 * @param  activity
	 *         The activity to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidActivity(Statement activity) {
		return true;
	}
	
	/**
	 * Set the activity of this Task to the given activity.
	 * 
	 * @param  activity
	 *         The new activity for this Task.
	 * @post   The activity of this new Task is equal to
	 *         the given activity.
	 *       | new.getActivity() == activity
	 * @throws IllegalArgumentException
	 *         The given activity is not a valid activity for any
	 *         Task.
	 *       | ! isValidActivity(getActivity())
	 */
	@Raw
	public void setActivity(Statement activity) 
			throws IllegalArgumentException {
		if (! isValidActivity(activity))
			throw new IllegalArgumentException();
		this.activity = activity;
	}
	
	/**
	 * Variable registering the activity of this Task.
	 */
	private Statement activity;
	
	/* priority */

	/**
	 * Return the Priority of this Task.
	 */
	@Basic @Raw
	public int getPriority() {
		return this.priority;
	}
	
	/**
	 * Check whether the given Priority is a valid Priority for
	 * any Task.
	 *  
	 * @param  Priority
	 *         The Priority to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidPriority(int priority) {
		return true;
	}
	
	/**
	 * Set the Priority of this Task to the given Priority.
	 * 
	 * @param  priority
	 *         The new Priority for this Task.
	 * @post   The Priority of this new Task is equal to
	 *         the given Priority.
	 *       | new.getPriority() == priority
	 * @throws IllegalArgumentException
	 *         The given Priority is not a valid Priority for any
	 *         Task.
	 *       | ! isValidPriority(getPriority())
	 */
	@Raw
	public void setPriority(int priority) 
			throws IllegalArgumentException {
		if (! isValidPriority(priority))
			throw new IllegalArgumentException();
		this.priority = priority;
	}
	
	/**
	 * Variable registering the Priority of this Task.
	 */
	private int priority;
		
	/* name */
	
	/**
	 * Return the name of this Task.
	 */
	@Basic @Raw @Immutable
	public String getName() {
		return this.name;
	}
	
	/**
	 * Check whether this Task can have the given name as its name.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return 
	 *       | result == true				
	*/
	@Raw
	public boolean canHaveAsName(String name) {
		return true;
	}
	
	/**
	 * Variable registering the name of this Task.
	 */
	private final String name;
	
	/* Unit */	
	
	/**
	 * Return the unit of this Task.
	 */
	@Basic @Raw
	public Unit getUnit() {
		return this.unit;
	}
	
	/**
	 * check whether this Task has a unit 
	 * @return true iff the unit of this Task is not null
	 * 		| result == (this.getUnit() != null)
	 */
	public boolean hasUnit(){
		return this.getUnit() != null;
	}
	
	/**
	 * Check whether the given unit is a valid unit for
	 * any Task.
	 *  
	 * @param  unit
	 *         The unit to check.
	 * @return 
	 *       | result == (unit == null || !unit.hasTask() || unit.getTask() == this)
	*/
	public boolean canHaveAsUnit(Unit unit) {
		return (unit == null || !unit.hasTask() || unit.getTask() == this);
	}
	
	/**
	 * Set the unit of this Task to the given unit.
	 * 
	 * @param  unit
	 *         The new unit for this Task.
	 * @post   The unit of this new Task is equal to
	 *         the given unit.
	 *       | new.getUnit() == unit
	 * @effect if the given unit is not null the new task of the give unit is 
	 * 			set to this task
	 * 		| if ( unit != null) then unit.setTask(this)
	 * @throws IllegalArgumentException
	 *         The given unit is not a valid unit for any
	 *         Task.
	 *       | ! isValidUnit(getUnit())
	 */
	@Raw
	public void setUnit(Unit unit) 
			throws IllegalArgumentException {
		if (! canHaveAsUnit(unit))
			throw new IllegalArgumentException();
		this.unit = unit;
		if (unit != null){ 
			unit.setTask(this);
		}	
	}
	
	/**
	 * set the task of this tasks unit to null and the unit of this task to null
	 * @effect If this Task has a Unit the Unit is set to null and the task of 
	 * 			that unit is set to null else nothing changes
	 * 		| if (this.hasUnit()) then
	 * 		| 	this.getUnit().setUnit(null) && this.setUnit(null) 
	 */
	public void removeUnit(){
		this.getUnit().setTask(null);
		this.setUnit(null);
	}
	
	/**
	 * Variable registering the unit of this Task.
	 */
	private Unit unit;
	
	/* Schedulers */
	
	/**
	 * Check whether this Task has the given scheduler as one of its
	 * schedulers.
	 * 
	 * @param  scheduler
	 *         The scheduler to check.
	 */
	@Basic
	@Raw
	public boolean hasAsScheduler(@Raw Scheduler scheduler) {
		return schedulers.contains(scheduler);
	}
	
	/**
	 * Check whether this Task can have the given scheduler
	 * as one of its schedulers.
	 * 
	 * @param  scheduler
	 *         The scheduler to check.
	 * @return True if and only if the given scheduler is effective
	 *         and that scheduler has this Task as one of its tasks
	 *       | result ==
	 *       |   (scheduler != null) &&
	 *       |   Scheduler.hasAsTask(this)
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return (scheduler != null) && (scheduler.hasAsTask(this));
	}
	
	/**
	 * Check whether this Task has proper schedulers attached to it.
	 * 
	 * @return True if and only if this Task can have each of the
	 *         schedulers attached to it as one of its schedulers,
	 *         and if each of these schedulers references this Task as
	 *         one of the tasks to which they are attached.
	 *       | for each scheduler in Scheduler:
	 *       |   if (hasAsScheduler(scheduler))
	 *       |     then canHaveAsScheduler(scheduler) &&
	 *       |          (scheduler.hasAsTask(this))
	 */
	public boolean hasProperSchedulers() {
		for (Scheduler scheduler : schedulers) {
			if (!canHaveAsScheduler(scheduler))
				return false;
			if (!scheduler.hasAsTask(this))
				return false;
		}
		return true;
	}
	
	/**
	 * Return the number of schedulers associated with this Task.
	 *
	 * @return  The total number of schedulers collected in this Task.
	 *        | result ==
	 *        |   card({scheduler:Scheduler | hasAsScheduler({scheduler)})
	 */
	public int getNbSchedulers() {
		return schedulers.size();
	}
	
	/**
	 * Add the given scheduler to the set of schedulers of this Task.
	 * 
	 * @param  scheduler
	 *         The scheduler to be added.
	 * @Pre    The given scheduler is effective and already references
	 *         this Task.
	 *       | (scheduler != null) && (scheduler.getTask() == this)
	 * @post   This Task has the given scheduler as one of its schedulers.
	 *       | new.hasAsScheduler(scheduler)
	 */
	public void addScheduler(@Raw Scheduler scheduler) {
		assert (scheduler != null) && (scheduler.hasAsTask(this));
		schedulers.add(scheduler);
	}
	
	/**
	 * Remove the given scheduler from the set of schedulers of this Task.
	 * 
	 * @param  scheduler
	 *         The scheduler to be removed.
	 * @Pre    This Task has the given scheduler as one of
	 *         its schedulers, and the given scheduler does not
	 *         reference this Task.
	 *       | this.hasAsScheduler(scheduler) &&
	 *       | (scheduler.getTask() == null)
	 * @post   This Task no longer has the given scheduler as
	 *         one of its schedulers.
	 *       | ! new.hasAsScheduler(scheduler)
	 */
	@Raw
	public void removeScheduler(Scheduler scheduler) {
		assert this.hasAsScheduler(scheduler) && (!scheduler.hasAsTask(this));
		schedulers.remove(scheduler);
	}
	
	/**
	 * Variable referencing a set collecting all the schedulers
	 * of this Task.
	 * 
	 * @Invar  The referenced set is effective.
	 *       | schedulers != null
	 * @Invar  Each scheduler registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each scheduler in schedulers:
	 *       |   ( (scheduler != null) &&
	 *       |     (! scheduler.isTerminated()) )
	 */
	private final Set<Scheduler> schedulers = new HashSet<Scheduler>();

	public Set<Scheduler> getScheulers(){
		return this.schedulers;
	}
	
	/**
	 * Return one of the schedulers this task belongs to
	 * @return schedulers
	 */
	public Scheduler getScheduler(){
		for (Scheduler element : this.schedulers){
			return element;
		}return null;
	}
	
	/* Position */
	
	/**
	 * Return the Position of this Task.
	 */
	@Basic @Raw
	public Coordinate getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given Position is a valid Position for
	 * any Task.
	 *  
	 * @param  Position
	 *         The Position to check.
	 * @return 
	 *       | result == true
	*/
	public boolean canHaveAsPosition(Coordinate position) {
		//return this.getScheduler().getFaction().getRandomUnit().getWorld().withinBounds(position);
		return true;
	}
	
	/**
	 * Set the Position of this Task to the given Position.
	 * 
	 * @param  position
	 *         The new Position for this Task.
	 * @post   The Position of this new Task is equal to
	 *         the given Position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given Position is not a valid Position for any
	 *         Task.
	 *       | ! canHaveAsPosition(getPosition())
	 */
	@Raw
	public void setPosition(Coordinate position) 
			throws IllegalArgumentException {
		if (! canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable registering the Position of this Task.
	 */
	private Coordinate position;
		
	/* isWelFormed */
	
	/**
	 * Check whether this task is well formed
	 * @return true iff every activity of this task is well formed
	 * 		| result == checkSequence(false, (Sequence) this.activity, new Set<String>())
	 */
	public boolean isWelFormed(){
		Set<String> assignments = new HashSet<String>();
		return checkSequence(false, (Sequence) this.activity, assignments) && this.isWellTyped();
	}

	/**
	 * Check whether a given sequence is well formed
	 * @param inBodyOfWhile
	 * 		| a boolean that returns true iff the given sequence is found in a 
	 * 		| while loop
	 * @param Sequence
	 * 		| The sequence to check
	 * @param statements
	 * 		| A set containing all the variables that have been assigned before 
	 * 		| this sequence
	 * @return true if every statement in the body of the given sequence are well formed
	 * 		| for every statement in sequence.getBody()
	 * 		| 		if (! checkStatement(inBodyOfWhile, statement, statements))
	 * 		| 			result == false
	 * 		| else result == true
	 */
	private static boolean checkSequence(boolean inBodyOfWhile, Sequence Sequence, Set<String> statements){
		for(Statement statement : Sequence.getBody() ){
			if (! checkStatement(inBodyOfWhile, statement, statements)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check whether a given statement is properly formed
	 *  
	 * @param inBodyOfWhile
	 * 		| A boolean that is true iff the given statement is in a while
	 * @param statement
	 * 		|The statement to check
	 * @param assignments
	 * 		| A set containing all the variables that have been assignments 
	 * 		| before this point in the program
	 * @return True iff every ReadVariable in that is a statement of Attack, 
	 * 		MoveTo or Work has been assigned, a break only occurs in the body 
	 * 		of a while, sequences satisfy checkSequence, a the sequence of a 
	 * 		while satisfies checkSequence and a If statement satisfies checkStatementInIf.
	 *		| if(Statement is an AttackStatement and its expression is a ReadVarriable) 
	 *		| 	then result == Statements.contaigns(ReadVarriable.getName())
	 *		| else if(Statement is a Break statement) result == inBodyOfWhile
	 *		| else if(Statement is a MoveTo statement and its expression is a ReadVarriable) 
	 *		| 	result ==  Statements.contaigns(ReadVarriable.getName())
	 *		| else if(statement is a SequenceStatement) 
	 *		|	result == this.checkSequence(inBodyOfWhile, (Sequence) statement, assignments)
	 *		| else if(statement is a while) then 
	 *		|	result == this.checkStatement(true, ((While) statement).getBody(), assignments)
	 *		| else if(statement is a work statement and its TODO condition is a readVarable) 
	 *		| 	result == Statements.contaigns(condition.getName())
	 *		| else if(statement is a If statement) then 
	 *		| 	result == checkStatementInIf(inBodyOfWhile, ((If) statement).getIfBody(), ((If) statement).getElseBody(), assignments)
	 *		| else result == true 
	 */
	private static boolean checkStatement(boolean inBodyOfWhile, Statement statement, Set<String> assignments){
		if(statement instanceof Assignment){
			assignments.add(((Assignment) statement).getName());
		}else if (statement instanceof Attack){
			if( ((Convert) ((Attack) statement).getVictim()).expression instanceof ReadVariable){
				if (! assignments.contains((((ReadVariable) ((Convert) ((Attack) statement).getVictim()).expression).getName()))){
					return false;
				} 
			}
		}else if (statement instanceof Break){
			if(! inBodyOfWhile){
				return false;
			}
		}else if (statement instanceof MoveTo){
			if( ((Convert) ((MoveTo) statement).destination).expression instanceof ReadVariable){
				if (! assignments.contains((((ReadVariable) ((Convert) ((MoveTo) statement).destination).expression).getName()))){
					return false;
				} 
			}
		}else if (statement instanceof Sequence){
			if (! checkSequence(inBodyOfWhile, (Sequence) statement, assignments)){
				return false;
			}
		}else if (statement instanceof While){
			if (! checkStatement(true, ((While) statement).getBody(), assignments)){
				return false;
			}
		}else if (statement instanceof Work){
			if( ((Convert) ((Work) statement).condition).expression instanceof ReadVariable){
				if (! assignments.contains((((ReadVariable) ((Convert) ((MoveTo) statement).destination).expression).getName()))){
					return false;
				} 
			}
		}else if (statement instanceof If){
			return checkStatementInIf(inBodyOfWhile, ((If) statement).getIfBody(), ((If) statement).getElseBody(), assignments);
		}
		return true;
	}
	
	/**
	 * Check whether the given if and else body are valid statements 
	 * @param inBodyOfWhile
	 * 		| a boolean that returns true iff the given sequence is found in a 
	 * 		| while loop
	 * @param ifStatement
	 * 		| the ifBody to check
	 * @param elseStatement
	 * 		| the else body to check
	 * @param assignments
	 * 		| A set containing all the variables that have been assigned before 
	 * 		| this sequence
	 * @return true iff both the if and the while body is well formed
	 * 		| result == checkStatement(inBodyOfWhile,  ifStatement, ifAssignments) 
	 * 		| 	&& checkStatement(inBodyOfWhile,  elseStatement, elseAssignments)
	 * @effect every assignment that occurs in both the if and the else body is added to the set of assignments
	 */
	private static boolean checkStatementInIf(boolean inBodyOfWhile, Statement ifStatement, Statement elseStatement , Set<String> assignments){
		Set<String> ifAssignments = new HashSet<String>();
		ifAssignments.addAll(assignments);
		Set<String> elseAssignments = new HashSet<String>();
		elseAssignments.addAll(assignments);
		if (! checkStatement(inBodyOfWhile,  ifStatement, ifAssignments)){
			return false;
		}else if (! checkStatement(inBodyOfWhile,  elseStatement, elseAssignments)){
			return false;
		}
		for (String element : ifAssignments){
			if (elseAssignments.contains(element) && ! assignments.contains(element)){
				assignments.add(element);
			}
		}
		return true;
	}
	
	/**
	 * Sets the next field of all Break Statements in this Task. When a Break
	 * is not inside a while loop, it is not altered, and no sign of 
	 * unwell-formedness is given, although obvious.
	 * @pre
	 * 		All Statements in this Task should have had their next and their
	 * 		linearNext set already (although not perfectly yet as for Breaks).
	 * 		| this.getStatement().setNext() has been called
	 * 		| this.getStatement().setLinearNext() has been called
	 * @post
	 * 		All Break Statements in this Task that are located within a While
	 * 		loop will have their next field set to their directly enclosing
	 * 		While loop.
	 * 		| foreach (Statement break in allStatementsInThisTask) {
	 * 		|     if (break instanceof Break && breakHasDirectlyEnclosingWhileLoop) {
	 * 		|         break.getNextStatement() == break.getNext() == breaksDirectlyEnclosingWhileLoop
	 * 		|     }
	 * 		| }
	 */
	private void setBreaksNextStatement () {
		Stack<Statement> stack = new Stack<Statement>();
		Statement current = this.getActivity();
		do {
			if (current instanceof While) {
				stack.push(current);
			} else if (current instanceof Break) {
				current.setNext(stack.isEmpty()?null:stack.peek());
			}
			if (!stack.isEmpty() && current.getLinearNext() == stack.peek().getNext()) {
				stack.pop();
			}
			current = current.getLinearNext();
		} while (current != null);
	}
	
	/* Well-typedness */
	
	private boolean isWellTyped () {
		return this.getActivity().isWellTyped();
	}
	
}
