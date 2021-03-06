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
		assertTrue(manad.isValid(new Resa(new Dag("2007-11-13"))));
		assertTrue(manad.isValid(new Resa(new Dag("2007-12-11"))));
		assertFalse(manad.isValid(new Resa(new Dag("2007-12-12"))));
	}

	@Test
	public void testIsValid() {
		assertTrue(manad.isValid(new Resa(new Dag("2007-11-13"))));
		assertFalse(manad.isValid(new Resa(new Dag("2007-11-11"))));

		Dag dag4 = new Dag("2007-11-12");
		Resa resa4 = new Resa(dag4.plusDays(34));
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
		assertTrue(manad.add(new Resa(new Dag("2007-11-13"))));
		assertEquals(2, manad.resor.size());
		assertFalse(manad.add(new Resa(new Dag("2007-12-29"))));
	}

	@Test
	public void testGetPris() {
		assertEquals(1900, manad.getPris());
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldFailWithTooLowAntalResor() {
		Resa resa2 = new Resa(new Dag("2007-11-19"));
		Configbiljett.getInstance("Månadskort", resa2, -1, 30, 1900);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldFailWithTooLowAntalDagar() {
		Resa resa2 = new Resa(new Dag("2007-11-19"));
		Configbiljett.getInstance("Månadskort", resa2, 3, -3, 1900);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldFailWithTooLowPrice() {
		Resa resa2 = new Resa(new Dag("2007-11-19"));
		Configbiljett.getInstance("Månadskort", resa2, 3, 30, -4);
	}
}
