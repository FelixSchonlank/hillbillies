package hillbillies.model;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ogp.framework.util.Util;

import java.util.*;
public class TestUnit {

	private Unit Baas;
	private Unit victim;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		}

	@Before
	public void setUp() throws Exception {
		int[] position = {3, 4, 5};
		Baas = new Unit ("WillieW" , position , 75 , 50, 50, 50, false);
		victim = new Unit ("ViezeFur" , position , 75 , 50, 50, 50, false);
	}

	/* Constructor */
	
	@Test
	public void testConstructor_legalCase(){
		int[] position = {3, 4, 5};
		new Unit ("WillieW" , position , 75 , 50, 50, 50, false);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_illegalName_short(){
		int[] position = {3, 4, 5};
		new Unit ("W" , position , 75 , 50, 50, 50, false);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_illegalName_NotUpper(){
		int[] position = {3, 4, 5};
		new Unit ("willieWartaal" , position , 75 , 50, 50, 50, false);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_illegalNullName(){
		int[] position = {3, 4, 5};
		new Unit (null , position , 75 , 50, 50, 50, false);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_illegalPosition_short(){
		int[] badPosition = { 4, 5};
		new Unit ("WillieW" , badPosition , 75 , 50, 50, 50, false);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_illegalPosition_long(){
		int[] badPosition = {3, 4, 5, 6};
		new Unit ("WillieW" , badPosition , 75 , 50, 50, 50, false);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_illegalPosition_MaxCoordinate(){
		int[] badPosition = {(int)Unit.getMaxCoordinate(), (int)Unit.getMaxCoordinate(), (int)Unit.getMaxCoordinate()};
		new Unit ("WillieW" , badPosition , 75 , 50, 50, 50, false);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_illegalPosition_negative(){
		int[] badPosition = {(int)Unit.getMinCoordinate() - 10, (int)Unit.getMinCoordinate() - 10, (int)Unit.getMinCoordinate() - 10};
		new Unit ("WillieW" , badPosition , 75 , 50, 50, 50, false);
	}
	
	@Test 
	public void testConstructor_maxWeight(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 100 , 50, 50, 50, false);
		assertEquals(100, Baas.getWeight()); 
	}
	
	@Test 
	public void testConstructor_minWeight(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 38 , 25 , 25, 25, false);
		assertEquals( 38, Baas.getWeight()); 
	}
	
	@Test 
	public void testConstructor_largeWeight(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , Unit.getMaxWeight() + 100 , 50, 50, 50, false);
		assertEquals(Unit.getMaxWeight(), Baas.getWeight()); 
	}
	
	@Test 
	public void testConstructor_smallWeight() {
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, Unit.getMaxWeight() - 100, 50, 50, false);
		assertEquals(Unit.getMaxWeight(), Baas.getWeight()); 
	}
	
	@Test 
	public void testConstructor_maxAgility(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 100 , 50, 50, false);
		assertEquals(100, Baas.getAgility()); 
	}
	
	@Test 
	public void testConstructor_minAgiglity(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 25 , 50, 50, false);
		assertEquals( 25, Baas.getAgility()); 
	}
	
	@Test 
	public void testConstructor_largeAgility(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, Unit.getMaxAgility() + 100 , 50, 50, false);
		assertEquals(Unit.getMaxAgility(), Baas.getAgility()); 
	}
	
	@Test 
	public void testConstructor_smallAgility(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, Unit.getMinAgility() - 100 , 50, 50, false);
		assertEquals(Unit.getMaxAgility(), Baas.getAgility()); 
	}
	
	@Test 
	public void testConstructor_maxStrength(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 50, 100 , 50, false);
		assertEquals(100, Baas.getStrength()); 
	}
	
	@Test 
	public void testConstructor_minStrength(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 50, 25 , 50, false);
		assertEquals(25, Baas.getStrength()); 
	}
	
	@Test 
	public void testConstructor_largeStrength(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 50, Unit.getMaxStrength() + 100 , 50, false);
		assert(Baas.getStrength() == Unit.getMaxStrength()); 
	}
	
	@Test 
	public void testConstructor_smallStrength(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 50, Unit.getMaxStrength() - 100 ,  50, false);
		assert(Baas.getStrength() == Unit.getMaxStrength()); 
	}
	
	@Test 
	public void testConstructor_maxToughness(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position ,  50, 50, 50, 100 , false);
		assertEquals(100, Baas.getToughness()); 
	}
	
	@Test 
	public void testConstructor_minToughness(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 50, 50, 25 , false);
		assertEquals(25, Baas.getToughness()); 
	}
	
	@Test 
	public void testConstructor_largeToughness(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 50, 50, Unit.getMaxToughness() + 100, false);
		assert(Baas.getToughness() == Unit.getMaxToughness()); 
	}
	
	@Test 
	public void testConstructor_smallToughness(){
		int[] position = {3, 4, 5};
		Unit Baas = new Unit ("WillieW" , position , 50, 50, 50, Unit.getMaxToughness() - 100 , false);
		assert(Baas.getToughness() == Unit.getMaxToughness()); 
	}
	
	/* AdvanceTime */
	
	@Test
	public void testAdvanceTime() {
		fail("Not yet implemented");
	}
	
	/* MoveToAdjacent */

	@Test
	public void testMoveToAdjacent_LegalCase() throws IllegalArgumentException, BadFSMStateException {
		double newPosition[] = new double[3]; 
		newPosition[0] = Baas.getPosition()[0] - 1.0;
		newPosition[1] = Baas.getPosition()[1]; 
		newPosition[2] = Baas.getPosition()[2] + 1.0;
		Baas.setState(State.NOTHING);
		Baas.moveToAdjacent(-1, 0, 1);
		assertTrue(Arrays.equals(newPosition, Baas.getImmediateTarget()));
		Baas.setState(State.RESTING_HP);
		Baas.moveToAdjacent(-1, 0, 1);
		assertTrue(Arrays.equals(newPosition, Baas.getImmediateTarget()));
		Baas.setState(State.RESTING_STAMINA);
		Baas.moveToAdjacent(-1, 0, 1);
		assertTrue(Arrays.equals(newPosition, Baas.getImmediateTarget()));
		Baas.setState(State.WORKING);
		Baas.moveToAdjacent(-1, 0, 1);
		assertTrue(Arrays.equals(newPosition, Baas.getImmediateTarget()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMoveToAdjacent_OutOfRange() throws BadFSMStateException{
		Baas.moveToAdjacent(2, 3, 4);
	}
	
	@Test (expected = BadFSMStateException.class) 
	public void testMoveToAdjacent_BadState() throws BadFSMStateException{
		Baas.setState(State.MOVING);
		Baas.moveToAdjacent(1, 0, -1);
	}
	
	/* MoveTo */

	@Test
	public void testMoveTo_LegalCase() throws IllegalArgumentException, BadFSMStateException {
		double[] excpected = {25.5, 25.5, 25.5};
		int[] destinationInt = {25, 25, 25};
		Baas.moveTo(destinationInt);
		assertArrayEquals( excpected, Baas.getPath().get(Baas.getPath().size() - 1), Util.DEFAULT_EPSILON);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMoveTo_illegalDestination() throws IllegalArgumentException, BadFSMStateException{
		int[] destination = {(int)Unit.getMaxCoordinate() + 1000, (int)Unit.getMinCoordinate() - 1000, (int)Unit.getMaxCoordinate() + 2};
		Baas.moveTo(destination);
	}
	
	/* attack */

	@Test
	public void testAttack_LegalCase() throws IllegalArgumentException, BadFSMStateException {
		Baas.setState(State.NOTHING);
		Baas.attack(victim);
		assertEquals(victim, Baas.getVictim());
		assertTrue(Baas.getShouldAttackFlag());
		Baas.setState(State.RESTING_HP);
		Baas.attack(victim);
		assertEquals(victim, Baas.getVictim());
		assertTrue(Baas.getShouldAttackFlag());
		Baas.setState(State.RESTING_STAMINA);
		Baas.attack(victim);
		assertEquals(victim, Baas.getVictim());
		assertTrue(Baas.getShouldAttackFlag());
		Baas.setState(State.WORKING);
		Baas.attack(victim);
		assertEquals(victim, Baas.getVictim());
		assertTrue(Baas.getShouldAttackFlag());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAttack_nullVictim() throws IllegalArgumentException, BadFSMStateException {
		Baas.attack(null);
	}
	
	@Test (expected = BadFSMStateException.class)
	public void testAttack_badState() throws IllegalArgumentException, BadFSMStateException{
		Baas.setState(State.MOVING);
		Baas.attack(victim);
	}

	@Test
	public void testDefend_legalCase() {
		int oldHP = victim.getHP();
		victim.defend(Baas);
		assertTrue( victim.getHP() == oldHP - Baas.getStrength() / 10 || victim.getHP() == oldHP );
	}

	@Test (expected = IllegalArgumentException.class)	
	public void testDefend_nullAttackert(){
		victim.defend(null);
	}

	@Test
	public void testRest_legalCase() throws BadFSMStateException {
		Baas.setState(State.NOTHING);
		Baas.rest();
		assertTrue(Baas.getShouldRestFlag());
		Baas.setState(State.WORKING);
	}
	
	@Test (expected = BadFSMStateException.class)
	public void tetsRest_BadState() throws BadFSMStateException{
		Baas.setState(State.MOVING);
		Baas.rest();
	}
	
	@Test (expected = BadFSMStateException.class)
	public void testWork_BadState() throws BadFSMStateException{
		Baas.setState(State.RESTING_INIT);
		Baas.work();
	}

	@Test
	public void testWork_legalCase() throws BadFSMStateException {
		Baas.setState(State.NOTHING);
		Baas.work();
		assertTrue(Baas.getShouldWorkFlag());
		Baas.setState(State.RESTING_HP);
		Baas.work();
		assertTrue(Baas.getShouldWorkFlag());
		Baas.setState(State.RESTING_STAMINA);
		Baas.work();
		assertTrue(Baas.getShouldWorkFlag());
	}
}
