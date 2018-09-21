package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

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
		Dag dag2 = new Dag("2007-11-13");
		Resa resa2 = new Resa(dag2);
		assertTrue(tioresor.isValid(resa2));

		Dag dag3 = new Dag("2008-11-11");
		Resa resa3 = new Resa(dag3);
		assertFalse(tioresor.isValid(resa3));
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
		dag = new Dag("2007-11-12");
		assertEquals("2007-12-11", tioresor.getSistaGiltighetsdag().toString());

		// Ytterligare test d� jag tror att jag r�knade fel f�rut

		Dag dag3 = new Dag("2007-11-19");
		Resa resa3 = new Resa(dag3);
		Properties props3 = new Properties();
		props3.setProperty("priceTioresor", "650");
//		TioResor tioResor3 = new TioResor(resa3, props);
		Biljett tioResor3 = Configbiljett.getInstance("TioResor", resa3, 10, 30, 650);

		assertEquals("2007-12-18", tioResor3.getSistaGiltighetsdag().toString());

		// Sista giltdag m�ste �ndras n�r man anv�nt alla 10 resor
		Dag dag2 = new Dag("2007-11-12");
		Resa resa2 = new Resa(dag2);
		tioresor.add(resa2);
		dag2 = new Dag("2007-11-13");
		resa2 = new Resa(dag2);
		tioresor.add(resa2);
		dag2 = new Dag("2007-11-13");
		resa2 = new Resa(dag2);
		tioresor.add(resa2);
		dag2 = new Dag("2007-11-14");
		resa2 = new Resa(dag2);
		tioresor.add(resa2);
		dag2 = new Dag("2007-11-14");
		resa2 = new Resa(dag2);
		tioresor.add(resa2);
		dag2 = new Dag("2007-11-15");
		resa2 = new Resa(dag2);
		tioresor.add(resa2);
		dag2 = new Dag("2007-11-15");
		resa2 = new Resa(dag2);
		tioresor.add(resa2);
		dag2 = new Dag("2007-11-16");
		resa2 = new Resa(dag2);
		tioresor.add(resa2);
		assertEquals("2007-12-11", tioresor.getSistaGiltighetsdag().toString());
		dag2 = new Dag("2007-11-16");
		resa2 = new Resa(dag2);
		tioresor.add(resa2);
		assertEquals("2007-11-16", tioresor.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testSetSistaGiltighetsdag() {
		Dag dag2 = new Dag("2007-12-27");
		tioresor.setSistaGiltighetsdag(dag2);
		assertEquals("2007-12-27", tioresor.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testAdd() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testGetPris() {
		assertEquals(650, tioresor.getPris());
	}

}
