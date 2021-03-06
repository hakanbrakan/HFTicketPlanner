package se.frihak.ticketplanner.kalender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

import org.junit.Before;
import org.junit.Test;

public class DagTest {
	private Dag dag1;
	private Dag dag2;
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void setUp() throws Exception {
		dag1 = new Dag("2007-05-25");
		dag2 = new Dag("2007-06-10");
	}

	@Test
	public void testGetNextDag() {
		Dag nasta = dag1.getNextDag();
		assertEquals("2007-05-26", nasta.toString());

		// Det ska inte bli n�gon skillnad om vi g�r samma sak igen.
		nasta = dag1.getNextDag();
		assertEquals("2007-05-26", nasta.toString());
	}

	@Test
	public void testEqualsObject() {
		assertTrue(dag1.equals(dag1));
		assertFalse(dag1.equals(dag2));
	}

	@Test
	public void testPlusDays() {
		Dag dagTest = dag1.plusDays(3);
		assertNotEquals(dag1, dagTest);
		
		dag1 = dag1.plusDays(1);
		assertEquals("2007-05-26", dag1.toString());
		dag1 = dag1.plusDays(2);
		assertEquals("2007-05-28", dag1.toString());
		dag1 = dag1.plusDays(5);
		assertEquals("2007-06-02", dag1.toString());
		dag1 = dag1.plusDays(7);
		assertEquals("2007-06-09", dag1.toString());
	}

	@Test
	public void framTillArsskifte() {
		Dag testDag = new Dag("2007-12-24");
		testDag = testDag.plusDays(7);
		assertEquals("2007-12-31", testDag.toString());
	}

	@Test
	public void overArsskifte() {
		Dag testDag = new Dag("2007-12-24");
		testDag = testDag.plusDays(39);
		assertEquals("2008-02-01", testDag.toString());
	}

	@Test
	public void overArsskifteOchFebruari() {
		Dag testDag = new Dag("2007-12-24");
		testDag = testDag.plusDays(68);
		assertEquals("2008-03-01", testDag.toString());
	}

	@Test
	public void backaOverManadsskifte() {
		Dag testDag = new Dag("2007-12-24");
		testDag = testDag.plusDays(-25);
		assertEquals("2007-11-29", testDag.toString());
	}

	@Test
	public void backaOverArsskifte() {
		Dag testDag = new Dag("2008-01-01");
		testDag = testDag.plusDays(-2);
		assertEquals("2007-12-30", testDag.toString());
	}

	@Test
	public void testGetWeekdayFormatted() {
		assertEquals("Fredag", dag1.getWeekdayFormatted());
		Dag dag2 = dag1.getNextDag();
		assertEquals("Lördag", dag2.getWeekdayFormatted());
		dag2 = dag2.plusDays(1);
		assertEquals("Söndag", dag2.getWeekdayFormatted());
		dag2 = dag2.plusDays(1);
		assertEquals("Måndag", dag2.getWeekdayFormatted());
		dag2 = dag2.plusDays(1);
		assertEquals("Tisdag", dag2.getWeekdayFormatted());
		dag2 = dag2.plusDays(1);
		assertEquals("Onsdag", dag2.getWeekdayFormatted());
		dag2 = dag2.plusDays(1);
		assertEquals("Torsdag", dag2.getWeekdayFormatted());
	}

	@Test
	public void testBefore() {
		assertTrue(dag1.isBefore(dag2));
		assertFalse(dag2.isBefore(dag2));

		Dag dag3 = new Dag("2007-05-25");
		assertFalse(dag1.isBefore(dag3));
	}

	@Test(expected = DateTimeParseException.class)
	public void testIllegalDate() {
		new Dag("1982-13-45");
	}
}
