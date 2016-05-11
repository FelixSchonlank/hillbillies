package hillbillies.model;

import static org.junit.Assert.*;
import hillbillies.part2.listener.TerrainChangeListener;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ogp.framework.util.Util;

import java.util.*;
public class TestWorld {

	World willem;
	
	@Before
	public void initialize(){
		int[][][] terrain = new int[3][3][3];
			
			for(int i = 0; i<3; i++){
				for(int j = 0; j<3; j++){
					for(int k = 0; k<3; k++){
						terrain[i][j][k] = 0;
					}
				}
			}
			terrain[0][0][0] = 1;
			TerrainChangeListener terrainChangeListener = new TerrainChangeListener(); 
			
			willem = new World(terrain, terrainChangeListener);
	}
	
	
	@Test
	public void TestCubeCoordinates_legalCase(){
		Position position = new Position(1.5, 1.5, 1.5);
		Coordinate Expected = new Coordinate(1, 1, 1);
		Coordinate coordinate = willem.cubeCoordinates(position);
		assertEquals("Expected coordinate was " + Expected.toString() + "but the result was " + coordinate.toString(), Expected, coordinate);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void TestCubeCoordinates_positionOutOfBounds(){
		Position position = new Position(5, 5, 5);
		willem.cubeCoordinates(position);
	}
	
	@Test
	public void  TestAreAdjacentCubes_legal_True(){
		Coordinate first = new Coordinate(1, 1, 1);
		Coordinate second = new Coordinate(1, 2, 1);
		assertTrue(first.toString() + " shoot be ajacent to " + second.toString() ,willem.areAdjacentCubes(first, second));
	}
	
	@Test
	public void  TestAreAdjacentCubes_legal_False(){
		Coordinate first = new Coordinate(0, 0, 0);
		Coordinate second = new Coordinate(2, 2, 2);
		assertFalse(first.toString() + " shoot not be ajacent to " + second.toString() ,willem.areAdjacentCubes(first, second));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void  TestAreAdjacentCubes_illegal(){
		Coordinate first = new Coordinate(3, 3, 3);
		Coordinate second = new Coordinate(3, 4, 5);
		willem.areAdjacentCubes(first, second);
	}
	
	@Test
	public void TestWithinBoundsCoordinate_True(){
		Coordinate first = new Coordinate(2, 2, 2);
		assertTrue(first.toString() + " shoot be within the bounds of " + willem.toString() ,willem.withinBounds(first));
	}
	
	@Test
	public void TestWithinBoundsCoordinate_False(){
		Coordinate first = new Coordinate(5, 6, 7);
		assertFalse(first.toString() + " shoot not be within the bounds of " + willem.toString() ,willem.withinBounds(first));
	}
	
	@Test
	public void TestWithinBoundsPosition_True(){
		Position first = new Position(2.5 ,1.4, 0.3);
		assertTrue(first.toString() + " shoot be within the bounds of " + willem.toString() ,willem.withinBounds(first));
	}
	
	@Test
	public void TestWithinBoundsPosition_False(){
		Position first = new Position(6.5 ,7.4, 8.3);
		assertFalse(first.toString() + " shoot not be within the bounds of " + willem.toString() ,willem.withinBounds(first));
	}
	
	@Test
	public void TestAreSameCube_True(){
		Coordinate first = new Coordinate(2, 2, 2);
		Coordinate second = new Coordinate(2, 2, 2);
		assertTrue(first.toString() + " is the same cube as " + second.toString() , willem.areAdjacentCubes(first, second));
	}
	
	@Test
	public void TestAreSameCube_False(){
		Coordinate first = new Coordinate(2, 2, 2);
		Coordinate second = new Coordinate(1, 1, 1);
		assertFalse(first.toString() + " is not the same cube as " + second.toString() , willem.areAdjacentCubes(first, second));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void TestAreSameCube_Illegal(){
		Coordinate first = new Coordinate(5, 2, 2);
		Coordinate second = new Coordinate(3, 6, 3);
		willem.areSameCube(first, second);
	}
	
	@Test
	public void TestGetNeighbors(){
		Coordinate first = new Coordinate(1, 1, 1);
		Set<Coordinate> willem1 = new HashSet<Coordinate>();
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				for(int k = 0; k<3; k++){
					if(i == 1 && j == 1 && k == 1){
						willem1.add(new Coordinate(i, j, k));
					}
				}
			}
		}
		Set<Coordinate> willem2 = willem.getNeighbors(first) ;
		Boolean willemBool = willem1.containsAll(willem2) && willem2.containsAll(willem1);
		assertTrue("Fout in getNeighours() ", willemBool);
	}
	
	@Test
	public void TestIsPassableCube_True(){
		Coordinate first = new Coordinate(1, 1, 1);
		assertTrue("the cube " + first.toString() + " is passable" , willem.isPassableCube(first));
	}
	
	@Test
	public void isPassable_False(){
		Coordinate first = new Coordinate(0, 0, 0);
		assertTrue("the cube " + first.toString() + " is not passable" , willem.isPassableCube(first));
	}
	
	@Test
	public void TestIsAroundSolid_True(){
		Coordinate first = new Coordinate(1, 1, 1);
		assertTrue("the cube " + first.toString() + " is around solid" , willem.isAroundSolid(first));
	}
	
	@Test
	public void TestIsAroundSolid_False(){
		Coordinate first = new Coordinate(2, 2, 2);
		assertTrue("the cube " + first.toString() + " is not around solid" , willem.isAroundSolid(first));
	}
	
	@Test
	public void TestIsAboveSolid_True(){
		Coordinate first = new Coordinate(0, 0, 1);
		assertTrue("the cube " + first.toString() + " is above solid" , willem.isAboveSolid(first));
	}
	
	@Test
	public void TestIsAboveSolid_False(){
		Coordinate first = new Coordinate(2, 2, 2);
		assertFalse("the cube " + first.toString() + " is not above solid" , willem.isAboveSolid(first));
	}
	
	@Test
	public void TestGetCubeAt_AIR(){
		Coordinate first = new Coordinate(2, 2, 2);
		assertEquals("The terrainType of " + first.toString() + "is AIR not " + willem.getCubeAt(first).toString(), World.TerrainType.AIR, willem.getCubeAt(first));
	}
	
	@Test
	public void TestGetCubeAt_ROCK(){
		Coordinate first = new Coordinate(0, 0, 0);
		assertEquals("The terrainType of " + first.toString() + "is ROCK not " + willem.getCubeAt(first).toString(), World.TerrainType.ROCK , willem.getCubeAt(first));
	}
	
	
}
