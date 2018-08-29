package se.frihak.ticketplanner.kalender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
		assertEquals(dag1, dag1);
		assertFalse(dag1.equals(dag2));
	}

	@Test
	public void testAddDays() {
		dag1.addDays(1);
		assertEquals("2007-05-26", dag1.toString());
		dag1.addDays(2);
		assertEquals("2007-05-28", dag1.toString());
		dag1.addDays(5);
		assertEquals("2007-06-02", dag1.toString());
		dag1.addDays(7);
		assertEquals("2007-06-09", dag1.toString());

		Dag testDag = new Dag("2007-12-24");
		testDag.addDays(7);
		assertEquals("2007-12-31", testDag.toString());

		testDag = new Dag("2007-12-24");
		testDag.addDays(39);
		assertEquals("2008-02-01", testDag.toString());

		testDag = new Dag("2007-12-24");
		testDag.addDays(68);
		assertEquals("2008-03-01", testDag.toString());

		testDag = new Dag("2007-12-24");
		testDag.addDays(-25);
		assertEquals("2007-11-29", testDag.toString());

		testDag = new Dag("2008-01-01");
		testDag.addDays(-2);
		assertEquals("2007-12-30", testDag.toString());
	}

	@Test
	public void testClone() {
		Dag dag2 = (Dag) dag1.clone();

		assertEquals(dag1, dag2);
		assertEquals(dag1.toString(), dag2.toString());
		assertEquals(dag1.getNextDag(), dag2.getNextDag());

		dag2.addDays(1);
		assertFalse(dag1.equals(dag2));
	}

	@Test
	public void testGetWeekdayFormatted() {
		assertEquals("Fredag", dag1.getWeekdayFormatted());
		Dag dag2 = dag1.getNextDag();
		assertEquals("Lördag", dag2.getWeekdayFormatted());
		dag2.addDays(1);
		assertEquals("Söndag", dag2.getWeekdayFormatted());
		dag2.addDays(1);
		assertEquals("Måndag", dag2.getWeekdayFormatted());
		dag2.addDays(1);
		assertEquals("Tisdag", dag2.getWeekdayFormatted());
		dag2.addDays(1);
		assertEquals("Onsdag", dag2.getWeekdayFormatted());
		dag2.addDays(1);
		assertEquals("Torsdag", dag2.getWeekdayFormatted());
	}

	@Test
	public void testBefore() {
		assertTrue(dag1.before(dag2));
		assertFalse(dag2.before(dag2));

		Dag dag3 = new Dag("2007-05-25");
		assertFalse(dag1.before(dag3));
	}

	@Test
	public void testGetToday() {
		Dag today = Dag.getToday();

		Calendar cal = new GregorianCalendar();
		String formattedDate = formatter.format(cal.getTime());
		assertEquals(formattedDate, today.toString());
	}
}
