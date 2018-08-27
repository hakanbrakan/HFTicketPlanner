package se.frihak.ticketplanner;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;

public class PlannerUtilityTest
{
	private PlannerUtility util;

	@Before
	public void setUp() throws Exception
	{
		util = new PlannerUtility();
	}

	@Test
	public void testGetProperties()
	{
		Properties props = util.getProperties();
		
		props.setProperty("testkey", "testvalue");
		assertEquals("testvalue", props.getProperty("testkey"));
	}

	@Test
	public void testGetDagPlan()
	{
		Dag firstDay = new Dag("2008-02-27");
		Dag lastDay = new Dag("2008-03-02");
		
		List<Dag> list = util.getDagPlan(firstDay, lastDay);
		
		assertEquals("2008-02-28", (list.get(1)).toString());
		assertEquals("2008-02-29", (list.get(2)).toString());
		assertEquals("2008-03-01", (list.get(3)).toString());

	
	
		firstDay = new Dag("2007-12-25");
		lastDay = new Dag("2008-03-02");
		
		list =util.getDagPlan(firstDay, lastDay);
		
		assertEquals("2007-12-26", (list.get(1)).toString());
		assertEquals("2008-01-01", (list.get(7)).toString());
		assertEquals("2008-02-01", (list.get(38)).toString());
		assertEquals("2008-02-28", (list.get(65)).toString());
		assertEquals("2008-03-01", (list.get(67)).toString());
		assertEquals("2008-03-02", (list.get(68)).toString());
	}
}
