package hillbillies.part3;

import java.util.List;

import com.sun.javafx.fxml.expression.LiteralExpression;

import hillbillies.model.Coordinate;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.expressions.*;
import hillbillies.model.statements.Statement;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWhile(Expression<?> condition, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createIf(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(Expression<?> value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createMoveTo(Expression<?> position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWork(Expression<?> position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createFollow(Expression<?> unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAttack(Expression<?> unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createIsSolid(Expression<?> position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createIsPassable(Expression<?> position, SourceLocation sourceLocation) {
		Convert<Coordinate> unit1 =  new Convert<Coordinate>(position, Coordinate.class);
		return null;
	}

	@Override
	public Expression<?> createIsFriend(Expression<?> unit, SourceLocation sourceLocation) {
		Convert<Unit> unit1 =  new Convert<Unit>(unit, Unit.class);
		return new IsAlive(unit1);
	}

	@Override
	public Expression<?> createIsEnemy(Expression<?> unit, SourceLocation sourceLocation) {
		Convert<Unit> unit1 =  new Convert<Unit>(unit, Unit.class);
		return new IsEnemy(unit1);
	}

	@Override
	public Expression<?> createIsAlive(Expression<?> unit, SourceLocation sourceLocation) {
		Convert<Unit> unit1 =  new Convert<Unit>(unit, Unit.class);
		return new IsAlive(unit1);
	}

	@Override
	public Expression<?> createCarriesItem(Expression<?> unit, SourceLocation sourceLocation) {
		Convert<Unit> unit1 =  new Convert<Unit>(unit, Unit.class);
		return new CarriesItem(unit1);
	}

	@Override
	public Expression<?> createNot(Expression<?> expression, SourceLocation sourceLocation) {
		Convert<Boolean> exp =  new Convert<Boolean>(expression, Boolean.class);
		return new Not(exp) ;
	}

	@Override
	public Expression<?> createAnd(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		Convert<Boolean> left1 =  new Convert<Boolean>(left, Boolean.class);
		Convert<Boolean> right1 = new Convert<Boolean>(right, Boolean.class);
		return new And(left1, right1);
	}

	@Override
	public Expression<?> createOr(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		Convert<Boolean> left1 =  new Convert<Boolean>(left, Boolean.class);
		Convert<Boolean> right1 = new Convert<Boolean>(right, Boolean.class);
		return new Or(left1, right1);
	}

	@Override
	public Expression<?> createHerePosition(SourceLocation sourceLocation) {
		return new Here();
	}

	@Override
	public Expression<?> createLogPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createBoulderPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
