package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class ConfigTioResorTest {
	private Dag dag;
	private Resa resa;
	private Configbiljett tioresor;

	@Before
	public void setUp() {
		dag = new Dag("2007-11-12");
		resa = new Resa(dag);
		tioresor = Configbiljett.getInstance("Tioresor", resa, 10, 30, 650);
	}

	@Test
	public void testTioResorIsValid() {
		assertTrue(tioresor.add(new Resa(new Dag("2007-11-15"))));
		assertTrue(tioresor.add(new Resa(new Dag("2007-11-18"))));
		assertTrue(tioresor.add(new Resa(new Dag("2007-11-22"))));
		assertTrue(tioresor.add(new Resa(new Dag("2007-11-27"))));
		assertTrue(tioresor.add(new Resa(new Dag("2007-11-28"))));
		assertTrue(tioresor.add(new Resa(new Dag("2007-12-01"))));
		assertTrue(tioresor.add(new Resa(new Dag("2007-12-03"))));
		assertTrue(tioresor.add(new Resa(new Dag("2007-12-04"))));
		assertTrue(tioresor.add(new Resa(new Dag("2007-12-05"))));
		assertFalse(tioresor.add(new Resa(new Dag("2007-12-06"))));
		assertFalse(tioresor.add(new Resa(new Dag("2007-12-10"))));
		assertFalse(tioresor.add(new Resa(new Dag("2007-12-11"))));
		assertFalse(tioresor.add(new Resa(new Dag("2007-12-12"))));
	}

	@Test
	public void testTioResorToString() {
		assertEquals("Tioresor 2007-11-12 t.o.m 2007-12-11", tioresor.toString());
	}
	
	
	@Test
	public void testIsValid() {
		assertTrue(tioresor.isValid(new Resa(new Dag("2007-11-13"))));
		assertFalse(tioresor.isValid(new Resa(new Dag("2008-11-11"))));
	}

	@Test
	public void testToString() {
		assertEquals("Tioresor 2007-11-12 t.o.m 2007-12-11", tioresor.toString());
	}

	@Test
	public void testSetPris() {
		tioresor.setPris(1234);
		assertEquals(1234, tioresor.getPris());
	}

	@Test
	public void testGetForstaGiltighetsdag() {
		assertEquals("2007-11-12", tioresor.getForstaGiltighetsdag().toString());
	}

	@Test
	public void testSetForstaGiltighetsdag() {
		tioresor.setForstaGiltighetsdag(new Dag("2007-11-11"));
		assertEquals("2007-11-11", tioresor.getForstaGiltighetsdag().toString());
	}

	@Test
	public void testGetSistaGiltighetsdag() {
		assertEquals("2007-12-11", tioresor.getSistaGiltighetsdag().toString());
	}

	@Test
	public void sistaGiltighetsdagMasteAndrasNarTioResorHarAnvants() {
		tioresor.add(new Resa(new Dag("2007-11-12")));
		tioresor.add(new Resa(new Dag("2007-11-13")));
		tioresor.add(new Resa(new Dag("2007-11-13")));
		tioresor.add(new Resa(new Dag("2007-11-14")));
		tioresor.add(new Resa(new Dag("2007-11-14")));
		tioresor.add(new Resa(new Dag("2007-11-15")));
		tioresor.add(new Resa(new Dag("2007-11-15")));
		tioresor.add(new Resa(new Dag("2007-11-16")));
		assertEquals("2007-12-11", tioresor.getSistaGiltighetsdag().toString());

		tioresor.add(new Resa(new Dag("2007-11-16")));
		assertEquals("2007-11-16", tioresor.getSistaGiltighetsdag().toString());
	}

	@Test
	public void ytterligareTestGetSistaGiltighetsdagTioResor() {
		Resa resa = new Resa(new Dag("2007-11-19"));
		Biljett tioResor = Configbiljett.getInstance("TioResor", resa, 10, 30, 650);

		assertEquals("2007-12-18", tioResor.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testSetSistaGiltighetsdag() {
		tioresor.setSistaGiltighetsdag(new Dag("2007-12-27"));
		assertEquals("2007-12-27", tioresor.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testGetPris() {
		assertEquals(650, tioresor.getPris());
	}
}
