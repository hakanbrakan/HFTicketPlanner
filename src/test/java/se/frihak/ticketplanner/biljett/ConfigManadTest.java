package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class ConfigManadTest {
	private Resa resa;
	private Configbiljett manad;

	@Before
	public void setUp() {
		resa = new Resa(new Dag("2007-11-12"));
		manad = Configbiljett.getInstance("Månadskort", resa, 48, 30, 1900);
	}

	@Test
	public void testManadIsValid() {
		assertTrue(manad.isValid(resa));

		Resa resa2 = new Resa(new Dag("2007-11-13"));
		assertTrue(manad.isValid(resa2));

		Resa resa3 = new Resa(new Dag("2007-12-11"));
		assertTrue(manad.isValid(resa3));

		assertFalse(manad.isValid(new Resa(new Dag("2007-12-12"))));
	}

	//TODO Nytt nedan. Gamla tester from gamla testklassen
	@Test
	public void testIsValid() {
		Dag dag2 = new Dag("2007-11-13");
		Resa resa2 = new Resa(dag2);
		assertTrue(manad.isValid(resa2));

		Dag dag3 = new Dag("2007-11-11");
		Resa resa3 = new Resa(dag3);
		assertFalse(manad.isValid(resa3));

		Dag dag4 = new Dag("2007-11-12");
		dag4.addDays(34);
		Resa resa4 = new Resa(dag4);
		assertFalse(manad.isValid(resa4));
	}

	@Test
	public void testToString() {
		assertEquals("Månadskort 2007-11-12 t.o.m 2007-12-11", manad.toString());
	}

	@Test
	public void testSetPris() {
		manad.setPris(1345);
		assertEquals(1345, manad.getPris());
	}

	@Test
	public void testGetForstaGiltighetsdag() {
		assertEquals("2007-11-12", manad.getForstaGiltighetsdag().toString());
	}

	@Test
	public void testSetForstaGiltighetsdag() {
		manad.setForstaGiltighetsdag(new Dag("2007-05-05"));
		assertEquals("2007-05-05", manad.getForstaGiltighetsdag().toString());
	}

	@Test
	public void testGetSistaGiltighetsdag() {
		assertEquals("2007-12-11", manad.getSistaGiltighetsdag().toString());

		Dag dag2 = new Dag("2007-11-19");
		Resa resa2 = new Resa(dag2);
		Properties props2 = new Properties();
		props2.setProperty("priceManad", "1900");
		Configbiljett manad2 = Configbiljett.getInstance("Månadskort", resa2, 48, 30, 1900);
		assertEquals("2007-12-18", manad2.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testSetSistaGiltighetsdag() {
		manad.setSistaGiltighetsdag(new Dag("2007-01-01"));
		assertEquals("2007-01-01", manad.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testAdd() {
		// TODO Jobba mer h�r fail("Not yet implemented");
	}

	@Test
	public void testGetPris() {
		assertEquals(1900, manad.getPris());
	}
}
