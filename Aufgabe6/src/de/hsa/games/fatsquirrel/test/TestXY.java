package de.hsa.games.fatsquirrel.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hsa.games.fatsquirrel.core.XY;

public class TestXY {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testXY() {
//		fail("Not yet implemented");
		assertNotNull(new XY(1,1));
	}
	
	@Test
	public void testGetX() {
		assertEquals(0,new XY(0,2).getX());
	}
	
	@Test
	public void testGetY() {
		assertEquals(2,new XY(0,2).getY());
	}

}
