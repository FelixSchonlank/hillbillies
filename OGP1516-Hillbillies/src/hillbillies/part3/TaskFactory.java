package hillbillies.part3;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.Coordinate;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.VariableNameGenerator;
import hillbillies.model.expressions.*;
import hillbillies.model.statements.*;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		List<Task> list = new ArrayList<Task>();
		for (int[] cube : selectedCubes) {
			list.add(new Task(name, priority, new Coordinate(cube), activity));
		}
		return list;
	}

	@Override
	public Statement createAssignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		return new Assignment(variableName, value);
	}

	@Override
	public Statement createWhile(Expression<?> condition, Statement body, SourceLocation sourceLocation) {
		return new While(new Convert<Boolean>(condition, Boolean.class), body);
	}

	@Override
	public Statement createIf(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new If(new Convert<Boolean>(condition, Boolean.class), ifBody, elseBody);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new Break();
	}

	@Override
	public Statement createPrint(Expression<?> value, SourceLocation sourceLocation) {
		return new Print(value);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new Sequence(statements);
	}

	@Override
	public Statement createMoveTo(Expression<?> position, SourceLocation sourceLocation) {
		return new MoveTo(new Convert<Coordinate>(position, Coordinate.class));
	}

	@Override
	public Statement createWork(Expression<?> position, SourceLocation sourceLocation) {
		return new Work(new Convert<Coordinate>(position, Coordinate.class));
	}

	@Override
	public Statement createFollow(Expression<?> unit, SourceLocation sourceLocation) {
		List<Statement> statementList = new ArrayList<Statement>();
		statementList.add(new Assignment(VariableNameGenerator.getNext(), unit));
		statementList.add(
				new While(
						new AreAdjacent(
								new This(), 
								new Convert<Unit>(
										unit, 
										Unit.class
										)
								)
						)
				);
		return new Sequence(statementList);
	}

	@Override
	public Statement createAttack(Expression<?> unit, SourceLocation sourceLocation) {
		return new Attack(new Convert<Unit>(unit, Unit.class));
	}

	@Override
	public Expression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVariable(variableName);
	}

	@Override
	public Expression<?> createIsSolid(Expression<?> position, SourceLocation sourceLocation) {
		return new IsSolid(new Convert<Coordinate>(position, Coordinate.class));
	}

	@Override
	public Expression<?> createIsPassable(Expression<?> position, SourceLocation sourceLocation) {
		return new IsPassable(new Convert<Coordinate>(position, Coordinate.class));
	}

	@Override
	public Expression<?> createIsFriend(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsFriend(new Convert<Unit>(unit, Unit.class));
	}

	@Override
	public Expression<?> createIsEnemy(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsEnemy(new Convert<Unit>(unit, Unit.class));
	}

	@Override
	public Expression<?> createIsAlive(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsAlive(new Convert<Unit>(unit, Unit.class));
	}

	@Override
	public Expression<?> createCarriesItem(Expression<?> unit, SourceLocation sourceLocation) {
		return new CarriesItem(new Convert<Unit>(unit, Unit.class));
	}

	@Override
	public Expression<?> createNot(Expression<?> expression, SourceLocation sourceLocation) {
		return new Not(new Convert<Boolean>(expression, Boolean.class));
	}

	@Override
	public Expression<?> createAnd(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		Convert<Boolean> left1 =  new Convert<Boolean>(left, Boolean.class);
		Convert<Boolean> right1 = new Convert<Boolean>(right, Boolean.class);
		return new And(left1, right1);
	}

	@Override
	public Expression<?> createOr(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new Or(
				new Convert<Boolean>(left, Boolean.class), 
				new Convert<Boolean>(right, Boolean.class)
				);
	}

	@Override
	public Expression<?> createHerePosition(SourceLocation sourceLocation) {
		return new Here();
	}

	@Override
	public Expression<?> createLogPosition(SourceLocation sourceLocation) {
		return new Log();
	}

	@Override
	public Expression<?> createBoulderPosition(SourceLocation sourceLocation) {
		return new Boulder();
	}

	@Override
	public Expression<?> createWorkshopPosition(SourceLocation sourceLocation) {
		return new Workshop();
	}

	@Override
	public Expression<?> createSelectedPosition(SourceLocation sourceLocation) {
		return new Selected();
	}

	@Override
	public Expression<?> createNextToPosition(Expression<?> position, SourceLocation sourceLocation) {
		Convert<Coordinate> cube =  new Convert<Coordinate>(position, Coordinate.class);
		return new NextTo(cube);
	}

	@Override
	public Expression<?> createPositionOf(Expression<?> unit, SourceLocation sourceLocation) {
		return new PositionOf(new Convert<Unit>(unit, Unit.class));
	}

	@Override
	public Expression<?> createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new Literal(x, y, z);
	}

	@Override
	public Expression<?> createThis(SourceLocation sourceLocation) {
		return new This();
	}

	@Override
	public Expression<?> createFriend(SourceLocation sourceLocation) {
		return new Friend();
	}

	@Override
	public Expression<?> createEnemy(SourceLocation sourceLocation) {
		return new Enemy();
	}

	@Override
	public Expression<?> createAny(SourceLocation sourceLocation) {
		return new Any();
	}

	@Override
	public Expression<?> createTrue(SourceLocation sourceLocation) {
		return new True();
	}

	@Override
	public Expression<?> createFalse(SourceLocation sourceLocation) {
		return new False();
	}

}
