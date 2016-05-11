package hillbillies.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.Util;

import java.util.*;
public class TestItem {
	World willemWorld;
	Item willemItem;
	
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
		TerrainChangeListener terrainChangeListener = new TerrainChangeListener(); 
		willemWorld =  new World(terrain, terrainChangeListener);
		willemItem = new Item(willemWorld, new Position(1, 1, 1));
	}
	
	@Test
	public void TestTerminate(){
		willemItem.terminate();
		assertTrue("the World of an Item is not set to null afther termination", willemItem.getWorld() == null);
		assertTrue("the Unit of an Item is not set to null afther termination", willemItem.getUnit() == null);
		assertTrue("the Item is still part of its World afther termination of that Item",  willemWorld.hasAsItem(willemItem));
		assertTrue("the Unit still has its Item afther termination of that Item",  willemWorld.hasAsItem(willemItem));
	}

}
