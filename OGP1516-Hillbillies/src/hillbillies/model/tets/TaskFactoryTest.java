package hillbillies.model.tets;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Coordinate;
import hillbillies.model.Task;
import hillbillies.model.Utils;
import hillbillies.model.expressions.*;
import hillbillies.model.statements.*;
import hillbillies.part3.TaskFactory;
import hillbillies.part3.programs.SourceLocation;

/**
 * A class of ...
 *
 * @author  ...
 * @version 1.0
 */
public class TaskFactoryTest {
	
	private static MoveTo goodProgram;
	private static MoveTo badlyTypedProgram;
	private static Sequence badlyTypedProgramWithVariable;
	private static ReadVariable readWithoutAssignment;
	private static Sequence breakOutsideOfWhile;
	private static ArrayList<int[]> noSelectedCubes;
	private static List<int[]> oneSelectedCube;
	private static True booleanExpression;
	private static Here positionExpression;
	private static This unitExpression;
	private static ReadVariable objectExpression;
	private static SourceLocation sl;
	private TaskFactory taskFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		goodProgram = new MoveTo(new Here());
		badlyTypedProgram = new MoveTo(new Convert<Coordinate>(new True(), Coordinate.class));
		List<Statement> listOfTasks = new ArrayList<Statement>();
		listOfTasks.add(new Assignment("IsFelixBaas", new True()));
		listOfTasks.add(new MoveTo(new Convert<Coordinate>(new ReadVariable("IsFelixBaas"), Coordinate.class)));
		badlyTypedProgramWithVariable = new Sequence(listOfTasks);
		readWithoutAssignment = new ReadVariable("Willem");
		listOfTasks = new ArrayList<Statement>();
		listOfTasks.add(new While(new True(), new MoveTo(new Here())));
		listOfTasks.add(new Break());
		breakOutsideOfWhile = new Sequence(listOfTasks);
		
		booleanExpression = new True();
		positionExpression = new Here();
		unitExpression = new This();
		objectExpression = new ReadVariable("IkLeesEenVariabele");
		
		noSelectedCubes = new ArrayList<int[]>();
		List<int[]> listOfCoordinates = new ArrayList<int[]>();
		listOfCoordinates.add(Utils.unboxArray(new Coordinate(0, 0, 0).toArray()));
		oneSelectedCube = listOfCoordinates;
		
		sl = new SourceLocation(0, 0);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.taskFactory = new TaskFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createTasks(java.lang.String, int, hillbillies.model.statements.Statement, java.util.List)}.
	 */
	@Test
	public void testCreateTasks() {
		List<Task> listOfTasks = this.taskFactory.createTasks("Felix", 1337, goodProgram, noSelectedCubes);
		assertFalse(listOfTasks.isEmpty());
		
		this.taskFactory = new TaskFactory();
		listOfTasks = this.taskFactory.createTasks("Felix", 1337, goodProgram, oneSelectedCube);
		assertTrue(listOfTasks.size() == 1);
		assertTrue(listOfTasks.get(0).getPosition().equals(new Coordinate(0, 0, 0)));
		
		//TODO isWellTyped checken.
		//TODO isWellFormed checken.
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createAssignment(java.lang.String, hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateAssignment() {
		Statement statement = this.taskFactory.createAssignment("WillieW", objectExpression, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof Assignment);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createWhile(hillbillies.model.expressions.Expression, hillbillies.model.statements.Statement, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateWhile() {
		Statement statement = this.taskFactory.createWhile(booleanExpression, goodProgram, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof While);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createIf(hillbillies.model.expressions.Expression, hillbillies.model.statements.Statement, hillbillies.model.statements.Statement, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateIf() {
		Statement statement = this.taskFactory.createIf(booleanExpression, goodProgram, goodProgram, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof If);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createBreak(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateBreak() {
		Statement statement = this.taskFactory.createBreak(sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof Break);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createPrint(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreatePrint() {
		Statement statement = this.taskFactory.createPrint(objectExpression, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof Print);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createSequence(java.util.List, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateSequence() {
		List<Statement> list = new ArrayList<Statement>();
		list.add(goodProgram);
		list.add(goodProgram);
		Statement statement = this.taskFactory.createSequence(list, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof Sequence);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createMoveTo(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateMoveTo() {
		Statement statement = this.taskFactory.createMoveTo(positionExpression, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof MoveTo);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createWork(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateWork() {
		Statement statement = this.taskFactory.createWork(positionExpression, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof Work);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createFollow(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateFollow() {
		Statement statement = this.taskFactory.createFollow(unitExpression, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof Sequence);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createAttack(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateAttack() {
		Statement statement = this.taskFactory.createAttack(unitExpression, sl);
		assertTrue(statement.isWellTyped());
		assertTrue(statement instanceof Attack);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createReadVariable(java.lang.String, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateReadVariable() {
		Expression<?> expression = this.taskFactory.createReadVariable("DezeNegerKomtZoHard", sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof ReadVariable);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createIsSolid(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateIsSolid() {
		Expression<?> expression = this.taskFactory.createIsSolid(positionExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof IsSolid);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createIsPassable(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateIsPassable() {
		Expression<?> expression = this.taskFactory.createIsPassable(positionExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof IsPassable);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createIsFriend(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateIsFriend() {
		Expression<?> expression = this.taskFactory.createIsFriend(unitExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof IsFriend);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createIsEnemy(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateIsEnemy() {
		Expression<?> expression = this.taskFactory.createIsEnemy(unitExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof IsEnemy);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createIsAlive(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateIsAlive() {
		Expression<?> expression = this.taskFactory.createIsAlive(unitExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof IsAlive);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createCarriesItem(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateCarriesItem() {
		Expression<?> expression = this.taskFactory.createCarriesItem(unitExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof CarriesItem);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createNot(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateNot() {
		Expression<?> expression = this.taskFactory.createNot(booleanExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Not);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createAnd(hillbillies.model.expressions.Expression, hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateAnd() {
		Expression<?> expression = this.taskFactory.createAnd(booleanExpression, booleanExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof And);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createOr(hillbillies.model.expressions.Expression, hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateOr() {
		Expression<?> expression = this.taskFactory.createOr(booleanExpression, booleanExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Or);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createHerePosition(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateHerePosition() {
		Expression<?> expression = this.taskFactory.createHerePosition(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Here);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createLogPosition(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateLogPosition() {
		Expression<?> expression = this.taskFactory.createLogPosition(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Log);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createBoulderPosition(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateBoulderPosition() {
		Expression<?> expression = this.taskFactory.createBoulderPosition(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Boulder);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createWorkshopPosition(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateWorkshopPosition() {
		Expression<?> expression = this.taskFactory.createWorkshopPosition(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Workshop);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createSelectedPosition(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateSelectedPosition() {
		Expression<?> expression = this.taskFactory.createSelectedPosition(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Selected);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createNextToPosition(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateNextToPosition() {
		Expression<?> expression = this.taskFactory.createNextToPosition(positionExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof NextTo);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createPositionOf(hillbillies.model.expressions.Expression, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreatePositionOf() {
		Expression<?> expression = this.taskFactory.createPositionOf(unitExpression, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof PositionOf);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createLiteralPosition(int, int, int, hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateLiteralPosition() {
		Expression<?> expression = this.taskFactory.createLiteralPosition(0, 0, 0, sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Literal);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createThis(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateThis() {
		Expression<?> expression = this.taskFactory.createThis(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof This);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createFriend(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateFriend() {
		Expression<?> expression = this.taskFactory.createFriend(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Friend);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createEnemy(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateEnemy() {
		Expression<?> expression = this.taskFactory.createEnemy(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Enemy);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createAny(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateAny() {
		Expression<?> expression = this.taskFactory.createAny(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof Any);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createTrue(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateTrue() {
		Expression<?> expression = this.taskFactory.createTrue(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof True);
	}

	/**
	 * Test method for {@link hillbillies.part3.TaskFactory#createFalse(hillbillies.part3.programs.SourceLocation)}.
	 */
	@Test
	public void testCreateFalse() {
		Expression<?> expression = this.taskFactory.createFalse(sl);
		assertTrue(expression.isWellTyped());
		assertTrue(expression instanceof False);
	}

}
