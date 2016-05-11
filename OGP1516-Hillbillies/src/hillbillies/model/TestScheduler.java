package hillbillies.model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.Util;

import java.util.*;
public class TestScheduler {
	Unit unit;
	Task task1, task2;
	Scheduler scheduler;
	
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
		
		World willem = new World(terrain, terrainChangeListener);
		unit = new Unit(willem, true);
		task1 = new Task("Willem", 1, new List<Statement>());
		task2 = new Task("Willem", 2, new List<Statement>());
		scheduler = new Scheduler(willem.getSmallestFaction());
		scheduler.addTask(task1);
	}
	
	@Test
	public void TestReplaceTask(){
		scheduler.setTaskToBeScheduled(unit);
		scheduler.replaceTask(task1, task2);
		assertFalse("The task should no longer have a Unit", task1.hasUnit());
		assertTrue("The scheduler schould have the new task as its task", scheduler.hasAsTask(task2));
		assertTrue("The scheduler schould no longer have the old task as its task", scheduler.hasAsTask(task1));
	}
	
	@Test
	public void TestGetMostImportandTask(){
		scheduler.addTask(task1);
		assertEquals("The most importans task in the scheduler should be " + task2.toString(), task2, scheduler.getMostImportandTask());
	}
	
	@Test
	public void TestGetSscheduledTasksAndTaskToBeScheduled(){
		scheduler.setTaskToBeScheduled(unit);
		assertTrue("The list of scheduledTasks should contain task2", scheduler.getScheduledTasks().contains(task2));
		assertFalse("The list of scheduledTasks should not contain task1", scheduler.getScheduledTasks().contains(task1));
	}
	
	@Test
	public void TestIsScheduled(){
		assertTrue("Task2 schould be scheuled", scheduler.isScheduled(task2));
		assertFalse("Task1 schould not be scheuled", scheduler.isScheduled(task1));
	}
	
	@Test
	public void TestResetTask(){
		scheduler.resetTask(task2);
		assertFalse("Task2 schould not be scheuled", scheduler.isScheduled(task2));
	}
}
