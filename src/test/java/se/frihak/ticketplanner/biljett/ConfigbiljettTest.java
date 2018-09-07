package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class ConfigbiljettTest {

	private Dag dag;
	private Resa resa;
	private Configbiljett enkel;
	private Configbiljett manad;
	private Configbiljett tioresor;

	@Before
	public void setUp() {
		dag = new Dag("2007-11-12");
		resa = new Resa(dag);
		enkel = Configbiljett.getInstance("Enkel", resa, 1, 1, 123);
		manad = Configbiljett.getInstance("Manad", resa, 48, 30, 123);
		tioresor = Configbiljett.getInstance("TioResor", resa, 10, 30, 123);
	}

	@Test
	public void testEnkelIsValid() {
		assertFalse(enkel.isValid(resa));

		Resa resa2 = new Resa(new Dag("2007-11-13"));
		assertFalse(enkel.isValid(resa2));

		Resa resa3 = new Resa(new Dag("2007-11-11"));
		assertFalse(enkel.isValid(resa3));
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
	public void testEnkelToString() {
		assertEquals("Enkel 2007-11-12", enkel.toString());
	}

	@Test
	public void testManadToString() {
		assertEquals("Manad 2007-11-12", manad.toString());
	}

	@Test
	public void testTioResorToString() {
		assertEquals("TioResor 2007-11-12", tioresor.toString());
	}
}
